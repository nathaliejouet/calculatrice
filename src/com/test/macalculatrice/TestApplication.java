package com.test.macalculatrice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;

public class TestApplication {

	private JFrame frmMaFentre;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestApplication window = new TestApplication();
					window.frmMaFentre.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

	public TestApplication() {
		initialize();
	}

	String s = "";
	String actuel = "";
	String precedent = "";
	boolean operatorClicked = false;

	JButton Button0 = new JButton("0");
	JButton Button1 = new JButton("1");
	JButton Button2 = new JButton("2");
	JButton Button3 = new JButton("3");
	JButton Button4 = new JButton("4");
	JButton Button5 = new JButton("5");
	JButton Button6 = new JButton("6");
	JButton Button7 = new JButton("7");
	JButton Button8 = new JButton("8");
	JButton Button9 = new JButton("9");
	JButton Button10 = new JButton("X");
	JButton Button11 = new JButton("-");
	JButton Button12 = new JButton("/");
	JButton Button13 = new JButton("+");
	JButton Button14 = new JButton("%");
	JButton Button15 = new JButton("+/-");
	JButton Button16 = new JButton(".");
	JButton Button17 = new JButton("C");
	JButton Button18 = new JButton("=");
	JButton[] buttonsNumbers = { Button0, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8,
			Button9 };
	JButton[] buttonsOperators = { Button10, Button11, Button12, Button13 };
	JButton[] buttonsOperators2 = { Button10, Button11, Button12, Button13, Button14, Button16, Button17 };
	private JTextField textField;

	public void nombre(String f) {
		if (actuel.contains(".") == true && f == ".") {
			actuel.replace(f, "");
		} else {
			actuel += f;
		}
	}

	public void calcul() {
		precedent = actuel;
		actuel = "";

	}

	public void resultat() {
		double x = 0;
		switch (s) {
		case ("+"):
			x = Math.round((Double.parseDouble(precedent) + Double.parseDouble(actuel)) * 100);
			x = x / 100;
			actuel = Double.toString(x);
			break;
		case ("-"):
			x = Math.round((Double.parseDouble(precedent) - Double.parseDouble(actuel)) * 100);
			x = x / 100;
			actuel = Double.toString(x);
			break;
		case ("/"):
			x = Math.round((Double.parseDouble(precedent) / Double.parseDouble(actuel)) * 100);
			x = x / 100;
			if (Double.parseDouble(actuel) == 0) {
				x = 0;
			}
			actuel = Double.toString(x);
			break;
		}
		if (Double.parseDouble(actuel) == (int) Double.parseDouble(actuel)) {
			actuel = actuel.replace(".0", "");
		}
	}

	public String pourcentage() {
		actuel = Double.toString(Double.parseDouble(actuel) / 100);
		return actuel;
	}

	public void reverse() {
		if (Double.parseDouble(actuel) > 0) {
			actuel = "-" + actuel;
		} else {
			actuel = actuel.replace("-", "");
		}
	}

	public void doublon() {
		Button10.setEnabled(operatorClicked);
		Button11.setEnabled(operatorClicked);
		Button12.setEnabled(operatorClicked);
		Button13.setEnabled(operatorClicked);
	}

	public void efface() {
		precedent = "";
		actuel = "";
		textField.setText("");
	}

	public void styleBoutton(JButton Button) {
		Button.setForeground(Color.GRAY);
		Button.setBackground(Color.WHITE);
		Button.setFont(new Font("Calibri", Font.BOLD, 30));
	}

	public void styleBoutton2(JButton Button) {
		Button.setForeground(Color.WHITE);
		Button.setBackground(SystemColor.controlHighlight);
		Button.setFont(new Font("Calibri", Font.BOLD, 30));
	}

	private void initialize() {
		frmMaFentre = new JFrame();
		frmMaFentre.setTitle("Calculatrice");
		frmMaFentre.setBounds(100, 100, 349, 564);
		frmMaFentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMaFentre.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmMaFentre.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		for (int i = 0; i < buttonsNumbers.length; i++) {
			final int index = i;
			buttonsNumbers[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					nombre(Integer.toString(index));
					Button18.setEnabled(true);
					textField.setText(actuel);
				}
			});
		}

		for (int j = 0; j < buttonsOperators.length; j++) {
			final int index = j;
			buttonsOperators[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					operatorClicked = false;
					doublon();
					Button18.setEnabled(false);
					s = buttonsOperators[index].getText();
					textField.setText(s);
					calcul();
				}
			});
		}

		Button14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operatorClicked = false;
				doublon();
				Button18.setEnabled(true);
				s = "%";
				textField.setText("%");
				pourcentage();
			}
		});

		Button15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reverse();
				Button18.setEnabled(false);
				textField.setText(actuel);
			}
		});

		Button16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nombre(".");
				Button18.setEnabled(false);
				textField.setText(actuel);
			}
		});

		Button17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Button18.setEnabled(false);
				efface();
			}
		});

		Button18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operatorClicked = true;
				doublon();
				resultat();
				textField.setText(actuel);
				s = "";
			}
		});

		for (int k = 0; k < buttonsNumbers.length; k++) {
			styleBoutton(buttonsNumbers[k]);
			panel.add(buttonsNumbers[k]);
		}

		for (int l = 0; l < buttonsOperators2.length; l++) {
			styleBoutton2(buttonsOperators2[l]);
			panel.add(buttonsOperators2[l]);
		}

		Button0.setBounds(97, 442, 70, 60);
		Button1.setBounds(20, 237, 70, 60);
		Button2.setBounds(97, 237, 70, 60);
		Button3.setBounds(174, 237, 70, 60);
		Button4.setBounds(20, 306, 70, 60);
		Button5.setBounds(97, 306, 70, 60);
		Button6.setBounds(174, 306, 70, 60);
		Button7.setBounds(20, 375, 70, 60);
		Button8.setBounds(97, 375, 70, 60);
		Button9.setBounds(174, 375, 70, 60);
		Button10.setBounds(20, 167, 70, 60);
		Button11.setBounds(97, 167, 70, 60);
		Button12.setBounds(174, 167, 70, 60);
		Button13.setBounds(251, 167, 70, 60);
		Button14.setBounds(252, 237, 70, 60);
		Button15.setBounds(252, 306, 70, 60);
		Button15.setForeground(Color.WHITE);
		Button15.setBackground(SystemColor.controlHighlight);
		Button15.setFont(new Font("Calibri", Font.BOLD, 28));
		panel.add(Button15);
		Button16.setBounds(20, 442, 70, 60);
		Button17.setBounds(252, 375, 70, 60);
		Button18.setBounds(174, 442, 147, 60);
		Button18.setFont(new Font("Calibri", Font.BOLD, 24));
		Button18.setBackground(new Color(255, 140, 0));
		Button18.setForeground(Color.WHITE);
		panel.add(Button18);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setForeground(new Color(128, 128, 128));
		textField.setBackground(SystemColor.control);
		textField.setFont(new Font("Calibri", Font.BOLD, 40));
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
		textField.setBounds(0, 25, 343, 111);
		panel.add(textField);
		textField.setColumns(10);
	}
}
