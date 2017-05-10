package com.zachary.reddit.ui.AddTopic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zachary.reddit.R;
import com.zachary.reddit.base.BaseAppCompatActivity;

public class AddTopicActivity extends BaseAppCompatActivity {
    private AddTopicPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupContentView() {
        setContentView(R.layout.activity_add_topic);
    }

    @Override
    protected void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddTopicFragment addTopicFragment = new AddTopicFragment();
        mPresenter = new AddTopicPresenter(addTopicFragment);

        getSupportFragmentManager().beginTransaction().add(R.id.flContainer,addTopicFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_topic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                mPresenter.addTopic();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
