package model.connection;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.Random;
import java.util.StringTokenizer;

import model.Player;

class Listener extends Thread {
	Socket conn = null;
	boolean listening = true;
	IServerRequester serverRequester;

	public void setServerRequester(IServerRequester serverRequester) {
		this.serverRequester = serverRequester;
	}

	private static final int MAX_BUFFER = 500;

	public Listener(Socket conn) {
		this.conn = conn;
		this.setName("Listener");
	}

	public void connect() {
		this.start();
	}

	@Override
	public void run() {
		InputStream instream = null;
		try {
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader reader = new BufferedReader(isr);

			char[] buffer = new char[MAX_BUFFER];
			while (listening) {

				int bytes = reader.read(buffer);
				if (bytes < 0) {
					// po³¹czenie zerwane lub b³¹d
					return;
				}

				System.out.print("Odebralem:");
				System.out.println(buffer);
				String message = new String(buffer);
				parseMessage(message);
				for (int i = 0; i < MAX_BUFFER; i++) {
					buffer[i] = 0;
				}

			}
		} catch (StreamCorruptedException sce) {
			// skip over the bad bytes
			try {
				if (instream != null)
					instream.skip(instream.available());
			} catch (Exception e1) {
				listening = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			listening = false;
		}
	}

	private void parseMessage(String message) // TODO zaimplementuj kolejne
												// przypadki
	{
		new Random().nextBoolean();

		if (serverRequester != null) {
			if (message.contains("<request type='game' what='start'")) {
				serverRequester.startGame(new String[] { "Krzysztof",
						"Wojciech" }); // TODO mock
			} else
			/*
			 * if (message.contains("where='0'/>")) //TODO parsuj wszystkie
			 * wiadomosci, nie tylko jedno containts, narazie takze tez wybieram
			 * losowo kto sie ruszyl { serverRequester.MOCK_movePlayer(((new
			 * Random().nextBoolean())?"Krzysztof":"Wojciech"), 0); } else if
			 * (message.contains("where='1'/>")) {
			 * serverRequester.MOCK_movePlayer(((new
			 * Random().nextBoolean())?"Krzysztof":"Wojciech"), 1); }
			 */
			if (message
					.contains("<reply type='move' nick='Krzysztof' where='0'/>")) {
				serverRequester.MOCK_movePlayer("Krzysztof", 0);
			} else if (message
					.contains("<reply type='move' nick='Krzysztof' where='1'/>")) {
				serverRequester.MOCK_movePlayer("Krzysztof", 1);
			} else if (message
					.contains("<reply type='move' nick='Krzysztof' where='2'/>")) {
				serverRequester.MOCK_movePlayer("Krzysztof", 2);
			} else if (message
					.contains("<reply type='move' nick='Wojciech' where='0'/>")) {
				serverRequester.MOCK_movePlayer("Wojciech", 0);
			} else if (message
					.contains("<reply type='move' nick='Wojciech' where='1'/>")) {
				serverRequester.MOCK_movePlayer("Wojciech", 1);
			} else if (message
					.contains("<reply type='move' nick='Wojciech' where='2'/>")) {
				serverRequester.MOCK_movePlayer("Wojciech", 2);
			}

		} else {
			// TODO nie bylo serverRequestera zapodanego
		}
	}

}