package states;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import util.ExperimentState;
import util.ExperimentStateManager;


public class ExplanationStateOne extends ExperimentState {
	
	private JLabel explanationLabel;
	private JLabel explanationLabel2;
	private JLabel initiationLabel;

	
	public ExplanationStateOne(ExperimentStateManager esm) {
		super(esm);
		
		init();
	}

	@Override
	public void init() {
		GridBagConstraints c = new GridBagConstraints();
		
		explanationLabel = new JLabel("U krijgt zometeen...[uitleg]");
		explanationLabel.setFont(new Font("Arial", Font.PLAIN, 20));

		c.gridx = 0;
		c.gridy = 0;
		esm.panel.add(explanationLabel, c);
		
		explanationLabel2 = new JLabel("Eerst krijgt u een aantal oefenronden.");
		explanationLabel2.setFont(new Font("Arial", Font.PLAIN, 20));

		c.gridx = 0;
		c.gridy = 1;
		esm.panel.add(explanationLabel2, c);
		
		initiationLabel = new JLabel("-- Druk op ENTER om door te gaan --");
		initiationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		c.gridx = 0;
		c.gridy = 3;
		esm.panel.add(initiationLabel, c);
		esm.panel.updateUI();
	}

	@Override
	public void tick() {
		//System.out.println("Running Explanation state one");
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) { 
			esm.states.push(new TrialState(esm, true));
			esm.panel.removeAll();
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
