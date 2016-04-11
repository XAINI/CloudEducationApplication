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
import android.widget.TextView;

//implements SurfaceHolder.Callback
public class SurfaceActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private LinearLayout ll;
    private LinearLayout ll2;
    MediaPlayer player;
    SurfaceView surface;
    SurfaceHolder surfaceHolder;
    Button play,pause,stop;

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

        play=(Button)findViewById(R.id.button1);
        pause=(Button)findViewById(R.id.button2);
        stop=(Button)findViewById(R.id.button3);
        surface=(SurfaceView)findViewById(R.id.surface);

        surfaceHolder=surface.getHolder();  //SurfaceHolder是SurfaceView的控制接口
        surfaceHolder.addCallback(this);  //因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
        surfaceHolder.setFixedSize(320, 220);  //显示的分辨率,不设置为视频默认
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  //Surface类型

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player.start();
                Log.v("AlreadyClickPlay","videoStart"+ player);
            }});

        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player.pause();
                Log.v("AlreadyClickPlay","videoPause");
            }});

        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player.stop();
                Log.v("AlreadyClickPlay","videoStop");
            }});

    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        //必须在surface创建后才能初始化MediaPlayer,否则不会显示图像
        player=new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDisplay(surfaceHolder);
        //设置显示视频显示在SurfaceView上
        try {
            player.setDataSource("http://7xsd7r.com1.z0.glb.clouddn.com/%E6%B5%8B%E8%AF%95%E8%A7%86%E9%A2%91.mp4");
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(player.isPlaying()){
            player.stop();
        }
        player.release();
        //Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
    }
}
