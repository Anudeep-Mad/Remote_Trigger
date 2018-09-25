package jobs.vmware.com.remote_trigger.DAO;

import android.app.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jobs.vmware.com.remote_trigger.MainActivity;
import jobs.vmware.com.remote_trigger.R;
import jobs.vmware.com.remote_trigger.model.LoginPojo;
import jobs.vmware.com.remote_trigger.model.LoginReturn;
import jobs.vmware.com.remote_trigger.model.Repo;
import jobs.vmware.com.remote_trigger.rest.InformaticaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amedishetti on 31-05-2018.
 */

public class Repository_popup extends AppCompatActivity {
    private String TAG = "Add Repository Popup", response_text;
    static int status;
    EditText username_popup, password_popup;
    Button save_test;
    LoginPojo loginPojo = new LoginPojo();
    Set<String> env_details= new HashSet();
    private static final String DATABASE_NAME = "repo_db";
    private RepoDatabase repoDatabase;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_layout);
        username_popup = (EditText) findViewById(R.id.username_popup);
        password_popup = (EditText) findViewById(R.id.password_popup);
        save_test = (Button) findViewById(R.id.test_popup);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        /*repoDatabase = Room.databaseBuilder(getApplicationContext() ,
                RepoDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build() ;*/
        repoDatabase = RepoDatabase.getInstance(getApplicationContext());


        loginPojo.setRepository_name(getIntent().getStringExtra("repository_name"));
        loginPojo.setDomain_name(getIntent().getStringExtra("domain_name"));
        loginPojo.setRepository(getIntent().getStringExtra("environment_name"));


        save_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask mt = new MyTask();
                mt.execute();
            }
        });


    }

    class MyTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            loadData();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.d("status in Post Execute", status + " ");
            if (status == 200) {


            }
        }

        public void loadData() {

            loginPojo.setUsername(username_popup.getText().toString());
            loginPojo.setPassword(password_popup.getText().toString());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(InformaticaService.base_url)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();

            InformaticaService api = retrofit.create(InformaticaService.class);

            Call<LoginReturn> call = api.login(loginPojo);
            call.enqueue(new Callback<LoginReturn>() {
                @Override
                public void onResponse(Call<LoginReturn> call, Response<LoginReturn> response) {
                    status = response.code();
                    if (status == 200) {

                            insertRepo(loginPojo);

                       /* SharedPreferences sharedPreferences1 = getSharedPreferences("spinner_repository",MODE_PRIVATE);
                        Set<String> fetch = sharedPreferences1.getStringSet("environments",null);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        if(fetch==null){
                            fetch = new HashSet<>();
                            fetch.add(loginPojo.getRepository());
                            editor1.putStringSet("environments", fetch);
                            editor1.commit();
                        }
                        else {
                            fetch.add(loginPojo.getRepository());
                            editor1.putStringSet("environments", fetch);
                            editor1.commit();
                        }*/

                        /*SharedPreferences sharedPreferences = getSharedPreferences("repository_details",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        env_details.add(loginPojo.getRepository());
                        env_details.add(loginPojo.getRepository_name());
                        env_details.add(loginPojo.getDomain_name());
                        editor.putStringSet(loginPojo.getRepository(),env_details);
                        editor.commit();*/
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please check your details and retry", Toast.LENGTH_LONG);
                        toast.show();
                        startActivity(new Intent(getApplicationContext(), AddRepository.class));
                    }
                }

                @Override
                public void onFailure(Call<LoginReturn> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
                }
            });


        }

    }

    public void insertRepo(final LoginPojo loginPojo){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Repo repo = new Repo() ;
                repo.setRepository_name(loginPojo.getRepository_name());
                repo.setDomain_name(loginPojo.getDomain_name());
                repo.setEnv(loginPojo.getRepository());
                repoDatabase.repoDao().insert(repo);
            }
        }).start();
    }
}
