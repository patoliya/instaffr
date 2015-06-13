package com.pingbits.instaffr_app;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pingbits.greendao.Connection;
import com.pingbits.greendao.ConnectionDao;
import com.pingbits.greendao.DaoMaster;
import com.pingbits.greendao.DaoSession;
import com.pingbits.greendao.Todo;
import com.pingbits.greendao.TodoDao;

import java.util.ArrayList;
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

        seedConnections();
    }

    private static void seedConnections() {
        if (mConnectionDao.count() != 0)
            return;
        switch (BuildConfig.CONNECTIONSET) {
            case "0": {
                ArrayList<Connection> lst = new ArrayList<>(3);
                lst.add(new Connection(null, "Parth", "parthpatolia@gmail.com", R.drawable.parth));
                lst.add(new Connection(null, "Nidhi", "jainnidhi703@gmail.com", R.drawable.nidhi));
                lst.add(new Connection(null, "Kiran", "kiran1673@gmail.com", R.drawable.kiran));
                mConnectionDao.insertInTx(lst);
                break;
            }
            case "1": {
                ArrayList<Connection> lst = new ArrayList<>(3);
                lst.add(new Connection(null, "Jaydeep", "jaydp17@gmail.com", R.drawable.profile));
                lst.add(new Connection(null, "Nidhi", "jainnidhi703@gmail.com", R.drawable.nidhi));
                lst.add(new Connection(null, "Kiran", "kiran1673@gmail.com", R.drawable.kiran));
                mConnectionDao.insertInTx(lst);
                break;
            }
        }
    }

    public static List<Todo> getAllTodos() {
        return mTodoDao.loadAll();
    }
}
