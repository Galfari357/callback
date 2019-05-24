package com.ari.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ari.callback.NewsCallback;
import com.ari.callback.NewsCallbackImpl;
import com.ari.model.artikel.ListData;

public class MainActivity extends AppCompatActivity implements NewsCallback {

    private NewsCallbackImpl newsCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String type = "news";
        String keyword = "";
        String email = "";
        int page = 1;
        int page_size = 10;
        newsCallback = new NewsCallbackImpl(this, this);
        newsCallback.executeGetNewsAll(type, email, page, page_size, keyword);
    }

    @Override
    public void onNewsSuccess(ListData listData) {

    }

    @Override
    public void onNewsFailure(String message) {

    }
}
