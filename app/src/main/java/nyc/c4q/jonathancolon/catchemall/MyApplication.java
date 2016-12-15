package nyc.c4q.jonathancolon.catchemall;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by dannylui on 12/14/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

    }
}
