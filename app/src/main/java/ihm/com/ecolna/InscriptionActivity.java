package ihm.com.ecolna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InscriptionActivity extends AppCompatActivity {
    EditText mail = null;
    EditText key = null;
    EditText nom = null;
    EditText prenom = null;
    EditText ecole = null;
    TextView verif = null;
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
        verif = (TextView)findViewById(R.id.verif);
    }



    public void connexion(View view){
        if(!((nom.getText().toString().equals(""))||(prenom.getText().toString().equals(""))
                ||(mail.getText().toString().equals(""))||(key.getText().toString().equals(""))||(ecole.getText().toString().equals("")))) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else{
            verif.setText("Veuillez saisir le formulaire");
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
}
