package model.animation;

import model.Player;

public class Jump implements Runnable {
	private double animationTimePerMiliseconds;
	private double v0;
	private double g;
	private int frequencyPerSeconds;
	private Player player;
	
	public Jump(double animationTimePerSeconds, int frequencyPerSeconds, int height, Player player) {
		this.animationTimePerMiliseconds = animationTimePerSeconds*1000;
		this.frequencyPerSeconds = frequencyPerSeconds;
		this.g = 8*height/(animationTimePerSeconds*animationTimePerSeconds);
		this.v0 = Math.sqrt(2*height*g);
		this.player = player;
	}

	@Override
	public void run() {
		player.setAnimated(true);
		double stepTime = (int)(animationTimePerMiliseconds/(frequencyPerSeconds+1));
		int numberOfSteps = (int)(1.1*frequencyPerSeconds);
		int initPlayer = player.getY();
		for (int step=0; step<numberOfSteps+1; step++) {
			double t = (double)step/frequencyPerSeconds;
			int height = (int)(v0*t - (g*t*t/2));
			player.setY(initPlayer - height);
			player.getMatch().getPlayingState().getFrame().repaint();
			try { Thread.sleep((int)stepTime); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		player.setAnimated(false);
	}
	
}
