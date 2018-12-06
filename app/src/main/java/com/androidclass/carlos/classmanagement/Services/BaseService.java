package com.androidclass.carlos.classmanagement.Services;

import android.content.Context;

import com.androidclass.carlos.classmanagement.Domain.Entity;
import com.androidclass.carlos.classmanagement.Repositories.BaseRepository;

public abstract class BaseService<T extends Entity>
{
    private Context context;
    private BaseRepository<T> repository;

    public BaseService (Context context, BaseRepository repo)
    {
        this.context = context;
        this.repository = repo;
    }

    public Context getContext ()
    {
        return context;
    }

    public BaseRepository<T> getRepository ()
    {
        return repository;
    }

    public void setRepository (BaseRepository<T> repository)
    {
        this.repository = repository;
    }

    public void save(T obj)
    {
        if (obj.getId() > 0)
            this.getRepository().update(obj);
        else
            this.getRepository().insert(obj);
    }
}
