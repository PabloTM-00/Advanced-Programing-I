import simpleFilesWordCounting.*;

public class MainWordCounter {
	public static void main(String[] args) {
		WordCounter counter = new WordCounter(5);
		String [] datos = {
		        "This is the first sentence of the example",
		        "and this is the second one"
		    };
	
		counter.includeAll(datos,"[ ]");
		
		System.out.println(counter);
	}
	
}
