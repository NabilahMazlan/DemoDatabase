package com.example.demodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "tasks.db";

    //creating tasks table
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
                                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + COLUMN_DESCRIPTION + " TEXT, "
                                + COLUMN_DATE + " TEXT )";
        sqLiteDatabase.execSQL(createTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + TABLE_TASK);
        onCreate(sqLiteDatabase);
    }

    public void insertTasks(String description, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_DATE, date);
        db.insert(TABLE_TASK, null, cv);
        db.close();
    }

    public ArrayList<String> getTaskContent(){
        ArrayList<String> tasks = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_DESCRIPTION + " FROM " + TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                tasks.add(cursor.getString(0));
            }while(cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return  tasks;
    }

    public ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                            + COLUMN_DESCRIPTION + ", "
                            + COLUMN_DATE + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String desc = cursor.getString(1);
                String date = cursor.getString(2);
                Task task = new Task(id, desc, date);
                tasks.add(task);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  tasks;
    }
}
