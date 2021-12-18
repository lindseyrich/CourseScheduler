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

public class StudentQueries extends StudentEntry {
    private static Connection connection;
    
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudents;
    private static PreparedStatement dropStudent;
    private static PreparedStatement getStudent;
    
    
    private static ResultSet resultSet;
    
    public StudentQueries(String studentID, String firstName, String lastName){
        super(studentID, firstName, lastName);
        
        
    
    }
    
      public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.Student (Studentid, Firstname, Lastname) values (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            
            //courses.add(course);
            //System.out.println(course.getCourseCode());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
} 
       public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getStudents = connection.prepareStatement("select* from app.Student order by Firstname");
            resultSet = getStudents.executeQuery();
            
            while(resultSet.next())
            {
                //System.out.println("First Name "+ resultSet.getString("Firstname"));
                String studentID = resultSet.getString("StudentID");
                String firstName = resultSet.getString("Firstname");
                String lastName = resultSet.getString("Lastname");
                StudentEntry student = new StudentEntry(studentID, firstName, lastName);
                students.add(student);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        //System.out.println(students.toString());
        return students;
        
    }
     
       public static void dropStudent(String studentID){
        connection = DBConnection.getConnection();
        
        try
        {
            dropStudent = connection.prepareStatement("delete from app.Student where StudentID = ?");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    
    
    }
       
       public static StudentEntry getStudent(String studentID){
           connection = DBConnection.getConnection();
           ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
           
        
        try
        {
            getStudent = connection.prepareStatement("select* from app.Student where StudentID = ?");
            getStudent.setString(1, studentID);
            resultSet = getStudent.executeQuery();
            while(resultSet.next())
            {
                //System.out.println("First Name "+ resultSet.getString("Firstname"));
                
                String firstName = resultSet.getString("Firstname");
                String lastName = resultSet.getString("Lastname");
                StudentEntry student = new StudentEntry(studentID, firstName, lastName);
                students.add(student);
                
                
            }
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
       //System.out.println(students.get(0));
       return students.get(0);
       
       
       }
       
       
    
}
