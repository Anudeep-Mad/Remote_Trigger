package jobs.vmware.com.remote_trigger.DAO;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jobs.vmware.com.remote_trigger.R;
import jobs.vmware.com.remote_trigger.model.FolderAdapter;
import jobs.vmware.com.remote_trigger.model.FolderInfo;
import jobs.vmware.com.remote_trigger.rest.InformaticaDetailsRequest;
import jobs.vmware.com.remote_trigger.rest.InformaticaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amedishetti on 23-05-2018.
 */

public class FolderActivity  extends AppCompatActivity{

    private RecyclerView recyclerView;
    private String  TAG = "FolderActivity";
    public String[] heroes;
    public static List<FolderInfo> heroList = new ArrayList<>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.folder_toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.workflows_button);


        recyclerView = (RecyclerView) findViewById(R.id.folders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new MyTask().execute();

        //Creating an String array for the ListView



    }

    public class MyTask extends AsyncTask {
        @Override
        public Object doInBackground(Object[] objects){
            heroList = InformaticaDetailsRequest.getFolders();

            return null;
        }

        @Override
        public void onPostExecute(Object o){

            heroes = new String[heroList.size()];

            //looping through all the heroes and inserting the names inside the string array
            for (int i = 0; i < heroList.size(); i++) {
                heroes[i] = heroList.get(i).getName();
                Log.d(TAG, heroes[i]);
            }
            recyclerView.setAdapter(new FolderAdapter(heroList, FolderActivity.this));

        }
    }
}