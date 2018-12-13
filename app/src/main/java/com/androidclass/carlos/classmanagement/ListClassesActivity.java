package com.androidclass.carlos.classmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.GridLayout;
import android.widget.Toast;

import com.androidclass.carlos.classmanagement.Adapters.SubjectListAdapter;
import com.androidclass.carlos.classmanagement.Domain.Student;
import com.androidclass.carlos.classmanagement.Domain.Subject;
import com.androidclass.carlos.classmanagement.Utils.NavigateUtil;
import com.androidclass.carlos.classmanagement.Utils.ServiceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListClassesActivity extends SessionAppActivity
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
        subjects = ServiceUtils.subjectService.getAllByUserId(this.getLoggedUser().getId());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(linearLayoutManager);
        list.addItemDecoration(new DividerItemDecoration(this, GridLayout.VERTICAL));
        adapter = new SubjectListAdapter(this, subjects);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_list_classes_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_lca_add:
                Intent nextPage = new Intent(this, AddSubjectActivity.class);
                nextPage.putExtra("subjectId", 0);
                this.startActivity(nextPage);
                this.finish();
                break;
            case R.id.menu_lca_logut:
                SharedPreferences myPref = getSharedPreferences("userDetails", MODE_PRIVATE);
                myPref.edit().remove("UserId").commit();
                NavigateUtil.goTo(this, MainActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
