package com.azamirdev.screenhelper;

/*
//
//test gitHub module system
//
//
*/

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class ScreenHelper {
    private final  String LOG_TAG = "ScreenHelper";
    private int REAL_HEIGHT;
    private final int HEIGHT;
    private final int WIDTH;
    private  float DENSITY, SIZE_mm;
    private final int ROTATION_SCREEN;
    private final double HEIGHT_SIZE_mm;
    private final DisplayMetrics metrics;
    private final Context context;
    public final boolean isPortrait;

    public ScreenHelper(Context context){
        metrics = new DisplayMetrics();
        this.context=context;
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        HEIGHT = metrics.heightPixels;
        WIDTH = metrics.widthPixels;
        ((Activity)context).getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        REAL_HEIGHT = metrics.heightPixels;
        ROTATION_SCREEN = ((Activity)context).getWindowManager().getDefaultDisplay().getRotation();
        HEIGHT_SIZE_mm =REAL_HEIGHT / metrics.ydpi;
        REAL_HEIGHT=HEIGHT;
        isPortrait= context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        Log.d(LOG_TAG,"created screen H/W"+REAL_HEIGHT+" / "+WIDTH);

    }
//ROTATION func
    public boolean isRotated(){
        return ROTATION_SCREEN > 0;
    }
    public  int getRotationAngle(){
        switch (ROTATION_SCREEN) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }
//DENSITY convert func
    //public  float mmToSp(int mm){ return (float)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 1,  ((Activity)context).getResources().getDisplayMetrics())*mm;}
    //public  float pxToMM(int px){ return (float)px/TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 1,  ((Activity)context).getResources().getDisplayMetrics());}
    public  float pxToDp(int px) {
    return (float) (px / metrics.density);
}
    public  float dpToPx(int dp) {
        return (float) (dp * metrics.density);
    }
    public  float pxToSp(int px) {
        //return (float) (px / metrics.scaledDensity);
        return (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px,  ((Activity)context).getResources().getDisplayMetrics());
    }
    public  float pxToSp(float px) {
        //return (float) (px / metrics.scaledDensity);
        return (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px,  ((Activity)context).getResources().getDisplayMetrics());
    }
//SCREEN percent
    public int getPercentToPxFromHeight(int percent){
        Log.d(LOG_TAG, " get % px Height: "+ Math.round((float) REAL_HEIGHT / 100 * percent));
    return Math.round((float)REAL_HEIGHT/100*percent);}
    public int getPercentToPxFromWidth(int percent){
        Log.d(LOG_TAG, " get % px Width: "+ Math.round((float) WIDTH / 100 * percent));
        return Math.round((float)WIDTH/100*percent);}
    public int getPercentToPxFromSize(int percent,int size_px){
        Log.d(LOG_TAG," get % size:"+ Math.round((float) size_px / 100 * percent)); return Math.round((float)size_px/100*percent);}
    public int getItemSizeAsScreenWidth(int item_amount){
        Log.d(LOG_TAG, " get item-size as Width:"+ WIDTH / item_amount);
        return WIDTH/item_amount;}
    public int getItemMargin(int item_amount,int percent_margin_as_item){
        Log.d(LOG_TAG, " get item-margin:"+ Math.round((float) getItemSizeAsScreenWidth(item_amount) / 100 * percent_margin_as_item));
        return Math.round((float)getItemSizeAsScreenWidth(item_amount)/100*percent_margin_as_item);
    }

    public void showSnackInfo(View baseView, String text,boolean show_time_longed){
        Snackbar snackbar;
        if (show_time_longed){
         snackbar = Snackbar
                .make(baseView, text, Snackbar.LENGTH_LONG);}else{
            snackbar = Snackbar
                .make(baseView, text, Snackbar.LENGTH_SHORT);}
        snackbar.show();
    }
    public void autoResizeSingleTextSize(TextView textView, String text, float percentWidth,float percentHeight,float stepSize){
        float base_size = 0;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, base_size);
        Rect bounds = new Rect();
        int height = 0;
        while (textView.getPaint().measureText(text) < textView.getWidth() * percentWidth & height <textView.getHeight()*percentHeight) {
            textView.getPaint().getTextBounds(text, 0, text.length(), bounds);
            height = bounds.height();
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, base_size);
            base_size += stepSize;
        }
    }

    public DisplayMetrics getDisplayMetrics(){
        return metrics;
    }
}
