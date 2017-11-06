package franklin_practicum.f17gradebook;

import org.json.JSONArray;
import org.json.JSONObject;


public interface HttpResponse {

    void handle(JSONObject json);

    void handle(String s);

    void exceptionCaught(Exception e);

}