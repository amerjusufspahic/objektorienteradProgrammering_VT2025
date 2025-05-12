package lab3.GUI;

import lab3.accountManager.BankFunctions;
import lab3.users.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class BankingGUI extends JFrame{

	private final JTextArea outputArea;
	private final JButton depositButton;
	private final JButton withdrawButton;
	private final JButton transferButton;
	private final JRadioButton showHistoryButton;
	private final JRadioButton hideHistoryButton;
	private final BankFunctions manager; 
	private final UserData db = new UserData();
	private String loggedInUsername; 

	public BankingGUI() {
		super("Simple Banking System");
		manager = new BankFunctions();
		manager.setBalance(db.getBalance(loggedInUsername));
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());


		// Top: welcome and balance panel
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel welcomeLabel = new JLabel("\n <<< Welcome to Simple Banking System>>>");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(welcomeLabel, BorderLayout.NORTH);
		
		outputArea = new JTextArea("Current Balance: " + manager.getBalance() + "\n"); 
		outputArea.setEditable(false);
		JScrollPane scrollPanel = new JScrollPane(outputArea);
		topPanel.add(scrollPanel, BorderLayout.CENTER);



		showHistoryButton = new JRadioButton("Show History", true);
		hideHistoryButton = new JRadioButton("Hide History");
		ButtonGroup group = new ButtonGroup();
		group.add(showHistoryButton);
		group.add(hideHistoryButton);
		JPanel radioPanel = new JPanel();
		radioPanel.add(showHistoryButton);
		radioPanel.add(hideHistoryButton);
		topPanel.add(radioPanel, BorderLayout.SOUTH);
		
		hideHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputArea.setText("Current Balance: " + manager.getBalance() + "\n");
			}
		});
		add(topPanel, BorderLayout.CENTER);

		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		depositButton = new JButton("Deposit");
		withdrawButton = new JButton("Withdraw");
		transferButton = new JButton("Transfer");
		buttonPanel.add(depositButton);
		buttonPanel.add(withdrawButton);
		buttonPanel.add(transferButton);
		add(buttonPanel, BorderLayout.SOUTH);



		ButtonHandler handler = new ButtonHandler();
		depositButton.addActionListener(handler);
		withdrawButton.addActionListener(handler);
		transferButton.addActionListener(handler);
		setVisible(true);
	}

	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			int newBalance = manager.getBalance(); // hämta nytt saldo från BankFunctions
			
			
			String transactionType = "";
			if (a.getSource() == depositButton) transactionType = "deposit";
			if (a.getSource() == withdrawButton) transactionType = "withdraw";
			if (a.getSource() == transferButton) transactionType = "transfer";

			String input = JOptionPane.showInputDialog(
					BankingGUI.this,
					"Enter amount to " + transactionType + ":",
					"Input Amount",
					JOptionPane.PLAIN_MESSAGE
					);



			if (input == null) return;


			try {
				int amount = Integer.parseInt(input.trim());
				manager.processTransaction(transactionType, amount);
				db.updateBalance(loggedInUsername, newBalance);
				db.logTransaction(loggedInUsername, transactionType, amount);

				if (hideHistoryButton.isSelected()) {
					outputArea.setText("Current Balance: " + manager.getBalance()
					+ "\n");
				} else {
					outputArea.append(transactionType.substring(0,
							1).toUpperCase() + transactionType.substring(1) + "ed " + amount + "\n");
					outputArea.append("Updated Balance: " + manager.getBalance() +
							"\n");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(BankingGUI.this, "Please enter avalid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(BankingGUI.this, e.getMessage(),
						"Transaction Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
		
	public static void main(String[] args) {
		UserData db = new UserData();
		
	    for (int i = 0; i < 3; i++) {
	        JPanel panel = new JPanel(new GridLayout(2, 2));
	        JTextField usernameField = new JTextField();
	        JPasswordField passwordField = new JPasswordField();

	        panel.add(new JLabel("Username:"));
	        panel.add(usernameField);
	        panel.add(new JLabel("Password:"));
	        panel.add(passwordField);

	        int result = JOptionPane.showConfirmDialog(
	                null,
	                panel,
	                "Login",
	                JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.QUESTION_MESSAGE
	        );

	        if (result == JOptionPane.OK_OPTION) {
	            String user = usernameField.getText();
	            String pass = new String(passwordField.getPassword());

	            if (db.authenticate(user, pass)) {
	                BankingGUI gui = new BankingGUI();
	                gui.loggedInUsername = user;
	                return;
	            } else {
	                JOptionPane.showMessageDialog(
	                        null,
	                        "Username or password is incorrect.",
	                        "Login Failed",
	                        JOptionPane.ERROR_MESSAGE
	                );
	            }
	        } else {
	            System.exit(0); 
	        }
	    }

	    JOptionPane.showMessageDialog(
	            null,
	            "Too many failed attempts. Program will exit.",
	            "Access Denied",
	            JOptionPane.ERROR_MESSAGE
	    );
	    System.exit(0);
	}
}