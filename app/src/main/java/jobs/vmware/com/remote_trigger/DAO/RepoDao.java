package jobs.vmware.com.remote_trigger.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import jobs.vmware.com.remote_trigger.model.Repo;

/**
 * Created by amedishetti on 07-06-2018.
 */
@Dao
public interface RepoDao {

    @Query("SELECT * from repo ORDER BY env ASC")
   public LiveData<List<Repo>> getAllRepos();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Repo repo);

    @Query("SELECT * FROM repo ORDER BY env ASC")
    public List<Repo> getRepos();
/*    @Query("DELETE FROM repo")
    void deleteAll(); */

    @Query("SELECT env,repository_name,domain_name from repo where env like:env")
    public Repo getRepo(String env);

}
