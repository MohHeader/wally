package com.mohheader.wally.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mohheader.wally.R;
import com.mohheader.wally.models.Post;

/**
 * Created by thedreamer on 12/12/14.
 */
public class PostView extends LinearLayout {
    TextView postText;
    public PostView(Context context) {
        this(context, null);
    }

    public PostView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        inflater.inflate(R.layout.post_view, this);
        loadViews();
    }

    private void loadViews() {
        this.postText = (TextView) findViewById(R.id.post_text);

    }
    public void setPost(Post post){
        postText.setText(post.getText());
    }
}
