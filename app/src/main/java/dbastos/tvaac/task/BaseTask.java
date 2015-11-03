package dbastos.tvaac.task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dbastos.tvaac.TvVideo;
import dbastos.tvaac.adapter.RecentVideosAdapter;

public class BaseTask extends AsyncTask{

    private String url;
    private String jsonString;
    private RecentVideosAdapter videos;


    public BaseTask(String url, RecentVideosAdapter videos) {
        this.url = url;
        this.videos = videos;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        jsonString = getJSON(url,0);
        publishProgress();
        return jsonString;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);

        JsonObject jobj = new Gson().fromJson(jsonString, JsonObject.class);

        JsonArray items = jobj.get("items").getAsJsonArray();
        //String result = jobj.get("items").toString();

        for(int i=0; i<items.size(); i++){
            videos.add(new TvVideo(items.get(i).getAsJsonObject()));
        }

        //Log.e("JSON", result);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    private String getJSON(String url, int timeout) {

        HttpURLConnection c = null;

        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            //c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();

                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    private String getElement(String element){
        JsonObject jobj = new Gson().fromJson(jsonString, JsonObject.class);

        String result = jobj.get("thumbnails").toString();
        return result;
    }
}
