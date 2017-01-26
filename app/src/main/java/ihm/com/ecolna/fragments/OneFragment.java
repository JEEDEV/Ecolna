package ihm.com.ecolna.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import ihm.com.ecolna.MainActivity;

import ihm.com.ecolna.R;
import ihm.com.ecolna.model.Etudiant;
import ihm.com.ecolna.model.Qcm;
import ihm.com.ecolna.tools.IOperation;
import ihm.com.ecolna.tools.IRequest;
import ihm.com.ecolna.tools.MAsyncTask;

/**
 * Created by bilel on 13/11/2016.
 */

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class OneFragment extends Fragment{

    EditText edit1 = null;
    EditText edit2 = null;
    Button score=null;
    TextView verif = null;
    TextView ques1 =null;
    TextView ques2 = null;
    TextView ques3 = null;
    TextView ques4 = null;
    TextView ques5 = null;
    CheckBox check1 = null;
    CheckBox check2 = null;
    CheckBox check3 = null;
    Spinner spinner = null;
    private List<Qcm> qcm1;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_one, container, false);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        String [] values =
                {"Tenter de réaliser un calcul très compliqué","Tenter de réaliser une opération qui peut échouer","Tenter de lancer un programme extérieur"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        qcm1 = new ArrayList<Qcm>();

        score=(Button)v.findViewById(R.id.valider);
//         if(findQcm(verif.getText().toString())) {

             score.setOnClickListener(new View.OnClickListener() {

                 @Override
                 public void onClick(View view) {
                     LayoutInflater inflater = getActivity().getLayoutInflater();
                     View dialoglayout = inflater.inflate(R.layout.popup_score, null);

                     android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getActivity());

                     // set title
                     // alertDialogBuilder.setTitle("Ajouter votre réponse");
                     //alertDialogBuilder.setIcon(R.drawable.note);

                     // set dialog message
                     alertDialogBuilder
                             .setView(dialoglayout)
                             .setCancelable(false)
                             .setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int id) {

                                     dialog.cancel();

                                 }
                             });


                     // create alert dialog
                     android.app.AlertDialog alertDialog = alertDialogBuilder.create();

                     // show it
                     alertDialog.show();
                 }
             });
         //}

        return v;
    }
   /* public boolean findQcm(String niveau){
        boolean IsHere = false;
        MAsyncTask mTask = new MAsyncTask(getActivity(), IRequest.getAllQcm, IOperation.getAllQcm);
        try {
            HashMap map = mTask.execute().get();
            if (!map.containsKey("error"))
                qcm1=(List<Qcm>) map.get("Response");
            for(Qcm q: qcm1) {
                if(q.getNiveau().equals(niveau)){
                    ques1.setText(q.getQuestion1().toString());
                    ques2.setText(q.getQuestion2().toString());
                    ques3.setText(q.getQuestion3().toString());
                    ques4.setText(q.getQuestion4().toString());
                    ques5.setText(q.getQuestion5().toString());
                    check1.setText(q.getCheck1().toString());
                    check2.setText(q.getCheck1().toString());
                    check3.setText(q.getCheck1().toString());
                    String values[]= {q.getSpinner1().toString(), q.getSpinner2().toString(), q.getSpinner3().toString()};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner.setAdapter(adapter);

                }


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace() ;
        }




        return IsHere;

    }*/
}