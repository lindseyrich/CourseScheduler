
//import java.security.Timestamp;
import java.util.Calendar;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author linds
 */
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private java.sql.Timestamp timeStamp;
    public ScheduleEntry(String semester, String courseCode, String studentID, String status, java.sql.Timestamp timeStamp){
        this.semester = semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.status = status;
        this.timeStamp = timeStamp;
        //java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        
    
    }
    public String getSemester(){
        return this.semester;
    }
    public String getCourseCode(){
    
        return this.courseCode;
    }
    public String getStudentID(){
        return this.studentID;
    }
    public String getStatus(){
        return this.status;
    }
    public java.sql.Timestamp getTimeStamp(){
        return this.timeStamp;
    }
}
