package nyc.c4q.jonathancolon.catchemall.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Danny on 12/14/2016.
 */

public class PrisonerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "prisoner.db";
    private static final int DATABASE_VERSION = 1;
    private static PrisonerDatabaseHelper instance;

    public static synchronized PrisonerDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PrisonerDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public PrisonerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        cupboard().register(Prisoner.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
