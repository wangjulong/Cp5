package com.wangjulong.cp5.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * 数据库帮助类，创建、更新数据库
 * Created by Administrator on 2016/9/21.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    // 数据库文件名
    private static final String DATABASE_NAME = "lotteries.db";
    // 数据库版本号
    private static final int DATABASE_VERSION = 1;

    // 访问数据表(Lottery)的 DAO 对象
    private Dao<Lottery, Integer> lotteryIntegerDao = null;
    private RuntimeExceptionDao<Lottery, Integer> lotteryRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created.
     * Usually you should call createTable statements here to create the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            // 数据库创建
            TableUtils.createTableIfNotExists(connectionSource, Lottery.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number.
     * This allows you to adjust the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Lottery.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得数据库的访问对象
     * Returns the Database Access Object (DAO) for our Lottery class.
     */
    public Dao<Lottery, Integer> getDao() throws SQLException {
        if (lotteryIntegerDao == null) {
            lotteryIntegerDao = getDao(Lottery.class);
        }
        return lotteryIntegerDao;
    }

    /**
     * 获得数据库的访问对象
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Lottery class.
     * RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Lottery, Integer> getLotteryRuntimeDao() {
        if (lotteryRuntimeDao == null) {
            lotteryRuntimeDao = getRuntimeExceptionDao(Lottery.class);
        }
        return lotteryRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        lotteryIntegerDao = null;
        lotteryRuntimeDao = null;
    }
}
