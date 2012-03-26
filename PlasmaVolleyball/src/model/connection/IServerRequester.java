package model.connection;

import java.awt.geom.Point2D;

import model.Player;

public interface IServerRequester {
	void startGame(String[] players);
	void sendPositions(Point2D[] points);
	void MOCK_movePlayer(String nick, int move);
}
