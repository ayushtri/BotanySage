package com.celes.botanysage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.celes.botanysage.mlModelClasses.FlowerModel;
import com.celes.botanysage.mlModelClasses.FruitVegModel;
import com.celes.botanysage.redditSubs.BotanyMemes;
import com.celes.botanysage.redditSubs.BotanyNormal;


public class MainActivity extends AppCompatActivity {
    Button postBtn, memeBtn;
    Button floBtn, fruVegBtn;
    //git check
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Home");

        postBtn = findViewById(R.id.postsBtn);
        memeBtn = findViewById(R.id.memesBtn);

        floBtn = findViewById(R.id.flowerBtn);
        fruVegBtn = findViewById(R.id.fruVegBtn);


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BotanyNormal.class);
                startActivity(intent);
            }
        });

        memeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BotanyMemes.class);
                startActivity(intent);
            }
        });

        floBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FlowerModel.class);
                startActivity(intent);
            }
        });

        fruVegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FruitVegModel.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.side_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.about_us){
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}