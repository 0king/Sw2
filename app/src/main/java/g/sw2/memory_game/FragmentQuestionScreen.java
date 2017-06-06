package g.sw2.memory_game;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import g.sw2.R;
import g.sw2.utility.ContentManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentQuestionScreen extends Fragment implements View.OnClickListener {
	
	private static final String TAG = "FragmentQuestionScreen";
	TextView tvQuestionText;
	private ActivityGameQuestionScreenSlide parentActivity;
	private String colorList[] = {"#ff0015", "#f200ff", "#00aeff", "#00ff15", "#f6ff00"};//red, purple,blue,green,yellow
	private int indexOfArrayColor;
	private QuestionMCQ questionMCQ;//todo initialization
	private Button[] buttonQuestionOptions;
	
	
	public FragmentQuestionScreen() {
		// Required empty public constructor
	}
	
	public static Fragment newInstance(int indexOfArrayColor) {
		FragmentQuestionScreen myFragment = new FragmentQuestionScreen();
		myFragment.indexOfArrayColor = indexOfArrayColor;
		return myFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_question_screen, container, false);
		parentActivity = (ActivityGameQuestionScreenSlide) getActivity();
		rootView.setBackgroundColor(Color.parseColor(colorList[indexOfArrayColor]));
		
		
		tvQuestionText = (TextView) rootView.findViewById(R.id.tv_question);
		
		buttonQuestionOptions = new Button[5];
		buttonQuestionOptions[0] = (Button) rootView.findViewById(R.id.option1);
		buttonQuestionOptions[1] = (Button) rootView.findViewById(R.id.option2);
		buttonQuestionOptions[2] = (Button) rootView.findViewById(R.id.option3);
		buttonQuestionOptions[3] = (Button) rootView.findViewById(R.id.option4);
		buttonQuestionOptions[4] = (Button) rootView.findViewById(R.id.option5);
		for (int i = 0; i < buttonQuestionOptions.length; i++) {
			buttonQuestionOptions[i].setOnClickListener(this);
		}
		
		initializeInterface();
		return rootView;
	}
	
	void initializeInterface() {
		List<String> options = questionMCQ.getMultipleOptions();
		Collections.shuffle(options);
		tvQuestionText.setText(questionMCQ.getQuestionText());
		for (int i = 0; i < buttonQuestionOptions.length; i++) {
			if (i < options.size()) {
				buttonQuestionOptions[(4 - i)].setText((CharSequence) options.get(i));
			} else {
				buttonQuestionOptions[(4 - i)].setVisibility(View.INVISIBLE);
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.option1:
				checkAnswer(buttonQuestionOptions[0]);
				break;
			case R.id.option2:
				checkAnswer(buttonQuestionOptions[1]);
				break;
			case R.id.option3:
				checkAnswer(buttonQuestionOptions[2]);
				break;
			case R.id.option4:
				checkAnswer(buttonQuestionOptions[3]);
				break;
			case R.id.option5:
				checkAnswer(buttonQuestionOptions[4]);
				break;
		}
	}
	
	void checkAnswer(Button buttonOption) {
		String userSelection = buttonOption.getText().toString();
		if (questionMCQ.getCorrectAnswer().equals(userSelection)) {
			ContentManager.getInstance().playCorrectAnswerSound(getContext());
			buttonOption.setBackgroundResource(R.drawable.button_rectangle_green);
			//todo update user data, etc
		} else {
			ContentManager.getInstance().playIncorrectAnswerSound(getContext());
			buttonOption.setBackgroundResource(R.drawable.button_rectangle_red);
		}
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				parentActivity.moveToNextQuestionPage();
			}
		}, 500);
	}
	
}
