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
import com.cbprog3.Model.Budget;
import com.cbprog3.Model.DateTime;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 16:05:21 SGT 2025
 */



/**
 * The BudgetMenu class provides a graphical user interface for managing budgets
 * in the Expense Tracker system. It allows users to perform CRUD operations
 * (Create, Read, Update, Delete) on budgets through an intuitive table-based interface.
 * 
 * <p>This class implements a complete budget management system with the following features:
 * <ul>
 *   <li>Display all user budgets in a sortable, scrollable table</li>
 *   <li>Add new budgets with amount, date ranges, and category selection</li>
 *   <li>Edit existing budget details with pre-populated forms</li>
 *   <li>Delete budgets with confirmation dialogs</li>
 *   <li>Real-time table refresh after database operations</li>
 *   <li>Navigation back to the main menu</li>
 * </ul>
 * 
 * <p>The GUI is built using Swing components with MigLayout for flexible component positioning.
 * The class follows the Model-View-Controller pattern, delegating data operations to controller classes.
 * 
 * @author Cruz, Zachary Andre A.
 * @author Magdaluyo, Alaine Carlo R.
 * @version 0.6
 * 
 */
public class BudgetMenu  {

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
	 * Sets the navigation status of the BudgetMenu.
	 * This method is typically called by the main application controller
	 * to reset the navigation state.
	 * 
	 * @param b the new navigation status to set
	 */
	public void setStatus(boolean b){
		backed = b;
	}

	/**
	 * Constructs a new BudgetMenu with the specified controllers.
	 * Initializes the GUI components and prepares the menu for display.
	 * 
	 * @param uc the UserController for user-related operations and authentication
	 * @param dbc the DatabaseController for database CRUD operations
	 * @param ec the ExpenseController for category management and expense-related logic
	 */
	public BudgetMenu(UserController uc, DatabaseController dbc, ExpenseController ec){
		this.uc = uc;
		this.dbc = dbc;
		this.ec = ec;

		initComponents();
	}

	/**
	 * Sets the visibility of the BudgetMenu main window.
	 * 
	 * @param b true to make the window visible, false to hide it
	 */
	public void setVisible(boolean b){
		BudgetMenu.setVisible(b);
	}

