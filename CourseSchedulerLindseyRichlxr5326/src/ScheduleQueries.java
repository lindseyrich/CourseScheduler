
import java.security.Timestamp;

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
import java.util.Calendar;
public class ScheduleQueries extends ScheduleEntry{
    private static Connection connection;
    private static PreparedStatement addSchedule;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getSchedules;
    private static PreparedStatement getCoursesByStudent;
    private static ResultSet resultSet;
    private static PreparedStatement dropCourse;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement deleteScheduleByStudent;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement getWaitlistedByCourse;
    private static PreparedStatement updateScheduleEntry;
    
    
    public ScheduleQueries(String semester, String courseCode, String studentID, String status, java.sql.Timestamp timeStamp){
        super(semester, courseCode, studentID, status, timeStamp);
        
        
    
    
    }
    
    
       public static void addScheduleEntry(ScheduleEntry schedule)
    {
        connection = DBConnection.getConnection();
        try
        {   //java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            addSchedule = connection.prepareStatement("insert into app.Schedule (Semester, Coursecode, StudentID, Status, Timestamp) values (?, ?, ?, ?, ?)");
            addSchedule.setString(1, schedule.getSemester());
            addSchedule.setString(2, schedule.getCourseCode());
            addSchedule.setString(3, schedule.getStudentID());
            addSchedule.setString(4, schedule.getStatus());
            addSchedule.setTimestamp(5, schedule.getTimeStamp());
            
            
            addSchedule.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
} 
       public static int getScheduledStudentCount(String currentSemester, String courseCode){
           connection = DBConnection.getConnection();
        ArrayList<String> semester = new ArrayList<String>();
        int count = 0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(Studentid) from app.Schedule where semester = ? and coursecode = ?");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            
            while(resultSet.next())
            {
                count += resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return count;
       
       }
       
       public static ArrayList<ScheduleEntry> getScheduleByStudent(String s, String ID){
       
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<ScheduleEntry>();
        try
        {
            getSchedules = connection.prepareStatement("select* from app.Schedule where semester = ? and studentid = ?");
            getSchedules.setString(1, s);
            getSchedules.setString(2,ID);
            resultSet = getSchedules.executeQuery();
            
            while(resultSet.next())
            {
                //System.out.println("First Name "+ resultSet.getString("Firstname"));
                String semester = resultSet.getString("Semester");
                String courseCode = resultSet.getString("Coursecode");
                String studentID = resultSet.getString("Studentid");
                String status = resultSet.getString("Status");
                java.sql.Timestamp currentTimestamp = resultSet.getTimestamp("Timestamp");
                
                ScheduleEntry schedule = new ScheduleEntry(semester, courseCode, studentID, status,currentTimestamp );
                schedules.add(schedule);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return schedules;
       
       }
       public static ArrayList<String> getCoursesByStudent(String studentID){
       
        connection = DBConnection.getConnection();
        ArrayList<String> studentCourses = new ArrayList<String>();
        try
        {
            getCoursesByStudent = connection.prepareStatement("select coursecode from app.Schedule where studentID = ?");
            getCoursesByStudent.setString(1, studentID);
       
            resultSet = getCoursesByStudent.executeQuery();
            
            while(resultSet.next())
            {   
                
                studentCourses.add(resultSet.getString(1));
                
                
                
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return studentCourses;
       
       }
       
       
       public static void dropStudentScheduleByCourse(String semester, String studentID,  String courseCode){
        connection = DBConnection.getConnection();
        
        try
        {
            dropCourse = connection.prepareStatement("delete from app.Schedule where Semester = ? and StudentID = ? and  Coursecode = ?");
            dropCourse.setString(1, semester);
            dropCourse.setString(2, studentID);
            dropCourse.setString(3, courseCode);
            dropCourse.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    
    
    }
       
       
       
       public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode){
            connection = DBConnection.getConnection();
            ArrayList<ScheduleEntry> scheduledStudents = new ArrayList<ScheduleEntry>();
            try
        {
            getScheduledStudentsByCourse= connection.prepareStatement("select* from app.Schedule where semester = ? and courseCode = ? and Status = 'S'");
            getScheduledStudentsByCourse.setString(1, semester);
            getScheduledStudentsByCourse.setString(2, courseCode);
            
       
            resultSet = getScheduledStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {   
                
                
                String studentID = resultSet.getString("Studentid");
                String status = resultSet.getString("Status");
                java.sql.Timestamp currentTimestamp = resultSet.getTimestamp("Timestamp");
                
                ScheduleEntry schedule = new ScheduleEntry(semester, courseCode, studentID, status,currentTimestamp );
                scheduledStudents.add(schedule);
                
                
                
                
            }
        }
            catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
            return scheduledStudents;
       
       
       
       
       
       }
       
       
       
       public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode){
            connection = DBConnection.getConnection();
            ArrayList<ScheduleEntry> waitlistedStudents = new ArrayList<ScheduleEntry>();
            try
        {
            getWaitlistedStudentsByCourse= connection.prepareStatement("select* from app.Schedule where semester = ? and courseCode = ? and Status = 'W' order by Timestamp ASC");
            getWaitlistedStudentsByCourse.setString(1, semester);
            getWaitlistedStudentsByCourse.setString(2, courseCode);
            
       
            resultSet = getWaitlistedStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {   
                
                
                String studentID = resultSet.getString("Studentid");
                String status = resultSet.getString("Status");
                java.sql.Timestamp currentTimestamp = resultSet.getTimestamp("Timestamp");
                
                ScheduleEntry schedule = new ScheduleEntry(semester, courseCode, studentID, status,currentTimestamp );
                waitlistedStudents.add(schedule);
                
                
                
                
            }
        }
            catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
            return waitlistedStudents;
       
       
       
       
       
       }
       
       public static void deleteScheduleByStudent(String semester, String studentID){
       
       connection = DBConnection.getConnection();
        
        try
        {
            deleteScheduleByStudent = connection.prepareStatement("delete from app.Schedule where Semester = ? and StudentID = ?");
            deleteScheduleByStudent.setString(1, semester);
            deleteScheduleByStudent.setString(2, studentID);
            deleteScheduleByStudent.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
       
       
       
       }
       
       
       
       public static void dropScheduleByCourse(String semester, String courseCode){
       
           connection = DBConnection.getConnection();
        
        try
        {
            dropScheduleByCourse = connection.prepareStatement("delete from app.Schedule where Semester = ? and  Coursecode = ?");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2, courseCode);
            dropScheduleByCourse.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
       
       
       
       
       }
       
       public static void updateScheduleEntry(String semester, ScheduleEntry Entry){
       
           connection = DBConnection.getConnection();
        
        try
        {
            dropScheduleByCourse = connection.prepareStatement("update app.Schedule set Status = 'S' where Semester = ? and StudentID = ? and Coursecode = ? and Timestamp = ?");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2, Entry.getStudentID());
            dropScheduleByCourse.setString(3, Entry.getCourseCode());
            dropScheduleByCourse.setTimestamp(4,Entry.getTimeStamp());
                    
            
            dropScheduleByCourse.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
       
       
       
       
       
       
       }
       
       
       

       
       
       
       
       
}
