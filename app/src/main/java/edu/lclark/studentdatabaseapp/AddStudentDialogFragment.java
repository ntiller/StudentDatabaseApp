package edu.lclark.studentdatabaseapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ntille on 3/10/16.
 */
public class AddStudentDialogFragment extends DialogFragment {

    @Bind(R.id.fragment_add_student_name_edittext)
    EditText mNameEditText;
    @Bind(R.id.fragment_add_student_year_radiogroup)
    RadioGroup mRadioGroup;
    @Bind(R.id.fragment_add_student_networth_edittext)
    EditText mNetWorthEditText;

    public interface StudentCreatedListener {
        void onStudentCreated(Student student);
    }

    private StudentCreatedListener mListener;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mListener = (StudentCreatedListener) getActivity();

        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_student, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(rootView)
                .setTitle(getActivity().getString(R.string.new_student))
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = mNameEditText.getText().toString().trim();
                                String networth = mNetWorthEditText.getText().toString().trim();
                                String year = Student.FRESHMAN;
                                int id = mRadioGroup.getCheckedRadioButtonId();
                                switch (id) {
                                    case R.id.fragment_add_student_freshman:
                                        year = Student.FRESHMAN;
                                        break;
                                    case R.id.fragment_add_student_sophomore:
                                        year = Student.SOPHOMORE;
                                        break;
                                    case R.id.fragment_add_student_junior:
                                        year = Student.JUNIOR;
                                        break;
                                    case R.id.fragment_add_student_senior:
                                        year = Student.SENIOR;
                                        break;
                                }

                                Student student = new Student(name, year, Double.valueOf(networth));
                                mListener.onStudentCreated(student);

                            }
                        })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        ButterKnife.bind(this, rootView);

        return dialog;
    }
}
