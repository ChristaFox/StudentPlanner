package franklin_practicum.f17gradebook;

/**
 * Created by Lisat on 11/7/2017.
 */

public class Assignment {


        private String assignID, userID, courseID, assignName, assignStartDate, assignEndDate;
        private int pointsPossible, pointsEarned, currentGradeGoal;
        Assignment(String assignID, String userID, String courseID, String assignName, String assignStartDate, String assignEndDate,
                          int pointsPossible, int pointsEarned, int currentGradeGoal){
            this.assignID = assignID;
            this.userID = userID;
            this.courseID = courseID;
            this.assignName = assignName;
            this.assignStartDate = assignStartDate;
            this.assignEndDate = assignEndDate;
            this.pointsPossible = pointsPossible;
            this.pointsEarned = pointsEarned;
            this.currentGradeGoal = currentGradeGoal;
        }

        public String getName() {
            return this.assignName;
        }

    public int getPtsAvail() {
        return this.pointsPossible;
    }



}
