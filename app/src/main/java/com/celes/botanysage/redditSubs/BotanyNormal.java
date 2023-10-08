package com.celes.botanysage.redditSubs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.celes.botanysage.R;
import com.celes.botanysage.helpers.RedditLoaderActivity;

public class BotanyNormal extends RedditLoaderActivity {
    String url = "https://meme-api.com/gimme/BotanicalPorn/10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadApi(url);

        setTitle("Botany Posts");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh_btn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.refresh_reddit){
            loadApi(url);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
