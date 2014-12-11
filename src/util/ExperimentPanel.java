package util;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class ExperimentPanel extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	
	public final int width = 1280;
	public final int height = 720;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private int FPS = 60;
	private long targetTime = 1000/FPS;
	
	private ExperimentStateManager esm;

	public ExperimentPanel(){
		setPreferredSize(new Dimension(width,height));
		
		addKeyListener(this);
		setFocusable(true);
		
		start();
	}
	
	private void start(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		long start, elapsed, wait;
		
		esm = new ExperimentStateManager(this);
		
		while(isRunning){
			start = System.nanoTime();
			
			tick();
			repaint();
			
			elapsed = System.nanoTime() - start;
			wait = (targetTime - elapsed)/1000000;
			
			if(wait <= 0){
				wait = 5;
			}
			
			try{
				Thread.sleep(wait);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void tick(){
		esm.tick();
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(g != null){
			super.paintComponent(g);
		
			g.clearRect(0, 0, width, height);
		
			esm.draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		esm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		esm.keyReleased(e.getKeyCode());
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	
}
