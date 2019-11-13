package UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import tess4j.Test;

public class OCR_Window {

	public JFrame frame;
	private JLabel FileLabel;
	private JLabel lblChoosenFileContents;
	private ImageIcon ic;
	private JLabel imageLabel;
	private JLabel lblRecoveredText;
	private JButton btnConvertToText;
	private String FilePath;
	private JTextArea textArea;
	private JButton btnNext;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OCR_Window window = new OCR_Window();
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
	public OCR_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		frame.setBounds(20, 20, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPrintedTextFile = new JLabel("Printed Text File :");
		lblPrintedTextFile.setBounds(58, 38, 112, 20);
		frame.getContentPane().add(lblPrintedTextFile);
		
		FileLabel = new JLabel("");
		FileLabel.setBounds(215, 41, 163, 14);
		frame.getContentPane().add(FileLabel);
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FileLabel.setText("");
				JFileChooser chooser = new JFileChooser("images\\");
				//chooser.showOpenDialog(null);
				chooser.setDialogTitle("Choose the File");
				
				chooser.setFileFilter(new FileNameExtensionFilter("JPG File", "jpg"));
				chooser.setFileFilter(new FileNameExtensionFilter("TIF File", "tif"));
				chooser.setFileFilter(new FileNameExtensionFilter("PNG File", "png"));
				int result = chooser.showOpenDialog(null);
				
				if(result == chooser.APPROVE_OPTION) {
					//SampleFileLocation = chooser.getSelectedFile().getPath();
					FileLabel.setText(chooser.getSelectedFile().getName());
				}
				
				FilePath = chooser.getSelectedFile().getPath();
				ic = new ImageIcon(chooser.getSelectedFile().getPath());
				imageLabel = new JLabel( ic);
				JScrollPane js = new JScrollPane(imageLabel);
				js.setBounds(70, 180, 500, 350);
				js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				frame.getContentPane().add(js);
				imageLabel.repaint();
				
				
				
								
			}
		});
		btnChooseFile.setBounds(466, 37, 200, 23);
		frame.getContentPane().add(btnChooseFile);
		
		
		
		lblChoosenFileContents = new JLabel("Choosen File Contents : ");
		lblChoosenFileContents.setBounds(81, 146, 200, 14);
		frame.getContentPane().add(lblChoosenFileContents);
		
		lblRecoveredText = new JLabel("Recovered Text : ");
		lblRecoveredText.setBounds(608, 146, 200, 14);
		frame.getContentPane().add(lblRecoveredText);
		
		btnConvertToText = new JButton("Convert to Text File");
		btnConvertToText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Test t1 = new Test(FilePath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileReader fr;
				try {
					fr = new FileReader("Recovered.txt");
					BufferedReader br = new BufferedReader(fr);
					String text="",line;
					while((line=br.readLine())!=null) {
						text+=line;
						text+="\n";
					}
					br.close();fr.close();
					textArea.setText(text);	
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnConvertToText.setBounds(466, 79, 200, 23);
		frame.getContentPane().add(btnConvertToText);
		
		textArea = new JTextArea();
		textArea.setBounds(609, 171, 612, 362);
		frame.getContentPane().add(textArea);
		
		btnNext = new JButton("NEXT : Summarisation");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				First fr = new First();
				fr.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNext.setBounds(850, 51, 228, 23);
		frame.getContentPane().add(btnNext);
		
		
	}

}
