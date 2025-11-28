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
 * @author Acee
 */
public class InsightMenu  {

	private UserController uc;
	private DatabaseController dbc;
	private ExpenseController ec;

	private boolean backed = false;

	public boolean getStatus(){
		return backed;
	}

	public void setStatus(boolean b){
		backed = b;
	}

	public InsightMenu(UserController uc, DatabaseController dbc, ExpenseController ec){
		this.uc = uc;
		this.dbc = dbc;
		this.ec = ec;

		initComponents();
	}

	public void setVisible(boolean b){
		InsightMenu.setVisible(b);
	}

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

	}

	private void RefreshTables(ActionEvent e) {
		refreshTables();
	}

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
