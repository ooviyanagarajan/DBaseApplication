package com.example.guru.databaseapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity{
    EditText et_nme,et_sid,et_ml;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nme = (EditText)findViewById(R.id.studname);
      et_sid = (EditText)findViewById(R.id.studid);
      et_ml = (EditText)findViewById(R.id.email);
        b1=(Button)findViewById(R.id.btnshow);


    }

    public void StudReg(View view) {

        String name=et_nme.getText().toString();
        String num=et_sid.getText().toString();
        String mail=et_ml.getText().toString();

        String method="register";

        Processing p=new Processing(this);
        p.execute(method,name,num,mail);

    }

    public void StudDelete(View view) {

        String delid=et_sid.getText().toString();
        String method="delete";
        Processing p=new Processing(this);
        p.execute(method,delid);

    }

    public void StudUpdate(View view) {
        String upid=et_sid.getText().toString();
        String method="update";
        Processing p=new Processing(this);
        p.execute(method,upid);
    }
    public void showdet(View b1) {

        startActivity(new Intent(this,ActivityDetails.class));
    }
}