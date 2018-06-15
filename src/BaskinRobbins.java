import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.Socket;
import java.util.Scanner;

public class BaskinRobbins extends JFrame {

	public JPanel1 jpanel1 = null;
	public JPanel2 jpanel2 = null;
	public JPanel3 jpanel3 = null;
	public JPanel4 jpanel4 = null;

	static String eServer = "";
	static int ePort = 0000;
	// static int clientID = -1;
	static Socket baskinSocket = null;

	public BaskinRobbins() {
		setTitle("Baskin Robbins 31");
		setBackground(new Color(255, 105, 180));
		setBounds(100, 30, 1400, 900);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		// GraphicsDevice gd = ge.getDefaultScreenDevice();
		// gd.setFullScreenWindow(this);

	}

	public void connect(String eserver, int eport) {
		this.eServer = eserver;
		this.ePort = eport;

		try {
			baskinSocket = new Socket(eServer, ePort);
			// clientID = chatSocket.getLocalPort();
		} catch (BindException b) {
			System.out.println("Can't bind on: " + ePort);
			System.exit(1);
		} catch (IOException i) {
			System.out.println(i);
			System.exit(1);
		}
		new Thread(new ClientReceiver(baskinSocket, this)).start();
		// new Thread(new ClientSender(baskinSocket, this)).start();
		new ClientSender(baskinSocket, this);

	}

	public void change(String panelname) {

		if (panelname.equals("panel2")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel2);
			revalidate();
			repaint();
		} else if (panelname.equals("panel3")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel3);
			revalidate();
			repaint();
		} else if (panelname.equals("panel4")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel4);
			if (jpanel2.isOne) {
				jpanel4.lblNewLabel_3.setVisible(true);
			} else if (jpanel2.isMany) {
				jpanel4.lblNewLabel_2.setVisible(true);
			}
			revalidate();
			repaint();
		}
	}

	// public void Updatelist(int player) {
	// jpanel4.model.addElement("Player" + Integer.toString(player));
	// jpanel4.Updatelist();
	// }

	public static void main(String[] args) {

		BaskinRobbins game = new BaskinRobbins();

		game.jpanel1 = new JPanel1(game);
		game.jpanel2 = new JPanel2(game);
		game.jpanel3 = new JPanel3(game);
		game.jpanel4 = new JPanel4(game);
		game.add(game.jpanel1);
		game.setVisible(true);
	}

}

class JPanel1 extends JPanel {

	private BaskinRobbins game;

	private ImageIcon image;
	private JButton stbutton;

	public JPanel1(BaskinRobbins game) {
		this.game = game;
		setLayout(new BorderLayout(0, 0));

		image = new ImageIcon("logo2.png");
		stbutton = new JButton(null, image);
		stbutton.setBackground(new Color(255, 228, 225));
		add(stbutton, BorderLayout.CENTER);

		stbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.change("panel2");
			}
		});

	}
}

class JPanel2 extends JPanel {

	private BaskinRobbins game;

	private ImageIcon imageone;
	private ImageIcon imagemany;
	private JButton One;
	private JButton Many;

	boolean isOne = false;
	boolean isMany = false;

	public JPanel2(BaskinRobbins game) {
		this.game = game;
		setLayout(new GridLayout(1, 0, 0, 0));

		imageone = new ImageIcon("one.png");
		One = new JButton(null, imageone);
		One.setBackground(Color.WHITE);
		One.setBorder(BorderFactory.createLineBorder(Color.black));
		add(One);

		One.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isOne = true;
				isMany = false;
				game.change("panel3");
			}
		});

		imagemany = new ImageIcon("many.png");
		Many = new JButton(null, imagemany);
		Many.setBackground(Color.WHITE);
		Many.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Many);

		Many.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isMany = true;
				isOne = false;
				game.change("panel3");
			}
		});

	}
}

class JPanel3 extends JPanel {

	private BaskinRobbins game;

	private JTextField ip1;
	private JTextField ip2;
	private JTextField ip3;
	private JTextField ip4;
	private JTextField port;

