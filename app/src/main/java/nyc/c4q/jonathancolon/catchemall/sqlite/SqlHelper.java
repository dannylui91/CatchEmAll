package nyc.c4q.jonathancolon.catchemall.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Danny on 12/14/2016.
 */

public class SqlHelper {

    public SqlHelper() {
    }

    public static List<Prisoner> selectAllPrisoners(SQLiteDatabase db) {
        List<Prisoner> prisoners = new ArrayList<>();
        try {
            QueryResultIterable<Prisoner> itr = cupboard().withDatabase(db).query(Prisoner.class).query();
            for (Prisoner prisoner : itr) {
                prisoners.add(prisoner);
            }
            itr.close();
        } catch (Exception e) {
            Log.e("MainActivity", "selectAllCats: ", e);
        }
        return prisoners;
    }

    public static Prisoner getRandomPrisoner(SQLiteDatabase db) {
        Random rand = new Random();
        Prisoner prisoner = cupboard().withDatabase(db).get(Prisoner.class, rand.nextInt(20));
        return prisoner;
    }
}
