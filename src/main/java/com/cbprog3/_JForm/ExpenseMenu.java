package com.cbprog3._JForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 17:54:58 SGT 2025
 */



/**
 * @author Acee
 */
public class ExpenseMenu  {

	private void AddExpense(ActionEvent e) {
		// TODO add your code here
	}

	private void EditExpense(ActionEvent e) {
		// TODO add your code here
	}

	private void DeleteExpense(ActionEvent e) {
		// TODO add your code here
	}

	private void Back(ActionEvent e) {
		// TODO add your code here
	}

	private void Refresh(ActionEvent e) {
		// TODO add your code here
	}

	private void ConfirmAdd(ActionEvent e) {
		// TODO add your code here
	}

	private void CancelAdd(ActionEvent e) {
		// TODO add your code here
	}

	private void ConfirmEdit(ActionEvent e) {
		// TODO add your code here
	}

	private void CancelEdit(ActionEvent e) {
		// TODO add your code here
	}

	private void ConfirmDelete(ActionEvent e) {
		// TODO add your code here
	}

	private void CancelDelete(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		ExpenseMenu = new JFrame();
		ExpensePane = new JScrollPane();
		ExpenseTable = new JTable();
		AddExpense = new JButton();
		EditExpense = new JButton();
		DeleteExpense = new JButton();
		BackButton = new JButton();
		RefreshButton = new JButton();
		AddExpenseDialog = new JDialog();
		ExpenseAmountLabelAdd = new JLabel();
		ExpenseAmountFieldAdd = new JTextField();
		ExpenseDateLabelAdd = new JLabel();
		DateYearFieldAdd = new JTextField();
		DateMonthFieldAdd = new JTextField();
		DateDayFieldAdd = new JTextField();
		ExpenseCategoryLabelAdd = new JLabel();
		CategoryBoxAdd = new JComboBox();
		ConfirmAdd = new JButton();
		CancelAdd = new JButton();
		EditExpenseDialog = new JDialog();
		EditAmountLabel = new JLabel();
		ExpenseAmountFieldEdit = new JTextField();
		EditDateLabel = new JLabel();
		DateYearFieldEdit = new JTextField();
		DateMonthFieldEdit = new JTextField();
		DateDayFieldEdit = new JTextField();
		EditCategoryLabel = new JLabel();
		CategoryBoxEdit = new JComboBox();
		ConfirmEdit = new JButton();
		CancelEdit = new JButton();
		DeleteExpenseDialog = new JDialog();
		DeleteLabel = new JLabel();
		ConfirmDelete = new JButton();
		CancelDelete = new JButton();

		//======== ExpenseMenu ========
		{
			ExpenseMenu.setResizable(false);
			ExpenseMenu.setTitle("Expense Tracker - Manage Expenses");
			ExpenseMenu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			Container ExpenseMenuContentPane = ExpenseMenu.getContentPane();
			ExpenseMenuContentPane.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]",
				// rows
				"[grow]" +
				"[grow]" +
				"[grow]" +
				"[]"));

			//======== ExpensePane ========
			{

				//---- ExpenseTable ----
				ExpenseTable.setShowHorizontalLines(true);
				ExpenseTable.setShowVerticalLines(true);
				ExpenseTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				ExpenseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				ExpensePane.setViewportView(ExpenseTable);
			}
			ExpenseMenuContentPane.add(ExpensePane, "cell 0 0 5 2");

			//---- AddExpense ----
			AddExpense.setText("Add");
			AddExpense.setFont(AddExpense.getFont().deriveFont(AddExpense.getFont().getSize() + 3f));
			AddExpense.addActionListener(e -> AddExpense(e));
			ExpenseMenuContentPane.add(AddExpense, "cell 0 2 1 2,alignx center,growx 0,hmin 35");

			//---- EditExpense ----
			EditExpense.setText("Edit");
			EditExpense.setFont(EditExpense.getFont().deriveFont(EditExpense.getFont().getSize() + 3f));
			EditExpense.addActionListener(e -> EditExpense(e));
			ExpenseMenuContentPane.add(EditExpense, "cell 1 2 1 2,alignx center,growx 0,hmin 35");

			//---- DeleteExpense ----
			DeleteExpense.setText("Delete");
			DeleteExpense.setFont(DeleteExpense.getFont().deriveFont(DeleteExpense.getFont().getSize() + 3f));
			DeleteExpense.addActionListener(e -> DeleteExpense(e));
			ExpenseMenuContentPane.add(DeleteExpense, "cell 2 2 1 2,alignx center,growx 0,hmin 35");

			//---- BackButton ----
			BackButton.setText("Back");
			BackButton.setFont(BackButton.getFont().deriveFont(BackButton.getFont().getSize() + 2f));
			BackButton.addActionListener(e -> Back(e));
			ExpenseMenuContentPane.add(BackButton, "cell 4 2 1 2,alignx center,growx 0,wmax 75,hmin 35");

			//---- RefreshButton ----
			RefreshButton.setText("Refresh");
			RefreshButton.addActionListener(e -> Refresh(e));
			ExpenseMenuContentPane.add(RefreshButton, "cell 3 2 1 2,alignx center,growx 0,wmin 90,hmin 35");
			ExpenseMenu.setSize(450, 530);
			ExpenseMenu.setLocationRelativeTo(null);
		}

		//======== AddExpenseDialog ========
		{
			AddExpenseDialog.setResizable(false);
			AddExpenseDialog.setTitle("Manage Expenses - Add Expense");
			AddExpenseDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			AddExpenseDialog.setType(Window.Type.POPUP);
			Container AddExpenseDialogContentPane = AddExpenseDialog.getContentPane();
			AddExpenseDialogContentPane.setLayout(new MigLayout(
				"fill,hidemode 3",
				// columns
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[fill]",
				// rows
				"[]" +
				"[]" +
				"[]" +
				"[]"));

			//---- ExpenseAmountLabelAdd ----
			ExpenseAmountLabelAdd.setText("Expense Amount:");
			AddExpenseDialogContentPane.add(ExpenseAmountLabelAdd, "cell 0 0");
			AddExpenseDialogContentPane.add(ExpenseAmountFieldAdd, "cell 1 0 3 1");

			//---- ExpenseDateLabelAdd ----
			ExpenseDateLabelAdd.setText("Expense Date (YYYY-MM-DD):");
			AddExpenseDialogContentPane.add(ExpenseDateLabelAdd, "cell 0 1");
			AddExpenseDialogContentPane.add(DateYearFieldAdd, "cell 1 1,width 100");
			AddExpenseDialogContentPane.add(DateMonthFieldAdd, "cell 2 1,width 100");
			AddExpenseDialogContentPane.add(DateDayFieldAdd, "cell 3 1,width 100");

			//---- ExpenseCategoryLabelAdd ----
			ExpenseCategoryLabelAdd.setText("Expense Category:");
			AddExpenseDialogContentPane.add(ExpenseCategoryLabelAdd, "cell 0 2");
			AddExpenseDialogContentPane.add(CategoryBoxAdd, "cell 1 2 3 1");

			//---- ConfirmAdd ----
			ConfirmAdd.setText("Confirm");
			ConfirmAdd.addActionListener(e -> ConfirmAdd(e));
			AddExpenseDialogContentPane.add(ConfirmAdd, "cell 1 3 2 1,alignx center,growx 0");

			//---- CancelAdd ----
			CancelAdd.setText("Cancel");
			CancelAdd.addActionListener(e -> CancelAdd(e));
			AddExpenseDialogContentPane.add(CancelAdd, "cell 3 3,alignx center,growx 0");
			AddExpenseDialog.setSize(430, 200);
			AddExpenseDialog.setLocationRelativeTo(null);
		}

		//======== EditExpenseDialog ========
		{
			EditExpenseDialog.setResizable(false);
			EditExpenseDialog.setTitle("Manage Expenses - Edit Expense");
			EditExpenseDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			EditExpenseDialog.setType(Window.Type.POPUP);
			Container EditExpenseDialogContentPane = EditExpenseDialog.getContentPane();
			EditExpenseDialogContentPane.setLayout(new MigLayout(
				"fill,hidemode 3",
				// columns
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[fill]",
				// rows
				"[]" +
				"[]" +
				"[]" +
				"[]"));

			//---- EditAmountLabel ----
			EditAmountLabel.setText("Expense Amount:");
			EditExpenseDialogContentPane.add(EditAmountLabel, "cell 0 0");
			EditExpenseDialogContentPane.add(ExpenseAmountFieldEdit, "cell 1 0 3 1");

			//---- EditDateLabel ----
			EditDateLabel.setText("Expense Date (YYYY-MM-DD):");
			EditExpenseDialogContentPane.add(EditDateLabel, "cell 0 1");
			EditExpenseDialogContentPane.add(DateYearFieldEdit, "cell 1 1,width 100");
			EditExpenseDialogContentPane.add(DateMonthFieldEdit, "cell 2 1,width 100");
			EditExpenseDialogContentPane.add(DateDayFieldEdit, "cell 3 1,width 100");

			//---- EditCategoryLabel ----
			EditCategoryLabel.setText("Expense Category:");
			EditExpenseDialogContentPane.add(EditCategoryLabel, "cell 0 2");
			EditExpenseDialogContentPane.add(CategoryBoxEdit, "cell 1 2 3 1");

			//---- ConfirmEdit ----
			ConfirmEdit.setText("Confirm");
			ConfirmEdit.addActionListener(e -> ConfirmEdit(e));
			EditExpenseDialogContentPane.add(ConfirmEdit, "cell 1 3 2 1,alignx center,growx 0");

			//---- CancelEdit ----
			CancelEdit.setText("Cancel");
			CancelEdit.addActionListener(e -> CancelEdit(e));
			EditExpenseDialogContentPane.add(CancelEdit, "cell 3 3,alignx center,growx 0");
			EditExpenseDialog.setSize(430, 200);
			EditExpenseDialog.setLocationRelativeTo(null);
		}

		//======== DeleteExpenseDialog ========
		{
			DeleteExpenseDialog.setTitle("Manage Expenses - Delete Expense");
			DeleteExpenseDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			DeleteExpenseDialog.setResizable(false);
			DeleteExpenseDialog.setType(Window.Type.POPUP);
			Container DeleteExpenseDialogContentPane = DeleteExpenseDialog.getContentPane();
			DeleteExpenseDialogContentPane.setLayout(new MigLayout(
				"fill,hidemode 3",
				// columns
				"[grow,sizegroup 1,fill]" +
				"[grow,sizegroup 1,fill]",
				// rows
				"[]" +
				"[]"));

			//---- DeleteLabel ----
			DeleteLabel.setText("Are you sure you want to delete the selected expense?");
			DeleteLabel.setFont(DeleteLabel.getFont().deriveFont(DeleteLabel.getFont().getSize() + 2f));
			DeleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
			DeleteExpenseDialogContentPane.add(DeleteLabel, "cell 0 0 2 1");

			//---- ConfirmDelete ----
			ConfirmDelete.setText("Confirm");
			ConfirmDelete.addActionListener(e -> ConfirmDelete(e));
			DeleteExpenseDialogContentPane.add(ConfirmDelete, "cell 0 1,alignx center,growx 0,width 100::100");

			//---- CancelDelete ----
			CancelDelete.setText("Cancel");
			CancelDelete.addActionListener(e -> CancelDelete(e));
			DeleteExpenseDialogContentPane.add(CancelDelete, "cell 1 1,alignx center,growx 0,width 100::100");
			DeleteExpenseDialog.setSize(430, 110);
			DeleteExpenseDialog.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JFrame ExpenseMenu;
	private JScrollPane ExpensePane;
	private JTable ExpenseTable;
	private JButton AddExpense;
	private JButton EditExpense;
	private JButton DeleteExpense;
	private JButton BackButton;
	private JButton RefreshButton;
	private JDialog AddExpenseDialog;
	private JLabel ExpenseAmountLabelAdd;
	private JTextField ExpenseAmountFieldAdd;
	private JLabel ExpenseDateLabelAdd;
	private JTextField DateYearFieldAdd;
	private JTextField DateMonthFieldAdd;
	private JTextField DateDayFieldAdd;
	private JLabel ExpenseCategoryLabelAdd;
	private JComboBox CategoryBoxAdd;
	private JButton ConfirmAdd;
	private JButton CancelAdd;
	private JDialog EditExpenseDialog;
	private JLabel EditAmountLabel;
	private JTextField ExpenseAmountFieldEdit;
	private JLabel EditDateLabel;
	private JTextField DateYearFieldEdit;
	private JTextField DateMonthFieldEdit;
	private JTextField DateDayFieldEdit;
	private JLabel EditCategoryLabel;
	private JComboBox CategoryBoxEdit;
	private JButton ConfirmEdit;
	private JButton CancelEdit;
	private JDialog DeleteExpenseDialog;
	private JLabel DeleteLabel;
	private JButton ConfirmDelete;
	private JButton CancelDelete;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
