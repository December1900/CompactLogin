package net.december1900.compactlogin;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.register_view)
    RelativeLayout mRegisterView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.card)
    CardView mCard;
    @BindView(R.id.btn_close)
    ImageView mBtnClose;
    Animation alphaAppear, alphaDisappear;
    int x, y, width, height, hypotenuse;
    float pixelDensity;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        pixelDensity = getResources().getDisplayMetrics().density;
        alphaAppear = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        alphaDisappear = AnimationUtils.loadAnimation(this, R.anim.alpha_disappear);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLogin();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnLogin.setBackground(ContextCompat.getDrawable(LoginActivity.this,R.drawable.bg_press_btn));
            }
        });

    }

    private void goToRegister() {
        width = mCard.getWidth();
        height = mCard.getHeight();

        x = width / 2;
        y = height / 2;
        hypotenuse = (int) Math.hypot(x , y);

        y = (int) (y - ((40 * pixelDensity) + (40 * pixelDensity)));

        FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                mRegisterView.getLayoutParams();
        parameters.height = mCard.getHeight();
        mRegisterView.setLayoutParams(parameters);

        mFab.animate()
                .translationX(-x)
                .translationY(-y)
                .setDuration(350)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        Animator anim = ViewAnimationUtils.createCircularReveal(mRegisterView, width / 2, height / 2, 40 * pixelDensity, hypotenuse);
                        anim.setDuration(350);
                        anim.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {
                                System.out.println("start");
                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                System.out.println("startend");

                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                        mFab.setVisibility(View.GONE);
                        mRegisterView.setVisibility(View.VISIBLE);
                        anim.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    private void backToLogin() {

        alphaDisappear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animator anim = ViewAnimationUtils.createCircularReveal(mRegisterView, width / 2, height / 2, hypotenuse, 40 * pixelDensity);
                anim.setDuration(350);
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mRegisterView.setVisibility(View.GONE);
                        mFab.setVisibility(View.VISIBLE);
                        mFab.animate()
                                .translationX(0f)
                                .translationY(0f)
                                .setDuration(200)
                                .setListener(null);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                anim.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mBtnClose.startAnimation(alphaDisappear);
    }
}




