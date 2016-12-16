package nyc.c4q.jonathancolon.catchemall;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.service.MyAlarmReceiver;
import nyc.c4q.jonathancolon.catchemall.service.MyService;
import nyc.c4q.jonathancolon.catchemall.sqlite.PrisonerDatabaseHelper;
import nyc.c4q.jonathancolon.catchemall.sqlite.SqlHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class CellBlock extends AppCompatActivity implements PrisonerAdapter.Listener {
    private static final String TAG = CellBlock.class.getName();
    private static PrisonerDatabaseHelper prisonerDatabaseHelper;
    private SQLiteDatabase db;
    private RecyclerView recyclerView;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellblock);
        this.activity = this;

        setupRecyclerView();
        refreshRecyclerView();

        if (!MyService.hasStarted) {
            System.out.println("CellBlock: starting service");
            startService();
        }
    }

    public void refreshRecyclerView() {
        PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(this);
        db = dbHelper.getWritableDatabase();
        List<Prisoner> prisoners = SqlHelper.selectAllPrisoners(db);
        PrisonerAdapter adapter = (PrisonerAdapter) recyclerView.getAdapter();
        adapter.setData(prisoners);
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new PrisonerAdapter(this));
    }

    public void startService() {
        // Sends a broadcast to MyAlarmReceiver that will start MyService
        IntentFilter filter = new IntentFilter(MyAlarmReceiver.ACTION);
        this.registerReceiver(new MyAlarmReceiver(), filter);

        Intent intent = new Intent(MyAlarmReceiver.ACTION);
        sendBroadcast(intent);
    }

    public static void onPrisonerCapture(Prisoner prisoner) {
        System.out.println("Capturing prisoner");
    }

    @Override
    public void onPrisonerClicked(Prisoner prisoner) {
        System.out.println("Clicked prisoner: " + prisoner.getFirstName() + " " + prisoner.getLastName());
        //logic after clicking one of the prisoners
        Intent intent = new Intent(this, PrisonCell.class);
        intent.putExtra(PrisonCell.PRISONER_KEY, prisoner);

        startActivity(intent);
    }

    @Override
    public void onPrisonerLongClicked(Prisoner prisoner) {
        cupboard().withDatabase(db).delete(prisoner);
        refreshRecyclerView();
    }
}
