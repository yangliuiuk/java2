/* 
Course Project: Student Grade Analyzer

Objective:

The goal of this assignment is to develop a Java program that reads student records from a text file, calculates and displays each student's average score, identifies the top student, and displays the top student for each subject along with their score.

Instructions:

1. Read data from the provided "student-record.txt Download student-record.txt" file that contains student records in the following format for each line:

StudentID,FirstName,LastName,SubjectID,SubjectName,Grade

2. Store the data into appropriate data structures (e.g., arrays, ArrayLists, or HashMaps).

3. Calculate each student's average score based on their grades in different subjects.

4. Display student grades in a tabular format, showing each student's grades for all subjects and the average score.

5. Identify and display the top student with the highest average score.

6. For each subject, determine and display the top student and their score.

7. Implement error handling to handle potential issues, such as file not found or data format errors. (optional for extra points)

Sample Output:

Here's an example of what the program's output might look like:

Student Grade Table:
StudentID FirstName LastName  Math      Science   History   Average
101       John      Smith     92        78        85        85.00     
102       Emily     Jones     88        92        79        86.33     
103       Michael   Doe       89        76        90        85.00     
104       Susan     Johnson   75        96        88        86.33     
105       Lisa      Williams  85        88        72        81.67     
106       David     Anderson  78        82        84        81.33     

Top Student:
Student ID: 102, Name: Emily Jones, Average Score: 86.33

Top Student for Each Subject:
Subject   Top Student ID      FirstName LastName  Score     
Math      101                 John      Smith     92.00     
Science   104                 Susan     Johnson   96.00     
History   103                 Michael   Doe       90.00 
Note: the numbers in the sample output may not be accurate.

Submission:

Java source code
Screenshot of output

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class StudentGradeAnalyzer {
    public static void main(String[] args) {

        HashMap<String, String[]> students = new HashMap<>(); // studentID : [firstName, lastName]
        HashMap<String, String> subjects = new HashMap<>(); // subjectID: subjectName

        HashMap<String, HashMap<String, Double>> studentGrades = new HashMap<>(); // studentID : <subjectID: score>
        HashMap<String, HashMap<String, Double>> subjectGrades = new HashMap<>(); // subjectID : <studentID: score>

        try {
            Scanner scanner = new Scanner(new File("resource/student-records.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0)
                    continue;
                String[] data = line.split(",");
                if (data[0].equals("StudentID"))
                    continue;
                String studentID = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String[] fullName = {firstName, lastName};
                String subjectID = data[3];
                String subjectName = data[4];
                double score = 0.0;
                try {
                    score = Double.parseDouble(data[5]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // Store student data
                if (!students.containsKey(studentID)) {
                    students.put(studentID, fullName);
                }

                // Store subject data
                if (!subjects.containsKey(subjectID)) {
                    subjects.put(subjectID, subjectName);
                }

                // Store student's score on a subject organized by students
                if (!studentGrades.containsKey(studentID)) {
                    studentGrades.put(studentID, new HashMap<String, Double>());
                }
                studentGrades.get(studentID).put(subjectID, score);

                // Store student's score on a subject organized by subjects
                if (!subjectGrades.containsKey(subjectID)) {
                    subjectGrades.put(subjectID, new HashMap<String, Double>());
                }
                subjectGrades.get(subjectID).put(studentID, score);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Print student grade table
        System.out.println("Student Grade Table:");
        System.out.printf("%-10s%-10s%-10s", "StudentID", "FirstName", "LastName"); // Each column 10 character wide, left aligned
        // Print subject list
        for (String subjectID: subjects.keySet()) {
            System.out.printf("%-10s", subjects.get(subjectID));
        }
        System.out.println("Average");

        for (String studentID: studentGrades.keySet()) {
            String firstName = students.get(studentID)[0];
            String lastName = students.get(studentID)[1];
            System.out.printf("%-10s%-10s%-10s", studentID, firstName, lastName);
            double totalScore = 0;
            for (String subjectID: subjects.keySet()) {
                double score = studentGrades.get(studentID).get(subjectID);
                System.out.printf("%-10.0f", score);
                totalScore += score;
            }
            double averageScore = totalScore / subjects.size();
            studentGrades.get(studentID).put("Average", averageScore); // Add the "Average" as a subject
            System.out.printf("%-10.2f", averageScore);
            System.out.println();
        }
        System.out.println();

        // Finding the top student
        String topStudentID = null;
        double topAverageScore = -1;
        for (String studentID : studentGrades.keySet()) {
            double averageScore = studentGrades.get(studentID).get("Average");
            if (averageScore > topAverageScore) {
                topAverageScore = averageScore;
                topStudentID = studentID;
            }
        }
        String[] topStudentName = students.get(topStudentID);
        System.out.println("Top Student:");
        System.out.printf("Student ID: %s, Name: %s %s, Average Score: %.2f", topStudentID, topStudentName[0], topStudentName[1], topAverageScore);
        System.out.println("\n");

        // Finding the top student for each subject
        System.out.println("Top Student for Each Subject:");
        System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", "Subject", "Top Student ID", "FirstName", "LastName", "Score");
        for (String subjectID: subjectGrades.keySet()) {
            String subjectName = subjects.get(subjectID);
            String subjectTopStudentID = null;
            double subjectTopScore = -1;
            for (String studentID: subjectGrades.get(subjectID).keySet()) {
                double subjectScore = subjectGrades.get(subjectID).get(studentID);
                if (subjectScore > subjectTopScore) {
                    subjectTopScore = subjectScore;
                    subjectTopStudentID = studentID;
                }
            }
            String[] subjectTopStudentName = students.get(subjectTopStudentID);
            System.out.printf("%-10s%-20s%-10s%-10s%-10.2f\n", subjectName, subjectTopStudentID, subjectTopStudentName[0], subjectTopStudentName[1], subjectTopScore);
        }
    }
}
