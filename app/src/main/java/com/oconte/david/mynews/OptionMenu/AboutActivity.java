package com.oconte.david.mynews.OptionMenu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.oconte.david.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.markdownview.MarkdownView;

public class AboutActivity extends AppCompatActivity {

    // FOR DESIGN
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

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * it's the markdown view.
     */
    public void markDownView(){
        markdownView.loadFromText("### Library\n" +
                "\n" +
                "* [Android Support Library](https://developer.android.com/topic/libraries/support-library) \n" +
                "* [Gson](https://github.com/google/gson) \n" +
                "* [ButterKnife](https://github.com/JakeWharton/butterknife) \n" +
                "* [Espresso](https://developer.android.com/training/testing/espresso) \n" +
                "* [Mockito](https://site.mockito.org/) \n" +
                "* [Markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) \n" +
                "* [WorkManager](https://developer.android.com/jetpack/androidx/releases/work) \n" +
                "* [Retrofit](https://square.github.io/retrofit/) \n" +
                "* [Picasso](https://square.github.io/picasso/) \n" +
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

    }
}
