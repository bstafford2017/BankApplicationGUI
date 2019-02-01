package BankApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class BankAppGUI extends JFrame {
	
	// Store address of Accounts file
	// (Future: Retrieve data from JDBC, instead of file)
	String address = "C:\\Users\\Benjamin\\Dropbox\\JavaProject\\All Accounts\\accounts";
	
	// Data structure for storing username and password
	// (Future: implement HASHING into data structure)
	public HashMap<String, String> map = new HashMap<>();
	
	// Purpose: to access field data in Action Listeners classes
	private JTextField userField; // for LoginListener
	private JPasswordField passField; // for LoginListener
	private JPasswordField conField; // for CreateListener
	
	private final int WIDTH = 650;
	private final int HEIGHT = 400;
	private final int LOGIN_WIDTH = 420;
	private final int LOGIN_HEIGHT = 250;
	
	public BankAppGUI() {
		
		// Instantiate object "global" members
		userField = new JTextField(25);
		passField = new JPasswordField(25);
		
		// Instantiate panels for GridLayout
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		// Instantiate local variables
		JLabel title = new JLabel("Bank Application Login");
		JLabel userLabel = new JLabel("Enter a Username:");
		JLabel passLabel = new JLabel("Enter a Password: ");
		JButton loginButton = new JButton("Login");
		JButton registerButton = new JButton("Register");
		
		
		// Set JFrames attributes
		setTitle("Bank Application");
		setSize(LOGIN_WIDTH, LOGIN_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(5, 1)); // (5 rows, 1 column)
		setResizable(false);
		
		// Change title font
		title.setFont(new Font("", Font.PLAIN, 20));
		
		// Add action listener BankAppGUI 
		loginButton.addActionListener(new LoginListener());
		registerButton.addActionListener(new RegisterListener());
		
		// Add components to JPanel #1
		panel1.add(title);
		
		// Add components to JPanel #2
		panel2.add(userLabel);
		panel2.add(userField);
		
		// Add components to JPanel #3
		panel3.add(passLabel);
		panel3.add(passField);
		
		// Add component to JPanel #4
		panel4.add(loginButton);
		
		// Add component to JPanel $5
		panel5.add(registerButton);	
		
		// Add JPanels to JFrame
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		
		//pack();
		setVisible(true);
	}
	
	public void mainPage() {
		
		// Set new window
		setSize(WIDTH, HEIGHT);
		
		// Remove all previous components
		getContentPane().removeAll();
		repaint();
		
		/*
		 * BEGIN HERE FOR DEVELOPING MAIN PAGE!
		 */
	}
	
	public void loadMap(File file) {
		Scanner sc = null;
		
		// For FNF
		try {
			sc = new Scanner(file);
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
			System.exit(0);
		}
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
			// Split line into String[] by delimiter
			String[] delim = line.split(":");
			
			// Trim input
			delim[0] = delim[0].trim();
			delim[1] = delim[1].trim();
			
			// Put each entry into HashMap
			map.put(delim[0], delim[1]);
		}
		sc.close();
	}
	
	public boolean checkLogin(String username, String password) {
		for(String key : map.keySet()) {
			if(key.equals(username)) {
				if(map.get(key).equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void saveFile() {
		PrintWriter pw = null;
		
		// For FNF
		try {
			pw = new PrintWriter(new File(address));
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
		
		// Print all key and value pairs
		for(String key : map.keySet()) {
			pw.println(key + " : " + map.get(key));
		}
		pw.close();
	}

	public static void main(String[] args) {
		new BankAppGUI();
	}
	
	/*
	 * Inner classes for Action Listener implementation 
	 */
	
	private class LoginListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			// Get username and password
			String username = userField.getText();
			String password = new String(passField.getPassword());
			
			
			// Trim input
			username = username.trim();
			password = password.trim();
			
			File file = new File(address);
			
			// Load HashMap from File
			loadMap(file);
			
			// Check login credentials
			if(checkLogin(username, password)) {
				JOptionPane.showMessageDialog(null, "You Successfully Logged In!");
				mainPage();
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Credentials!");
				
				// Reset text fields
				userField.setText("");
				passField.setText("");
			}
			
			// Print out username and password to console
			System.out.println(username + " " + password);
		}
	}
	
	private class RegisterListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			// Remove all previous components
			getContentPane().removeAll();
			getContentPane().repaint();
			
			// Instantiate panels for GridLayout
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			JPanel panel3 = new JPanel();
			JPanel panel4 = new JPanel();
			JPanel panel5 = new JPanel();
			JPanel panel6 = new JPanel();
			
			// Instantiate local variables
			JLabel title = new JLabel("Register Banking Account");
			JLabel username = new JLabel("Username: ");
			JLabel password = new JLabel("Password: "); 
			JLabel confirm = new JLabel("Confirm Password: ");
			userField = new JTextField(25);
			passField = new JPasswordField(25);
			conField = new JPasswordField(25);
			JButton registerButton = new JButton("Register");
			JButton backButton = new JButton("Back");
							
			setLayout(new GridLayout(6, 1)); // (5 rows, 1 column)
			
			title.setFont(new Font("", Font.PLAIN, 20));
			
			// Add action listener BankAppGUI 
			registerButton.addActionListener(new CreateListener());
			backButton.addActionListener(new BackListener());
			
			// Add components to JPanel #1
			panel1.add(title);
			
			// Add components to JPanel #2
			panel2.add(username);
			panel2.add(userField);
			
			// Add components to JPanel #3
			panel3.add(password);
			panel3.add(passField);
			
			// Add component to JPanel #4
			panel4.add(confirm);
			panel4.add(conField);
			
			// Add component to JPanel #5
			panel5.add(registerButton);
			
			// Add component to JPanel #5
			panel6.add(backButton);
			
			// Add JPanels to JFrame
			add(panel1);
			add(panel2);
			add(panel3);
			add(panel4);
			add(panel5);
			add(panel6);
			
			setVisible(true);
		}
		
		private class CreateListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				
				// Get user input
				String username = new String(userField.getText());
				String password = new String(passField.getPassword());
				String conPass = new String(conField.getPassword());
				
				// Trim input
				username = username.trim();
				password = password.trim();
				conPass = conPass.trim();
				
				if(password.equals(conPass)) {
					if(!checkLogin(username, password)) {
						map.put(username, password);
						JOptionPane.showMessageDialog(null,"Account Successfully Created!");
						saveFile();
						setVisible(false);
						new BankAppGUI();
					} else {
						JOptionPane.showMessageDialog(null,"Username and Password Already Exist!");
						userField.setText("");
						passField.setText("");
						conField.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null,"Password and Confirm Password Do Not Match!");
					userField.setText("");
					passField.setText("");
					conField.setText("");
				}
			}
			
		}

		private class BackListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new BankAppGUI();
			}
		}
	}
}