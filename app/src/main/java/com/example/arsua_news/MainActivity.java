package com.example.arsua_news;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements HeadlineFragment.OnHeadlineSelectedListener{

    boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        View newsFragment = findViewById(R.id.news_fragment);
        isDualPane = newsFragment != null && newsFragment.getVisibility() == View.VISIBLE;

        if (!isDualPane) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HeadlineFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onHeadlineSelected(int position) {
        if (isDualPane) {
            // In landscapae, show the news in the second fragment
            NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.news_fragment);
            if (newsFragment != null) {
                newsFragment.updateNews(position);
            }
        } else {
            // In portrait, replace the headline fragment with the news fragment
            NewsFragment newsFragment = new NewsFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newsFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}