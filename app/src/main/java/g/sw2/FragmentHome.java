package g.sw2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import g.sw2.swipelib.SwipeFlingAdapterView;


public class FragmentHome extends Fragment {

	SwipeFlingAdapterView swipeView;
	ArrayAdapter<String> arrayAdapter;
	ArrayList<String> itemList;
	int i;


	//ArrayList<String> arrayOfUrls;
	//WebAdapter webAdapter;



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

		itemList = new ArrayList<>();
		itemList.add("Hello");itemList.add("handsome");itemList.add("you");itemList.add("are");itemList.add("awesome");itemList.add("because");itemList.add("it's");itemList.add("working");

		arrayAdapter = new ArrayAdapter(getContext(), R.layout.item, R.id.text, itemList);    //new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items); - in case of no item.layout

		//this is not working - arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.webview);

		//arrayOfUrls = new ArrayList<>();
		//arrayOfUrls.add("www.google.com");arrayOfUrls.add("www.yahoo.com");arrayOfUrls.add("www.getzenius.com");


		//create adapter for webview
		//webAdapter = new WebAdapter(getContext(),arrayOfUrls);

		swipeView.setAdapter(arrayAdapter);

		swipeView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
			@Override
			public void removeFirstObjectInAdapter() {
				itemList.remove(0);
				arrayAdapter.notifyDataSetChanged();
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
				itemList.add("Android ".concat(String.valueOf(i)));
				arrayAdapter.notifyDataSetChanged();
				++i;
			}

			@Override
			public void onScroll(float v) {

			}
		});


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

	public void forceCrash(View view) {
		throw new RuntimeException("forceCrash: This is a crash");
	}

}
