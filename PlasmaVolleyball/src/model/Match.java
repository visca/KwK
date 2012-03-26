package model;

import java.awt.Graphics;

import view.GameFrame;



public class Match {
	private Player home, away;
	private Ball ball;
	private Net net;
	private Player yourPlayer;
	
	public Match(String[] players, boolean youAreHome) {
		home = new Player(100, 400, "\\img\\playingPlayerHome.png", players[0]); //TODO narazie tylko 2 graczy
		away = new Player(600, 400, "\\img\\playingPlayerAway.png", players[1]);
		ball = new Ball(370, 120, "\\img\\playingBall.png");
		net = new Net(390, 270, "\\img\\playingNet.png");
		
		yourPlayer = youAreHome ? home : away;
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

}

