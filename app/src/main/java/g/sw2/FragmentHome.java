package g.sw2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import g.sw2.swipelib.SwipeFlingAdapterView;


public class FragmentHome extends Fragment {

	SwipeFlingAdapterView swipeView;
	ArrayAdapter<String> arrayAdapter;
	ArrayList<String> itemList;//todo create and use card object instead of String
	int i;

	TextView cardText;
	//ArrayList<String> arrayOfUrls;
	//WebAdapter webAdapter;

	ArrayList<Card> cardList;
	CardAdapter cardAdapter;


	public FragmentHome() {
		// Required empty public constructor
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);

		swipeView = (SwipeFlingAdapterView) view.findViewById(R.id.swipecards);

		cardList = new ArrayList<>();
		cardList.add(new Card("123","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
		cardList.add(new Card("456","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
		cardList.add(new Card("567","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
		cardList.add(new Card("678","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
		cardList.add(new Card("890","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));

		//todo create a default image space, show image loading there

		cardAdapter = new CardAdapter(getContext(), cardList);


		/*itemList = new ArrayList<>();
		itemList.add("In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers. We continue our discussion on real numbers in this chapter. We begin with two very important properties of positive integers in Sections 1.2 and 1.3, namely the Euclid’s division algorithm and the Fundamental Theorem of Arithmetic.\n" +
				             "Euclid’s division algorithm, as the name suggests, has to do with divisibility of integers. Stated simply, it says any positive integer a can be divided by another positive integer b in such a way that it leaves a remainder r that is smaller than b. Many of you probably recognise this as the usual long division process. Although this result is quite easy to state and understand, it has many applications related to the divisibility properties of integers. We touch upon a few of them, and use it mainly to compute the HCF of two positive integers. (150 words)\n" +
				             "The Fundamental Theorem of Arithmetic, on the other hand, has to do something with multiplication of positive integers. You already know that every composite number can be expressed as a product of primes in a unique way—this important fact is the Fundamental Theorem of Arithmetic. Again, while it is a result that is easy to state and understand, it has some very deep and significant applications in the field of mathematics. We use the Fundamental Theorem of Arithmetic for two main applications. First, we use it to prove the irrationality of many of the numbers you studied in Class IX, such as 2 , 3 and 5 . Second, we apply this theorem to explore when exactly the decimal p expansion of a rational number, say q ( q ≠ 0) , is terminating and when it is non- terminating repeating. We do so by looking at the prime factorisation of the denominator p q of . You will see that the prime factorisation of q will completely reveal the nature q p of the decimal expansion of . q");
		itemList.add("In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers. We continue our discussion on real numbers in this chapter. We begin with two very important properties of positive integers in Sections 1.2 and 1.3, namely the Euclid’s division algorithm and the Fundamental Theorem of Arithmetic.\n" +
				             "Euclid’s division algorithm, as the name suggests, has to do with divisibility of integers. Stated simply, it says any positive integer a can be divided by another positive integer b in such a way that it leaves a remainder r that is smaller than b. Many of you probably recognise this as the usual long division process. Although this result is quite easy to state and understand, it has many applications related to the divisibility properties of integers. We touch upon a few of them, and use it mainly to compute the HCF of two positive integers. (150 words)\n" +
				             "The Fundamental Theorem of Arithmetic, on the other hand, has to do something with multiplication of positive integers. You already know that every composite number can be expressed as a product of primes in a unique way—this important fact is the Fundamental Theorem of Arithmetic. Again, while it is a result that is easy to state and understand, it has some very deep and significant applications in the field of mathematics. We use the Fundamental Theorem of Arithmetic for two main applications. First, we use it to prove the irrationality of many of the numbers you studied in Class IX, such as 2 , 3 and 5 . Second, we apply this theorem to explore when exactly the decimal p expansion of a rational number, say q ( q ≠ 0) , is terminating and when it is non- terminating repeating. We do so by looking at the prime factorisation of the denominator p q of . You will see that the prime factorisation of q will completely reveal the nature q p of the decimal expansion of . q");
		itemList.add("In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers. We continue our discussion on real numbers in this chapter. We begin with two very important properties of positive integers in Sections 1.2 and 1.3, namely the Euclid’s division algorithm and the Fundamental Theorem of Arithmetic");
		itemList.add("are");itemList.add("awesome");itemList.add("because");itemList.add("it's");itemList.add("working");

		arrayAdapter = new ArrayAdapter(getContext(), R.layout.item, R.id.cardText, itemList);*/    //new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items); - in case of no item.layout







		//this is not working - arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.webview);

		//arrayOfUrls = new ArrayList<>();
		//arrayOfUrls.add("www.google.com");arrayOfUrls.add("www.yahoo.com");arrayOfUrls.add("www.getzenius.com");


		//create adapter for webview
		//webAdapter = new WebAdapter(getContext(),arrayOfUrls);

		swipeView.setAdapter(cardAdapter);

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
				//Toast.makeText(getContext(),"left",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onRightCardExit(Object o) {

			}

			@Override
			public void onAdapterAboutToEmpty(int itemsInAdapter) {
				//itemList.add("Android ".concat(String.valueOf(i)));
				cardList.add(new Card("Math".concat(String.valueOf(i)),"http://www.telugubang.in/wp-content/uploads/2017/02/Hot-Disha-patani-at-Jio-Filmfare-Award-2017t.jpg","Android ".concat(String.valueOf(i))));
				//arrayAdapter.notifyDataSetChanged();
				cardAdapter.notifyDataSetChanged();
				++i;
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

		return view;

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
			// Check if an existing view is being reused, otherwise inflate the view
			if (convertView == null) {
				convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.item, parent, false);
			}
			// Lookup view for data population
			TextView cardText = (TextView) convertView.findViewById(R.id.cardText);
			ImageView cardImage = (ImageView) convertView.findViewById(R.id.cardImage);

			// Populate the data into the template view using the data object
			cardText.setText(card.getText());
			//cardImage.setImageURI(Uri.parse(card.getMediaUrl()));
			Glide.with(getContext()).load(card.getMediaUrl()).into(cardImage);

			return convertView;
		}

		/* other extra methods */
		public void addItem(final Card item){

		}

	}

	public Card getTopCardDetails(){
		//Card topCard = new Card();
		if(cardAdapter == null) {
			cardList = new ArrayList<>();
			cardList.add(new Card("123","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
			cardList.add(new Card("456","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
			cardList.add(new Card("567","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
			cardList.add(new Card("678","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));
			cardList.add(new Card("890","http://st1.bollywoodlife.com/wp-content/uploads/photos/disha-patani-looks-extremely-hot-during-her-photo-shoot-201601-649027.jpg","In Class IX, you began your exploration of the world of real numbers and encountered irrational numbers"));

			cardAdapter = new CardAdapter(getContext(), cardList);
		}
		return cardAdapter.getItem(0);
	}



	public void forceCrash(View view) {
		throw new RuntimeException("forceCrash: This is a crash");
	}

}
