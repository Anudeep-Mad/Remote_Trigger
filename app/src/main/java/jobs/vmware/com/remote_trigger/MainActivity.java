package jobs.vmware.com.remote_trigger;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import jobs.vmware.com.remote_trigger.DAO.AddRepository;
import jobs.vmware.com.remote_trigger.DAO.FolderActivity;
import jobs.vmware.com.remote_trigger.DAO.RepoDatabase;
import jobs.vmware.com.remote_trigger.model.LoginPojo;
import jobs.vmware.com.remote_trigger.model.Repo;
import jobs.vmware.com.remote_trigger.rest.InformaticaDetailsRequest;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText username, password;
    String repo_list_value;
    Spinner repo_list;
    Button connect;
    LoginPojo loginPojo = new LoginPojo();
    private static final String TAG = "MainActivity";
    public static List<String> categories = new ArrayList<>();
    //private static final String DATABASE_NAME = "repo_db";
    private RepoDatabase repoDatabase;
    private LifecycleRegistry mLifecycleRegistry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        repo_list = (Spinner) findViewById(R.id.repo_list);
        connect = (Button) findViewById(R.id.connect);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        repoDatabase = RepoDatabase.getInstance(getApplicationContext());
        //
        // LoadRepos();

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Repo> repo_ini = repoDatabase.repoDao().getRepos();
                if (repo_ini.size() > 0) {
                    for (Repo repo : repo_ini) {
                        if (!categories.contains(repo.getEnv()))
                            categories.add(repo.getEnv());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, categories);
                    dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    repo_list.setAdapter(dataAdapter);
                }
            }
        }).start();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(getApplicationContext(), AddRepository.class);
                startActivity(intent);
                //startActivity(new Intent(MainActivity.this, AddRepository.class));

            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyTask mt = new MyTask();
                mt.execute();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class MyTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            loadData();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Intent intent = new Intent(MainActivity.this, FolderActivity.class);
            startActivity(intent);

        }
    }

    public void loadData() {

        repo_list_value = repo_list.getSelectedItem().toString();
        Log.d("rspinner value", repo_list_value);
        Repo data = repoDatabase.repoDao().getRepo(repo_list_value);
        loginPojo.setUsername(username.getText().toString());
        loginPojo.setPassword(password.getText().toString());
        loginPojo.setRepository(data.getEnv());
        loginPojo.setRepository_name(data.getRepository_name());
        loginPojo.setDomain_name(data.getDomain_name());
        int code = InformaticaDetailsRequest.Login(loginPojo);


    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    /* class LoadSpinner extends AsyncTask {
         @Override*/
    protected void LoadRepos() {

        List<Repo> repo_ini = repoDatabase.repoDao().getRepos();
        if (repo_ini.size() > 0) {
            for (Repo repo : repo_ini) {
                if (!categories.contains(repo))
                    categories.add(repo.getEnv());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            repo_list.setAdapter(dataAdapter);

        }
    }
}