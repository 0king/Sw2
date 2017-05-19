package g.sw2.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 5dr on 25/02/17.
 */

public class Card {
	
	/* data, core values*/
	@SerializedName("card_id")
	private long cardId;
	@SerializedName("card_text")
	private String mainText = "";
	@SerializedName("card_media_url")
	private String mediaUrl = "";
	@SerializedName("card_image_url")
	private String imageUrl = "";
	private String card_text_above = "";
	private String card_text_below = "";
	
	/* format */
	@SerializedName("card_design_format")
	private String designFormat = "text";//image,card_item_image,card_item_image+image,video+card_item_image,etc
	@SerializedName("card_is_latex")
	private boolean isLatex = false;
	private String card_image_type = "portrait";
	
	
	/* styling */
	@SerializedName("card_background_color")
	private String bgColor = "#ffffff";
	@SerializedName("card_font_type")
	private String fontType = "ubuntu";
	@SerializedName("card_text_alignment")
	private String textAlignment = "left";
	@SerializedName("card_gravity")
	private String cardGravity = "center";
	@SerializedName("card_text_color")
	private String textColor = "#000000";
	@SerializedName("card_text_style")
	private String textStyle = "normal";//normal, bold, italics
	private int card_font_size = 11;
	
	
	/* structure */
	@SerializedName("card_heading")
	private String heading1 = "";
	@SerializedName("card_contains_input_field")
	private boolean containsInputField = false;
	@SerializedName("card_extra_bottom_information")
	private String extraInfo = "";
	@SerializedName("card_contains_buttons")
	private boolean containsButtons = false;
	@SerializedName("card_button1_name")
	private String btn1Name = "";
	@SerializedName("card_button2_name")
	private String btn2Name;
	@SerializedName("card_button3_name")
	private String btn3Name;
	
	/* other data required by chapter and topic cards*/
	private int duration = 0;
	private List<Card> chapter_learning_objectives = new ArrayList<>();
	private List<Card> chapter_summary = new ArrayList<>();
	private int chapter_duration = 0;
	private String chapter_image_url = "";
	
	
	//public Card(){}
	
	public Card(long id, String text, String designFormat, int duration, String imageUrl, List<Card> chapterLearningObjectives, List<Card> chapter_summary) {
		this.cardId = id;
		this.mainText = text;
		this.designFormat = designFormat;
		this.duration = duration;
		this.imageUrl = imageUrl;
		this.chapter_learning_objectives = chapterLearningObjectives;
		this.chapter_summary = chapter_summary;
	}
	
	
	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getText() {
		return mainText;
	}

	public void setText(String text) {
		this.mainText = text;
	}
	
	public String getCardType() {
		return designFormat;
	}
	
	public String getMainText() {
		return mainText;
	}
	
	public void setMainText(String mainText) {
		this.mainText = mainText;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getDesignFormat() {
		return designFormat;
	}
	
	public void setDesignFormat(String designFormat) {
		this.designFormat = designFormat;
	}
	
	public String getBgColor() {
		return bgColor;
	}
	
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	
	public String getFontType() {
		return fontType;
	}
	
	public void setFontType(String fontType) {
		this.fontType = fontType;
	}
	
	public String getTextAlignment() {
		return textAlignment;
	}
	
	public void setTextAlignment(String textAlignment) {
		this.textAlignment = textAlignment;
	}
	
	public String getCardGravity() {
		return cardGravity;
	}
	
	public void setCardGravity(String cardGravity) {
		this.cardGravity = cardGravity;
	}
	
	public String getTextColor() {
		return textColor;
	}
	
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
	
	public String getTextStyle() {
		return textStyle;
	}
	
	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}
	
	public String getHeading1() {
		return heading1;
	}
	
	public void setHeading1(String heading1) {
		this.heading1 = heading1;
	}
	
	
	
	public String getExtraInfo() {
		return extraInfo;
	}
	
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	
	public String getBtn1Name() {
		return btn1Name;
	}
	
	public void setBtn1Name(String btn1Name) {
		this.btn1Name = btn1Name;
	}
	
	public String getBtn2Name() {
		return btn2Name;
	}
	
	public void setBtn2Name(String btn2Name) {
		this.btn2Name = btn2Name;
	}
	
	public String getBtn3Name() {
		return btn3Name;
	}
	
	public void setBtn3Name(String btn3Name) {
		this.btn3Name = btn3Name;
	}
	
	
	public String getCard_text_above() {
		return card_text_above;
	}
	
	public void setCard_text_above(String card_text_above) {
		this.card_text_above = card_text_above;
	}
	
	public String getCard_text_below() {
		return card_text_below;
	}
	
	public void setCard_text_below(String card_text_below) {
		this.card_text_below = card_text_below;
	}
	
	public long getCardId() {
		return cardId;
	}
	
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	
	public boolean isLatex() {
		return isLatex;
	}
	
	public void setLatex(boolean latex) {
		isLatex = latex;
	}
	
	public String getCard_image_type() {
		return card_image_type;
	}
	
	public void setCard_image_type(String card_image_type) {
		this.card_image_type = card_image_type;
	}
	
	public int getCard_font_size() {
		return card_font_size;
	}
	
	public void setCard_font_size(int card_font_size) {
		this.card_font_size = card_font_size;
	}
	
	public boolean isContainsInputField() {
		return containsInputField;
	}
	
	public void setContainsInputField(boolean containsInputField) {
		this.containsInputField = containsInputField;
	}
	
	public boolean isContainsButtons() {
		return containsButtons;
	}
	
	public void setContainsButtons(boolean containsButtons) {
		this.containsButtons = containsButtons;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public List<Card> getChapter_learning_objectives() {
		return chapter_learning_objectives;
	}
	
	public void setChapter_learning_objectives(List<Card> chapter_learning_objectives) {
		this.chapter_learning_objectives = chapter_learning_objectives;
	}
	
	public List<Card> getChapter_summary() {
		return chapter_summary;
	}
	
	public void setChapter_summary(List<Card> chapter_summary) {
		this.chapter_summary = chapter_summary;
	}
	
	public int getChapter_duration() {
		return chapter_duration;
	}
	
	public void setChapter_duration(int chapter_duration) {
		this.chapter_duration = chapter_duration;
	}
	
	public String getChapter_image_url() {
		return chapter_image_url;
	}
	
	public void setChapter_image_url(String chapter_image_url) {
		this.chapter_image_url = chapter_image_url;
	}
}
