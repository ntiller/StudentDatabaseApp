package edu.lclark.studentdatabaseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements AddStudentDialogFragment.StudentCreatedListener {

    @Bind(R.id.activity_main_recyclerview)
    RecyclerView mRecyclerView;

    private StudentAdapter mStudentAdapter;
    private StudentSQLiteHelper mStudentSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mStudentSQLiteHelper = StudentSQLiteHelper.getInstance(getApplicationContext());

        mStudentAdapter = new StudentAdapter(mStudentSQLiteHelper.getAllStudents());
        mRecyclerView.setAdapter(mStudentAdapter);

        mStudentSQLiteHelper.initialize();

    }

    @OnClick(R.id.activity_main_fab)
    public void onFabClick() {
        AddStudentDialogFragment fragment = new AddStudentDialogFragment();
        fragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onStudentCreated(Student student) {
        Log.d(getClass().getSimpleName(), "Created -- " + student.toString());
        mStudentSQLiteHelper.insertStudent(student);
        mStudentAdapter.setStudents(mStudentSQLiteHelper.getAllStudents());
        mStudentSQLiteHelper.getCSClassForStudents();
    }
}
