import java.util.*;
class Calculator{
    int datastructure;
    int maths;
    int chemistry;
    int java;
    int physics;
    double tm;
    double ap;
    Calculator(int ds,int m,int ch,int j,int ph){
        datastructure=ds;
        maths=m;
        chemistry=ch;
        java=j;
        physics=ph;
    }
    void TotalMarks(){
       tm = datastructure + maths + chemistry + java + physics;
        System.out.println("==========================================");
        System.out.println(" TOTAL MARKS");
        System.out.println("==========================================");
        System.out.println("Total Marks (out of 500): " + tm);
    }
    void AveragePercentage(){
        ap = tm / 5;
        System.out.println("\n==========================================");
        System.out.println(" AVERAGE PERCENTAGE");
        System.out.println("==========================================");
        System.out.printf("Average Percentage: %.2f%%\n", ap);
    }
    
    void OverallGrade(double percentage) {
        String grade;

        if (percentage >= 90) grade = "A";
        else if (percentage >= 80) grade = "B";
        else if (percentage >= 70) grade = "C";
        else if (percentage >= 60) grade = "D";
        else if (percentage >= 50) grade = "E";
        else grade = "F";

        System.out.println("\n==========================================");
        System.out.println(" OVERALL GRADE");
        System.out.println("==========================================");
        System.out.println("Based on Average Percentage: Grade " + grade);
    }
    void Grade(){
       
        int[] marks = {datastructure, maths, chemistry, java, physics};
        String[] subjects = {"Data Structure", "Maths", "Chemistry", "Java", "Physics"};

        System.out.println("\n==========================================");
        System.out.println(" SUBJECT-WISE GRADES");
        System.out.println("==========================================");
        System.out.printf("%-20s %-10s %-10s\n", "Subject", "Marks", "Grade");
        System.out.println("------------------------------------------");

        for (int i = 0; i < 5; i++) {
            String grade;

            if (marks[i] >= 90) grade = "A";
            else if (marks[i] >= 80) grade = "B";
            else if (marks[i] >= 70) grade = "C";
            else if (marks[i] >= 60) grade = "D";
            else if (marks[i] >= 50) grade = "E";
            else grade = "F";

            System.out.printf("%-20s %-10d %-10s\n", subjects[i], marks[i], grade);
        
        }
    }

}

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
          System.out.println("========== STUDENT GRADE CALCULATOR ==========\n");
        System.out.println("Enter the marks of each subject (out of 100):");
        System.out.println("--------------------------------------------------");

        System.out.println("Enter the mark of the subject Data Structure:");
        int ds = sc.nextInt();
        System.out.println("Enter the mark of the subject Maths:");
        int m = sc.nextInt();
        System.out.println("Enter the mark of the subject Chemistry:");
        int ch = sc.nextInt();
        System.out.println("Enter the mark of the subject Java:");
        int j = sc.nextInt();
        System.out.println("Enter the mark of the subject Physics:");
        int ph = sc.nextInt();
        Calculator c = new Calculator(ds,m,ch,j,ph);
        c.TotalMarks();
        System.out.println();
        c.AveragePercentage();
        System.out.println();
        c.Grade();
        System.out.println();
        c.OverallGrade(c.ap);
    }
}