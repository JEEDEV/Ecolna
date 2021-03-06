package ihm.com.ecolna.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import ihm.com.ecolna.R;

public class ThreeFragment extends Fragment{

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_one, container, false);
        String [] values =
                {"Il ne peut accéder qu'aux membres de la classe de base","Il peut accéder à des membres de la classe seulement dérivées"," Les deux classes de base et les membres de la classe dérivée"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        TextView ques1 =(TextView)v.findViewById(R.id.ques1);
        TextView ques2 =(TextView)v.findViewById(R.id.ques2);
        TextView ques3 =(TextView)v.findViewById(R.id.ques3);
        TextView ques4 =(TextView)v.findViewById(R.id.ques4);
        ques1.setText("Une classe de base abstraite ne peut pas avoir destructor");
        ques2.setText("quel est le type d'héritage?");
        ques3.setText("Lequel des éléments suivants est le mot - clé propre à désaffecter la mémoire?");
        ques4.setText("Lorsqu'une base des points de pointeur de la classe à l' objet de la classe dérivée?");
        CheckBox check1 =(CheckBox)v.findViewById(R.id.checkbox1);
        CheckBox check2 =(CheckBox)v.findViewById(R.id.checkbox2);
        CheckBox check3 =(CheckBox)v.findViewById(R.id.checkbox3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        Button score=(Button)v.findViewById(R.id.valider);
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
                        .setPositiveButton("Continuer",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                dialog.cancel();

                            }
                        });


                // create alert dialog
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        return v;
    }


}