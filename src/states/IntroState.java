package states;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import util.Data;
import util.ExperimentState;
import util.ExperimentStateManager;


public class IntroState extends ExperimentState implements ActionListener {
	
	private JFormattedTextField participantField;
	private JLabel participantLabel;
	
	public IntroState(ExperimentStateManager esm) {
		super(esm);
		
		esm.panel.setLayout(new GridBagLayout());
	}

	@Override
	public void init() {
		
		participantLabel = new JLabel("Enter participant number (e. g. 001) : ");
		participantLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		
		esm.panel.add(participantLabel);
		
		participantField = new JFormattedTextField();
		participantField.setColumns(10);
		participantField.addActionListener(this);
		
		esm.panel.add(participantField);
		esm.panel.updateUI();
		
	}

	
	@Override
	public void tick() {
		
	}

	@Override
	public void draw(Graphics g) {
		
	}

	
	@Override
	public void keyPressed(int k) {
		/*Debugging
		//press 1 for practice trials, press 2 for recorded trials
		
		if(k == KeyEvent.VK_1) { 
			Data.participantNumber = "999";
			esm.panel.removeAll();
			esm.states.push(new EndState(esm));
			
		}
		
		if(k == KeyEvent.VK_2) { 
			Data.participantNumber = "999";
			esm.panel.removeAll();
			esm.states.push(new TrialState(esm, false));
			
		}
		
		Debug end
		*/
	}

	
	@Override
	public void keyReleased(int k) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Data.participantNumber = participantField.getText();
		
		esm.panel.removeAll();
		
		esm.states.push(new ExplanationStateOne(esm));
		
	}

}
