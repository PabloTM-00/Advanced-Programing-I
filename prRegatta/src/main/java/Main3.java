import java.io.FileNotFoundException;
import java.io.PrintWriter;

import regatta.Regatta;

public class Main3 {
	public static void main(String [] args) throws FileNotFoundException {
		Regatta regatta = new Regatta();
		regatta.readFile("ships.txt");
		PrintWriter out = new PrintWriter(System.out,true);
		regatta.writeTo(out);
		regatta.move(10);
		regatta.writeToFile("output.txt");
		regatta.writeTo(out);
	}
}
