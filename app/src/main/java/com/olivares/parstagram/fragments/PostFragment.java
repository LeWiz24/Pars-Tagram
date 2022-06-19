package com.olivares.parstagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olivares.parstagram.R;
import com.olivares.parstagram.adapters.PostsAdapter;
import com.olivares.parstagram.classes.EndlessRecyclerViewScrollListener;
import com.olivares.parstagram.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {

    public static final String TAG = "PostFragment";
    protected SwipeRefreshLayout kukujtgulegtufrbivevifbbviltnufbbhnjirhubgrhgrrkjcctcunenbittgrg;
    private PostsAdapter adapter;
    private List<Post> allPosts;
    RecyclerView rvPosts;
    SwipeRefreshLayout swipeRefreshLayout;
    private EndlessRecyclerViewScrollListener scrollListener;


    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPosts = view.findViewById(R.id.rvPosts);
        swipeRefreshLayout=view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clear();
                queryPosts();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Creating adapter
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Set adapter
        rvPosts.setAdapter(adapter);
        // Create layoutmanager
        rvPosts.setLayoutManager(layoutManager);

        scrollListener =new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadMore();
            }
        };

        // Set scrolllistener on rvPosts
        rvPosts.addOnScrollListener(scrollListener);
        queryPosts();

    }



    private void queryPosts() {
        // specify what type of data we want to query - Post.class
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        // limit query to latest 20 items
        query.setLimit(5);
        // order posts by creation date (newest first)
        query.addDescendingOrder("createdAt");
        // start an asynchronous call for posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                // check for errors
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                // for debugging purposes let's print every post description to logcat
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                // save received posts to list and notify adapter of new data
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void loadMore() {
        Post lastPost=allPosts.get(allPosts.size()-1);
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(2);
        query.orderByDescending(Post.KEY_CREATEDAT);
        query.whereLessThan(Post.KEY_CREATEDAT,lastPost.getCreatedAt());
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                swipeRefreshLayout.setRefreshing(false);
                if(e!=null)
                {
                    Log.i(TAG,"Issue with loading more post");
                    return;
                }
                for(Post post:posts)
                {
                    Log.i(TAG, "Post: "+post.getDescription());
                }
                addAll(posts);
            }
        });
    }
    public void addAll(List<Post> posts)
    {
        allPosts.addAll(posts);
        adapter.notifyDataSetChanged();
    }

    protected void clear()
    {
        allPosts.clear();
        adapter.notifyDataSetChanged();
    }
}