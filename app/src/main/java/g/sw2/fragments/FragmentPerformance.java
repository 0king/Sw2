package g.sw2.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import g.sw2.R;
import g.sw2.game_memory.ActivityGameScreenSlide;

/**
 * Created by Kush Agrawal on 4/1/2017.
 */

public class FragmentPerformance extends Fragment {
	
	
	MaterialCalendarView calendarView;
	BarChart timeBarChart;
	LineChart scoreLineChart;
	
	@BindView(R.id.button)
	Button button;
	
	public FragmentPerformance() {
		// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.performance_layout, container, false);
	    //ButterKnife.bind(getActivity());
	    ButterKnife.bind(this, view);
	
	    timeBarChart = (BarChart) view.findViewById(R.id.time_bar_chart);
	    timeBarChart.setTouchEnabled(false);
	    timeBarChart.setDragEnabled(false);
	    timeBarChart.setScaleEnabled(false);
	    timeBarChart.setPinchZoom(false);
	    timeBarChart.setDoubleTapToZoomEnabled(false);
	    timeBarChart.setDragDecelerationEnabled(false);
	
	    timeBarChart.animateXY(1000, 1000);
	
	    timeBarChart.getDescription().setEnabled(false);
	    timeBarChart.setDrawGridBackground(false);
	    timeBarChart.setDrawValueAboveBar(true);
	
	    Legend legend = timeBarChart.getLegend();
	    legend.setEnabled(false);
	
	    timeBarChart.setFitBars(true);// make the x-axis fit exactly all bars
	
	    // the labels that should be drawn on the XAxis
	    final String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
	    IAxisValueFormatter formatter = new IAxisValueFormatter() {
		    @Override
		    public String getFormattedValue(float value, AxisBase axis) {
			    //return 23 Feb
			    return days[(int) value];
		    }
		    // we don't draw numbers, so no decimal digits needed
/*            @Override
            public int getDecimalDigits() {  return 0; }*/
	    };
	    XAxis xAxis = timeBarChart.getXAxis();
	    xAxis.setValueFormatter(formatter);
	    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
	    xAxis.setDrawGridLines(false);
	
	    //xAxis.setGranularity(1.0f);
	    //xAxis.setLabelCount(7);
	
	    YAxis yAxisRight = timeBarChart.getAxisRight();
	    yAxisRight.setEnabled(false);
	    YAxis yAxisLeft = timeBarChart.getAxisLeft();
	    yAxisLeft.setDrawGridLines(false);
	    yAxisLeft.setAxisMinimum(0f);//clear the offset
	
	
	    List<BarEntry> entries = new ArrayList<BarEntry>();
	    entries.add(new BarEntry(1, 450));
	    entries.add(new BarEntry(2, 345));
	    entries.add(new BarEntry(3, 89));
	    entries.add(new BarEntry(4, 467));
	    entries.add(new BarEntry(5, 329));
	    entries.add(new BarEntry(6, 500));
	    entries.add(new BarEntry(0, 50));
	    //entries.add(new BarEntry(1,00));
	    //entries.add(new BarEntry(2,170));
	    //entries.add(new BarEntry(5,210));
	    BarDataSet barDataSet = new BarDataSet(entries, "");
	    BarData barData = new BarData(barDataSet);
	
	    //barData.setBarWidth(0.9f);// set custom bar width
	    barDataSet.setBarBorderWidth(1f);
	    //barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
	
	    timeBarChart.setData(barData);
	    timeBarChart.invalidate(); //refresh
    
    /*
    *
    * Score Graph
    *
    * */
	
	    scoreLineChart = (LineChart) view.findViewById(R.id.score_line_chart);
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
	    scoreLineChartXAxis.setValueFormatter(formatter);
	    scoreLineChartXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
	    scoreLineChartXAxis.setDrawGridLines(false);
	
	    YAxis scoreLineChartAxisRight = scoreLineChart.getAxisRight();
	    scoreLineChartAxisRight.setEnabled(false);
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
    
    
    
    
    
    /*
    *
    * Calendar View
    *
    * */
	
	    calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView2);
	    calendarView.state().edit()
			    .setFirstDayOfWeek(Calendar.MONDAY)
			    .setMinimumDate(CalendarDay.from(2017, 0, 1))//jan=0, yy,mm,dd
			    .setMaximumDate(CalendarDay.from(2017, 3, 3))//apr=4-1
			    .setCalendarDisplayMode(CalendarMode.MONTHS)
			    .commit();
	    calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
	
	
	    RedEventDecorator r1 = new RedEventDecorator(getContext());
	    RedEventDecorator r2 = new RedEventDecorator(getContext());
	    RedEventDecorator r3 = new RedEventDecorator(getContext());
	    RedEventDecorator r4 = new RedEventDecorator(getContext());
	    RedEventDecorator r5 = new RedEventDecorator(getContext());
	    RedEventDecorator r6 = new RedEventDecorator(getContext());
	
	    calendarView.addDecorators(
			    r1, r2, r3, r4, r5, r6
	    );
	
	
	    try {
		    r1.setDate(new Date());
		    r2.setDate(new SimpleDateFormat("dd-mm-yyyy").parse("22-0-2017"));//todo fix JAN bug
		    r3.setDate(new SimpleDateFormat("dd-mm-yyyy").parse("23-1-2017"));
		    r4.setDate(new SimpleDateFormat("dd-mm-yyyy").parse("24-2-2017"));
		    r5.setDate(new SimpleDateFormat("dd-mm-yyyy").parse("25-2-2017"));
		    r6.setDate(new SimpleDateFormat("dd-mm-yyyy").parse("26-2-2017"));
		
	    } catch (ParseException e) {
	    }
	
	    calendarView.invalidateDecorators();//refresh
        
        return view;
    }
	
	
	public class RedEventDecorator implements DayViewDecorator {
		private final Drawable red_circle;
		private CalendarDay date;
		
		public RedEventDecorator(Context context) {
			red_circle = ContextCompat.getDrawable(context, R.drawable.red_circle);
			date = CalendarDay.today();
		}
		
		@Override
		public boolean shouldDecorate(CalendarDay day) {
			return date != null && day.equals(date);
		}
		
		@Override
		public void decorate(DayViewFacade view) {
			view.setBackgroundDrawable(red_circle);
		}
		
		public void setDate(Date date) {
			this.date = CalendarDay.from(date);
		}
	}
	
	
	public class GreenEventDecorator implements DayViewDecorator {
		private final Drawable green_circle;
		
		//Date todayDate;
		public GreenEventDecorator(Activity context) {
			green_circle = ContextCompat.getDrawable(context, R.drawable.green_circle);
			//todayDate = date; //date = CalendarDay.from(date), CalendarDay.today()
		}
		
		@Override
		public boolean shouldDecorate(CalendarDay day) {
			return true;
		}
		
		@Override
		public void decorate(DayViewFacade view) {
			view.setBackgroundDrawable(green_circle);
		}
	}
	
	
	@OnClick(R.id.button)
	void buttonClick(View v) {
		startActivity(new Intent(getContext(), ActivityGameScreenSlide.class));
	}
	
	
}
