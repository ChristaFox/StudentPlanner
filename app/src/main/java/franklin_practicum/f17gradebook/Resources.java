package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Resources extends AppCompatActivity {

    private void putArraysInIntent(Intent i) {
        i.putExtra("userID", userID);
        i.putExtra("resourcesID", resourceID);
    }

    private void getArraysFromIntent() {
        userID = getIntent().getStringExtra("userID");
        resourceID = getIntent().getStringExtra("resourceID");
    }

    public class resource {

        public String userID, resourceID, website, resourceName;

        public resource() {}

        public resource(String userID, String resourceID, String website, String resourceName) {
            this.userID = userID;
            this.resourceID = resourceID;
            this.website = website;
            this.resourceName = resourceName;
        }
    }

    private String userID, resourceID;

    public ArrayList<resource> resources = new ArrayList<>();
    //private ImageView addResource;
    ArrayList<resource> resourcesList = new ArrayList<resource>();
    private ListView resourcesListView;
    ResourcesListAdapter resourcesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        getArraysFromIntent();

        resourcesListView = (ListView) findViewById(R.id.resourcesListView);
        resourcesAdapter = new ResourcesListAdapter(this, resourcesList);

        resourcesListView.setAdapter(resourcesAdapter);

        new FrankResourcesData().execute();

        Button contactsButton = (Button) findViewById(R.id.contactsButton);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resources.this, Contacts.class);
                startActivity(intent);
            }
        });
    }

    public class FrankResourcesData extends AsyncTask {
        private boolean firstResourceLoad;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                DataUtil dataUtil = new DataUtil("Resources.php?userID="+userID);
                //DataUtil dataUtil = new DataUtil("CourseAssignmentListSelect.php?CourseID="+"6"+"UserID="+"30");
                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    System.out.println(resources.get(i).resourceID.toString());

                    resourcesList.add(new resource(resourceID, userID, jsonObj.getString("resourceName"), jsonObj.getString("website")));
                    System.out.println(resources.get(i).resourceID.toString());
                }
                return jsonArray;
            }

            catch (Exception e) {
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   int length = resourcesList.size();
                   int iStart = 0;
                   if (firstResourceLoad == false)
                       iStart = 0;
                   else
                       firstResourceLoad =false;
                    for(int i = iStart; i < length; i++) {
                        resourcesAdapter.add(resourcesList.get(i).resourceName, resourcesList.get(i).website);
                    }
                }
            });
        }
    }
}