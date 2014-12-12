package com.mohheader.wally.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mohheader.wally.R;
import com.mohheader.wally.models.Post;
import com.mohheader.wally.views.PostView;

import java.util.List;

/**
 * Created by thedreamer on 12/12/14.
 */
public class PostsAdapter extends ArrayAdapter<Post> {
    Context context;
    List<Post> posts;
    public PostsAdapter(Context context, List<Post> posts){
        super(context, R.layout.item_post, posts);
        this.context = context;
        this.posts = posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_post, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.view = (PostView) convertView.findViewById(R.id.item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Post post = posts.get(position);
        viewHolder.view.setPost(post);
        return convertView;
    }

    static class ViewHolder {
        PostView view;
    }
}
