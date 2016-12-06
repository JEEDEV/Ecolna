package ihm.com.ecolna;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ihm.com.ecolna.fragments.CoursesFragment;
import ihm.com.ecolna.fragments.ForumFragment;
import ihm.com.ecolna.fragments.HomeFragment;
import ihm.com.ecolna.fragments.QcmFragment;

public class ForumActivity extends AppCompatActivity {
    EditText rep =null;

    RatingBar rating = null;
    ImageButton speech = null;
    TextView reponse=null;
    final Context context = this;
    private static final int REQUEST_CODE = 100;
    String quest,repo;
    ArrayList<String> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        reponse = (TextView) findViewById(R.id.rep);
        speech =(ImageButton)findViewById(R.id.btnSpeak);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.content_popup, null);
                rep = (EditText)dialoglayout.findViewById(R.id.reponse) ;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);



                // set dialog message
                alertDialogBuilder
                        .setView(dialoglayout)
                        .setCancelable(false)

                        .setPositiveButton("Valider",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                repo = rep.getText().toString();
                                ForumActivity.this.finish();
                                //reponse.setText(repo);
                            }
                        })
                        .setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });




        speech.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                        "AndroidBite Voice Recognition...");
                startActivityForResult(intent, REQUEST_CODE);

            }

        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        // retrieves data from the VoiceRecognizer
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(ForumActivity.this,
            // android.R.layout.simple_list_item_1, results);
            reponse.setText(results.get(1));
            //reponse.setAdapter(adapter);

        }

        super.onActivityResult(requestCode, resultCode, data);

    }
    /* if (requestCode == 10000 && resultCode == RESULT_OK) {
         results = data
                 .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
         reponse.setAdapter(new ArrayAdapter<String>(this,
                 android.R.layout.simple_list_item_1, results));
     }

     super.onActivityResult(requestCode, resultCode, data);
 }*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // This should save all the data so that when the phone changes
        // orientation the data is saved
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("results", results);
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
