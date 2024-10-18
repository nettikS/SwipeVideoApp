package com.example.swipevideorb;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ViewPager2 vidViewPager = findViewById(R.id.videosViewPager);

        List<VidItem> vidItemsList = new ArrayList<>();
        VidItem vid1 = new VidItem();
        vid1.vidURL = "https://firebasestorage.googleapis.com/v0/b/fir-test-1eda0.appspot.com/o/KKKKKKKirby.mp4?alt=media&token=402b79b6-cc62-43a0-80fe-8910424a050e";
        vid1.vidTitle = "KKKKKKKirby";
        vid1.vidDesc = "George Kirby's strikeout graphic that shows up on the out-of-town scoreboard";
        vid1.vidID = 2941;
        vidItemsList.add(vid1);

        VidItem vid2 = new VidItem();
        vid2.vidURL = "https://firebasestorage.googleapis.com/v0/b/fir-test-1eda0.appspot.com/o/content_warning_funny.mp4?alt=media&token=9f30affd-8cad-4903-a88d-96aeee02ba50";
        vid2.vidTitle = "Content Warning Funny Moments";
        vid2.vidDesc = "Ryan and his brother laugh at creatures that can't pathfind to them";
        vid2.vidID = 27532;
        vidItemsList.add(vid2);

        VidItem vid3 = new VidItem();
        vid3.vidURL = "https://firebasestorage.googleapis.com/v0/b/fir-test-1eda0.appspot.com/o/spoke%20too%20soon.mp4?alt=media&token=6be36c69-3a5a-4d51-9d4c-041e5c7b4aeb";
        vid3.vidTitle = "Spoke too soon...";
        vid3.vidDesc = "I shouldn't have said anything (LANGUAGE WARNING)";
        vid3.vidID = 50985;
        vidItemsList.add(vid3);

        vidViewPager.setAdapter(new VidAdapter(vidItemsList));

    }
}