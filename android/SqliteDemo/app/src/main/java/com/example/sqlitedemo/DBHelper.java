package com.example.sqlitedemo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "User.db";
    public static final String TABLE_NAME = "TBL_USER";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                TBL_USER.access_token + " TEXT, " +
                TBL_USER.refresh_token + " TEXT, " +
                TBL_USER.account_id + " INTEGER, " +
                TBL_USER.expire_time + " TEXT, " +
                TBL_USER.created_at + " TEXT, " +
                TBL_USER.updated_at + " TEXT, " +
                TBL_USER.status + " INTEGER )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public String insertDB(Credential credential) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TBL_USER.access_token, credential.getAccess_token());
        cv.put(TBL_USER.refresh_token, credential.getRefresh_token());
        cv.put(TBL_USER.account_id, credential.getAccount_id());
        cv.put(TBL_USER.expire_time, credential.getExpire_time());
        cv.put(TBL_USER.created_at, credential.getCreated_at());
        cv.put(TBL_USER.updated_at, credential.getUpdated_at());
        cv.put(TBL_USER.status, credential.getStatus());
        long isSuccess = db.insert(TABLE_NAME, null, cv);
        if (isSuccess > 0) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    @SuppressLint("Range")
    public List<Credential> getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        List<Credential> credentials = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        Log.d("USER1", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Credential credential = new Credential();
                credential.setAccess_token(cursor.getString(cursor.getColumnIndex(TBL_USER.access_token)));
                credential.setRefresh_token(cursor.getString(cursor.getColumnIndex(TBL_USER.refresh_token)));
                credential.setAccount_id(cursor.getInt(cursor.getColumnIndex(TBL_USER.account_id)));
                credential.setExpire_time(cursor.getString(cursor.getColumnIndex(TBL_USER.expire_time)));
                credential.setCreated_at(cursor.getString(cursor.getColumnIndex(TBL_USER.created_at)));
                credential.setUpdated_at(cursor.getString(cursor.getColumnIndex(TBL_USER.updated_at)));
                credential.setStatus(cursor.getInt(cursor.getColumnIndex(TBL_USER.status)));
                credentials.add(credential);
            } while (cursor.moveToNext());
        }
        return credentials;
    }

    public String updateUser(Credential credential) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TBL_USER.access_token, credential.getAccess_token());
        cv.put(TBL_USER.refresh_token, credential.getRefresh_token());
        cv.put(TBL_USER.account_id, credential.getAccount_id());
        cv.put(TBL_USER.expire_time, credential.getExpire_time());
        cv.put(TBL_USER.created_at, credential.getCreated_at());
        cv.put(TBL_USER.updated_at, credential.getUpdated_at());
        cv.put(TBL_USER.status, credential.getStatus());
        long isSuccess = db.update(TABLE_NAME, cv, "account_id=" + credential.getAccount_id(), null);
        if (isSuccess > 0) {
            return "Update Success";
        } else {
            return "Update Failed";
        }
    }

    public String deleteUser(int user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long isSuccess = db.delete(TABLE_NAME, "account_id=" + user_id, null);
        if (isSuccess > 0) {
            return "Update Success";
        } else {
            return "Update Failed";
        }
    }
}
