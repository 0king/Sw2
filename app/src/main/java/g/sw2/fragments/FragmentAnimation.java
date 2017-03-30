package g.sw2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import g.sw2.R;

/**
 * Created by Kush Agrawal on 2/28/2017.
 */

public class FragmentAnimation extends Fragment {

    public FragmentAnimation() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view1 = inflater.inflate(R.layout.fragment_animation, container, false);

        final ImageView image = (ImageView)view1.findViewById(R.id.imageView2);
        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                image.setImageResource(R.mipmap.openedit);
                ImageView img1,img2;
                img1 = (ImageView)view1.findViewById(R.id.imageballon1);
                img2= (ImageView)view1.findViewById(R.id.imageballon2);
                img1.setImageResource(R.mipmap.balloon_05);
                img2.setImageResource(R.mipmap.balloon_05);
            }
        });
        return view1;
    }
}
