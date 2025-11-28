package com.cbprog3.View;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
/*
 * Created by JFormDesigner on Fri Nov 28 10:05:56 PST 2025
 */



/**
 * @author zacha
 */
public class MainMenu{
	public MainMenu() {
		initComponents();
	}

	public void setVisible(boolean b){
		MainMenu.setVisible(b);
	}

	public boolean isVisible(){
		return MainMenu.isVisible();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Asdasmaksd
		MainMenu = new JFrame();
		label1 = new JLabel();

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
				"[grow]"));

			//---- label1 ----
			label1.setText("WOWIE YOURE AT THE MAIN MENU!!! :D");
			MainMenuContentPane.add(label1, "cell 2 2");
			MainMenu.setSize(400, 400);
			MainMenu.setLocationRelativeTo(null);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Asdasmaksd
	private JFrame MainMenu;
	private JLabel label1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
