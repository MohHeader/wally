package com.mohheader.wally;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.mohheader.wally.adapters.PostsAdapter;
import com.mohheader.wally.helpers.KeyboardHelper;
import com.mohheader.wally.models.DatabaseHelper;
import com.mohheader.wally.models.Post;
import com.mohheader.wally.models.User;

import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class WallActivity extends Activity {
    List<Post> posts;
    RuntimeExceptionDao<Post,Integer> postDao;
    ListView listView;
    PostsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);

        listView = (ListView) findViewById(R.id.list_view);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup addPostView = (ViewGroup)inflater.inflate(R.layout.add_post_view, null);
        final EditText postInput = (EditText) addPostView.findViewById(R.id.post_input);
        listView.addHeaderView(addPostView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

        DatabaseHelper dbHelper = OpenHelperManager.getHelper(getApplicationContext(), DatabaseHelper.class);
        postDao = dbHelper.getPostDao();

        try {
            posts = postDao.queryBuilder().orderBy("id",false).query();
        } catch (SQLException e) {
            posts = postDao.queryForAll();
            e.printStackTrace();
        }
        adapter = new PostsAdapter(this,posts);
        listView.setAdapter(adapter);

        addPostView.findViewById(R.id.add_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = postInput.getText().toString();
                postInput.setText("");
                Post post = new Post(text, WallActivity.this);
                postDao.create(post);
                posts.add(0, post);

                adapter = null;
                adapter = new PostsAdapter(WallActivity.this,posts);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);

                KeyboardHelper.hideKeyboard(WallActivity.this);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            User.logout(this);

            Intent i = new Intent(WallActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
