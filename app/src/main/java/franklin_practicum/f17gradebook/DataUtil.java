package franklin_practicum.f17gradebook;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by LarryXu on 10/29/2017.
 */

public class DataUtil {

    private String httpMethod;
    private String urlString;
    private String host;

    public DataUtil(String phpFileName) {
        this.httpMethod = "GET";
        this.host = "http://f17grade.franklinpracticum.com/";
        this.urlString = this.host + phpFileName;
    }

    public DataUtil (String httpMethod, String phpFileName) {
        this.httpMethod = httpMethod;
        this.host = "http://f17grade.franklinpracticum.com/";
        this.urlString = this.host + phpFileName;
    }

    public DataUtil(String httpMethod, String host, String phpFileName) {
        this.httpMethod = httpMethod;
        this.host = host;
        this.urlString = this.host + phpFileName;
    }

    public String request(HashMap<String, String> params) {
        String result = "";
        if (this.httpMethod.equals("GET")) {
            this.urlString += ((params == null) ? "" : ("?" + stringifyParams(params)));
            result = httpGet();
        }
        else if (this.httpMethod.equals("POST")) {
            result = httpPost(stringifyParams(params));
        }
        return result;
    }

    public String stringifyParams(HashMap<String, String> params) {
        String paramQueryString = "";
        try {
            if (params != null) {
                Iterator<HashMap.Entry<String, String>> iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    HashMap.Entry<String, String> entry = iterator.next();
                    paramQueryString += ((this.httpMethod == "GET") ? entry.getKey() : URLEncoder.encode(entry.getKey(), "utf8")) + "="
                            + ((this.httpMethod == "GET") ? entry.getValue() : URLEncoder.encode(entry.getValue(), "utf8"))
                            + ((iterator.hasNext()) ? "&" : "");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return paramQueryString;
    }

    public String httpPost(String data) {
        StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection )url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            conn.setChunkedStreamingMode(0);

            if(!"".equals(data)) {
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();
            }

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                result.append(line);
                break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
        }
        return result.toString();
    }

    public String httpGet() {
        StringBuffer result = new StringBuffer("");
        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL(this.urlString);
            conn = (HttpURLConnection) url.openConnection();

            //conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("USER-AGENT", "Mozilla/5.0");

            conn.connect();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line = "";

            while ((line = in.readLine()) != null) {
                result.append(line + "\n");
            }

            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
        }
        return result.toString();
    }

    public String process(HashMap<String, String> params) {
        return request(params);
    }

}
