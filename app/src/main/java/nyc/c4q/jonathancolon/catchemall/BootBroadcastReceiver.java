package nyc.c4q.jonathancolon.catchemall;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by dannylui on 12/12/16.
 */

public class BootBroadcastReceiver extends WakefulBroadcastReceiver {
    // WakefulBroadcastReceiver ensures the device does not go back to sleep
    // during the startup of the service
    @Override
    public void onReceive(Context context, Intent intent) {
        // Launch the specified service when this message is received
        Intent startServiceIntent = new Intent(context, MyTestService.class);
        startWakefulService(context, startServiceIntent);
    }
}