	public JPanel3(BaskinRobbins game) {

		this.game = game;

		setBackground(new Color(255, 228, 225));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Input ServerIP");
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 75));
		lblNewLabel.setBounds(210, 325, 505, 80);
		add(lblNewLabel);

		ip1 = new JTextField();
		ip1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		ip1.setBounds(80, 438, 131, 53);
		ip1.setText("127");
		add(ip1);
		ip1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel(".");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 46));
		lblNewLabel_1.setBounds(241, 448, 37, 43);
		add(lblNewLabel_1);

		ip2 = new JTextField();
		ip2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		ip2.setBounds(290, 438, 131, 53);
		ip2.setText("0");
		add(ip2);
		ip2.setColumns(10);

		JLabel label = new JLabel(".");
		label.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 46));
		label.setBounds(451, 448, 37, 43);
		add(label);

		ip3 = new JTextField();
		ip3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		ip3.setColumns(10);
		ip3.setBounds(500, 438, 131, 53);
		ip3.setText("0");
		add(ip3);

		JLabel label_1 = new JLabel(".");
		label_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 46));
		label_1.setBounds(661, 448, 37, 43);
		add(label_1);

		ip4 = new JTextField();
		ip4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		ip4.setColumns(10);
		ip4.setBounds(700, 438, 131, 53);
		ip4.setText("1");
		add(ip4);

		JButton btnConnect = new JButton("connet");
		btnConnect.setFont(new Font("Berlin Sans FB", Font.PLAIN, 27));
		btnConnect.setBackground(new Color(255, 255, 255));
		btnConnect.setBounds(650, 541, 179, 61);
		add(btnConnect);

		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String eserver = ip1.getText() + "." + ip2.getText() + "." + ip3.getText() + "." + ip4.getText();
				int eport = Integer.parseInt(port.getText());
				game.connect(eserver, eport);
				game.change("panel4");
			}
		});

		JLabel lblNewLabel_2 = new JLabel("/");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 94));
		lblNewLabel_2.setBounds(950, 414, 62, 97);
		add(lblNewLabel_2);

		port = new JTextField();
		port.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		port.setColumns(10);
		port.setBounds(1080, 438, 131, 53);
		port.setText("10000");
		add(port);

		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 75));
		lblPort.setBounds(1080, 325, 179, 80);
		add(lblPort);

		JButton btnBack = new JButton("\u2190");
		btnBack.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 45));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(27, 790, 85, 54);
		btnBack.setBorder(BorderFactory.createLineBorder(Color.black));
		add(btnBack);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.change("panel2");
			}
		});
	}
}

class JPanel4 extends JPanel {

	private BaskinRobbins game;

	JTextField textInput;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;
	JLabel lblNewLabel_4;
	JList playerlist;
	DefaultListModel model;
	JTextArea numberArea;
	JButton btnEnter;
	JTextPane txtFailed;
	JTextPane txtFinished;
	JScrollPane scrollPane;

	JPanel4(BaskinRobbins game) {
		this.game = game;

		model = new DefaultListModel();

		setBackground(Color.PINK);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		numberArea = new JTextArea();
		numberArea.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 95));
		numberArea.setBounds(555, 54, 301, 763);
		numberArea.setBorder(new LineBorder(Color.black));

		scrollPane = new JScrollPane(numberArea);
		scrollPane.setBounds(555, 54, 301, 763);
		// logarea.setBorder(BorderFactory.createLineBorder(Color.black));
//		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		validate();
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(0, 0, 399, 870);
		// panel.setBorder(new LineBorder(Color.black));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("logo1.png"));
		panel.add(lblNewLabel, BorderLayout.NORTH);

		playerlist = new JList();
		playerlist.setBorder(new LineBorder(new Color(0, 0, 0)));
		playerlist.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 77));
		panel.add(playerlist, BorderLayout.CENTER);

		lblNewLabel_1 = new JLabel("Your turn");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 59));
		lblNewLabel_1.setBounds(996, 281, 273, 61);
		add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);

		lblNewLabel_2 = new JLabel("Waiting...");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 59));
		lblNewLabel_2.setBounds(996, 281, 273, 61);
		add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);

		lblNewLabel_3 = new JLabel("Game Start!");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 59));
		lblNewLabel_3.setBounds(964, 281, 328, 61);
		add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		lblNewLabel_4 = new JLabel("\uB2E4\uC2DC \uC785\uB825\uD558\uC138\uC694");
		lblNewLabel_4.setFont(new Font("HY헤드라인M", Font.ITALIC, 43));
		lblNewLabel_4.setBounds(964, 280, 328, 61);
		add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);

		textInput = new JTextField();
		textInput.setBounds(984, 377, 285, 66);
		textInput.setBorder(new LineBorder(Color.BLACK));
		textInput.setFont(new Font("Berlin Sans FB", Font.PLAIN, 43));
		add(textInput);
		textInput.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("(Seperator : SPACE BAR)");
		lblNewLabel_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 28));
		lblNewLabel_5.setBounds(980, 455, 294, 61);
		add(lblNewLabel_5);

		btnEnter = new JButton("ENTER\r\n");
		btnEnter.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		btnEnter.setBackground(new Color(240, 240, 240));
		btnEnter.setBounds(1028, 528, 198, 61);
		btnEnter.setEnabled(false);
		add(btnEnter);
		
		txtFailed = new JTextPane();
		txtFailed.setForeground(Color.WHITE);
		txtFailed.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 55));
		txtFailed.setEditable(false);
		txtFailed.setText("      You Failed");
		txtFailed.setBackground(Color.RED);
		txtFailed.setBounds(903, 280, 440, 69);
		txtFailed.setVisible(false);
		add(txtFailed);
		
		txtFinished = new JTextPane();
		txtFinished.setForeground(Color.WHITE);
		txtFinished.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 55));
		txtFinished.setEditable(false);
		txtFinished.setText("  Game Finished");
		txtFinished.setBackground(Color.RED);
		txtFinished.setBounds(903, 280, 440, 69);
		txtFinished.setVisible(false);
		add(txtFinished);
	}

	// public void Updatelist() {
	// playerlist.setModel(model);
	// }
}

