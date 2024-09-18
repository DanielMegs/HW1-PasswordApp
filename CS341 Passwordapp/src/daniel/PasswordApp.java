package daniel;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Password Strength App
 * @author danny
 */
public class PasswordApp {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Initializes and displays the main application window.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordApp window = new PasswordApp();
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
	public PasswordApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Output :");
		lblNewLabel.setBounds(69, 200, 64, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setBounds(57, 106, 64, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(131, 83, 600, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Please enter your password. With 8 - 12 characters and no spaces.");
		lblNewLabel_2.setBounds(133, 48, 342, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(133, 195, 600, 41);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Check Password");
		btnNewButton.setBounds(131, 146, 137, 28);
		frame.getContentPane().add(btnNewButton);
		
		// Action listener for the password button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = textField.getText();
				String outputMessage = validatePassword(password);
				textArea.setText(outputMessage);
			}
		});
	}

	// Validate the password and compute the largest block
	private String validatePassword(String password) {
		// Check for length and spaces
		if (password.length() < 8 || password.length() > 12) {
			return "Error: Password must be between 8 and 12 characters.";
		}
		if (password.contains(" ")) {
			return "Error: Password shouldn't contain spaces.";
		}

		// Calculate largest block of consecutive repeating characters
		int largestBlock = findLargestBlock(password);

		// Check if the largest block is good or needs to be changed
		if (largestBlock <= 2) {
			return "The largest block in the password is " + largestBlock + ". This is a good password.";
		} else {
			int reduceBy = largestBlock - 2;
			return "The largest block in the password is " + largestBlock + ". This password can be made stronger by reducing this block by " + reduceBy + ".";
		}
	}

	// Function to find the largest block of repeating characters
	private int findLargestBlock(String password) {
		int maxBlock = 1;
		int currentBlock = 1;

		for (int i = 1; i < password.length(); i++) {
			if (password.charAt(i) == password.charAt(i - 1)) {
				currentBlock++;
			} else {
				if (currentBlock > maxBlock) {
					maxBlock = currentBlock;
				}
				currentBlock = 1;
			}
		}
		return Math.max(maxBlock, currentBlock);
	}
}

