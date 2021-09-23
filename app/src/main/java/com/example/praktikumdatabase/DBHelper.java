package com.example.praktikumdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table userdetails(nama TEXT, nrp TEXT primary key, jurusan TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists userdetails");
    }

    //  Insert Data Function
    public Boolean insertuserdata(String nama, String nrp, String jurusan) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("nrp", nrp);
        contentValues.put("jurusan", jurusan);
        long result = DB.insert("userdetails", null, contentValues);
        return result != -1;
    }

    //  Update Data Function
    public Boolean updateuserdata(String nama, String nrp, String jurusan) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nrp", nrp);
        contentValues.put("jurusan", jurusan);
        Cursor cursor = DB.rawQuery("select * from userdetails where nama = ?", new String[]{nama});
        if (cursor.getCount() > 0) {
            long result = DB.update("userdetails", contentValues, "nama=?", new String[]{nama});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    //  Delete Data Function
    public Boolean deletedata(String nama) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from userdetails where nama = ?", new String[]{nama});
        if (cursor.getCount() > 0) {
            long result = DB.delete("userdetails", "nama=?", new String[]{nama});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

//    Fungsi Ambil Data
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from userdetails",null);
        return cursor;
    }
}