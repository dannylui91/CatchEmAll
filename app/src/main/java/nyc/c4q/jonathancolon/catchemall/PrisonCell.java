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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class PrisonCell extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key_third_activity";

    private FloatingActionButton inspectLockBtn;
    private ImageView prisonBars;
    private TextView nameLayer;

    private Prisoner prisoner;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prison_cell);

        prisoner = (Prisoner) getIntent().getExtras().getSerializable(PRISONER_KEY);

        initViews();
        setViews();

        openCellAnimation();

        inspectLockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inspectLockBtn.setClickable(false);
                closeCellAnimation();
            }
        });
    }

    private void initViews() {
        inspectLockBtn = (FloatingActionButton) findViewById(R.id.fab_inspect_lock);
        prisonBars = (ImageView) findViewById(R.id.prison_bars);
        nameLayer = (TextView) findViewById(R.id.prisoner_info);
    }

    private void setViews() {
        PrisonerHelper prisonerHelper = new PrisonerHelper(this);
        prisonerHelper.updatePrisonerSpriteView(prisoner); //draw prisoner sprite

        DateFormat calanderDateformatter = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat timeDateFormatter = new SimpleDateFormat("hh:mm:ss a");

        String imprisonDateFormatted = calanderDateformatter.format(new Date(prisoner.getLastInspected()));
        String lastInspectedTimeFormatted = timeDateFormatter.format(new Date(prisoner.getLastInspected()));

        String prisonerName = prisoner.getFirstName() + " " + prisoner.getLastName();
        nameLayer.setText(prisonerName + "\n\n" + "Last Lock Inspection: \n" + imprisonDateFormatted + "\n" + lastInspectedTimeFormatted);
    }

    private void openCellAnimation() {
        prisonBars.animate().translationX(-getPhoneWidth()).setDuration(1500);
    }

    private void closeCellAnimation() {
        prisonBars.animate().translationX(0).setDuration(1500).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(PrisonCell.this);
                db = dbHelper.getWritableDatabase();
                long currentTime = System.currentTimeMillis();
                prisoner.setLastInspected(currentTime);
                cupboard().withDatabase(db).put(prisoner);
                setViews();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent intent = new Intent(PrisonCell.this, CellBlock.class);
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
