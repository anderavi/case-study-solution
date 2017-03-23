package com.app.imagejson.ocr.vo;

import java.util.List;

public class Bill {

	private BillInfo billInfo;
	private List<BillItem> billItems;

	public BillInfo getBillInfo() {
		return billInfo;
	}

	public void setBillInfo(BillInfo billInfo) {
		this.billInfo = billInfo;
	}

	public List<BillItem> getBillItems() {
		return billItems;
	}

	public void setBillItems(List<BillItem> billItems) {
		this.billItems = billItems;
	}

	@Override
	public String toString() {
		return "Bill [billInfo=" + billInfo + ", billItems=" + billItems + "]";
	}

}
