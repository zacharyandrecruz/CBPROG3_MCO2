package com.cbprog3._JForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 14:54:53 SGT 2025
 */



/**
 * @author Acee
 */
public class MainMenu  {

	private void ManageUsers(ActionEvent e) {
		// TODO add your code here
	}

	private void ManageExpenses(ActionEvent e) {
		// TODO add your code here
	}

	private void ManageBudgets(ActionEvent e) {
		// TODO add your code here
	}

	private void ViewInsights(ActionEvent e) {
		// TODO add your code here
	}

	private void RefreshTables(ActionEvent e) {
		// TODO add your code here
	}

	private void Bank(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		MainMenu = new JFrame();
		UserLabel = new JLabel();
		BankButton = new JButton();
		ExpensePane = new JScrollPane();
		ExpenseTable = new JTable();
		DigitalExpensePane = new JScrollPane();
		DigitalExpenseTable = new JTable();
		BudgetPane = new JScrollPane();
		BudgetTable = new JTable();
		ExpenseButton = new JButton();
		BudgetButton = new JButton();
		UserButton = new JButton();
		RefreshTables = new JButton();
		InsightButton = new JButton();

		//======== MainMenu ========
		{
			MainMenu.setResizable(false);
			MainMenu.setTitle("Expense Tracker - Main Menu");
			MainMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			Container MainMenuContentPane = MainMenu.getContentPane();
			MainMenuContentPane.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]",
				// rows
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[]"));

			//---- UserLabel ----
			UserLabel.setText("Logged in as: ");
			MainMenuContentPane.add(UserLabel, "cell 0 0");

			//---- BankButton ----
			BankButton.setText("Manage Banks");
			BankButton.addActionListener(e -> Bank(e));
			MainMenuContentPane.add(BankButton, "cell 8 0,alignx center,growx 0");

			//======== ExpensePane ========
			{

				//---- ExpenseTable ----
				ExpenseTable.setShowHorizontalLines(true);
				ExpenseTable.setShowVerticalLines(true);
				ExpenseTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				ExpenseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				ExpensePane.setViewportView(ExpenseTable);
			}
			MainMenuContentPane.add(ExpensePane, "cell 0 1 2 1");

			//======== DigitalExpensePane ========
			{

				//---- DigitalExpenseTable ----
				DigitalExpenseTable.setShowHorizontalLines(true);
				DigitalExpenseTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				DigitalExpenseTable.setShowVerticalLines(true);
				DigitalExpenseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				DigitalExpensePane.setViewportView(DigitalExpenseTable);
			}
			MainMenuContentPane.add(DigitalExpensePane, "cell 2 1 2 1");

			//======== BudgetPane ========
			{

				//---- BudgetTable ----
				BudgetTable.setShowHorizontalLines(true);
				BudgetTable.setShowVerticalLines(true);
				BudgetTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				BudgetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				BudgetPane.setViewportView(BudgetTable);
			}
			MainMenuContentPane.add(BudgetPane, "cell 4 1 5 1,width 300::423");

			//---- ExpenseButton ----
			ExpenseButton.setText("<html>Manage<br>Expenses</html>");
			ExpenseButton.addActionListener(e -> ManageExpenses(e));
			MainMenuContentPane.add(ExpenseButton, "cell 0 3,alignx center,growx 0,width :100:100,hmin 50");

			//---- BudgetButton ----
			BudgetButton.setText("<html>Manage<br>Budgets</html>");
			BudgetButton.addActionListener(e -> ManageBudgets(e));
			MainMenuContentPane.add(BudgetButton, "cell 8 3,alignx center,growx 0,width :100:100,hmin 50");

			//---- UserButton ----
			UserButton.setText("<html>Manage<br>User</html>");
			UserButton.addActionListener(e -> ManageUsers(e));
			MainMenuContentPane.add(UserButton, "cell 2 4,align center center,grow 0 0,width :100:100,hmin 50");

			//---- RefreshTables ----
			RefreshTables.setText("<html>Refresh<br>Tables</html>");
			RefreshTables.addActionListener(e -> RefreshTables(e));
			MainMenuContentPane.add(RefreshTables, "cell 3 4 2 1,alignx center,growx 0,width :100:100,hmin 50");

			//---- InsightButton ----
			InsightButton.setText("<html>View<br>Insights</html>");
			InsightButton.addActionListener(e -> ViewInsights(e));
			MainMenuContentPane.add(InsightButton, "cell 5 4,alignx center,growx 0,width :100:100,hmin 50");
			MainMenu.setSize(900, 600);
			MainMenu.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JFrame MainMenu;
	private JLabel UserLabel;
	private JButton BankButton;
	private JScrollPane ExpensePane;
	private JTable ExpenseTable;
	private JScrollPane DigitalExpensePane;
	private JTable DigitalExpenseTable;
	private JScrollPane BudgetPane;
	private JTable BudgetTable;
	private JButton ExpenseButton;
	private JButton BudgetButton;
	private JButton UserButton;
	private JButton RefreshTables;
	private JButton InsightButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
