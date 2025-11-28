import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 28 11:01:04 PST 2025
 */



/**
 * @author zacha
 */
public class MainMenu  {

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Asdasmaksd
		MainMenu = new JFrame();
		label1 = new JLabel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		scrollPane2 = new JScrollPane();
		table2 = new JTable();
		scrollPane3 = new JScrollPane();
		table3 = new JTable();
		button1 = new JButton();
		button4 = new JButton();
		button2 = new JButton();
		button7 = new JButton();

		//======== MainMenu ========
		{
			MainMenu.setResizable(false);
			MainMenu.setTitle("Expense Tracker - Main Menu");
			Container MainMenuContentPane = MainMenu.getContentPane();
			MainMenuContentPane.setLayout(new MigLayout(
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
				"[grow]" +
				"[grow]" +
				"[]"));

			//---- label1 ----
			label1.setText("Logged in as: ");
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			MainMenuContentPane.add(label1, "cell 0 0");

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(table1);
			}
			MainMenuContentPane.add(scrollPane1, "cell 0 1");

			//======== scrollPane2 ========
			{
				scrollPane2.setViewportView(table2);
			}
			MainMenuContentPane.add(scrollPane2, "cell 1 1");

			//======== scrollPane3 ========
			{
				scrollPane3.setViewportView(table3);
			}
			MainMenuContentPane.add(scrollPane3, "cell 3 1");

			//---- button1 ----
			button1.setText("<html>Manage<br>Users</html>");
			MainMenuContentPane.add(button1, "cell 0 3,align center center,grow 0 0,width :100:100,hmin 50");

			//---- button4 ----
			button4.setText("<html>Manage<br>Expenses</html>");
			MainMenuContentPane.add(button4, "cell 1 3,alignx center,growx 0,width :100:100,hmin 50");

			//---- button2 ----
			button2.setText("<html>Manage<br>Budgets</html>");
			MainMenuContentPane.add(button2, "cell 3 3,alignx center,growx 0,width :100:100,hmin 50");

			//---- button7 ----
			button7.setText("<html>View<br>Insights</html>");
			MainMenuContentPane.add(button7, "cell 1 4 1 2,alignx center,growx 0,width :100:100,hmin 50");
			MainMenu.setSize(900, 600);
			MainMenu.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Asdasmaksd
	private JFrame MainMenu;
	private JLabel label1;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JScrollPane scrollPane2;
	private JTable table2;
	private JScrollPane scrollPane3;
	private JTable table3;
	private JButton button1;
	private JButton button4;
	private JButton button2;
	private JButton button7;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
