package sansan.ru.rockylabs.sansan.utils.prefs;

import android.support.annotation.Nullable;

/**
 * Created by Zinnur on 19.12.16.
 */

public class AuthPrefs {

    private static final String TOKEN = "token";

    public static @Nullable
    String getToken() {
        return PrefUtils.getPrefs().getString(TOKEN, null);
    }

    public static void setToken(String token) {
        PrefUtils.transact( p -> p.putString(TOKEN,token));
    }

    public static void clear(){
        PrefUtils.getEditor().remove(TOKEN).apply();
    }
}