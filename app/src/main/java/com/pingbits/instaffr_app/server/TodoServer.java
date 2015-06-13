package com.pingbits.instaffr_app.server;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pingbits.greendao.Todo;
import com.pingbits.instaffr_app.BuildConfig;
import com.pingbits.instaffr_app.DbUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TodoServer implements Callback<List<TodoItem>> {

    private static TodoServer instance = null;
    private final Context context;
    private TodoService service;

    private SharedPreferences prefs;

    private TodoCallback callback = null;

    public TodoServer(Context context) {
        this.context = context;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BuildConfig.SERVERURL)
                .build();
        service = restAdapter.create(TodoService.class);
        prefs = context.getSharedPreferences("com.pingbits.instaffr", Context.MODE_PRIVATE);
    }

    public static TodoServer getInstance(Context context) {
        if (instance == null) {
            instance = new TodoServer(context);
        }
        return instance;
    }

    public void getLatestTodos(TodoCallback cb) {
        callback = cb;
        String name = BuildConfig.CONNECTIONSET.equals("0") ? "Jaydeep": "Parth";
        service.getTodos(name, this);
    }

    public void postTodo(String sendTo, String title) {
        String addedBy = BuildConfig.CONNECTIONSET.equals("0") ? "Jaydeep" : "Parth";
        service.postTodo(sendTo, title, addedBy, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d("SUCCESS", "asdf");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("FAILURE", error.toString());
            }
        });
    }

    @Override
    public void success(List<TodoItem> todoItems, Response response) {
        long lastId = prefs.getLong("lastId", 0);
        ArrayList<Todo> todos = new ArrayList<>();
        for (int i = 0; i < todoItems.size(); ++i) {
            TodoItem item = todoItems.get(i);
            if (item._id > lastId) {
                todos.add(new Todo(null, item.title, item.addedBy, false));
                lastId = item._id;
            }
        }
        callback.newTodos(todos);
        DbUtils.mTodoDao.insertInTx(todos);
        prefs.edit().putLong("lastId", lastId).commit();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e("FAILURE", error.toString());
    }

    public interface TodoCallback {
        void newTodos(List<Todo> todos);
    }
}
