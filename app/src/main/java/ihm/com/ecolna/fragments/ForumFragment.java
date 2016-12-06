package ihm.com.ecolna.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import ihm.com.ecolna.ForumActivity;
import ihm.com.ecolna.QcmActivity;
import ihm.com.ecolna.R;



public class ForumFragment extends ListFragment  implements AdapterView.OnItemClickListener {

    public ForumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.questions, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Intent in = new Intent(getActivity().getApplicationContext(),ForumActivity.class);

        startActivity(in);

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