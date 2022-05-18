package com.neuro.testtask;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.neuro.testtask.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final String TAG = "DebugMainActivity";
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //call method for listen and move textview
        moveTextToTouch(view);
        //call method for listen touch on the textview and stop it
        stopMovement(binding.tvGreeting);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /*
     *This method listen root view and move textView to X Y position
     */
    private void moveTextToTouch(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int y = (int) event.getRawY();
                final int x = (int) event.getRawX();
                binding.tvGreeting.setX(x);
                binding.tvGreeting.setY(y);
                changeTextViewColor(binding.tvGreeting, getLocale().toString());
                runTimer();
                return false;
            }
        });
    }

    /*
     *This method counts 5 seconds and move text down
     */
    private void runTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //toast("5 seconds is passed");
                animationTextMoveTop(binding.tvGreeting);
            }
        }, 3000);
        animationTextMoveDown(binding.tvGreeting);
    }

    /*
     *This method move animate textView down
     */
    private void animationTextMoveDown(TextView textView) {

        textView.animate()
                .translationX(60)
                .translationY(750)
                .setDuration(2000);

    }

    /*
     *This method move animate textView top
     */
    private void animationTextMoveTop(TextView textView) {
        textView.animate()
                .translationX(-10)
                .translationY(-300)
                .setDuration(2000);
    }

    /*
    *This method stop movement textview
     */
    private void stopMovement(TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.animate().cancel();
                textView.clearAnimation();
            }
        });
    }

    /*
     *This method change color of textview
     */
    private void changeTextViewColor(TextView textView, String locale) {
        if (locale.equals("ru_RU")) {
            textView.setTextColor(getResources().getColor(R.color.blue));
        } else if (locale.equals("en_EN")) {
            textView.setTextColor(getResources().getColor(R.color.red));
        }
    }

    /*
     *This method return current locale
     */
    private Locale getLocale() {
        return getResources().getConfiguration().locale;
    }


    /*
     *This message show simple notification
     */
    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}