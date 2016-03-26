package com.example.tomas.fruitmachine;

/*
Project: Fruit Machine
Class: DatabaseOperation
Purpose: Create and manage animations
Author: Tomas Hreha
Date: 25.3.2015
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tomas_000 on 25. 3. 2016.
 */
public class DatabaseOperation extends SQLiteOpenHelper {

    private static final int databaseVersion = 1;

    public String srcCreateTable = "CREATE TABLE " + TableData.TableInfo.FruitMachineResults + "(" + TableData.TableInfo.FirstColumnResult + " INTEGER, " +
            TableData.TableInfo.SecondColumnResult + " INTEGER, " + TableData.TableInfo.ThirdColumnResult + " INTEGER, " + TableData.TableInfo.Result + " TEXT, " +
            TableData.TableInfo.PrimaryKeyDateTime + " TEXT);";

    public DatabaseOperation(Context context) {

        super(context, TableData.TableInfo.DatabaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(srcCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public void PutData(DatabaseOperation DB, String FirstColumnResult, String SecondColumnResult, String ThirdColumnResult, String Result, String PrimaryKeyDateTime) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.FirstColumnResult, FirstColumnResult);
        CV.put(TableData.TableInfo.SecondColumnResult, SecondColumnResult);
        CV.put(TableData.TableInfo.ThirdColumnResult, ThirdColumnResult);
        CV.put(TableData.TableInfo.Result, Result);
        CV.put(TableData.TableInfo.PrimaryKeyDateTime, PrimaryKeyDateTime);

        SQ.insert(TableData.TableInfo.FruitMachineResults, null, CV);
    }

    public Cursor GetData(DatabaseOperation DB, String condition) {
        SQLiteDatabase SQ = DB.getReadableDatabase();
        return SQ.rawQuery("SELECT * FROM " + TableData.TableInfo.FruitMachineResults + condition, null);
    }

    public void UpdateData(DatabaseOperation DB, String columnForChange, String newValue, String PrimaryKeyDateTime) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(columnForChange, newValue);
        SQ.update(TableData.TableInfo.FruitMachineResults, Values, TableData.TableInfo.PrimaryKeyDateTime + " == " + PrimaryKeyDateTime, null);
    }

    public void DeleteData(DatabaseOperation DB, String PrimaryKeyDateTime) {
        SQLiteDatabase SQ = DB.getWritableDatabase();
        SQ.delete(TableData.TableInfo.FruitMachineResults, TableData.TableInfo.PrimaryKeyDateTime + " == " + PrimaryKeyDateTime, null);
    }

}
