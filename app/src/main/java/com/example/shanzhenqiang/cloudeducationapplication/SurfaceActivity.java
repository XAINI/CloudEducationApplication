package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

//implements SurfaceHolder.Callback
public class SurfaceActivity extends AppCompatActivity{

    private LinearLayout ll;
    private LinearLayout ll2;

    private MediaPlayer player;
    private SurfaceView surface;
    private Button play,pause,stop,replay;
    private SeekBar seekBar;

    private boolean isChanging;//互斥变量，防止定时器与SeekBar拖动时进度冲突
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        ll2 = (LinearLayout) findViewById(R.id.headerId);
        ll = (LinearLayout) findViewById(R.id.footerId);

        // header
        TextView textView = (TextView) ll2.findViewById(R.id.headerTitleTextViewId);
        textView.setText("PHP视频教程");
        final Intent intentBack = new Intent(this, CurriculumMaterialActivity.class);
        ImageButton imageBtn = (ImageButton) ll2.findViewById(R.id.backId);
        imageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(intentBack);
            }
        });

        // footer
        final Intent intent1 = new Intent(this, CloudEducationHomeActivity.class);
        final Intent intent2 = new Intent(this, CurriculumActivity.class);
        final Intent intent3 = new Intent(this, PersonalCenterActivity.class);

        ImageButton btn1 = (ImageButton) ll.findViewById(R.id.home_page);
        ImageButton btn2 = (ImageButton) ll.findViewById(R.id.allCurriculum);
        ImageButton btn3 = (ImageButton) ll.findViewById(R.id.myCenter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent3);
            }
        });

        // video player

        play = (Button)findViewById(R.id.button1);// 播放
        pause = (Button)findViewById(R.id.button2); //暂停
        stop = (Button)findViewById(R.id.button3); // 停止
        replay = (Button) findViewById(R.id.replayBtn);
        surface=(SurfaceView)findViewById(R.id.surface);
        seekBar = (SeekBar) this.findViewById(R.id.seekBarId);

        player = new MediaPlayer();
        player.setOnCompletionListener(complete);


        play.setOnClickListener(click);
        pause.setOnClickListener(click);
        stop.setOnClickListener(click);
        replay.setOnClickListener(click);


        // 为SurfaceHolder添加回掉
        surface.getHolder().addCallback(callback);
        seekBar.setOnSeekBarChangeListener(change);

    }




    //测试代码
    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (currentPosition > 0){
                play(currentPosition);
                currentPosition = 0;
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (player != null && player.isPlaying()){
                currentPosition = player.getCurrentPosition();
                player.stop();
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener change = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            if (player != null && player.isPlaying()){
                player.seekTo(progress);
            }
        }

    };

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    play(0);
                    break;
                case R.id.button2:
                    pause();
                    break;
                case R.id.button3:
                    stop();
                    break;
                case R.id.replayBtn:
                    replay();
                    break;
                default:
                    break;
            }
        }
    };

    protected void stop(){
        if (player != null && player.isPlaying()){
            player.stop();
            player.release();
            player = null;
            play.setEnabled(true);
            isChanging = false;
        }
    }

    protected void play(final int msec){
        try {
            player = new MediaPlayer();
            player.reset();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDisplay(surface.getHolder());
            player.setDataSource("http://7xsd7r.com1.z0.glb.clouddn.com/%E6%B5%8B%E8%AF%95%E8%A7%86%E9%A2%91.mp4");
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    player.seekTo(msec);
                    seekBar.setMax(player.getDuration());
                    new Thread(){
                        public void run(){
                            try {
                                isChanging = true;
                                while (isChanging){
                                    int current = player.getCurrentPosition();
                                    seekBar.setProgress(current);
                                    sleep(500);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }catch (IllegalStateException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                    play.setEnabled(false);
                }
            });
            player.prepareAsync();

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    play.setEnabled(true);
                }
            });

            player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    play(0);
                    isChanging = false;
                    return false;
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void replay(){
        System.out.println("player"+player.getDuration());
        if (player != null && player.isPlaying()){
            player.seekTo(0);
            Toast.makeText(this, "重新播放", Toast.LENGTH_LONG).show();
            pause.setText("暂停");
            return;
        }
        replay.setEnabled(true);
        isChanging = false;
        play(0);
    }

    protected void pause(){
        if (pause.getText().toString().trim().equals("播放")){
            pause.setText("暂停");
            player.start();
            Toast.makeText(this, "继续播放", Toast.LENGTH_LONG).show();
            return;
        }

        if (player != null && player.isPlaying()){
            player.pause();
            pause.setText("播放");
            Toast.makeText(this, "暂停播放", Toast.LENGTH_LONG).show();
        }
    }


    private MediaPlayer.OnCompletionListener complete = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            play(0);
        }
    };


}
