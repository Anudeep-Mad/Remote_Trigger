package jobs.vmware.com.remote_trigger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amedishetti on 01-06-2018.
 */

public class InstancePojo {

    @SerializedName("workflow_name")
    @Expose
    private String workflow_name;

    @SerializedName("folder_name")
    @Expose
    private String folder_name;

    @SerializedName("isValid")
    @Expose
    private boolean isValid;

    @SerializedName("localDepth")
    @Expose
    private int localDepth;

    public String getWorkflow_name() {
        return workflow_name;
    }

    public void setWorkflow_name(String workflow_name) {
        this.workflow_name = workflow_name;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getLocalDepth() {
        return localDepth;
    }

    public void setLocalDepth(int localDepth) {
        this.localDepth = localDepth;
    }
}
