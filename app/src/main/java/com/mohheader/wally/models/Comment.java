package com.mohheader.wally.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by thedreamer on 12/12/14.
 */

@DatabaseTable(tableName = "comments")
public class Comment {
    @DatabaseField(generatedId = true, canBeNull = false,unique=true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String user_name;// in real App it should be int user_id

    @DatabaseField(canBeNull = false)
    private String text;

    @DatabaseField(dataType = DataType.DATE_TIME)
    private Date timestamp;


    @DatabaseField(foreign = true, foreignAutoRefresh=true, readOnly = true)
    private Post post;

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
