package com.oss6team.emptylecturefinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.professionalandrioid.apps.timeapp.R;

public class Global extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.global);

        Button global_town_btn = (Button) findViewById(R.id.global_town_btn);
        global_town_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(Global.this, Global_town.class);
                startActivity(intent);
            }
        });

        //글로벌 시간표로 이동
        Button global_time_btn = (Button) findViewById(R.id.global_time_btn);
        global_time_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(Global.this, Global.class);
                startActivity(intent);
            }
        });
    }
}
