package view.state;

import model.connection.SocketClient;
import view.GameFrame;
import view.menu.*;

public class MenuGame extends GameState {

	public MenuGame(GameFrame frame) {
		super(frame);
		background = loadImage("\\img\\menuGameBackground.jpg");
		menu = new Menu(frame.getWidth(), frame.getHeight());
		menu.add(new MenuItem(loadImage("\\img\\menuGameNewGame.gif"),
				loadImage("\\img\\menuGameNewGameSelected.gif")));
		menu.add(new MenuItem(loadImage("\\img\\menuGameEscape.gif"),
				loadImage("\\img\\menuGameEscapeSelected.gif")));
	}

	@Override
	public void enter() {
		switch (menu.getSelectedIndex()) {
		case 0:
			frame.setWaitingForPlayers();
			SocketClient socketClient = new SocketClient("127.0.0.1"); // TODO
																			// tutaj
																			// pobierzemy
																			// z
																			// menu
																			// IP
																			// Serwera
			socketClient.setServerRequester(frame.getServerRequester());
			socketClient.connect();
			// char tmp = (char)((new Random().nextInt(20))+40);

			// socketClient.getClientRequester().join(Character.toString(tmp));
			// //TODO tutaj podamy wpierw nasz nick
			socketClient.getClientRequester().join(frame.getYourNick());
			frame.setSocketClient(socketClient);
			break;
		case 1:
			frame.close();
			break;
		}

	}

	@Override
	public void left() {
	}

	@Override
	public void right() {
	}

	@Override
	public void escape() {
	}

}
