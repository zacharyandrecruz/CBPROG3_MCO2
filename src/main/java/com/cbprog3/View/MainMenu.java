package com.cbprog3.View;

import java.awt.Container;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.cbprog3.Controller.*;
import com.cbprog3.Model.*;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 10:05:56 PST 2025
 */



/**
 * @author zacha
 */
public class MainMenu{

	private boolean switchingTime = false;
	private String switchTo;

	private UserController uc;
	private DatabaseController dbc;
	
	public boolean getSwitchStatus(){
		return switchingTime;
	}

	public String getSwitchDestination(){
		return switchTo;
	}

	public MainMenu(UserController uc, DatabaseController dbc) {
		this.uc = uc;
		this.dbc = dbc;
		initComponents();
	}

	public void setVisible(boolean b){
		MainMenu.setVisible(b);
		switchingTime = false;
		switchTo = "";
	}

	public boolean isVisible(){
		return MainMenu.isVisible();
	}

	public void setUserLabel(String text){
		UserLabel.setText("Logged in as: " + text);
	}

	public void reloadTables(){

		//Load Expenses
		ArrayList<Expense> expenseList = dbc.loadUserExpenses(uc.getCurrentUser().getUserID());

		TableModel etm = new TableModel() {

			@Override
			public int getRowCount() {
				return expenseList.size();
			}

			@Override
			public int getColumnCount() {
				return 4;
			}

			@Override
			public String getColumnName(int columnIndex) {
				switch(columnIndex){
					case 0: return "Expense ID";
					case 1: return "Expense Amount";
					case 2: return "Expense Date";
					case 3: return "Expense Category";
				}
				return "null";
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex){
					case 0: return String.class;
					case 1: return Float.class;
					case 2: return String.class;
					case 3: return String.class;
				}
				return String.class;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				Expense e = expenseList.get(rowIndex);

				switch(columnIndex){
					case 0: return e.getExpenseID();
					case 1: return e.getExpenseAmount();
					case 2: return e.getExpenseDateTime().getDateString();
					case 3: return e.getExpenseCategory();
				}
				return null;

			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				System.out.println("Attempted to set value at: (" + Integer.toString(rowIndex) + ", " + Integer.toString(columnIndex) + ")");
			}

			@Override
			public void addTableModelListener(TableModelListener l) {
			}

			@Override
			public void removeTableModelListener(TableModelListener l) {
			}
			
		};

		ExpenseTable.setModel(etm);

		TableColumnModel tcm = ExpenseTable.getColumnModel();

		for(int i = 0; i < tcm.getColumnCount(); i++){
			tcm.getColumn(i).setMinWidth(150);
		}

		ExpenseTable.setColumnModel(tcm);

		//Load Budgets

		ArrayList<Budget> budgetList = dbc.loadUserBudgets(uc.getCurrentUser().getUserID());

		TableModel btm = new TableModel() {

			@Override
			public int getRowCount() {
				return budgetList.size();
			}

			@Override
			public int getColumnCount() {
				return 5;
			}

			@Override
			public String getColumnName(int columnIndex) {
				switch(columnIndex){
					case 0: return "Budget ID";
					case 1: return "Budget Amount";
					case 2: return "Budget Start Date";
					case 3: return "Budget End Date";
					case 4: return "Budget Category";
				}
				return "null";
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex){
					case 0: return String.class;
					case 1: return Float.class;
					case 2: return String.class;
					case 3: return String.class;
					case 4: return String.class;
				}
				return String.class;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				Budget b = budgetList.get(rowIndex);

				switch(columnIndex){
					case 0: return b.getBudgetID();
					case 1: return b.getBudgetAmt();
					case 2: return b.getBudgetStart().getDateString();
					case 3: return b.getBudgetEnd().getDateString();
					case 4: return b.getBudgetCategory();
				}
				return null;

			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				System.out.println("Attempted to set value at: (" + Integer.toString(rowIndex) + ", " + Integer.toString(columnIndex) + ")");
			}

			@Override
			public void addTableModelListener(TableModelListener l) {
			}

			@Override
			public void removeTableModelListener(TableModelListener l) {
			}
			
		};

		BudgetTable.setModel(btm);

		TableColumnModel btcm = BudgetTable.getColumnModel();

		for(int i = 0; i < btcm.getColumnCount(); i++){
			btcm.getColumn(i).setMinWidth(150);
		}

		BudgetTable.setColumnModel(btcm);

	}

	private void ManageUsers(ActionEvent e) {
		switchTo = "manageUsers";
		switchingTime = true;
	}

	private void ManageExpenses(ActionEvent e) {
		switchTo = "manageExpenses";
		switchingTime = true;
	}

	private void ManageBudgets(ActionEvent e) {
		switchTo = "manageBudgets";
		switchingTime = true;
	}

	private void ViewInsights(ActionEvent e) {
		switchTo = "viewInsights";
		switchingTime = true;
	}

	private void RefreshTables(ActionEvent e) {
		reloadTables();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		MainMenu = new JFrame();
		UserLabel = new JLabel();
		ExpensePane = new JScrollPane();
		ExpenseTable = new JTable();
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

			//======== ExpensePane ========
			{

				//---- ExpenseTable ----
				ExpenseTable.setShowHorizontalLines(true);
				ExpenseTable.setShowVerticalLines(true);
				ExpenseTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				ExpenseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				ExpensePane.setViewportView(ExpenseTable);
			}
			MainMenuContentPane.add(ExpensePane, "cell 0 1 4 1");

			//======== BudgetPane ========
			{

				//---- BudgetTable ----
				BudgetTable.setShowHorizontalLines(true);
				BudgetTable.setShowVerticalLines(true);
				BudgetTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				BudgetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				BudgetPane.setViewportView(BudgetTable);
			}
			MainMenuContentPane.add(BudgetPane, "cell 4 1 5 1");

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
	private JScrollPane ExpensePane;
	private JTable ExpenseTable;
	private JScrollPane BudgetPane;
	private JTable BudgetTable;
	private JButton ExpenseButton;
	private JButton BudgetButton;
	private JButton UserButton;
	private JButton RefreshTables;
	private JButton InsightButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
