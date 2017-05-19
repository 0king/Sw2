package g.sw2.model;

import java.util.List;

/**
 * Created by Kush Agrawal on 4/4/2017.
 */

public class DataInfo {
	
	/* not using this class, delete it */
	private String class_level;
	private String board;
	private String language_of_communication;
	private String school;
	private String country;
	private String area;
	private String storage_type;
	private String content_type;
	private String categories_included;
	private String tags;
	private String storage_list;
	
	private List<Subject> subjects_list;
	
	public String getClassLevel() {
		return class_level;
	}
}
