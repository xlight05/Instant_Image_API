package com.example.asus.fashionista;

import android.graphics.drawable.Drawable;

/**
 * Created by ASUS on 9/21/2017.
 */


public class DataObject {
    private String mText1;
    private String mText2;
    private Drawable drawable;

    public DataObject(String mText1, String mText2) {
        this.mText1 = mText1;
        this.mText2 = mText2;
    }

    public DataObject(String text1, String text2, Drawable drawable) {
        mText1 = text1;
        mText2 = text2;
        this.drawable = drawable;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
