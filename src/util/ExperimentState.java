package util;
import java.awt.Graphics;

public abstract class ExperimentState {
	protected ExperimentStateManager esm;
	
	public ExperimentState(ExperimentStateManager esm){
		this.esm = esm;
		init();
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void draw(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
