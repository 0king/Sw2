package g.sw2;

/**
 * Created by 5dr on 25/02/17.
 */

public class Card {

	String cardId;
	String text;
	String mediaUrl;

	//other details...


	public Card(String cardId, String mediaUrl, String text) {
		this.cardId = cardId;
		this.mediaUrl = mediaUrl;
		this.text = text;
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
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
