package szwedo.bookhubapp.dataAccess.local;

import android.content.Context;
import android.content.SharedPreferences;

import szwedo.bookhubapp.secure.SecureToken;
import szwedo.bookhubapp.ui.activity.main.MainActivity;

import static szwedo.bookhubapp.utils.Constants.SP_LIBRARY;
import static szwedo.bookhubapp.utils.Constants.SP_LOGIN;
import static szwedo.bookhubapp.utils.Constants.SP_TOKEN;

public class LocalDataAccess {

    public static boolean isLogin() {
        return getSP().getBoolean(SP_LOGIN, false);
    }

    public static void setLogin(boolean login) {
        getSP().edit().putBoolean(SP_LOGIN, login).apply();
    }

    public static String getToken() {
        return SecureToken.load(MainActivity.getAppContext());
    }

    public static void setToken(String token) {
        SecureToken.save(token, MainActivity.getAppContext());
    }

    public static void clean() {
        SharedPreferences sp = getSP();
        sp.edit().remove(SP_TOKEN).apply();
        sp.edit().remove(SP_LOGIN).apply();
    }

    private static SharedPreferences getSP() {
        return MainActivity.getAppContext().getSharedPreferences(SP_LIBRARY, Context.MODE_PRIVATE);
    }
}
