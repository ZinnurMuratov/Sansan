package sansan.ru.rockylabs.sansan.utils.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.annimon.stream.function.Function;

import sansan.ru.rockylabs.sansan.di.App;

/**
 * Created by Zinnur on 19.12.16.
 */

public class PrefUtils {

    private static final String PREF_NAME = "sansan";
    private static SharedPreferences preferences;

    public static SharedPreferences getPrefs() {
        preferences = App.getComponent().getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences;
    }

    public static SharedPreferences.Editor getEditor() {
        return getPrefs().edit();
    }

    public static void clear(){
        getEditor().clear().commit();
    }


    public static void transact(Function<SharedPreferences.Editor, SharedPreferences.Editor> action) {
        action.apply(getEditor()).apply();
    }


}