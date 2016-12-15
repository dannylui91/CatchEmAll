package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;

public class PrisonCell extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key_third_activity";

    private TextView nameLayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prison_cell);

        Intent intent = getIntent();
        Prisoner prisoner = (Prisoner) intent.getExtras().getSerializable(PRISONER_KEY);
        //checking if getting the right prisoner from main activity
        System.out.println("FROM THIRD ACTIVITY: " + prisoner.getFirstName() + " " + prisoner.getLastName());
        initViews();
        PrisonerHelper prisonerHelper = new PrisonerHelper(this);
        prisonerHelper.updatePrisonerSpriteView(prisoner);
        String name = (prisoner.getFirstName() + " " + prisoner.getLastName());

        Date date = new Date(prisoner.getLastInspected());

        DateFormat calanderDateformatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

        String imprisonDateFormatted = calanderDateformatter.format(date);
        String timeSinceInspectionFormatted = timeFormatter.format(timeSinceInspection(prisoner));


        nameLayer.setText(name + "\n" + imprisonDateFormatted + "\n" + timeSinceInspectionFormatted);


    }

    private void initViews() {
        nameLayer = (TextView) findViewById(R.id.prisoner_info);
    }

    private long timeSinceInspection(Prisoner prisoner) {
        long lastInspection = prisoner.getLastInspected();
        long currentTime = System.currentTimeMillis();
        long timeSinceInspection = currentTime - lastInspection;

        return timeSinceInspection;
    }
}
