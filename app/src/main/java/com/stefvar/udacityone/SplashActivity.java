package com.stefvar.udacityone;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve62742 on 28/7/2016.
 */
public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @BindView(R.id.splashlogo)
    ImageView splashlogo;
    @BindView(R.id.splashlayout)
    LinearLayout splashlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);
        ButterKnife.bind(this);

        splashlayout.setBackgroundColor( getResources().getColor( R.color.colorPrimary ) );

        final Boolean netAv = isNetworkAvailable(this);



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                if ( netAv ){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    showMsg("There is no Internet Connection");
                }
            }
        }, SPLASH_TIME_OUT);

    }

    public void showMsg(String msg){

        new SuperToast(this)
                .setText(msg)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setPriorityColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_RED))
                .show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }
        }, Style.DURATION_LONG);
    }


    public static boolean isNetworkAvailable(Context context) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);

            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }


}
