package lokacar.projet.app;

import android.app.Application;

public class AppName extends Application{
    private String mAppName;

    public String getmAppName() {
        return mAppName;
    }

    public void setmAppName(String appName) {
        this.mAppName = appName;
    }
}
