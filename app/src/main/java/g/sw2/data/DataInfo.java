package g.sw2.data;

import java.util.List;

/**
 * Created by Kush Agrawal on 4/4/2017.
 */

public class DataInfo {

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

    public String getClassLevel(){
        return class_level;
    }
}
