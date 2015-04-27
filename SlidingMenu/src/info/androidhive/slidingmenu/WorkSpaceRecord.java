package info.androidhive.slidingmenu;

public class WorkSpaceRecord {
	private String date;
	private String subject;
	private String notes;
	public WorkSpaceRecord(String date, String subject, String notes) {
	this.date = date;
	this.subject=subject;
	this.notes = notes;
}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
