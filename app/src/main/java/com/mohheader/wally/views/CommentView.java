package com.mohheader.wally.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohheader.wally.R;
import com.mohheader.wally.models.Comment;

/**
 * Created by thedreamer on 12/12/14.
 */
public class CommentView extends LinearLayout {
    TextView commentText;
    public CommentView(Context context) {
        this(context, null);
    }

    public CommentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        inflater.inflate(R.layout.comment_view, this);
        loadViews();
    }

    private void loadViews() {
        this.commentText = (TextView) findViewById(R.id.comment_text);
    }

    public void setComment(Comment comment) {
        commentText.setText(comment.getText());
    }
}
