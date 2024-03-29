package model;

import java.awt.Graphics;

import view.GameFrame;
import view.state.Playing;



public class Match {
	private Player home, away;
	private Ball ball;
	private Net net;
	private Player yourPlayer;
	private Playing playingState;
	
	public Match(String[] players, Playing playingState, boolean youAreHome) {
		home = new Player(100, 400, "\\img\\playingPlayerHome.png", this, players[0]); //TODO narazie tylko 2 graczy
		away = new Player(600, 400, "\\img\\playingPlayerAway.png", this, players[1]);
		ball = new Ball(270, 180, "\\img\\playingBall.png");
		net = new Net(390, 270, "\\img\\playingNet.png");
		
		yourPlayer = youAreHome ? home : away;
		this.playingState = playingState;
	}
	
	public void drawGameObjects(Graphics g, GameFrame frame) {
		g.drawImage(home.getImage(), home.getX(), home.getY(), frame);
		g.drawImage(away.getImage(), away.getX(), away.getY(), frame);
		g.drawImage(ball.getImage(), ball.getX(), ball.getY(), frame);
		g.drawImage(net.getImage(), net.getX(), net.getY(), frame);
	}
	
	public Player getYourPlayer() {
		return yourPlayer;
	}
	
	public Player getPlayerByNick(String nick)
	{
		if (home.getNick().equals(nick)) {
			return home;
		} 
		else if (away.getNick().equals(nick)) {
			return away;
		}
		
		return null;
	}
	
	public Playing getPlayingState() {
		return playingState;
	}

}