class ClientSender {
	private Socket baskinSocket = null;
	private BaskinRobbins baskin = null;

	String userInput = "";
	PrintWriter out = null;

	ClientSender(Socket socket, BaskinRobbins baskin) {
		this.baskinSocket = socket;
		this.baskin = baskin;

		try {
			out = new PrintWriter(baskinSocket.getOutputStream(), true);

			baskin.jpanel4.btnEnter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					userInput = baskin.jpanel4.textInput.getText();
					System.out.println(userInput);
//					baskin.jpanel4.numberArea.append(userInput + "\n");
					out.println(userInput);
					out.flush();
					baskin.jpanel4.textInput.setText(null);
				}
			});

//			out.close();
			// baskinSocket.close();
		} catch (IOException i) {
			try {
				if (out != null)
					out.close();

				if (baskinSocket != null)
					baskinSocket.close();
			} catch (IOException e) {

			}
			System.exit(1);
		}

	}

}

class ClientReceiver implements Runnable {
	private Socket baskinSocket = null;
	private BaskinRobbins baskin = null;

	int clientCount = 0;

	ClientReceiver(Socket socket, BaskinRobbins baskin) {
		this.baskinSocket = socket;
		this.baskin = baskin;
	}

	public void run() {
		while (baskinSocket.isConnected()) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(baskinSocket.getInputStream()));
				String readSome = null;
				while ((readSome = in.readLine()) != null) {
					if (readSome.contains("Connected")) {
						DefaultListModel model = new DefaultListModel();
						readSome = readSome.substring(9);
						clientCount = Integer.parseInt(readSome);
						for (int i = 0; i < clientCount; i++)
							model.addElement("Player" + Integer.toString(i + 1));
						baskin.jpanel4.playerlist.setModel(model);
					}
					else if(readSome.equals("다시 입력하세요")) {
						baskin.jpanel4.lblNewLabel_1.setVisible(false);
						baskin.jpanel4.lblNewLabel_2.setVisible(false);
						baskin.jpanel4.lblNewLabel_3.setVisible(false);
						baskin.jpanel4.lblNewLabel_4.setVisible(true);
					}
					else if(readSome.equals("Game Start")) {
//						System.out.println("1111");
						baskin.jpanel4.lblNewLabel_1.setVisible(false);
						baskin.jpanel4.lblNewLabel_2.setVisible(false);
						baskin.jpanel4.lblNewLabel_3.setVisible(true);
						baskin.jpanel4.lblNewLabel_4.setVisible(false);
						Thread.sleep(2000);
						baskin.jpanel4.lblNewLabel_3.setVisible(false);
					}
					else if(readSome.equals("Your Turn")) {
						baskin.jpanel4.lblNewLabel_1.setVisible(true);
						baskin.jpanel4.lblNewLabel_2.setVisible(false);
						baskin.jpanel4.lblNewLabel_3.setVisible(false);
						baskin.jpanel4.lblNewLabel_4.setVisible(false);
						baskin.jpanel4.btnEnter.setEnabled(true);
					}
					else if(readSome.equals("You Failed")) {
						baskin.jpanel4.lblNewLabel_1.setVisible(false);
						baskin.jpanel4.lblNewLabel_2.setVisible(false);
						baskin.jpanel4.lblNewLabel_3.setVisible(false);
						baskin.jpanel4.lblNewLabel_4.setVisible(false);
						baskin.jpanel4.btnEnter.setEnabled(false);
						baskin.jpanel4.txtFailed.setVisible(true);
					}
					else if(readSome.equals("Game Finished")) {
						baskin.jpanel4.lblNewLabel_1.setVisible(false);
						baskin.jpanel4.lblNewLabel_2.setVisible(false);
						baskin.jpanel4.lblNewLabel_3.setVisible(false);
						baskin.jpanel4.lblNewLabel_4.setVisible(false);
						baskin.jpanel4.btnEnter.setEnabled(false);
						baskin.jpanel4.txtFinished.setVisible(true);
					}
					else if(readSome.contains("Who")) {
						readSome = readSome.substring(3);
						int whoturn = Integer.parseInt(readSome);
						baskin.jpanel4.playerlist.setSelectedIndex(whoturn);
					}
					else {
						baskin.jpanel4.numberArea.append(readSome + "\n");
						baskin.jpanel4.scrollPane.getVerticalScrollBar()
							.setValue(baskin.jpanel4.scrollPane.getVerticalScrollBar().getMaximum());
						baskin.jpanel4.lblNewLabel_1.setVisible(false);
						baskin.jpanel4.lblNewLabel_2.setVisible(false);
						baskin.jpanel4.lblNewLabel_3.setVisible(false);
						baskin.jpanel4.lblNewLabel_4.setVisible(false);
						baskin.jpanel4.btnEnter.setEnabled(false);
					}
				}
				in.close();
				baskinSocket.close();
			} catch (IOException i) {
				try {
					if (in != null)
						in.close();
					if (baskinSocket != null)
						baskinSocket.close();
				} catch (IOException e) {

				}
				// SocketTimeout, "Bye.", Max connection and more
				System.out.println("Leave.");
				System.exit(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}