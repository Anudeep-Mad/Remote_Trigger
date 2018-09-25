package jobs.vmware.com.remote_trigger.rest;

import java.util.List;

import jobs.vmware.com.remote_trigger.model.FolderInfo;
import jobs.vmware.com.remote_trigger.model.InstanceInfo;
import jobs.vmware.com.remote_trigger.model.InstancePojo;
import jobs.vmware.com.remote_trigger.model.LoginPojo;
import jobs.vmware.com.remote_trigger.model.LoginReturn;
import jobs.vmware.com.remote_trigger.model.WorkflowInfo;
import jobs.vmware.com.remote_trigger.model.WorkflowsPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by amedishetti on 22-05-2018.
 */

public interface InformaticaService {

    String base_url = "http://10.148.238.18:8090/";
    //String base_url = "http://10.142.176.69:8090/";

    @POST("folders")
    Call<List<FolderInfo>> getFolders();

    @POST("login")
    Call<LoginReturn> login(@Body LoginPojo loginPojo);

    @POST("workflows")
    //Call<List<WorkflowInfo>> getWorkflows(@Field("folder_name") String foldername);
    Call<List<WorkflowInfo>> getWorkflows(@Body WorkflowsPojo workflowsPojo);

    @POST("instances")
    Call<List<InstanceInfo>> getInstances(@Body InstancePojo instancePojo);
}
