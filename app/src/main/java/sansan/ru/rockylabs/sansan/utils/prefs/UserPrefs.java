package sansan.ru.rockylabs.sansan.utils.prefs;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;

import sansan.ru.rockylabs.sansan.MVP.models.dto.UserDTO;
import sansan.ru.rockylabs.sansan.di.App;

/**
 * Created by Zinnur on 20.12.16.
 */

public class UserPrefs {

    private static final String USER = "user";

    public static @Nullable UserDTO getUser() {
        return App.getComponent().getGson().fromJson(PrefUtils.getPrefs().getString(USER, null), getType());
    }

    public static void setUser(UserDTO user) {
        PrefUtils.transact(prefs -> prefs.putString(USER, App.getComponent().getGson().toJson(user)));
    }

    private static Type getType(){
        return new TypeToken<UserDTO>() {}.getType();
    }
}
