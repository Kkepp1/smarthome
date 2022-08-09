package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class THG extends AppCompatActivity {
    ImageView add;
    TextView LTV,L1TV,L2TV,LH1TV,LH2TV,KTV,K1TV,K2TV,KH1TV,KH2TV,BTV,B1TV,B2TV,BH1TV,BH2TV;
    int lhour,lmin,lshour,lsmin;
    int khour,kmin,kshour,ksmin;
    int bhour,bmin,bshour,bsmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thg);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.m2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
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

        LTV=(TextView)findViewById(R.id.LTV);
        L1TV=(TextView)findViewById(R.id.L1TV);
        L2TV=(TextView)findViewById(R.id.L2TV);
        LH1TV=(TextView)findViewById(R.id.LH1TV);
        LH2TV=(TextView)findViewById(R.id.LH2TV);
        KTV=(TextView)findViewById(R.id.KTV);
        K1TV=(TextView)findViewById(R.id.K1TV);
        K2TV=(TextView)findViewById(R.id.K2TV);
        KH1TV=(TextView)findViewById(R.id.KH1TV);
        KH2TV=(TextView)findViewById(R.id.KH2TV);
        BTV=(TextView)findViewById(R.id.BTV);
        B1TV=(TextView)findViewById(R.id.B1TV);
        B2TV=(TextView)findViewById(R.id.B2TV);
        BH1TV=(TextView)findViewById(R.id.BH1TV);
        BH2TV=(TextView)findViewById(R.id.BH2TV);

        add = (ImageView)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), add.class));
            }
        });
        // bottom navigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    //check id
                    case R.id.m1:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                    case R.id.m2:
                        break;
                }
                return true;
            }
        });
        // read time alarm form  fire base
        //--- living room
        myData.child("l_hour").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lhour=snapshot.getValue(Integer.class);
                myData.child("l_min").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        lmin=snapshot.getValue(Integer.class);
                        if(lhour<0){
                            L1TV.setText("Start:");
                        }else L1TV.setText("Start:    "+lhour+"  :  "+lmin);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myData.child("l_shour").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lshour=snapshot.getValue(Integer.class);
                myData.child("l_smin").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        lsmin=snapshot.getValue(Integer.class);
                        if(lsmin<0){
                            L2TV.setText("Stop :");
                        }else L2TV.setText("Stop :    "+lshour+"  :  "+lsmin);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // kitchen
        myData.child("k_hour").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                khour=snapshot.getValue(Integer.class);
                myData.child("k_min").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        kmin=snapshot.getValue(Integer.class);
                        if(kmin<0){
                            K1TV.setText("Start:");
                        }else K1TV.setText("Start:    "+khour+"  :  "+kmin);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myData.child("k_shour").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kshour=snapshot.getValue(Integer.class);
                myData.child("k_smin").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ksmin=snapshot.getValue(Integer.class);
                        if(ksmin<0){
                            K2TV.setText("Stop :");
                        }else K2TV.setText("Stop :    "+kshour+"  :  "+ksmin);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // bedroom
        myData.child("b_hour").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bhour=snapshot.getValue(Integer.class);
                myData.child("b_min").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        bmin=snapshot.getValue(Integer.class);
                        if(bmin<0){
                            B1TV.setText("Start:");
                        }else B1TV.setText("Start:    "+bhour+"  :  "+bmin);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myData.child("b_shour").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bshour=snapshot.getValue(Integer.class);
                myData.child("b_smin").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        bsmin=snapshot.getValue(Integer.class);
                        if(bsmin<0){
                            B2TV.setText("Stop :");
                        }else B2TV.setText("Stop :    "+bshour+"  :  "+bsmin);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // delete infomation
        // living room
        LH1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l_hour.setValue(-1);
                l_min.setValue(-1);

            }
        });
        LH2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l_shour.setValue(-1);
                l_smin.setValue(-1);

            }
        });
        // kitchen
        KH1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k_hour.setValue(-1);
                k_min.setValue(-1);

            }
        });
        KH2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k_shour.setValue(-1);
                k_smin.setValue(-1);

            }
        });
        // bedroom
        BH1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_hour.setValue(-1);
                b_min.setValue(-1);

            }
        });
        BH2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_shour.setValue(-1);
                b_smin.setValue(-1);

            }
        });

        //end program-------------------------------------------------------------------------------
    }

    private void show() {

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