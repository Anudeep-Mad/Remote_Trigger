package jobs.vmware.com.remote_trigger.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amedishetti on 07-06-2018.
 */

@Entity
public class Repo {

    @PrimaryKey
    @NonNull
    private String env;
    private String repository_name;
    private String domain_name;

    public String getEnv() {
        return env;
    }

    public void setEnv(@NonNull String env) {
        this.env = env;
    }

    public String getRepository_name() {
        return repository_name;
    }

    public void setRepository_name(@NonNull String repository_name) {
        this.repository_name = repository_name;
    }
    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(@NonNull String domain_name) {
        this.domain_name = domain_name;
    }


}
