package com.example.parstagram;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    public TextView tvScreenName;
    public TextView tvTimestamp;
    public TextView tvNameBelowHeart;
    public TextView tvDetailDesc;
    public  TextView tvLikes;
    public ImageView ivPostPic;
    public ImageView ivPFP;
    public ImageButton ibHeart;
    public ImageButton ibComment;
    public ImageButton ibDirectMessage;
    public RecyclerView rvComments;
    public CommentsAdapter adapter;
    public Post post;


    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshComments();
    }

    private void refreshComments() {
        // load all comments for the post
        ParseQuery<Comment> query = ParseQuery.getQuery("Comment");
        query.whereEqualTo(Comment.KEY_POST, post);
        query.include(Comment.KEY_AUTHOR);
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> objects, com.parse.ParseException e) {
                if (e != null) {
                    return;
                }
                adapter.mComments.clear();
                adapter.mComments.addAll(objects);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        post = getIntent().getParcelableExtra("post");

        tvScreenName = findViewById(R.id.tvScreenName);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        tvNameBelowHeart = findViewById(R.id.tvNameBelowHeart);
        tvDetailDesc = findViewById(R.id.tvDetailDesc);
        tvLikes = findViewById(R.id.tvLikes);
        ivPostPic = findViewById(R.id.ivPostPic);
        ivPFP = findViewById(R.id.ivProfilePic);
        rvComments = findViewById(R.id.rvComments);
        ibHeart = findViewById(R.id.ibHeart);

        tvLikes.setText(post.getLikesCount());
        if (post.isLikedByCurrentUser()) {
            ibHeart.setBackgroundResource(R.drawable.ufi_heart_active);
        }
        else {
            ibHeart.setBackgroundResource(R.drawable.ufi_heart);
        }

        ibHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ParseUser> likedBy = post.getLikedBy();
                if (post.isLikedByCurrentUser()) {
                    // unlike
                    post.unlike();
                    ibHeart.setBackgroundResource(R.drawable.ufi_heart);
                }
                else {
                    // like
                    post.like();
                    ibHeart.setBackgroundResource(R.drawable.ufi_heart_active);
                }
                tvLikes.setText(post.getLikesCount());
            }
        });

        adapter = new CommentsAdapter();
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        rvComments.setAdapter(adapter);

        refreshComments();

        ibComment = findViewById(R.id.ibComment);
        ibComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(DetailActivity.this, CommentsActivity.class);
                i.putExtra("post", post);
                startActivity(i);
            }
        });
        ibDirectMessage = findViewById(R.id.ibDM);

        // populate views
        String rawTime = post.getCreatedAt().toString();
        tvTimestamp.setText(getRelativeTimeAgo(rawTime));
        tvScreenName.setText(post.getUser().getUsername());
        tvNameBelowHeart.setText(post.getUser().getUsername());
        tvDetailDesc.setText(post.getDescription());
        Glide.with(this).load(post.getImage().getUrl()).into(ivPostPic);
        int radius = 50;
        if (post.getPFP() != null) {
            Glide.with(this).load(post.getPFP().getUrl()).into(ivPFP);
        }
        else {
            Log.i("detail", "WOOOOOOOOO");
            Glide.with(this).load(R.drawable.icon).into(ivPFP);
        }

    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String timeFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(timeFormat, Locale.ENGLISH);
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