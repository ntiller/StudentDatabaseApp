package edu.lclark.studentdatabaseapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ntille on 3/8/16.
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> mStudents;


    public StudentAdapter(ArrayList<Student> students) {
        mStudents = students;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_student, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student student = mStudents.get(position);
        holder.mIdTextView.setText(String.valueOf(student.getIdNumber()));
        holder.mNameTextView.setText(student.getName());
        holder.mNetworthTextView.setText(String.valueOf(student.getNetWorth()));
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public void addStudent(Student student) {
        mStudents.add(student);
        notifyItemInserted(mStudents.size()-1);
    }

    public void setStudents(ArrayList<Student> students) {
        mStudents = students;
        notifyDataSetChanged();
    }
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.row_student_id_textview)
        TextView mIdTextView;
        @Bind(R.id.row_student_name_textview)
        TextView mNameTextView;
        @Bind(R.id.row_student_networth_textview)
        TextView mNetworthTextView;

        public StudentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
