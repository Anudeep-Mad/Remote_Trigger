package jobs.vmware.com.remote_trigger.model;

/**
 * Created by amedishetti on 16-05-2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FolderInfo {

    @SerializedName("name")
    @Expose
    private String name;

    public FolderInfo(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
