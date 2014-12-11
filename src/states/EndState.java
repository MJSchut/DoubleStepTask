package states;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;

import util.Data;
import util.ExperimentState;
import util.ExperimentStateManager;


public class EndState extends ExperimentState {

	private JLabel explanationLabel;
	
	public EndState(ExperimentStateManager esm) {
		super(esm);
		
	}

	@Override
	public void init() {
		GridBagConstraints c = new GridBagConstraints();
		
		writeFile();
		
		explanationLabel = new JLabel("U bent nu klaar. Druk op ENTER om het programma af te sluiten");
		explanationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		c.gridx = 0;
		c.gridy = 0;
		esm.panel.add(explanationLabel);
		esm.panel.updateUI();

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) { 
			System.exit(0);
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
	
	private void writeFile(){
		BufferedWriter writer = null;
		File file;
		
		try {
			
			String currentPath = new File(System.getProperty("java.class.path")).getParent();
			new File(currentPath + File.separator + "Data").mkdir();
			file = new File(currentPath + File.separator + "Data" + File.separator + Data.participantNumber + ".txt");
			
			file.createNewFile(); 
			
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("Participantnumber");
			writer.newLine();
			writer.write(Data.participantNumber);
			writer.close();
			
			
		} catch (IOException e) {
			
		}
	}

}
