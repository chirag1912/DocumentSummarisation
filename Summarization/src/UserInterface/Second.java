package UserInterface;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.glassfish.grizzly.streams.AbstractStreamWriter.DisposeBufferCompletionHandler;
import org.junit.rules.Timeout;

import com.sun.prism.Image;
import com.sun.prism.paint.ImagePattern;

import MindMapGraph.Extractor;
import MindMapGraph.drawGraph;
import Sentence_Scoring.CuePhrase;
import preprocessor.Tokeniser;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.concurrent.TimeoutException;
import java.awt.event.ActionEvent;
import UserInterface.Third;
import javax.swing.JScrollPane;

public class Second {

	public JFrame frame;
	private String SummaryFileLocation;
	private JLabel FileName;
	private String structured_summary;
	private JTextField titletextField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Second window = new Second();

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
	public Second() {
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
		
		JLabel lblEnterSummarizedParagraph = new JLabel("Summarized File : ");
		lblEnterSummarizedParagraph.setBounds(37, 34, 200, 14);
		frame.getContentPane().add(lblEnterSummarizedParagraph);

		textArea = new JTextArea();
		
		JScrollPane js = new JScrollPane(textArea);
		js.setBounds(147, 167, 599, 471);
		//js.setBounds(70, 180, 500, 350);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(js);
		
		JButton btnChooseFile = new JButton("Choose New File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FileName.setText("");
				textArea.setText("");
				JFileChooser chooser = new JFileChooser("F:\\Project(Summarization)");
				// chooser.showOpenDialog(null);
				chooser.setDialogTitle("Choose the File");
				
				chooser.setFileFilter(new FileNameExtensionFilter("Doc File", "doc"));
				chooser.setFileFilter(new FileNameExtensionFilter("Docx File", "docx"));
				chooser.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
				int result = chooser.showOpenDialog(null);

				if (result == chooser.APPROVE_OPTION) {
					SummaryFileLocation = chooser.getSelectedFile().getPath();
					FileName.setText(chooser.getSelectedFile().getName());
				}

			}
		});
		JButton btnGenerate = new JButton("Display Structured File");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// SummaryFileLocation = summaryPathTextField.getText();
				Extractor e1 = new Extractor();
				try {

					String title = titletextField.getText();
					structured_summary = e1.create_structured_file(SummaryFileLocation, title);
					
					
					textArea.setText(structured_summary);
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// textArea.setText(structured_summary);

			}
		});
		btnGenerate.setBounds(771, 99, 171, 23);
		frame.getContentPane().add(btnGenerate);

		JLabel lblStructuredfile = new JLabel("StructuredFile : ");
		lblStructuredfile.setBounds(37, 167, 187, 14);
		frame.getContentPane().add(lblStructuredfile);

		JButton btnNext = new JButton("NEXT : Display Mind Map");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawGraph draw = new drawGraph();
				draw.main_calling();

				// Timeout.seconds(10);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Third third = new Third();
				third.frame.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnNext.setBounds(771, 299, 192, 23);
		frame.getContentPane().add(btnNext);

		btnChooseFile.setBounds(486, 65, 192, 23);
		frame.getContentPane().add(btnChooseFile);

		JButton btnSaveFile = new JButton("Save Changes");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FileWriter fw;
				try {
					fw = new FileWriter("F:\\Project(Summarization)\\Summarization\\outA.txt");
					fw.flush();
					fw.write(textArea.getText());
					fw.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSaveFile.setBounds(771, 163, 180, 23);
		frame.getContentPane().add(btnSaveFile);

		JButton btnNewButton = new JButton("Use Previous File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileName.setText("");
				textArea.setText("");
				SummaryFileLocation = "F:\\Project(Summarization)\\Summarised.txt";
				FileName.setText("Summarised.txt");

			}
		});
		btnNewButton.setBounds(247, 65, 143, 23);
		frame.getContentPane().add(btnNewButton);

		FileName = new JLabel("");
		FileName.setBounds(246, 34, 144, 14);
		frame.getContentPane().add(FileName);

		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				First first = new First();
				first.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(771, 232, 89, 23);
		frame.getContentPane().add(btnBack);

		JLabel lblTitleOfDocument = new JLabel("Title Of Document : ");
		lblTitleOfDocument.setBounds(344, 103, 143, 14);
		frame.getContentPane().add(lblTitleOfDocument);

		
		
		titletextField = new JTextField();
		titletextField.setBounds(486, 100, 192, 20);
		frame.getContentPane().add(titletextField);
		titletextField.setColumns(10);
		
	/*	JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 167, 599, 471);
		frame.getContentPane().add(scrollPane);*/
	}
}
