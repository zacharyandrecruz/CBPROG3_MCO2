package com.cbprog3._JForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 15:33:15 SGT 2025
 */



/**
 * @author Acee
 */
public class LoginMenu  {

	private void Login(ActionEvent e) {
		// TODO add your code here
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
