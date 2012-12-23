package com.socmodder.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 12/9/12
 * Time: 2:42 PM
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //Name of the database
    private static final String DATABASE_NAME = "database.db";
    //any time changes are made to database objects, database version should be incremented
    private static final int DATABASE_VERSION = 1;

    private static Dao<Sign, Integer> signDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Sign.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<Sign, Integer> getDao() throws SQLException{
        if(signDao == null){
            signDao = getDao(Sign.class);
        }
        return signDao;
    }

    @Override
    public void close(){
        super.close();
    }
}
