package g.sw2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kush Agrawal on 4/4/2017.
 */

public class Chapter {
	
	/* using this class */
	
	private long chapter_id;
	private String chapter_name = "";
	private int chapter_duration = 0;
	private String chapter_image_url = "";
	private List<Card> chapter_learning_objectives = new ArrayList<>();
	private List<Card> chapter_summary = new ArrayList<>();
	private List<Topic> topics_list = new ArrayList<>();
	
	
	/*others:*/
	/*private String chapter_number;
	private String chapter_importance;
	private String chapter_time_expected;
	private String chapter_average_time_taken_by_users;
	private String chapter_shortest_time_taken_by_user;
	private String chapter_included_in_syllabus;
	private String chapter_exam_potential;
	private String chapter_difficulty;
	private String chapter_rating;
	private String chapter_author;
	private String chapter_created;
	private String chapter_comments;
	private String chapter_user_feedback;
	private String chapter_user_interaction;
	private String total_number_of_topics;*/
	
	
}
