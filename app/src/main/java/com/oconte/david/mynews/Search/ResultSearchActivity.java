package com.oconte.david.mynews.Search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.oconte.david.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class ResultSearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.test_view_search) TextView textView;

    //private ResultsSearchView resultsSearchView;
    //private ResultsSearchPresenter presenter;
    //private String queryTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        this.configureToolbar();

        this.getSearchQuery();

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

    // -----------------
    // GET DATA FROM INTENT
    // -----------------
    String query = "";
    String beginDate = "";
    String endDate = "";
    String art ;
    String business ;
    String entrepreneurs ;
    String politics ;
    String sports ;
    String travel ;

    private void getSearchQuery(){

        Bundle searchString = getIntent().getExtras();
        assert searchString != null;

        query = searchString.getString("extra_query");
        beginDate = searchString.getString("extra_beginDate");
        endDate = searchString.getString("extra_endDate");
        art = searchString.getString("extra_art");
        business = searchString.getString("extra_business");
        entrepreneurs = searchString.getString("extra_entrepreneurs");
        politics = searchString.getString("extra_politics");
        sports = searchString.getString("extra_sports");
        travel = searchString.getString("extra_travel");

        /*searchString.putString("extra_query", query);
        searchString.putString("extra_beginDate", beginDate);
        searchString.putString("extra_endDate", endDate);
        searchString.putString("extra_art", art);
        searchString.putString("extra_business", business);
        searchString.putString("extra_entrepreneurs", entrepreneurs);
        searchString.putString("extra_politics", politics);
        searchString.putString("extra_sports", sports);
        searchString.putString("extra_travel", travel);*/

        Log.d("searchString", "extra_query" + query);
        Log.d("searchString", "extra_beginDate " + beginDate);
        Log.d("searchActivity", "endDate " + endDate);
        Log.d("searchActivity", "art " + art);
        Log.d("searchActivity", "business " + business);
        Log.d("searchActivity", "entrepreneurs " + entrepreneurs);
        Log.d("searchActivity", "politics " + politics);
        Log.d("searchActivity", "sports " + sports);
        Log.d("searchActivity", "travel " + travel);

        Toast.makeText(this, query + beginDate + endDate + art + business + entrepreneurs + politics + sports + travel, Toast.LENGTH_LONG).show();

        textView.setText(query + " \n" + beginDate + " \n" + endDate + " \n" + art + " \n" + business + " \n" + entrepreneurs + " \n" + politics + " \n" + sports + " \n" + travel);

        //intent.putExtras(searchString);

    }
}
