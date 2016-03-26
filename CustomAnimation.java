package com.example.tomas.fruitmachine;

/*
Project: Fruit Machine
Class: CustomAnimation
Purpose: Handle running animations.
Author: Tomas Hreha
Date: 25.3.2015
 */


import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CustomAnimation {

    TranslateAnimation firstAnimation = new PausableTranslateAnimation(0, 0, -1, 0);
    TranslateAnimation secondtAnimation = new PausableTranslateAnimation(0, 0, 0, 1);
    TranslateAnimation finishFirstAnimation, finishSecondAnimation;

    LinearLayout firstColumn, secondColumn;
    int duration;
    boolean stopAnimation = false, restartAnimation = false;
    //Constructor for class, set handling for every column
    public CustomAnimation(LinearLayout firstColumn, LinearLayout secondColumn, int duration) {
        this.firstColumn = firstColumn;
        this.secondColumn = secondColumn;
        this.duration = duration;
    }
    //Create and handle animations
    public void CreateAnimation(final double durationModification) {
        //Restart animations
        if(restartAnimation) {
            firstAnimation = new PausableTranslateAnimation(0, 0, -1, 0);
            secondtAnimation = new PausableTranslateAnimation(0, 0, 0, 1);
            stopAnimation = false;
        } else
            restartAnimation = false;

        firstAnimation.setDuration(duration);
        firstAnimation.setInterpolator(new LinearInterpolator());
        firstColumn.startAnimation(firstAnimation);

        secondtAnimation.setDuration(duration);
        secondtAnimation.setInterpolator(new LinearInterpolator());
        secondColumn.startAnimation(secondtAnimation);

        //Finish animations
        finishFirstAnimation.setDuration(duration);
        finishFirstAnimation.setInterpolator(new LinearInterpolator());
        finishFirstAnimation.setFillAfter(true);

        finishSecondAnimation.setDuration(duration);
        finishSecondAnimation.setInterpolator(new LinearInterpolator());
        finishSecondAnimation.setFillAfter(true);

        //Repeat animations and accelerate or decelerate after every repetition
        firstAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animatio) {
                firstAnimation.setDuration(duration);
                firstAnimation.getStartTime();
            }

            @Override
            public void onAnimationEnd(Animation animatio) {
                firstColumn.clearAnimation();

                if (stopAnimation) {
                    if (duration <= 4000) {
                        //Slow down
                        duration = (int) (duration + durationModification);
                        firstColumn.startAnimation(firstAnimation);
                    } else {
                        firstAnimation = null;
                        firstColumn.startAnimation(finishFirstAnimation);
                        secondColumn.clearAnimation();
                        secondtAnimation = null;
                        secondColumn.startAnimation(finishSecondAnimation);
                    }
                } else {
                    if (duration > 1000)
                        //Speed up
                        duration = (int) (duration - durationModification);
                    firstColumn.startAnimation(firstAnimation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        secondtAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animatio) {
                secondtAnimation.setDuration(duration);
                secondtAnimation.getStartTime();
            }

            @Override
            public void onAnimationEnd(Animation animatio) {
                secondColumn.clearAnimation();

                if (stopAnimation) {
                    if (duration <= 4000) {
                        duration = (int) (duration + durationModification);
                        secondColumn.startAnimation(secondtAnimation);
                    } else {
                        System.out.print("finisujem");
                        secondtAnimation = null;
                        secondColumn.startAnimation(finishSecondAnimation);
                    }
                } else {
                    if (duration > 1000)
                        duration = (int) (duration - durationModification);
                    secondColumn.startAnimation(secondtAnimation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void CreateFinishApplication(int position) {
        //Determine which image is finale
        switch (position) {
            case 0:
                finishFirstAnimation = new PausableTranslateAnimation(0, 0, -1, 0);
                finishSecondAnimation = new PausableTranslateAnimation(0, 0, 0, 1);
                break;
            case 1:
                finishFirstAnimation = new PausableTranslateAnimation(0, 0, -1, -0.75f);
                finishSecondAnimation = new PausableTranslateAnimation(0, 0, 0, 0.25f);
                break;
            case 2:
                finishFirstAnimation = new PausableTranslateAnimation(0, 0, -1, -0.5f);
                finishSecondAnimation = new PausableTranslateAnimation(0, 0, 0, 0.5f);
                break;
            case 3:
                finishFirstAnimation = new PausableTranslateAnimation(0, 0, -1, -0.25f);
                finishSecondAnimation = new PausableTranslateAnimation(0, 0, 0, 0.75f);
                break;
        }
    }
}
