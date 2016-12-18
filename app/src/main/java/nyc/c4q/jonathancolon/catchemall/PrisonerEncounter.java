package nyc.c4q.jonathancolon.catchemall;

import android.animation.Animator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class PrisonerEncounter extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key";

    private FloatingActionButton capturePrisonerButton;
    private ImageView encounterBackground;
    private ImageView prisonBars;
    private TextView nameLayer;

    private Prisoner prisoner;
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisoner_encounter);

        prisoner = (Prisoner) getIntent().getExtras().getSerializable(PRISONER_KEY);

        initViews();
        setupPrisonBarAnimation();
    }

    private void initViews() {
        capturePrisonerButton = (FloatingActionButton) findViewById(R.id.fab_capture_prisoner);
        encounterBackground = (ImageView) findViewById(R.id.encounter_image);
        prisonBars = (ImageView) findViewById(R.id.encounter_prison_bars);
        nameLayer = (TextView) findViewById(R.id.prisoner_name);

        prisonBars.setTranslationX(-getPhoneWidth()); //sets the prison bars to the left of the screen on load
        Picasso.with(this).load(R.drawable.background_encounter_happy).fit().into(encounterBackground);
        nameLayer.setText(prisoner.getFirstName() + " " + prisoner.getLastName());
        PrisonerHelper prisonerHelper = new PrisonerHelper(this);
        prisonerHelper.updatePrisonerSpriteView(prisoner);
    }

    private void setupPrisonBarAnimation() {
        capturePrisonerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturePrisonerButton.setClickable(false);
                closeCellAnimation();
            }
        });
    }

    private void closeCellAnimation() {
        prisonBars.animate().translationX(0).setDuration(2000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(PrisonerEncounter.this);
                db = dbHelper.getWritableDatabase();
                cupboard().withDatabase(db).put(prisoner);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent intent = new Intent(PrisonerEncounter.this, CellBlock.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private int getPhoneWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return width;
    }
}
