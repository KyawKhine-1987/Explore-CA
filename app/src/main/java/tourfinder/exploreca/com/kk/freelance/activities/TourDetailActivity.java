package tourfinder.exploreca.com.kk.freelance.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

import tourfinder.exploreca.com.kk.freelance.R;
import tourfinder.exploreca.com.kk.freelance.database.ToursDataSource;
import tourfinder.exploreca.com.kk.freelance.model.Tour;

public class TourDetailActivity extends AppCompatActivity {

    public static final String LOG_TAG = TourDetailActivity.class.getSimpleName();

    Tour tour;
    ToursDataSource tds;
    boolean isMyTours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST : onCreate() called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_detail);

	/*	tour = new Tour();
        tour.setId(1);
		tour.setTitle("Tour title");
		tour.setDescription("Tour description");
		tour.setPrice(999);
		tour.setImage("map_winecountry");*/
        Bundle b = getIntent().getExtras();
        tour = b.getParcelable(".model.Tour");

        refreshDisplay();
        tds = new ToursDataSource(this);
    }

    private void refreshDisplay() {
        Log.i(LOG_TAG, "TEST : refreshDisplay() called...");

        TextView tv = (TextView) findViewById(R.id.titleText);
        tv.setText(tour.getTitle());

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        tv = (TextView) findViewById(R.id.priceText);
        tv.setText(nf.format(tour.getPrice()));

        tv = (TextView) findViewById(R.id.descText);
        tv.setText(tour.getDescription());

        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        int imageResource = getResources().getIdentifier(
                tour.getImage(), "drawable", getPackageName());
        if (imageResource != 0) {
            iv.setImageResource(imageResource);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(LOG_TAG, "TEST : onCreateOptionsMenu() called...");

        getMenuInflater().inflate(R.menu.tour_detail, menu);

        //Show delete menu item if we came from My Tours
        menu.findItem(R.id.menu_delete).setVisible(isMyTours);

        //Show add menu item if we didn't come from My Tours
        menu.findItem(R.id.menu_delete).setVisible(!isMyTours);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(LOG_TAG, "TEST : onOptionsItemSelected() called...");

        switch (item.getItemId()) {
            case R.id.menu_add:
                if (tds.addToMyTours(tour)) {
                    Log.i(LOG_TAG, "TEST : Tour added");
                } else {
                    Log.i(LOG_TAG, "TEST : Tour added");
                }
                break;
            case R.id.menu_delete:
                if (tds.removeFromMyTours(tour)){
                    setResult(-1);
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "TEST : onResume() called...");
        super.onResume();
        tds.open();
    }

    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "TEST : onPause() called...");
        super.onPause();
        tds.close();
    }
}
