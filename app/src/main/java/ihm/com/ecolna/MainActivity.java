package ihm.com.ecolna;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ihm.com.ecolna.model.Etudiant;
import ihm.com.ecolna.tools.IOperation;
import ihm.com.ecolna.tools.IRequest;
import ihm.com.ecolna.tools.MAsyncTask;

public class MainActivity extends AppCompatActivity  {
    EditText mail = null;
    EditText key = null;
    TextView rslt=null;

   public String etudiant;

    private List<Etudiant> le ;



    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mail = (EditText) findViewById(R.id.Email);
        key = (EditText) findViewById(R.id.key);
        le = new ArrayList<Etudiant>();


    }


    public void authenticate(View view) {

            if (mail.getText().toString().length() == 0) mail.setError("l'Email est obligatoire !");

            if (key.getText().toString().length() == 0 && mail.getText().toString().length() != 0)
                    key.setError("le mot de passe est obligatoire !");
               else
                {
                    if(findStudent(mail.getText().toString(),key.getText().toString()))
                    {
                    Intent intent = new Intent(this, HomeActivity.class);

                    startActivity(intent);
                   }


                }
            }


    public void inscrire(View view) {
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }


    public boolean findStudent(String email, String password)
    {
        boolean IsHere = false;
        MAsyncTask mTask = new MAsyncTask(MainActivity.this,IRequest.getAllStudents, IOperation.getAllStudents);
        try {
            HashMap map = mTask.execute().get();
            if (!map.containsKey("error"))
                le=(List<Etudiant>) map.get("Response");
            for(Etudiant e : le)
            {
                if(e.getEmail().equals(email)&&e.getPassword().equals(password)) {
                    NavigationView navigationView =(NavigationView)findViewById(R.id.nav_header_container);
                    View headerView = LayoutInflater.from(this).inflate(R.layout.fragment_navigation_drawer, navigationView, false);
                   // navigationView.addHeaderView(headerView);
                    rslt = (TextView)headerView.findViewById(R.id.etudiant);
                    rslt.setText("Bienvenue"+ e.getPrenom().toString()+" "+e.getNom().toString());
                    return true;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace() ;
        }




        return IsHere;

    }

     public String getEtudiant(){
         return etudiant;
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


