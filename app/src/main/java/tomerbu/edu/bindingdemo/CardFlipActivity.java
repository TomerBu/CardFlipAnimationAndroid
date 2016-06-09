package tomerbu.edu.bindingdemo;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class CardFlipActivity extends AppCompatActivity {
    ImageView cardFront, cardBack;

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cardBack = (ImageView) findViewById(R.id.imgBack);
        cardFront = (ImageView) findViewById(R.id.imgFront);
        loadAnimations();
        changeCameraDistance();
    }



    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_out);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_in);
    }


    public void flipCard(View view) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(cardFront);
            mSetLeftIn.setTarget(cardBack);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(cardBack);
            mSetLeftIn.setTarget(cardFront);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }


    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            cardFront.setCameraDistance(scale);
            cardBack.setCameraDistance(scale);
        }
    }

}
