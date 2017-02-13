package tourfinder.exploreca.com.kk.freelance.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import tourfinder.exploreca.com.kk.freelance.R;

/**
 * Created by Administrator on 11/04/2016.
 */

public class SettingsActivity extends PreferenceActivity {

    private static final String LOG_TAG = SettingsActivity.class.getSimpleName();

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST : onCreate() called...");

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
