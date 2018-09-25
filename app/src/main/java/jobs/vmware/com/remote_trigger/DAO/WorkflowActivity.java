package jobs.vmware.com.remote_trigger.DAO;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jobs.vmware.com.remote_trigger.MainActivity;
import jobs.vmware.com.remote_trigger.R;
import jobs.vmware.com.remote_trigger.model.WorkflowAdapter;
import jobs.vmware.com.remote_trigger.model.WorkflowInfo;
import jobs.vmware.com.remote_trigger.model.WorkflowsPojo;
import jobs.vmware.com.remote_trigger.rest.InformaticaDetailsRequest;


/**
 * Created by amedishetti on 28-05-2018.
 */

public class WorkflowActivity extends AppCompatActivity{

    private RecyclerView workflow_recyclerView;
    private String  TAG = "FolderActivity";
    public static List<WorkflowInfo> workflows = new ArrayList<>();
    public String folder_name;
    WorkflowsPojo workflowsPojo;
    ProgressDialog pdia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workflow_layout);

        workflow_recyclerView =  findViewById(R.id.workflow_recycler);
        workflow_recyclerView.setHasFixedSize(true);
        workflow_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        folder_name =getIntent().getStringExtra("foldername");
        workflowsPojo = new WorkflowsPojo(folder_name);

        FetchTask ft = new FetchTask();
        ft.execute();
    }

    class FetchTask extends AsyncTask {

        @Override
        protected  void  onPreExecute(){

        }
        @Override
        protected Object doInBackground(Object[] objects) {
            workflows = InformaticaDetailsRequest.getWorkflows(workflowsPojo);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            for (int i = 0; i < workflows.size(); i++) {
                Log.d(TAG,workflows.get(i).getName());
            }
            workflow_recyclerView.setAdapter(new WorkflowAdapter(workflows,getApplicationContext(),folder_name));
        }

    }
}





