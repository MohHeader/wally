package com.mohheader.wally.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mohheader.wally.R;

import java.sql.SQLException;

/**
 * Created by thedreamer on 12/12/14.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "wally.db";
    public static final int DATABASE_VERSION = 6;


    private RuntimeExceptionDao<Post,Integer> postDao = null;
    private RuntimeExceptionDao<Comment,Integer> commentDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Post.class);
            TableUtils.createTable(connectionSource, Comment.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //TODO : Make Real Upgrade Schema
        try {
            TableUtils.dropTable(connectionSource,Post.class,true);
            TableUtils.dropTable(connectionSource,Comment.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RuntimeExceptionDao<Post,Integer> getPostDao () {
        if(postDao == null){
            postDao = getRuntimeExceptionDao(Post.class);
        }
        return postDao;
    }

    public RuntimeExceptionDao<Comment,Integer> getCommentDao () {
        if(commentDao == null){
            commentDao = getRuntimeExceptionDao(Comment.class);
        }
        return commentDao;
    }

}