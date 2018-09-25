package jobs.vmware.com.remote_trigger.model;

/**
 * Created by amedishetti on 16-05-2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllFoldersReturn {

    @SerializedName("folderInfo")
    @Expose
    private List<FolderInfo> folderInfo = null;
    @SerializedName("folderInfoSpecified")
    @Expose
    private Boolean folderInfoSpecified;

    public List<FolderInfo> getFolderInfo() {
        return folderInfo;
    }

    public void setFolderInfo(List<FolderInfo> folderInfo) {
        this.folderInfo = folderInfo;
    }

    public Boolean getFolderInfoSpecified() {
        return folderInfoSpecified;
    }

    public void setFolderInfoSpecified(Boolean folderInfoSpecified) {
        this.folderInfoSpecified = folderInfoSpecified;
    }

}