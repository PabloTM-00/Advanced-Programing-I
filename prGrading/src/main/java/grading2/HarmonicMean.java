package grading2;
 
import java.util.List;

public class HarmonicMean implements AverageCalculation {

    @Override
    public double calculate(List<Student> sts) throws StudentException {
        double denominatorSum = 0;
        int count = 0;

        for (Student s : sts) {
            double grade = s.getGrade();
            if (grade > 0) {
                denominatorSum += 1.0 / grade;
                count++;
            }
        }

        if (count == 0) {
            throw new StudentException("No students");
        }

        return count / denominatorSum;
    }
}
