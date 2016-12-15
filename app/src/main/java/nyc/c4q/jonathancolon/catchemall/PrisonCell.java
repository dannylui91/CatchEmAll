package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
        nameLayer.setText(prisoner.getFirstName() + " " + prisoner.getLastName());




    }

    private void initViews() {
        nameLayer = (TextView)findViewById(R.id.prisoner_name);
    }
}
