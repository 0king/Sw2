package g.sw2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import g.sw2.R;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class PostSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_session);
        GifImageView mGigImageView = (GifImageView) findViewById(R.id.postsessionimage);

        GifDrawable gifDrawable = null;
        try {
            gifDrawable = new GifDrawable(getResources(), R.mipmap.tickmark);
            gifDrawable.setLoopCount(1);
            gifDrawable.setSpeed(1.5f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mGigImageView.setImageDrawable(gifDrawable);


        TextView txt = (TextView)findViewById(R.id.tapcontinue);
        txt.setFocusable(true);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostSessionActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
