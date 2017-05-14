package g.sw2.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 5dr on 25/02/17.
 */

public class Card {
	
	/* data, core values*/
	@SerializedName("card_id")
	private String cardId;
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
	private String designFormat = "card_item_image";//image,card_item_image,card_item_image+image,video+card_item_image,etc
	@SerializedName("card_is_latex")
	private String isLatex = "no";
	
	
	/* styling */
	@SerializedName("card_background_color")
	private String bgColor = "white";
	@SerializedName("card_font_type")
	private String fontType = "ubuntu";
	@SerializedName("card_text_alignment")
	private String textAlignment = "left";
	@SerializedName("card_gravity")
	private String cardGravity = "center";
	@SerializedName("card_text_color")
	private String textColor = "black";
	@SerializedName("card_text_style")
	private String textStyle = "normal";//normal, bold, italics
	
	/* structure */
	@SerializedName("card_heading")
	private String heading1 = "";
	@SerializedName("card_contains_input_field")
	private String containsInputField = "no";
	@SerializedName("card_extra_bottom_information")
	private String extraInfo = "";
	@SerializedName("card_contains_buttons")
	private String containsButtons = "no";
	@SerializedName("card_button1_name")
	private String btn1Name = "";
	@SerializedName("card_button2_name")
	private String btn2Name;
	@SerializedName("card_button3_name")
	private String btn3Name;
	
	/* other data required by chapter and topic cards*/
	private String duration = "";
	private String chapterLearningObjectives = "";
	
	
	//public Card(){}
	
	public Card(String id, String text, String designFormat, String duration, String imageUrl, String chapterLearningObjectives) {
		this.cardId = id;
		this.mainText = text;
		this.designFormat = designFormat;
		this.duration = duration;
		this.imageUrl = imageUrl;
		this.chapterLearningObjectives = chapterLearningObjectives;
	}

	public Card(String cardId, String mediaUrl, String text,String card_type) {
		this.cardId = cardId;
		this.mediaUrl = mediaUrl;
		this.mainText = text;
		this.designFormat = card_type;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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
	
	public String getIsLatex() {
		return isLatex;
	}
	
	public void setIsLatex(String isLatex) {
		this.isLatex = isLatex;
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
	
	public String getContainsInputField() {
		return containsInputField;
	}
	
	public void setContainsInputField(String containsInputField) {
		this.containsInputField = containsInputField;
	}
	
	public String getExtraInfo() {
		return extraInfo;
	}
	
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	public String getContainsButtons() {
		return containsButtons;
	}
	
	public void setContainsButtons(String containsButtons) {
		this.containsButtons = containsButtons;
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
	
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getChapterLearningObjectives() {
		return chapterLearningObjectives;
	}
	
	public void setChapterLearningObjectives(String chapterLearningObjectives) {
		this.chapterLearningObjectives = chapterLearningObjectives;
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
}
