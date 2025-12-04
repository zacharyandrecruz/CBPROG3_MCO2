package com.cbprog3.View;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.cbprog3.Controller.DatabaseController;
import com.cbprog3.Controller.ExpenseController;
import com.cbprog3.Controller.UserController;
import com.cbprog3.Model.Expense;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 16:24:35 SGT 2025
 */



/**
 * The InsightMenu class provides a comprehensive financial analytics dashboard
 * for the Expense Tracker system. It displays various insights and summaries
 * of expense data through multiple synchronized table views and calculated metrics.
 * 
 * <p>This class implements a multi-panel dashboard with the following analytical features:
 * <ul>
 *   <li>Daily expense tracking with date-wise breakdown and average calculation</li>
 *   <li>Monthly expense overview (interface prepared for future implementation)</li>
 *   <li>Complete expense listing with all transaction details</li>
 *   <li>Category-based expense totals (interface prepared for future implementation)</li>
 *   <li>Real-time calculation of daily spending averages</li>
 *   <li>Total expense amount tracking</li>
 * </ul>
 * 
 * <p>The dashboard is organized into four main sections:
 * <ol>
 *   <li>Daily Expenses - Shows expenses grouped by day with daily average</li>
 *   <li>Monthly Expenses - Reserved for monthly expense analysis</li>
 *   <li>Total Expenses - Displays all individual expense records</li>
 *   <li>Category Totals - Reserved for category-based expense breakdown</li>
 * </ol>
 * 
 * <p>The GUI uses Swing components with MigLayout for a responsive multi-panel layout
 * and follows the Model-View-Controller pattern for data management.
 *
 * @author Cruz, Zachary Andre A.
 * @author Magdaluyo, Alaine Carlo R. 
 * @version 0.6
 */
public class InsightMenu  {

	private UserController uc;
	private DatabaseController dbc;
	private ExpenseController ec;

	private boolean backed = false;

	/**
	 * Returns the navigation status indicating whether the user has requested
	 * to return to the main menu.
	 * 
	 * @return true if the Back button has been pressed and the menu should close,
	 *         false otherwise
	 */
	public boolean getStatus(){
		return backed;
	}

	/**
	 * Sets the navigation status of the InsightMenu.
	 * This method is typically called by the main application controller
	 * to reset the navigation state after returning to the main menu.
	 * 
	 * @param b the new navigation status to set
	 */
	public void setStatus(boolean b){
		backed = b;
	}

	/**
	 * Constructs a new InsightMenu with the specified controllers.
	 * Initializes the GUI components and prepares the analytics dashboard for display.
	 * 
	 * @param uc the UserController for user-related operations and authentication
	 * @param dbc the DatabaseController for loading expense data from the database
	 * @param ec the ExpenseController for expense calculations and analytics
	 */
	public InsightMenu(UserController uc, DatabaseController dbc, ExpenseController ec){
		this.uc = uc;
		this.dbc = dbc;
		this.ec = ec;

		initComponents();
	}

	/**
	 * Sets the visibility of the InsightMenu main window.
	 * 
	 * @param b true to make the window visible, false to hide it
	 */
	public void setVisible(boolean b){
		InsightMenu.setVisible(b);
	}

