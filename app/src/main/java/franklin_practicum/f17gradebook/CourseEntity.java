package franklin_practicum.f17gradebook;

/**
 * Created by LarryXu on 11/9/2017.
 */

public class CourseEntity {
    public String courseID;
    public String courseName;
    public String courseDesc;

    public CourseEntity(String courseID, String courseName, String courseDesc) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDesc = courseDesc;
    }
}
