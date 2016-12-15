package nyc.c4q.jonathancolon.catchemall.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import nyc.c4q.jonathancolon.catchemall.R;
import nyc.c4q.jonathancolon.catchemall.SecondActivity;
import nyc.c4q.jonathancolon.catchemall.models.UinamesModel;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerBuilder;
import nyc.c4q.jonathancolon.catchemall.networks.UinamesClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Danny on 12/13/2016.
 */
public class MyService extends IntentService {
    private UinamesClient client;
    public static boolean hasStarted = false;
    private static Long lastCreatedPrisoner = System.currentTimeMillis();

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        WakefulBroadcastReceiver.completeWakefulIntent(intent);

        System.out.println("SERVICE CALLED");

        if (!hasStarted) { // Needed or else it'll keep scheduling new alarms and you'll be swarmed with notifications
            System.out.println("Setting alarm");
            scheduleAlarm();
            hasStarted = true;
        }

        // Do the task here
        if (lastCreatedPrisoner + 60000L < System.currentTimeMillis()) {
            System.out.println("lastCreated: " + lastCreatedPrisoner);
            System.out.println("currentTime: " + System.currentTimeMillis());
            lastCreatedPrisoner = System.currentTimeMillis();

            client = UinamesClient.getInstance();
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


    public Prisoner generatePrisonerSprite(){
        Prisoner prisoner = PrisonerBuilder.createPrisoner();
        System.out.println("CALLED A NEW PRISONER");
        return prisoner;
    }

    public void showNotification(Prisoner prisoner) {
        int NOTIFICATION_ID = 555;

        // Define an intent to trigger when notification is selected (in this case to open an activity)
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(SecondActivity.PRISONER_KEY, prisoner);

        // Turn this into a PendingIntent
        int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        // Attach the pendingIntent to a new notification using setContentIntent
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
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
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000L, pi);
    }

    public void cancelAlarm() {
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }
}
