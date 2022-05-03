package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "UserdetailWHIP.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table if not exists UserdetailWHIP(id TEXT, name TEXT, phone TEXT, email TEXT, instagram TEXT, snapchat TEXT, twitter TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserdetailWHIP.db");
    }

    public boolean insertuserdata(String id, String name, String email, String phone, String instagram, String snapchat, String twitter){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("instagram", instagram);
        contentValues.put("snapchat", snapchat);
        contentValues.put("twitter", twitter);
        long result = DB.insert("UserdetailWHIP", null, contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean updateuserdata(String id, String name, String email, String phone, String instagram, String snapchat, String twitter){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("instagram", instagram);
        contentValues.put("snapchat", snapchat);
        contentValues.put("twitter", twitter);
        Cursor cursor = DB.rawQuery("Select * from UserdetailWHIP where id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = DB.update("UserdetailWHIP", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }


    public boolean deleteuserdata(String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserdetailWHIP where id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = DB.delete("UserdetailWHIP", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserdetailWHIP", null);
        return cursor;
    }
}