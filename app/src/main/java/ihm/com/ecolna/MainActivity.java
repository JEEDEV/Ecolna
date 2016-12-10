package ihm.com.ecolna;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText mail = null;
    EditText key = null;
    TextView cnx = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mail = (EditText) findViewById(R.id.Email);
        key = (EditText) findViewById(R.id.key);
        cnx = (TextView) findViewById(R.id.db);
        new GetData().execute("http://192.168.1.2:1000/api/etudiant");
    }


    public void authenticate(View view) {


        if ((mail.getText().toString().equals("maha")) && (key.getText().toString().equals("bilel"))) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {

            if (mail.getText().toString().length() == 0)
                mail.setError("l'Email est obligatoire !");

            else {
                if (key.getText().toString().length() == 0 && mail.getText().toString().length() != 0)
                    key.setError("le mot de passe est obligatoire !");
                else key.setError("Email ou mot de passe est invalide");
            }


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

    class GetData extends AsyncTask<String, Void, String> {
        StringBuilder resultat = new StringBuilder();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            BufferedReader bufferedReader = null;
            try {
                //initialize and config request , then connect to server
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("content-Type", "application/json");
                urlConnection.connect();

                //read data reponse from server
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    resultat.append(line).append("\n");
                }
            } catch (IOException ex) {
                return "network error";
            }
            return resultat.toString();

        }

        @Override
        protected void onPostExecute(String resultat) {
            super.onPostExecute(resultat);

            //set data to textview
            cnx.setText(resultat);
        }

     /*   private String getData(String urlPath) throws IOException {
            BufferedReader bufferedReader = null;
            try {
                //initialize and config request , then connect to server
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("content-Type", "application/json");
                urlConnection.connect();

                //read data reponse from server
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    resultat.append(line).append("\n");

                }
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            return resultat.toString();
        }*/
    }
}


