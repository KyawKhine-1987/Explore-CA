package tourfinder.exploreca.com.kk.freelance.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class UIHelper {
    private static final String LOG_TAG = UIHelper.class.getSimpleName();

    public static void displayText(Activity activity, int id, String text) {
        Log.i(LOG_TAG, "TEST : displayText() called...");

        TextView tv = (TextView) activity.findViewById(id);
        tv.setText(text);
    }

    public static String getText(Activity activity, int id) {
        Log.i(LOG_TAG, "TEST : getText() called...");

        EditText et = (EditText) activity.findViewById(id);
        return et.getText().toString();
    }

    public static boolean getCBChecked(Activity activity, int id) {
        Log.i(LOG_TAG, "TEST : getCBChecked() called...");

        CheckBox cb = (CheckBox) activity.findViewById(id);
        return cb.isChecked();
    }

    public static void setCBChecked(Activity activity, int id, boolean value) {
        Log.i(LOG_TAG, "TEST : setCBChecked() called...");

        CheckBox cb = (CheckBox) activity.findViewById(id);
        cb.setChecked(value);
    }

}