	/**
	 * Refreshes all tables and analytical data in the insight dashboard.
	 * This method performs a comprehensive update of all displayed information:
	 * 
	 * <p>Update process includes:
	 * <ol>
	 *   <li>Updates expense controller with latest database information</li>
	 *   <li>Refreshes daily expense table with date-wise expense totals</li>
	 *   <li>Calculates and displays daily average expense</li>
	 *   <li>Updates total expenses table with all individual expense records</li>
	 *   <li>Calculates and displays total expense amount</li>
	 *   <li>Prepares interfaces for monthly and category-based analysis (future implementation)</li>
	 * </ol>
	 * 
	 * <p>The daily expense table displays:
	 * <ul>
	 *   <li>Expense Date - The specific date of expenses</li>
	 *   <li>Expense Amount - Total amount spent on that date</li>
	 * </ul>
	 * 
	 * <p>The total expenses table displays:
	 * <ul>
	 *   <li>Expense ID - Unique identifier for each expense</li>
	 *   <li>Expense Amount - Individual expense amount</li>
	 *   <li>Expense Date - Date when expense occurred</li>
	 *   <li>Expense Category - Category classification</li>
	 * </ul>
	 * 
	 * <p>All tables have a minimum column width of 150 pixels for consistent display.
	 */
	public void refreshTables(){

		ec.updateDatabases(uc, dbc);

		//DailyAve

		ArrayList<Float> dailyExpenseList = ec.getDailyExpenses();
		ArrayList<String> dayExpenseList = ec.getDailyExpensesDate();

		TableModel detm = new TableModel() {

			@Override
			public int getRowCount() {
				return dailyExpenseList.size();
			}

			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public String getColumnName(int columnIndex) {
				switch(columnIndex){
					case 0: return "Expense Date";
					case 1: return "Expense Amount";
				}
				return "null";
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex){
					case 0: return String.class;
					case 1: return Float.class;
				}
				return String.class;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {

				switch(columnIndex){
					case 0: return dayExpenseList.get(rowIndex);
					case 1: return dailyExpenseList.get(rowIndex);
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

		DailyTable.setModel(detm);

		TableColumnModel dtcm = DailyTable.getColumnModel();

		for(int i = 0; i < dtcm.getColumnCount(); i++){
			dtcm.getColumn(i).setMinWidth(150);
		}

		DailyTable.setColumnModel(dtcm);

		DailyAverageLabel.setText("Average Daily Expense: " + ec.computeDailyAve());

		//MonthlyAve
		ArrayList<Float> monthlyExpenseList = ec.getMonthlyExpenses();
		ArrayList<String> monthExpenseList = ec.getMonthlyExpensesDate();

		TableModel metm = new TableModel() {

			@Override
			public int getRowCount() {
				return monthlyExpenseList.size();
			}

			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public String getColumnName(int columnIndex) {
				switch(columnIndex){
					case 0: return "Expense Date";
					case 1: return "Expense Amount";
				}
				return "null";
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex){
					case 0: return String.class;
					case 1: return Float.class;
				}
				return String.class;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {

				switch(columnIndex){
					case 0: return monthExpenseList.get(rowIndex);
					case 1: return monthlyExpenseList.get(rowIndex);
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

		MonthlyTable.setModel(metm);

		TableColumnModel mtcm = DailyTable.getColumnModel();

		for(int i = 0; i < mtcm.getColumnCount(); i++){
			mtcm.getColumn(i).setMinWidth(150);
		}
		
		MonthlyTable.setColumnModel(mtcm);

		MonthlyAverageLabel.setText("Average Daily Expense: " + ec.computeMonthlyAverage());

		//Total

		ArrayList<Expense> expenseList = dbc.loadUserExpenses(uc.getCurrentUser().getUserID());

		TableModel tetm = new TableModel() {

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

		TotalTable.setModel(tetm);

		TableColumnModel ttcm = TotalTable.getColumnModel();

		for(int i = 0; i < ttcm.getColumnCount(); i++){
			ttcm.getColumn(i).setMinWidth(150);
		}

		TotalTable.setColumnModel(ttcm);

		TotalAverageLabel.setText("Total Expense Amount: " + ec.getTotalExpenses());

		//Total (Category)
		ArrayList<Float> categoryTotal = new ArrayList<>();

		for(String c : ec.getCategories()){
			categoryTotal.add(ec.getTotalCategoryExpense(c));
		}

		TableModel ctetm = new TableModel() {

			@Override
			public int getRowCount() {
				return categoryTotal.size();
			}

			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public String getColumnName(int columnIndex) {
				switch(columnIndex){
					case 0: return "Category";
					case 1: return "Total Amount";
				}
				return "null";
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex){
					case 0: return String.class;
					case 1: return Float.class;
				}
				return String.class;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				Float f = categoryTotal.get(rowIndex);

				switch(columnIndex){
					case 0: return ec.getCategories().get(rowIndex);
					case 1: return f;
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

		CategoryTotalTable.setModel(ctetm);

		TableColumnModel cttcm = CategoryTotalTable.getColumnModel();

		for(int i = 0; i < cttcm.getColumnCount(); i++){
			cttcm.getColumn(i).setMinWidth(150);
		}

		CategoryTotalTable.setColumnModel(cttcm);

		float aveCat = 0;
		float t = 0;

		for(Float f : categoryTotal){
			t += f;
		}

		aveCat = t/categoryTotal.size();

		TotalAverageLabel.setText("Average Category Expense Amount: " + aveCat);

	}

	/**
	 * Handles the Refresh Tables button action event.
	 * Reloads all analytical data and refreshes all table displays to reflect
	 * the most current expense information from the database.
	 * 
	 * <p>This method ensures that all calculated metrics (daily averages, totals)
	 * and table contents are synchronized with the latest database state.
	 * 
	 * @param e the ActionEvent triggered by clicking the Refresh Tables button
	 */
	private void RefreshTables(ActionEvent e) {
		refreshTables();
	}

	/**
	 * Handles the Back button action event.
	 * Sets the navigation status to true, signaling to the main application
	 * controller that the user wants to return to the main menu.
	 * 
	 * @param e the ActionEvent triggered by clicking the Back button
	 */
	private void Back(ActionEvent e) {
		backed = true;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		InsightMenu = new JFrame();
		DailyExpenseLabel = new JLabel();
		DailyAverageLabel = new JLabel();
		MonthlyExpenseLabel = new JLabel();
		MonthlyAverageLabel = new JLabel();
		DailyPane = new JScrollPane();
		DailyTable = new JTable();
		MonthlyPane = new JScrollPane();
		MonthlyTable = new JTable();
		TotalLabel = new JLabel();
		TotalAverageLabel = new JLabel();
		CategoryTotalLabel = new JLabel();
		TotalPane = new JScrollPane();
		TotalTable = new JTable();
		CategoryTotalPane = new JScrollPane();
		CategoryTotalTable = new JTable();
		RefreshTables = new JButton();
		BackButton = new JButton();

		//======== InsightMenu ========
		{
			InsightMenu.setResizable(false);
			InsightMenu.setTitle("Expense Tracker - Main Menu");
			InsightMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			Container InsightMenuContentPane = InsightMenu.getContentPane();
			InsightMenuContentPane.setLayout(new MigLayout(
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
				"[]" +
				"[grow]" +
				"[]" +
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[]"));

			//---- DailyExpenseLabel ----
			DailyExpenseLabel.setText("Daily Expenses:");
			InsightMenuContentPane.add(DailyExpenseLabel, "cell 0 0");

			//---- DailyAverageLabel ----
			DailyAverageLabel.setText("Average Daily Expense:");
			InsightMenuContentPane.add(DailyAverageLabel, "cell 3 0");

			//---- MonthlyExpenseLabel ----
			MonthlyExpenseLabel.setText("Monthly Expenses");
			InsightMenuContentPane.add(MonthlyExpenseLabel, "cell 4 0");

			//---- MonthlyAverageLabel ----
			MonthlyAverageLabel.setText("Average Monthly Expense:");
			InsightMenuContentPane.add(MonthlyAverageLabel, "cell 8 0");

			//======== DailyPane ========
			{

				//---- DailyTable ----
				DailyTable.setShowHorizontalLines(true);
				DailyTable.setShowVerticalLines(true);
				DailyTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				DailyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				DailyPane.setViewportView(DailyTable);
			}
			InsightMenuContentPane.add(DailyPane, "cell 0 1 4 1,height 200::300");

			//======== MonthlyPane ========
			{

				//---- MonthlyTable ----
				MonthlyTable.setShowHorizontalLines(true);
				MonthlyTable.setShowVerticalLines(true);
				MonthlyTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				MonthlyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				MonthlyPane.setViewportView(MonthlyTable);
			}
			InsightMenuContentPane.add(MonthlyPane, "cell 4 1 5 1,height 200::300");

			//---- TotalLabel ----
			TotalLabel.setText("Total Expenses:");
			InsightMenuContentPane.add(TotalLabel, "cell 0 2");

			//---- TotalAverageLabel ----
			TotalAverageLabel.setText("Average Expense:");
			InsightMenuContentPane.add(TotalAverageLabel, "cell 3 2");

			//---- CategoryTotalLabel ----
			CategoryTotalLabel.setText("Total Expenses (Per Category):");
			InsightMenuContentPane.add(CategoryTotalLabel, "cell 4 2");

			//======== TotalPane ========
			{
				TotalPane.setViewportView(TotalTable);
			}
			InsightMenuContentPane.add(TotalPane, "cell 0 3 4 1,height 200::300");

			//======== CategoryTotalPane ========
			{
				CategoryTotalPane.setViewportView(CategoryTotalTable);
			}
			InsightMenuContentPane.add(CategoryTotalPane, "cell 4 3 5 1,height 200::300");

			//---- RefreshTables ----
			RefreshTables.setText("<html>Refresh<br>Tables</html>");
			RefreshTables.addActionListener(e -> RefreshTables(e));
			InsightMenuContentPane.add(RefreshTables, "cell 0 6,align center center,grow 0 0,width :100:100,hmin 50");

			//---- BackButton ----
			BackButton.setText("Back");
			BackButton.addActionListener(e -> Back(e));
			InsightMenuContentPane.add(BackButton, "cell 8 6,align center center,grow 0 0,width 100,hmin 50");
			InsightMenu.setSize(900, 600);
			InsightMenu.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JFrame InsightMenu;
	private JLabel DailyExpenseLabel;
	private JLabel DailyAverageLabel;
	private JLabel MonthlyExpenseLabel;
	private JLabel MonthlyAverageLabel;
	private JScrollPane DailyPane;
	private JTable DailyTable;
	private JScrollPane MonthlyPane;
	private JTable MonthlyTable;
	private JLabel TotalLabel;
	private JLabel TotalAverageLabel;
	private JLabel CategoryTotalLabel;
	private JScrollPane TotalPane;
	private JTable TotalTable;
	private JScrollPane CategoryTotalPane;
	private JTable CategoryTotalTable;
	private JButton RefreshTables;
	private JButton BackButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
