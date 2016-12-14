package ihm.com.ecolna;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ihm.com.ecolna.model.Etudiant;
import ihm.com.ecolna.tools.IRequest;
import ihm.com.ecolna.tools.WebService;

public class MainActivity extends AppCompatActivity {
    EditText mail = null;
    EditText key = null;
    TextView cnx = null;

    private String TAG = MainActivity.class.getSimpleName();

    private List<Etudiant> le ;
    private ProgressDialog pDialog;


    private String email;
    private String password;
    private boolean isValid = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mail = (EditText) findViewById(R.id.Email);
        key = (EditText) findViewById(R.id.key);
        cnx = (TextView) findViewById(R.id.db);
        le = new ArrayList<Etudiant>();


    }


    public void authenticate(View view) {


        email =mail.getText().toString();
        password = key.getText().toString();



            if (mail.getText().toString().length() == 0) mail.setError("l'Email est obligatoire !");

            if (key.getText().toString().length() == 0 && mail.getText().toString().length() != 0)
                    key.setError("le mot de passe est obligatoire !");
                else
                {
                    new GetStudents().execute();
                    if(isValid)
                    {
                        Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    }

                    else  key.setError("erreur !");
                }
            }


    public void inscrire(View view) {
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_deconnexion) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class GetStudents extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            WebService ws = new WebService();
            try {
                le = ws.getStudents(IRequest.getAllStudents);
                int i =0;
                if(le.isEmpty()) runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Aucune donnée dans la base ",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

                while (i<le.size())
                {
                    if(!email.equals(le.get(i).getEmail()) && !password.equals(le.get(i).getPassword()))
                    {
                        i++;
                        if(i>le.size())
                        {
                            isValid=false;
                        }
                    }

                    else
                    {
                       i=le.size()+1;
                       isValid=true;
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();


        }
    }
}


