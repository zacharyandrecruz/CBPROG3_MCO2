package com.cbprog3._JForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 22:40:12 SGT 2025
 */



/**
 * @author Acee
 */
public class BudgetMenu  {

	private void AddBudget(ActionEvent e) {
		// TODO add your code here
	}

	private void EditBudget(ActionEvent e) {
		// TODO add your code here
	}

	private void DeleteBudget(ActionEvent e) {
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
		BudgetMenu = new JFrame();
		BudgetPane = new JScrollPane();
		BudgetTable = new JTable();
		AddBudget = new JButton();
		EditBudget = new JButton();
		DeleteBudget = new JButton();
		BackButton = new JButton();
		RefreshButton = new JButton();
		AddBudgetDialog = new JDialog();
		BudgetAmountLabelAdd = new JLabel();
		BudgetAmountFieldAdd = new JTextField();
		BudgetStartDateLabelAdd = new JLabel();
		DateStartYearFieldAdd = new JTextField();
		DateStartMonthFieldAdd = new JTextField();
		DateStartDayFieldAdd = new JTextField();
		BudgetEndDateLabelAdd = new JLabel();
		DateEndYearFieldAdd = new JTextField();
		DateEndMonthFieldAdd = new JTextField();
		DateEndDayFieldAdd = new JTextField();
		BudgetCategoryLabelAdd = new JLabel();
		CategoryBoxAdd = new JComboBox();
		ConfirmAdd = new JButton();
		CancelAdd = new JButton();
		EditBudgetDialog = new JDialog();
		EditAmountLabel = new JLabel();
		BudgetAmountFieldEdit = new JTextField();
		EditStartDateLabel = new JLabel();
		DateStartYearFieldEdit = new JTextField();
		DateStartMonthFieldEdit = new JTextField();
		DateStartDayFieldEdit = new JTextField();
		EditEndDateLabel = new JLabel();
		DateEndYearFieldEdit = new JTextField();
		DateEndMonthFieldEdit = new JTextField();
		DateEndDayFieldEdit = new JTextField();
		EditCategoryLabel = new JLabel();
		CategoryBoxEdit = new JComboBox();
		ConfirmEdit = new JButton();
		CancelEdit = new JButton();
		DeleteBudgetDialog = new JDialog();
		DeleteLabel = new JLabel();
		ConfirmDelete = new JButton();
		CancelDelete = new JButton();

		//======== BudgetMenu ========
		{
			BudgetMenu.setTitle("Expense Tracker - Manage Budgets");
			BudgetMenu.setResizable(false);
			Container BudgetMenuContentPane = BudgetMenu.getContentPane();
			BudgetMenuContentPane.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]",
				// rows
				"[grow]" +
				"[grow]"));

			//======== BudgetPane ========
			{

				//---- BudgetTable ----
				BudgetTable.setShowHorizontalLines(true);
				BudgetTable.setShowVerticalLines(true);
				BudgetTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				BudgetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				BudgetPane.setViewportView(BudgetTable);
			}
			BudgetMenuContentPane.add(BudgetPane, "cell 0 0 5 1");

			//---- AddBudget ----
			AddBudget.setText("Add");
			AddBudget.setFont(AddBudget.getFont().deriveFont(AddBudget.getFont().getSize() + 3f));
			AddBudget.addActionListener(e -> AddBudget(e));
			BudgetMenuContentPane.add(AddBudget, "cell 0 1,alignx center,growx 0,hmin 35");

			//---- EditBudget ----
			EditBudget.setText("Edit");
			EditBudget.setFont(EditBudget.getFont().deriveFont(EditBudget.getFont().getSize() + 3f));
			EditBudget.addActionListener(e -> EditBudget(e));
			BudgetMenuContentPane.add(EditBudget, "cell 1 1,alignx center,growx 0,hmin 35");

			//---- DeleteBudget ----
			DeleteBudget.setText("Delete");
			DeleteBudget.setFont(DeleteBudget.getFont().deriveFont(DeleteBudget.getFont().getSize() + 3f));
			DeleteBudget.addActionListener(e -> DeleteBudget(e));
			BudgetMenuContentPane.add(DeleteBudget, "cell 2 1,alignx center,growx 0,hmin 35");

			//---- BackButton ----
			BackButton.setText("Back");
			BackButton.addActionListener(e -> Back(e));
			BudgetMenuContentPane.add(BackButton, "cell 4 1,alignx center,growx 0,wmax 75,hmin 35");

			//---- RefreshButton ----
			RefreshButton.setText("Refresh");
			RefreshButton.addActionListener(e -> Refresh(e));
			BudgetMenuContentPane.add(RefreshButton, "cell 3 1,alignx center,growx 0,hmin 35");
			BudgetMenu.setSize(450, 530);
			BudgetMenu.setLocationRelativeTo(null);
		}

		//======== AddBudgetDialog ========
		{
			AddBudgetDialog.setResizable(false);
			AddBudgetDialog.setTitle("Manage Budgets - Add Budget");
			AddBudgetDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			AddBudgetDialog.setType(Window.Type.POPUP);
			Container AddBudgetDialogContentPane = AddBudgetDialog.getContentPane();
			AddBudgetDialogContentPane.setLayout(new MigLayout(
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

			//---- BudgetAmountLabelAdd ----
			BudgetAmountLabelAdd.setText("Budget Amount:");
			AddBudgetDialogContentPane.add(BudgetAmountLabelAdd, "cell 0 0");
			AddBudgetDialogContentPane.add(BudgetAmountFieldAdd, "cell 1 0 3 1");

			//---- BudgetStartDateLabelAdd ----
			BudgetStartDateLabelAdd.setText("Budget Start Date (YYYY-MM-DD):");
			AddBudgetDialogContentPane.add(BudgetStartDateLabelAdd, "cell 0 1");
			AddBudgetDialogContentPane.add(DateStartYearFieldAdd, "cell 1 1,width 100");
			AddBudgetDialogContentPane.add(DateStartMonthFieldAdd, "cell 2 1,width 100");
			AddBudgetDialogContentPane.add(DateStartDayFieldAdd, "cell 3 1,width 100");

			//---- BudgetEndDateLabelAdd ----
			BudgetEndDateLabelAdd.setText("Budget End Date (YYYY-MM-DD):");
			AddBudgetDialogContentPane.add(BudgetEndDateLabelAdd, "cell 0 2");
			AddBudgetDialogContentPane.add(DateEndYearFieldAdd, "cell 1 2,width 100");
			AddBudgetDialogContentPane.add(DateEndMonthFieldAdd, "cell 2 2,width 100");
			AddBudgetDialogContentPane.add(DateEndDayFieldAdd, "cell 3 2,width 100");

			//---- BudgetCategoryLabelAdd ----
			BudgetCategoryLabelAdd.setText("Budget Category:");
			AddBudgetDialogContentPane.add(BudgetCategoryLabelAdd, "cell 0 3");
			AddBudgetDialogContentPane.add(CategoryBoxAdd, "cell 1 3 3 1");

			//---- ConfirmAdd ----
			ConfirmAdd.setText("Confirm");
			ConfirmAdd.addActionListener(e -> ConfirmAdd(e));
			AddBudgetDialogContentPane.add(ConfirmAdd, "cell 1 4 2 1,alignx center,growx 0");

			//---- CancelAdd ----
			CancelAdd.setText("Cancel");
			CancelAdd.addActionListener(e -> CancelAdd(e));
			AddBudgetDialogContentPane.add(CancelAdd, "cell 3 4,alignx center,growx 0");
			AddBudgetDialog.setSize(430, 200);
			AddBudgetDialog.setLocationRelativeTo(null);
		}

		//======== EditBudgetDialog ========
		{
			EditBudgetDialog.setResizable(false);
			EditBudgetDialog.setTitle("Manage Budgets - Edit Budget");
			EditBudgetDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			EditBudgetDialog.setType(Window.Type.POPUP);
			Container EditBudgetDialogContentPane = EditBudgetDialog.getContentPane();
			EditBudgetDialogContentPane.setLayout(new MigLayout(
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
			EditAmountLabel.setText("Budget Amount:");
			EditBudgetDialogContentPane.add(EditAmountLabel, "cell 0 0");
			EditBudgetDialogContentPane.add(BudgetAmountFieldEdit, "cell 1 0 3 1");

			//---- EditStartDateLabel ----
			EditStartDateLabel.setText("Budget Start Date (YYYY-MM-DD):");
			EditBudgetDialogContentPane.add(EditStartDateLabel, "cell 0 1");
			EditBudgetDialogContentPane.add(DateStartYearFieldEdit, "cell 1 1,width 100");
			EditBudgetDialogContentPane.add(DateStartMonthFieldEdit, "cell 2 1,width 100");
			EditBudgetDialogContentPane.add(DateStartDayFieldEdit, "cell 3 1,width 100");

			//---- EditEndDateLabel ----
			EditEndDateLabel.setText("Budget End Date (YYYY-MM-DD):");
			EditBudgetDialogContentPane.add(EditEndDateLabel, "cell 0 2");
			EditBudgetDialogContentPane.add(DateEndYearFieldEdit, "cell 1 2,width 100");
			EditBudgetDialogContentPane.add(DateEndMonthFieldEdit, "cell 2 2,width 100");
			EditBudgetDialogContentPane.add(DateEndDayFieldEdit, "cell 3 2,width 100");

			//---- EditCategoryLabel ----
			EditCategoryLabel.setText("Budget Category:");
			EditBudgetDialogContentPane.add(EditCategoryLabel, "cell 0 3");
			EditBudgetDialogContentPane.add(CategoryBoxEdit, "cell 1 3 3 1");

			//---- ConfirmEdit ----
			ConfirmEdit.setText("Confirm");
			ConfirmEdit.addActionListener(e -> ConfirmEdit(e));
			EditBudgetDialogContentPane.add(ConfirmEdit, "cell 1 4 2 1,alignx center,growx 0");

			//---- CancelEdit ----
			CancelEdit.setText("Cancel");
			CancelEdit.addActionListener(e -> CancelEdit(e));
			EditBudgetDialogContentPane.add(CancelEdit, "cell 3 4,alignx center,growx 0");
			EditBudgetDialog.setSize(430, 200);
			EditBudgetDialog.setLocationRelativeTo(null);
		}

		//======== DeleteBudgetDialog ========
		{
			DeleteBudgetDialog.setTitle("Manage Budgets - Delete Budget");
			DeleteBudgetDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			DeleteBudgetDialog.setResizable(false);
			DeleteBudgetDialog.setType(Window.Type.POPUP);
			Container DeleteBudgetDialogContentPane = DeleteBudgetDialog.getContentPane();
			DeleteBudgetDialogContentPane.setLayout(new MigLayout(
				"fill,hidemode 3",
				// columns
				"[grow,sizegroup 1,fill]" +
				"[grow,sizegroup 1,fill]",
				// rows
				"[]" +
				"[]"));

			//---- DeleteLabel ----
			DeleteLabel.setText("Are you sure you want to delete the selected budget?");
			DeleteLabel.setFont(DeleteLabel.getFont().deriveFont(DeleteLabel.getFont().getSize() + 2f));
			DeleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
			DeleteBudgetDialogContentPane.add(DeleteLabel, "cell 0 0 2 1");

			//---- ConfirmDelete ----
			ConfirmDelete.setText("Confirm");
			ConfirmDelete.addActionListener(e -> ConfirmDelete(e));
			DeleteBudgetDialogContentPane.add(ConfirmDelete, "cell 0 1,alignx center,growx 0,width 100::100");

			//---- CancelDelete ----
			CancelDelete.setText("Cancel");
			CancelDelete.addActionListener(e -> CancelDelete(e));
			DeleteBudgetDialogContentPane.add(CancelDelete, "cell 1 1,alignx center,growx 0,width 100::100");
			DeleteBudgetDialog.setSize(430, 110);
			DeleteBudgetDialog.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JFrame BudgetMenu;
	private JScrollPane BudgetPane;
	private JTable BudgetTable;
	private JButton AddBudget;
	private JButton EditBudget;
	private JButton DeleteBudget;
	private JButton BackButton;
	private JButton RefreshButton;
	private JDialog AddBudgetDialog;
	private JLabel BudgetAmountLabelAdd;
	private JTextField BudgetAmountFieldAdd;
	private JLabel BudgetStartDateLabelAdd;
	private JTextField DateStartYearFieldAdd;
	private JTextField DateStartMonthFieldAdd;
	private JTextField DateStartDayFieldAdd;
	private JLabel BudgetEndDateLabelAdd;
	private JTextField DateEndYearFieldAdd;
	private JTextField DateEndMonthFieldAdd;
	private JTextField DateEndDayFieldAdd;
	private JLabel BudgetCategoryLabelAdd;
	private JComboBox CategoryBoxAdd;
	private JButton ConfirmAdd;
	private JButton CancelAdd;
	private JDialog EditBudgetDialog;
	private JLabel EditAmountLabel;
	private JTextField BudgetAmountFieldEdit;
	private JLabel EditStartDateLabel;
	private JTextField DateStartYearFieldEdit;
	private JTextField DateStartMonthFieldEdit;
	private JTextField DateStartDayFieldEdit;
	private JLabel EditEndDateLabel;
	private JTextField DateEndYearFieldEdit;
	private JTextField DateEndMonthFieldEdit;
	private JTextField DateEndDayFieldEdit;
	private JLabel EditCategoryLabel;
	private JComboBox CategoryBoxEdit;
	private JButton ConfirmEdit;
	private JButton CancelEdit;
	private JDialog DeleteBudgetDialog;
	private JLabel DeleteLabel;
	private JButton ConfirmDelete;
	private JButton CancelDelete;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
