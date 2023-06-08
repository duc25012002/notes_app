package eu.tutorials.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eu.tutorials.newsapp.model.NewsHeadlines;

public class CustomViewHolder extends RecyclerView.Adapter<CustomViewHolder.ViewHolder> {
    private Context context;
    private List<NewsHeadlines> newsHeadlinesList;
    private SelectListener selectListener;

    public CustomViewHolder(Context context, List<NewsHeadlines> newsHeadlinesList, SelectListener selectListener) {
        this.context = context;
        this.newsHeadlinesList = newsHeadlinesList;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_headline_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsHeadlines newsHeadlines = newsHeadlinesList.get(position);
        holder.bind(newsHeadlines);
    }

    @Override
    public int getItemCount() {
        return newsHeadlinesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView main_container;
        TextView txt_title, txt_source;
        ImageView img_headline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main_container = itemView.findViewById(R.id.main_container);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_source = itemView.findViewById(R.id.txt_source);
            img_headline = itemView.findViewById(R.id.img_headline);
        }

        public void bind(NewsHeadlines newsHeadlines) {
            txt_title.setText(newsHeadlines.getTitle());
            txt_source.setText(newsHeadlines.getSource().getName());
            if (newsHeadlines.getUrlToImage() != null) {
                Picasso.get().load(newsHeadlines.getUrlToImage()).into(img_headline);
            }
            main_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectListener.onFetch(newsHeadlines);
                }
            });
        }
    }
}
