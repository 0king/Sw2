package g.sw2.database;

/**
 * Created by 5dr on 25/04/17.
 */

public class DailyLog {
	private long id;
	private String date;
	private long duration;
	private boolean studiedToday;
	//others - get from the schema
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public boolean isStudiedToday() {
		return studiedToday;
	}
	
	public void setStudiedToday(boolean studiedToday) {
		this.studiedToday = studiedToday;
	}
}
