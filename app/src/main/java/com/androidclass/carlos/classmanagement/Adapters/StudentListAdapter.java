package com.androidclass.carlos.classmanagement.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidclass.carlos.classmanagement.Domain.Student;
import com.androidclass.carlos.classmanagement.R;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>
{
    public final class StudentViewHolder extends RecyclerView.ViewHolder
    {
        EditText txtSubName;
        EditText txtMarks;

        public StudentViewHolder (@NonNull View holder)
        {
            super(holder);

            txtSubName = itemView.findViewById(R.id.itst_name);
            txtMarks = itemView.findViewById(R.id.itst_marks);
        }
    }

    private ArrayList<Student> studentArrayList;
    private Context context;

    public StudentListAdapter (Context context, ArrayList<Student> subs)
    {
        this.studentArrayList = subs;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentListAdapter.StudentViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(this.context)
                .inflate(R.layout.item_students, viewGroup, false);

        StudentListAdapter.StudentViewHolder mVH = new StudentListAdapter.StudentViewHolder(itemView);
        return mVH;
    }

    @Override
    public void onBindViewHolder (@NonNull StudentViewHolder studentViewHolder, int position)
    {
        Student st = this.studentArrayList.get(position);
        studentViewHolder.itemView.setTag(st);
        studentViewHolder.txtSubName.setText(st.getName());
        st.setTxtName(studentViewHolder.txtSubName);
        studentViewHolder.txtMarks.setText(String.valueOf(st.getTotalMarks()));
        st.setTxtMarks(studentViewHolder.txtMarks);
    }

    @Override
    public int getItemCount ()
    {
        return this.studentArrayList.size();
    }

}
