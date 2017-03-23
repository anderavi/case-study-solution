package com.app.imagejson.ocr.vo;

public class BillInfo {
	private String reportId;
	private String date;
	private String dc;
	private String address;
	private String scac;
	private String consScac;
	private String tailer;
	private String manifest;
	private String artRankingScore;
	private String apptComment;
	private Integer totalSced;
	private Integer totalRecv;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getScac() {
		return scac;
	}

	public void setScac(String scac) {
		this.scac = scac;
	}

	public String getConsScac() {
		return consScac;
	}

	public void setConsScac(String consScac) {
		this.consScac = consScac;
	}

	public String getTailer() {
		return tailer;
	}

	public void setTailer(String tailer) {
		this.tailer = tailer;
	}

	public String getManifest() {
		return manifest;
	}

	public void setManifest(String manifest) {
		this.manifest = manifest;
	}

	public String getArtRankingScore() {
		return artRankingScore;
	}

	public void setArtRankingScore(String artRankingScore) {
		this.artRankingScore = artRankingScore;
	}

	public String getApptComment() {
		return apptComment;
	}

	public void setApptComment(String apptComment) {
		this.apptComment = apptComment;
	}

	public Integer getTotalSced() {
		return totalSced;
	}

	public void setTotalSced(Integer totalSced) {
		this.totalSced = totalSced;
	}

	public Integer getTotalRecv() {
		return totalRecv;
	}

	public void setTotalRecv(Integer totalRecv) {
		this.totalRecv = totalRecv;
	}

	@Override
	public String toString() {
		return "BillInfo [reportId=" + reportId + ", date=" + date + ", dc=" + dc + ", address=" + address + ", scac="
				+ scac + ", consScac=" + consScac + ", tailer=" + tailer + ", manifest=" + manifest
				+ ", artRankingScore=" + artRankingScore + ", apptComment=" + apptComment + ", totalSced=" + totalSced
				+ ", totalRecv=" + totalRecv + "]";
	}

}
