import grading2.*;

public class Main2 {
    static final String[] als = {
            "25653443S;Garcia Gomez, Juan;8.1",
            "23322443K;Lopez Turo, Manuel;4.3",
            "24433522M;Merlo Martinez, Juana;5.3",
            "53553421D;Santana Medina, Petra;-7.1",
            "55343442L,Godoy Molina, Marina;6.3",
            "34242432J;Fernandez Vara, Pedro;2.k",
            "42424312G;Lopez Gama, Luisa;7.1" };
    public static void main(String[] args) { 
        try {
            Subject algebra = new Subject("Algebra", als); 
            try {
                Student al1 = new Student("23322443k", "Lopez Turo, Manuel"); 
                Student al2 = new Student("34242432J", "Fernandez Vara, Pedro"); 
                System.out.println("Grade of " + al1 + ": "
                        + algebra.getGrade(al1));
                System.out.println("Grade of " + al2 + ": "
                        + algebra.getGrade(al2)); }
            catch (StudentException e) {
                System.err.println(e.getMessage());
            }
            try {
                AverageCalculation m1 = new ArithmeticMean();
                AverageCalculation m2 = new HarmonicMean();
                TrimmedMean m3 = new TrimmedMean(5.0, 9.0); 
                System.out.println("Arithmetic Mean: " + algebra.getAverage(m1)); 
                System.out.println("Harmonic Mean: " + algebra.getAverage(m2)); 
                System.out.println("Mean of values in the range ["+m3.getMin()+", "
                        + m3.getMax()+"]: " + algebra.getAverage(m3)); } 
            catch (StudentException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Students...");
            for (Student student : algebra.getStudents()) {
                System.out.println(student + ": " + student.getGrade());
            }
            System.out.println("Errors...");
            for (String error : algebra.getErrors()) {
                System.out.println(error);
            }
            System.out.println(algebra);

        } catch (Exception e) { 
            System.err.println(e.getMessage());
        } 
    }
}