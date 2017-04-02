package g.sw2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import g.sw2.R;

public class PostSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_session);
        ImageView imageview = (ImageView)findViewById(R.id.postsessionimage);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageview);

        Glide.with(this).load(R.mipmap.tickmark).into(imageViewTarget);

        TextView txt = (TextView)findViewById(R.id.tapcontinue);
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
