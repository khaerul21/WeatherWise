package com.example.weatherwise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.weatherwise.model.Weather;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Mycity.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mycity (id INTEGER PRIMARY KEY AUTOINCREMENT, weather_id INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mycity");


    }

    public ArrayList<Weather> getMyCity() {
        ArrayList<Weather> mycitieds = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("mycity", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int weatherId = cursor.getInt(cursor.getColumnIndexOrThrow("weather_id"));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return mycitieds;
    }

    public void insertMyCity(Weather weather) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("weather_id", weather.getId());

        db.insert("mycity", null, values);
        db.close();
    }

    public boolean isMyCityCitied(int weatherId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("mycity", null, "weather_id = ?", new String[]{String.valueOf(weatherId)}, null, null, null);
        boolean citied = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return citied;
    }

    public int getWeatherIdFromMyCityToUseItOnMyCityAdapterWhichShowedOnMyCityFragment(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT weather_id FROM mycity WHERE id = ? LIMIT 1", new String[]{String.valueOf(id)});
        int weather_id = 0;
        if (cursor.moveToFirst()) {
            weather_id = cursor.getInt(cursor.getColumnIndexOrThrow("weather_id"));
        }
        cursor.close();
        db.close();
        return weather_id;
    }
}
