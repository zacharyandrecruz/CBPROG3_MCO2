package com.cbprog3.View;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.cbprog3.Controller.DatabaseController;
import com.cbprog3.Controller.ExpenseController;
import com.cbprog3.Controller.UserController;
import com.cbprog3.Model.Bank;
import com.cbprog3.Model.DateTime;
import com.cbprog3.Model.Expense;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 17:46:40 SGT 2025
 */



/**
 * The ExpenseMenu class provides a graphical user interface for managing expenses
 * in the Expense Tracker system. It enables users to perform comprehensive CRUD operations
 * (Create, Read, Update, Delete) on expenses through an intuitive table-based interface.
 * 
 * <p>This class implements a complete expense management system with the following features:
 * <ul>
 *   <li>Display all user expenses in a sortable, scrollable table with grid lines</li>
 *   <li>Add new expenses with amount, date, and category selection</li>
 *   <li>Edit existing expense details with pre-populated forms</li>
 *   <li>Delete expenses with confirmation dialogs to prevent accidental data loss</li>
 *   <li>Real-time table refresh after database operations</li>
 *   <li>Navigation back to the main menu</li>
 *   <li>Support for categorized expense tracking</li>
 * </ul>
 * 
 * <p>The GUI is built using Swing components with MigLayout for flexible component positioning.
 * The class follows the Model-View-Controller pattern, delegating data operations to controller classes
 * and maintaining separation between UI logic and business logic.
 * 
 * @author Cruz, Zachary Andre A.
 * @author Magdaluyo, Alaine Carlo R.
 * @version 0.6
 *
 */
public class ExpenseMenu  {

	private UserController uc;
	private DatabaseController dbc;
	private ExpenseController ec;

	private boolean backed = false;
	private int selectedTable;

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
	 * Sets the navigation status of the ExpenseMenu.
	 * This method is typically called by the main application controller
	 * to reset the navigation state after returning to the main menu.
	 * 
	 * @param b the new navigation status to set
	 */
	public void setStatus(boolean b){
		backed = b;
	}

	/**
	 * Constructs a new ExpenseMenu with the specified controllers.
	 * Initializes the GUI components and prepares the menu for display.
	 * 
	 * @param uc the UserController for user-related operations and authentication
	 * @param dbc the DatabaseController for database CRUD operations on expenses
	 * @param ec the ExpenseController for category management and expense-related business logic
	 */
	public ExpenseMenu(UserController uc, DatabaseController dbc, ExpenseController ec){
		this.uc = uc;
		this.dbc = dbc;
		this.ec = ec;

		initComponents();
	}

	/**
	 * Sets the visibility of the ExpenseMenu main window.
	 * 
	 * @param b true to make the window visible, false to hide it
	 */
	public void setVisible(boolean b){
		ExpenseMenu.setVisible(b);
	}

