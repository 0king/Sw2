package g.sw2.model;

/**
 * Created by 5dr on 17/04/17.
 */

public class SessionData {
	
	private static SessionData instance;
	
	static {
		instance = new SessionData();
	}
	
	int noOfCheckpoints;
	String[] sessionDataArray;
	/* Other properties */
	int noOfMiniCheckpoints;
	int noOfSubjects;
	int noOfBridges;
	int noOfAcademicCheckpoints;
	int noOfNonAcademicCheckpoints;
	
	private SessionData() {
	}
	
	public static SessionData getInstance() {
		return instance;
	}
	
	public void init() {
		//this.noOfCheckpoints=
		sessionDataArray = new String[noOfCheckpoints];
		//sessionDataArray[0]=
	}
	
}
