package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;

public class ThirdActivity extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key_third_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        Prisoner prisoner = (Prisoner) intent.getExtras().getSerializable(PRISONER_KEY);
        //checking if getting the right prisoner from main activity
        System.out.println("FROM THIRD ACTIVITY: " + prisoner.getFirstName() + " " + prisoner.getLastName());


    }
}
