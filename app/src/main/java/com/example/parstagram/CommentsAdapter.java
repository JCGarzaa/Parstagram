package com.example.parstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{
    Context context;
    ArrayList<Comment> mComments = new ArrayList<>();

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        Comment comment  = mComments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAuthor;
        public TextView tvBody;
        
        public ViewHolder(@NonNull View view) {
            super(view);
            tvAuthor = (TextView) view.findViewById(R.id.tvAuthor);
            tvBody = (TextView) view.findViewById(R.id.tvBody);
        }

        public void bind(Comment comment) {
            tvAuthor.setText(comment.getAuthor().getUsername());
            tvBody.setText(comment.getBody());
        }
    }
}
