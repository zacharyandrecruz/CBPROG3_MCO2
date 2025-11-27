package com.cbprog3.View;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Thu Nov 27 23:35:47 SGT 2025
 */



/**
 * @author Acee
 */
public class MainMenu extends JFrame {
	
	public MainMenu() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		TitleLabel = new JLabel();
		EmailLabel = new JLabel();
		EmailField = new JTextField();
		PasswordLabel = new JLabel();
		PasswordField = new JTextField();
		LoginButton = new JButton();

		//======== this ========
		setTitle("Expense Tracker - Main Menu");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
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
		contentPane.add(TitleLabel, "cell 2 1 4 1");

		//---- EmailLabel ----
		EmailLabel.setText("Email:");
		contentPane.add(EmailLabel, "cell 2 2 2 1");
		contentPane.add(EmailField, "cell 4 2 2 1");

		//---- PasswordLabel ----
		PasswordLabel.setText("Password:");
		contentPane.add(PasswordLabel, "cell 2 3 2 1");
		contentPane.add(PasswordField, "cell 4 3 2 1");

		//---- LoginButton ----
		LoginButton.setText("Login");
		contentPane.add(LoginButton, "cell 4 4,alignx center,growx 0,wmax 100,hmin 40");
		setSize(400, 400);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JLabel TitleLabel;
	private JLabel EmailLabel;
	private JTextField EmailField;
	private JLabel PasswordLabel;
	private JTextField PasswordField;
	private JButton LoginButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
