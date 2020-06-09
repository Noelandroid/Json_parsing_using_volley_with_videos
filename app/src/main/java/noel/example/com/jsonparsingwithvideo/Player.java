package noel.example.com.jsonparsingwithvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class Player extends AppCompatActivity {
    public  static  final String TAG="TAG";
    ProgressBar spliner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spliner=findViewById(R.id.progressBar);

        Intent intent= getIntent();
        Bundle data= intent.getExtras();
        Video v=(Video) data.getSerializable("videodata");

        getSupportActionBar().setTitle(v.getTitle());

        TextView title=findViewById(R.id.videotitle);
        TextView desc=findViewById(R.id.videoDesc);
        final VideoView videplayer = findViewById(R.id.videoView);


        try {
            title.setText(v.getTitle());
            desc.setText(v.getDescription());
            Uri videoUrl = Uri.parse(v.getVideourl());
            videplayer.setVideoURI(videoUrl);
            videplayer.requestFocus();
            videplayer.start();
            MediaController mc=new MediaController(this);
            videplayer.setMediaController(mc);

            videplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    videplayer.start();
                    spliner.setVisibility(View.GONE);

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
