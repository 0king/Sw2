package g.sw2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import g.sw2.R;

/**
 * Created by Kush Agrawal on 4/1/2017.
 */

public class FragmentAllContents extends Fragment {
	
	String[] mParties = {"Speed", "Focus", "Memory", "Creativity", "Problem Solving", "Logic", "Comprehension", "Analysis", "Critical Thinking", "Gyan", "Philo", "psy", "etc"};
	private PieChart mChart;

    public FragmentAllContents() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_contents_layout, container, false);
	    mChart = (PieChart) view.findViewById(R.id.dummy_pie_chart);
	
	    this.mChart.setUsePercentValues(true);
	    this.mChart.getDescription().setEnabled(false);
	    this.mChart.setExtraOffsets(5.0f, 10.0f, 5.0f, 5.0f);
	    this.mChart.animateXY(1400, 1400);
	    this.mChart.setDragDecelerationFrictionCoef(0.95f);
	    this.mChart.setCenterText("Your Brain");
	    this.mChart.setDrawHoleEnabled(true);
	    this.mChart.setTransparentCircleAlpha(R.styleable.AppCompatTheme_ratingBarStyleSmall);
	    this.mChart.setHoleRadius(58.0f);
	    this.mChart.setTransparentCircleRadius(61.0f);
	    this.mChart.setDrawCenterText(true);
	    this.mChart.setRotationAngle(0.0f);
	    this.mChart.setRotationEnabled(true);
	    this.mChart.setHighlightPerTapEnabled(true);
	    this.mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
		    @Override
		    public void onValueSelected(Entry e, Highlight h) {
		    }
		
		    @Override
		    public void onNothingSelected() {
		    }
	    });
	    this.mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
	    Legend l = this.mChart.getLegend();
	    l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
	    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
	    l.setOrientation(Legend.LegendOrientation.VERTICAL);
	    l.setDrawInside(false);
	    l.setXEntrySpace(0.0f);
	    l.setYEntrySpace(0.0f);
	    l.setYOffset(0.0f);
	
	    setData(12, 100.0f);
	    
        return view;
    }
	
	
	private void setData(int count, float range) {
		float mult = range;
		ArrayList<PieEntry> entries = new ArrayList();
		for (int i = 0; i < count; i++) {
			entries.add(new PieEntry((float) ((Math.random() * ((double) mult)) + ((double) (mult / 5.0f))), this.mParties[i]));//this.mParties[i % this.mParties.length]
		}
		PieDataSet dataSet = new PieDataSet(entries, "Success factors ");
		dataSet.setSliceSpace(3.0f);
		dataSet.setSelectionShift(5.0f);
		ArrayList<Integer> colors = new ArrayList();
		for (int c : ColorTemplate.VORDIPLOM_COLORS) {
			colors.add(Integer.valueOf(c));
		}
		for (int c2 : ColorTemplate.JOYFUL_COLORS) {
			colors.add(Integer.valueOf(c2));
		}
		for (int c22 : ColorTemplate.COLORFUL_COLORS) {
			colors.add(Integer.valueOf(c22));
		}
		for (int c222 : ColorTemplate.LIBERTY_COLORS) {
			colors.add(Integer.valueOf(c222));
		}
		for (int c2222 : ColorTemplate.PASTEL_COLORS) {
			colors.add(Integer.valueOf(c2222));
		}
		colors.add(Integer.valueOf(ColorTemplate.getHoloBlue()));
		dataSet.setColors((List) colors);
		PieData data = new PieData(dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(11.0f);
		data.setValueTextColor(-1);
		this.mChart.setData(data);
		this.mChart.highlightValues(null);
		this.mChart.invalidate();
	}
	
}
