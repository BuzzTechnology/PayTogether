package com.buzztechnology.paytogether.paytogether;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

import com.buzztechnology.paytogether.paytogether.choisePal.ChoisePalActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_welcome);
    }

    public void onClickOfflineCalc(View view) {
        getWindow().setExitTransition(new Explode());
        Intent intent = new Intent(WelcomeActivity.this, ChoisePalActivity.class);
//        startActivity(intent);
        startActivity(intent, ActivityOptions
                .makeSceneTransitionAnimation(this).toBundle());
    }
}
