package com.oss6team.emptylecturefinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.professionalandrioid.apps.timeapp.R;

public class Seoul extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seoul);

        Button seoul_town_btn = (Button) findViewById(R.id.seoul_town_btn);
        seoul_town_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(Seoul.this, Seoul_town.class);
                startActivity(intent);
            }
        });

        //서울 시간표로 이동
        Button seoul_time_btn = (Button) findViewById(R.id.seoul_time_btn);
        seoul_time_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(Seoul.this, Global.class);
                startActivity(intent);
            }
        });
    }
}
