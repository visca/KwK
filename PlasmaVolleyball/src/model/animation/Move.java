package model.animation;

import model.Player;

public class Move implements Runnable {
	private double animationTimePerMiliseconds;
	private int width;
	private int frequencyPerSeconds;
	private Player player;
	private boolean left;
	
	public Move(double animationTimePerSeconds, int frequencyPerSeconds, int width, Player player, boolean left) {
		this.animationTimePerMiliseconds = animationTimePerSeconds*1000;
		this.width = width;
		this.frequencyPerSeconds = frequencyPerSeconds;
		this.player = player;
		this.left = left;
	}

	@Override
	public void run() {
		player.setAnimated(true);
		int numberOfSteps = frequencyPerSeconds;
		int stepTime = (int)(animationTimePerMiliseconds/(numberOfSteps+1));
		double stepWidth = (double)width/(double)numberOfSteps;
		
		int initX = player.getX();
		for (int step=1; step<numberOfSteps+1; step++) {
			if (left) player.setX(initX - (int)(step*stepWidth)); else player.setX(initX + (int)(step*stepWidth));
			player.getMatch().getPlayingState().getFrame().repaint();
			try { Thread.sleep(stepTime); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		player.setAnimated(false);
	}
	
}