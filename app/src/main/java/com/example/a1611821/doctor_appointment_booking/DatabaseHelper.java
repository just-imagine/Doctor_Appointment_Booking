package com.example.a1611821.doctor_appointment_booking;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	
	private String DB_PATH;
	private String DB_NAME;
	private SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/*
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DatabaseHelper(Context p,String dbname) {
		super(p, dbname + ".db", null, 1); 
		DB_NAME = dbname + ".db";
	}
	
	public String getNow(){
		return sdf.format(new java.util.Date());
	}

	public Cursor doQuery(String sql, String[] params) {
		try {
			Cursor mCur = getReadableDatabase().rawQuery(sql, params);
			return mCur;
		} catch (SQLException mSQLException) {
			System.err.println("-- doQuery --\n"+sql);
			mSQLException.printStackTrace(System.err);
			return null;
		}
	}
	
	public void doUpdate(String sql, String[] params) {
		try {
			 getWritableDatabase().execSQL(sql, params);
		} catch (SQLException mSQLException) {
			System.err.println("-- doUpdate --\n"+sql);
			mSQLException.printStackTrace(System.err);
		}
	}
	
	
	public Cursor doQuery(String sql) {
		try {
			Cursor mCur = getReadableDatabase().rawQuery(sql,null);
			return mCur;
		} catch (SQLException mSQLException) {
			System.err.println("-- doQuery --\n"+sql);
			mSQLException.printStackTrace();
			return null;
		}
	}
		
	public void doUpdate(String sql) {
		try {
			 this.getWritableDatabase().execSQL(sql);
		} catch (SQLException mSQLException) {
			System.err.println("-- doUpdate --\n"+sql);
			mSQLException.printStackTrace(System.err);
		}
	}

	public long getSize()
	{
		/* Open the database object in "read" mode. */
	    final SQLiteDatabase db = getReadableDatabase();

	    /* Get length of database file. */
        final String dbPath       = db.getPath();        
        final File   dbFile       = new File(dbPath);
        final long   dbFileLength = dbFile.length();
        
        return (dbFileLength);
	}	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	   	db.execSQL("Create table ACCOUNTS(ID_NUMBER text PRIMARY KEY, EMAIL_ADDRESS text not null, NAME text,SURNAME text,CONTACT_NO text,GENDER text,PASSWORD text)");


	}

	public SQLiteDatabase getDB(){
		return getWritableDatabase();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}
}
