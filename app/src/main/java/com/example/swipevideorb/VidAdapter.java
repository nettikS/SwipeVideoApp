package com.example.swipevideorb;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VidAdapter extends RecyclerView.Adapter<VidAdapter.VidViewHolder>{

    private List<VidItem> vidItems;

    public VidAdapter(List<VidItem> vidItems){
        this.vidItems = vidItems;
    }

    @NonNull
    @Override
    public VidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VidViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull VidViewHolder holder, int position) {
        holder.setVidData(vidItems.get(position));
    }

    @Override
    public int getItemCount() {
        return vidItems.size();
    }


    static class VidViewHolder extends RecyclerView.ViewHolder {
        TextView textVidTitle1, textVidDesc1, textVidID1;
        VideoView vidView;
        ProgressBar progressBar;

        public VidViewHolder(@NonNull View itemView) {
            super(itemView);
            vidView = itemView.findViewById(R.id.videoView);
            textVidTitle1 = itemView.findViewById(R.id.textVideoTitle);
            textVidDesc1 = itemView.findViewById(R.id.textVideoDesc);
            textVidID1 = itemView.findViewById(R.id.textVideoID);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        @SuppressLint("DefaultLocale")
        void setVidData(VidItem vidItem){
            textVidTitle1.setText(vidItem.vidTitle);
            textVidDesc1.setText(vidItem.vidDesc);
            textVidID1.setText(String.format("ID: %06d", vidItem.vidID));
            vidView.setVideoPath(vidItem.vidURL);

            vidView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();

                    float vidRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = vidView.getWidth() / (float) vidView.getHeight();
                    float scale = vidRatio / screenRatio;

                    if (scale >= 1f){
                        vidView.setScaleX(scale);
                    }else{
                        vidView.setScaleY(1f / scale);
                    }
                }
            });

            vidView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }


}
