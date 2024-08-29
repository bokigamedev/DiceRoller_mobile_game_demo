package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int delayTime = 20;
    int rollAnimations = 40;
    int[] diceImages = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3,
            R.drawable.d4, R.drawable.d5, R.drawable.d6};
    Random random = new Random();
    TextView tvHelp;
    ImageView die1;
    ImageView die2;
    LinearLayout diceContainer;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHelp = findViewById(R.id.tvHelp);
        diceContainer = findViewById(R.id.diceContainer);
        die1 = findViewById(R.id.die1);
        die2 = findViewById(R.id.die2);
        mp = MediaPlayer.create(this, R.raw.button_click);
        diceContainer.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View view) {

                try {
                    rollDice();
                }

                catch (Exception e){


                    e.printStackTrace();

                }

            }
        });
    }

    private void rollDice() {

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            for (int i=0; i < rollAnimations; i++)
            {
                int dice1 = random.nextInt(6) + 1;
                int dice2 = random.nextInt(6) + 1;
                die1.setImageResource(diceImages[dice1 - 1]);
                die2.setImageResource(diceImages[dice2 - 1]);
                try {
                    Thread.sleep(delayTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    Thread thread = new Thread(runnable);
    thread.start();
    if(mp != null)
    {

        mp.start();

    }

    }

}