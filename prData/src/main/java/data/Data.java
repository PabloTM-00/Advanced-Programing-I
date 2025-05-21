package data;

import java.util.ArrayList;

public class Data {
    private ArrayList <Double> data = new ArrayList<>();    
    private ArrayList <String> errors = new ArrayList<>();     
    private double min;              
    private double max;
    
    /**
     * These 2 arrays can be initialized outside the constructor because they are independent 
     */
    
    public Data(String[] values, double min, double max) {
        this.min = min;
        this.max = max;
        
        for (String val : values) { //Advanced for loop
        	try {
        		data.add(Double.parseDouble(val));
        	}
        	//We know the error to catch from the documentation
        	catch(NumberFormatException nfe){
        		errors.add(val);
        	}
        }
    }

	public ArrayList<Double> getData() {
		return data;
	}

	public void setData(ArrayList<Double> data) {
		this.data = data;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}
	
	public double calcAverage() throws DataException {
	    double sum = 0;
	    int count = 0;

 	    for (double val : data) {
	        if (val >= min && val <= max) {
	            sum += val;   
	            count++;     
	        }
	    }

 	   if (count == 0) {
 		    throw new DataException("There is no data in the specified range");
 		  }
 		  return sum / count;
	}
	
	public double calcStandardDeviation() {
		double avg = calcAverage();
		
		double sum = 0;
	    int count = 0;
		
	    for (double val : data) {
	        if (val >= min && val <= max) {
	            sum += Math.pow((val - avg), 2);   
	            count++;     
	        }
	    }
	    return Math.sqrt(sum/count);   
	}

	public void setRange(String newRange) throws DataException {
		if (newRange == null || newRange.indexOf(';') == -1) {
	        throw new DataException("Data error setting range");
	    }

	    int i = newRange.indexOf(';');
	    //extract min and max from the string
	    String minStr = newRange.substring(0, i);
	    String maxStr = newRange.substring(i + 1);

	    try {
	        double newMin = Double.parseDouble(minStr);
	        double newMax = Double.parseDouble(maxStr);

	        this.min = newMin;
	        this.max = newMax;

	    } catch (NumberFormatException e) {
	        throw new DataException("Invalid range values: " + e.getMessage());
	    }
	}


	@Override
	public String toString() {
		//strings that store avg and sd separately
	    String averageStr;
	    String stdDevStr;

	    
	    //exceptions thrown in case of errors
	    try {
	        averageStr = String.valueOf(calcAverage());
	        stdDevStr = String.valueOf(calcStandardDeviation());
	    } catch (DataException e) {
	        averageStr = "ERROR";
	        stdDevStr = "ERROR";
	    }

	    return "Min: " + min + ", Max: " + max +
	           data  + errors + "Average: " + averageStr 
	           + ", StandardDeviation: " + stdDevStr;
	}
		
}
