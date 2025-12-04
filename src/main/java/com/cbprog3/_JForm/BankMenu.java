package com.cbprog3._JForm;

import java.awt.*;
import java.awt.event.*;
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

	private void Add(ActionEvent e) {
		// TODO add your code here
	}

	private void Edit(ActionEvent e) {
		// TODO add your code here
	}

	private void Delete(ActionEvent e) {
		// TODO add your code here
	}

	private void Refresh(ActionEvent e) {
		// TODO add your code here
	}

	private void Back(ActionEvent e) {
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
		BankMenu = new JFrame();
		BankPane = new JScrollPane();
		BankTable = new JTable();
		AddButton = new JButton();
		EditButton = new JButton();
		DeleteButton = new JButton();
		RefreshButton = new JButton();
		BackButton = new JButton();
		AddExpenseDialog = new JDialog();
		ExpenseAmountLabelAdd = new JLabel();
		ExpenseAmountFieldAdd = new JTextField();
		ExpenseDateLabelAdd = new JLabel();
		DateYearFieldAdd = new JTextField();
		DateMonthFieldAdd = new JTextField();
		DateDayFieldAdd = new JTextField();
		ExpenseCategoryLabelAdd = new JLabel();
		CategoryBoxAdd = new JComboBox();
		ExpenseBankLabelAdd = new JLabel();
		BankBoxAdd = new JComboBox();
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
		EditBankLabel = new JLabel();
		BankBoxEdit = new JComboBox();
		ConfirmEdit = new JButton();
		CancelEdit = new JButton();
		DeleteExpenseDialog = new JDialog();
		DeleteLabel = new JLabel();
		ConfirmDelete = new JButton();
		CancelDelete = new JButton();

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
			AddButton.addActionListener(e -> Add(e));
			BankMenuContentPane.add(AddButton, "cell 0 1,alignx center,growx 0,wmin 71,hmin 50");

			//---- EditButton ----
			EditButton.setText("Edit");
			EditButton.addActionListener(e -> Edit(e));
			BankMenuContentPane.add(EditButton, "cell 1 1,alignx center,growx 0,wmin 71,hmin 50");

			//---- DeleteButton ----
			DeleteButton.setText("Delete");
			DeleteButton.addActionListener(e -> Delete(e));
			BankMenuContentPane.add(DeleteButton, "cell 2 1,alignx center,growx 0,wmin 71,hmin 50");

			//---- RefreshButton ----
			RefreshButton.setText("Refresh");
			RefreshButton.addActionListener(e -> Refresh(e));
			BankMenuContentPane.add(RefreshButton, "cell 3 1,wmin 71,hmin 50");

			//---- BackButton ----
			BackButton.setText("Back");
			BackButton.addActionListener(e -> Back(e));
			BankMenuContentPane.add(BackButton, "cell 4 1,alignx center,growx 0,wmin 71,hmin 50");
			BankMenu.setSize(400, 355);
			BankMenu.setLocationRelativeTo(null);
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

			//---- ExpenseBankLabelAdd ----
			ExpenseBankLabelAdd.setText("Bank:");
			AddExpenseDialogContentPane.add(ExpenseBankLabelAdd, "cell 0 3");
			AddExpenseDialogContentPane.add(BankBoxAdd, "cell 1 3 3 1");

			//---- ConfirmAdd ----
			ConfirmAdd.setText("Confirm");
			ConfirmAdd.addActionListener(e -> ConfirmAdd(e));
			AddExpenseDialogContentPane.add(ConfirmAdd, "cell 1 4 2 1,alignx center,growx 0");

			//---- CancelAdd ----
			CancelAdd.setText("Cancel");
			CancelAdd.addActionListener(e -> CancelAdd(e));
			AddExpenseDialogContentPane.add(CancelAdd, "cell 3 4,alignx center,growx 0");
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

			//---- EditBankLabel ----
			EditBankLabel.setText("Bank:");
			EditExpenseDialogContentPane.add(EditBankLabel, "cell 0 3");
			EditExpenseDialogContentPane.add(BankBoxEdit, "cell 1 3 3 1");

			//---- ConfirmEdit ----
			ConfirmEdit.setText("Confirm");
			ConfirmEdit.addActionListener(e -> ConfirmEdit(e));
			EditExpenseDialogContentPane.add(ConfirmEdit, "cell 1 4 2 1,alignx center,growx 0");

			//---- CancelEdit ----
			CancelEdit.setText("Cancel");
			CancelEdit.addActionListener(e -> CancelEdit(e));
			EditExpenseDialogContentPane.add(CancelEdit, "cell 3 4,alignx center,growx 0");
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
	private JFrame BankMenu;
	private JScrollPane BankPane;
	private JTable BankTable;
	private JButton AddButton;
	private JButton EditButton;
	private JButton DeleteButton;
	private JButton RefreshButton;
	private JButton BackButton;
	private JDialog AddExpenseDialog;
	private JLabel ExpenseAmountLabelAdd;
	private JTextField ExpenseAmountFieldAdd;
	private JLabel ExpenseDateLabelAdd;
	private JTextField DateYearFieldAdd;
	private JTextField DateMonthFieldAdd;
	private JTextField DateDayFieldAdd;
	private JLabel ExpenseCategoryLabelAdd;
	private JComboBox CategoryBoxAdd;
	private JLabel ExpenseBankLabelAdd;
	private JComboBox BankBoxAdd;
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
	private JLabel EditBankLabel;
	private JComboBox BankBoxEdit;
	private JButton ConfirmEdit;
	private JButton CancelEdit;
	private JDialog DeleteExpenseDialog;
	private JLabel DeleteLabel;
	private JButton ConfirmDelete;
	private JButton CancelDelete;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
