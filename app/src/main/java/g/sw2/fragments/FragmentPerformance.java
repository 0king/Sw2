package g.sw2.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import g.sw2.R;
import g.sw2.activities.ActivityShowTimeSpent;
import g.sw2.database.DatabaseManager;
import g.sw2.game_memory.ActivityGameScreenSlide;
import g.sw2.utility.QueryDb;

/**
 * Created by Kush Agrawal on 4/1/2017.
 */

public class FragmentPerformance extends Fragment {
	
	
	MaterialCalendarView calendarView;
	LineChart scoreLineChart;
	View rootView;
	
	@BindView(R.id.button)
	Button button;
	@BindView(R.id.button2)
	Button button2;
	
	BarChart timeBarChart;
	private List<BarEntry> entries;
	private BarDataSet barDataSet;
	private BarData barData;
	
	public FragmentPerformance() {
		// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    rootView = inflater.inflate(R.layout.performance_layout, container, false);
	    //ButterKnife.bind(getActivity());
	    ButterKnife.bind(this, rootView);
	    setUpDailyStudyTimeBarChart();
	    setUpDailyUserZScoreLineChart();
	    setUpDailyStudyHistoryCalendar();
	    return rootView;
    }
	
	
	@OnClick(R.id.button)
	void buttonClick(View v) {
		startActivity(new Intent(getContext(), ActivityGameScreenSlide.class));
	}
	
	@OnClick(R.id.button2)
	void button2Click(View v) {
		startActivity(new Intent(getContext(), ActivityShowTimeSpent.class));
	}
	
