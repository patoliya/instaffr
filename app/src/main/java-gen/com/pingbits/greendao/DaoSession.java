package com.pingbits.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.pingbits.greendao.Connection;
import com.pingbits.greendao.Todo;

import com.pingbits.greendao.ConnectionDao;
import com.pingbits.greendao.TodoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig connectionDaoConfig;
    private final DaoConfig todoDaoConfig;

    private final ConnectionDao connectionDao;
    private final TodoDao todoDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        connectionDaoConfig = daoConfigMap.get(ConnectionDao.class).clone();
        connectionDaoConfig.initIdentityScope(type);

        todoDaoConfig = daoConfigMap.get(TodoDao.class).clone();
        todoDaoConfig.initIdentityScope(type);

        connectionDao = new ConnectionDao(connectionDaoConfig, this);
        todoDao = new TodoDao(todoDaoConfig, this);

        registerDao(Connection.class, connectionDao);
        registerDao(Todo.class, todoDao);
    }
    
    public void clear() {
        connectionDaoConfig.getIdentityScope().clear();
        todoDaoConfig.getIdentityScope().clear();
    }

    public ConnectionDao getConnectionDao() {
        return connectionDao;
    }

    public TodoDao getTodoDao() {
        return todoDao;
    }

}
