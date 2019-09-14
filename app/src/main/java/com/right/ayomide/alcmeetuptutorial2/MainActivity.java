package com.right.ayomide.alcmeetuptutorial2;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaC;
    ImageButton btnPlayPause;
    ProgressDialog progressDialog;

    String videoURL = "android.resource://com.right.ayomide.alcmeetuptutorial2/"+R.raw.next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        videoView = findViewById( R.id.videoView );
        mediaC = new MediaController( this );
        btnPlayPause = findViewById( R.id.btn_play );
        btnPlayPause.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog( MainActivity.this );
                progressDialog.setMessage( "Loading..." );
                progressDialog.show();

                try {
                    //check if video is playing or not
                    if (!videoView.isPlaying())
                    {
                        Uri uri = Uri.parse( videoURL );
                        videoView.setVideoURI( uri );
                        videoView.setMediaController( mediaC );
                        mediaC.setAnchorView( videoView );
                        videoView.start();
                        btnPlayPause.setImageResource( R.drawable.ic_play );
                    }
                    else
                        {
                            progressDialog.dismiss();
                            videoView.pause();
                            btnPlayPause.setImageResource( R.drawable.ic_play );
                        }
                }
                catch (Exception e) //any errors
                {
                    //...
                }
                videoView.requestFocus();
                videoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        progressDialog.dismiss();
                        mediaPlayer.setLooping( true );

                        videoView.start();
                        btnPlayPause.setImageResource( R.drawable.ic_pause );
                    }
                } );
            }
        } );
    }
}
