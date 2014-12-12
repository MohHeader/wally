package com.mohheader.wally;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.mohheader.wally.adapters.PostsAdapter;
import com.mohheader.wally.models.DatabaseHelper;
import com.mohheader.wally.models.Post;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;


public class WallActivity extends ListActivity {
    List<Post> posts;
    RuntimeExceptionDao<Post,Integer> postDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup addPostView = (ViewGroup)inflater.inflate(R.layout.add_post_view, null);
        getListView().addHeaderView(addPostView);

        DatabaseHelper dbHelper = OpenHelperManager.getHelper(getApplicationContext(), DatabaseHelper.class);
        postDao = dbHelper.getPostDao();
        posts = postDao.queryForAll();
        setListAdapter(new PostsAdapter(this,posts));

        addPostView.findViewById(R.id.add_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ((EditText)addPostView.findViewById(R.id.post_input)).getText().toString();
                Post post = new Post();
                post.setText(text);
                post.setUserName("Ahmed"); //TODO: Make the User Login/Logout !
                post.setTimestamp(new DateTime());
                postDao.create(post);
                posts.add(post);
                getListView().invalidateViews();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
