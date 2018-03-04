package com.example.orpriesender.karaoke;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


import java.io.File;
import java.util.List;

/**
 * Created by Or Priesender on 05-Jan-18.
 */

public class FeedAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Post> posts;
    PostListFragment.onUsernameClicked listener;

    public FeedAdapter(Activity activity, List<Post> posts){
            this.posts = posts;
            this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
//        String postId = posts.get(position).getId();
//        long id = Long.parseLong(postId);
        return 0;
    }

    public void setPosts(List<Post> posts){
        this.posts = posts;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //if this list item is empty - inflate it
        if(convertView == null){
            convertView = inflater.inflate(R.layout.feed_item_layout,null);
        }


        //ImageView image = (ImageView) convertView.findViewById(R.id.user_image);
        TextView username = (TextView) convertView.findViewById(R.id.username);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        SeekBar seekBar = (SeekBar) convertView.findViewById(R.id.seek_bar);
        ImageButton playPause = (ImageButton) convertView.findViewById(R.id.play_pause_button);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                posts.get(position).setAudioPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //pause the audio if it plays
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //start the audio if it is paused
            }
        });

        if(listener != null)
            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUsernameClicked(posts.get(position).getUserId());
                }
            });

        convertView.setTag(position);
        username.setText(posts.get(position).getUsername());
        description.setText(posts.get(position).getDescription());
        time.setText(posts.get(position).getTime());
        seekBar.setProgress(posts.get(position).getAudioPosition());

        return convertView;
    }

    public void setOnUsernameClickListener(PostListFragment.onUsernameClicked listener){
        this.listener = listener;
    }
}
