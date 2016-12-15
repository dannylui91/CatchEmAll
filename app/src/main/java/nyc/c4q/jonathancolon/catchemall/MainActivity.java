package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.service.MyAlarmReceiver;
import nyc.c4q.jonathancolon.catchemall.service.MyService;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.SqlHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static PrisonerDatabaseHelper prisonerDatabaseHelper;
    private static SQLiteDatabase db;
    TextView prisonerNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!MyService.hasStarted) {
            System.out.println("MainActivity: starting service");
            startService();
        }
        PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(MainActivity.this);
        prisonerNames = (TextView)findViewById(R.id.prisoner_list);
        db = dbHelper.getReadableDatabase();

        List<Prisoner> prisonerList = SqlHelper.selectAllPrisoners(db);

        for (int i = 0; i < prisonerList.size(); i++) {
            StringBuilder names = new StringBuilder();
            names.append(prisonerList.get(i).getFirstName() + " " + prisonerList.get(i).getLastName() + "\n");
            prisonerNames.setText(names);
        }

        Toast.makeText(this, prisonerList.size(), Toast.LENGTH_SHORT).show();
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
