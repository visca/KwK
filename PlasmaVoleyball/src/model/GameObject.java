package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import config.Config;

import view.GameFrame;


public abstract class GameObject {
	private Image image;
	private int x, y;
	
	public GameObject(int x, int y, String pathImage) {
		this.x = x;
		this.y = y;
		this.image = loadImage(pathImage);
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public void draw(Graphics g, GameFrame frame) {
		g.drawImage(image, x, y, frame);
	}
	
	public Image loadImage(String path) {
		return Toolkit.getDefaultToolkit().getImage(Config.projectPath + path);
	}
	
}