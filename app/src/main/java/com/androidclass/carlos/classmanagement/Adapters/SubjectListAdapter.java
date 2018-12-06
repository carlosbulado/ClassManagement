package com.androidclass.carlos.classmanagement.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidclass.carlos.classmanagement.Domain.Subject;
import com.androidclass.carlos.classmanagement.R;

import java.util.ArrayList;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder>
{
    public final class SubjectViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtSubName;
        TextView txtQuantStu;
        TextView txtAvgMarks;

        public SubjectViewHolder (@NonNull View holder)
        {
            super(holder);

            txtSubName = itemView.findViewById(R.id.is_subjectname);
            txtQuantStu = itemView.findViewById(R.id.is_quantStudents);
            txtAvgMarks = itemView.findViewById(R.id.is_avgMarks);
        }
    }

    private ArrayList<Subject> subjectArrayList;
    private Context context;

    public SubjectListAdapter (Context context, ArrayList<Subject> subs)
    {
        this.subjectArrayList = subs;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(this.context)
                .inflate(R.layout.item_subject, viewGroup, false);

        SubjectViewHolder mVH = new SubjectViewHolder(itemView);
        return mVH;
    }

    @Override
    public void onBindViewHolder (@NonNull SubjectViewHolder subjectViewHolder, int position)
    {
        Subject sub = this.subjectArrayList.get(position);
        subjectViewHolder.txtSubName.setText("Subject name: " + sub.getName());
        subjectViewHolder.txtQuantStu.setText("Quantity of students: " + sub.getStudentsQuantity());
        subjectViewHolder.txtAvgMarks.setText("Avg marks of students: " + String.valueOf(sub.getAvgStudentsMarksPercent()) + "%");
    }

    @Override
    public int getItemCount ()
    {
        return this.subjectArrayList.size();
    }

}
