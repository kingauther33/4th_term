package com.example.sqlitedemo;

import android.content.Context;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DBHelperTest extends TestCase {

    private Context context;
    private DBHelper db;

    @Before
    public void setUp() throws Exception {
        context = ApplicationProvider.getApplicationContext();
        db = new DBHelper(context);
        super.setUp();
    }

    @Test
    public void testInsertDB() {
        Credential credential = new Credential("2184892175982175821985", "1oiu29821587217589", 2, "921482195891052",
                "2022-12-01", "2022-01-12", 1);
        String message = db.insertDB(credential);
        System.out.println(message);
        Log.d("MESSAGE", message);
    }
}