package jobs.vmware.com.remote_trigger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amedishetti on 01-06-2018.
 */

public class InstanceInfo {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isChildTaskSpecified() {
        return childTaskSpecified;
    }

    public void setChildTaskSpecified(boolean childTaskSpecified) {
        this.childTaskSpecified = childTaskSpecified;
    }

    public String getChildTask() {
        return childTask;
    }

    public void setChildTask(String childTask) {
        this.childTask = childTask;
    }

    @SerializedName("name")

    @Expose
    private String name;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("isValid")
    @Expose
    private boolean isValid;

    @SerializedName("childTaskSpecified")
    @Expose
    private boolean childTaskSpecified;

    @SerializedName("childTask")
    @Expose
    private String childTask;

}
