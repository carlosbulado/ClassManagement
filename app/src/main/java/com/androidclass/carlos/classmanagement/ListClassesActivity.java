package com.androidclass.carlos.classmanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.GridLayout;

import com.androidclass.carlos.classmanagement.Adapters.SubjectListAdapter;
import com.androidclass.carlos.classmanagement.Domain.Student;
import com.androidclass.carlos.classmanagement.Domain.Subject;
import com.androidclass.carlos.classmanagement.Utils.ServiceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListClassesActivity extends AppCompatActivity
{
    @BindView(R.id.lca_list)
    RecyclerView list;

    private ArrayList<Subject> subjects = new ArrayList<>();
    private SubjectListAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_classes);
        ButterKnife.bind(this);

        // Fetch all Subjects
        subjects = ServiceUtils.subjectService.getAllByUserId(1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(linearLayoutManager);
        list.addItemDecoration(new DividerItemDecoration(this, GridLayout.VERTICAL));
        adapter = new SubjectListAdapter(this, subjects);
        list.setAdapter(adapter);

        list.addOnItemTouchListener(new RecyclerView.OnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent (@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                return false;
            }

            @Override
            public void onTouchEvent (@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent (boolean b)
            {

            }
        });
    }
}
