package eu.tutorials.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.tutorials.newsapp.model.NewsApiResponse;
import eu.tutorials.newsapp.model.NewsHeadlines;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView rec_main;
    CustomViewHolder adapter;
    ProgressDialog dialog;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        btn_6 = findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        RequestManager manager = new RequestManager(this);
        manager.getNewHeadlines(onFetchDataListener, "general", null);
    }

    private OnFetchDataListener<NewsApiResponse> onFetchDataListener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        rec_main = findViewById(R.id.rec_main);
        rec_main.setHasFixedSize(true);
        rec_main.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomViewHolder(MainActivity.this, list, this);
        rec_main.setAdapter(adapter);
    }

    @Override
    public void onFetch(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class).putExtra("data", headlines));
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        String category = b.getText().toString();
        dialog.setTitle("New " + category + " loading...");
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewHeadlines(onFetchDataListener, category, null);
    }
}