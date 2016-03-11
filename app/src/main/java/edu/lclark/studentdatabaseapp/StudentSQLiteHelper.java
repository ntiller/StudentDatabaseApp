package edu.lclark.studentdatabaseapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ntille on 3/8/16.
 */
public class StudentSQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "roster.db";
    private static StudentSQLiteHelper sInstance;

    private StudentSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /**
     * Returns an instance of StudentSqliteHelper
     *
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
                Student._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Student.COL_NAME + " TEXT, " +
                Student.COL_YEAR + " TEXT, " +
                Student.COL_NET_WORTH + " BIGINT )"
        );

        db.execSQL(CSClass.CREATE_TABLE);

    }

    public void initialize() {

        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();

        ArrayList<CSClass> classes = CSClass.getAllClasses();
        for (CSClass csClass : classes) {
            database.insert(CSClass.TABLE_NAME, null, csClass.getContentValues());
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + Student.TABLE_NAME);
        db.execSQL("DROP TABLE " + CSClass.TABLE_NAME);
        onCreate(db);
    }

    public String getCursorString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public long getCursorLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public int getCursorInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public void getCSClassForStudents() {
        String sql = "SELECT " +
                Student.TABLE_NAME + "." + Student.COL_NAME + ", " + CSClass.TABLE_NAME + ".* " +
                "FROM " + CSClass.TABLE_NAME + " INNER JOIN " + Student.TABLE_NAME +
                " ON " + CSClass.TABLE_NAME + "." + CSClass.COL_YEAR + " LIKE " + Student.TABLE_NAME + "." + Student.COL_YEAR;

        Log.d("getCSClassForStudents", sql);
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()) {
            String builder = getCursorString(cursor, Student.COL_NAME) +
                    " goes to " +
                    getCursorString(cursor, CSClass.COL_YEAR) +
                    " : " +
                    getCursorString(cursor, CSClass.COL_NAME);
            Log.d("getCSClassForStudents", builder);
        }

        cursor.close();


    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();


        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + Student.TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            do {
                int id = getCursorInt(cursor, Student._ID);
                String name = getCursorString(cursor, Student.COL_NAME);
                double netWorth = getCursorLong(cursor, Student.COL_NET_WORTH);
                String year = getCursorString(cursor, Student.COL_YEAR);
                students.add(new Student(id, name, year, netWorth));
            } while (cursor.moveToNext());

        }

        cursor.close();
        return students;
    }

    public void insertStudent(Student student) {
        getWritableDatabase().insert(Student.TABLE_NAME, null, student.getContentValues());
    }
}
