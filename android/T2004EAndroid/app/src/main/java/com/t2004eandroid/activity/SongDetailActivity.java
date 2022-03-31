package com.t2004eandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.t2004eandroid.R;
import com.t2004eandroid.entity.Song;
import com.t2004eandroid.util.MyGlobalClass;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SongDetailActivity extends AppCompatActivity {

    Context _context;
    MediaPlayer mediaPlayer;
    Song currentSong;
    List<Song> songs;
    TextView songName, songCurrentTime, songTotalTime;
    SeekBar seekBar;
    ImageView thumbnailImageView;
    ImageView controlImageView;
    ImageView previousImageView;
    ImageView nextImageView;
    int currentPosition;
    int x = 0;
//    Future longRunningTaskFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        initView();
        initData();
        initActions();
    }

    private void initView() {
        _context = getApplication();
        MyGlobalClass globalVariable = (MyGlobalClass) _context;
        mediaPlayer = globalVariable.getMediaPlayer();
        songName = findViewById(R.id.song_detail_name);
        songCurrentTime = findViewById(R.id.song_detail_current_time);
        songTotalTime = findViewById(R.id.song_detail_total_time);
        seekBar = findViewById(R.id.song_detail_seekbar);
        thumbnailImageView = findViewById(R.id.song_detail_thumbnail);
        controlImageView = findViewById(R.id.song_detail_control);
        previousImageView = findViewById(R.id.song_detail_previous);
        nextImageView = findViewById(R.id.song_detail_next);
        songName.setHorizontallyScrolling(true);
        songName.setSelected(true);
    }

    private void initData() {
//        currentSong = (Song) getIntent().getSerializableExtra("currentSong");
        songs = (List<Song>) getIntent().getSerializableExtra("songs");
        currentPosition = getIntent().getIntExtra("position", 0);
        currentSong = songs.get(currentPosition);
        songName.setText(currentSong.getName());
        try {
            if (mediaPlayer.isPlaying() || mediaPlayer.getCurrentPosition() != 0) {
                mediaPlayer.reset();
            }
            mediaPlayer.setDataSource(currentSong.getLink());
            mediaPlayer.prepare();
            mediaPlayer.start();
            songCurrentTime.setText(convertToTime(String.valueOf(mediaPlayer.getCurrentPosition())));
            songTotalTime.setText(convertToTime(String.valueOf(mediaPlayer.getDuration())));
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initActions() {
        // Seekbar OnChange Action
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable longRunningTask = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    songCurrentTime.setText(convertToTime(String.valueOf(mediaPlayer.getCurrentPosition())));
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    if (mediaPlayer.isPlaying()) {
                        thumbnailImageView.setRotation(x++);
                    } else {
                        thumbnailImageView.setRotation(x);
                    }
                }
                new Handler().postDelayed(this::run, 10);
            }
        };
        runOnUiThread(longRunningTask);

//        longRunningTaskFuture = executorService.submit(longRunningTask);

        // SeekBar User Action
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Control Action
        controlImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("IMAGE VIEW CONSTANT: ",  String.valueOf(controlImageView.getDrawable().getConstantState()));
                Log.d("Play Circle CONSTANT: ",  String.valueOf(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_play_circle_filled_24, null).getConstantState()));
                Log.d("Play Circle CONSTANT CONTEXT: ",  String.valueOf(ContextCompat.getDrawable(SongDetailActivity.this, R.drawable.ic_baseline_play_circle_filled_24).getConstantState()));
                Log.d("TEST FUNCTION", String.valueOf(checkImageResource(SongDetailActivity.this, controlImageView, R.drawable.ic_baseline_play_circle_filled_24)));
                Log.d("TEST Tag", String.valueOf(controlImageView.getTag()));

                if (String.valueOf(controlImageView.getTag()).equals("control_play")) {
                    controlImageView.setTag("control_pause");
                    controlImageView.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                    mediaPlayer.pause();
                } else {
                    controlImageView.setTag("control_play");
                    controlImageView.setImageResource(R.drawable.ic_baseline_stop_circle_24);
                    int currentProgress = mediaPlayer.getCurrentPosition();
                    mediaPlayer.start();
                    mediaPlayer.seekTo(currentProgress);
                }
            }
        });

        // Previous Action
        previousImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPosition == 0) {
                    currentPosition = songs.size() - 1;
                } else {
                    currentPosition--;
                }
                currentSong = songs.get(currentPosition);
                songName.setText(currentSong.getName());
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(currentSong.getLink());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    songCurrentTime.setText(convertToTime(String.valueOf(mediaPlayer.getCurrentPosition())));
                    songTotalTime.setText(convertToTime(String.valueOf(mediaPlayer.getDuration())));
                    seekBar.setProgress(0);
                    seekBar.setMax(mediaPlayer.getDuration());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Next Action
        nextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPosition == songs.size() - 1) {
                    currentPosition = 0;
                } else {
                    currentPosition++;
                }
                currentSong = songs.get(currentPosition);
                songName.setText(currentSong.getName());
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(currentSong.getLink());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    songCurrentTime.setText(convertToTime(String.valueOf(mediaPlayer.getCurrentPosition())));
                    songTotalTime.setText(convertToTime(String.valueOf(mediaPlayer.getDuration())));
                    seekBar.setProgress(0);
                    seekBar.setMax(mediaPlayer.getDuration());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                nextImageView.performClick();
            }
        });
    }

    // di an trom
    public static String convertToTime(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static boolean checkImageResource(Context ctx, ImageView imageView,
                                             int imageResource) {
        boolean result = false;

        if (ctx != null && imageView != null && imageView.getDrawable() != null) {
            Drawable.ConstantState constantState;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                constantState = ctx.getResources()
                        .getDrawable(imageResource, ctx.getTheme())
                        .getConstantState();
            } else {
                constantState = ctx.getResources().getDrawable(imageResource)
                        .getConstantState();
            }

            if (imageView.getDrawable().getConstantState() == constantState) {
                result = true;
            }
        }

        return result;
    }
}