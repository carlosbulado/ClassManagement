package com.androidclass.carlos.classmanagement;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidclass.carlos.classmanagement.Adapters.StudentListAdapter;
import com.androidclass.carlos.classmanagement.Adapters.SubjectListAdapter;
import com.androidclass.carlos.classmanagement.Domain.Student;
import com.androidclass.carlos.classmanagement.Domain.Subject;
import com.androidclass.carlos.classmanagement.Utils.NavigateUtil;
import com.androidclass.carlos.classmanagement.Utils.ServiceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddSubjectActivity extends SessionAppActivity
{
    @BindView(R.id.ada_name)
    EditText subjectName;
    @BindView(R.id.ada_avg_marks)
    TextView avgMarks;

    int subId;

    @BindView(R.id.ada_students)
    RecyclerView list;

    private ArrayList<Student> students = new ArrayList<>();
    private StudentListAdapter adapter;

    private Subject subject = new Subject();

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        ButterKnife.bind(this);

        Intent previousPage = getIntent();
        Bundle mBundle = previousPage.getExtras();
        subId = mBundle.getInt("subjectId");
        if(subId > 0) subject = ServiceUtils.subjectService.getRepository().getById(subId);

        subjectName.setText(subject.getName());
        avgMarks.setText(String.format("%.2f%%", subject.getAvgStudentsMarksPercent()));

        // Fetch all Subjects
        students = subject.getStudents();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(linearLayoutManager);
        list.addItemDecoration(new DividerItemDecoration(this, GridLayout.VERTICAL));
        adapter = new StudentListAdapter(this, students);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_add_subject_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.masa_addStudent:
                students.add(new Student());
                break;
            case R.id.masa_saveSubject:
                Subject sub = new Subject();
                sub.setId(subId);
                sub.setName(subjectName.getText().toString());
                sub.setProfessor(loggedUser);

                for (Student st : students)
                {
                    st.setName(st.getTxtName().getText().toString());
                    st.setTotalMarks(Integer.parseInt(st.getTxtMarks().getText().toString()));
                    Student saved = ServiceUtils.studentService.save(st);
                    st.setId(saved.getId());
                }

                sub.setStudents(students);
                ServiceUtils.subjectService.save(sub);

                NavigateUtil.goTo(this, ListClassesActivity.class);
                break;
            case R.id.masa_back:
                NavigateUtil.goTo(this, ListClassesActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
