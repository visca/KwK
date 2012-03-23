package view.state;

import java.awt.Graphics;

import controller.Match;
import view.GameFrame;


public class Playing extends GameState {
	private Match match;

	public Playing(GameFrame frame, boolean youAreHome) {
		super(frame);
		background =  loadImage("\\img\\playingBackground.jpg");
		match = new Match(youAreHome);
	}

	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void left() {
		match.getYourPlayer().left();
		frame.repaint();
	}

	@Override
	public void right() {
		match.getYourPlayer().right();
		frame.repaint();
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
