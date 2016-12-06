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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class InscriptionActivity extends AppCompatActivity {
    EditText mail = null;
    EditText key = null;
    EditText nom = null;
    EditText prenom = null;
    EditText ecole = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_inscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nom = (EditText)findViewById(R.id.nom);
        prenom = (EditText)findViewById(R.id.prenom);
        mail = (EditText)findViewById(R.id.mail);
        key= (EditText)findViewById(R.id.password);
        ecole=(EditText)findViewById(R.id.ecole);
        new PostData().execute("http://localhost:1000/api/user");
    }



    public void connexion(View view){
        if(nom.getText().toString().equals(""))
            nom.setError("Veuillez saisir votre nom");
        else if((prenom.getText().toString().equals("")) && !(nom.getText().toString().equals("")))

            prenom.setError("Veuillez saisir votre prénom");
        else if((mail.getText().toString().equals(""))&& !(nom.getText().toString().equals("")) && !(prenom.getText().toString().equals("")))
            mail.setError("Veuillez saisir votre Email");
        else if((key.getText().toString().equals(""))&& !(nom.getText().toString().equals(""))
                && !(prenom.getText().toString().equals(""))&& !(mail.getText().toString().equals("")))
            key.setError("Veuillez saisir votre mot de passe");
        else if((ecole.getText().toString().equals(""))&& !(nom.getText().toString().equals("")) && !(prenom.getText().toString().equals(""))
                && !(mail.getText().toString().equals(""))&& !(key.getText().toString().equals("")))
            ecole.setError("Veuillez saisir votre institut");

        else if (!(nom.getText().toString().equals(""))&& !(prenom.getText().toString().equals(""))&& !(mail.getText().toString().equals(""))
                && !(key.getText().toString().equals(""))&& !(ecole.getText().toString().equals(""))){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
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
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class PostData extends AsyncTask<String, Void, String> {

        StringBuilder resultat = new StringBuilder();
        @Override
        protected String doInBackground(String... params) {
            try{
                return postData(params[0]);
            }catch (IOException e){
                return "network error";
            }catch (JSONException ex){
                return "data invalid";
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String resultat) {
            super.onPostExecute(resultat);
        }

        private String postData(String urlPath) throws IOException, JSONException {
            BufferedReader bufferedReader=null;
            BufferedWriter bufferedWriter=null;
            try {
                //create data to send to server
                JSONObject data = new JSONObject();
                data.put("prenom", prenom.getText().toString());
                data.put("nom", nom.getText().toString());
                data.put("institut", ecole.getText().toString());
                data.put("email", mail.getText().toString());
                data.put("password", key.getText().toString());

                //initialize and config request , then connect to server
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("content-Type", "application/json");
                urlConnection.connect();

                //write data into server
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(data.toString());
                bufferedWriter.flush();

                //read data reponse from server
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    resultat.append(line).append("\n");
                }
            }finally {
                if(bufferedReader !=null){
                    bufferedReader.close();
                }
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            }
            return resultat.toString();
        }
    }


}
