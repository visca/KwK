package view.state;

import java.awt.Graphics;

import model.Match;

import view.GameFrame;


public class Playing extends GameState {
	private Match match;

	public Playing(GameFrame frame, String[] players) {
		super(frame);
		background =  loadImage("\\img\\playingBackground.jpg");
		
		if (frame.getYourNick().equals(players[0])) {
			match = new Match(players,this,true);
		} 
		else if (frame.getYourNick().equals(players[1])) {
			match = new Match(players,this,false);
		} 
		else {
			//TODO rzuc jakims wyjatkiem czy cos
		}
		
	}

	@Override
	public void up() {
		if (match.getYourPlayer().getAnimated() == null) {
			frame.getSocketClient().getClientRequester().up();
		}
	}

	@Override
	public void left() {
		if (match.getYourPlayer().getAnimated() == null) {
			frame.getSocketClient().getClientRequester().left();
		}
	}

	@Override
	public void right() {
		if (match.getYourPlayer().getAnimated() == null) {
			frame.getSocketClient().getClientRequester().right();
		}
	}

	@Override
	public void escape() {
		frame.setPause();
	}

	@Override
	public void enter() {}
	
	@Override
	public void down() {}
	
	public void drawGameObjects(Graphics g, GameFrame frame) {
		match.drawGameObjects(g, frame);
	} 
	
	public Match getMatch()
	{
		return match;
	}
	
}