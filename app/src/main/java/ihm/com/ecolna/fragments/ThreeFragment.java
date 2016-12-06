package ihm.com.ecolna.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
                {"Time at Residence","Under 6 months","6-12 months","1-2 years","2-4 years","4-8 years","8-15 years","Over 15 years",};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
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