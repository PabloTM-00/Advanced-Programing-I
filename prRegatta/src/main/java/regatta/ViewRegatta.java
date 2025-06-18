package regatta;

import java.awt.event.ActionListener;

public interface ViewRegatta {

	String READ = "R"; 	
	String MOVE = "M"; 	
	String SAVE = "S";		
	
	/**
	 * Input File
	 * @return the name of the input file
	 */
	String getInputFile();

	/**
	 * Output File
	 * @return the name of the output file
	 */
	String getOutputFile();

	/**
	 * The method adds a line
	 * @param line  the line to add 
	 * A jump to the next line is added automatically
	 */
	void addLine(String line);

	/**
	 * It clears the text area
	 */
	void clear();

	/**
	 * A message is written at the bottom of the panel
	 * @param msg
	 */
	void setMesssage(String msg);

	/**
	 * The controller is registered into the view buttons
	 * @param ctr the controller to register
	 */
	void controller(ActionListener ctr);
}