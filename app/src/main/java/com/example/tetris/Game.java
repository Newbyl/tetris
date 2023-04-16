package com.example.tetris;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    MyGLSurfaceView mGLView;
    PreviewView GLPrev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGLView = findViewById(R.id.myGLSurfaceView4);
        GLPrev = findViewById(R.id.previewView);


        TextView score = findViewById(R.id.score);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        if (mGLView.anim())
                        {
                            startActivity(new Intent(Game.this,WinActivity.class));
                        }

                        if (mGLView.lose()){
                            startActivity(new Intent(Game.this,LoseActivity.class));
                            break;
                        }
                }

            }
        };
        Runnable runnable2 = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        Tetromino tetromino = new Tetromino(mGLView.cascade());
                        GLPrev.anim(tetromino);

                        score.setText("SCORE\n" +
                                "" + mGLView.grille.getScore());
                }

            }
        };

        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);

        thread.start();
        thread2.start();
    }

}