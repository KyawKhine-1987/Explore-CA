package tourfinder.exploreca.com.kk.freelance.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import tourfinder.exploreca.com.kk.freelance.R;
import tourfinder.exploreca.com.kk.freelance.adapter.TourListRVAdapter;
import tourfinder.exploreca.com.kk.freelance.database.ToursDataSource;
import tourfinder.exploreca.com.kk.freelance.model.Tour;
import tourfinder.exploreca.com.kk.freelance.parse.ToursPullParser;

public class TourListActivity extends AppCompatActivity implements TourListRVAdapter.ListItemClickListener {

    public static final String LOG_TAG = TourListActivity.class.getSimpleName();

    public static final String USERNAME = "pref_username";
    public static final String VIEWIMAGE = "pref_viewimages";
    private static final int TOUR_DETAIL_ACTIVITY = 1001;

    private SharedPreferences settings;
    private OnSharedPreferenceChangeListener listener;
    private List<Tour> tours = new ArrayList<>();
    boolean isMyTours;

    ToursDataSource ds;

    /*
    * References to RecyclerView and Adapter to reset the list to its
    * "pretty" state when the reset menu item is clicked.
    */
    private TourListRVAdapter mAdapter;
    private RecyclerView mToursList;
    private static final int TOUR_LIST_ITEMS = 26;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST : onCreate() called...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_lists);

//        settings = getPreferences(MODE_PRIVATE);
        settings = PreferenceManager.getDefaultSharedPreferences(this);

        listener = new OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.i(LOG_TAG, "onSharedPreferenceChanged() called...");
//                MainActivity.this.refreshDisplay(null);
                TourListActivity.this.refreshDisplay();
            }
        };

        settings.registerOnSharedPreferenceChangeListener(listener);

        ds = new ToursDataSource(this);
        ds.open();

        tours = ds.findAll();
        if (tours.size() == 0) {
            createData();
            tours = ds.findAll();
        }
        isMyTours = false;

        mToursList = (RecyclerView) findViewById(R.id.rvToursListItems);
        mToursList.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mToursList.setLayoutManager(layoutManager);

        mAdapter = new TourListRVAdapter(getApplicationContext(), tours, this);
        mToursList.setAdapter(mAdapter);

        //refreshDisplay();
    }

    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "TEST : onResume() called...");

        super.onResume();
        ds.open();
    }

    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "TEST : onPause() called...");

        super.onPause();
        ds.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(LOG_TAG, "TEST : onCreateOptionsMenu() called...");

        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(LOG_TAG, "TEST : onOptionsItemSelected() called...");

        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
            case R.id.menu_all:
                tours = ds.findAll();
                refreshDisplay();
                isMyTours = false;
                break;
            case R.id.menu_cheap:
                tours = ds.findFiltered("price <= 300", "price ASC");
                refreshDisplay();
                isMyTours = false;
                break;
            case R.id.menu_fancy:
                tours = ds.findFiltered("price >= 1000", "price DESC");
                refreshDisplay();
                isMyTours = false;
                break;
            case R.id.menu_mytours:
                tours = ds.findMyTours();
                refreshDisplay();
                isMyTours = true;
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshDisplay() {
        Log.i(LOG_TAG, "TEST : refreshDisplay() called...");
        boolean viewImages = settings.getBoolean(VIEWIMAGE, false);

        if (viewImages) {
            mAdapter = new TourListRVAdapter(getApplicationContext(), tours, this);
            mToursList.setAdapter(mAdapter);
        }
    }

    private void createData() {
        Log.i(LOG_TAG, "TEST : createData() called...");

        ToursPullParser tpp = new ToursPullParser();
        List<Tour> tours = tpp.parseXML(this);

        for (Tour tour : tours) {
            ds.create(tour);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(LOG_TAG, "TEST : onActivityResult() called...");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TOUR_DETAIL_ACTIVITY && resultCode == -1) {
            ds.open();
            tours = ds.findMyTours();
//            refreshDisplay();
            isMyTours = true;
        }
    }

    @Override
    public void onListItemClick(int position) {
        Log.i(LOG_TAG, "TEST : onListItemClick() called...");

        Tour tour = tours.get(position);

        Intent i = new Intent(this, TourDetailActivity.class);

        i.putExtra(".model.Tour", tour);
        i.putExtra("isMyTours", isMyTours);
        startActivityForResult(i, TOUR_DETAIL_ACTIVITY);
    }
}
