import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class View {

	private JFrame frame;
	private JTextField textField;
	private JFileChooser fileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
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
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblShipperTravel = new JLabel("Shipper Travel");
		lblShipperTravel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblShipperTravel.setBounds(148, 11, 154, 32);
		frame.getContentPane().add(lblShipperTravel);

		textField = new JTextField();

		textField.setBounds(132, 54, 271, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 126, 393, 107);
		frame.getContentPane().add(textPane);

		JButton btnChooseFile = new JButton("Choose file");
		btnChooseFile.addActionListener(e -> {
			int result = fileChooser.showOpenDialog(frame);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				textField.setText(file.getAbsolutePath());
				try {
					TSPSolver tspSolver = new TSPSolver();
					tspSolver.readInnput(file.getAbsolutePath());
					
					textPane.setText(tspSolver.solver());
				} catch (Exception e1) {
					e1.printStackTrace();

				}
			}
		});
		btnChooseFile.setBounds(10, 51, 112, 23);
		frame.getContentPane().add(btnChooseFile);
		
		
		
		JLabel lblSolver = new JLabel("Solver");
		lblSolver.setBounds(10, 101, 46, 14);
		frame.getContentPane().add(lblSolver);
	}
}
