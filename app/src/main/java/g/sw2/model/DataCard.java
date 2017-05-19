package g.sw2.model;

/**
 * Created by 5dr on 13/12/16.
 */

public class DataCard {
	
	/* not using this class, delete it*/

	static int cardId;
	private String imageUrl;
	private String text;

	void DataCard(String imageUrl, String text){
		cardId++;
		this.imageUrl = imageUrl;
		this.text = text;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
