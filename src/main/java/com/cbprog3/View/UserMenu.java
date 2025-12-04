package com.cbprog3.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.cbprog3.Controller.DatabaseController;
import com.cbprog3.Controller.UserController;
import com.cbprog3.Model.User;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 16:05:38 SGT 2025
 */



/**
 * @author Acee
 */
public class UserMenu  {

	private UserController uc;
	private DatabaseController dbc;

	private boolean updated = false;

	public boolean getStatus(){
		return updated;
	}

	public void setStatus(boolean b){
		updated = b;
	}

	public void setVisible(boolean b){
		UserMenu.setVisible(b);

		EmailField.setText(uc.getCurrentUser().getUserEmail());
		FirstNameField.setText(uc.getCurrentUser().getFirstName());
		LastNameField.setText(uc.getCurrentUser().getSurname());
		PasswordField.setText(uc.getCurrentUser().getUserPassword());

	}

	public boolean isVisible(){
		return UserMenu.isVisible();
	}

	public UserMenu(UserController uc, DatabaseController dbc){
		this.uc = uc;
		this.dbc = dbc;

		initComponents();
	}

	private void Apply(ActionEvent e) {
		
		User u = new User(uc.getCurrentUser().getUserID(), EmailField.getText(), FirstNameField.getText(), null, LastNameField.getText());
		u.setUserPassword(PasswordField.getText());

		dbc.updateUser(u);
		updated = true;

	}

	private void Back(ActionEvent e) {
		updated = true;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		UserMenu = new JFrame();
		EmailLabel = new JLabel();
		EmailField = new JTextField();
		FirstNameLabel = new JLabel();
		FirstNameField = new JTextField();
		LastNameLabel = new JLabel();
		LastNameField = new JTextField();
		PasswordLabel = new JLabel();
		PasswordField = new JTextField();
		ApplyButton = new JButton();
		BackButton = new JButton();

		//======== UserMenu ========
		{
			UserMenu.setResizable(false);
			UserMenu.setTitle("Expense Tracker - Manage User");
			Container UserMenuContentPane = UserMenu.getContentPane();
			UserMenuContentPane.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]",
				// rows
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[grow]"));

			//---- EmailLabel ----
			EmailLabel.setText("Email:");
			EmailLabel.setFont(EmailLabel.getFont().deriveFont(EmailLabel.getFont().getSize() + 2f));
			EmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
			UserMenuContentPane.add(EmailLabel, "cell 0 0");
			UserMenuContentPane.add(EmailField, "cell 1 0 2 1");

			//---- FirstNameLabel ----
			FirstNameLabel.setText("First Name:");
			FirstNameLabel.setFont(FirstNameLabel.getFont().deriveFont(FirstNameLabel.getFont().getSize() + 2f));
			FirstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			UserMenuContentPane.add(FirstNameLabel, "cell 0 1");
			UserMenuContentPane.add(FirstNameField, "cell 1 1 2 1");

			//---- LastNameLabel ----
			LastNameLabel.setText("Last Name:");
			LastNameLabel.setFont(LastNameLabel.getFont().deriveFont(LastNameLabel.getFont().getSize() + 2f));
			LastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			UserMenuContentPane.add(LastNameLabel, "cell 0 2");
			UserMenuContentPane.add(LastNameField, "cell 1 2 2 1");

			//---- PasswordLabel ----
			PasswordLabel.setText("Password:");
			PasswordLabel.setFont(PasswordLabel.getFont().deriveFont(PasswordLabel.getFont().getSize() + 2f));
			PasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
			UserMenuContentPane.add(PasswordLabel, "cell 0 3");
			UserMenuContentPane.add(PasswordField, "cell 1 3 2 1");

			//---- ApplyButton ----
			ApplyButton.setText("Apply Changes");
			ApplyButton.addActionListener(e -> Apply(e));
			UserMenuContentPane.add(ApplyButton, "cell 1 4,alignx center,growx 0,height 35");

			//---- BackButton ----
			BackButton.setText("Back");
			BackButton.addActionListener(e -> Back(e));
			UserMenuContentPane.add(BackButton, "cell 2 4,alignx center,growx 0,height 35");
			UserMenu.pack();
			UserMenu.setLocationRelativeTo(UserMenu.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JFrame UserMenu;
	private JLabel EmailLabel;
	private JTextField EmailField;
	private JLabel FirstNameLabel;
	private JTextField FirstNameField;
	private JLabel LastNameLabel;
	private JTextField LastNameField;
	private JLabel PasswordLabel;
	private JTextField PasswordField;
	private JButton ApplyButton;
	private JButton BackButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
