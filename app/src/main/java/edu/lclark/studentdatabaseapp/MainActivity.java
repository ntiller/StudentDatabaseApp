package edu.lclark.studentdatabaseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_recyclerview)
    RecyclerView mRecyclerView;

    private StudentAdapter mStudentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        StudentSQLiteHelper studentSQLiteHelper = StudentSQLiteHelper.getInstance(getApplicationContext());
        studentSQLiteHelper.insertStudent(new Student("Nick", 4444, "Super Senior", 9));
        studentSQLiteHelper.insertStudent(new Student("Bill", 763254, "Freshman", 9000));
        studentSQLiteHelper.insertStudent(new Student("Tom", 233222, "Junior", 33));

        mStudentAdapter = new StudentAdapter(studentSQLiteHelper.getAllStudents());
        mRecyclerView.setAdapter(mStudentAdapter);



    }
}
