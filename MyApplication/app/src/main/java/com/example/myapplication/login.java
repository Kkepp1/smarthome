package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
    Button button;
    EditText ed1,ed2;
    TextView tv2;
    String ten,mk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        tv2 = (TextView) findViewById(R.id.tv2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten = ed1.getText().toString();
                mk = ed2.getText().toString();
                if(ten.equals("admin")==true && mk.equals("1234") ){
                    startActivity(new Intent(login.this,MainActivity.class));
                    ed1.setText("");
                    ed2.setText("");
                }else
                    tv2.setText("Sai tài khoản hoặc mật khẩu");
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
                        System.exit(0);
                    }
                })
                .setPositiveButton("No",null)
                .show();
    }
}