	void setUpDailyStudyTimeBarChart() {
		timeBarChart = (BarChart) rootView.findViewById(R.id.time_bar_chart);
		timeBarChart.setTouchEnabled(false);
		timeBarChart.setDragEnabled(false);
		timeBarChart.setScaleEnabled(false);
		timeBarChart.setPinchZoom(false);
		timeBarChart.setDoubleTapToZoomEnabled(false);
		timeBarChart.setDragDecelerationEnabled(false);
		timeBarChart.animateXY(3000, 3000);
		timeBarChart.getDescription().setEnabled(false);
		timeBarChart.setDrawGridBackground(false);
		timeBarChart.setDrawValueAboveBar(true);
		Legend legend = timeBarChart.getLegend();
		legend.setEnabled(false);
		timeBarChart.setFitBars(true);// make the x-axis fit exactly all bars
		
		// the labels that should be drawn on the XAxis
		//final String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		IAxisValueFormatter formatter = new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				DecimalFormat df = new DecimalFormat("#");
				df.setMaximumFractionDigits(0);
				String xValue = df.format(value);
				Log.d("durga value 2", xValue);
				xValue = xValue.substring(6);//+" "+xValue.substring(4,6);
				//return 23 Feb
				//Log.d("durga value 3",xValue);
				return xValue;//				return days[(int) value];
			}
			// we don't draw numbers, so no decimal digits needed
/*            @Override
            public int getDecimalDigits() {  return 0; }*/
		};
		
		IAxisValueFormatter axisValueFormatter2 = new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				return "" + (long) value;
			}
		};
		
		XAxis xAxis = timeBarChart.getXAxis();
		xAxis.setValueFormatter(axisValueFormatter2);
		
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		int colorLightGrey = Color.argb(255, 216, 216, 216);//#d8d8d8 light grey
		int colorPaleSky = Color.parseColor("#a3d8e2");
		xAxis.setTextColor(colorPaleSky);
		
		//xAxis.setGranularity(1.0f);
		//xAxis.setLabelCount(7);
		
		YAxis yAxisRight = timeBarChart.getAxisRight();
		yAxisRight.setEnabled(false);
		timeBarChart.getAxisLeft().setEnabled(false);
		YAxis yAxisLeft = timeBarChart.getAxisLeft();
		yAxisLeft.setDrawGridLines(false);
		yAxisLeft.setAxisMinimum(0f);//clear the offset
		
		new FillDataInBarChart().execute();
		
	}
	
	//todo use CursorLoader or IntentService for long running db ops instead of Asynctask
	
	void setUpDailyUserZScoreLineChart() {
		
		scoreLineChart = (LineChart) rootView.findViewById(R.id.score_line_chart);
		scoreLineChart.setTouchEnabled(false);
		scoreLineChart.setDragEnabled(false);
		scoreLineChart.setScaleEnabled(false);
		scoreLineChart.setPinchZoom(false);
		scoreLineChart.setDoubleTapToZoomEnabled(false);
		scoreLineChart.setDragDecelerationEnabled(false);
		scoreLineChart.animateXY(2000, 2000);
		scoreLineChart.getDescription().setEnabled(false);
		scoreLineChart.setDrawGridBackground(false);
		Legend score_legend = scoreLineChart.getLegend();
		score_legend.setEnabled(false);
		
		XAxis scoreLineChartXAxis = scoreLineChart.getXAxis();
		
		final String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		IAxisValueFormatter formatter = new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				//return 23 Feb
				return days[(int) value];
			}
		};
		
		
		scoreLineChartXAxis.setValueFormatter(formatter);
		scoreLineChartXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		scoreLineChartXAxis.setDrawGridLines(false);
		int colorLightGrey = Color.argb(255, 216, 216, 216);//#d8d8d8 light grey
		int colorPaleSky = Color.parseColor("#a3d8e2");
		scoreLineChartXAxis.setTextColor(colorPaleSky);
		
		YAxis scoreLineChartAxisRight = scoreLineChart.getAxisRight();
		scoreLineChartAxisRight.setEnabled(false);
		scoreLineChart.getAxisLeft().setEnabled(false);
		YAxis scoreLineChartAxisLeft = scoreLineChart.getAxisLeft();
		scoreLineChartAxisLeft.setDrawGridLines(false);
		scoreLineChartAxisLeft.setAxisMinimum(0f);//clear the offset
		
		List<Entry> scoreEntries = new ArrayList<Entry>();
		scoreEntries.add(new Entry(0, 30));
		scoreEntries.add(new Entry(1, 40));
		scoreEntries.add(new Entry(2, 50));
		scoreEntries.add(new Entry(3, 100));
		scoreEntries.add(new Entry(4, 200));
		scoreEntries.add(new Entry(5, 400));
		scoreEntries.add(new Entry(6, 500));
		LineDataSet lineDataSet = new LineDataSet(scoreEntries, "scores"); // add entries to dataset
		lineDataSet.setColor(ColorTemplate.getHoloBlue());
		lineDataSet.setValueTextColor(ColorTemplate.getHoloBlue());
		LineData lineData = new LineData(lineDataSet);
		scoreLineChart.setData(lineData);
		scoreLineChart.invalidate(); // refresh
	}
	
	void setUpDailyStudyHistoryCalendar() {
		calendarView = (MaterialCalendarView) rootView.findViewById(R.id.calendarView2);
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		calendarView.state().edit()
				.setFirstDayOfWeek(Calendar.MONDAY)
				.setMinimumDate(CalendarDay.from(2017, 0, 1))//CalendarDay.from(2017, 0, 1): jan=0, yy,mm,dd //todo getFirstInstalldate
				.setMaximumDate(instance.getTime())//CalendarDay.from(2017, 3, 3): apr=4-1
				.setCalendarDisplayMode(CalendarMode.MONTHS)
				.commit();
		calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
		//calendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
		
		new SetUpCalendarMarks().executeOnExecutor(Executors.newSingleThreadExecutor());
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		DatabaseManager.getInstance().closeDatabase();
	}
	
	private class FillDataInBarChart extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			entries = new ArrayList<BarEntry>();
			HashMap<String, Long> values = QueryDb.INSTANCE.getAllTimeDurationPerDay();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			Calendar calendar = Calendar.getInstance();
			Date today = new Date();
			calendar.setTime(today);
			HashMap<Date, Long> dataSet = new HashMap<>();
			String date = dateFormat.format(calendar.getTime());
			//Log.d("durga values", String.valueOf(Arrays.asList(values)));//correct values from db query
			for (int i = 0; i < 14; i++) {
				if (values.containsKey(date)) {
					try {
						dataSet.put(dateFormat.parse(date), convertToMinutes(values.get(date)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					try {
						dataSet.put(dateFormat.parse(date), 0L);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				//Log.d("durga dataSet", String.valueOf(Arrays.asList(dataSet)));//correct values
				BarEntry newBarEntry = null;
				String xValue = date.substring(0, 4) + date.substring(5, 7) + date.substring(8);//endIndex is exclusive
				//Log.d("durga xValue",xValue);//correct values
				//DecimalFormat df = new DecimalFormat("#");
				//df.setMaximumFractionDigits(0);
				//Float f = null;
				//try {
				//	f =(float) df.parse(xValue);
				//} catch (ParseException e) {e.printStackTrace();}
				//Log.d("durga float",f.toString());
				try {
					newBarEntry = new BarEntry(0f + getNumberOfDays(dateFormat.parse(date)), (float) dataSet.get(dateFormat.parse(date)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				entries.add(newBarEntry);
				calendar.add(Calendar.DATE, -1);//decreasing date by 1
				calendar.setTime(calendar.getTime());
				date = dateFormat.format(calendar.getTime());
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void avoid) {
			barDataSet = new BarDataSet(entries, "");
			barDataSet.setValueTextColor(ColorTemplate.getHoloBlue());
			barData = new BarData(barDataSet);
			//barData.setBarWidth(0.9f);// set custom bar width
			//barDataSet.setBarBorderWidth(1f);
			//barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
			timeBarChart.setData(barData);
			timeBarChart.invalidate(); //refresh
		}
		
		int getNumberOfDays(Date endDate) throws ParseException {
			Date startDate;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			startDate = dateFormat.parse("2017-01-01");
			long duration = endDate.getTime() - startDate.getTime();
			long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
			return (int) ((diffInHours / 24) + 1);
		}
		
		private long convertToMinutes(long timeInMili) {
			return (timeInMili / (1000 * 60));
		}
	}
	
	private class SetUpCalendarMarks extends AsyncTask<Void, Void, List<CalendarDay>> {
		@Override
		protected void onPreExecute() {
			//super.onPreExecute();
		}
		
		@Override
		protected List<CalendarDay> doInBackground(Void... params) {
			ArrayList<CalendarDay> dates = new ArrayList<>();
			List<String> stringDates = QueryDb.INSTANCE.getAllHistory();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());////capital MM is necessary
			for (String s : stringDates) {
				try {
					Date date = format.parse(s);
					dates.add(CalendarDay.from(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return dates;
		}
		
		@Override
		protected void onPostExecute(List<CalendarDay> calendarDays) {
			super.onPostExecute(calendarDays);
			/* if (isFinishing()) {
		        return;
            }*/
			calendarView.addDecorator(new RedEventDecorator(getContext(), calendarDays));
		}
	}
	
	public class RedEventDecorator implements DayViewDecorator {
		
		private final Drawable mark;
		private HashSet<CalendarDay> dates;
		
		public RedEventDecorator(Context context, Collection<CalendarDay> dates) {
			mark = ContextCompat.getDrawable(context, R.drawable.bg_sky_circle);
			this.dates = new HashSet<>(dates);
		}
		
		@Override
		public boolean shouldDecorate(CalendarDay day) {
			return dates.contains(day);
		}
		
		@Override
		public void decorate(DayViewFacade view) {
			view.setBackgroundDrawable(mark);
		}
		
	}
}
