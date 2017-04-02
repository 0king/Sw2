package g.sw2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g.sw2.R;

/**
 * Created by Kush Agrawal on 4/1/2017.
 */

public class FragmentAllContents extends Fragment {

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

        return view;
    }
}
