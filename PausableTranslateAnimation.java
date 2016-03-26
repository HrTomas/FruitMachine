package com.example.tomas.fruitmachine;

import android.content.Context;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tomas_000 on 20. 3. 2016.
 */
public class PausableTranslateAnimation extends TranslateAnimation {

    public PausableTranslateAnimation(float fromX, float toX, float fromY, float toY) {
        super(TranslateAnimation.ABSOLUTE, fromX,
                TranslateAnimation.ABSOLUTE, toX,
                TranslateAnimation.RELATIVE_TO_SELF, fromY,
                TranslateAnimation.RELATIVE_TO_SELF, toY);
        // TODO Auto-generated constructor stub
    }

    private long mElapsedAtPause=0;
    private boolean mPaused=false;

    @Override
    public boolean getTransformation(long currentTime, Transformation outTransformation) {
        if(mPaused && mElapsedAtPause==0) {
            mElapsedAtPause=currentTime-getStartTime();
        }
        if(mPaused)
            setStartTime(currentTime-mElapsedAtPause);
        return super.getTransformation(currentTime, outTransformation);
    }

    public void pause(int Postpone) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mElapsedAtPause=0;
                mPaused=true;
            }
        }, Postpone);
    }

    public void resume() {
        mPaused=false;
    }
}
