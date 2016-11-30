package g.sw2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by 5dr on 06/11/16.
 */

public class WebAdapter extends ArrayAdapter<String> {

	WebView webView;

	public WebAdapter(Context context, ArrayList<String> urls) {
		super(context, R.layout.webview, urls);
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get the data item for this position
		String url = getItem(position);

		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.webview, parent, false);
		}

		// Lookup view for data population
		webView = (WebView) convertView.findViewById(R.id.webview);
		webView.loadUrl(url);

		// Return the completed view to render on screen
		return convertView;


	}
}
