package model;


public class Player extends GameObject {

	public Player(int x, int y, String pathImage) {
		super(x, y, pathImage);
	}
	
	public void left() {
		int x = getX();
		setX(x - 10);
	}
	
	public void right() {
		int x = getX();
		setX(x + 10);
	}

}
