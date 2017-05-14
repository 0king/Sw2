package g.sw2.memory_game;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g.sw2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGameScreen extends Fragment {
	
	private static final String TAG = "FragmentGameScreen";
	
	ActivityGameScreenSlide parentActivity;
	String colorList[] = {"#ff0015", "#f200ff", "#00aeff", "#00ff15", "#f6ff00"};//red, purple,blue,green,yellow
	int indexOfArrayColor;
	
	
	public FragmentGameScreen() {
		// Required empty public constructor
	}
	
	public static Fragment newInstance(int indexOfArrayColor) {
		FragmentGameScreen myFragment = new FragmentGameScreen();
		myFragment.indexOfArrayColor = indexOfArrayColor;
		/*Bundle args = new Bundle();
		args.putInt("indexOfArrayColor", indexOfArrayColor);
		myFragment.setArguments(args);*/
		return myFragment;
	}
	
	/*void FragmentGameScreen(int indexOfArrayColor){
		this.indexOfArrayColor = indexOfArrayColor;
	}*/
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_activity_game_screen_text, container, false);
		
		parentActivity = (ActivityGameScreenSlide) getActivity();
		
		rootView.setBackgroundColor(Color.parseColor(colorList[indexOfArrayColor]));
		return rootView;
	}
	
	
}
