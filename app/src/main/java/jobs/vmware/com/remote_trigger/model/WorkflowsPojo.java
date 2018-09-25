package jobs.vmware.com.remote_trigger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amedishetti on 28-05-2018.
 */

public class WorkflowsPojo {

    @SerializedName("foldername")
    @Expose
    private String foldername;
    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public WorkflowsPojo(String foldername){
        this.foldername = foldername;
    }



}
