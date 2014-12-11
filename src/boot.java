import java.awt.BorderLayout;

import javax.swing.JFrame;

import util.ExperimentPanel;


public class boot {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Experiment 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(BorderLayout.CENTER, new ExperimentPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
