package Client;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ClientPlayer {
	private int STEP = 4;
	private int ACCELATOR_COUNT = 10;
	private int posX, posY;
	private int tailNum;
	private int catchcount;
	private boolean accflag;
	private boolean isAlive;
	private boolean isHaveAWeapon;
	private int weaponNum;
	private Image playerImage = new ImageIcon(Client.class.getResource("../images/PlayerBasicImage.png")).getImage();
	private boolean downmotionflag = true;
	private boolean upmotionflag = true;
	private boolean rightmotionflag = true;
	private boolean leftmotionflag = true;
	private boolean attackflag = true;
	private Image downImage1 = new ImageIcon(Client.class.getResource("../images/PlayerDownMoveImage1.png")).getImage();
	private Image downImage2 = new ImageIcon(Client.class.getResource("../images/PlayerDownMoveImage2.png")).getImage();
	private Image upImage1 = new ImageIcon(Client.class.getResource("../images/PlayerUpMoveImage1.png")).getImage();
	private Image upImage2 = new ImageIcon(Client.class.getResource("../images/PlayerUpMoveImage2.png")).getImage();
	private Image leftImage1 = new ImageIcon(Client.class.getResource("../images/PlayerLeftMoveImage1.png")).getImage();
	private Image leftImage2 = new ImageIcon(Client.class.getResource("../images/PlayerLeftMoveImage2.png")).getImage();
	private Image rightImage1 = new ImageIcon(Client.class.getResource("../images/PlayerRightMoveImage1.png"))
			.getImage();
	private Image rightImage2 = new ImageIcon(Client.class.getResource("../images/PlayerRightMoveImage2.png"))
			.getImage();

	public ClientPlayer(int x, int y, int tailNum) {
		catchcount = 0;
		isAlive = true;
		isHaveAWeapon = false;
		posX = x;
		posY = y;
		accflag = false;
		this.tailNum = tailNum;
	}

	public void Catch() {
		catchcount++;
	}

	public int getCatchCount() {
		return catchcount;
	}

	public int getTailNum() {
		return tailNum;
	}

	public Image getPlayerImage() {
		return playerImage;
	}

	public void setPlayerImage(Image playerImage) {
		this.playerImage = playerImage;
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void increasePosX() {
		if (posX > Client.SCREEN_WIDTH - 150)
			return;
		posX = posX + STEP;
	}

	public void decreasePosX() {
		if (posX < Client.SCREEN_WIDTH - 700)
			return;
		posX = posX - STEP;
	}

	public void increasePosY() {
		if (posY > Client.SCREEN_HEIGHT - 60)
			return;
		posY = posY + STEP;
	}

	public void decreasePosY() {
		if (posY < 30)
			return;
		posY = posY - STEP;
	}

	public boolean isDownmotionflag() {
		return downmotionflag;
	}

	public void setDownmotionflag(boolean downmotionflag) {
		this.downmotionflag = downmotionflag;
	}

	public boolean isUpmotionflag() {
		return upmotionflag;
	}

	public void setUpmotionflag(boolean upmotionflag) {
		this.upmotionflag = upmotionflag;
	}

	public boolean isRightmotionflag() {
		return rightmotionflag;
	}

	public void setRightmotionflag(boolean rightmotionflag) {
		this.rightmotionflag = rightmotionflag;
	}

	public boolean isLeftmotionflag() {
		return leftmotionflag;
	}

	public void setLeftmotionflag(boolean leftmotionflag) {
		this.leftmotionflag = leftmotionflag;
	}

	public void HaveAWeapon() {
		isHaveAWeapon = true;
		downImage1 = new ImageIcon(Client.class.getResource("../images/PlayerDownMoveImage(Sword)1.png")).getImage();
		downImage2 = new ImageIcon(Client.class.getResource("../images/PlayerDownMoveImage(Sword)2.png")).getImage();
		upImage1 = new ImageIcon(Client.class.getResource("../images/PlayerUpMoveImage(Sword)1.png")).getImage();
		upImage2 = new ImageIcon(Client.class.getResource("../images/PlayerUpMoveImage(Sword)2.png")).getImage();
		leftImage1 = new ImageIcon(Client.class.getResource("../images/PlayerLeftMoveImage(Sword)1.png")).getImage();
		leftImage2 = new ImageIcon(Client.class.getResource("../images/PlayerLeftMoveImage(Sword)2.png")).getImage();
		rightImage1 = new ImageIcon(Client.class.getResource("../images/PlayerRightMoveImage(Sword)1.png")).getImage();
		rightImage2 = new ImageIcon(Client.class.getResource("../images/PlayerRightMoveImage(Sword)2.png")).getImage();
	}

	public void Attack() {
		downImage1 = new ImageIcon(Client.class.getResource("../images/PlayerDownMoveImage(Sword)Effect1.png"))
				.getImage();
		downImage2 = new ImageIcon(Client.class.getResource("../images/PlayerDownMoveImage(Sword)Effect2.png"))
				.getImage();
		upImage1 = new ImageIcon(Client.class.getResource("../images/PlayerUpMoveImage1.png")).getImage();
		upImage2 = new ImageIcon(Client.class.getResource("../images/PlayerUpMoveImage2.png")).getImage();
		leftImage1 = new ImageIcon(Client.class.getResource("../images/PlayerLeftMoveImage(Sword)Effect1.png"))
				.getImage();
		leftImage2 = new ImageIcon(Client.class.getResource("../images/PlayerLeftMoveImage(Sword)Effect2.png"))
				.getImage();
		rightImage1 = new ImageIcon(Client.class.getResource("../images/PlayerRightMoveImage(Sword)Effect1.png"))
				.getImage();
		rightImage2 = new ImageIcon(Client.class.getResource("../images/PlayerRightMoveImage(Sword)Effect2.png"))
				.getImage();
	}

	public int Dead() {
		setAlive(false);
		if (isHaveAWeapon) {
			Client.swords[weaponNum].setPosX(posX);
			Client.swords[weaponNum].setPosY(posY);
		}

		posX = -100;
		return tailNum;
	}

	public void decreaseAccelator() {
		ACCELATOR_COUNT--;
		if (ACCELATOR_COUNT == 0) {
			STEP = 4;
			accflag = false;
		}
	}

	public void inputCommand(int command) {
		if (command == 1) {
			if (isDownmotionflag()) {
				setPlayerImage(downImage1);
				setDownmotionflag(false);
			} else {
				setPlayerImage(downImage2);
				setDownmotionflag(true);
			}
			increasePosY();
			if (accflag)
				decreaseAccelator();

		} else if (command == 2) {
			if (isUpmotionflag()) {
				setPlayerImage(upImage1);
				setUpmotionflag(false);
			} else {
				setPlayerImage(upImage2);
				setUpmotionflag(true);
			}
			decreasePosY();
			if (accflag)
				decreaseAccelator();
		} else if (command == 3) {
			if (isRightmotionflag()) {
				setPlayerImage(rightImage1);
				setRightmotionflag(false);
			} else {
				setPlayerImage(rightImage2);
				setRightmotionflag(true);
			}
			increasePosX();
			if (accflag)
				decreaseAccelator();
		} else if (command == 4) {
			if (isLeftmotionflag()) {
				setPlayerImage(leftImage1);
				setLeftmotionflag(false);
			} else {
				setPlayerImage(leftImage2);
				setLeftmotionflag(true);
			}
			decreasePosX();
			if (accflag)
				decreaseAccelator();
		} else if (command == 5) {
			int distanceX, distanceY;
			for (int i = 0; i < Client.MAX_SWORD; i++) {
				distanceX = Math.abs(Client.swords[i].getPosX() - this.getPosX());
				distanceY = Math.abs(Client.swords[i].getPosY() - this.getPosY());
				if (distanceX < 20 && distanceY < 8) {
					HaveAWeapon();
					weaponNum = i;
					Client.swords[i].disappear();
				}
			}
		} else if (command == 6) {
			if (this.isHaveAWeapon) {
				if (attackflag) {
					Attack();
					attackflag = false;
				} else {
					HaveAWeapon();
					attackflag = true;
				}

			} else
				return;
			int distanceX, distanceY;

			distanceX = Math.abs(Client.players[tailNum].getPosX() - this.getPosX());
			distanceY = Math.abs(Client.players[tailNum].getPosY() - this.getPosY());
			if (distanceX < 30 && distanceY < 10) {
				tailNum = Client.players[tailNum].Dead();
				Catch();

			}

		} else if (command == 7) {
			if (ACCELATOR_COUNT == 5 && accflag == false) {
				accflag = true;
				STEP = STEP * 3;
			}

		}
	}
}
