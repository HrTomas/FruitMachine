package com.example.tomas.fruitmachine;

/*
Project: Fruit Machine
Class: MainActivity
Purpose: Create animations, handle clicks on buttons
Author: Tomas Hreha
Date: 25.3.2015
Known issues: Parsing JSON data doesnt work, downloading pictures using url dosnt work,
animations is lugging little bit, GUI can look better too.
 */


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int duration = 4000;
    private CustomAnimation centerAnimation, leftAnimation, rightAnimation;
    private DatabaseOperation DB = new DatabaseOperation(this);
    private String result = "";
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final LinearLayout CenterColumn = (LinearLayout) findViewById(R.id.CenterColumn);
        CreateView(CenterColumn);
        final LinearLayout CenterColumn1 = (LinearLayout) findViewById(R.id.CenterColumn1);
        CreateView(CenterColumn1);
        final LinearLayout LeftColumn = (LinearLayout) findViewById(R.id.LeftColumn);
        CreateView(LeftColumn);
        final LinearLayout LeftColumn1 = (LinearLayout) findViewById(R.id.LeftColumn1);
        CreateView(LeftColumn1);
        final LinearLayout RightColumn = (LinearLayout) findViewById(R.id.RightColumn);
        CreateView(RightColumn);
        final LinearLayout RightColumn1 = (LinearLayout) findViewById(R.id.RightColumn1);
        CreateView(RightColumn1);

        //Determines how it ends
        final int firstPosition = random.nextInt(4);
        final int secondPosition = random.nextInt(4);
        final int thirdPosition = random.nextInt(4);

        //Determines if players wins or not for database
        if((firstPosition == secondPosition) && (firstPosition == thirdPosition))
            result = "Win!!!";
        else
            result = "Lose.";

        //Create animations
        centerAnimation = new CustomAnimation(CenterColumn, CenterColumn1, duration);
        centerAnimation.CreateFinishApplication(firstPosition);
        centerAnimation.CreateAnimation(300);

        leftAnimation = new CustomAnimation(LeftColumn, LeftColumn1, duration);
        leftAnimation.CreateFinishApplication(secondPosition);
        leftAnimation.CreateAnimation(225);

        rightAnimation = new CustomAnimation(RightColumn, RightColumn1, duration);
        rightAnimation.CreateFinishApplication(thirdPosition);
        rightAnimation.CreateAnimation(350);

        //Buttons listeners
        Button Spin = (Button) findViewById(R.id.Spin);
        Spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random = new Random();
                centerAnimation.restartAnimation = true;
                leftAnimation.restartAnimation = true;
                rightAnimation.restartAnimation = true;
                centerAnimation.CreateAnimation(500);
                leftAnimation.CreateAnimation(425);
                rightAnimation.CreateAnimation(550);
            }
        });

        Button Stop = (Button) findViewById(R.id.Stop);
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DateTime for primary key for database
                Calendar c = Calendar.getInstance();
                System.out.println("Current time =&gt; "+c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = df.format(c.getTime());

                centerAnimation.stopAnimation = true;
                leftAnimation.stopAnimation = true;
                rightAnimation.stopAnimation = true;
                //Insert data into database
                DB.PutData(DB, String.valueOf(firstPosition), String.valueOf(secondPosition), String.valueOf(thirdPosition), result, dateTime);
            }
        });

        Button Result = (Button) findViewById(R.id.Results);
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShowResults.class);
                i.putExtra("TurnamentPK", "0");
                startActivity(i);
            }
        });
    }

    public void CreateView(LinearLayout columnForChange) {

        //Set height and width of elements for correct responsive effect
        Configuration config = getResources().getConfiguration();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(config.screenHeightDp/5, config.screenHeightDp/5);

        RelativeLayout TopWhite = (RelativeLayout) findViewById(R.id.TopWhite);
        TopWhite.getLayoutParams().height = config.screenHeightDp/5;
        RelativeLayout BottomWhite = (RelativeLayout) findViewById(R.id.BottomWhite);
        BottomWhite.getLayoutParams().height = config.screenHeightDp/5;

//        Bitmap bitmap = GetImage("https://bytebucket.org/thefuntasty/test-android/raw/master/banana.jpg");
        ImageView bananaPicture = new ImageView(this);
//        bananaPicture.setImageBitmap(bitmap);
        bananaPicture.setImageResource(R.drawable.banana);
        bananaPicture.setLayoutParams(layoutParams);
        bananaPicture.getLayoutParams().width = config.screenHeightDp/5;
        bananaPicture.getLayoutParams().height = config.screenHeightDp/5;
        columnForChange.addView(bananaPicture);

//        bitmap = GetImage("https://bytebucket.org/thefuntasty/test-android/raw/master/orange.jpg");
        ImageView applePicture = new ImageView(this);
        //        applePicture.setImageBitmap(bitmap);
        applePicture.setImageResource(R.drawable.apple);
        applePicture.setLayoutParams(layoutParams);
        applePicture.getLayoutParams().width = config.screenHeightDp/5;
        applePicture.getLayoutParams().height = config.screenHeightDp/5;
        columnForChange.addView(applePicture);

//        bitmap = GetImage("https://bytebucket.org/thefuntasty/test-android/raw/master/cherry.jpg");
        ImageView cherryPicture = new ImageView(this);
//        cherryPicture.setImageBitmap(bitmap);
        cherryPicture.setImageResource(R.drawable.cherry);
        cherryPicture.setLayoutParams(layoutParams);
        cherryPicture.getLayoutParams().width = config.screenHeightDp/5;
        cherryPicture.getLayoutParams().height = config.screenHeightDp/5;
        columnForChange.addView(cherryPicture);

//        bitmap = GetImage("https://bytebucket.org/thefuntasty/test-android/raw/master/apple.jpg");
        ImageView orangePicture = new ImageView(this);
//        orangePicture.setImageBitmap(bitmap);
        orangePicture.setImageResource(R.drawable.orange);
        orangePicture.setLayoutParams(layoutParams);
        orangePicture.getLayoutParams().width = config.screenHeightDp/5;
        orangePicture.getLayoutParams().height = config.screenHeightDp/5;
        columnForChange.addView(orangePicture);
    }

    //Function for retriev images from url, but it doesnt work
    private Bitmap GetImage(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            System.out.print("Error while loading images.");
            e.printStackTrace();
            return null;
        }
    }
}
