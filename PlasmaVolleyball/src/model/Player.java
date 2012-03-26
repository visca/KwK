package model;


public class Player extends GameObject {
	private String nick;
	public Player(int x, int y, String pathImage, String nick) {
		super(x, y, pathImage);
		this.nick = nick;
	}
	
	
	public String getNick() {
		return nick;
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
