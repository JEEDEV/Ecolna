package ihm.com.ecolna.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ihm.com.ecolna.CoursActivity;
import ihm.com.ecolna.HomeActivity;
import ihm.com.ecolna.MainActivity;
import ihm.com.ecolna.R;
import ihm.com.ecolna.model.Cours;
import ihm.com.ecolna.model.Etudiant;
import ihm.com.ecolna.tools.IOperation;
import ihm.com.ecolna.tools.IRequest;
import ihm.com.ecolna.tools.MAsyncTask;

import static android.content.ContentValues.TAG;


public class CoursesFragment extends ListFragment implements AdapterView.OnItemClickListener {

    TextView cours = null;
    private List<Cours> courses;

    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courses = new ArrayList<Cours>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course, container, false);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.cours, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CoursActivity.class);
        startActivity(intent);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}