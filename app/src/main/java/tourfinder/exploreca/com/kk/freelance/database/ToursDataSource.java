package tourfinder.exploreca.com.kk.freelance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tourfinder.exploreca.com.kk.freelance.model.Tour;

/**
 * Created by Administrator on 11/07/2016.
 */

public class ToursDataSource {

    public static final String LOG_TAG = ToursDataSource.class.getSimpleName();

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            ToursDBOpenHelper.COLUMN_ID,
            ToursDBOpenHelper.COLUMN_TITLE,
            ToursDBOpenHelper.COLUMN_DESC,
            ToursDBOpenHelper.COLUMN_PRICE,
            ToursDBOpenHelper.COLUMN_IMAGE,
    };

    public ToursDataSource(Context context) {
        Log.i(LOG_TAG, "TEST : ToursDataSource() called...");

        dbHelper = new ToursDBOpenHelper(context);
//        database = dbHelper.getWritableDatabase();
    }

    public void open() {
        Log.i(LOG_TAG, "TEST : open() called that database was opened.");
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        Log.i(LOG_TAG, "TEST : open() called that database was closed.");
        dbHelper.close();
    }

    public Tour create(Tour tour) {
        Log.i(LOG_TAG, "TEST : create() called.");

        ContentValues cv = new ContentValues();
        cv.put(ToursDBOpenHelper.COLUMN_TITLE, tour.getTitle());
        cv.put(ToursDBOpenHelper.COLUMN_DESC, tour.getDescription());
        cv.put(ToursDBOpenHelper.COLUMN_PRICE, tour.getPrice());
        cv.put(ToursDBOpenHelper.COLUMN_IMAGE, tour.getImage());
        long id = database.insert(ToursDBOpenHelper.TABLE_TOURS, null, cv);
        tour.setId(id);
        return tour;
    }

    public List<Tour> findAll(){
        Log.i(LOG_TAG, "TEST : findAll() called.");

//        List<Tour> tours = new ArrayList<Tour>();

        Cursor c = database.query(ToursDBOpenHelper.TABLE_TOURS, allColumns, null, null, null, null, null);
        Log.i(LOG_TAG, "Returned " + c.getCount() + " rows.");
       /* List<Tour> tours = new ArrayList<Tour>();
        if(c.getCount() > 0){
            while (c.moveToNext()){
                Tour tour = new Tour();
                tour.setId(c.getLong(c.getColumnIndex(ToursDBOpenHelper.COLUMN_ID)));
                tour.setTitle(c.getString(c.getColumnIndex(ToursDBOpenHelper.COLUMN_TITLE)));
                tour.setDescription(c.getString(c.getColumnIndex(ToursDBOpenHelper.COLUMN_DESC)));
                tour.setImage(c.getString(c.getColumnIndex(ToursDBOpenHelper.COLUMN_IMAGE)));
                tour.setPrice(c.getDouble(c.getColumnIndex(ToursDBOpenHelper.COLUMN_PRICE)));
                tours.add(tour);
            }
        }
        return tours;*/
        List<Tour> tours = cursorToList(c);
        return tours;
    }

    public List<Tour> findFiltered(String selection, String orderBy){
        Log.i(LOG_TAG, "TEST : findFiltered() called.");

        Cursor c = database.query(ToursDBOpenHelper.TABLE_TOURS, allColumns, selection, null, null, null, orderBy);
        Log.i(LOG_TAG, "Returned " + c.getCount() + " rows.");
        List<Tour> tours = cursorToList(c);
        return tours;
    }


    @NonNull
    private List<Tour> cursorToList(Cursor c) {
        Log.i(LOG_TAG, "TEST : cursorToList() called.");

        List<Tour> tours = new ArrayList<Tour>();
        if(c.getCount() > 0){
            while (c.moveToNext()){
                Tour tour = new Tour();
                tour.setId(c.getLong(c.getColumnIndex(ToursDBOpenHelper.COLUMN_ID)));
                tour.setTitle(c.getString(c.getColumnIndex(ToursDBOpenHelper.COLUMN_TITLE)));
                tour.setDescription(c.getString(c.getColumnIndex(ToursDBOpenHelper.COLUMN_DESC)));
                tour.setImage(c.getString(c.getColumnIndex(ToursDBOpenHelper.COLUMN_IMAGE)));
                tour.setPrice(c.getDouble(c.getColumnIndex(ToursDBOpenHelper.COLUMN_PRICE)));
                tours.add(tour);
            }
        }
        return tours;
    }

    public boolean addToMyTours(Tour tour) {
        Log.i(LOG_TAG, "TEST : addToMyTours() called.");

        ContentValues values = new ContentValues();
        values.put(ToursDBOpenHelper.COLUMN_ID, tour.getId());
        long result = database.insert(ToursDBOpenHelper.TABLE_MYTOURS, null, values);
        return (result != -1);
    }

    public List<Tour> findMyTours() {
        Log.i(LOG_TAG, "TEST : findMyTours() called.");

        String query = "SELECT tours.* FROM " +
                "tours JOIN mytours ON " +
                "tours.tourId = mytours.tourId";
        Cursor cursor = database.rawQuery(query, null);

        Log.i(LOG_TAG, "Returned " + cursor.getCount() + " rows.");

        List<Tour> tours = cursorToList(cursor);
        return tours;
    }

    public boolean removeFromMyTours(Tour tour){
        Log.i(LOG_TAG, "TEST : removeFromMyTours() called.");

        String where = ToursDBOpenHelper.COLUMN_ID + "=" + tour.getId();
        int result = database.delete(ToursDBOpenHelper.TABLE_MYTOURS, where, null);
        return (result == 1);
    }
}
