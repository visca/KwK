package view.menu;

import java.awt.Image;


public class MenuItem {
	private Image img;
	private Image imgSelected;
	private int x, y;
	
	public MenuItem(Image img, Image imgSelected) {
		this.img = img;
		this.imgSelected = imgSelected;
		this.x = 0;
		this.x = 0;
	}
	
	public Image getImage(boolean selected) {
		return selected ? imgSelected : img;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
