package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    private TextView nameLayer;
    private Button lockBtn;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prison_cell);
        nameLayer = (TextView) findViewById(R.id.prisoner_info);
        lockBtn = (Button) findViewById(R.id.inspect_lock_button);

        Intent intent = getIntent();
        Prisoner prisoner = (Prisoner) intent.getExtras().getSerializable(PRISONER_KEY);

        setViews(prisoner);
        lockBtn.setOnClickListener(onClickInspectLock(prisoner));
    }

    private View.OnClickListener onClickInspectLock(final Prisoner prisoner) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(PrisonCell.this);
                db = dbHelper.getWritableDatabase();
                prisoner.setLastInspected(System.currentTimeMillis());
                cupboard().withDatabase(db).put(prisoner);
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
