package com.example.guru.databaseapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;


public class MainActivity extends Activity{
    EditText et_nme,et_sid,et_ml;
    Button btnshow, btnupd, btndel, btnreg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nme = (EditText)findViewById(R.id.studname);
        et_sid = (EditText)findViewById(R.id.studid);
        et_ml = (EditText)findViewById(R.id.email);



        btnshow=(Button)findViewById(R.id.btnshow);
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ActivityDetails.class));

            }
        });



        btnupd=(Button)findViewById(R.id.button);
        btnupd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=et_sid.getText().toString();
                String mail=et_ml.getText().toString();
                String method="update";
                Processing p=new Processing(MainActivity.this);
                p.execute(method,num,mail);
            }
        });

        btndel=(Button)findViewById(R.id.button2);
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=et_sid.getText().toString();
                String method="delete";
                Processing p=new Processing(MainActivity.this);
                p.execute(method,num);
            }
        });


        btnreg=(Button)findViewById(R.id.btnreg);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_nme.getText().toString();
                String num=et_sid.getText().toString();
                String mail=et_ml.getText().toString();
                String method="register";
                Processing p=new Processing(MainActivity.this);
                p.execute(method,name,num,mail);

            }
        });


    }



}