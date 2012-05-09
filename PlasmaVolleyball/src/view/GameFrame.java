package view;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.geom.Point2D;

import model.Player;
import model.connection.IServerRequester;
import model.connection.SocketClient;

import view.state.GameState;
import view.state.MenuGame;
import view.state.Pause;
import view.state.Playing;
import view.state.Score;
import view.state.WaitingForPlayers;


public class GameFrame extends DoubleBuffer {
	private GameState state;
	private GameState menuGame, waitingForPlayers, playing, pause, score;
	private int width, height;
	private SocketClient socketClient = null;
	private IServerRequester serverRequester = null;
	private String yourNick = "Krzysztof";
	


	public GameFrame(int width, int height) {
    	super("Plasma Volleyball");
    	setLocation(200, 100);
    	this.width = width;
    	this.height = height;
    	
        setSize(width, height);
        setResizable(false);
        addKeyListener(new GameListener());
        addWindowListener(new WindowListener());
        
        menuGame = new MenuGame(this);
        waitingForPlayers = new WaitingForPlayers(this);
        playing = null;
        pause = new Pause(this);
        score = new Score(this);
        state = menuGame;
        
        serverRequester =  new IServerRequester() {	
			@Override
			public void startGame(String[] players) {
				if (state == waitingForPlayers) {
					setNewPlaying(players);
				}
			}
			
			@Override
			public void sendPositions(Point2D[] points) {
				// TODO
			}

			@Override
			public void MOCK_movePlayer(String nick, int move) {
				if (state == playing) {
					Player playerToMove = ((Playing)playing).getMatch().getPlayerByNick(nick);
					if (move == 0) {
						playerToMove.left();
					} 
					else if (move==1) {
						playerToMove.right();
					}
					else if (move==2) {
						playerToMove.up();
					}
				}
				
				repaint();
			}
		};
        
    }
	
	
    
    public void paintBuffer(Graphics g) {
    	g.drawImage(state.getBackground(), 0, 0, this);
    	
    	if (state == playing) {
    		((Playing)state).drawGameObjects(g, this);
    	}
    	else if (state == menuGame) {
    		state.drawMenu(g);
    	}
    	else if (state == waitingForPlayers) {
    		// TODO
    	}
    	else if (state == pause) {
    		state.drawMenu(g);
    	}
    	else if (state == score) {
    		// TODO
    	}
    	
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void setMenuGame() {
    	setState(menuGame);
    }
    
    public void setWaitingForPlayers() {
    	setState(waitingForPlayers);
    }
    
    public void setNewPlaying(String[] players) {
    	playing = new Playing(this, players);
    	setState(playing);
    }
    
    public void setPlaying() {
    	setState(playing);
    }
    
    public void setPause() {
    	setState(pause);
    }
    
    public void setScore() {
    	setState(score);
    }
    
    private void setState(GameState state) {
    	this.state = state;
    	repaint();
    }
    
    public IServerRequester getServerRequester() {
		return serverRequester;
	}

    public String getYourNick() {
		return yourNick;
	}
    
	public SocketClient getSocketClient() {
		return socketClient;
	}

	public void setSocketClient(SocketClient socketClient) {
		this.socketClient = socketClient;
	}
	
	public void close() {
    	dispose();
        System.exit(0);
    }
    
    
    class GameListener implements KeyListener {

    	@Override
    	public void keyPressed(KeyEvent e) {
    		
    		switch (e.getKeyCode()) {
    			case KeyEvent.VK_UP:
    				state.up();
    				break;
    			case KeyEvent.VK_DOWN:
    				state.down();
    				break;
    			case KeyEvent.VK_LEFT:
    				state.left();
    				break;
    			case KeyEvent.VK_RIGHT:
    				state.right();
    				break;
    			case KeyEvent.VK_ENTER:
    				state.enter();
    				break;
    			case KeyEvent.VK_ESCAPE:
    				state.escape();
    				break;
    			case KeyEvent.VK_2:
    				yourNick = yourNick.equals("Wojciech") ? "Krzysztof" : "Wojciech"; //TODO na ten czas do zmieniania graczy
    				break;
    		}
    		
    	}

    	@Override
    	public void keyReleased(KeyEvent e) {}

    	@Override
    	public void keyTyped(KeyEvent e) {}
    	
    }
    
    
    class WindowListener extends WindowAdapter {
    	
    	@Override
        public void windowClosing(WindowEvent e) {
        	close();
        }
        
     }
    
    
}