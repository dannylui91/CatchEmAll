package nyc.c4q.jonathancolon.catchemall;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.util.Random;

/**
 * Created by dannylui on 12/12/16.
 */

public class MyTestService extends IntentService {
    public MyTestService() {
        super("MyTestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        WakefulBroadcastReceiver.completeWakefulIntent(intent);

        // Do the task here
        Random rand = new Random();
        int randNum = rand.nextInt(20);
        System.out.println("Random Number: " + randNum);
    }
}
