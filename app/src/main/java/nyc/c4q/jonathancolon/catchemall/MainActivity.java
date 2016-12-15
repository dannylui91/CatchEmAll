package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.service.MyAlarmReceiver;
import nyc.c4q.jonathancolon.catchemall.service.MyService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!MyService.hasStarted) {
            System.out.println("MainActivity: starting service");
            startService();
        }
    }

    public void startService() {
        // Sends a broadcast to MyAlarmReceiver that will start MyService
        IntentFilter filter = new IntentFilter(MyAlarmReceiver.ACTION);
        this.registerReceiver(new MyAlarmReceiver(), filter);

        Intent intent = new Intent(MyAlarmReceiver.ACTION);
        sendBroadcast(intent);
    }

    public static void onPrisonerCapture(Prisoner prisoner) {
        System.out.println("Capturing prisoner");
    }
}
