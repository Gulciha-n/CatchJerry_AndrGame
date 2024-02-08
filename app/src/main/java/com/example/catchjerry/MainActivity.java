package com.example.catchjerry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    int score;
    Handler handler;
    Runnable run;
    int randomX;
    int randomY;

    ImageView jerry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);
        score = 0;

        jerry = findViewById(R.id.imageView);

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Left : "+ millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this,"Done!",Toast.LENGTH_LONG);
                timeText.setText("Finished");
                handler.removeCallbacks(run);
                jerry.setVisibility(View.INVISIBLE);

                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");

                alert.setPositiveButton("Yes", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_LONG);
                    }
                });
                alert.show();
            }
        }.start();

        generateNumber();

    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score : " + score);
    }

    public void generateNumber(){
        handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                randomX = new Random().nextInt(1000);
                randomY = new Random().nextInt(1000);
                handler.postDelayed(this,400);
                jerry.setX(randomX);
                jerry.setY(randomY);
            }
        };
        handler.post(run);
    }
}

