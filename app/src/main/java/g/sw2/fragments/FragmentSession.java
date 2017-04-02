package g.sw2.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import g.sw2.R;
import g.sw2.views.ActiveChallengeItemView;

/**
 * Created by Kush Agrawal on 3/30/2017.
 */

public class FragmentSession extends Fragment implements ActiveChallengeItemView.Delegate{

    private ViewGroup mLinearLayout;

    public FragmentSession() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_layout, container, false);
        mLinearLayout = (ViewGroup) view.findViewById(R.id.linear_layout);
        mLinearLayout.setVisibility(View.VISIBLE);
        for(int i=0;i<4;i++){
            addLayout();
        }
        return view;
    }

    private void addLayout(){

        Rect rectangle = new Rect();
        Window window =((Activity)getContext()).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop =
                window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight= contentViewTop - statusBarHeight;

        Log.i("*** Elenasys :: ", "StatusBar Height= " + statusBarHeight + " , TitleBar Height = " + titleBarHeight);

        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int cellheight = ((height-statusBarHeight-160)/4) ;

        ActiveChallengeItemView activeChallengeItemView = new ActiveChallengeItemView(getContext(), cellheight);
        activeChallengeItemView.setup(this);

        mLinearLayout.addView(activeChallengeItemView);
        // activeChallengeItemView.badgeView.createFillingAnimation();
    }

    @Override
    public void lockedChallengeTapped() {
        _mClickListener.onViewSelected(456);


    }

    @Override
    public void lockedProChallengeTapped() {

    }

    OnViewSelected _mClickListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _mClickListener = (OnViewSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onViewSelected");
        }
    }

    public interface OnViewSelected {
        public void onViewSelected(int viewId);
    }
}
