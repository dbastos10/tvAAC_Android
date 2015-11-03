package dbastos.tvaac;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import dbastos.tvaac.config.Keys;

public class VideoActivity extends AppCompatActivity {

    private TvVideo video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("video");
        video = (TvVideo) b.get("video");

        YouTubePlayerSupportFragment youTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.playerFragment);

        youTubePlayerFragment.initialize(Keys.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

                if(!wasRestored) {
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.loadVideo(video.getId(), 0);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        });

        TextView text = (TextView) findViewById(R.id.playerTitle);
        text.setText(video.getDescription());

        setTitle(video.getTitle());
    }

}
