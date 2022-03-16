package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCountryActivity extends AppCompatActivity{

    private Button addbtn;
    private EditText subjectEditText;
    private EditText descEditText;
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar();
        setTitle("Add Record");
        setContentView(R.layout.activity_add_country);

        subjectEditText=findViewById(R.id.subject_edittext);
        descEditText=findViewById(R.id.description_edittext);
        addbtn=findViewById(R.id.add_record);

        dbManager=new DBManager(this);
        dbManager.open();
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=subjectEditText.getText().toString();
                final String desc=descEditText.getText().toString();
                if(name==""&&desc==""||name.length()==0&&desc.length()==0){
                    Toast.makeText(AddCountryActivity.this, "Please Enter Content", Toast.LENGTH_SHORT).show();

                }else {
                    dbManager.insert(name, desc);
                    Intent main=new Intent(AddCountryActivity.this,CountryList.class);
                    main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main);

                }

            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.add_Record:
//                final String name=subjectEditText.getText().toString();
//                final String desc=descEditText.getText().toString();
//                dbManager.insert(name,desc);
//                Intent main=new Intent(AddCountryActivity.this,CountryList.class);
//                main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(main);
//        }
//
//    }
}