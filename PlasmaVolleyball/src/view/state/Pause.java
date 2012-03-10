package view.state;

import view.GameFrame;
import view.menu.Menu;
import view.menu.MenuItem;


public class Pause extends GameState {

	public Pause(GameFrame frame) {
		super(frame);
		background =  loadImage("\\img\\playingBackground.jpg");
		menu = new Menu(frame.getWidth(), frame.getHeight());
		menu.add(new MenuItem(loadImage("\\img\\pauseReturn.gif"), loadImage("\\img\\pauseReturnSelected.gif")));
		menu.add(new MenuItem(loadImage("\\img\\pauseEscape.gif"), loadImage("\\img\\pauseEscapeSelected.gif")));
	}

	@Override
	public void enter() {
		switch (menu.getSelectedIndex()) {
			case 0: 
				frame.setPlaying(false);
				break;
			case 1:
				frame.setMenuGame();
				break;
		}
	}

	@Override
	public void escape() {
		frame.setPlaying(false);
	}
	
	@Override
	public void left() {}

	@Override
	public void right() {}


}
