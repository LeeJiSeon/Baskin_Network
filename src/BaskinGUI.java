import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.prism.Image;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JTextPane;

public class BaskinGUI extends JFrame {

	private JPanel contentPane;
	private ImageIcon im;
	private JTextField textInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaskinGUI frame = new BaskinGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BaskinGUI() {
		setForeground(new Color(255, 255, 255));
		setTitle("Baskin Robbins 31");
		setBackground(new Color(255, 105, 180));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 0, 1400, 900);
		
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice gd = ge.getDefaultScreenDevice();
//		//setUndecorated(true);
//		gd.setFullScreenWindow(this);
//		
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtFinished = new JTextPane();
		txtFinished.setForeground(Color.WHITE);
		txtFinished.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 55));
		txtFinished.setEditable(false);
		txtFinished.setText("      You Failed");
		txtFinished.setBackground(Color.RED);
		txtFinished.setBounds(903, 280, 440, 69);
		contentPane.add(txtFinished);
		
		JTextArea numberArea = new JTextArea();
		numberArea.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 95));
		numberArea.setBounds(555, 54, 301, 763);
		numberArea.setBorder(new LineBorder(Color.black));
		contentPane.add(numberArea);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(0, 0, 399, 855);
//		panel.setBorder(new LineBorder(Color.black));
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\j5119\\Desktop\\Baskinrobbins\\logo1.png"));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JList playerlist = new JList();
		playerlist.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 77));
		playerlist.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(playerlist, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("\uB2E4\uC2DC \uC785\uB825\uD558\uC138\uC694");
		lblNewLabel_1.setFont(new Font("HY헤드라인M", Font.ITALIC, 43));
		lblNewLabel_1.setBounds(964, 280, 328, 61);
		contentPane.add(lblNewLabel_1);
		
		textInput = new JTextField();
		textInput.setBackground(Color.WHITE);
		textInput.setFont(new Font("Berlin Sans FB", Font.PLAIN, 43));
		textInput.setBounds(984, 377, 285, 66);
		textInput.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(textInput);
		textInput.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("(Seperator : SPACE BAR)");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 28));
		lblNewLabel_2.setBounds(980, 455, 294, 61);
		contentPane.add(lblNewLabel_2);
		
		JButton btnEnter = new JButton("ENTER\r\n");
		btnEnter.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		btnEnter.setBackground(new Color(240, 240, 240));
		btnEnter.setBounds(1028, 528, 198, 61);
		contentPane.add(btnEnter);

		
		
	}
}

