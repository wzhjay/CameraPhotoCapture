package com.androidexample.cameraphotocapture;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TempleDBHandler extends SQLiteOpenHelper {
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "singaporemap";
    
    // TEMPLE table name
    private static final String TABLE_TEMPLE = "temple";
    
    // temple Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TEMPLENAME = "temple_name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DEITIES = "deities";
    private static final String KEY_DIALECT = "dialect";
    private static final String KEY_BUILDERNAME = "builder_name";
    private static final String KEY_WORSHIPS = "worships";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_OTHERS = "others";
    private static final String KEY_LON = "lon";
    private static final String KEY_LAT = "lat";
    
    public TempleDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEMPLE_TABLE = "CREATE TABLE " + TABLE_TEMPLE + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_TEMPLENAME + " TEXT,"
                + KEY_TYPE + " TEXT,"
                + KEY_DEITIES + " TEXT,"
                + KEY_DIALECT + " TEXT,"
                + KEY_BUILDERNAME + " TEXT,"
                + KEY_WORSHIPS + " TEXT,"
                + KEY_CONTACT + " TEXT,"
                + KEY_OTHERS + " TEXT,"
                + KEY_LON + " TEXT,"
                + KEY_LAT + " TEXT"
                + ")";
        db.execSQL(CREATE_TEMPLE_TABLE);
    }
    
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEMPLE);
 
        // Create tables again
        onCreate(db);
    }
    
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    // Adding new temple
    void addTemple(Temple temple) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        String sql =
                "INSERT or REPLACE INTO \""+ TABLE_TEMPLE+"\" (\""+KEY_ID+"\", \""+KEY_TYPE+"\", \""+KEY_DEITIES+"\", \""+KEY_DIALECT+"\", \""+KEY_BUILDERNAME+"\", \""+KEY_WORSHIPS+"\", \""+KEY_CONTACT+"\", \""+KEY_OTHERS+"\", \""+KEY_LON+"\", \""+KEY_LAT+"\")"
                + " VALUES(\""+temple.getID()+"\", \""+temple.getTempleName()+"\", \""+temple.getType()+"\", \""+temple.getDeities()+"\", \""+temple.getBuilderName()+"\", \""+temple.getWorships()+"\", \""+temple.getContact()+"\", \""+temple.getOthers()+"\", \""+temple.getLon()+"\", \""+temple.getLat()+"\")" ;       
                    db.execSQL(sql);
        db.close(); // Closing database connection
    }
    
    // Getting single temple
    Temple getTemple(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_TEMPLE, new String[] { KEY_ID,
        		KEY_TYPE, KEY_DEITIES, KEY_DIALECT, KEY_BUILDERNAME, KEY_WORSHIPS, KEY_CONTACT, KEY_OTHERS, KEY_LON, KEY_LAT, }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Temple temple = new Temple(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6),
                cursor.getString(7), cursor.getString(8),
                cursor.getString(9), cursor.getString(10));
        // return temple
        return temple;
    }
    
    // check is temple login in before
    public boolean hasTemple(){
    	SQLiteDatabase db = this.getReadableDatabase();
    	String selectQuery = "SELECT  * FROM " + TABLE_TEMPLE;
    	Cursor cursor = db.rawQuery(selectQuery, null);
    	if(cursor.getCount() > 0){
    		return true;
    	}
    	else
    		return false;
    }

    // get all temples
    public ArrayList<Temple> getAllTemples(){
        ArrayList<Temple> list = new ArrayList<Temple>();
        Log.v("LocalTemple", "hello");
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TEMPLE;
        Log.v("LocalTemple", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
            Log.v("LocalTemple", cursor.toString());
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Temple obj = new Temple();
                        Log.v("LocalTemple", "String0: " + cursor.getString(0) + "String1: " + cursor.getString(1) + "String2: " + cursor.getString(2));
                        obj.setID(cursor.getString(0));
                        obj.setTempleName(cursor.getString(1));
                        obj.setType(cursor.getString(2));
                        obj.setDeities(cursor.getString(3));
                        obj.setDialect(cursor.getString(4));
                        obj.setBuilderName(cursor.getString(5));
                        obj.setWorships(cursor.getString(6));
                        obj.setContact(cursor.getString(7));
                        obj.setOthers(cursor.getString(8));
                        obj.setLon(cursor.getString(9));
                        obj.setLat(cursor.getString(10));
                        
                        list.add(obj);
                    } while (cursor.moveToNext());
                }

        // return  list
        return list;
    }
    
    // Updating single temple
    public int updateTemple(Temple temple) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, temple.getID()); // id
        values.put(KEY_TEMPLENAME, temple.getTempleName()); // templeName
        values.put(KEY_TYPE, temple.getType());
        values.put(KEY_DEITIES, temple.getDeities());
        values.put(KEY_DIALECT, temple.getDialect());
        values.put(KEY_BUILDERNAME, temple.getBuilderName());
        values.put(KEY_WORSHIPS, temple.getWorships());
        values.put(KEY_CONTACT, temple.getContact());
        values.put(KEY_OTHERS, temple.getOthers());
        values.put(KEY_LON, temple.getLon());
        values.put(KEY_LAT, temple.getLat()); 
 
        // updating row
        return db.update(TABLE_TEMPLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(temple.getID()) });
    }
    
    // Deleting single temple
    public void deleteTemple(Temple temple) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEMPLE, KEY_ID + " = ?",
                new String[] { String.valueOf(temple.getID()) });
        db.close();
    }
    
    // Deleting temple table
    public void deleteTemple() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_TEMPLE);
        db.close();
    }
}
