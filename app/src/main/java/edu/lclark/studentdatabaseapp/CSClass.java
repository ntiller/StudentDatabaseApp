package edu.lclark.studentdatabaseapp;

import android.content.ContentValues;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by ntille on 3/9/16.
 */
public class CSClass implements BaseColumns {

    public static final String COL_YEAR = "year";
    public static final String COL_NAME = "name";
    public static final String TABLE_NAME = "csclasses";

    private String mYear, mName, mId;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " TEXT PRIMARY KEY, " +
            COL_NAME + " TEXT, " +
            COL_YEAR + " TEXT )";


    public CSClass(String year, String name, String id) {
        mYear = year;
        mName = name;
        mId = id;
    }

    public static ArrayList<CSClass> getAllClasses() {
        ArrayList<CSClass> classList = new ArrayList<>();
        classList.add(new CSClass(Student.FRESHMAN, "CS 107 Perspectives in Computer Science", "107"));
        classList.add(new CSClass(Student.FRESHMAN, "CS 171 Computer Science I", "171"));
        classList.add(new CSClass(Student.FRESHMAN, "CS 172 Computer Science II", "172"));
        classList.add(new CSClass(Student.SOPHOMORE, "CS 230 Computational Mathematics", "230"));
        classList.add(new CSClass(Student.SOPHOMORE, "CS 277 Computer Architecture and Assembly Languages", "277"));
        classList.add(new CSClass(Student.JUNIOR, "CS 363 Operating Systems", "363"));
        classList.add(new CSClass(Student.JUNIOR, "CS 367 Computer Graphics", "367"));
        classList.add(new CSClass(Student.JUNIOR, "CS 369 Artificial Intelligence", "369"));
        classList.add(new CSClass(Student.JUNIOR, "CS 373 Programming Language Structures", "373"));
        classList.add(new CSClass(Student.JUNIOR, "CS 383 Algorithm Design and Analysis", "383"));
        classList.add(new CSClass(Student.JUNIOR, "CS 393 Computer Networks", "393"));
        classList.add(new CSClass(Student.SENIOR, "CS 465 Theory of Computation", "465"));
        classList.add(new CSClass(Student.SENIOR, "CS 467 Advanced Computer Graphics", "467"));
        classList.add(new CSClass(Student.SENIOR, "CS 487 Advanced Algorithms", "487"));
        classList.add(new CSClass(Student.SENIOR, "CS 488 Software Development", "488"));
        classList.add(new CSClass(Student.SENIOR, "CS 495 Mobile Development", "495"));

        return classList;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, mName);
        contentValues.put(COL_YEAR, mYear);
        contentValues.put(_ID, mId);
        return contentValues;
    }
}
