package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add extends AppCompatActivity {
    NumberPicker NUM1,NUM2;
    CheckBox repeat;
    TextView saveBtn,A1TV,A2TV,A3TV,OnTV,OffTV;
    ImageView back;
    int select1=-1,select2=-1,select3=-1;
    int onoff=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference l_hour = database.getReference("l_hour");
        DatabaseReference k_hour = database.getReference("k_hour");
        DatabaseReference b_hour = database.getReference("b_hour");
        DatabaseReference l_min = database.getReference("l_min");
        DatabaseReference k_min = database.getReference("k_min");
        DatabaseReference b_min = database.getReference("b_min");

        DatabaseReference l_shour = database.getReference("l_shour");
        DatabaseReference k_shour = database.getReference("k_shour");
        DatabaseReference b_shour = database.getReference("b_shour");
        DatabaseReference l_smin = database.getReference("l_smin");
        DatabaseReference k_smin = database.getReference("k_smin");
        DatabaseReference b_smin = database.getReference("b_smin");

        NUM1=(NumberPicker) findViewById(R.id.Num1);
        NUM2=(NumberPicker) findViewById(R.id.Num2);
        NUM1.setMinValue(0);
        NUM2.setMinValue(0);
        NUM1.setMaxValue(23);
        NUM2.setMaxValue(59);

        saveBtn=(TextView) findViewById(R.id.saveBtn);
        A1TV=(TextView)findViewById(R.id.A1TV);
        A2TV=(TextView)findViewById(R.id.A2TV);
        A3TV=(TextView)findViewById(R.id.A3TV);
        OnTV=(TextView)findViewById(R.id.OnTV);
        OffTV=(TextView)findViewById(R.id.OffTV);
        back=(ImageView) findViewById(R.id.back);
        OnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onoff=1;
                OnTV.setBackgroundColor(Color.parseColor("#88EA8C"));
                OffTV.setBackgroundColor(Color.parseColor("#54120A0A"));
            }
        });
        OffTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onoff=2;
                OffTV.setBackgroundColor(Color.parseColor("#88EA8C"));
                OnTV.setBackgroundColor(Color.parseColor("#54120A0A"));
            }
        });
        A1TV.setOnClickListener(new View.OnClickListener() {
            boolean tt1=false;
            @Override
            public void onClick(View v) {
                tt1=!tt1;
                if(tt1==true){
                    select1=1;
                    A1TV.setBackgroundColor(Color.parseColor("#88EA8C"));
                }else {
                    select1=-1;
                    A1TV.setBackgroundColor(Color.parseColor("#A15A5252"));
                }

//
//                A2TV.setBackgroundColor(Color.parseColor("#54120A0A"));
//                A3TV.setBackgroundColor(Color.parseColor("#54120A0A"));
            }
        });
        A2TV.setOnClickListener(new View.OnClickListener() {
            boolean tt2=false;
            @Override
            public void onClick(View v) {
                tt2=!tt2;
                if(tt2==true){
                    select2=1;
                    A2TV.setBackgroundColor(Color.parseColor("#88EA8C"));
                }else {
                    select2=-1;
                    A2TV.setBackgroundColor(Color.parseColor("#54120A0A"));
                }

//                A1TV.setBackgroundColor(Color.parseColor("#54120A0A"));
//                A3TV.setBackgroundColor(Color.parseColor("#54120A0A"));
            }
        });
        A3TV.setOnClickListener(new View.OnClickListener() {
            boolean tt3=false;
            @Override
            public void onClick(View v) {
                tt3=!tt3;
                if(tt3==true){
                    select3=1;
                    A3TV.setBackgroundColor(Color.parseColor("#88EA8C"));
                }else {
                    select3=-1;
                    A3TV.setBackgroundColor(Color.parseColor("#54120A0A"));
                }

//
//                A1TV.setBackgroundColor(Color.parseColor("#54120A0A"));
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h=NUM1.getValue();
                int m=NUM2.getValue();
                if(onoff==1){
                    if(select1==1){
                        l_hour.setValue(h);
                        l_min.setValue(m);
                    }
                    if(select2==1){
                        k_hour.setValue(h);
                        k_min.setValue(m);
                    }
                    if(select3==1){
                        b_hour.setValue(h);
                        b_min.setValue(m);
                    }
                }else if(onoff==2){
                    if(select1==1){
                        l_shour.setValue(h);
                        l_smin.setValue(m);
                    }
                    if(select2==1){
                        k_shour.setValue(h);
                        k_smin.setValue(m);
                    }
                    if(select3==1){
                        b_shour.setValue(h);
                        b_smin.setValue(m);
                    }
                }
                startActivity(new Intent(getApplicationContext(), THG.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), THG.class));
            }
        });

    }
}