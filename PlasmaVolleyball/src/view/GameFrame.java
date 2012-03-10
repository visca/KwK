package view;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import view.state.GameState;
import view.state.MenuGame;
import view.state.Pause;
import view.state.Playing;
import view.state.Score;
import view.state.WaitingForPlayers;


public class GameFrame extends Frame {
	private GameState state;
	private GameState menuGame, waitingForPlayers, playing, pause, score;
	private int width, height;
	
	
    public GameFrame(int width, int height) {
    	super("Plasma Volleyball");
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
    }
    
    public void paint(Graphics g) {
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
    
    public void setMenuGame() {
    	setState(menuGame);
    }
    
    public void setWaitingForPlayers() {
    	setState(waitingForPlayers);
    }
    
    public void setPlaying() {
    	if (playing == null) playing = new Playing(this);
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
    		}
    		
    	}

    	@Override
    	public void keyReleased(KeyEvent e) {}

    	@Override
    	public void keyTyped(KeyEvent e) {}
    	
    }
    
    
    class WindowListener extends WindowAdapter {
    	
    	@Override
        public void windowClosing(WindowEvent e)
        {
        	close();
        }
        
     }
    
    
}