package com.zachary.reddit.ui.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.zachary.reddit.R;
import com.zachary.reddit.base.BaseAppCompatActivity;
import com.zachary.reddit.ui.AddTopic.AddTopicActivity;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTopicActivity.class);
               startActivity(intent);
            }
        });

        MainFragment mPayFragment = new MainFragment();
        MainPresenter mPresenter = new MainPresenter(mPayFragment);

        getSupportFragmentManager().beginTransaction().add(R.id.flContainer,mPayFragment).commit();
    }
}
