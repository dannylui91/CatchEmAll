package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class PrisonerEncounter extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key";
    private ImageView eyeColorLayer;
    private ImageView skinToneLayer;
    private ImageView hairStyleLayer;
    private ImageView accessoryLayer;
    private ImageView beardLayer;
    private Button createPrisonerButton;
    private Animation closeCellAnimation;
    private FloatingActionButton capturePrisonerButton;
    private static SQLiteDatabase db;
    private TextView nameLayer;
    private ImageView prisonBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisoner_encounter);

        initViews();

        Intent intent = getIntent();
        final Prisoner prisoner = (Prisoner) intent.getExtras().getSerializable(PRISONER_KEY);
        PrisonerHelper prisonerHelper = new PrisonerHelper(this);

        prisonerHelper.updatePrisonerSpriteView(prisoner);
        nameLayer.setText(prisoner.getFirstName() + " " + prisoner.getLastName());


        capturePrisonerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CellBlock.onPrisonerCapture(prisoner);
                PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(PrisonerEncounter.this);
                db = dbHelper.getWritableDatabase();
                cupboard().withDatabase(db).put(prisoner);
                Intent intent = new Intent(PrisonerEncounter.this, CellBlock.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);



                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        nameLayer = (TextView)findViewById(R.id.prisoner_name);
        capturePrisonerButton = (FloatingActionButton) findViewById(R.id.fab_capture_prisoner);
        prisonBars = (ImageView) findViewById(R.id.prison_bars);
    }

    public void closeCellAnimation(View view) {
        prisonBars.startAnimation(closeCellAnimation);
    }

}
