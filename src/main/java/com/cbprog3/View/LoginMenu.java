package com.cbprog3.View;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.cbprog3.Controller.UserController;

import net.miginfocom.swing.MigLayout;
/*
 * Created by JFormDesigner on Fri Nov 28 10:23:02 PST 2025
 */



/**
 * The LoginMenu class provides the user authentication interface for the Expense Tracker system.
 * It serves as the entry point to the application, handling user login credentials
 * and managing the transition to the main application upon successful authentication.
 * 
 * <p>This class implements a simple yet secure login interface with the following features:
 * <ul>
 *   <li>Email and password input fields with pre-populated demo credentials</li>
 *   <li>Real-time authentication against user database</li>
 *   <li>Login status tracking for application flow control</li>
 *   <li>Clean, centered layout with proper visual hierarchy</li>
 *   <li>Debug logging for development and troubleshooting</li>
 * </ul>
 * 
 * <p>The login process involves:
 * <ol>
 *   <li>User enters email and password credentials</li>
 *   <li>Credentials are validated against the UserController</li>
 *   <li>Upon success, login status is set and main application launches</li>
 *   <li>Upon failure, user remains on login screen with debug feedback</li>
 * </ol>
 * 
 * <p>Default demo credentials are provided for testing:
 * <ul>
 *   <li>Email: user123@email.com</li>
 *   <li>Password: password123</li>
 * </ul>
 * 
 * @author zacha
 * @version 1.0
 * @since 2025
 * 
 * @see UserController
 */
public class LoginMenu{

	private UserController uc;
	private boolean loginComplete = false;

	/**
	 * Returns the current login authentication status.
	 * This status is used by the main application controller to determine
	 * when to transition from the login screen to the main application.
	 * 
	 * @return true if user has successfully authenticated, false otherwise
	 */
	public boolean getLoginStatus(){
		return loginComplete;
	}

	/**
	 * Sets the visibility of the LoginMenu window.
	 * 
	 * @param b true to make the window visible, false to hide it
	 */
	public void setVisible(boolean b){
		LoginMenu.setVisible(b);
	}

	/**
	 * Checks the current visibility state of the LoginMenu window.
	 * 
	 * @return true if the window is currently visible, false otherwise
	 */
	public boolean isVisible(){
		return LoginMenu.isVisible();
	}

	/**
	 * Constructs a new LoginMenu with the specified UserController.
	 * Initializes the GUI components and prepares the login interface for display.
	 * 
	 * @param uc the UserController responsible for handling user authentication
	 *           and credential validation
	 */
	public LoginMenu(UserController uc) {
		this.uc = uc;

		initComponents();
	}

	/**
	 * Handles the Login button action event.
	 * Processes user credentials and attempts authentication through the UserController.
	 * 
	 * <p>Process flow:
	 * <ol>
	 *   <li>Captures email and password from input fields</li>
	 *   <li>Outputs debug information to console for development tracking</li>
	 *   <li>Delegates authentication to UserController's login method</li>
	 *   <li>Sets login status to true upon successful authentication</li>
	 *   <li>Maintains login status as false for failed attempts</li>
	 * </ol>
	 * 
	 * <p>Debug information includes:
	 * <ul>
	 *   <li>Login button press confirmation</li>
	 *   <li>Entered email address</li>
	 *   <li>Entered password (for development purposes only)</li>
	 *   <li>Authentication success/failure status</li>
	 *   <li>Final login status and visibility state</li>
	 * </ul>
	 * 
	 * @param e the ActionEvent triggered by clicking the Login button
	 * 
	 * @see UserController#login(String, String)
	 */
	private void Login(ActionEvent e) {
		
		System.out.println("PRESSED LOGIN! AAHAHSDUASHDOANN");
		System.out.println("EMAIL: " + EmailField.getText());
		System.out.println("PASSWORD: " + PasswordField.getText());

		if(uc.login(EmailField.getText(), PasswordField.getText())){
			System.out.println("Login Successful!!! :D");
			loginComplete = true;
			System.out.println("Login Status = " + loginComplete);
			System.out.println("Is Visible = " + LoginMenu.isVisible());
		}else{
			System.out.println("Login Failed... :(");
		}

	}

	/**
	 * Initializes all GUI components using JFormDesigner-generated code.
	 * This method sets up the login interface with proper layout, styling,
	 * and component positioning.
	 * 
	 * <p>The login interface includes:
	 * <ul>
	 *   <li>Application title label with enhanced font size and center alignment</li>
	 *   <li>Email input field with label and pre-populated demo email</li>
	 *   <li>Password input field with label and pre-populated demo password</li>
	 *   <li>Login button with event handler for authentication</li>
	 * </ul>
	 * 
	 * <p>Layout characteristics:
	 * <ul>
	 *   <li>Uses MigLayout for flexible component positioning</li>
	 *   <li>Centered title spanning multiple columns</li>
	 *   <li>Properly aligned form labels and input fields</li>
	 *   <li>Centered login button with specific dimensions</li>
	 *   <li>Non-resizable window for consistent user experience</li>
	 *   <li>Application termination on window close</li>
	 * </ul>
	 * 
	 * <p><strong>Note:</strong> This method is automatically generated by JFormDesigner
	 * and should not be modified manually. Any changes to the GUI layout should
	 * be made through the JFormDesigner visual editor.
	 */

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		LoginMenu = new JFrame();
		TitleLabel = new JLabel();
		EmailLabel = new JLabel();
		EmailField = new JTextField();
		PasswordLabel = new JLabel();
		PasswordField = new JTextField();
		LoginButton = new JButton();

		//======== LoginMenu ========
		{
			LoginMenu.setTitle("Expense Tracker - Login Menu");
			LoginMenu.setResizable(false);
			LoginMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			Container LoginMenuContentPane = LoginMenu.getContentPane();
			LoginMenuContentPane.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]",
				// rows
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[]" +
				"[grow]"));

			//---- TitleLabel ----
			TitleLabel.setText("Expense Tracker");
			TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			TitleLabel.setFont(TitleLabel.getFont().deriveFont(TitleLabel.getFont().getSize() + 8f));
			LoginMenuContentPane.add(TitleLabel, "cell 2 1 4 1");

			//---- EmailLabel ----
			EmailLabel.setText("Email:");
			LoginMenuContentPane.add(EmailLabel, "cell 1 2 2 1");

			//---- EmailField ----
			EmailField.setText("user123@email.com");
			LoginMenuContentPane.add(EmailField, "cell 2 2 4 1,alignx trailing,growx 0,wmin 200");

			//---- PasswordLabel ----
			PasswordLabel.setText("Password:");
			LoginMenuContentPane.add(PasswordLabel, "cell 1 3 2 1");

			//---- PasswordField ----
			PasswordField.setText("password123");
			LoginMenuContentPane.add(PasswordField, "cell 2 3 4 1,alignx trailing,growx 0,wmin 200");

			//---- LoginButton ----
			LoginButton.setText("Login");
			LoginButton.addActionListener(e -> Login(e));
			LoginMenuContentPane.add(LoginButton, "cell 4 4,alignx center,growx 0,wmax 100,hmin 40");
			LoginMenu.setSize(400, 400);
			LoginMenu.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JFrame LoginMenu;
	private JLabel TitleLabel;
	private JLabel EmailLabel;
	private JTextField EmailField;
	private JLabel PasswordLabel;
	private JTextField PasswordField;
	private JButton LoginButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
