package com.androidopshanka.mynote;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Note implements Serializable {

    private long mDatTime;
    private String mTitile;
    private String mContent;

    public Note(long DatTime, String Title, String Content) {
        this.mDatTime = DatTime;
        this.mTitile = Title;
        this.mContent = Content;
    }

    public long getmDatTime() {
        return mDatTime;
    }

    public String getmTitile() {
        return mTitile;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmDatTime(long mDatTime) {
        this.mDatTime = mDatTime;
    }

    public void setmTitile(String mTitile) {
        this.mTitile = mTitile;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String dateFormated(Context context){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ",context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDatTime));

    }
}
