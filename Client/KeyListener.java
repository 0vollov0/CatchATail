
package Client;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		if (!Game.isGameScreen) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Client.command = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			Client.command = 2;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Client.command = 3;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Client.command = 4;
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			Client.command = 5;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			Client.command = 6;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			Client.command = 7;
		}
	}
}
