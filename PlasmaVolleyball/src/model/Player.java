package model;

import model.animation.Jump;
import model.animation.Move;


public class Player extends GameObject {
	private String nick;
	private boolean animated = false;
	private Match match;
	
	public Player(int x, int y, String pathImage, Match match, String nick) {
		super(x, y, pathImage);
		this.nick = nick;
		this.match = match;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public Match getMatch() {
		return match;
	}
	
	public boolean isAnimated() {
		return animated;
	}
	
	public void setAnimated(boolean animated) {
		this.animated = animated;
	}

	public void up() {
		//if (!isAnimated()) {
			new Thread(new Jump(1.1, 100, 240, this)).start();
		//}
	}
	
	public void left() {
		//if (!isAnimated()) {
			new Thread(new Move(0.1, 20, 20, this, true)).start();
		//}
	}
	
	public void right() {
		//if (!isAnimated()) {
			new Thread(new Move(0.1, 20, 20, this, false)).start();
		//}
	}

}
