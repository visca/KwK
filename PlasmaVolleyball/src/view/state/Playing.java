package view.state;

import java.awt.Graphics;

import model.Match;

import view.GameFrame;


public class Playing extends GameState {
	public Match getMatch()
	{
		return match;
	}

	private Match match;

	public Playing(GameFrame frame, String[] players) {
		super(frame);
		background =  loadImage("\\img\\playingBackground.jpg");
		
		if (frame.getYourNick().equals(players[0])) {
			match = new Match(players,true);
		} 
		else if (frame.getYourNick().equals(players[1])) {
			match = new Match(players,false);
		} 
		else {
			//TODO rzuc jakims wyjatkiem czy cos
		}
		
	}

	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void left() {
		frame.getSocketClient().getClientRequester().left();
		//match.getYourPlayer().left();
		//frame.repaint();
	}

	@Override
	public void right() {
		frame.getSocketClient().getClientRequester().right();
		//match.getYourPlayer().right();
		//frame.repaint();
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
	
}