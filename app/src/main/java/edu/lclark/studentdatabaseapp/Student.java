package edu.lclark.studentdatabaseapp;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by ntille on 3/8/16.
 */
public class Student implements BaseColumns {

    private String mName;
    private long mIdNumber;
    private String mYear;
    private double mNetWorth;

    public static final String TABLE_NAME = "students";
    public static final String COL_NAME = "name";
    public static final String COL_YEAR = "year";
    public static final String COL_NET_WORTH = "net_worth";

    public Student(String name, long idNumber, String year, double netWorth) {
        mName = name;
        mIdNumber = idNumber;
        mYear = year;
        mNetWorth = netWorth;
    }

    public String getName() {
        return mName;
    }

    public long getIdNumber() {
        return mIdNumber;
    }

    public String getYear() {
        return mYear;
    }

    public double getNetWorth() {
        return mNetWorth;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, mIdNumber);
        contentValues.put(COL_NAME, mName);
        contentValues.put(COL_YEAR, mYear);
        contentValues.put(COL_NET_WORTH, mNetWorth);
        return contentValues;
    }
}
