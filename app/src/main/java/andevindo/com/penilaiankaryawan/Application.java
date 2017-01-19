package andevindo.com.penilaiankaryawan;

import android.content.Context;



/**
 * Created by -H- on 10/30/2015.
 */
public class Application extends android.app.Application {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getInstance(){
        return sApplication;
    }

    public static Context getContext(){
        return sApplication.getApplicationContext();
    }
}
