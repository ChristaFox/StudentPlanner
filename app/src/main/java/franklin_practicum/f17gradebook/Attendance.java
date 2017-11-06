package franklin_practicum.f17gradebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.PrintStream;
import java.util.Scanner;

public class Attendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        PrintStream printf = System.out.printf("How many weeks is this course?");
        int n = reader.nextInt(); // Scans the next token of the input as an int.
        reader.close();
        for (int i = 1; i < n; i++) {
            PrintStream printf1 = System.out.printf("week %d", n);
            //pull course number from database
            //add course number to print out above
            //for this feild the attends will be true/ boolean
            // attendance = true;
            System.out.println();
        }
    }
}
