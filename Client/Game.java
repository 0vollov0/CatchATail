package Client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	private int playerNum = Client.playerNum;
	private ClientPlayer[] players = Client.players;
	private Image mainbackground = new ImageIcon(Client.class.getResource("../images/MainScreen.png")).getImage();
	private Image gamestage = new ImageIcon(Client.class.getResource("../images/GameStageImage.png")).getImage();
	private Image Dead = new ImageIcon(Client.class.getResource("../images/deadImage.png")).getImage();
	private Image Win = new ImageIcon(Client.class.getResource("../images/winImage.png")).getImage();
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(
			Client.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Client.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Client.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Client.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(
			Client.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Client.class.getResource("../images/quitButtonBasic.png"));

	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private JLabel menuBar = new JLabel(new ImageIcon(Client.class.getResource("../images/menuBar.png")));

	private int mouseX, mouseY;

	public static boolean isGameScreen = false;
	public static boolean winflag = false;
	private Font font = new Font("Arial Black", Font.BOLD, 14);

	public Game() {
		setUndecorated(true);
		setSize(Client.SCREEN_WIDTH, Client.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		addKeyListener(new KeyListener());
		setFocusable(true);

		startButton.setBounds(20, 200, 300, 75);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				enterGame();
			}
		});

		add(startButton);

		quitButton.setBounds(20, 300, 300, 75);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		add(quitButton);

		exitButton.setBounds(760, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		add(exitButton);

		menuBar.setBounds(0, 0, 800, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}

	public void paint(Graphics g) {
		screenImage = createImage(Client.SCREEN_WIDTH, Client.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(mainbackground, 0, 0, null);
		if (isGameScreen) {
			g.drawImage(gamestage, 100, 0, null);
			if (players[playerNum].isAlive() == false) {
				g.drawImage(Dead, 100, 0, null);
			}
			int deadcount = 0;
			int aliveplayerNum = -1;
			for (int i = 0; i < Client.MAX_PLAYER; i++) {
				if (players[i].isAlive() == false)
					deadcount++;
				else
					aliveplayerNum = i;
			}
			if (deadcount == Client.MAX_PLAYER - 1 && playerNum == aliveplayerNum) {
				g.drawImage(Win, 100, 0, null);
			}
			for (int i = 0; i < Client.MAX_SWORD; i++) {
				g.drawImage(Client.swords[i].getSwordImage(), Client.swords[i].getPosX(), Client.swords[i].getPosY(),
						null);
			}
			for (int i = 0; i < Client.MAX_PLAYER; i++) {
				if (players[i].isAlive()) {
					if (playerNum == i) {
						g.setColor(Color.WHITE);
					}
					g.drawString("PLAYER : " + i, players[i].getPosX() - 15, players[i].getPosY() - 5);
					g.drawImage(players[i].getPlayerImage(), players[i].getPosX(), players[i].getPosY(), 30, 60, null);
					g.setColor(Color.BLACK);
				}
			}
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("YOU CAN CATCH PLAYER [ " + players[playerNum].getTailNum() + " ]", 10, 50);
			g.drawString("CATCH [ " + players[playerNum].getCatchCount() + " ]", 580, 50);
			g.drawString("ALIVE [ " + (Client.MAX_PLAYER - deadcount) + " ] ", 700, 50);
		}
		paintComponents(g);
		this.repaint();
	}

	public void enterGame() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		isGameScreen = true;

	}
}
