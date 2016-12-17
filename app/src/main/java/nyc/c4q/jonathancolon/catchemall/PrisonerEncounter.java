package nyc.c4q.jonathancolon.catchemall;

import android.animation.Animator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class PrisonerEncounter extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key";
    private Animation closeCellAnimation;
    private FloatingActionButton capturePrisonerButton;
    private static SQLiteDatabase db;
    private TextView nameLayer;
    private ImageView prisonBars;
    private ImageView encounterBackground;

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

        closeCellAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.close_cell);
        final Animation.AnimationListener animationListener = getAnimationListener();
        closeCellAnimation.setAnimationListener(animationListener);

        capturePrisonerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationListener.onAnimationStart(closeCellAnimation);
                PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(PrisonerEncounter.this);
                db = dbHelper.getWritableDatabase();
                cupboard().withDatabase(db).put(prisoner);
            }
        });
    }

    private Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                prisonBars.animate().translationX(0).setDuration(2000).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Intent intent = new Intent(PrisonerEncounter.this, CellBlock.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    private void initViews() {
        nameLayer = (TextView)findViewById(R.id.prisoner_name);
        capturePrisonerButton = (FloatingActionButton) findViewById(R.id.fab_capture_prisoner);
        prisonBars = (ImageView) findViewById(R.id.encounter_prison_bars);
        prisonBars.setTranslationX(-getPhoneWidth()); //sets the prison bars to the left of the screen on load
        encounterBackground = (ImageView) findViewById(R.id.encounter_image);
        Picasso.with(this).load(R.drawable.background_encounter_happy).fit().into(encounterBackground);
    }

    private int getPhoneWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return width;
    }

}
