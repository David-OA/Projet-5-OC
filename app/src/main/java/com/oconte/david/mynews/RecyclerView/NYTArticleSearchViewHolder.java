package com.oconte.david.mynews.RecyclerView;

import android.annotation.SuppressLint;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oconte.david.mynews.Models.Article;
import com.oconte.david.mynews.R;
import com.oconte.david.mynews.Utils.ConfigureDate;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.oconte.david.mynews.Utils.ConfigureText.convertSectionNameForDisplay;

public class NYTArticleSearchViewHolder extends RecyclerView.ViewHolder {

    // FOR DESIGN
    @BindView(R.id.fragment_main_title) TextView textView;
    @BindView(R.id.fragment_main_section) TextView textView1;
    @BindView(R.id.fragment_main_date) TextView date;
    @BindView(R.id.fragment_main_image) ImageView imageView;

    public NYTArticleSearchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void updateWithNYTArticle (Article article) {
        String title = article.getTitle();
        if (title == null) {
            this.textView.setText(article.getHeadline().getMain());
        } else {
            this.textView.setText(title);
        }

        this.date.setText(ConfigureDate.convertDateFromAPIToDisplay(article.getPublishedDate()));

        String section = convertSectionNameForDisplay(article.getSection());
        String subsection = convertSectionNameForDisplay(article.getSubsection());
        String sectionName = convertSectionNameForDisplay(article.getSection());
        if (section.length() <= 0) {
            this.textView1.setText(sectionName);
        } else {
            if (subsection.length() <= 0) {
                this.textView1.setText(section);
            } else {
                this.textView1.setText(section + " > " + subsection);
            }
        }

        String firstUrl = getFirstUrl(article);
        if (firstUrl != null) {
            Picasso.get()
                    .load(firstUrl)
                    .placeholder(R.drawable.info)
                    .resize(60, 60)
                    .into(this.imageView);
        } else {
            Picasso.get()
                    .load(R.drawable.info)
                    .resize(60, 60)
                    .into(this.imageView);
        }

    }

    /**
     * It's for get url Images
     * @param article
     * @return
     */
    public String getFirstUrl(Article article) {
        if (article.getMultimedia() != null && article.getMultimedia().size() > 0) {
            String url = article.getMultimedia().get(0).getUrl();
            return "https://static01.nyt.com/" + url;
        } else {
            return null;
        }

    }
}
