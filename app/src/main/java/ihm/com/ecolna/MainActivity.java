package ihm.com.ecolna;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText mail = null;
    EditText key = null;
    TextView verif = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mail = (EditText)findViewById(R.id.Email);
        key = (EditText)findViewById(R.id.key);
        verif = (TextView)findViewById(R.id.verif);
    }


    public void authenticate(View view) {

        if((mail.getText().toString().equals("maha")) && (key.getText().toString().equals("bilel"))) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else
            verif.setText("Email ou mot de passe est invalide");

    }
    public void inscrire(View view){
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
}
