package com.example.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
Button button;
SeekBar seekBar;
String time;
CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textview);
        button=(Button) findViewById(R.id.button);
        seekBar=(SeekBar) findViewById(R.id.seekBar);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        textView.setText("00:30");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("GO!"))
                {
                    seekBar.setEnabled(false);
                    button.setText("STOP");

                    countDownTimer=new CountDownTimer(seekBar.getProgress()*1000+100,100)
                    {

                        @Override
                        public void onTick(long millisUntilFinished) {
                           updateTimer((int)millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {
                            MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.sound);
                            mediaPlayer.start();
                            resetTimer();
                        }
                    }.start();
                }
                else
                {
                    countDownTimer.cancel();
                    resetTimer();

                }
            }
        });
    }
    public void resetTimer()
    {
        seekBar.setEnabled(true);
        button.setText("GO!");
        seekBar.setProgress(30);
        textView.setText("00:30");
    }
    public void updateTimer(int progress)
    {
        int minutes=(int) progress/60;
        int seconds=progress- minutes*60;

        String secondsLeft=String.valueOf(seconds);
        if(seconds<=9)
        {
            secondsLeft="0"+seconds;
        }
        textView.setText(String.valueOf(minutes)+":"+secondsLeft);
    }

}