package grading2;

 

import java.util.List;

public class TrimmedMean implements AverageCalculation {
    private double min;
    private double max;

    public TrimmedMean(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public double calculate(List<Student> sts) throws StudentException {
        double sum = 0;
        int count = 0;

        for (Student s : sts) {
            double grade = s.getGrade();
            if (grade >= min && grade <= max) {
                sum += grade;
                count++;
            }
        }

        if (count == 0) {
            throw new StudentException("No students");
        }

        return sum / count;
    }
}
