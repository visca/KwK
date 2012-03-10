package view.state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import config.Config;

import view.GameFrame;
import view.menu.Menu;


public abstract class GameState {
	protected GameFrame frame;
	protected Image background;
	protected Menu menu;
	
	public GameState(GameFrame frame) {
		this.frame = frame;
	}
	
	public Image getBackground() {
		return background;
	}
	
	public void setBackground(Image background) {
		this.background = background;
	}
	
	public Image loadImage(String path) {
		return Toolkit.getDefaultToolkit().getImage(Config.projectPath + path);
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public void drawMenu(Graphics g) {
		for (int i=0; i < menu.getSize(); i++) {
			g.drawImage(menu.getImage(i), menu.getItem(i).getX(), menu.getItem(i).getY(), frame);
		}
	}

	public void up() {
		menu.up();
		frame.repaint();
	} 

	public void down() {
		menu.down();
		frame.repaint();
	}
	
	public abstract void left();
	public abstract void right();
	public abstract void enter();
	public abstract void escape();
		
}
