package com.oconte.david.mynews.OptionMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.oconte.david.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.markdownview.MarkdownView;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.markdown_view) MarkdownView markdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        this.configureToolbar();

        this.markDownView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My News");

        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void markDownView(){
        markdownView.loadFromText("### Library\n" +
                "\n" +
                "* [Retrofit](https://square.github.io/retrofit/) - Retrofit\n" +
                "* [Picasso](https://square.github.io/picasso/) - Picasso\n" +
                " \n" +
                "### API\n" +
                "* [New York Times API](https://developer.nytimes.com/) - New York Times API\n" +
                "\n" +
                "### Architecture\n" +
                "* MVP\n" +
                "* Java\n" +
                "\n" +
                "### Developed By \n" +
                "* [OCONTE David](https://fr.linkedin.com/in/david-oconte-95a64314a) \n" +
                "\n" +
                "\n" +
                "\n" +
                "```sh\n" +
                "          Copyright 2020 OCONTE David\n" +
                "```");

        //InputStream inputStream = this.getResources().openRawResource(R.raw.about);
        //InputStreamReader inputStreamReader = new InputStreamReader(inputStream);


    }
}
