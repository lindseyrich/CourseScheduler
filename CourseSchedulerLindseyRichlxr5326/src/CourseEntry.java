/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author linds
 */
public class CourseEntry {
    private String semester;
    private String courseCode;
    private String description; 
    private int seats;
        public CourseEntry(String semester, String courseCode, String description, int seats){
            this.semester = semester;
            this.courseCode = courseCode;
            this.description = description;
            this.seats = seats;
        }
        public String getSemester(){
            return this.semester;
            
        }
        public String getCourseCode(){
            return this.courseCode;
          
        }
        public String getDescription(){
            return this.description;
        }
        public int getSeats(){
        
            return this.seats;
        }
        
        
}
