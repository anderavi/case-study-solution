package com.app.imagejson.ocr.vo;

public class BillItem {
	private String pro;
	private String srcPo;
	private String vendorName;
	private String edi;
	private String seq;
	private Integer sched;
	private Integer recv;
	private String externalDamagedRecv;
	private String returnedToCarrier;
	private String probArea;
	private String expNo;
	private String comments;

	public String getPro() {
		return pro;
	}

	public void setPro(String pro) {
		this.pro = pro;
	}

	public String getSrcPo() {
		return srcPo;
	}

	public void setSrcPo(String srcPo) {
		this.srcPo = srcPo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEdi() {
		return edi;
	}

	public void setEdi(String edi) {
		this.edi = edi;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public Integer getSched() {
		return sched;
	}

	public void setSched(Integer sched) {
		this.sched = sched;
	}

	public Integer getRecv() {
		return recv;
	}

	public void setRecv(Integer recv) {
		this.recv = recv;
	}

	public String getExternalDamagedRecv() {
		return externalDamagedRecv;
	}

	public void setExternalDamagedRecv(String externalDamagedRecv) {
		this.externalDamagedRecv = externalDamagedRecv;
	}

	public String getReturnedToCarrier() {
		return returnedToCarrier;
	}

	public void setReturnedToCarrier(String returnedToCarrier) {
		this.returnedToCarrier = returnedToCarrier;
	}

	public String getProbArea() {
		return probArea;
	}

	public void setProbArea(String probArea) {
		this.probArea = probArea;
	}

	public String getExpNo() {
		return expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "BillItem [pro=" + pro + ", srcPo=" + srcPo + ", vendorName=" + vendorName + ", edi=" + edi + ", seq="
				+ seq + ", sched=" + sched + ", recv=" + recv + ", externalDamagedRecv=" + externalDamagedRecv
				+ ", returnedToCarrier=" + returnedToCarrier + ", probArea=" + probArea + ", expNo=" + expNo
				+ ", comments=" + comments + "]";
	}

}
