package com.oconte.david.mynews.OptionMenu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import com.oconte.david.mynews.Search.ResultSearchActivity;
import com.oconte.david.mynews.Utils.ConfigureDate;
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
                if (mQueryTerm.length() <= 0) {
                    errorQueryTerm();

                } else if (!mArt.isChecked() && !mBusiness.isChecked() && !mEntrepreneurs.isChecked() && !mPolitics.isChecked() && !mSport.isChecked() && !mTravel.isChecked()) {
                    forgetCheckBox();
                } else if (!ConfigureDate.compareDate(mBeginDate.getText().toString(), mEndDate.getText().toString())){
                    incorrectDate();
                } else {
                    startResultSearchActivity();
                }
            }
        });
    }

    ////////////////////////////////////////
    // Error messages
    ////////////////////////////////////////

    public void errorQueryTerm() {
        AlertDialog.Builder myAlertDialogue = new AlertDialog.Builder(this);
        myAlertDialogue.setTitle("Alert !");
        myAlertDialogue.setMessage("You forget something");

        myAlertDialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myAlertDialogue.show();
    }

    public void forgetCheckBox() {
        AlertDialog.Builder myAlertDialogue = new AlertDialog.Builder(this);
        myAlertDialogue.setTitle("Alert !");
        myAlertDialogue.setMessage("You need to choice one ore more categories");

        myAlertDialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myAlertDialogue.show();
    }

    public void incorrectDate() {
        AlertDialog.Builder myAlertDialogue = new AlertDialog.Builder(this);
        myAlertDialogue.setTitle("Alert !");
        myAlertDialogue.setMessage("You are in the future");

        myAlertDialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myAlertDialogue.show();
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

    }

    ///////////////////////////////////////////////
    // DATE PICKER
    ///////////////////////////////////////////////

    private static final int START_SELECTED = 0;
    private static final int END_SELECTED = 1;

    private int selectedDate;

    /**
     *  Gere l'apparition du date picker pour un seul
     * @param v
     */
    public void showStartDatePickerDialog(View v) {
        selectedDate = START_SELECTED;
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void showEndDatePickerDialog(View v) {
        selectedDate = END_SELECTED;
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (selectedDate == START_SELECTED) {
            String dateBeginString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
            mBeginDate.setText(dateBeginString);
        } else if (selectedDate == END_SELECTED) {
            String dateEndString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
            mEndDate.setText(dateEndString);
        }

    }

}