	/**
	 * Refreshes the budget table with current data from the database.
	 * Loads all budgets for the current user and updates the table model
	 * to display the latest budget information in a formatted table.
	 * 
	 * <p>The table displays the following columns:
	 * <ul>
	 *   <li>Budget ID - Unique identifier for the budget</li>
	 *   <li>Budget Amount - Monetary amount allocated for the budget</li>
	 *   <li>Budget Start Date - Beginning date of the budget period</li>
	 *   <li>Budget End Date - Ending date of the budget period</li>
	 *   <li>Budget Category - Expense category the budget applies to</li>
	 * </ul>
	 * 
	 * <p>Each column has a minimum width of 150 pixels to ensure proper content visibility.
	 * The table is non-editable and displays horizontal and vertical grid lines.
	 */
	public void refreshTable(){

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

	/**
	 * Handles the Refresh button action event.
	 * Reloads the budget table with current data from the database,
	 * ensuring the display reflects any recent changes.
	 * 
	 * @param e the ActionEvent triggered by clicking the Refresh button
	 */
	private void Refresh(ActionEvent e) {
		refreshTable();
	}

	/**
	 * Handles the Add Budget button action event.
	 * Prepares and displays the Add Budget dialog with cleared input fields
	 * and a populated category dropdown containing all available expense categories.
	 * 
	 * <p>The dialog includes fields for:
	 * <ul>
	 *   <li>Budget amount (numeric input)</li>
	 *   <li>Start date (year, month, day components)</li>
	 *   <li>End date (year, month, day components)</li>
	 *   <li>Category selection (dropdown)</li>
	 * </ul>
	 * 
	 * @param e the ActionEvent triggered by clicking the Add Budget button
	 */
	private void AddBudget(ActionEvent e) {
		
		BudgetAmountFieldAdd.setText("");
		DateStartYearFieldAdd.setText("");
		DateStartMonthFieldAdd.setText("");
		DateStartDayFieldAdd.setText("");
		DateEndYearFieldAdd.setText("");
		DateEndMonthFieldAdd.setText("");
		DateEndDayFieldAdd.setText("");

		for(String s : ec.getCategories()){
			CategoryBoxAdd.addItem(s);
		}

		AddBudgetDialog.setVisible(true);

	}

	/**
	 * Handles the Edit Budget button action event.
	 * Prepares and displays the Edit Budget dialog with pre-populated fields
	 * containing the currently selected budget's values.
	 * 
	 * <p>This method only executes if a budget row is selected in the table.
	 * It parses the date strings from the table display to populate the
	 * individual date component fields (year, month, day).
	 * 
	 * @param e the ActionEvent triggered by clicking the Edit Budget button
	 * @throws NumberFormatException if the budget amount cannot be parsed from the table
	 * @throws ArrayIndexOutOfBoundsException if the date string format is invalid
	 */
	private void EditBudget(ActionEvent e) {
		
		if(BudgetTable.getSelectedRow() != -1){

			BudgetAmountFieldEdit.setText(Float.toString((float)BudgetTable.getModel().getValueAt(BudgetTable.getSelectedRow(), 1)));

			String dateStartString = (String) BudgetTable.getModel().getValueAt(BudgetTable.getSelectedRow(), 2);
			String[] dStartStrings = dateStartString.split("/");
			DateStartYearFieldEdit.setText(dStartStrings[2]);
			DateStartMonthFieldEdit.setText(dStartStrings[0]);
			DateStartDayFieldEdit.setText(dStartStrings[1]);

			String dateEndString = (String) BudgetTable.getModel().getValueAt(BudgetTable.getSelectedRow(), 3);
			String[] dEndStrings = dateEndString.split("/");
			DateEndYearFieldEdit.setText(dEndStrings[2]);
			DateEndMonthFieldEdit.setText(dEndStrings[0]);
			DateEndDayFieldEdit.setText(dEndStrings[1]);

			for(String s : ec.getCategories()){
				CategoryBoxEdit.addItem(s);
			}

			CategoryBoxAdd.setSelectedItem((String)BudgetTable.getModel().getValueAt(BudgetTable.getSelectedRow(), 4));

			EditBudgetDialog.setVisible(true);

		}

	}

	/**
	 * Handles the Delete Budget button action event.
	 * Displays a confirmation dialog for budget deletion to prevent accidental data loss.
	 * 
	 * <p>This method only executes if a budget row is selected in the table.
	 * The actual deletion occurs only after user confirmation in the dialog.
	 * 
	 * @param e the ActionEvent triggered by clicking the Delete Budget button
	 */
	private void DeleteBudget(ActionEvent e) {
		if(BudgetTable.getSelectedRow() != -1){
			DeleteBudgetDialog.setVisible(true);
		}
	}

	/**
	 * Handles the Confirm Add button action event in the Add Budget dialog.
	 * Creates a new Budget object from the dialog input fields and saves it to the database.
	 * 
	 * <p>Process flow:
	 * <ol>
	 *   <li>Parse budget amount from text field</li>
	 *   <li>Create DateTime objects for start and end dates</li>
	 *   <li>Get selected category from dropdown</li>
	 *   <li>Create new Budget object (ID generated by database)</li>
	 *   <li>Save budget to database via DatabaseController</li>
	 *   <li>Close dialog and refresh table</li>
	 * </ol>
	 * 
	 * @param e the ActionEvent triggered by clicking the Confirm Add button
	 * @throws NumberFormatException if the budget amount field contains invalid numeric data
	 */
	private void ConfirmAdd(ActionEvent e) {
		
		float amt = Float.parseFloat(BudgetAmountFieldAdd.getText());
		DateTime sdt = new DateTime(DateStartYearFieldAdd.getText(), DateStartMonthFieldAdd.getText(), DateStartDayFieldAdd.getText(), null, null);
		DateTime edt = new DateTime(DateEndYearFieldAdd.getText(), DateEndMonthFieldAdd.getText(), DateEndDayFieldAdd.getText(), null, null);
		String cat = (String) CategoryBoxAdd.getSelectedItem();

		Budget b = new Budget(null, amt, sdt, edt, cat);

		dbc.saveBudget(b, uc.getCurrentUser().getUserID());
		AddBudgetDialog.setVisible(false);
		refreshTable();

	}

	/**
	 * Handles the Cancel Add button action event in the Add Budget dialog.
	 * Closes the dialog without saving any changes or creating a new budget.
	 * 
	 * @param e the ActionEvent triggered by clicking the Cancel Add button
	 */
	private void CancelAdd(ActionEvent e) {
		AddBudgetDialog.setVisible(false);
	}

	/**
	 * Handles the Confirm Edit button action event in the Edit Budget dialog.
	 * Updates the selected budget with new values from the dialog input fields
	 * and persists the changes to the database.
	 * 
	 * <p>Retains the original budget ID while updating all other fields
	 * with the new values provided by the user.
	 * 
	 * @param e the ActionEvent triggered by clicking the Confirm Edit button
	 * @throws NumberFormatException if the budget amount field contains invalid numeric data
	 */
	private void ConfirmEdit(ActionEvent e) {
		float amt = Float.parseFloat(BudgetAmountFieldEdit.getText());
		DateTime sdt = new DateTime(DateStartYearFieldEdit.getText(), DateStartMonthFieldEdit.getText(), DateStartDayFieldEdit.getText(), null, null);
		DateTime edt = new DateTime(DateEndYearFieldEdit.getText(), DateEndMonthFieldEdit.getText(), DateEndDayFieldEdit.getText(), null, null);
		String cat = (String) CategoryBoxEdit.getSelectedItem();

		Budget b = new Budget((String)BudgetTable.getModel().getValueAt(BudgetTable.getSelectedRow(), 0), amt, sdt, edt, cat);

		dbc.updateBudget(b);
		EditBudgetDialog.setVisible(false);
		refreshTable();
	}

	/**
	 * Handles the Cancel Edit button action event in the Edit Budget dialog.
	 * Closes the dialog without saving any changes to the selected budget.
	 * 
	 * @param e the ActionEvent triggered by clicking the Cancel Edit button
	 */
	private void CancelEdit(ActionEvent e) {
		EditBudgetDialog.setVisible(false);
	}

	/**
	 * Handles the Confirm Delete button action event in the Delete Budget dialog.
	 * Permanently deletes the selected budget from the database using its budget ID
	 * and refreshes the table to reflect the change.
	 * 
	 * @param e the ActionEvent triggered by clicking the Confirm Delete button
	 */
	private void ConfirmDelete(ActionEvent e) {
		dbc.deleteBudget((String) BudgetTable.getModel().getValueAt(BudgetTable.getSelectedRow(), 0));
		DeleteBudgetDialog.setVisible(false);
		refreshTable();
	}

	/**
	 * Handles the Cancel Delete button action event in the Delete Budget dialog.
	 * Closes the confirmation dialog without performing the deletion operation.
	 * 
	 * @param e the ActionEvent triggered by clicking the Cancel Delete button
	 */
	private void CancelDelete(ActionEvent e) {
		DeleteBudgetDialog.setVisible(false);
	}

	/**
	 * Initializes all GUI components using JFormDesigner-generated code.
	 * This method sets up the main window, dialogs, and all Swing components
	 * with their respective layouts, sizes, positions, and event handlers.
	 * 
	 * <p><strong>Note:</strong> This method is automatically generated by JFormDesigner
	 * and should not be modified manually. Any changes to the GUI layout should
	 * be made through the JFormDesigner visual editor.
	 */

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
