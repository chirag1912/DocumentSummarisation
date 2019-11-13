package UserInterface;

import preprocessor.PrerequisiteCalc;
import preprocessor.Tokeniser;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Sentence_Scoring.CuePhrase;

public class First {

	public JFrame frame;
	private JLabel lblTitleOfDocument;
	private JTextField titleField;
	private JLabel lblSummary;
	private JTextPane textPane;
	private JButton btnChooseFile;
	private String SampleFileLocation;
	private int sentLength;
	private JLabel titleLabel;
	private JTextField cuePhrasestextField;
	private JButton btnBack;
	private JLabel lblLengthOfSummary;
	private JTextField lengthtextField;
	private JTextField sentTextField;
	private JTextField wordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					First window = new First();
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
	public First() {
		frame = new JFrame();
		frame.setBounds(0,0,1400, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JLabel lblNewLabel = new JLabel("Document : ");
		lblNewLabel.setBounds(21, 33, 159, 14);
		frame.getContentPane().add(lblNewLabel);

		lblSummary = new JLabel("Summary  :");
		lblSummary.setBounds(21, 243, 92, 14);
		frame.getContentPane().add(lblSummary);

		textPane = new JTextPane();
		textPane.setBounds(133, 246, 870, 388);
		// JScrollPane scroll = new
		// JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(textPane);

		btnChooseFile = new JButton("Choose FIle");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				titleField.setText("");
				cuePhrasestextField.setText("");
				textPane.setText("");
				titleLabel.setText("");
				sentTextField.setText(Integer.toString(0));
				wordTextField.setText(Integer.toString(0));
				JFileChooser chooser = new JFileChooser("F:\\Project(Summarization)");
				// chooser.showOpenDialog(null);
				chooser.setDialogTitle("Choose the File");
				chooser.setFileFilter(new FileNameExtensionFilter("Doc File", "doc"));
				chooser.setFileFilter(new FileNameExtensionFilter("Docx File", "docx"));
				chooser.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));

				int result = chooser.showOpenDialog(null);

				if (result == chooser.APPROVE_OPTION) {
					SampleFileLocation = chooser.getSelectedFile().getPath();
					int[] ret = { 0, 0 };
					try {
						PrerequisiteCalc p = new PrerequisiteCalc();
						ret = p.Calc(SampleFileLocation);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sentLength = ret[0];
					sentTextField.setText(Integer.toString(ret[0]));
					wordTextField.setText(Integer.toString(ret[1]));
					lengthtextField.setText("5");
					titleLabel.setText(chooser.getSelectedFile().getName());

				}

			}
		});

		JButton btnNewButton = new JButton("Generate Summary");
		// final String Summary = "" ;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String SampleFileLocation = SampleField.getText();
				String Title = titleField.getText();
				String CuePhrases = cuePhrasestextField.getText();
				int length = Integer.parseInt(lengthtextField.getText());

				while (((float) length / sentLength) > 0.75) {
					length = Integer.parseInt(lengthtextField.getText());
					JOptionPane op = new JOptionPane();
					length = Integer.parseInt(op.showInputDialog(frame,
							"The Threshold for the Sumaary is 0.75(sentences/2).\nPlease enter the Length Properly"));
					lengthtextField.setText(Integer.toString(length));
					
				}
				
				CuePhrase c = new CuePhrase("F:\\Project(Summarization)\\CuePhrases.txt");
				try {
					c.CuePhrases_creation(CuePhrases);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// JOptionPane.showMessageDialog(null, SampleFileLocation);
				Tokeniser t = new Tokeniser();
				try {
					String Summary = t.tokenise(SampleFileLocation, Title, length);
					if (Summary.equalsIgnoreCase("null")) {
						/*JOptionPane.showMessageDialog(frame,
								"Required No. of Lines Greater than Original Paragraph\nPLEASE ENTER AGAIN");*/
						lengthtextField.setText("");
					} else
						textPane.setText(Summary);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(135, 212, 172, 23);
		frame.getContentPane().add(btnNewButton);

		lblTitleOfDocument = new JLabel("Title Of Document : ");
		lblTitleOfDocument.setBounds(21, 80, 159, 14);
		frame.getContentPane().add(lblTitleOfDocument);

		titleField = new JTextField();
		titleField.setBounds(236, 77, 263, 20);
		frame.getContentPane().add(titleField);
		titleField.setColumns(10);

		JLabel lblCuephrases = new JLabel("Cue-Phrases : ");
		lblCuephrases.setBounds(21, 114, 159, 14);
		frame.getContentPane().add(lblCuephrases);

		JButton btnNext = new JButton("NEXT : Structured File");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Second sc = new Second();
				sc.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNext.setBounds(377, 212, 210, 23);
		frame.getContentPane().add(btnNext);

		btnChooseFile.setBounds(504, 29, 159, 23);
		frame.getContentPane().add(btnChooseFile);

		titleLabel = new JLabel("");
		titleLabel.setBounds(236, 33, 180, 14);
		frame.getContentPane().add(titleLabel);

		cuePhrasestextField = new JTextField();
		cuePhrasestextField.setBounds(236, 109, 263, 35);
		frame.getContentPane().add(cuePhrasestextField);
		cuePhrasestextField.setColumns(10);

		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OCR_Window ocr = new OCR_Window();
				ocr.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(1015, 33, 89, 23);
		frame.getContentPane().add(btnBack);

		JButton btnChoosePreviousText = new JButton("Choose Previous Text File");
		btnChoosePreviousText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				titleField.setText("");
				cuePhrasestextField.setText("");
				textPane.setText("");

				SampleFileLocation = "Recovered.txt";
				titleLabel.setText("Recovered.txt");
			}
		});
		btnChoosePreviousText.setBounds(709, 29, 210, 23);
		frame.getContentPane().add(btnChoosePreviousText);

		lblLengthOfSummary = new JLabel("Length Of Summary :  ");
		lblLengthOfSummary.setBounds(759, 80, 146, 14);
		frame.getContentPane().add(lblLengthOfSummary);

		lengthtextField = new JTextField();
		lengthtextField.setBounds(915, 77, 86, 20);
		frame.getContentPane().add(lengthtextField);
		lengthtextField.setColumns(10);

		JLabel lblSentences = new JLabel("Sentences : ");
		lblSentences.setBounds(526, 80, 83, 14);
		frame.getContentPane().add(lblSentences);

		sentTextField = new JTextField();
		sentTextField.setBounds(619, 77, 44, 20);
		frame.getContentPane().add(sentTextField);
		sentTextField.setColumns(10);

		JLabel lblWords = new JLabel("Words : ");
		lblWords.setBounds(526, 114, 83, 14);
		frame.getContentPane().add(lblWords);

		wordTextField = new JTextField();
		wordTextField.setBounds(619, 111, 44, 20);
		frame.getContentPane().add(wordTextField);
		wordTextField.setColumns(10);

	}
}
