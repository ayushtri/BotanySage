package com.celes.botanysage.helpers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.celes.botanysage.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RedditLoaderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_loader);

        recyclerView = findViewById(R.id.recyleCards);
    }
    protected void loadApi(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("memes");
                            ArrayList<RedditModelClass> redditPosts = new ArrayList<>();
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String imgUrl = jsonObject.getString("url");
                                String title = jsonObject.getString("title");
                                String user = "Posted By: " + jsonObject.getString("author");
                                String postUrl = jsonObject.getString("postLink");

                                redditPosts.add(new RedditModelClass(imgUrl,title,user,postUrl));
                            }
                            loadRecycleView(redditPosts);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(RedditLoaderActivity.this, "ummm", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }
    private void loadRecycleView(ArrayList<RedditModelClass> redditPosts){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecycleViewAdapterReddit adapterReddit = new RecycleViewAdapterReddit(this, redditPosts);
        recyclerView.setAdapter(adapterReddit);
    }
}