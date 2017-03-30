package g.sw2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import g.sw2.R;

public class SlideUpActivity extends AppCompatActivity implements View.OnClickListener {

    FrameLayout framelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_up);
        framelayout = (FrameLayout)findViewById(R.id.activity_main);


        final View dragView = findViewById(R.id.imageButton2);
        //dragView.setOnTouchListener(this);
        dragView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        slideToTop();
    }

    public void slideToTop(){
        TranslateAnimation animate = new TranslateAnimation(0,0,0,-framelayout.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        framelayout.startAnimation(animate);
        framelayout.setVisibility(View.GONE);
        /*Intent intent = new Intent(SlideUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();*/
    }
}
