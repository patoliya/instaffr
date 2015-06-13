package com.pingbits.instaffr_app;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pingbits.greendao.ConnectionDao;
import com.pingbits.greendao.DaoMaster;
import com.pingbits.greendao.DaoSession;
import com.pingbits.greendao.Todo;
import com.pingbits.greendao.TodoDao;

import java.util.List;

public class DbUtils {

    public static DaoSession mDaoSession = null;

    public static ConnectionDao mConnectionDao = null;

    public static TodoDao mTodoDao = null;

    public static void initialize(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "mydb", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        mConnectionDao = mDaoSession.getConnectionDao();
        mTodoDao = mDaoSession.getTodoDao();
    }

    public static List<Todo> getAllTodos() {
        return mTodoDao.loadAll();
    }
}
