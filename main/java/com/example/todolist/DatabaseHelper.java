package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="COUNTRIES";
    public static final String _ID="_id";
    public static final  String SUBJECT="subject";
    public static final  String DESC="description";

    static final String DB_Name="MY_DATABASE";
    static final int DB_VERSION=1;

    private static final String CREATE_TABLE= "create table "+TABLE_NAME+"(" + _ID
                                                + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ SUBJECT +
                                                " TEXT NOT NULL, "+DESC+" TEXT);";
    public DatabaseHelper(Context context){
        super(context,DB_Name,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
