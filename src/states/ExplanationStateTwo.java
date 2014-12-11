package states;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import util.ExperimentState;
import util.ExperimentStateManager;


public class ExplanationStateTwo extends ExperimentState {
	
	private JLabel explanationLabel;
	private JLabel initiationLabel;
	
	public ExplanationStateTwo(ExperimentStateManager esm) {
		super(esm);
	}

	@Override
	public void init() {
		GridBagConstraints c = new GridBagConstraints();
		
		explanationLabel = new JLabel("Nu begint de echte taak.");
		explanationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		c.gridx = 0;
		c.gridy = 0;
	
		esm.panel.add(explanationLabel,c);
		
		initiationLabel = new JLabel("-- Druk op ENTER om door te gaan --");
		initiationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		c.gridx = 0;
		c.gridy = 1;
		esm.panel.add(initiationLabel,c);
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
			esm.states.push(new TrialState(esm, false));
			esm.panel.removeAll();
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
