package ihm.com.ecolna.tools;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ihm.com.ecolna.model.Etudiant;


public class WebService {

    public Gson gson;

    private static final String TAG = WebService.class.getSimpleName();

    public WebService() {
        gson = new Gson();
    }

    private InputStream sendRequest(URL url) throws Exception {

        try {
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception("");
        }
        return null;
    }

    public Object getJsonObject(String url,int operation) throws Exception {

        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(url));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                switch (operation)
                {
                    case IOperation.getAllStudents:
                        return gson.fromJson(reader, new TypeToken<List<Etudiant>>(){}.getType());


                }

                // Retourne la liste désérialisée par le moteur GSON

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("WebService", "Impossible de rapatrier les données :(");
            throw e;
        }
        return null;
    }

}

