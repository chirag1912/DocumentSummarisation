package UserInterface;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.sun.javafx.iio.common.ImageLoaderImpl;
import com.sun.javafx.tk.Toolkit;

public class Third extends Applet{

	public JFrame frame;
	private JLabel imagelabel;
	private JScrollPane js;
	private Image img = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Third window = new Third();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Third() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public Image getImage(String path) {
		Image tempImage = null;
		
		URL imageurl = ImageLoaderImpl.class.getResource(path);
		tempImage = java.awt.Toolkit.getDefaultToolkit().getImage(imageurl);
		return tempImage;
	}
	public void paint(Graphics g) {
		this.setSize(640,480);
		img = getImage("F:\\Project(Summarization)\\Summarization\\outA.png");
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(img,25 , 50,25,25,this);
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(20, 20, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//label = new JLabel("");
		//Graphics g = null;
	//	paint(g);
		
		
		//URL imageurl = new 	
		ImageIcon icon = new ImageIcon("outA.png");
		imagelabel=new JLabel(icon);
	//	imagelabel.setBounds(0, 0, 300, 600);
		//frame.getContentPane().add(imagelabel);
		JScrollPane js = new JScrollPane(imagelabel);
		js.setBounds(0, 0, 1100, 500);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(js);
		
		JButton btnBack = new JButton("BACK ");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Second sc = new Second();
				sc.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(141, 526, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setBounds(279, 526, 89, 23);
		frame.getContentPane().add(btnClose);
		
		
		
		imagelabel = new JLabel("");
		imagelabel.setBounds(64, 106, 557, 341);
		frame.getContentPane().add(imagelabel);
		
		
	}
}
