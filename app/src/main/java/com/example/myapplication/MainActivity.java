package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private String  name, homenumber, phonenumber, email, zipcode;
    private TextView txtname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtname = findViewById(R.id.name);
        Bundle extra = getIntent().getExtras();
        if (extra != null)
        {
            name =  extra.getString("name");
            homenumber =  extra.getString("homenumber");
            phonenumber =  extra.getString("phonenumber");
            email =  extra.getString("email");
            zipcode =  extra.getString("zipcode");
            System.out.println("==== name :" + name);
            System.out.println("==== homenumber :" + homenumber);
            System.out.println("==== phonenumber :" + phonenumber);
            System.out.println("==== email :" + email);
            txtname.setText(name);
        }

    }
}
