package nyc.c4q.jonathancolon.catchemall;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by dannylui on 12/5/16.
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
