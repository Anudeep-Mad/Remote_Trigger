package jobs.vmware.com.remote_trigger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amedishetti on 25-05-2018.
 */

public class LoginReturn {


    public String getLoginReturn() {
        return loginReturn;
    }

    public void setLoginReturn(String loginReturn) {
        this.loginReturn = loginReturn;
    }

    @SerializedName("loginReturn")
    @Expose
    private String loginReturn;


}
