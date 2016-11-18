package ihm.com.ecolna.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import ihm.com.ecolna.MainActivity;

import ihm.com.ecolna.R;

/**
 * Created by bilel on 13/11/2016.
 */

import android.support.v4.app.Fragment;



public class OneFragment extends Fragment{

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
  /* public void valider(View view){
        AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
        alert.setTitle("level");
        alert.show();
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_one, container, false);
    }

}