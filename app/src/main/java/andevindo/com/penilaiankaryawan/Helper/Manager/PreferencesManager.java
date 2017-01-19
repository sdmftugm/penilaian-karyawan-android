package andevindo.com.penilaiankaryawan.Helper.Manager;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by -H- on 12/19/2015.
 */
public class PreferencesManager {

    private static String sName = "com.andevindo.penilaiankaryawan.preferences";

    public static void login(Context context, int id, String fullName, String email) {
        SharedPreferences.Editor editor = context.getSharedPreferences(sName, Context.MODE_PRIVATE).edit();
        editor.putInt("id", id);
        editor.putString("fullName", fullName);
        editor.putString("email", email);
        editor.putBoolean("isLogin", true);
        editor.apply();
    }

    public static boolean isLogin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(sName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLogin", false);
    }

    public static void logout(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(sName, Context.MODE_PRIVATE).edit();
        editor.putInt("id", 0);
        editor.putString("fullname", "");
        editor.putString("email", "");
        editor.putBoolean("isLogin", false);
        editor.apply();
    }



    public static int getId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(sName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", 0);
    }

    public static String getFullName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(sName, Context.MODE_PRIVATE);
        return sharedPreferences.getString("fullName", "");
    }

}
