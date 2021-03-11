package com.azamirdev.screenhelper;

/*
//
//test gitHub module system
//
//
*/

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

public class ScreenHelper {
    private final  String LOG_TAG = "ScreenHelper";
    private final int REAL_HEIGHT;
    private final int HEIGHT;
    private final int WIDTH;
    private  float DENSITY, SIZE_mm;
    private final int ROTATION_SCREEN;
    private final double HEIGHT_SIZE_mm;
    private final DisplayMetrics metrics;
    private final Context context;
    private Context cc;

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
        return (float) (px / metrics.scaledDensity);
    }
//SCREEN percent
    public int getPercentToPxFromHeight(int percent){return (int)(REAL_HEIGHT/100)*percent;}
    public int getPercentToPxFromWidth(int percent){return (int)(WIDTH/100)*percent;}
    public int getPercentToPxFromSize(int percent,int size_px){return (int)(size_px/100)*percent;}
    public int getItemSizeAsScreenWidth(int item_amount){
        Log.d(LOG_TAG+" getWidth:", String.valueOf(WIDTH/item_amount));
        return WIDTH/item_amount;}
    public int getItemMargin(int item_amount,int percent_margin_as_item){
        return getItemSizeAsScreenWidth(item_amount)/100*percent_margin_as_item;
    }
}
