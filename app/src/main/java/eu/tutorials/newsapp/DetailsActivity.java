package eu.tutorials.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import eu.tutorials.newsapp.model.NewsHeadlines;

public class DetailsActivity extends AppCompatActivity {
    NewsHeadlines newsHeadlines;
    TextView txt_detail_title, txt_detail_author, txt_detail_time, txt_detail_detail, txt_detail_content;
    ImageView img_detail_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        newsHeadlines = (NewsHeadlines) getIntent().getSerializableExtra("data");
        initViews();

        txt_detail_title.setText(newsHeadlines.getTitle());
        txt_detail_author.setText(newsHeadlines.getAuthor());
        txt_detail_time.setText(newsHeadlines.getPublisherAt());
        txt_detail_detail.setText(newsHeadlines.getDescription());
        txt_detail_content.setText(newsHeadlines.getContent());
        if (newsHeadlines.getUrlToImage() != null) {
            Picasso.get().load(newsHeadlines.getUrlToImage()).into(img_detail_news);
        }
    }

    private void initViews() {
        txt_detail_title = findViewById(R.id.txt_detail_title);
        txt_detail_author = findViewById(R.id.txt_detail_author);
        txt_detail_time = findViewById(R.id.txt_detail_time);
        txt_detail_detail = findViewById(R.id.txt_detail_detail);
        txt_detail_content = findViewById(R.id.txt_detail_content);
        img_detail_news = findViewById(R.id.img_detail_news);
    }
}