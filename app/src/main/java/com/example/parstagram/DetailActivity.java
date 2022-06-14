package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcel;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    public TextView tvScreenName;
    public TextView tvTimestamp;
    public TextView tvNameBelowHeart;
    public TextView tvDetailDesc;
    public ImageView ivPostPic;

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvScreenName = findViewById(R.id.tvScreenName);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        tvNameBelowHeart = findViewById(R.id.tvNameBelowHeart);
        tvDetailDesc = findViewById(R.id.tvDetailDesc);
        ivPostPic = findViewById(R.id.ivPostPic);

        // unwrap info from intent
        Post post = Parcels.unwrap(getIntent().getParcelableExtra("post"));

        String rawTime = post.getCreatedAt().toString();

        tvTimestamp.setText(getRelativeTimeAgo(rawTime));
        tvScreenName.setText(post.getUser().getUsername());
        tvNameBelowHeart.setText(post.getUser().getUsername());
        tvDetailDesc.setText(post.getDescription());
        Glide.with(this).load(post.getImage().getUrl()).into(ivPostPic);
    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}