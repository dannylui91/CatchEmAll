package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;

import static android.view.View.GONE;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class PrisonCell extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key_third_activity";
    private TextView nameLayer;
    private FloatingActionButton inspectLockBtn;
    Animation openCellAnimation;

    private SQLiteDatabase db;
    private ImageView prisonBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prison_cell);
        nameLayer = (TextView) findViewById(R.id.prisoner_info);
        inspectLockBtn = (FloatingActionButton) findViewById(R.id.fab_inspect_lock);
        prisonBars = (ImageView) findViewById(R.id.prison_bars);
        openCellAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.open_cell);

        Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                prisonBars.startAnimation(openCellAnimation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                prisonBars.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        openCellAnimation.setAnimationListener(animationListener);
        animationListener.onAnimationStart(openCellAnimation);


        Intent intent = getIntent();
        Prisoner prisoner = (Prisoner) intent.getExtras().getSerializable(PRISONER_KEY);

        setViews(prisoner);
        inspectLockBtn.setOnClickListener(onClickInspectLock(prisoner));
    }



    private View.OnClickListener onClickInspectLock(final Prisoner prisoner) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(PrisonCell.this);
                db = dbHelper.getWritableDatabase();
                long currentTime = System.currentTimeMillis();
                prisoner.setLastInspected(currentTime);
                cupboard().withDatabase(db).put(prisoner);
                setViews(prisoner);
                CellBlock cell = (CellBlock) CellBlock.activity;
                cell.refreshRecyclerView();
            }
        };
    }

    private void setViews(Prisoner prisoner) {
        PrisonerHelper prisonerHelper = new PrisonerHelper(this);
        prisonerHelper.updatePrisonerSpriteView(prisoner);

        String prisonerName = prisoner.getFirstName() + " " + prisoner.getLastName();

        DateFormat calanderDateformatter = new SimpleDateFormat("MM/dd/yyyy");
        String imprisonDateFormatted = calanderDateformatter.format(new Date(prisoner.getLastInspected()));

        SimpleDateFormat timeDateFormatter = new SimpleDateFormat("hh:mm:ss a");
        String lastInspectedTimeFormatted = timeDateFormatter.format(new Date(prisoner.getLastInspected()));

        nameLayer.setText(prisonerName + "\n" + imprisonDateFormatted + "\n" + lastInspectedTimeFormatted);
    }
}
