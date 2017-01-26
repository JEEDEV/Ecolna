package ihm.com.ecolna.tools;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.HashMap;



public class MAsyncTask extends android.os.AsyncTask<Void, HashMap, HashMap> {

    private String TAG = MAsyncTask.class.getSimpleName();
    private ProgressDialog pDialog;

    private Context mcontext;
    private String request;
    private int operation;


    public MAsyncTask(){}

    public MAsyncTask(Context ctx, String request, int operation)
    {
        this.mcontext=ctx;
        this.request = request;
        this.operation = operation;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(mcontext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();

    }

    @Override
    protected HashMap doInBackground(Void... arg0) {

        WebService ws = new WebService();
        HashMap map = new HashMap();
        try {
            map.put("Response",ws.getJsonObject(request,operation));



        } catch (Exception e) {
            e.printStackTrace();

            map.put("error","Erreur lors de la récupération du résultat");
        }
        return map;

    }

    @Override
    protected void onPostExecute(HashMap result) {

        super.onPostExecute(result);

        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();


    }
}