package view.menu;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<MenuItem> menuItems;
	private int selectedItem = 0;
	public int xResolution;
	public int yResolution;
	
	public Menu(int xResolution, int yResolution) {
		menuItems = new ArrayList<MenuItem>();
		this.xResolution = xResolution;
		this.yResolution = yResolution;
	}
	
	public void add(MenuItem menuItem) {
		menuItems.add(menuItem);
		
		menuItem.setX(xResolution/2 - 150);
		menuItem.setY(yResolution/2 + (menuItems.size()*100 - 200));
	}
	
	public void up() {
		if (selectedItem > 0) {
			selectedItem -= 1;
		}
		else
		{
			selectedItem = menuItems.size()-1;
		}
	}
	
	public void down() {
		if (selectedItem < (menuItems.size()-1)) {
			selectedItem += 1;
		}
		else
		{
			selectedItem = 0;
		}
	}
	
	public Image getImage(int index) {
 		return index == selectedItem ? menuItems.get(index).getImage(true) : menuItems.get(index).getImage(false);
	}
	
	public int getSize() {
		return menuItems.size();
	}
	
	public int getSelectedIndex() {
		return selectedItem;
	}
	
	public MenuItem getItem(int index) {
		return menuItems.get(index);
	}

}
