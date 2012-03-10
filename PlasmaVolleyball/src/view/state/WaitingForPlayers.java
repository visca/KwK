package view.state;

import view.GameFrame;


public class WaitingForPlayers extends GameState {

	public WaitingForPlayers(GameFrame frame) {
		super(frame);
		background =  loadImage("\\img\\waitingForPlayersBackground.jpg");
	}

	@Override
	public void escape() {
		frame.setMenuGame();
	}
	
	//tymczasowo, docelowo brak zadnej reakcji na up
	@Override
	public void up() {
		frame.setPlaying(true);
	}

	@Override
	public void down() {}

	@Override
	public void left() {}

	@Override
	public void right() {}

	@Override
	public void enter() {}


}
