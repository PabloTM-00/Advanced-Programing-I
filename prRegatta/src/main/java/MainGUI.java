
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;

import regatta.CtrRegatta;
import regatta.PanelRegatta;
import regatta.ViewRegatta;

public class MainGUI {
	public static void main(String [] args) {
		ViewRegatta viewRegatta = new PanelRegatta();
		ActionListener ctrRegatta = new CtrRegatta(viewRegatta);
		viewRegatta.controller(ctrRegatta);
		
		JFrame window = new JFrame("Regatta");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane((JPanel)viewRegatta) ;
		window.pack();
		window.setVisible(true);
	}
}
