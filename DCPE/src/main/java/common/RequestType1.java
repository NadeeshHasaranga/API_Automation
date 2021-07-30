package common;


public class RequestType1 {

 Long id;
 String approveRejectTime;
 String rejectReason;
 String status;
 
 
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getApproveRejectTime() {
	return approveRejectTime;
}
public void setApproveRejectTime(String approveRejectTime) {
	this.approveRejectTime = approveRejectTime;
}
public String getRejectReason() {
	return rejectReason;
}
public void setRejectReason(String rejectReason) {
	this.rejectReason = rejectReason;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
	
}


//"{\n" +
//"  \"id\": \"2\",\n" +
//"  \"approveRejectTime\": \"2017-01-13T17:09:42.411\"\",\n" +
//"  \"rejectReason\": \"valid\"\",\n" +
//"  \"status\": \"approved\" \n}";
