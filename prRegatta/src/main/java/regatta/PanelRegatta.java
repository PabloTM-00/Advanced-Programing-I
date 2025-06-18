package regatta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelRegatta extends JPanel implements ViewRegatta {
	JTextField jtfInputFile;
	JTextField jtfOutputFile;
	JButton jbRead;
	JButton jbMove;
	JButton jbSave;
	JTextArea jtaText;
	JLabel jlMessage;
	
	public PanelRegatta() {
		setLayout(new BorderLayout());
		jtfInputFile = new JTextField(20);
		jtfInputFile.setBorder(BorderFactory.createTitledBorder("Input File Name"));
		jtfOutputFile = new JTextField(20);
		jtfOutputFile.setBorder(BorderFactory.createTitledBorder("Output File Name"));
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelNorth.add(jtfInputFile);
		panelNorth.add(jtfOutputFile);
		add(panelNorth, BorderLayout.NORTH);

		jbRead = new JButton("Read");
		jbMove = new JButton("Move");
		jbSave = new JButton("Save");
		JPanel jpRight = new JPanel();
		jpRight.setLayout(new BoxLayout(jpRight, BoxLayout.Y_AXIS));
		jpRight.add(jbRead);
		jpRight.add(jbMove);
		jpRight.add(jbSave);
		add(jpRight, BorderLayout.EAST);
		
		jtaText = new JTextArea(30,70);
		add(new JScrollPane(jtaText), BorderLayout.CENTER);
		
		jlMessage = new JLabel(" ");
		add(jlMessage, BorderLayout.SOUTH);
	}
	
	
	@Override
	public String getInputFile() {
		return jtfInputFile.getText();
	}

	@Override
	public String getOutputFile() {
		return jtfOutputFile.getText();
	}

	
	@Override
	public void addLine(String line) {
		jtaText.append(line+"\n");
	}
	
	@Override
	public void clear() {
		jtaText.setText("");
	}
	
	@Override
	public void setMesssage(String msg) {
		jlMessage.setText(msg);
	}
	
	public void controller(ActionListener ctr) {
		jbRead.setActionCommand(ViewRegatta.READ);
		jbRead.addActionListener(ctr);
		jbSave.setActionCommand(ViewRegatta.SAVE);
		jbSave.addActionListener(ctr);
		jbMove.setActionCommand(ViewRegatta.MOVE);
		jbMove.addActionListener(ctr);
	}
}
