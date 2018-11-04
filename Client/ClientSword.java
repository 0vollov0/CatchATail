package Client;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ClientSword {
	private int posX,posY;
	private Image swordImage;
	
	public ClientSword(int posX,int posY) {
		this.posX = posX;
		this.posY = posY;
		swordImage = new ImageIcon(Client.class.getResource("../images/swordImageLeft.png")).getImage();
	}
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public Image getSwordImage() {
		return swordImage;
	}
	public void setSwordImage(Image swordImage) {
		this.swordImage = swordImage;
	}
	public void disappear() {
		posX = -100;
	}
}
