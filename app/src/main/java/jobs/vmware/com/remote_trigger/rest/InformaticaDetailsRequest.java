package jobs.vmware.com.remote_trigger.rest;

import java.io.IOException;
import java.util.List;

import jobs.vmware.com.remote_trigger.model.FolderInfo;
import jobs.vmware.com.remote_trigger.model.LoginPojo;
import jobs.vmware.com.remote_trigger.model.LoginReturn;
import jobs.vmware.com.remote_trigger.model.WorkflowInfo;
import jobs.vmware.com.remote_trigger.model.WorkflowsPojo;
import retrofit2.Call;

/**
 * Created by amedishetti on 9/25/2018.
 */

public class InformaticaDetailsRequest {
    private static InformaticaService informaticaService;

    static{
        informaticaService = InformaticaServiceGenerator.getRestClient().create(InformaticaService.class);
    }

    public static int Login(final LoginPojo loginPojo){
        final String TAG = "WSH login";

        Call<LoginReturn> call = informaticaService.login(loginPojo);
        try{
            return call.execute().code();
        }catch (IOException e){
            return 0;
        }
    }

    public static List<FolderInfo> getFolders(){
        final String TAG="WSH fetching folders";

        Call<List<FolderInfo>> call = informaticaService.getFolders();
        try{
            return call.execute().body();
        }catch (IOException e){
            return null;
        }
    }

    public static List<WorkflowInfo> getWorkflows(final WorkflowsPojo workflowsPojo){

        Call<List<WorkflowInfo>> call = informaticaService.getWorkflows(workflowsPojo);
        try{
            return call.execute().body();
        }catch(IOException e){
            return null;
        }

    }

}
