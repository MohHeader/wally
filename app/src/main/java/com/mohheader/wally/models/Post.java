package com.mohheader.wally.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by thedreamer on 12/12/14.
 */
@DatabaseTable(tableName = "posts")
public class Post {

    public Post(){}
    @DatabaseField(generatedId = true, canBeNull = false,unique=true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String user_name;// in real App it should be int user_id

    @DatabaseField(canBeNull = false)
    private String text;

    @DatabaseField(dataType = DataType.DATE_TIME)
    private DateTime timestamp;

    @ForeignCollectionField
    Collection<Comment> comments = new ArrayList<Comment>();

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
    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Collection<Comment> getComments() {
        return comments;
    }
}