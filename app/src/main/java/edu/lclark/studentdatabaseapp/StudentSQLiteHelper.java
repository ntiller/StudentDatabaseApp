package edu.lclark.studentdatabaseapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ntille on 3/8/16.
 */
public class StudentSQLiteHelper extends SQLiteOpenHelper {

    private static StudentSQLiteHelper sInstance;

    public static final String DB_NAME = "roster.db";

    private StudentSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /**
     * Returns an instance of StudentSqliteHelper
     * @param context : Must be Application Context
     * @return instance of StudentSqliteHelper
     */
    public static StudentSQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new StudentSQLiteHelper(context.getApplicationContext(), DB_NAME, null, 1);
        }

        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Student.TABLE_NAME + " ( " +
                Student._ID + " BIGINT PRIMARY KEY, " +
                Student.COL_NAME + " TEXT, " +
                Student.COL_YEAR + " TEXT, " +
                Student.COL_NET_WORTH + " BIGINT )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + Student.TABLE_NAME);
        onCreate(db);
    }

    public String getCursorString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public long getCursorLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();


        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + Student.TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            do {
                long id = getCursorLong(cursor, Student._ID);
                String name = getCursorString(cursor, Student.COL_NAME);
                double netWorth = getCursorLong(cursor, Student.COL_NET_WORTH);
                String year = getCursorString(cursor, Student.COL_YEAR);
                students.add(new Student(name, id, year, netWorth));
            } while (cursor.moveToNext());

        }

        cursor.close();
        return students;
    }

    public void insertStudent(Student student) {
        getWritableDatabase().insert(Student.TABLE_NAME, null, student.getContentValues());
    }
}
