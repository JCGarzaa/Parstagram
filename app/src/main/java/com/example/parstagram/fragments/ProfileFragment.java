package com.example.parstagram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.parstagram.MainActivity;
import com.example.parstagram.Post;
import com.example.parstagram.R;
import com.parse.FindCallback;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends Fragment {
    TextView tvProfileName;
    ImageView ivProfilePicture;
    Button btnLogout;
    MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ProfileFragment() { }
    public ProfileFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvProfileName = view.findViewById(R.id.tvProfileName);
        ivProfilePicture = view.findViewById(R.id.ivProfilePicture);
        tvProfileName.setText(ParseUser.getCurrentUser().getUsername());
        ParseFile file = ParseUser.getCurrentUser().getParseFile(Post.KEY_PFP);
        Glide.with(getContext()).load(file.getUrl()).into(ivProfilePicture);

        btnLogout = view.findViewById(R.id.btnLogOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                mainActivity.goLogin();      // go back to login screen
                Toast.makeText(getContext(), "Logout success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
