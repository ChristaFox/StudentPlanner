package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LarryXu on 11/7/2017.
 */

public class AssignmentActivity extends AppCompatActivity {

    private static final String TAG = "AssignmentActivity";

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    public class AssignmentData extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HashMap<String, String> args = new HashMap<>();
                args.put("date", (String)objects[0]);
                args.put("userID", (String)objects[1]);
                args.put("courseID", (String)objects[2]);
                DataUtil dataUtil = new DataUtil("GET","AssignmentList.php");
                String jsonString = dataUtil.process(args);
                JSONArray jsonArray = new JSONArray(jsonString);

                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    List<String> subItems = new ArrayList<String>();
                    subItems.add("Due date: " + jsonObj.getString("date"));
                    expandableListDetail.put(jsonObj.getString("name"), subItems);
                }
                return jsonArray;
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
                    expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                    expandableListAdapter = new CustomExpandableListAdapter(AssignmentActivity.this, expandableListTitle, expandableListDetail);
                    expandableListView.setAdapter(expandableListAdapter);
                }
            });
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_layout);

        expandableListDetail = new HashMap<String, List<String>>();
        expandableListView = (ExpandableListView) findViewById(R.id.assignmentList);

        Intent intent = getIntent();
        String strDate = null;

        strDate = intent.getStringExtra("date");

        if (strDate == null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            strDate = dateFormat.format(date);
        }

        new AssignmentData().execute(strDate, getIntent().getStringExtra("userID"), getIntent().getStringExtra("courseID"));

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }
}
