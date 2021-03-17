package com.example.cubicchart.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cubicchart.Model.BarChart;


import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    public database( Context context)
    {
        super(context,constant.Database_Name,null, constant.DATABASE_VERSION);
        //class.database name ,null,class.database version

    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {

        //Table
        String  BARCHART_TABLE = "CREATE TABLE "
                + constant.TABLE_NAME_BAR
                + " ("+ constant.KEY_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + constant.yAXIS1 + " STRING, "+ constant.yAXIS2 + " STRING, "+ constant.yAXIS3 + " STRING);";


        db.execSQL(BARCHART_TABLE);


    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS  " + constant.TABLE_NAME_BAR);

        onCreate(db);

    }



    public void saveData(BarChart valX)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(constant.yAXIS1,valX.getY_data1());
        values.put(constant.yAXIS2,valX.getY_data2());
        values.put(constant.yAXIS3,valX.getY_data3());

        db.insert(constant.TABLE_NAME_BAR,null,values);

    }

    //Array List


    public ArrayList<BarChart> queryBarData()
    {

        SQLiteDatabase db =   this.getReadableDatabase();
        ArrayList<BarChart> xData = new ArrayList<BarChart>();

        try {

            String query = "SELECT * FROM "+ constant.TABLE_NAME_BAR;

            Cursor cursor = db.rawQuery(query,null);

            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
            {

                xData.add(new BarChart(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),cursor.getString(3)));

            }
            cursor.close();
        }catch (Exception salman1) {}

        return xData;
    }



}
