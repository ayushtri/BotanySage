package com.celes.botanysage.helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.celes.botanysage.R;

import java.util.ArrayList;

public class RecycleViewAdapterReddit extends RecyclerView.Adapter<RecycleViewAdapterReddit.ViewHolder> {
    Context context;
    ArrayList<RedditModelClass> redditPosts;
    RecycleViewAdapterReddit(Context context, ArrayList<RedditModelClass> redditPosts){
        this.context = context;
        this.redditPosts = redditPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reddit_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(redditPosts.get(position).imgUrl).error(R.drawable.ic_launcher_background).into(holder.redImgView);
        holder.redTitle.setText(redditPosts.get(position).title);
        holder.redUser.setText(redditPosts.get(position).user);
        String pUrl = redditPosts.get(position).postUrl;
        holder.redUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(pUrl));
                v.getContext().startActivity(intent);
                holder.redUrl.setTextColor(Color.parseColor("#CF9FFF"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return redditPosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView redImgView;
        TextView redTitle, redUser, redUrl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            redImgView = itemView.findViewById(R.id.redditImgView);
            redTitle = itemView.findViewById(R.id.redditTitle);
            redUser = itemView.findViewById(R.id.redditUser);
            redUrl = itemView.findViewById(R.id.redditDirect);
        }
    }
}
