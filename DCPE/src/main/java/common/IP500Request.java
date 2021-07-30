package common;

public class IP500Request {
	int productCategory;
	int sbu;
	int reqType;
	String requestUserId;
	String remoteIP;
	String sourceSystem;
	public int getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}
	public int getSbu() {
		return sbu;
	}
	public void setSbu(int sbu) {
		this.sbu = sbu;
	}
	public int getReqType() {
		return reqType;
	}
	public void setReqType(int reqType) {
		this.reqType = reqType;
	}
	public String getRequestUserId() {
		return requestUserId;
	}
	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId;
	}
	public String getRemoteIP() {
		return remoteIP;
	}
	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}
	public String getSourceSystem() {
		return sourceSystem;
	}
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	

}
