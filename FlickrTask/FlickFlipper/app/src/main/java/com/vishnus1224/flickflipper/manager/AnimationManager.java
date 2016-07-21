package com.vishnus1224.flickflipper.manager;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.view.View;

import com.vishnus1224.flickflipper.R;


import javax.inject.Inject;

/**
 * Created by Vishnu on 7/21/2016.
 */
public class AnimationManager {

    private Activity activity;
    AnimatorSet flipOutAnimation;
    AnimatorSet flipInAnimation;

    @Inject
    public AnimationManager(Activity activity) {

        this.activity = activity;
    }

    public void init(){

        flipOutAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.flip_out_animator);
        flipInAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.flip_in_animator);


    }

    public void setFlipOutAnimationTarget(View view){

        if(flipOutAnimation.isRunning()){

            flipOutAnimation.end();
        }

        flipOutAnimation.setTarget(view);


    }

    public void setFlipInAnimationTarget(View view){

        if(flipInAnimation.isRunning()){

            flipInAnimation.end();
        }

        flipInAnimation.setTarget(view);

    }

    public void startAnimations(){

        flipOutAnimation.start();
        flipInAnimation.start();

    }

}
