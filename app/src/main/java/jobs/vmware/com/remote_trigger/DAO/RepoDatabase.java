package jobs.vmware.com.remote_trigger.DAO;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import jobs.vmware.com.remote_trigger.model.Repo;

/**
 * Created by amedishetti on 07-06-2018.
 */

@Database(entities={Repo.class},version =1, exportSchema = false)
public abstract class RepoDatabase extends RoomDatabase {

    private static RepoDatabase INSTANCE;
    private static final String DATABASE_NAME = "repo_db";

    public abstract RepoDao repoDao();

    public static RepoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RepoDatabase.class,DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }


}