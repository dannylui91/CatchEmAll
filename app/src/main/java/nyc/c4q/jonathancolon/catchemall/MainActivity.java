package nyc.c4q.jonathancolon.catchemall;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.jonathancolon.catchemall.models.Prisoner;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get instance of PrisonerDatabaseHelper
        PrisonerDatabaseHelper dbHelper = PrisonerDatabaseHelper.getInstance(this);

        //remember PrisonerDatabaseHelper helps create and manage our database connection
        //instantiate the SQLiteDatabase db with dbHelper
        db = dbHelper.getWritableDatabase();

        //create a Prisoner object and put it in Prisoner table
        Prisoner prisoner = new Prisoner("Jon", Calendar.getInstance().getTimeInMillis());
        cupboard().withDatabase(db).put(prisoner);

        //retrieve a list of objects from the database (perform a select all query in the database)
        //normally in SQL it would looke like SELECT * FROM Prisoner; but cupboard does it all for you
        List<Prisoner> prisoners = selectAllPrisoners();
        System.out.println(prisoners);

        //delete all rows/records from Prisoner table
        deletAllPrisoners();
    }

    private List<Prisoner> selectAllPrisoners() {
        List<Prisoner> prisoners = new ArrayList<>();
        try {
            QueryResultIterable<Prisoner> itr = cupboard().withDatabase(db).query(Prisoner.class).query();
            for (Prisoner prisoner : itr) {
                prisoners.add(prisoner);
            }
            itr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prisoners;
    }

    private void deletAllPrisoners() {
        //specify database table to delete
        String databaseTable = "Prisoner";
        db.delete(databaseTable, null, null);
    }
}
