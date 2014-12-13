package com.mohheader.wally.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.mohheader.wally.R;
import com.mohheader.wally.models.Comment;
import com.mohheader.wally.models.DatabaseHelper;
import com.mohheader.wally.models.Post;
import com.mohheader.wally.models.User;

import org.joda.time.DateTime;

import java.util.Collection;

/**
 * Created by thedreamer on 12/12/14.
 */
public class PostView extends LinearLayout {
    TextView postText, name;
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
        this.name = (TextView) findViewById(R.id.name);
        this.commentsContainer = (LinearLayout) findViewById(R.id.comments_container);
        this.commentInput = (EditText) findViewById(R.id.comment_input);

        this.commentInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_NULL
                        && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    addCommentToPost(commentInput.getText().toString());
                    return true;
                }
                return false;
            }
        });

//        this.commentInput.setOnKeyListener(new OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                // If the event is a key-down event on the "enter" button
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    addCommentToPost(commentInput.getText().toString());
//                    return true;
//                }
//                return true;
//            }
//        });
    }

    private void addCommentToPost(String text) {
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setText(text);
        comment.setUserName(User.getUserName(getContext()));
        comment.setTimestamp(new DateTime());
        commentDao.create(comment);
        addCommentToView(comment);
        commentInput.getText().clear();
    }

    public void setPost(Post _post){
        this.post = _post;
        name.setText(post.getUserName() + " wrote :");
        postText.setText(post.getText());
        postDao.refresh(post);
        Collection<Comment> comments =  post.getComments();
        for(Comment comment : comments){
            addCommentToView(comment);
        }
    }

    private void addCommentToView(Comment comment){
        CommentView cv = new CommentView(getContext());
        cv.setComment(comment);
        commentsContainer.addView(cv);
    }
}
