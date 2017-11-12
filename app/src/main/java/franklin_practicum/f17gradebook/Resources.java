package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Resources extends AppCompatActivity {

        private void putArraysInIntent(Intent i) {
        i.putExtra("userID", userID);
        i.putExtra("resourceID", resourceID);
    }

    private void getArraysFromIntent() {
        userID = getIntent().getStringExtra("userID");
        resourceID = getIntent().getStringExtra("resourceID");
    }

    private String userID;
    private int ResourceID;

    public class resource {
        public String userID, resourceID, website, resourceName;

        public resource() {
        }

        public resource(String userID, String resourceID, String website, String resourceName) {
            this.userID = userID;
            this.resourceID = resourceID;
            this.website = website;
            this.resourceName = resourceName;
        }
    }

    public ArrayList<resource> resources = new ArrayList<>();

    ArrayList<Object> resourcesList = new ArrayList<Object>();
    ResourcesListAdapter resourcesAdapter;

    private Button contactsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        getArraysFromIntent();

        contactsButton = (Button) findViewById(R.id.contactsButton);

        Bundle bundle = getIntent().getExtras();
        String resourceName= bundle.getString("resourceName");
        String website= bundle.getString("website");

        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Resources.this, Contacts.class);
                startActivity(intent);

        new FrankAssignData().execute();

        ListView resourcesListView = (ListView) findViewById(R.id.resourcesListView);
        resourcesAdapter = new ResourcesListAdapter((resourcesListView.getContext()), resourcesList);

        resourcesListView.setAdapter(resourcesAdapter);

        //resourcesAdapter.add(" " "URL: ");
        int length = resources.size();
        for (int i = 0; i < length; i++) {
            resourcesAdapter.add(resources.get(i).resourceName);
            resourcesAdapter.add(resources.get(i).website);
        }

        ImageView addResourceButton = (ImageView) findViewById(R.id.addResourceButton);
        addResourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resourcesAdapter.add(" ");
            }
        });
    }

    public class FrankAssignData extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                DataUtil dataUtil = new DataUtil("Resources.php?UserID=" + userID);
                //DataUtil dataUtil = new DataUtil("CourseAssignmentListSelect.php?CourseID="+"6"+"UserID="+"30");
                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    resources.add(new Resources().resource());
                    resources.get(i).resourceID = jsonObj.getString("resource ID: ");
                    resources.get(i).resourceName = jsonObj.getString("name: ");
                    resources.get(i).website = jsonObj.getString("URL: ");

                }
                return jsonArray;

                /*
			array_push($result, array('name' => $row["AssignmentName"],

						  'dueDate' => $row["AssignmentEndDate"],
						  'pointsPossible' => $row ["AssignmentPointsPossible"],
						  'pointsEarned' => $row ["AssignmentPointsEarned"],
						  'pointsGoal' => $row ["AssignmentCurrentGoal"]));

                */


            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                    //expandableListAdapter = new CustomExpandableListAdapter(CourseActivity.this, expandableListTitle, expandableListDetail);
                    //expandableListView.setAdapter(expandableListAdapter);
                }
            });
        }
    }

}