package com.oconte.david.mynews.OptionMenu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.oconte.david.mynews.R;
import com.oconte.david.mynews.Utils.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.search_button) Button button;

    @BindView(R.id.query_term) EditText mQueryTerm;
    @BindView(R.id.search_fragment_start_begin_date) EditText mBeginDate;
    @BindView(R.id.search_fragment_search_end_date) EditText mEndDate;

    // CheckBox
    @BindView(R.id.search_item_art) CheckBox mArt;
    @BindView(R.id.search_item_business) CheckBox mBusiness;
    @BindView(R.id.search_item_entrepreneurs) CheckBox mEntrepreneurs;
    @BindView(R.id.search_item_politics) CheckBox mPolitics;
    @BindView(R.id.search_item_sport) CheckBox mSport;
    @BindView(R.id.search_item_travel) CheckBox mTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        ButterKnife.bind(this);


        this.configureToolbar();

        this.searchButton();

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

    /**
     * this for start the search
     */
    private void searchButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResultSearchActivity();
            }
        });
    }

    /**
     * This is for do the search on the activity
     */
    private void startResultSearchActivity() {

        String query = mQueryTerm.getText().toString();
        String beginDate = mBeginDate.getText().toString();
        String endDate = mEndDate.getText().toString();
        String art = null;
        if (mArt.isChecked()) {
            art = mArt.getText().toString();
        }
        String business = null;
        if (mBusiness.isChecked()) {
            business = mBusiness.getText().toString();
        }
        String entrepreneurs = null;
        if (mEntrepreneurs.isChecked()) {
            entrepreneurs = mEntrepreneurs.getText().toString();
        }
        String politics = null;
        if (mPolitics.isChecked()) {
            politics = mPolitics.getText().toString();
        }
        String sports = null;
        if (mSport.isChecked()) {
            sports = mSport.getText().toString();
        }
        String travel = null;
        if (mTravel.isChecked()) {
            travel = mTravel.getText().toString();
        }

        /*Log.d("searchActivity", "queryterm " + query);
        Log.d("searchActivity", "beginDate " + beginDate);
        Log.d("searchActivity", "endDate " + endDate);
        Log.d("searchActivity", "art " + art);
        Log.d("searchActivity", "business " + business);
        Log.d("searchActivity", "entrepreneurs " + entrepreneurs);
        Log.d("searchActivity", "politics " + politics);
        Log.d("searchActivity", "sports " + sports);
        Log.d("searchActivity", "travel " + travel);*/

        Intent intent = new Intent(this, ResultSearchActivity.class);
        Bundle searchString = new Bundle();
        searchString.putString("extra_query", query);
        searchString.putString("extra_beginDate", beginDate);
        searchString.putString("extra_endDate", endDate);
        searchString.putString("extra_art", art);
        searchString.putString("extra_business", business);
        searchString.putString("extra_entrepreneurs", entrepreneurs);
        searchString.putString("extra_politics", politics);
        searchString.putString("extra_sports", sports);
        searchString.putString("extra_travel", travel);
        intent.putExtras(searchString);
        startActivity(intent);


        //Toast.makeText(this, "queryterm " + query + " beginDate " + beginDate + " enddate " + endDate + " art " + art, Toast.LENGTH_LONG).show();

        /*Intent intent = new Intent(this, ResultSearchActivity.class);
        mCategorieResult = CategoriesCheck.getQueryCategories(mCheckList);
        Bundle searchString = new Bundle();
        searchString.putString("query", mSearchResult);
        searchString.putString("q", mCategorieResult);
        //mResultSearchViewActivity = new ResultSearchActivity();
        mResultSearchFragment = new ResultSearchFragment();
        mResultSearchFragment.setArguments(searchString);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_search_main_recycler_view, mResultSearchFragment)
                .addToBackStack(SearchViewActivity.class.getSimpleName())
                .commit();*/

    }

    private void onHitEnter() { //  Handle the enter key

        /*mQueryTerm.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER) && checkBoxState()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mQueryTerm.getWindowToken(), 0);
                    startResultSearchActivity();
                    return true;
                }
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER) && !checkBoxState()) {
                    Toast toast =
                            Toast.makeText(getApplicationContext(), "You must check at least one category!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
                return false;
            }
        });*/
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        Calendar calendar = Calendar.getInstance();
        String dateBeginString = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

        String dateEndString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());

        mBeginDate.setText(dateBeginString);
        mEndDate.setText(dateEndString);

    }

}
