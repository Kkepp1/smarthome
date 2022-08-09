package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView text11,tt12,tt21,tt22,temp,temp1,hum,hum1,checkTv;
    CardView card11,card12,card21,card22,card31,card32;
    ImageView icon11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.m1);

        text11=(TextView) findViewById(R.id.text11);
        tt12= (TextView) findViewById(R.id.tt12);
        tt21= (TextView) findViewById(R.id.tt21);
        tt22= (TextView) findViewById(R.id.tt22);
        temp= (TextView) findViewById(R.id.temp);

        hum= (TextView) findViewById(R.id.hum);
        checkTv= (TextView)findViewById(R.id.checkTv);

        icon11=(ImageView) findViewById(R.id.icon11);

        card11 = (CardView) findViewById(R.id.card11);
        card12 = (CardView) findViewById(R.id.card12);
        card21 = (CardView) findViewById(R.id.card21);
        card22 = (CardView) findViewById(R.id.card22);

        // bottom navigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    //check id
                    case R.id.m1:
                        break;
                    case R.id.m2:
                        startActivity(new Intent(getApplicationContext(), THG.class));
                        finish();
                        overridePendingTransition(0, 0);
                }
                return true;
            }
        });
        //  firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference door = database.getReference("door");
        DatabaseReference bedroom = database.getReference("bedroom");
        DatabaseReference living = database.getReference("living");
        DatabaseReference kitchen = database.getReference("kitchen");
        DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
        // xu ly nut nhan
        card11.setOnClickListener(new View.OnClickListener() {
            Boolean tt1 = false;
            @Override
            public void onClick(View v) {
                tt1=!tt1;
                if (tt1==true){
                    door.setValue("1");
                }else {
                    door.setValue("0");
                }
            }
        });

        card12.setOnClickListener(new View.OnClickListener() {
            Boolean tt2=false;
            @Override
            public void onClick(View v) {
                tt2=!tt2;
                if(tt2==true){
                    living.setValue("1");
                    
                }else{
                    living.setValue("0");
                }
            }
        });

        card21.setOnClickListener(new View.OnClickListener() {
            Boolean tt3=false;
            @Override
            public void onClick(View v) {
                tt3=!tt3;
                if(tt3==true){
                    kitchen.setValue("1");
                }else{
                    kitchen.setValue("0");
                }
            }
        });
        card22.setOnClickListener(new View.OnClickListener() {
            Boolean tt4=false;
            @Override
            public void onClick(View v) {
                tt4=!tt4;
                if(tt4==true){
                    bedroom.setValue("1");
                }else{
                    bedroom.setValue("0");
                }
            }
        });
// read data from firebase
        // living
        myData.child("living").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString().equals("1")){
                    tt12.setText("loading");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card12.setCardBackgroundColor(Color.parseColor("#88EA8C"));
                            tt12.setText("On");
                        }
                    }, 2000);
                }else if(snapshot.getValue().toString().equals("0")){
                    tt12.setText("loading");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card12.setCardBackgroundColor(Color.parseColor("#A15A5252"));
                            tt12.setText("Off");
                        }
                    }, 2000);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // kitchen
        myData.child("kitchen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString().equals("1")){
                    tt21.setText("loading");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card21.setCardBackgroundColor(Color.parseColor("#88EA8C"));
                            tt21.setText("On");
                        }
                    }, 2000);

                }else if(snapshot.getValue().toString().equals("0")){
                    tt21.setText("loading");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card21.setCardBackgroundColor(Color.parseColor("#A15A5252"));
                            tt21.setText("Off");
                        }
                    }, 2000);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //bedroom
        myData.child("bedroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString().equals("1")){
                    tt22.setText("loading");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card22.setCardBackgroundColor(Color.parseColor("#88EA8C"));
                            tt22.setText("On");
                        }
                    }, 2000);

                }else if(snapshot.getValue().toString().equals("0")){
                    tt22.setText("loading");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card22.setCardBackgroundColor(Color.parseColor("#A15A5252"));
                            tt22.setText("Off");
                        }
                    }, 2000);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //door
        myData.child("door").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString().equals("1")){
                    text11.setText("loading");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card11.setCardBackgroundColor(Color.parseColor("#88EA8C"));
                            text11.setText("Open");
                            icon11.setImageResource(R.drawable.ic_doorout);
                        }
                    }, 2000);


                }else if(snapshot.getValue().toString().equals("0")){
                    text11.setText("loading");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card11.setCardBackgroundColor(Color.parseColor("#A15A5252"));
                            text11.setText("Close");
                            icon11.setImageResource(R.drawable.ic_doorin);
                        }
                    }, 2000);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // check connect

        myData.child("connect").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString().equals("1")){
                    checkTv.setBackgroundColor(Color.parseColor("#ED2617"));

                }else if(snapshot.getValue().toString().equals("-1")){
                    checkTv.setBackgroundColor(Color.parseColor("#20D828"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
// read temperature and humidity
        myData.child("temp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                temp.setText(snapshot.getValue().toString() + "°C");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myData.child("hum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hum.setText(snapshot.getValue().toString() + "%");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setCancelable(false)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                })
                .setPositiveButton("No",null)
                .show();
    }
}