package com.cbprog3._JForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Thu Nov 27 19:31:29 SGT 2025
 */

public class TestForm{

	public static void main(String[] args) {
		Form tf = new Form();
		tf.initComponents();
	}

}


/**
 * @author Acee
 */
class Form extends JFrame {

	private void label1KeyPressed(KeyEvent e) {
		// TODO add your code here
	}

	private void button1(ActionEvent e) {
		// TODO add your code here
	}

	public void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Acweafa
		label1 = new JLabel();
		button1 = new JButton();

		//======== this ========
		setTitle(" ");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]"));

		//---- label1 ----
		label1.setText("Hello World!");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				label1KeyPressed(e);
			}
		});
		contentPane.add(label1, "cell 3 1");

		//---- button1 ----
		button1.setText("Fuck Off.");
		button1.addActionListener(e -> button1(e));
		contentPane.add(button1, "cell 3 3");
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Acweafa
	private JLabel label1;
	private JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