	/**
	 * Refreshes the expense table with current data from the database.
	 * Loads all expenses for the current user and updates the table model
	 * to display the latest expense information in a formatted table.
	 * 
	 * <p>The table displays the following columns:
	 * <ul>
	 *   <li>Expense ID - Unique identifier for the expense</li>
	 *   <li>Expense Amount - Monetary amount of the expense</li>
	 *   <li>Expense Date - Date when the expense occurred</li>
	 *   <li>Expense Category - Category classification of the expense</li>
	 * </ul>
	 * 
	 * <p>Each column has a minimum width of 150 pixels to ensure proper content visibility.
	 * The table is non-editable and displays horizontal and vertical grid lines for better readability.
	 * The table uses single interval selection mode and auto-resize is disabled to maintain column widths.
	 */
	public void refreshTable(){

		ArrayList<Expense> rawExpenseList = dbc.loadUserExpenses(uc.getCurrentUser().getUserID());

		ArrayList<Expense> expenseList = new ArrayList<>();
		ArrayList<Expense> digitalExpenseList = new ArrayList<>();

		for(int i = 0; i < rawExpenseList.size(); i++){
			if(rawExpenseList.get(i).getExpenseBank() != null){
				digitalExpenseList.add(rawExpenseList.get(i));
			}else{
				expenseList.add(rawExpenseList.get(i));
			}
		}

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

		TableModel detm = new TableModel() {

			@Override
			public int getRowCount() {
				return digitalExpenseList.size();
			}

			@Override
			public int getColumnCount() {
				return 5;
			}

			@Override
			public String getColumnName(int columnIndex) {
				switch(columnIndex){
					case 0: return "Expense ID";
					case 1: return "Expense Amount";
					case 2: return "Expense Date";
					case 3: return "Expense Category";
					case 4: return "Bank";
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
				
				Expense e = digitalExpenseList.get(rowIndex);

				switch(columnIndex){
					case 0: return e.getExpenseID();
					case 1: return e.getExpenseAmount();
					case 2: return e.getExpenseDateTime().getDateString();
					case 3: return e.getExpenseCategory();
					case 4: return e.getExpenseBank().getBankName();
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

		DigitalExpenseTable.setModel(detm);

		TableColumnModel dtcm = DigitalExpenseTable.getColumnModel();

		for(int i = 0; i < dtcm.getColumnCount(); i++){
			dtcm.getColumn(i).setMinWidth(150);
		}

		DigitalExpenseTable.setColumnModel(dtcm);

	}

	/**
	 * Handles the Add Expense button action event.
	 * Prepares and displays the Add Expense dialog with cleared input fields
	 * and a populated category dropdown containing all available expense categories.
	 * 
	 * <p>The dialog includes fields for:
	 * <ul>
	 *   <li>Expense amount (numeric input)</li>
	 *   <li>Expense date (year, month, day components in separate fields)</li>
	 *   <li>Category selection (dropdown with all available categories)</li>
	 * </ul>
	 * 
	 * @param e the ActionEvent triggered by clicking the Add Expense button
	 */
	private void AddExpense(ActionEvent e) {
		ExpenseAmountFieldAdd.setText("");
		DateYearFieldAdd.setText("");
		DateMonthFieldAdd.setText("");
		DateDayFieldAdd.setText("");

		CategoryBoxAdd.removeAllItems();

		for(String s : ec.getCategories()){
			CategoryBoxAdd.addItem(s);
		}

		BankBoxAdd.removeAllItems();

		BankBoxAdd.addItem("None");

		for(Bank b : uc.getCurrentUser().getUserBanks()){
			BankBoxAdd.addItem(b.getBankName());
		}

		AddExpenseDialog.setVisible(true);
	}

	/**
	 * Handles the Edit Expense button action event.
	 * Prepares and displays the Edit Expense dialog with pre-populated fields
	 * containing the currently selected expense's values.
	 * 
	 * <p>This method only executes if an expense row is selected in the table.
	 * It parses the date string from the table display (MM/DD/YYYY format) to populate
	 * the individual date component fields (year, month, day).
	 * 
	 * @param e the ActionEvent triggered by clicking the Edit Expense button
	 * @throws NumberFormatException if the expense amount cannot be parsed from the table
	 * @throws ArrayIndexOutOfBoundsException if the date string format is invalid
	 */
	private void EditExpense(ActionEvent e) {
		
		if(selectedTable == 1){

			ExpenseAmountFieldEdit.setText(Float.toString((float)ExpenseTable.getModel().getValueAt(ExpenseTable.getSelectedRow(), 1)));
			String dateString = (String) ExpenseTable.getModel().getValueAt(ExpenseTable.getSelectedRow(), 2);
			String[] dStrings = dateString.split("/");
			DateYearFieldEdit.setText(dStrings[2]);
			DateMonthFieldEdit.setText(dStrings[0]);
			DateDayFieldEdit.setText(dStrings[1]);

			CategoryBoxEdit.removeAllItems();

			for(String s : ec.getCategories()){
				CategoryBoxEdit.addItem(s);
			}

			CategoryBoxEdit.setSelectedItem(ExpenseTable.getModel().getValueAt(ExpenseTable.getSelectedRow(), 3));
			
			BankBoxEdit.removeAllItems();

			BankBoxEdit.addItem("None");

			for(Bank b : uc.getCurrentUser().getUserBanks()){
				BankBoxEdit.addItem(b.getBankName());
			}
			
			CategoryBoxEdit.setSelectedItem(e);

			EditExpenseDialog.setVisible(true);

		}else{

			ExpenseAmountFieldEdit.setText(Float.toString((float)DigitalExpenseTable.getModel().getValueAt(DigitalExpenseTable.getSelectedRow(), 1)));
			String dateString = (String) DigitalExpenseTable.getModel().getValueAt(DigitalExpenseTable.getSelectedRow(), 2);
			String[] dStrings = dateString.split("/");
			DateYearFieldEdit.setText(dStrings[2]);
			DateMonthFieldEdit.setText(dStrings[0]);
			DateDayFieldEdit.setText(dStrings[1]);

			CategoryBoxEdit.removeAllItems();

			for(String s : ec.getCategories()){
				CategoryBoxEdit.addItem(s);
			}

			CategoryBoxEdit.setSelectedItem(DigitalExpenseTable.getModel().getValueAt(DigitalExpenseTable.getSelectedRow(), 3));
			
			BankBoxEdit.removeAllItems();

			BankBoxEdit.addItem("None");

			for(Bank b : uc.getCurrentUser().getUserBanks()){
				BankBoxEdit.addItem(b.getBankName());
			}

			BankBoxEdit.setSelectedItem(DigitalExpenseTable.getModel().getValueAt(DigitalExpenseTable.getSelectedRow(), 4));
			
			CategoryBoxEdit.setSelectedItem(e);

			EditExpenseDialog.setVisible(true);

		}

	}

	/**
	 * Handles the Delete Expense button action event.
	 * Displays a confirmation dialog for expense deletion to prevent accidental data loss.
	 * 
	 * <p>This method only executes if an expense row is selected in the table.
	 * The actual deletion occurs only after user confirmation in the dialog.
	 * 
	 * @param e the ActionEvent triggered by clicking the Delete Expense button
	 */
	private void DeleteExpense(ActionEvent e) {
		if(ExpenseTable.getSelectedRow() != -1){
			DeleteExpenseDialog.setVisible(true);
		}
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
	 * Reloads the expense table with current data from the database,
	 * ensuring the display reflects any recent changes made to expenses.
	 * 
	 * @param e the ActionEvent triggered by clicking the Refresh button
	 */
	private void Refresh(ActionEvent e) {
		refreshTable();
		
	}

	/**
	 * Handles the Confirm Add button action event in the Add Expense dialog.
	 * Creates a new Expense object from the dialog input fields and saves it to the database.
	 * 
	 * <p>Process flow:
	 * <ol>
	 *   <li>Parse expense amount from text field</li>
	 *   <li>Create DateTime object from date component fields</li>
	 *   <li>Get selected category from dropdown</li>
	 *   <li>Create new Expense object (ID generated by database, currency set to null for default)</li>
	 *   <li>Save expense to database via DatabaseController</li>
	 *   <li>Close dialog and refresh table to show new expense</li>
	 * </ol>
	 * 
	 * @param e the ActionEvent triggered by clicking the Confirm Add button
	 * @throws NumberFormatException if the expense amount field contains invalid numeric data
	 */
	private void ConfirmAdd(ActionEvent e) {
		
		float f = Float.parseFloat(ExpenseAmountFieldAdd.getText());
		DateTime dt = new DateTime(DateYearFieldAdd.getText(), DateMonthFieldAdd.getText(), DateDayFieldAdd.getText(), null, null);
		String cat = (String) CategoryBoxAdd.getSelectedItem();
		Bank b = uc.getCurrentUser().getBank((String)BankBoxAdd.getSelectedItem());

		Expense ex = new Expense(null, b.getBankName(), b.getBankAccNum(), f, null, null, null, dt, cat);

		dbc.saveExpense(ex, uc.getCurrentUser().getUserID());
		AddExpenseDialog.setVisible(false);
		refreshTable();

	}

	/**
	 * Handles the Cancel Add button action event in the Add Expense dialog.
	 * Closes the dialog without saving any changes or creating a new expense.
	 * 
	 * @param e the ActionEvent triggered by clicking the Cancel Add button
	 */
	private void CancelAdd(ActionEvent e) {
		AddExpenseDialog.setVisible(false);
	}

	/**
	 * Handles the Confirm Edit button action event in the Edit Expense dialog.
	 * Updates the selected expense with new values from the dialog input fields
	 * and persists the changes to the database.
	 * 
	 * <p>Retains the original expense ID while updating all other fields
	 * with the new values provided by the user. The currency field remains null
	 * (using default currency).
	 * 
	 * @param e the ActionEvent triggered by clicking the Confirm Edit button
	 * @throws NumberFormatException if the expense amount field contains invalid numeric data
	 */
	private void ConfirmEdit(ActionEvent e) {
		
		float f = Float.parseFloat(ExpenseAmountFieldEdit.getText());
		DateTime dt = new DateTime(DateYearFieldEdit.getText(), DateMonthFieldEdit.getText(), DateDayFieldEdit.getText(), null, null);
		String cat = (String) CategoryBoxEdit.getSelectedItem();
		Bank b = uc.getCurrentUser().getBank((String)BankBoxEdit.getSelectedItem());

		Expense ex;

		if(selectedTable == 1){
			ex = new Expense((String) ExpenseTable.getModel().getValueAt(ExpenseTable.getSelectedRow(), 0), b.getBankName(), b.getBankAccNum(), f, null, null, null, dt, cat);
		}else{
			if(b != null){
				ex = new Expense((String) DigitalExpenseTable.getModel().getValueAt(DigitalExpenseTable.getSelectedRow(), 0), b.getBankName(), b.getBankAccNum(), f, null, null, null, dt, cat);
			}else{
				ex = new Expense((String) DigitalExpenseTable.getModel().getValueAt(DigitalExpenseTable.getSelectedRow(), 0), f, null, dt, cat);
			}
		}

		dbc.updateExpense(ex);
		EditExpenseDialog.setVisible(false);
		refreshTable();

	}

	/**
	 * Handles the Cancel Edit button action event in the Edit Expense dialog.
	 * Closes the dialog without saving any changes to the selected expense.
	 * 
	 * @param e the ActionEvent triggered by clicking the Cancel Edit button
	 */
	private void CancelEdit(ActionEvent e) {
		EditExpenseDialog.setVisible(false);
	}

	/**
	 * Handles the Confirm Delete button action event in the Delete Expense dialog.
	 * Permanently deletes the selected expense from the database using its expense ID
	 * and refreshes the table to reflect the change.
	 * 
	 * @param e the ActionEvent triggered by clicking the Confirm Delete button
	 */
	private void ConfirmDelete(ActionEvent e) {
		dbc.deleteExpense((String) ExpenseTable.getModel().getValueAt(ExpenseTable.getSelectedRow(), 0));
		DeleteExpenseDialog.setVisible(false);
		refreshTable();
	}

	/**
	 * Handles the Cancel Delete button action event in the Delete Expense dialog.
	 * Closes the confirmation dialog without performing the deletion operation.
	 * 
	 * @param e the ActionEvent triggered by clicking the Cancel Delete button
	 */
	private void CancelDelete(ActionEvent e) {
		DeleteExpenseDialog.setVisible(false);
	}

	private void ExpenseTableMouseClicked(MouseEvent e) {
		selectedTable = 1;
	}

	private void DigitalExpenseTableMouseClicked(MouseEvent e) {
		selectedTable = 2;
	}

	/**
	 * Initializes all GUI components using JFormDesigner-generated code.
	 * This method sets up the main window, dialogs, and all Swing components
	 * with their respective layouts, sizes, positions, and event handlers.
	 * 
	 * <p>The GUI includes:
	 * <ul>
	 *   <li>Main expense table with scroll pane</li>
	 *   <li>Action buttons (Add, Edit, Delete, Refresh, Back)</li>
	 *   <li>Add Expense dialog with input fields</li>
	 *   <li>Edit Expense dialog with pre-populated fields</li>
	 *   <li>Delete Expense confirmation dialog</li>
	 * </ul>
	 * 
	 * <p><strong>Note:</strong> This method is automatically generated by JFormDesigner
	 * and should not be modified manually. Any changes to the GUI layout should
	 * be made through the JFormDesigner visual editor.
	 */

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		ExpenseMenu = new JFrame();
		ExpensePane = new JScrollPane();
		ExpenseTable = new JTable();
		DigitalExpensePane = new JScrollPane();
		DigitalExpenseTable = new JTable();
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
				"[fill]" +
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
				ExpenseTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ExpenseTableMouseClicked(e);
					}
				});
				ExpensePane.setViewportView(ExpenseTable);
			}
			ExpenseMenuContentPane.add(ExpensePane, "cell 0 0 3 2");

			//======== DigitalExpensePane ========
			{

				//---- DigitalExpenseTable ----
				DigitalExpenseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				DigitalExpenseTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				DigitalExpenseTable.setShowHorizontalLines(true);
				DigitalExpenseTable.setShowVerticalLines(true);
				DigitalExpenseTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DigitalExpenseTableMouseClicked(e);
					}
				});
				DigitalExpensePane.setViewportView(DigitalExpenseTable);
			}
			ExpenseMenuContentPane.add(DigitalExpensePane, "cell 3 0 3 2,wmax 300");

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
			ExpenseMenuContentPane.add(DeleteExpense, "cell 2 2 2 2,alignx center,growx 0,hmin 35");

			//---- BackButton ----
			BackButton.setText("Back");
			BackButton.setFont(BackButton.getFont().deriveFont(BackButton.getFont().getSize() + 2f));
			BackButton.addActionListener(e -> Back(e));
			ExpenseMenuContentPane.add(BackButton, "cell 5 2 1 2,alignx center,growx 0,wmax 75,hmin 35");

			//---- RefreshButton ----
			RefreshButton.setText("Refresh");
			RefreshButton.addActionListener(e -> Refresh(e));
			ExpenseMenuContentPane.add(RefreshButton, "cell 4 2 1 2,alignx center,growx 0,wmin 90,hmin 35");
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
	private JFrame ExpenseMenu;
	private JScrollPane ExpensePane;
	private JTable ExpenseTable;
	private JScrollPane DigitalExpensePane;
	private JTable DigitalExpenseTable;
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

	public void showSelected(){
		System.out.println("EXPENSE TABLE:" + ExpenseTable.getSelectedRow());
		System.out.println("DIGITAL EXPENSE TABLE:" + DigitalExpenseTable.getSelectedRow());
	}

}
