package com.cbprog3._JForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 16:24:35 SGT 2025
 */



/**
 * @author Acee
 */
public class InsightMenu  {

	private void RefreshTables(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		InsightMenu = new JFrame();
		label3 = new JLabel();
		label4 = new JLabel();
		ExpensePane = new JScrollPane();
		ExpenseTable = new JTable();
		BudgetPane = new JScrollPane();
		BudgetTable = new JTable();
		label1 = new JLabel();
		label2 = new JLabel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		scrollPane2 = new JScrollPane();
		table2 = new JTable();
		RefreshTables = new JButton();
		button1 = new JButton();

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

			//---- label3 ----
			label3.setText("text");
			InsightMenuContentPane.add(label3, "cell 0 0");

			//---- label4 ----
			label4.setText("text");
			InsightMenuContentPane.add(label4, "cell 4 0");

			//======== ExpensePane ========
			{

				//---- ExpenseTable ----
				ExpenseTable.setShowHorizontalLines(true);
				ExpenseTable.setShowVerticalLines(true);
				ExpenseTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				ExpenseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				ExpensePane.setViewportView(ExpenseTable);
			}
			InsightMenuContentPane.add(ExpensePane, "cell 0 1 4 1,height 200::300");

			//======== BudgetPane ========
			{

				//---- BudgetTable ----
				BudgetTable.setShowHorizontalLines(true);
				BudgetTable.setShowVerticalLines(true);
				BudgetTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				BudgetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				BudgetPane.setViewportView(BudgetTable);
			}
			InsightMenuContentPane.add(BudgetPane, "cell 4 1 5 1,height 200::300");

			//---- label1 ----
			label1.setText("text");
			InsightMenuContentPane.add(label1, "cell 0 2");

			//---- label2 ----
			label2.setText("text");
			InsightMenuContentPane.add(label2, "cell 4 2");

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(table1);
			}
			InsightMenuContentPane.add(scrollPane1, "cell 0 3 4 1,height 200::300");

			//======== scrollPane2 ========
			{
				scrollPane2.setViewportView(table2);
			}
			InsightMenuContentPane.add(scrollPane2, "cell 4 3 5 1,height 200::300");

			//---- RefreshTables ----
			RefreshTables.setText("<html>Refresh<br>Tables</html>");
			RefreshTables.addActionListener(e -> RefreshTables(e));
			InsightMenuContentPane.add(RefreshTables, "cell 0 6,align center center,grow 0 0,width :100:100,hmin 50");

			//---- button1 ----
			button1.setText("Back");
			InsightMenuContentPane.add(button1, "cell 8 6,align center center,grow 0 0,width 100,hmin 50");
			InsightMenu.setSize(900, 600);
			InsightMenu.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JFrame InsightMenu;
	private JLabel label3;
	private JLabel label4;
	private JScrollPane ExpensePane;
	private JTable ExpenseTable;
	private JScrollPane BudgetPane;
	private JTable BudgetTable;
	private JLabel label1;
	private JLabel label2;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JScrollPane scrollPane2;
	private JTable table2;
	private JButton RefreshTables;
	private JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
