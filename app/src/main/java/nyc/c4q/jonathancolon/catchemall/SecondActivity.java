package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class SecondActivity extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key";
    private ImageView eyeColorLayer;
    private ImageView skinToneLayer;
    private ImageView hairStyleLayer;
    private ImageView accessoryLayer;
    private ImageView beardLayer;
    private Button createPrisonerButton;

    private static SQLiteDatabase db;
    private TextView nameLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();

        Intent intent = getIntent();
        final Prisoner prisoner = (Prisoner) intent.getExtras().getSerializable(PRISONER_KEY);
        PrisonerHelper prisonerHelper = new PrisonerHelper(this);

        prisonerHelper.updatePrisonerSpriteView(prisoner);
        nameLayer.setText(prisoner.getFirstName() + " " + prisoner.getLastName());


        createPrisonerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onPrisonerCapture(prisoner);
                PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(SecondActivity.this);
                db = dbHelper.getWritableDatabase();
                cupboard().withDatabase(db).put(prisoner);
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        createPrisonerButton = (Button) findViewById(R.id.prisoner_button);
        nameLayer = (TextView)findViewById(R.id.prisoner_name);
    }

}
