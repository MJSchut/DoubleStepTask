package util;

public class Vector2 {
	
	private int x = 0;
	private int y = 0;
	
	public Vector2(){
		
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
