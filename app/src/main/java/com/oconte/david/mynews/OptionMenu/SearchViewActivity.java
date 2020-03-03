package com.oconte.david.mynews.OptionMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.oconte.david.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        ButterKnife.bind(this);


        this.configureToolbar();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * For the toolbar
     */
    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Articles");


        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
