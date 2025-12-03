package com.cbprog3._JForm;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Thu Dec 04 06:00:41 SGT 2025
 */



/**
 * @author Acee
 */
public class BankMenu {
	public BankMenu() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		BankMenu = new JFrame();
		BankPane = new JScrollPane();
		BankTable = new JTable();
		AddButton = new JButton();
		EditButton = new JButton();
		DeleteButton = new JButton();
		RefreshButton = new JButton();
		BackButton = new JButton();

		//======== BankMenu ========
		{
			BankMenu.setTitle("Expense Tracker - Bank Menu");
			BankMenu.setResizable(false);
			Container BankMenuContentPane = BankMenu.getContentPane();
			BankMenuContentPane.setLayout(new MigLayout(
				"fill,hidemode 3",
				// columns
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]",
				// rows
				"[]" +
				"[]"));

			//======== BankPane ========
			{
				BankPane.setViewportView(BankTable);
			}
			BankMenuContentPane.add(BankPane, "cell 0 0 5 1,hmax 250");

			//---- AddButton ----
			AddButton.setText("Add");
			BankMenuContentPane.add(AddButton, "cell 0 1,alignx center,growx 0,wmin 71,hmin 50");

			//---- EditButton ----
			EditButton.setText("Edit");
			BankMenuContentPane.add(EditButton, "cell 1 1,alignx center,growx 0,wmin 71,hmin 50");

			//---- DeleteButton ----
			DeleteButton.setText("Delete");
			BankMenuContentPane.add(DeleteButton, "cell 2 1,alignx center,growx 0,wmin 71,hmin 50");

			//---- RefreshButton ----
			RefreshButton.setText("Refresh");
			BankMenuContentPane.add(RefreshButton, "cell 3 1,wmin 71,hmin 50");

			//---- BackButton ----
			BackButton.setText("Back");
			BankMenuContentPane.add(BackButton, "cell 4 1,alignx center,growx 0,wmin 71,hmin 50");
			BankMenu.setSize(400, 355);
			BankMenu.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JFrame BankMenu;
	private JScrollPane BankPane;
	private JTable BankTable;
	private JButton AddButton;
	private JButton EditButton;
	private JButton DeleteButton;
	private JButton RefreshButton;
	private JButton BackButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
