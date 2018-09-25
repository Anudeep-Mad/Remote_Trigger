package jobs.vmware.com.remote_trigger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amedishetti on 28-05-2018.
 */

public class WorkflowInfo {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isValid")
    @Expose
    private Boolean isValid;
    @SerializedName("folderName")
    @Expose
    private String folderName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

}
