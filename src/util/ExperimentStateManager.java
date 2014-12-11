package util;

import java.awt.Graphics;
import java.util.Stack;

import states.IntroState;

public class ExperimentStateManager {

	public Stack<ExperimentState> states;
	public ExperimentPanel panel;
	
	public ExperimentStateManager(ExperimentPanel panel) {
		this.panel = panel;
		states = new Stack<ExperimentState>();
		states.push(new IntroState(this));
	}
	
	public void tick() {
		states.peek().tick();
	}
	
	public void draw(Graphics g) {
		states.peek().draw(g);
	}
	
	public void keyPressed(int k) {
		states.peek().keyPressed(k);
	}
	
	public void keyReleased(int k) {
		states.peek().keyReleased(k);
	}
}
