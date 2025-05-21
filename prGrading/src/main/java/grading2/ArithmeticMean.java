package grading2;

import java.util.List;

public class ArithmeticMean implements AverageCalculation {

    @Override
    public double calculate(List<Student> sts) throws StudentException {
        if (sts == null || sts.isEmpty()) {
            throw new StudentException("No students");
        }

        double sum = 0;
        for (Student s : sts) {
            sum += s.getGrade();
        }

        return sum / sts.size();
    }
}
