package com.oss6team.emptylecturefinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button seoul_btn = (Button) findViewById(R.id.seoul_btn);
        seoul_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Seoul.class);
                startActivity(intent);
            }
        });

        Button global_btn = (Button) findViewById(R.id.global_btn);
        global_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Global.class);
                startActivity(intent);
            }
        });

    }
}