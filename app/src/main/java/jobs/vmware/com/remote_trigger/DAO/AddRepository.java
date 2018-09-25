package jobs.vmware.com.remote_trigger.DAO;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jobs.vmware.com.remote_trigger.R;


/**
 * Created by amedishetti on 4/27/2018.
 */

public class AddRepository extends AppCompatActivity {

    private String TAG = "Add Repository";
    static final int ADD_REPOSITORY=1;
    EditText enviroment_name, repository_name,domain_name;
    Button add;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_repository);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        enviroment_name = (EditText) findViewById(R.id.environment_name);
    repository_name = (EditText) findViewById(R.id.repository_name);
    domain_name = (EditText) findViewById(R.id.domain_name);
    add = (Button) findViewById(R.id.add_repository);



    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(TextUtils.isEmpty(enviroment_name.getText())||
                    TextUtils.isEmpty(repository_name.getText())||TextUtils.isEmpty(domain_name.getText())){
                Toast toast = Toast.makeText(getApplicationContext(),"Please make sure all the fields are filled in",Toast.LENGTH_LONG);
                toast.show();
            }
            else{
                Intent intent = new Intent(getApplicationContext(),Repository_popup.class);
                intent.putExtra("environment_name",enviroment_name.getText().toString());
                intent.putExtra("repository_name",repository_name.getText().toString());
                intent.putExtra("domain_name",domain_name.getText().toString());
                startActivityForResult(intent,ADD_REPOSITORY);
            }


        }
    });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_repository_menu, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == ADD_REPOSITORY) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact

                // Do something with the phone number...
            }
        }
    }



}




