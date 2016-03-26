package com.example.tomas.fruitmachine;

/*
Project: Fruit Machine
Class: ShowResults
Purpose: Create animations, handle clicks on buttons
Author: Tomas Hreha
Date: 25.3.2015
 */

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShowResults extends AppCompatActivity {

    DatabaseOperation DB = new DatabaseOperation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        LinearLayout Results = (LinearLayout) findViewById(R.id.Results);
        //Get height and width of screen for correct responsive effect
        Configuration config = getResources().getConfiguration();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(config.screenHeightDp/5, config.screenHeightDp/5);
        //Load data from database
        Cursor CR = DB.GetData(DB, "");
        if(!CR.moveToFirst())
            return;
        do {
            LinearLayout oneRow = new LinearLayout(this);
            oneRow.setOrientation(LinearLayout.HORIZONTAL);
            //Determinate which picture was at the end of animation
//            ImageView firstImage = new ImageView(this);
//            switch (CR.getInt(0)) {
//                case 0:
//                    firstImage.setImageResource(R.drawable.orange);
//                    break;
//                case 1:
//                    firstImage.setImageResource(R.drawable.banana);
//                    break;
//                case 2:
//                    firstImage.setImageResource(R.drawable.apple);
//                    break;
//                case 3:
//                    firstImage.setImageResource(R.drawable.cherry);
//                    break;
//            }
//            firstImage.setLayoutParams(layoutParams);
//            firstImage.getLayoutParams().width = config.screenWidthDp/4;
//            firstImage.getLayoutParams().height = config.screenWidthDp/4;
//            oneRow.addView(firstImage);
//
//            ImageView secondImage = new ImageView(this);
//            switch (CR.getInt(1)) {
//                case 0:
//                    secondImage.setImageResource(R.drawable.orange);
//                    break;
//                case 1:
//                    secondImage.setImageResource(R.drawable.banana);
//                    break;
//                case 2:
//                    secondImage.setImageResource(R.drawable.apple);
//                    break;
//                case 3:
//                    secondImage.setImageResource(R.drawable.cherry);
//                    break;
//            }
//            secondImage.setLayoutParams(layoutParams);
//            secondImage.getLayoutParams().width = config.screenWidthDp/4;
//            secondImage.getLayoutParams().height = config.screenWidthDp/4;
//            oneRow.addView(secondImage);
//
//            ImageView thirdImage = new ImageView(this);
//            switch (CR.getInt(2)) {
//                case 0:
//                    thirdImage.setImageResource(R.drawable.orange);
//                    break;
//                case 1:
//                    thirdImage.setImageResource(R.drawable.banana);
//                    break;
//                case 2:
//                    thirdImage.setImageResource(R.drawable.apple);
//                    break;
//                case 3:
//                    thirdImage.setImageResource(R.drawable.cherry);
//                    break;
//            }
//            thirdImage.setLayoutParams(layoutParams);
//            thirdImage.getLayoutParams().width = config.screenWidthDp/4;
//            thirdImage.getLayoutParams().height = config.screenWidthDp/4;
//            oneRow.addView(thirdImage);

            TextView resultText = new TextView(this);
            resultText.setTextSize(20);
            resultText.setText(CR.getString(4) + " " + CR.getString(3));
            oneRow.addView(resultText);

            Results.addView(oneRow);

        }while(CR.moveToNext());
    }

}
