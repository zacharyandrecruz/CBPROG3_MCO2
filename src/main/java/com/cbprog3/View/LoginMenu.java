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
 * @author zacha
 */
public class LoginMenu{

	private UserController uc;
	private boolean loginComplete = false;

	public boolean getLoginStatus(){
		return loginComplete;
	}

	public void setVisible(boolean b){
		LoginMenu.setVisible(b);
	}

	public boolean isVisible(){
		return LoginMenu.isVisible();
	}

	public LoginMenu(UserController uc) {
		this.uc = uc;

		initComponents();
	}

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
