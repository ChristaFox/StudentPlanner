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
        i.putExtra("resourcesID", resourcesID);
    }

    private void getArraysFromIntent() {
        userID = getIntent().getStringExtra("userID");
        resourcesID = getIntent().getStringExtra("resourcesID");
    }

    public class resource {

        public String userID, resourcesID, website, resourceName;

        public resource() {}

        public resource(String userID, String courseID, String resourcesID, String website, String resourceName) {
            this.userID = userID;
            this.resourcesID = resourcesID;
            this.website = website;
            this.resourceName = resourceName;
        }
    }

    private String userID, resourcesID;

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

        resourcesListView = (ListVeiw) findViewById(R.id.resourcesListView);
        resourcesAdapter = new ResourcesListAdapter(this, resourcesList);

        resourceListVeiw.setAdapter().execute();

        new FrankAssignData().execute();

        //ListView resourcesListView = (ListView) findViewById(R.id.resourcesListView);
        //resourcesAdapter = new ResourcesListAdapter((resourcesListView.getContext()), resourcesList);
        //resourcesListView.setAdapter(resourcesAdapter);
        //int length = resources.size();
        //for (int i = 0; i < length; i++) {
        //    resourcesAdapter.add(resources.get(i).resourceName);
        //    resourcesAdapter.add(resources.get(i).website);
        //}

//        addResource = (ImageView) findViewById(R.id.addResourceButton);
//        addResource.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resourcesAdapter.add("ResourceName: ", "Website: ");
//            }
//        });

        Button contactsButton = (Button) findViewById(R.id.contactsButton);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resources.this, Contacts.class);
                startActivity(intent);
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

                    resources.add(new Resources.resource());
                    resources.get(i).resourcesID = jsonObj.getString("resourceID");
                    resources.get(i).resourceName = jsonObj.getString("resourceName: ");
                    resources.get(i).website = jsonObj.getString("website");
                    //String userID, String resourcesID, String website, String resourceName
                    //Resources.add(new Resources.resource("", userID, jsonObj.getString("id"),
                    //        jsonObj.getString("resourcesID"), jsonObj.getString("resourceName"),
                    //        jsonObj.getString("website"), "", "", "", ""));
                    System.out.println(resources.get(i).resourcesID.toString());
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
                    //ListView resourcesListView = (ListView) findViewById(R.id.resourcesListView);
                    //resourcesAdapter = new ResourcesListAdapter((resourcesListView.getContext()), resourcesList);
                    //resourcesListView.setAdapter(resourcesAdapter);
                    //int length = resources.size();
                    //for (int i = 0; i < length; i++) {
                    //    resourcesAdapter.add(resources.get(i).resourceName);
                    //    resourcesAdapter.add(resources.get(i).website);
                    //}
                    ListView resourcesListView = (ListView) findViewById(R.id.resourcesListView);
                    resourcesAdapter = new ResourcesListAdapter((resourcesListView.getContext()), resourcesList);
                    resourcesListView.setAdapter(resourcesAdapter);
                    int length = resources.size();
                    for (int i = 0; i < length; i++) {
                        resourcesAdapter.add(resources.get(i).resourceName, resources.get(i).website);
                    }
                }
            });
        }
    }
}