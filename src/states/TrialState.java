package states;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.Timer;

import util.ExperimentState;
import util.ExperimentStateManager;
import util.Vector2;


public class TrialState extends ExperimentState {
	
	boolean done = false;
	boolean ready = false;
	JLabel initiationLabel;
	
	private boolean practice;
	private Timer saccadeOneTimer;
	private Timer saccadeTwoTimer;
	private Timer saccadeTwoPause;
	private boolean drawSaccadeCrossOne = false;
	private boolean drawSaccadeCrossTwo = false;
	private boolean startNextTrial = false;
	private int timerOneDelays[];
	private int timerTwoDelays[];
	private int timerTwoPauseDelay = 750;

	Vector2[] locations;
	private Vector2 targetOneLocation, targetTwoLocation;
	
	private int width = esm.panel.width;
	private int height = esm.panel.height;
	private int trials;
	
	protected Graphics g;
	
	public TrialState(ExperimentStateManager esm, boolean practice) {
		super(esm);
		
		this.practice = practice;
		
		init();
	}

	@Override
	public void init() {
		if(practice){
			trials = 5;
		}
		else {
			trials = 10;
		}
		
		timerOneDelays = new int[4];
		for(int i = 0; i < timerOneDelays.length;i++){
			timerOneDelays[i] = (1500 + (i*500));
		}
		
		timerTwoDelays = new int[5];
		
		timerTwoDelays[0] = 110;
		timerTwoDelays[1] = 150;
		timerTwoDelays[2] = 190;
		timerTwoDelays[3] = 240;
		timerTwoDelays[4] = 280;
		
		generateLabels();
		generateTimers();
		generateLocations();
	}

	@Override
	public void tick() {
		if(!ready){
			
		} else if(ready){
			
			
			if (trials > 0){
				if(!drawSaccadeCrossOne && !saccadeOneTimer.isRunning() && !saccadeTwoTimer.isRunning() &&
						!saccadeTwoPause.isRunning()){
					saccadeOneTimer.setInitialDelay(TimerOneDelay());
					saccadeOneTimer.start();
					
					int one = randomLocation();
					int two = randomLocation(one);
					
					targetOneLocation = locations[one];
					targetTwoLocation = locations[two];
					System.out.println("Before saccadecross one in trial " + trials + "Location one: " + one + ", Location two: " + two);
					
					while(targetOneLocation == targetTwoLocation){
						targetOneLocation = locations[randomLocation()];
						targetTwoLocation = locations[randomLocation()];
					}
				}
				if (drawSaccadeCrossOne && !saccadeTwoTimer.isRunning()){
					System.out.println("Draw saccadecross one in trial " + trials);
					saccadeOneTimer.stop();
		        	saccadeTwoTimer.setInitialDelay(TimerTwoDelay());
					saccadeTwoTimer.start();
				} else if (drawSaccadeCrossTwo && !saccadeTwoPause.isRunning()){
					System.out.println("Draw saccadecross two in trial " + trials);
					saccadeTwoTimer.stop();
		        	saccadeTwoPause.setInitialDelay(timerTwoPauseDelay);
		        	saccadeTwoPause.start();
				} else if (startNextTrial){
					System.out.println("Start next trial in " + trials);
		        	saccadeTwoPause.stop();
					trials-=1;
					drawSaccadeCrossOne = false;
					drawSaccadeCrossTwo = false;
					startNextTrial = false;
					
				}
			}
			
			else if(trials <= 0){
				done = true;
				if(practice)
					esm.states.push(new ExplanationStateTwo(esm));
				else if(!practice)
					esm.states.push(new EndState(esm));
			
			}
			
		} 
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		
		if(!ready){
			esm.panel.add(initiationLabel);
			esm.panel.updateUI();
			
		} else if(ready){
			drawFixationCross(g);
		
			if(drawSaccadeCrossOne){
				drawSaccadeCross(g, 1);
			}
			
			if(drawSaccadeCrossTwo){
				drawSaccadeCross(g, 2);
			}
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_SPACE && !ready) { 
			esm.panel.removeAll();
			ready = true;
		}
		
	}

	@Override
	public void keyReleased(int k) {

		
	}
	
	void drawFixationCross(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		g.drawString("+", (width/6)*3, (height/6)*3);	
	}
	
	void drawSaccadeCross(Graphics g, int num){
		
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		
		switch (num){
		case 1:
			g.setColor(Color.WHITE);
			g.drawString("+", targetOneLocation.getX(), targetOneLocation.getY());	
			break;
		
		case 2: 
			g.setColor(Color.WHITE);
			g.drawString("+", targetTwoLocation.getX(), targetTwoLocation.getY());	
			
			break;
		}
	}
	

	

	private void generateLabels() {
		initiationLabel = new JLabel("-- Druk op de SPATIE BALK om te beginnen --");
		initiationLabel.setForeground(Color.GRAY);
		esm.panel.add(initiationLabel);
		esm.panel.updateUI();
		
	}
	
	private void generateTimers() {
		
		saccadeOneTimer = new Timer(TimerOneDelay(), null);
		saccadeOneTimer.addActionListener(new ActionListener() {
	        @Override
			public void actionPerformed(ActionEvent e) {
	        	drawSaccadeCrossOne = true;
	        	
	        }
	    });
		
		saccadeTwoTimer = new Timer(TimerTwoDelay(), null);
		saccadeTwoTimer.addActionListener(new ActionListener() {
	        @Override
			public void actionPerformed(ActionEvent e) {
	        	drawSaccadeCrossOne = false;
	        	drawSaccadeCrossTwo = true;
	        	
	        }
	    });
		
		saccadeTwoPause = new Timer(timerTwoPauseDelay, null);
		saccadeTwoPause.addActionListener(new ActionListener() {
	        @Override
			public void actionPerformed(ActionEvent e) {
	        	startNextTrial = true;
	        	
	        }
	    });
		
	}
	
	private void generateLocations() {
		
		targetOneLocation = new Vector2();
		targetTwoLocation = new Vector2();
		locations = new Vector2[10];

		for (int i = 0; i < 10; i++){
			locations[i] = new Vector2();
			if (i <= 4){
				locations[i].setLocation(width/6 + (i*(width/6)), height/6);
			} else {
				locations[i].setLocation(width/6 + ((i-5)*(width/6)), (height/6)*5);
			}
		}
		
	}
	
	private int randomLocation(){
		int choice = -1;
		while (choice == -1){
			int i = locations.length;
			Random RNG = new Random();
			choice = RNG.nextInt(i);
		}
		return choice;
	}
	
	private int randomLocation(int not){
		int choice = -1;
		int tries = 0;
		
		while (choice == not || choice == -1){
			int i = locations.length;
			Random RNG = new Random();
			choice = RNG.nextInt(i);
			tries++;
			
			if(tries > 3){
				if(not > 0)
				choice = not-1;
				else choice = not+1;
			}
		}
		return choice;
	}
	
	private int TimerOneDelay(){
		int delay = 1500;
		
		int i = timerOneDelays.length;
		Random RNG = new Random();
		i = RNG.nextInt(i);
		
		
		delay = timerOneDelays[i];
		System.out.println("Delay one is: " + delay);
		
		return delay;
	}
	
	private int TimerTwoDelay(){
		int delay = 250;
		
		int i = timerTwoDelays.length;
		Random RNG = new Random();
		i = RNG.nextInt(i);
		
		
		delay = timerTwoDelays[i];
		System.out.println("Delay two is: " + delay);
		
		return delay;
	}
	
}
