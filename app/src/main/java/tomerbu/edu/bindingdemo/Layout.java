package tomerbu.edu.bindingdemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

import tomerbu.edu.bindingdemo.databinding.ActivityMainBinding;

/**
 * Created by tomerbuzaglo on 09/06/2016.
 */
public class Layout {
    private final ActivityMainBinding binding;
    private final Context context;
    private String scale;
    private String translate;
    private String rotate;
    private String alpha;

    public Layout(Context context, ActivityMainBinding binding) {
        scale = "Scale";
        translate = "Translate";
        rotate = "Rotate";
        alpha = "Alpha";
        this.binding = binding;
        this.context = context;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public boolean api14() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public void alpha(View v) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", 0);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(600);
        animator.setRepeatCount(4);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();

    }

    public void scale(View v) {
        // ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.SCALE_X, 0f, 1f, 2f, 3f, 4f ,5f);
        int width = binding.getRoot().getWidth();

        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f, 2f, 4f);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f, 2f, 4f);
        PropertyValuesHolder holderRotate = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        PropertyValuesHolder holderTranslate = PropertyValuesHolder.ofFloat("translationX", 0, (width - v.getWidth()) / 2);


        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, holderX, holderY, holderRotate, holderTranslate);


        animator.start();
    }

    public void rotate(View v) {
        set(v);
    }

    public void translate(View v) {

        //v.animate().translationX( (binding.getRoot().getWidth() - v.getWidth()) / 2).start();
        ViewCompat.animate(v).translationX((binding.getRoot().getWidth() - v.getWidth()) / 2).start();

    }

    public void set(View v) {
//        AnimatorSet set = new AnimatorSet();
//
//        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", 0, 1);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(600);
//
//
//        ObjectAnimator rotate = ObjectAnimator.ofFloat(v, "rotation", 0f, 360f);
//        set.playSequentially(animator, rotate);
//        set.start();

        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.card_in);
        animator.setTarget(v);
        animator.start();
    }

    void argb() {

        ObjectAnimator color = ObjectAnimator.ofArgb(binding.included.hero.getDrawable(), "tint",
                context.getResources().getColor(R.color.colorPrimary), 0);
        color.setRepeatCount(ValueAnimator.INFINITE);
        color.setRepeatMode(ValueAnimator.REVERSE);
        color.start();
    }

    public void background(View v) {

        springIt(v);


        /*ObjectAnimator animator = ObjectAnimator.ofInt(v, "backgroundColor", 0xfff, 0xffddeeff);
        animator.setDuration(800);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();*/
    }

    public void springIt(final View v) {

    }

    public void animateLoginButton() {
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                final View v = binding.included.btnLogin;
                final View etName = binding.included.etName;
                final View etPass = binding.included.etPass;

                v.setTranslationX(-v.getWidth());
                etName.setTranslationX(-etName.getWidth());
                etPass.setTranslationX(-etPass.getWidth());
                // Create a system to run the physics loop for a set of springs.
                SpringSystem springSystem = SpringSystem.create();

                // Add a spring to the system.
                Spring spring = springSystem.createSpring();
                spring.setSpringConfig(new SpringConfig(200, 10));
                // Add a listener to observe the motion of the spring.
                spring.addListener(new SimpleSpringListener() {

                    @Override
                    public void onSpringUpdate(Spring spring) {
                        // You can observe the updates in the spring
                        // state by asking its current value in onSpringUpdate.
                        float value = (float) spring.getCurrentValue();

                        v.setTranslationX(value * (binding.getRoot().getWidth() - v.getWidth() - 20));
                        etName.setTranslationX(value * binding.getRoot().getWidth() - etName.getWidth() - 20);
                        etPass.setTranslationX(value * binding.getRoot().getWidth() - etPass.getWidth() - 20);
                        //v.setScaleY(scale);
                    }
                });

                // Set the spring in motion; moving from 0 to 1
                spring.setEndValue(1);

            }
        }, 500);

    }

}
