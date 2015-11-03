package dbastos.tvaac;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.HashMap;

public class TvVideo implements Serializable{

    private String id;
    private String title;
    private String description;

    private HashMap<String,String> thumbnails;

    public TvVideo(JsonObject item) {

        JsonObject snippet = item.get("snippet").getAsJsonObject();
        this.title = snippet.get("title").getAsString();
        this.description = snippet.get("description").getAsString();

        JsonObject th = snippet.get("thumbnails").getAsJsonObject();

        thumbnails = new HashMap<>();

        thumbnails.put("default",th.get("default").getAsJsonObject().get("url").getAsString());
        thumbnails.put("medium",th.get("medium").getAsJsonObject().get("url").getAsString());
        thumbnails.put("high",th.get("high").getAsJsonObject().get("url").getAsString());
        thumbnails.put("standard",th.get("standard").getAsJsonObject().get("url").getAsString());
        thumbnails.put("maxres", th.get("maxres").getAsJsonObject().get("url").getAsString());


        id = snippet.get("resourceId").getAsJsonObject().get("videoId").getAsString();
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail(String size){
        return thumbnails.get(size);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
