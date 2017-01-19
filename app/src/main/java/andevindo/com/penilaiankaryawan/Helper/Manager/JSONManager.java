package andevindo.com.penilaiankaryawan.Helper.Manager;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import andevindo.com.penilaiankaryawan.Helper.Config;
import andevindo.com.penilaiankaryawan.Model.User;

/**
 * Created by heendher on 8/1/2016.
 */
public class JSONManager {

    public static String getJSONFromAsset(Context context){
        String json = null;
        try {

            InputStream is = context.getAssets().open("fotografer.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (NullPointerException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static boolean isSuccess(JSONObject jsonObject) {
        int code = 0;
        try {
            code = jsonObject.getInt("kode");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (code == Config.SUCCESS_SERVER)
            return true;
        else
            return false;
    }

    public static boolean isNull(JSONObject jsonObject) {
        int code = 0;
        try {
            code = jsonObject.getInt("kode");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (code == Config.NULL_RESULT)
            return true;
        else
            return false;
    }

    public static boolean isMissingInput(JSONObject jsonObject) {
        int code = 0;
        try {
            code = jsonObject.getInt("kode");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (code == Config.MISSING_INPUT)
            return true;
        else
            return false;
    }

    public static boolean isBooked(JSONObject jsonObject) {
        int code = 0;
        try {
            code = jsonObject.getInt("kode");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (code == Config.ALREADY_BOOKED)
            return true;
        else
            return false;
    }

    public static User getUser(JSONObject jsonObject){
        try {
            User user = new User();
            user.setId(jsonObject.getInt("id"));
            user.setName(jsonObject.getString("name"));
            user.setPhoto(jsonObject.getString("photo"));
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<User> getListKaryawan(JSONObject jsonObject){
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<User> list = new ArrayList<>(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(getUser(jsonArray.getJSONObject(i)));
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
