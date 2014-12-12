package com.mohheader.wally.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.mohheader.wally.R;
import com.mohheader.wally.models.Comment;
import com.mohheader.wally.models.DatabaseHelper;
import com.mohheader.wally.models.Post;

import org.joda.time.DateTime;

import java.util.Collection;

/**
 * Created by thedreamer on 12/12/14.
 */
public class PostView extends LinearLayout {
    TextView postText;
    LinearLayout commentsContainer;
    Button addComment;
    EditText commentInput;

    Post post;

    RuntimeExceptionDao<Comment,Integer> commentDao;
    RuntimeExceptionDao<Post,Integer> postDao;

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

        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        commentDao = dbHelper.getCommentDao();
        postDao = dbHelper.getPostDao();
        loadViews();
    }

    private void loadViews() {
        this.postText = (TextView) findViewById(R.id.post_text);
        this.commentsContainer = (LinearLayout) findViewById(R.id.comments_container);
        this.addComment = (Button) findViewById(R.id.add_comment);
        this.commentInput = (EditText) findViewById(R.id.comment_input);

        this.addComment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = commentInput.getText().toString();
                commentInput.setText("");
                Comment comment = new Comment();
                comment.setPost(post);
                comment.setText(text);
                comment.setUserName("Ahmed"); //TODO: Make the User Login/Logout !
                comment.setTimestamp(new DateTime());
                commentDao.create(comment);
                addComment(comment);
            }
        });

    }
    public void setPost(Post post){
        this.post = post;
        postText.setText(post.getText());
        postDao.refresh(post);
        Collection<Comment> comments =  post.getComments();
        for(Comment comment : comments){
            addComment(comment);
        }
    }

    private void addComment(Comment comment){
        CommentView cv = new CommentView(getContext());
        cv.setComment(comment);
        commentsContainer.addView(cv);
    }


}
