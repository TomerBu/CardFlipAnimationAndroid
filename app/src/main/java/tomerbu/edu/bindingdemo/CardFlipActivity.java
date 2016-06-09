package tomerbu.edu.bindingdemo;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CardFlipActivity extends AppCompatActivity {
    ImageView cardFront, cardBack;

    private AnimatorSet cardOutAnim;
    private AnimatorSet cardInAnim;
    private boolean isFace = true;




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
        cardOutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_out);
        cardInAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_in);
    }


    public void flipCard(View view) {
        Button b = (Button) view;
        if (isFace) {
            cardOutAnim.setTarget(cardFront);
            cardInAnim.setTarget(cardBack);
            cardOutAnim.start();
            cardInAnim.start();
            isFace = false;
            b.setText("Right Out");
        } else {
            cardOutAnim.setTarget(cardBack);
            cardInAnim.setTarget(cardFront);
            cardOutAnim.start();
            cardInAnim.start();
            isFace = true;
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
