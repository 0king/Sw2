package g.sw2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import g.sw2.R;
import g.sw2.utility.QueryDb;

public class ActivityShowTimeSpent extends AppCompatActivity {
	
	@BindView(R.id.tvShowTodaysTime)
	TextView tvTodaysTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_time_spent);
		ButterKnife.bind(this);
		
		/* todo - use an AsyncTask handler HERE, which queries databse and then, updates the textview */
		
		long time = QueryDb.INSTANCE.getTodaysTotalTime();
		//Log.d("SHowTimeActivity"," "+time);
		//tvTodaysTime.setTextSize(13);//since it truncates any number having digits >8
		tvTodaysTime.setText(String.valueOf(time));
		//Log.d("SHowTimeActivity"," "+String.valueOf(time));
	}
}
