package jobs.vmware.com.remote_trigger.DAO;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jobs.vmware.com.remote_trigger.R;
import jobs.vmware.com.remote_trigger.model.InstanceAdapter;
import jobs.vmware.com.remote_trigger.model.InstanceInfo;
import jobs.vmware.com.remote_trigger.model.InstancePojo;
import jobs.vmware.com.remote_trigger.rest.InformaticaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amedishetti on 23-05-2018.
 */

public class InstanceActivity  extends AppCompatActivity{

    private RecyclerView recyclerView;
    private String  TAG = "FolderActivity";
    public String[] heroes;

    public List<InstanceInfo> instances = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instance_layout);


        recyclerView = (RecyclerView) findViewById(R.id.instance_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InformaticaService.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        InformaticaService api = retrofit.create(InformaticaService.class);
        final InstancePojo instancePojo = new InstancePojo();
        instancePojo.setFolder_name(getIntent().getExtras().getString("folder_name"));
        instancePojo.setLocalDepth(1);
        instancePojo.setValid(true);
        instancePojo.setWorkflow_name(getIntent().getExtras().getString("workflow_name"));

        Call<List<InstanceInfo>> call = api.getInstances(instancePojo);
        call.enqueue(new Callback<List<InstanceInfo>>() {
            @Override
            public void onResponse(Call<List<InstanceInfo>> call, retrofit2.Response<List<InstanceInfo>> response) {
                Log.d(TAG,response.code()+" ");
                instances=response.body();
                Log.d(TAG, instances.size() + "");

                //Creating an String array for the ListView
                heroes = new String[instances.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < instances.size(); i++) {
                    heroes[i] = instances.get(i).getName();
                    Log.d(TAG, heroes[i]);
                }
                recyclerView.setAdapter(new InstanceAdapter(instances,InstanceActivity.this));


            }

            @Override
            public void onFailure(Call<List<InstanceInfo>> call, Throwable t) {

            }
        });
       /* return heroList;
    }*/


    }
}