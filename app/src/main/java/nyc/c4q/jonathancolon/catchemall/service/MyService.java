package nyc.c4q.jonathancolon.catchemall.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import java.util.List;

import nyc.c4q.jonathancolon.catchemall.PrisonerEncounter;
import nyc.c4q.jonathancolon.catchemall.R;
import nyc.c4q.jonathancolon.catchemall.models.UinamesModel;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerBuilder;
import nyc.c4q.jonathancolon.catchemall.networks.UinamesClient;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.SqlHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Danny on 12/13/2016.
 */
public class MyService extends IntentService {
    private static final int TWELVE_HOURS_IN_MILLIS = 43200000;
    private static final int ONE_MINUTE_IN_MILLIS = 60000;

    private static Long lastCreatedPrisoner = System.currentTimeMillis();
    public static boolean hasStarted = false;

    private SQLiteDatabase db;

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        WakefulBroadcastReceiver.completeWakefulIntent(intent);

        System.out.println("Called IntentService...");

        if (!hasStarted) { // Needed or else it'll keep scheduling new alarms and you'll be swarmed with notifications
            System.out.println("Setting alarm for service...");
            scheduleAlarm();
            hasStarted = true;
        }

        generateRandomPrisoner();
        checkInspectionTime();
    }

    private void generateRandomPrisoner() {
        if (lastCreatedPrisoner + ONE_MINUTE_IN_MILLIS < System.currentTimeMillis()) {
            lastCreatedPrisoner = System.currentTimeMillis();

            UinamesClient client = UinamesClient.getInstance();
            Call<UinamesModel> call = client.getRandomName();
            call.enqueue(new Callback<UinamesModel>() {
                @Override
                public void onResponse(Call<UinamesModel> call, Response<UinamesModel> response) {
                    UinamesModel model = response.body();
                    String firstName = model.getName();
                    String lastName = model.getSurname();
                    Prisoner prisoner = generatePrisonerSprite();
                    prisoner.setFirstName(firstName);
                    prisoner.setLastName(lastName);
                    showNotification(prisoner);
                }

                @Override
                public void onFailure(Call<UinamesModel> call, Throwable t) {
                    Toast.makeText(MyService.this, "Failed to retrieve name data.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void checkInspectionTime() {
        PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        List<Prisoner> prisoners = SqlHelper.selectAllPrisoners(db);
        for (int i = 0; i < prisoners.size(); i++) {
            if (System.currentTimeMillis() - prisoners.get(i).getLastInspected() > TWELVE_HOURS_IN_MILLIS) {
                cupboard().withDatabase(db).delete(prisoners.get(i));
            }
        }
    }

    public Prisoner generatePrisonerSprite(){
        Prisoner prisoner = PrisonerBuilder.createPrisoner();
        System.out.println("** Magically create a new prisoner **");
        return prisoner;
    }

    public void showNotification(Prisoner prisoner) {
        int NOTIFICATION_ID = 555;

        // Define an intent to trigger when notification is selected (in this case to open an activity)
        Intent intent = new Intent(this, PrisonerEncounter.class);
        intent.putExtra(PrisonerEncounter.PRISONER_KEY, prisoner);

        // Turn this into a PendingIntent
        int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        // Attach the pendingIntent to a new notification using setContentIntent
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.notification_icon_cop_jon)
                .setContentTitle("Spotted an Escapee!")
                .setContentText(prisoner.getFirstName() + " " + prisoner.getLastName())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // Hides the notification after its been selected
                .build();

        // Get the notification manager system service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Setting a notification ID allows you to update the notification later on.
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void scheduleAlarm() {
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, MyService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), ONE_MINUTE_IN_MILLIS, pi);
    }

    public void cancelAlarm() {
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }
}
