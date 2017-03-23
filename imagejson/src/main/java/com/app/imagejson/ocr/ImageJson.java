package com.app.imagejson.ocr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.app.imagejson.ocr.exceptions.ImagePathException;
import com.app.imagejson.ocr.exceptions.OcrException;
import com.app.imagejson.ocr.exceptions.OcrProcessingException;
import com.app.imagejson.ocr.util.FileUtil;
import com.app.imagejson.ocr.util.OcrUtil;
import com.app.imagejson.ocr.vo.Bill;
import com.app.imagejson.ocr.vo.BillInfo;
import com.app.imagejson.ocr.vo.BillItem;
import com.google.gson.Gson;

public class ImageJson {

	public void process() throws OcrException, OcrProcessingException, ImagePathException, IOException  {
		String location = System.getProperty("billsLocation");
		File folder = new File(location);
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				continue;
			} else {
				String text = "";
				try {
					text = OcrUtil.doOCR(fileEntry.getAbsolutePath());
				} catch (OcrException e) {
					throw e;
				} catch (Exception e) {
					throw new OcrProcessingException("Exception processing ocr for file entry "+ fileEntry.getAbsolutePath());
				}
				File textDir = new File(String.valueOf(location + "text/"));
				if (!textDir.exists()) {
					textDir.mkdirs();
				}
				FileUtil.writeToFile(location + "text/" + fileEntry.getName() + ".txt", text);

				Bill bill = getBill(text);
				Gson gson = new Gson();
				String json = gson.toJson(bill);
				File jsonDir = new File(String.valueOf(location + "json/"));
				if (!jsonDir.exists()) {
					jsonDir.mkdirs();
				}
				FileUtil.writeToFile(location + "json/" + fileEntry.getName() + ".json", json);
			}
		}
	}

	public Bill getBill(String text) throws IOException {
		Bill bill = null;
		InputStream is = new ByteArrayInputStream(text.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		List<String> infoLines = new ArrayList<String>();
		List<String> itemLines = new ArrayList<String>();
		String line;
		boolean itemsOn = false;
		while ((line = br.readLine()) != null) {
			if (line.isEmpty())
				continue;
			if (line.contains("PRO") && line.contains("EDI")) {
				itemsOn = true;
				continue;
			}
			if (itemsOn) {
				itemLines.add(line);
			} else {
				infoLines.add(line);
			}
		}
		br.close();

		if (!infoLines.isEmpty()) {
			BillInfo info = getBillInfo(infoLines);
			bill = new Bill();
			bill.setBillInfo(info);
		}
		if (!itemLines.isEmpty()) {
			List<BillItem> items = getBillItems(itemLines);
			if (bill == null) {
				bill = new Bill();
			}
			bill.setBillItems(items);
		}

		return bill;
	}

	private List<BillItem> getBillItems(List<String> itemLines) {
		// PRO Src / PO Vendor Name EDI Seq Sched Recv Rcv'd Carrier Area #
		// Comma,“
		// 1851910114 0083/4223720 COSMOS CORPOR 16 0 1‘
		// 1551910114 0053/4337523 HEAD/PENN RAG V 1 67
		// 394196244 007118504224 ANGIES KETIL 2 119 30 L
		// 4586016863 008019871923 BEST CASE & A Y 3 71 7 V

		List<BillItem> items = new ArrayList<BillItem>();
		for (String line : itemLines) {
			BillItem item = new BillItem();

			String spaceSplit[] = line.split(" ");
			if (spaceSplit.length > 2) {
				item.setPro(spaceSplit[0]);
				item.setSrcPo(spaceSplit[1]);
			}

			String ediSplit[] = line.split(" Y ");
			if (ediSplit.length == 2) {
				item.setEdi("Y");

				String part1 = ediSplit[0];
				String vendArr[] = part1.split(" ");
				String vendorName = "";
				for (int i = 2; i < vendArr.length; i++) {
					vendorName += " " + vendArr[i];
				}
				item.setVendorName(vendorName);
				// 4586016863 008019871923 BEST CASE & A Y 3 71 7 V
				String part2 = ediSplit[1];
				String part2Arr[] = part2.split(" ");
				if (part2.length() >= 1) {
					item.setSeq(part2Arr[0]);
				}
				if (part2.length() >= 2) {
					int sched = 0;
					try {
						sched = Integer.parseInt(part2Arr[1]);
						item.setSched(sched);
					} catch (Exception e) {
					}
				}
				if (part2.length() >= 3) {
					int recv = 0;
					try {
						recv = Integer.parseInt(part2Arr[2]);
						item.setRecv(recv);
					} catch (Exception e) {

					}
				}
			} else {
				item.setEdi("N");
				// 394196244 007118504224 ANGIES KETIL 2 119 30 L
				if (spaceSplit.length > 3) {
					String vendorName = "";
					int i = 2;
					for (; i < spaceSplit.length; i++) {
						if (!isNumeric(spaceSplit[i])) {
							vendorName += " " + spaceSplit[i];
						} else {
							break;
						}
					}
					item.setVendorName(vendorName);
					try {
						String seq = spaceSplit[i++];
						item.setSeq(seq);
						String schedStr = spaceSplit[i++];
						try {
							int sched = Integer.parseInt(schedStr);
							item.setSched(sched);
						} catch (Exception e) {
						}
						String recvStr = spaceSplit[i++];
						try {
							int recv = Integer.parseInt(recvStr);
							item.setRecv(recv);
						} catch (Exception e) {
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}
			items.add(item);
		}
		return items;
	}

	private BillInfo getBillInfo(List<String> infoLines) {
		BillInfo info = new BillInfo();
		for (String line : infoLines) {

			// Repun ID- DELIVERY RECEIPT Dam; 0311212016 00:11:33
			info.setReportId("");
			if (line.contains("Dam;") || line.contains("Dam:") || line.contains("Date;") || line.contains("Date:")) {
				String[] arr = line.split("Dam;");
				if (arr.length < 2) {
					arr = line.split("Dam:");
				}
				if (arr.length < 2) {
					arr = line.split("Date;");
				}
				if (arr.length < 2) {
					arr = line.split("Date:");
				}
				if (arr.length >= 2) {
					info.setDate(arr[1]);
				}
			}

			// DC:
			if (line.contains("DC:")) {
				String[] arr = line.split("DC:");
				if (arr.length >= 2) {
					info.setDc(arr[1]);
				}
			}
			// Address:
			if (line.contains("Address:")) {
				String[] arr = line.split("Address:");
				if (arr.length >= 2) {
					info.setAddress(arr[1]);
				}
			}
			// Scat: RDWY 1
			if (line.contains("Scat:") || line.contains("Scac:")) {
				String[] arr = line.split("Scat:");
				if (arr.length < 2) {
					arr = line.split("Scac:");
				}
				if (arr.length >= 2) {
					info.setScac(arr[1]);
				}
			}
			// Cons Scan: ‘
			if (line.contains("Cons Scan:") || line.contains("Cons Scac:")) {
				String[] arr = line.split("Cons Scan:");
				if (arr.length < 2) {
					arr = line.split("Cons Scac:");
				}
				if (arr.length >= 2) {
					info.setConsScac(arr[1]);
				}
			}
			// Manifest: 1
			if (line.contains("Manifest:")) {
				String[] arr = line.split("Manifest:");
				if (arr.length >= 2) {
					info.setManifest(arr[1]);
				}
			}

			// ART Ranking Score:
			if (line.contains("ART Ranking Score:")) {
				String[] arr = line.split("ART Ranking Score:");
				if (arr.length >= 2) {
					info.setArtRankingScore(arr[1]);
				}
			}
			// Appt Comment: ‘
			if (line.contains("Appt Comment:")) {
				String[] arr = line.split("Appt Comment:");
				if (arr.length >= 2) {
					info.setApptComment(arr[1]);
				}
			}
			// Trailer: ‘
			if (line.contains("Trailer:")) {
				String[] arr = line.split("Trailer:");
				if (arr.length >= 2) {
					info.setTailer(arr[1]);
				}
			}

		}
		return info;
	}

	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}
