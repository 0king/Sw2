package g.sw2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import g.sw2.R;
import g.sw2.model.Card;
import g.sw2.model.Chapter;
import g.sw2.model.Topic;
import g.sw2.swipelib.SwipeFlingAdapterView;
import g.sw2.utility.TimeCounter;
import io.github.kexanie.library.MathView;
import okhttp3.OkHttpClient;


public class SessionActivity extends AppCompatActivity {

	SwipeFlingAdapterView swipeView;
	ArrayAdapter<String> arrayAdapter;
	ArrayList<String> itemList;//todo create and use card object instead of String
	int i;
	TextView cardText;
	ArrayList<Card> cardList = new ArrayList<Card>();
	ArrayList<Chapter> chapterList;
	ArrayList<Topic> topicList;
	CardAdapter cardAdapter;
	
	long startTime, endTime;
	
	@BindView(R.id.iv_show_options)
	ImageView ivShowOptions;
	
	@BindView(R.id.ll_all_options)
	View llAllOptions;
	
	Animation animFadeOut, animFadeIn, animFadeSlide;
	Animation animInFromRight;
	
	OkHttpClient okHttpClient;
	
	File mathJsonFile;
	String mathString;//complete file converted to string
	


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home);
		ButterKnife.bind(this);
		
		readMathJsonFile();
		//fillData();
		//getSupportActionBar().hide();

		swipeView = (SwipeFlingAdapterView) findViewById(R.id.swipecards);
		swipeView.bringToFront();
		
		JSONArray mathChaptersList = new JSONArray();
		
		try {
			mathChaptersList = new JSONArray(mathString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			for (int i = 0; i < mathChaptersList.length(); i++) {
				JSONObject chapter = mathChaptersList.getJSONObject(i);
				String chapterId = chapter.getString("chapter_id");
				String chapterName = chapter.getString("chapter_name");
				String chapterDuration = chapter.getString("chapter_duration");
				String chapterImageUrl = chapter.getString("chapter_image_url");
				String chapterLearningObjectives = chapter.getString("chapter_learning_objectives");
				
				Card chapterCard = new Card(chapterId, chapterName, "CHAPTER_COVER", chapterDuration, chapterImageUrl, chapterLearningObjectives);
				//chapterCard.setCardId(chapterId);
				//chapterCard.setMainText(chapterName);
				//chapterCard.setDesignFormat("CHAPTER_COVER");
				//chapterCard.setDuration(chapterDuration);
				//chapterCard.setImageUrl(chapterImageUrl);
				//chapterCard.setChapterLearningObjectives(chapterLearningObjectives);
				cardList.add(chapterCard);
				
				JSONArray topicsList = chapter.getJSONArray("topics_list");
				for (int j = 0; j < topicsList.length(); j++) {
					JSONObject topic = topicsList.getJSONObject(j);
					Card topicCard = new Card(topic.getString("topic_id"), topic.getString("topic_name"), "TOPIC_COVER", topic.getString("topic_duration"), topic.getString("topic_image_url"), topic.getString("topic_summary"));
					//topicCard.setCardId(topic.getString("topic_id"));
					//topicCard.setMainText(topic.getString("topic_name"));
					//topicCard.setDuration(topic.getString("topic_duration"));
					//topicCard.setImageUrl(topic.getString("topic_image_url"));
					//topicCard.setChapterLearningObjectives(topic.getString("topic_summary"));
					cardList.add(topicCard);
					
					JSONObject allCardsList = topic.getJSONObject("all_cards_list");
					JSONArray studyCardsList = allCardsList.getJSONArray("study_cards_list");
					
					Card[] cards = new Gson().fromJson(studyCardsList.toString(), Card[].class);
					cardList.addAll(new ArrayList<Card>(Arrays.asList(cards)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Gson gson = new Gson();
		//Type listType = new TypeToken<ArrayList<Card>>(){}.getType();
		//cardList = gson.fromJson(mathChaptersList,listType);
		
		//Card[] cards = gson.fromJson(mathString,Card[].class);
		//cardList = new ArrayList<>(Arrays.asList(cards));
		//Log.d("durga",cardList.toString());
		

		
		/*cardList.add(new Card("123","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT"));
		cardList.add(new Card("456","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","IMAGE"));
        cardList.add(new Card("890","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers","TEXT"));
*/
		
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
				//cardList.remove(0);
				cardAdapter.notifyDataSetChanged();
			}

			@Override
			public void onRightCardExit(Object o) {
				//cardList.remove(0);
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
		//cardText.setMovementMethod(new ScrollingMovementMethod());//todo how to initialise textview in card_item_latex_text layout

		/* code for Crashlytics */

		//todo check how to do this type of linking
/*		Button crashBtn = (Button) view.findViewById(R.id.crash_button);
		crashBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				throw new RuntimeException("OnClickListener: This is a crash");
			}
		});*/
		
		
		animFadeOut = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);
		animFadeIn = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
		animFadeSlide = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in_slide_left);//not using it, remove it
		//animInFromRight = inFromRightAnimation();
		//animFadeOut.setAnimationListener();
		
		ivShowOptions.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ivShowOptions.startAnimation(animFadeOut);
				ivShowOptions.setVisibility(View.GONE);
				llAllOptions.setVisibility(View.VISIBLE);
				//llAllOptions.startAnimation(inFromRightAnimation());
				llAllOptions.startAnimation(animFadeSlide);
				//to avoid this problem: android view gone still clickable:-
				//clearAnimation()
				//setClickable(false)
				//setFillAfter(false)
			}
		});
		
		llAllOptions.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				llAllOptions.startAnimation(animFadeOut);
				ivShowOptions.startAnimation(animFadeIn);
				ivShowOptions.setVisibility(View.VISIBLE);
				llAllOptions.setVisibility(View.GONE);
			}
		});
		
	}
	
	void fillData() {
		BufferedReader reader = null;
		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "math10.json");
		try {
			reader = new BufferedReader(new FileReader(file));
			Gson gson = new GsonBuilder().create();
			Chapter[] chapters = gson.fromJson(reader, Chapter[].class);
			//chapterList = new ArrayList<>(Arrays.asList(chapters));
			//Log.d("durga",String.valueOf(chapterList.size()));
			//Log.d("durga",chapters[0].toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	void readMathJsonFile() {
		mathString = null;
		try {
			mathJsonFile = new File(Environment.getExternalStorageDirectory(), "math10.json");
			FileInputStream inputStream = new FileInputStream(mathJsonFile);
			try {
				FileChannel fileChannel = inputStream.getChannel();
				MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
				mathString = Charset.defaultCharset().decode(mbb).toString();
				//Log.d("durga",mathString);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}
	
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state) ||
				    Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
	private Animation inFromRightAnimation() {
		
		Animation inFromRight = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				                                              Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(700);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}
	
	public Card getTopCardDetails() {
		//AllCardsList topCard = new AllCardsList();
//		if(cardAdapter == null) {
//			cardList = new ArrayList<>();
//			cardList.add(new AllCardsList("123","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//			cardList.add(new AllCardsList("456","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//			cardList.add(new AllCardsList("567","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//			cardList.add(new AllCardsList("678","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//			cardList.add(new AllCardsList("890","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
//
//			cardAdapter = new CardAdapter(getContext(), cardList);
//		}
		return cardAdapter.getItem(0);
	}
	
	public void forceCrash(View view) {
		throw new RuntimeException("forceCrash: This is a crash");
	}
	
	@Override
	protected void onResume() {//dont do in onStart
		super.onResume();
		startTime = SystemClock.uptimeMillis();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		endTime = SystemClock.uptimeMillis();
		long duration = endTime - startTime;
		TimeCounter.INSTANCE.updateTimeCounter(duration);
	}
	
	private class CardAdapter extends ArrayAdapter<Card> {

		//todo use view holder
		
		private CardAdapter(Context context, ArrayList<Card> items) {
			super(context, 0, items);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			// Get the data card_item_latex_text for this position
			Card card = getItem(position);
			String type = "card_item_image";//default value;
			String isLatex = "no";//default value;
			if (card != null && card.getCardType() != null) {
				type = card.getCardType();
				type = type.toUpperCase();
			}
			/* when there is latex */
			if (card != null && card.getIsLatex().equalsIgnoreCase("yes")) {
				switch (type) {
					case "TEXT_IMAGE": {
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_latex_text_image, parent, false);
						}
						ImageView img = (ImageView) convertView.findViewById(R.id.text_image);
						img.setImageResource(R.mipmap.disha);
						
						MathView cardText = null;
						cardText = (MathView) convertView.findViewById(R.id.text_image_text);
						
						String tex = "This come from string. You can insert inline formula:" +
								             " \\(ax^2 + bx + c = 0\\) " +
								             "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$" +
								             "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$"
								             + "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$" +
								             "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";
						//ImageView cardImage = (ImageView) convertView.findViewById(R.id.cardImage);
						
						// Populate the data into the template view using the data object
						cardText.setText(tex);
						WebSettings webSettings = cardText.getSettings();
						webSettings.setJavaScriptEnabled(true);
						break;
					}
					case "TEXT": {
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_latex_text, parent, false);
						}
						MathView cardText = null;
						cardText = (MathView) convertView.findViewById(R.id.formula_one);
						
						String longText = "Let’s get you started by\n" +
								                  "providing some invaluable study tips specific to Mathematics.\n" +
								                  "\n" +
								                  "1. Master the Key Concepts. You don’t need to memorize the formulas, but it is very necessary that you understand how the formula was created.\n" +
								                  "2. Practice, Practice & More Practice. It is impossible to study maths properly by just reading and listening.\n" +
								                  "3. Review Errors\n" +
								                  "4. Practice Practice Practice - From Newton to Bopdeva, everyone became great only after enough practice. Only through hard work and dedication is greatness achieved. However, we dont like to to do thses things, but we do crave for topping the exam. So decide your priority what do you want - comfort or rank?\n" +
								                  "\n";
						
						// Populate the data into the template view using the data object
						cardText.setText(card.getMainText());
						WebSettings webSettings = cardText.getSettings();
						webSettings.setJavaScriptEnabled(true);
						webSettings.setAppCacheEnabled(true);
						webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
						cardText.setWebViewClient(new WebViewClient() {
							@Override
							public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
								Log.d("Zenius", "shouldOverrideUrlLoading: ");
								return true;
							}
						});
						break;
					}
					default:
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_latex_text, parent, false);
						}
						MathView cardText = null;
						cardText = (MathView) convertView.findViewById(R.id.formula_one);
						cardText.setText(card.getMainText());
						WebSettings webSettings = cardText.getSettings();
						webSettings.setJavaScriptEnabled(true);
						webSettings.setAppCacheEnabled(true);
						webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
						cardText.setWebViewClient(new WebViewClient() {
							@Override
							public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
								Log.d("Zenius", "shouldOverrideUrlLoading: ");
								return true;
							}
						});
				}
			}
			/* when there's no latex required */
			else {
				switch (type) {
					case "TEXT":
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_text, parent, false);
						}
						TextView textView = (TextView) convertView.findViewById(R.id.tvSimpleText);
						textView.setText(card.getMainText());
						break;
					case "IMAGE":
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_image, parent, false);
						}
						ImageView img = (ImageView) convertView.findViewById(R.id.image_view);
						//img.setImageURI(Uri.parse(card.getMediaUrl()));
						Glide.with(getContext()).load(card.getImageUrl()).into(img);
						//img.setImageResource(R.mipmap.disha);
						break;
					case "TOPIC_COVER":
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_topic_cover, parent, false);
						}
						TextView tvTopicCover = (TextView) convertView.findViewById(R.id.tvTopicCover);
						tvTopicCover.setText(card.getMainText());
						break;
					case "CHAPTER_COVER":
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_chapter_cover, parent, false);
						}
						TextView tvChapterCover = (TextView) convertView.findViewById(R.id.tvChapterCover);
						tvChapterCover.setText(card.getMainText());
						break;
					case "TEXT_IMAGE":
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_text_image, parent, false);
						}
						TextView tvTxIm = (TextView) convertView.findViewById(R.id.tvTxIm);
						ImageView ivTxIm = (ImageView) convertView.findViewById(R.id.ivTxIm);
						tvTxIm.setText(card.getMainText());
						Glide.with(getContext()).load(card.getImageUrl()).into(ivTxIm);
						break;
					case "TEXT_IMAGE_TEXT":
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_text_image_text, parent, false);
						}
						TextView tvTxImTx1 = (TextView) convertView.findViewById(R.id.tvTxImTi1);
						TextView tvTxImTx2 = (TextView) convertView.findViewById(R.id.tvTxImTi2);
						ImageView ivTxImTx = (ImageView) convertView.findViewById(R.id.ivTxImTx);
						tvTxImTx1.setText(card.getCard_text_above());
						tvTxImTx2.setText(card.getCard_text_below());
						Glide.with(getContext()).load(card.getImageUrl()).into(ivTxImTx);
						break;
					default: //text
						if (convertView == null) {
							convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.card_item_text, parent, false);
						}
						TextView textView2 = (TextView) convertView.findViewById(R.id.tvSimpleText);
						textView2.setText(card.getMainText());
				}
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
	
	
}
