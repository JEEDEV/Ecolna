package ihm.com.ecolna;

import android.app.ProgressDialog;
import android.content.Context;
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
import java.util.List;

import ihm.com.ecolna.model.Etudiant;
import ihm.com.ecolna.tools.IRequest;
import ihm.com.ecolna.tools.WebService;

public class InscriptionActivity extends AppCompatActivity {
    EditText mail = null;
    EditText key = null;
    EditText nom = null;
    EditText prenom = null;
    EditText ecole = null;
    private String TAG = InscriptionActivity.class.getSimpleName();
    private boolean isValid = false ;
    private List<Etudiant> le ;
     ProgressDialog pDialog;

    Context mcontext;
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

        else
        {
            new PostData().execute("http://192.168.1.2:1000/api/etudiant");
            Intent intent = new Intent(this, MainActivity.class);
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
