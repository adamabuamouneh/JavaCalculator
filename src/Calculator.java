import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {	
	Color darkLiver = new Color (80, 80, 80);
	Color lightGray = new Color (212, 212, 210);
	Color eerieBlack = new Color (28, 28, 28);
	Color blueberry = new Color (66, 133, 244);
	
	String[] buttonValues = {
		"AC", "+/-", "%", "÷", 
        "7", "8", "9", "×",  
        "4", "5", "6", "-",
		"1", "2", "3", "+",
		"0", ".", "√", "="
	};
	String[] operators = {"÷", "×", "-", "+"};
	
	String a = "0";
	String operator = null;
	String b = null;
	
	JFrame frame = new JFrame("Calculator");
	JLabel displayLabel = new JLabel();
	JPanel displayPanel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	
	Calculator() {
		frame.setVisible(true);
		frame.setSize(360, 540);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		displayLabel.setBackground(eerieBlack);
		displayLabel.setForeground(Color.white);
		displayLabel.setFont(new Font("Dialog", Font.PLAIN, 80));
		displayLabel.setHorizontalAlignment(JLabel.RIGHT);
		displayLabel.setText("0");
		displayLabel.setOpaque(true);
		
		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(displayLabel);
		frame.add(displayPanel, BorderLayout.NORTH);
		
		buttonsPanel.setLayout(new GridLayout(5, 4));
		buttonsPanel.setBackground(eerieBlack);
		frame.add(buttonsPanel);
		
		for (int i = 0; i < buttonValues.length; i++) {
			JButton button = new JButton();
			String buttonValue = buttonValues[i];
			button.setFont(new Font("Dialog", Font.PLAIN, 30));
			button.setText(buttonValue);
			button.setFocusable(false);
			button.setBorder(new LineBorder(eerieBlack));
			if (Arrays.asList(operators).contains(buttonValue)) {
				button.setBackground(darkLiver);
				button.setForeground(Color.white);
			} else if (buttonValue.equals("=")) {
				button.setBackground(blueberry);
				button.setForeground(Color.white);
			} else {
				button.setBackground(lightGray);
				button.setForeground(eerieBlack);
			}
			
			buttonsPanel.add(button);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton) e.getSource();
					String buttonValue = button.getText();
					if (buttonValue.equals("AC")) {
						clearAll();
						displayLabel.setText("0");
					} else if (buttonValue.equals("+/-")) {
						double numDisplay = Double.parseDouble(displayLabel.getText()) * -1;
						displayLabel.setText(removeZeroDecimal(numDisplay));
					} else if (buttonValue.equals("%")) {
						double numDisplay = Double.parseDouble(displayLabel.getText()) / 100;
						displayLabel.setText(Double.toString(numDisplay));
					} else if (buttonValue.equals(".")) {
						if (!displayLabel.getText().contains(".")) displayLabel.setText(displayLabel.getText() + ".");
					} else if (buttonValue.equals("√")) {
						double numDisplay = Double.parseDouble(displayLabel.getText());
						displayLabel.setText(removeZeroDecimal(Math.sqrt(numDisplay)));
					} else if ("0123456789".contains(buttonValue)) {
						if (displayLabel.getText().equals("0")) {
							displayLabel.setText(buttonValue);
						} else {
							displayLabel.setText(displayLabel.getText() + buttonValue);
						}
					} else if ("÷×+-".contains(buttonValue)) {
						if (operator == null) {
							a = displayLabel.getText();
							displayLabel.setText("0");
							b = "0";
						} 
						operator = buttonValue;
					} else if (buttonValue.equals("=")) {
						if (a != null && operator != null) {
							b = displayLabel.getText();
							double numA = Double.parseDouble(a);
							double numB = Double.parseDouble(b);
							
							switch (operator) {
								case "÷":
									displayLabel.setText(removeZeroDecimal(numA / numB));
									break;
								case "×":
									displayLabel.setText(removeZeroDecimal(numA * numB));	
									break;
								case "+":
									displayLabel.setText(removeZeroDecimal(numA + numB));
									break;
								case "-":
									displayLabel.setText(removeZeroDecimal(numA - numB));
									break;
								default:
									if (operator == null) displayLabel.setText("0");
							}
							
							a = displayLabel.getText();
					        operator = null;
					        b = null;
						}
					}
				}
			});
		}
	}
	
	void clearAll() {
		a = "0";
		operator = null;
		b = null;
	}
	
	String removeZeroDecimal(double numDisplay) {
		if (numDisplay % 1 == 0) {
			return Integer.toString((int) numDisplay);
		} else {
			return Double.toString(numDisplay);
		}
	}
}

