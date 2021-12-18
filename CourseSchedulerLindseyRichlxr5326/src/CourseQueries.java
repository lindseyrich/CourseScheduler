/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author linds
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseQueries extends CourseEntry{
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourseCodes;
    private static PreparedStatement getCourses;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;
    
    public CourseQueries(String semester, String courseCode, String description, int seats){
        super(semester, courseCode, description, seats);
        
        
    
    }

 
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.Course (Semester, Coursecode, Description, Seats) values (?, ?, ?, ?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getDescription());
            addCourse.setInt(4, course.getSeats());
            
            
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
}     public static ArrayList<CourseEntry> getAllCourses(String s)
    {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        try
        {
            getCourses = connection.prepareStatement("select* from app.Course where semester = ? order by Coursecode");
            getCourses.setString(1, s);
            resultSet = getCourses.executeQuery();
            
            while(resultSet.next())
            {
                //System.out.println("First Name "+ resultSet.getString("Firstname"));
                String semester = resultSet.getString("Semester");
                String courseCode = resultSet.getString("Coursecode");
                String description = resultSet.getString("Description");
                int seats = resultSet.getInt("Seats");
                CourseEntry course = new CourseEntry(semester, courseCode, description, seats);
                courses.add(course);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        //System.out.print(courses);
        return courses;
        
    }

    public static ArrayList<String> getAllCourseCodes(String s)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getCourseCodes = connection.prepareStatement("select Coursecode from app.Course where Semester = ? order by Coursecode");
            getCourseCodes.setString(1,s);
            resultSet = getCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return courseCodes;
        
    }
    public static int getCourseSeats(String s, String c){
        connection = DBConnection.getConnection();
        int seats = 0;
        try
        {
            getCourseSeats = connection.prepareStatement("select Seats from app.Course where Semester = ? and Coursecode = ?");
            getCourseSeats.setString(1, s);
            getCourseSeats.setString(2, c);
            resultSet = getCourseSeats.executeQuery();
            
            while(resultSet.next())
            {
                seats = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;
    
    }
    
    public static void dropCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        
        try
        {
            dropCourse = connection.prepareStatement("delete from app.Course where Semester = ? and Coursecode = ?");
            dropCourse.setString(1, semester);
            dropCourse.setString(2, courseCode);
            dropCourse.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    
    
    }

}
    