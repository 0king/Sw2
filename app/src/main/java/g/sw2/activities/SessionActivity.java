package g.sw2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import g.sw2.Card;
import g.sw2.R;
import g.sw2.fragments.FragmentAnimation;
import g.sw2.swipelib.SwipeFlingAdapterView;
import io.github.kexanie.library.MathView;


public class SessionActivity extends AppCompatActivity {

	SwipeFlingAdapterView swipeView;
	ArrayAdapter<String> arrayAdapter;
	ArrayList<String> itemList;//todo create and use card object instead of String
	int i;
	TextView cardText;
	ArrayList<Card> cardList;
	CardAdapter cardAdapter;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home);

		swipeView = (SwipeFlingAdapterView) findViewById(R.id.swipecards);
		swipeView.bringToFront();

		cardList = new ArrayList<>();
		cardList.add(new Card("123","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT"));
		cardList.add(new Card("456","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","IMAGE"));
		cardList.add(new Card("567","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT_IMAGE"));
		cardList.add(new Card("678","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT"));
		cardList.add(new Card("890","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT"));
        cardList.add(new Card("123","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT"));
        cardList.add(new Card("456","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","IMAGE"));
        cardList.add(new Card("567","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT_IMAGE"));
        cardList.add(new Card("678","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","IMAGE"));
        cardList.add(new Card("890","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT"));


        //todo create a default image space, show image loading there

		cardAdapter = new CardAdapter(this, cardList);
		swipeView.setAdapter(cardAdapter);
        swipeView.setMaxVisible(cardAdapter.getCount());
		swipeView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
			@Override
			public void removeFirstObjectInAdapter() {
				/*itemList.remove(0);
				arrayAdapter.notifyDataSetChanged();*/
				cardList.remove(0);
				cardAdapter.notifyDataSetChanged();
			}

			@Override
			public void onLeftCardExit(Object o) {
				cardAdapter.notifyDataSetChanged();
			}

			@Override
			public void onRightCardExit(Object o) {
				cardAdapter.notifyDataSetChanged();
			}

			@Override
			public void onAdapterAboutToEmpty(int itemsInAdapter) {
				cardAdapter.notifyDataSetChanged();
                if(itemsInAdapter ==0) {
                    Intent intent = new Intent(SessionActivity.this, PostSessionActivity.class);
                    startActivity(intent);
                }

			}

			@Override
			public void onScroll(float v) {

			}
		});


		/* added this line to make textview scrollable */
		//cardText = (TextView) view.findViewById(R.id.cardText);
		//cardText.setMovementMethod(new ScrollingMovementMethod());//todo how to initialise textview in item layout

		/* code for Crashlytics */

		//todo check how to do this type of linking
/*		Button crashBtn = (Button) view.findViewById(R.id.crash_button);
		crashBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				throw new RuntimeException("OnClickListener: This is a crash");
			}
		});*/

	}


	public class CardAdapter extends ArrayAdapter<Card>{

		//todo use view holder

		public CardAdapter(Context context, ArrayList<Card> items) {
			super(context, 0, items);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Get the data item for this position
			Card card = getItem(position);
            String type = card.getCardType();
			// Check if an existing view is being reused, otherwise inflate the view
            if(type.equals("TEXT")){
                if (convertView == null) {
                    convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.item, parent, false);
                }
                // Lookup view for data population
                MathView cardText = null  ;
                cardText= (MathView) convertView.findViewById(R.id.formula_one);

                String tex = "<center>Greetings, user’s name!!!\n" +
                        "\n" +
                        "I am your study guide \n" +
                        "and assistant.\n" +
                        "I will help you study better,\n" +
                        "Work better,\n" +
                        "Focus better,\n" +
                        "And perform consistently.\n" +
                        "\n" +
                        "Just be honest with your\n" +
                        "commitments you make\n" +
                        "here and I will help you\n" +
                        "become a genius.\n <center>";
                //ImageView cardImage = (ImageView) convertView.findViewById(R.id.cardImage);

                // Populate the data into the template view using the data object
                cardText.setText(tex);
                WebSettings webSettings = cardText.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setAppCacheEnabled( true );
                webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

                cardText.setWebViewClient(new WebViewClient(){

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        Log.d("Zenius","shouldOverrideUrlLoading: " );
                        return true;
                    }
                });
            }else if(type.equals("IMAGE")) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.text, parent, false);
                }

                ImageView img = (ImageView)convertView.findViewById(R.id.imageview);
                //img.setImageURI(Uri.parse(card.getMediaUrl()));
                Glide.with(getContext()).load(card.getMediaUrl()).into(img);
                img.setImageResource(R.mipmap.grumpy);
            }else if(type.equals("TEXT_IMAGE")){
                if (convertView == null) {
                    convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.text_image, parent, false);
                }

                ImageView img = (ImageView)convertView.findViewById(R.id.text_image);
                img.setImageResource(R.mipmap.grumpy);

                MathView cardText = null  ;
                cardText= (MathView) convertView.findViewById(R.id.text_image_text);

                String tex = "This come from string. You can insert inline formula:" +
                        " \\(ax^2 + bx + c = 0\\) " +
                        "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$"+
                        "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$"
                        +"or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$"+
                        "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";
                //ImageView cardImage = (ImageView) convertView.findViewById(R.id.cardImage);

                // Populate the data into the template view using the data object
                cardText.setText(tex);
                WebSettings webSettings = cardText.getSettings();
                webSettings.setJavaScriptEnabled(true);
            }else {
                if (convertView == null) {
                    convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.video, parent, false);
                }

                VideoView video = (VideoView) convertView.findViewById(R.id.videoView);
               // video.

            }
//            cardText.setWebViewClient(new WebViewClient() {
//                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                    return false;
//                }
//            });
			//cardImage.setImageURI(Uri.parse(card.getMediaUrl()));
			//Glide.with(getContext()).load(card.getMediaUrl()).into(cardImage);

			return convertView;
		}

		/* other extra methods */
		public void addItem(final Card item){

		}

	}

	public Card getTopCardDetails(){
		//Card topCard = new Card();
//		if(cardAdapter == null) {
//			cardList = new ArrayList<>();
//			cardList.add(new Card("123","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//			cardList.add(new Card("456","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//			cardList.add(new Card("567","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//			cardList.add(new Card("678","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//			cardList.add(new Card("890","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//
//			cardAdapter = new CardAdapter(getContext(), cardList);
//		}
	return cardAdapter.getItem(0);
	}



	public void forceCrash(View view) {
		throw new RuntimeException("forceCrash: This is a crash");
	}

}
