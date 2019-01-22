
/*
This program simulated the windows 10 calculator implementing a UI 
We wrote the Gui by hand and did NOT use WindowBuilder, we implemented 
it through multiple layered panels.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calc {
	// prototyping JFrame Section
	private JFrame frame;
	// prototyping JPanel Section
	private JPanel KeyboardUpper, Keyboard, Conversion, DisplayArea, lowerInner, upperInnerTwo;

	// prototyping JButton Section
	private JButton sub, add, ParLeft, ParRight, addOrmin, period, equal, mult;

	private JButton num7, num8, num9, num4, num5, num6, num1, num2, num3, num0;

	private JButton letA, letB, letC, letD, letE, letF;

	private JButton arrow, JBmod, ce, c, backspace, div;

	private JButton lsh, rsh, or, xor, not, and;

	private JButton dots, dots2, word, MS, MLetter;

	private JButton hex, dec, oct, bin;
	// Prototyping JLabel section
	private JLabel NumLabel, equationLabel;
	private JLabel ProgrammerIcon;

	private int flag = 64;
	private int disPlayMod = 10;
	protected Long MAX_WORD = (long) (Math.pow(2, flag - 1));
	protected Long MIN_WORD = (long) (Math.pow(2, flag - 1)) * (-1);
	private boolean clean = false;
	private Long previousValue = 0L;
	private char operator = 'z';// z is the default
	boolean operatorStatus = false;
	private int leftPare = 0;
	private String equationLabelText = " ";
	private boolean rightPare = false;
	private boolean Bshift = false;

	private Long currentValue = 0L;

	public int xSize = 550;
	public int ySize = 1000;

	Font font1 = new Font("SansSerif", Font.BOLD, 18); // fonts for numbers in keyboard buttons
	Font font2 = new Font("SansSerif", Font.BOLD, 50); // font for where the numbers are
														// actually displayed in a big
	// size
	Font font3 = new Font("SansSerif", Font.PLAIN, 20); // font for the string on top of the display Area
	Font Foperator = new Font("Microsoft JhengHei UI Light", Font.PLAIN, 23);
	Font Fletter = new Font("SansSerif", Font.BOLD, 15);
	Font FLoperator = new Font("SansSerif", Font.PLAIN, 13);

	public static void main(String[] args) {
		new calc();
	}

	public calc() {

		// ************************** Components************
		NumLabel = new JLabel(); // Where the numbers are Displayed
		equationLabel = new JLabel(); // Text label holding all previous entries
		// where NumLabel and equationLabe is place
		// DisplayArea = new JPanel(new GridLayout(3, 1, 0, 0));
		DisplayArea = new JPanel(null);
		Conversion = new JPanel(new GridLayout(4, 1, 0, 0)); // conversion labels grid
		lowerInner = new JPanel(new GridLayout(6, 6, 5, 5)); // biggest grid holds the numbers letters and add,sub etc..
		upperInnerTwo = new JPanel(new GridLayout(1, 5, 0, 0)); // holds the uppermost row of buttons (word row)
		KeyboardUpper = new JPanel(new BorderLayout()); // puts upperInner and upperInnerTwo together (north south)
		Keyboard = new JPanel(new BorderLayout()); // Keyboard puts lower Inner & KeyboardUpper (north and south)
		ProgrammerIcon = new JLabel(new ImageIcon(calc.class.getResource("/images/Programmer.png"))); // together

		DisplayArea.setPreferredSize(new Dimension(xSize, 150));
		upperInnerTwo.setPreferredSize(new Dimension(xSize, 50));
		Conversion.setPreferredSize(new Dimension(xSize, 100));
		lowerInner.setPreferredSize(new Dimension(xSize, 350)); // 500 on windows 300 in mac

		lowerInner.setBackground(new Color(238, 238, 238));
		upperInnerTwo.setBackground(new Color(238, 238, 238));
		Conversion.setBackground(new Color(238, 238, 238));
		KeyboardUpper.setBackground(new Color(238, 238, 238));
		Keyboard.setBackground(new Color(238, 238, 238));
		DisplayArea.setBackground(new Color(238, 238, 238));

		NumLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // Type from right to Left
		ProgrammerIcon.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		equationLabel.setText(" ");
		ProgrammerIcon.setLocation(new Point(3,2));
		ProgrammerIcon.setSize(new Dimension(xSize, 31));
		equationLabel.setLocation(new Point(0, 23));
		equationLabel.setSize(new Dimension(xSize, 20)); // dimension
		NumLabel.setLocation(new Point(0, 40));
		NumLabel.setSize(new Dimension(xSize, 60)); // dimension

		// ************************** Components************
		NumLabel.setFont(font2); // Where the actual number is displayed
		NumLabel.setText("0"); // starts at 0

		// Definition of buttons and there characteristics such as color content fonts
		// action perform

		hex = new JButton("HEX");
		hex.setHorizontalAlignment(SwingConstants.LEFT);
		hex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disPlayMod = 16;
				hex.setBackground(Color.lightGray);
				oct.setBackground(new Color(238, 238, 238));
				dec.setBackground(new Color(238, 238, 238));
				bin.setBackground(new Color(238, 238, 238));
				Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag); // update
																											// Equation
																											// label
				Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod); // update
																													// values
				disableButton();
				clean = true;
			}
		});
		dec = new JButton("DEC");
		dec.setBackground(Color.lightGray);

		dec.setHorizontalAlignment(SwingConstants.LEFT);
		dec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disPlayMod = 10;
				dec.setBackground(Color.lightGray);
				hex.setBackground(new Color(238, 238, 238));
				oct.setBackground(new Color(238, 238, 238));
				bin.setBackground(new Color(238, 238, 238));
				Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);// update
																											// Equation
																											// label
				Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod); // update
																													// values
				disableButton();
				clean = true;
			}
		});
		oct = new JButton("OCT");
		oct.setHorizontalAlignment(SwingConstants.LEFT);
		oct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disPlayMod = 8;
				oct.setBackground(Color.lightGray);
				hex.setBackground(new Color(238, 238, 238));
				dec.setBackground(new Color(238, 238, 238));
				bin.setBackground(new Color(238, 238, 238));
				Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag); // update
																											// Equation
																											// label
				Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod); // update
																													// values
				disableButton();
				clean = true;
			}
		});
		bin = new JButton("BIN");
		bin.setHorizontalAlignment(SwingConstants.LEFT);
		bin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disPlayMod = 2;
				bin.setBackground(Color.lightGray);
				hex.setBackground(new Color(238, 238, 238));
				oct.setBackground(new Color(238, 238, 238));
				dec.setBackground(new Color(238, 238, 238));
				Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag); // update
																											// Equation
																											// label
				Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod); // update
																													// values
				disableButton();
				clean = true;
			}
		});

		// panel addition of labels
		Conversion.add(hex);
		Conversion.add(dec);
		Conversion.add(oct);
		Conversion.add(bin);

		{

			arrow = new JButton("↑");
			JBmod = new JButton("Mod"); // the mod gets the content of the remainder
			JBmod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					previousValue = currentValue;
					operator = '%';
					if (operatorStatus == false) {
						if (doubleStacks.isGood() == false)
							doubleStacks.push(previousValue);
						if (clean == false) {
							if (rightPare == false)
								equationLabelText = equationLabelText + currentValue + operator;
							else {
								equationLabelText = equationLabelText + operator;
								rightPare = false;
							}
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
						} else {
							equationLabelText = equationLabelText + operator;
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
						}
					} else {
						String ES = equationLabelText;
						String temp = ES.substring(0, ES.length() - 1);
						temp = temp + operator;
						equationLabelText = temp;
						Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag); // update
																													// equation
																													// label
					}
					rightPare = false;
					operatorStatus = true;
					clean = true;
				}
			});

			ce = new JButton("CE");

			ce.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cleanCurrent(NumLabel, hex, dec, oct, bin);
				}
			});
			c = new JButton("C");
			c.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NumLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
					cleanEvey(NumLabel, equationLabel, hex, dec, oct, bin);
				}
			});

			backspace = new JButton("⌫");
			backspace.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (clean == true);
					else
						currentValue /= disPlayMod;
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});
			letA = new JButton("A");
			letB = new JButton("B");
			letC = new JButton("C");
			letD = new JButton("D");
			letE = new JButton("E");
			letF = new JButton("F");
			// -------------------
			/*
			 * letters will only become available once hex mode is activated they are
			 * adjusted to represent each number in hex form
			 */
			letA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 10; // the value the letter represents
					if (clean == true) // condition
						cleanCurrent(NumLabel, hex, dec, oct, bin); // update Current
					NumButtons(theNum); // send the number value to the function
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod); // update
																														// the
																														// values
				}
			});
			letB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 11;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});
			letC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 12;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});
			letD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 13;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});
			letE.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 14;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});
			letF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 15;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});

			// ------------------- LEtter section

			div = new JButton("÷");
			div.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					previousValue = currentValue; // get the current input into editable
					operator = '/'; // switch to / instead of div sign
					if (operatorStatus == false) {
						if (doubleStacks.isGood() == false)// check on double stack
							doubleStacks.push(previousValue); // arrange stack
						if (clean == true) // check on clean
						{
							if (rightPare == false) // depends if the number string is finished
								equationLabelText = equationLabelText + currentValue + operator; // update the
																									// string
							else {
								equationLabelText = equationLabelText + operator;
								rightPare = false;
							}
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag); // update
																														// the
																														// equation
							// label string wise
						} else {
							equationLabelText = equationLabelText + previousValue + operator;
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);// mathematically
																														// update
							// the equation
						}
					} else {
						String ES = equationLabelText; // get the editable form of the past input
						String temp = ES.substring(0, ES.length() - 1);
						temp = temp + operator; // add the operator to the editable form
						equationLabelText = temp; // update the equation label with thte new operator
						Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag); // update
																													// string
																													// of
						// equatioon label visually
					}
					rightPare = false;
					operatorStatus = true;
					clean = true;
				}
			});
			mult = new JButton("x");
			mult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					previousValue = currentValue;
					operator = '*';

					if (operatorStatus == false) {
						if (doubleStacks.isGood() == false)
							doubleStacks.push(previousValue);
						if (clean == false) {
							if (rightPare == false)
								equationLabelText = equationLabelText + currentValue + operator;
							else {
								equationLabelText = equationLabelText + operator;
								rightPare = false;
							}
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
						} else {
							equationLabelText = equationLabelText + operator;
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
						}
					} else {
						String ES = equationLabelText;
						String temp = ES.substring(0, ES.length() - 1);
						temp = temp + operator;
						equationLabelText = temp;
						Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
					}
					rightPare = false;
					clean = true;
					operatorStatus = true;
				}
			});
			sub = new JButton("-");
			sub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					previousValue = currentValue;
					operator = '-';
					if (operatorStatus == false) {
						if (doubleStacks.isGood() == false)
							doubleStacks.push(previousValue);
						if (clean == true) {
							if (rightPare == false)
								equationLabelText = equationLabelText + currentValue + operator;
							else {
								equationLabelText = equationLabelText + operator;
								rightPare = false;
							}
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
						} else {
							equationLabelText = equationLabelText + previousValue + operator;
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
						}
					} else {
						String ES = equationLabelText;
						String temp = ES.substring(0, ES.length() - 1);
						temp = temp + operator;
						equationLabelText = temp;
						Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
					}
					rightPare = false;
					operatorStatus = true;
					clean = true;
				}
			});
			add = new JButton("+");
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					previousValue = currentValue;
					operator = '+';
					if (operatorStatus == false) {
						if (doubleStacks.isGood() == false)
							doubleStacks.push(previousValue);
						if (clean == true) {
							if (rightPare == false)
								equationLabelText = equationLabelText + currentValue + operator;
							else {
								equationLabelText = equationLabelText + operator;
								rightPare = false;
							}
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
						} else {
							equationLabelText = (equationLabelText + previousValue + operator);
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
						}
					} else {
						String ES = equationLabelText;
						String temp = ES.substring(0, ES.length() - 1);
						temp = temp + operator;
						equationLabelText = temp;
						Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
					}
					rightPare = false;
					operatorStatus = true;
					clean = true;
				}
			});

			/*
			 * Parentheis section
			 */
			ParLeft = new JButton("(");
			ParLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					leftPare++; // keeps count of parenthesis
					rightPare = false;
					previousValue = currentValue; // get editable form
					if (clean == true) {
						currentValue = 0L;
						Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag,
								disPlayMod);
					}
					if (operatorStatus == true) {

						doubleStacks.push(operator);// send it to stack of operator
						equationLabelText = equationLabelText + "("; // add it to the string form
						Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag); // send
																													// it
																													// to
																													// be
																													// calculated
						operatorStatus = false;
					} else {
						equationLabelText = equationLabelText + "("; // add it to the string form
						Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag); // send
																													// the
																													// string
																													// form
																													// to
						// the eqution label
					}
					doubleStacks.push('('); // push it in the stack
					currentValue = 0L;
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod); // implement
																														// the
																														// paranthesis
					hex.setText(String.valueOf(previousValue));
					clean = true;
				}
			});
			ParRight = new JButton(")");
			ParRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (leftPare <= 0)
						;
					else {
						leftPare--;
						rightPare = true;
						previousValue = currentValue;
						if (operatorStatus == false) {
							if (clean == false && operator == 'z') {
								equationLabelText = equationLabelText + previousValue + ")";
								Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod,
										flag);
								doubleStacks.push(currentValue);
								doubleStacks.push(')');

								clean = true;
								operator = 'x';
							} else {

								equationLabelText = equationLabelText + ")";
								Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod,
										flag);
								doubleStacks.push(')');

							}
						} else {

							equationLabelText = equationLabelText + currentValue + ")";
							Function.setEquationLabel(equationLabel, disPlayMod, equationLabelText, disPlayMod, flag);
							doubleStacks.push(operator);
							doubleStacks.push(currentValue);
							doubleStacks.push(')');

						}
						operatorStatus = false;

						NumLabel.setText("" + currentValue);
						Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag,
								disPlayMod);
						clean = true;
					}
				}
			});
			addOrmin = new JButton(" ±");
			addOrmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (currentValue > MIN_WORD) {
						currentValue = currentValue * (-1);
						NumLabel.setText(String.valueOf(currentValue));
						Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag,
								disPlayMod);

					}
					if (currentValue == MIN_WORD)
						;
				}
			});
			period = new JButton(".");
			period.setEnabled(false);
			equal = new JButton("=");
			equal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						equationLabelText = " ";
						if (operatorStatus == true) {
							doubleStacks.push(operator);
							doubleStacks.push(currentValue);
						} else {
							if (rightPare == false)
								doubleStacks.push(currentValue);
						}

						for (; leftPare > 0; leftPare--) // loop to check back on parenthesis
						{
							doubleStacks.push(')');
						}
						if (doubleStacks.numIsEmpty() == true)
							;
						else
							currentValue = doubleStacks.getResult();
						currentValue=Function.valueCheck(currentValue, flag);
						previousValue = currentValue;
						NumLabel.setText(String.valueOf(currentValue));// display in NumLabel in string form
						Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag,
								disPlayMod); // implement new values
						equationLabelText = " ";
						operator = 'z';
						doubleStacks.cleanUp();
						clean = true;
						equationLabel.setText("");
						doubleStacks.cleanUp();
					} catch (Exception E) {
						System.out.println("equals:"+E);
					}
				}
			});
			// added the images of the actual keypad and binary keypad
			dots = new JButton(new ImageIcon(calc.class.getResource("/images/dot1.png")));
			dots2 = new JButton(new ImageIcon(calc.class.getResource("/images/dot2.png")));
			word = new JButton("QWORD");
			// This implementation makes use of the flag
			// and acts in the form of a loop to implement Qwod, word, dword, and byte
			// by restarting the flag val each time
			word.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (flag == 8)
						flag = 64;
					else
						flag /= 2;
					long temp = currentValue;
					switch (flag) {
					case 64:
						word.setText("QWORD");
						MAX_WORD = Long.MAX_VALUE;
						MIN_WORD = Long.MIN_VALUE;
						Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag,
								disPlayMod);
						break;
					case 32:
						word.setText("DWORD");
						MAX_WORD = (long) Integer.MAX_VALUE;
						MIN_WORD = (long) Integer.MIN_VALUE;
						int dis32 = (int) temp;
						currentValue = (long) dis32;
						Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag,
								disPlayMod);
						break;
					case 16:
						word.setText("WORD");
						MAX_WORD = (long) Short.MAX_VALUE;
						MIN_WORD = (long) Short.MIN_VALUE;
						short dis16 = (short) temp;
						currentValue = (long) dis16;
						Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag,
								disPlayMod);
						break;
					case 8:
						MAX_WORD = (long) Byte.MAX_VALUE;
						MIN_WORD = (long) Byte.MIN_VALUE;
						word.setText("BYTE");
						byte dis8 = (byte) temp;
						currentValue = (long) dis8;
						Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag,
								disPlayMod);
						break;
					}
				}
			});
			MS = new JButton("MS");
			MLetter = new JButton(new ImageIcon(calc.class.getResource("/images/Marrow.png")));
			MLetter.setEnabled(false);
			/*
			 * The following buttons are for display purposes only
			 */
			lsh = new JButton("Lsh");
			rsh = new JButton("Rsh");
			or = new JButton("Or");
			xor = new JButton("Xor");
			not = new JButton("Not");
			and = new JButton("And");

			/*
			 * The number Section implementation
			 */
			num0 = new JButton("0");

			num0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 0; // if the button is pressed it obtains the value it represents
					if (clean == true) {
						if (operator == 'x') {
							equationLabelText.replaceFirst(String.valueOf(previousValue), "");
							equationLabelText.replaceFirst("(", "");
						}
						cleanCurrent(NumLabel, hex, dec, oct, bin); // update the current label
					}
					NumButtons(theNum); // dynamically sends the number value
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});

			num1 = new JButton("1");
			num1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 1;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});
			num2 = new JButton("2");
			num2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 2;
					if (clean == true) {
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					}
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});
			num3 = new JButton("3");
			num3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 3;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});

			num4 = new JButton("4");
			num4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 4;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});

			num5 = new JButton("5");
			num5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 5;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});

			num6 = new JButton("6");
			num6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 6;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});

			num7 = new JButton("7");
			num7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 7;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});

			num8 = new JButton("8");
			num8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 8;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);

				}
			});
			num9 = new JButton("9");
			num9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int theNum = 9;
					if (clean == true)
						cleanCurrent(NumLabel, hex, dec, oct, bin);
					NumButtons(theNum);
					Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);
				}
			});

		}

		/**
		 * Display characteristics for the numbers include background , Borderpainted to
		 * disapears to give it a better feel mouse listener capability
		 */
		num0.setBackground(new Color(251, 251, 251));
		num1.setBackground(new Color(251, 251, 251));
		num2.setBackground(new Color(251, 251, 251));
		num3.setBackground(new Color(251, 251, 251));
		num4.setBackground(new Color(251, 251, 251));
		num5.setBackground(new Color(251, 251, 251));
		num6.setBackground(new Color(251, 251, 251));
		num7.setBackground(new Color(251, 251, 251));
		num8.setBackground(new Color(251, 251, 251));
		num9.setBackground(new Color(251, 251, 251));
		num0.setBorderPainted(false);
		num1.setBorderPainted(false);
		num2.setBorderPainted(false);
		num3.setBorderPainted(false);
		num4.setBorderPainted(false);
		num5.setBorderPainted(false);
		num6.setBorderPainted(false);
		num7.setBorderPainted(false);
		num8.setBorderPainted(false);
		num9.setBorderPainted(false);
		num0.addMouseListener(new MyMouseListener());
		num1.addMouseListener(new MyMouseListener());
		num2.addMouseListener(new MyMouseListener());
		num3.addMouseListener(new MyMouseListener());
		num4.addMouseListener(new MyMouseListener());
		num5.addMouseListener(new MyMouseListener());
		num6.addMouseListener(new MyMouseListener());
		num7.addMouseListener(new MyMouseListener());
		num8.addMouseListener(new MyMouseListener());
		num9.addMouseListener(new MyMouseListener());
		// ==========================================
		sub.setBackground(new Color(244, 244, 244));
		add.setBackground(new Color(244, 244, 244));
		ParLeft.setBackground(new Color(244, 244, 244));
		ParRight.setBackground(new Color(244, 244, 244));
		addOrmin.setBackground(new Color(244, 244, 244));
		period.setBackground(new Color(244, 244, 244));
		equal.setBackground(new Color(244, 244, 244));
		mult.setBackground(new Color(244, 244, 244));
		sub.setBorderPainted(false);
		add.setBorderPainted(false);
		ParLeft.setBorderPainted(false);
		ParRight.setBorderPainted(false);
		addOrmin.setBorderPainted(false);
		period.setBorderPainted(false);
		equal.setBorderPainted(false);
		mult.setBorderPainted(false);
		sub.addMouseListener(new MyMouseListener());
		add.addMouseListener(new MyMouseListener());
		ParLeft.addMouseListener(new MyMouseListener());
		ParRight.addMouseListener(new MyMouseListener());
		addOrmin.addMouseListener(new MyMouseListener());
		period.addMouseListener(new MyMouseListener());
		equal.addMouseListener(new MyMouseListener());
		mult.addMouseListener(new MyMouseListener());
		sub.setFont(Foperator);
		add.setFont(Foperator);
		ParLeft.setFont(Foperator);
		ParRight.setFont(Foperator);
		addOrmin.setFont(Foperator);
		period.setFont(Foperator);
		equal.setFont(Foperator);
		mult.setFont(Foperator);
		// ==========================================
		letA.setBackground(new Color(251, 251, 251));
		letB.setBackground(new Color(251, 251, 251));
		letC.setBackground(new Color(251, 251, 251));
		letD.setBackground(new Color(251, 251, 251));
		letE.setBackground(new Color(251, 251, 251));
		letF.setBackground(new Color(251, 251, 251));
		letA.setBorderPainted(false);
		letB.setBorderPainted(false);
		letC.setBorderPainted(false);
		letD.setBorderPainted(false);
		letE.setBorderPainted(false);
		letF.setBorderPainted(false);
		letA.addMouseListener(new MyMouseListener());
		letB.addMouseListener(new MyMouseListener());
		letC.addMouseListener(new MyMouseListener());
		letD.addMouseListener(new MyMouseListener());
		letE.addMouseListener(new MyMouseListener());
		letF.addMouseListener(new MyMouseListener());
		disableButton();
		letA.setFont(Fletter);
		letB.setFont(Fletter);
		letC.setFont(Fletter);
		letD.setFont(Fletter);
		letE.setFont(Fletter);
		letF.setFont(Fletter);
		// =======================================
		arrow.setBackground(new Color(244, 244, 244));
		JBmod.setBackground(new Color(244, 244, 244));
		ce.setBackground(new Color(244, 244, 244));
		c.setBackground(new Color(244, 244, 244));
		backspace.setBackground(new Color(244, 244, 244));
		div.setBackground(new Color(244, 244, 244));
		arrow.setBorderPainted(false);
		JBmod.setBorderPainted(false);
		ce.setBorderPainted(false);
		c.setBorderPainted(false);
		backspace.setBorderPainted(false);
		div.setBorderPainted(false);
		arrow.addMouseListener(new MyMouseListener());
		JBmod.addMouseListener(new MyMouseListener());
		ce.addMouseListener(new MyMouseListener());
		c.addMouseListener(new MyMouseListener());
		backspace.addMouseListener(new MyMouseListener());
		div.addMouseListener(new MyMouseListener());
		arrow.setFont(new Font("SansSerif", Font.PLAIN, 18));
		JBmod.setFont(FLoperator);
		ce.setFont(new Font("SansSerif", Font.BOLD, 14));
		c.setFont(new Font("SansSerif", Font.BOLD, 14));
		backspace.setFont(FLoperator);
		div.setFont(Foperator);
		// ==========================================
		lsh.setBackground(new Color(244, 244, 244));
		rsh.setBackground(new Color(244, 244, 244));
		or.setBackground(new Color(244, 244, 244));
		xor.setBackground(new Color(244, 244, 244));
		not.setBackground(new Color(244, 244, 244));
		and.setBackground(new Color(244, 244, 244));
		lsh.setBorderPainted(false);
		rsh.setBorderPainted(false);
		or.setBorderPainted(false);
		xor.setBorderPainted(false);
		not.setBorderPainted(false);
		and.setBorderPainted(false);
		lsh.addMouseListener(new MyMouseListener());
		rsh.addMouseListener(new MyMouseListener());
		or.addMouseListener(new MyMouseListener());
		xor.addMouseListener(new MyMouseListener());
		not.addMouseListener(new MyMouseListener());
		and.addMouseListener(new MyMouseListener());
		lsh.setFont(FLoperator);
		rsh.setFont(FLoperator);
		or.setFont(FLoperator);
		xor.setFont(FLoperator);
		not.setFont(FLoperator);
		and.setFont(FLoperator);
		// ========================================
		dots.setBackground(new Color(238, 238, 238));
		dots2.setBackground(new Color(238, 238, 238));
		word.setBackground(new Color(238, 238, 238));
		MS.setBackground(new Color(238, 238, 238));
		MLetter.setBackground(new Color(238, 238, 238));
		dots.setBorderPainted(false);
		dots2.setBorderPainted(false);
		word.setBorderPainted(false);
		MS.setBorderPainted(false);
		MLetter.setBorderPainted(false);
		dots.addMouseListener(new MyMouseListener());
		dots2.addMouseListener(new MyMouseListener());
		word.addMouseListener(new MyMouseListener());
		MS.addMouseListener(new MyMouseListener());
		MLetter.addMouseListener(new MyMouseListener());
		// =======================================
		hex.setBackground(new Color(238, 238, 238));
		oct.setBackground(new Color(238, 238, 238));
		dec.setBackground(Color.LIGHT_GRAY);
		bin.setBackground(new Color(238, 238, 238));
		hex.setBorderPainted(false);
		oct.setBorderPainted(false);
		bin.setBorderPainted(false);
		dec.setBorderPainted(false);

		Function.ChangeValues(NumLabel, equationLabel, hex, dec, oct, bin, currentValue, flag, disPlayMod);

		num7.setFont(font1);
		num8.setFont(font1);
		num9.setFont(font1);
		num4.setFont(font1);
		num5.setFont(font1);
		num6.setFont(font1);
		num1.setFont(font1);
		num2.setFont(font1);
		num3.setFont(font1);
		num0.setFont(font1);
		/*
		 * Try an catch to make sure only numbers are being accepted
		 */

		try {
			num7.addKeyListener(new MyKeyListener());
			num8.addKeyListener(new MyKeyListener());
			num9.addKeyListener(new MyKeyListener());
			num4.addKeyListener(new MyKeyListener());
			num5.addKeyListener(new MyKeyListener());
			num6.addKeyListener(new MyKeyListener());
			num1.addKeyListener(new MyKeyListener());
			num2.addKeyListener(new MyKeyListener());
			num3.addKeyListener(new MyKeyListener());
			num0.addKeyListener(new MyKeyListener());
		} catch (Exception e) {
			System.out.println(e);
		}
		lowerInner.add(lsh);
		lowerInner.add(rsh);
		lowerInner.add(or);
		lowerInner.add(xor);
		lowerInner.add(not);
		lowerInner.add(and);
		lowerInner.add(arrow);
		lowerInner.add(JBmod);
		lowerInner.add(ce);
		lowerInner.add(c);
		lowerInner.add(backspace);
		lowerInner.add(div);
		lowerInner.add(letA);
		lowerInner.add(letB);
		lowerInner.add(num7);
		lowerInner.add(num8);
		lowerInner.add(num9);
		lowerInner.add(mult);
		lowerInner.add(letC);
		lowerInner.add(letD);
		lowerInner.add(num4);
		lowerInner.add(num5);
		lowerInner.add(num6);
		lowerInner.add(sub);
		lowerInner.add(letE);
		lowerInner.add(letF);
		lowerInner.add(num1);
		lowerInner.add(num2);
		lowerInner.add(num3);
		lowerInner.add(add);
		lowerInner.add(ParLeft);
		lowerInner.add(ParRight);
		lowerInner.add(addOrmin);
		lowerInner.add(num0);
		lowerInner.add(period);
		lowerInner.add(equal);

		upperInnerTwo.add(dots);
		upperInnerTwo.add(dots2);
		upperInnerTwo.add(word);
		upperInnerTwo.add(MS);
		upperInnerTwo.add(MLetter);

		/*
		 * This section builds the panels by layers
		 */
		KeyboardUpper.add(upperInnerTwo, BorderLayout.NORTH);
		Keyboard.add(KeyboardUpper, BorderLayout.NORTH);
		Keyboard.add(lowerInner, BorderLayout.SOUTH);

		equationLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // displaying purpose
		equationLabel.setFont(font3);
		DisplayArea.add(ProgrammerIcon);
		DisplayArea.add(equationLabel);
		DisplayArea.add(NumLabel);

		// ----------------------------Key listener
		// The set focusable to false will allow that once a button is pressed the
		// computer is still
		// waiting for the next button to be pressed, preventing it to "freeze in one
		// button"
		Keyboard.setFocusable(true);
		Conversion.setFocusable(false);
		Keyboard.addKeyListener(new MyKeyListener());
		Keyboard.setFocusable(true);
		dots.setFocusable(false);
		dots2.setFocusable(false);
		word.setFocusable(false);
		MS.setFocusable(false);
		MLetter.setFocusable(false);
		hex.setFocusable(false);
		dec.setFocusable(false);
		oct.setFocusable(false);
		bin.setFocusable(false);
		mult.setFocusable(false);
		add.setFocusable(false);
		sub.setFocusable(false);
		div.setFocusable(false);
		addOrmin.setFocusable(false);
		letA.setFocusable(false);
		letB.setFocusable(false);
		letC.setFocusable(false);
		letD.setFocusable(false);
		letE.setFocusable(false);
		letF.setFocusable(false);
		backspace.setFocusable(false);
		c.setFocusable(false);
		ce.setFocusable(false);
		arrow.setFocusable(false);
		JBmod.setFocusable(false);
		lsh.setFocusable(false);
		rsh.setFocusable(false);
		or.setFocusable(false);
		xor.setFocusable(false);
		not.setFocusable(false);
		and.setFocusable(false);
		ParLeft.setFocusable(false);
		ParRight.setFocusable(false);
		num1.setFocusable(false);
		num2.setFocusable(false);
		num3.setFocusable(false);
		num4.setFocusable(false);
		num5.setFocusable(false);
		num6.setFocusable(false);
		num7.setFocusable(false);
		num8.setFocusable(false);
		num9.setFocusable(false);
		num0.setFocusable(false);
		equal.setFocusable(false);
		KeyboardUpper.setFocusable(false);

		DisplayArea.setFocusable(false);

		upperInnerTwo.setFocusable(false);
		KeyboardUpper.setFocusable(false);

		disableButton();

		// ----------------------------
		// Frame creation and panel implementation within the MAIN Frame
		frame = new JFrame("Programmer Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(DisplayArea, BorderLayout.NORTH);
		frame.add(Conversion, BorderLayout.CENTER);
		frame.add(Keyboard, BorderLayout.SOUTH);
		// frame.addComponentListener(new MyComponentListener());

		// size is fixed
		//frame.setSize(new Dimension(xSize, ySize));
		frame.setResizable(false);

		frame.pack();
		frame.setVisible(true);

	}

	/*
	 * This function receives the button number value and actually adjust and
	 * implements it not in the string form but the actual number string form so
	 * that it can be calculated later
	 */
	public void NumButtons(int theNum) {

		Long postEdgeValue = (((long) Math.pow(2, flag - 1) - 1) / disPlayMod);
		Long negaEdgeValue = postEdgeValue * (-1);
		if (currentValue > 0 && currentValue.equals(postEdgeValue) && theNum > 7)
			;
		else if (currentValue < 0 && currentValue.equals(negaEdgeValue) && theNum > 8)
			;
		else if (currentValue > 0 && currentValue > postEdgeValue)
			;
		else if (currentValue < 0 && currentValue < negaEdgeValue)
			;
		else {
			if (operatorStatus == true) {
				if (operator != 'z') {
					doubleStacks.push(operator);
				}
			}
			if (currentValue < 0) {
				currentValue = currentValue * disPlayMod - theNum;
				if ((currentValue) <= MAX_WORD) {
				} else {
					currentValue += theNum;
					currentValue /= disPlayMod;
				}
			} else {
				currentValue = currentValue * disPlayMod + theNum;
				if ((currentValue) <= MAX_WORD) {
				} else {
					currentValue -= theNum;
					currentValue /= disPlayMod;
				}
			}
		}
		operatorStatus = false;
		operator = 'z';
	}

	// reboots the last entry to its null content, leaves the equation label as is
	public void cleanCurrent(JLabel jl, JButton Hex, JButton Dec, JButton Oct, JButton Bin) {
		currentValue = 0L;
		String temp = "0";
		jl.setText(temp);
		Hex.setText("HEX  0");
		Dec.setText("DEC  0");
		Oct.setText("OCT  0");
		Bin.setText("BIN   0");
		clean = false;
	}

	// Reboots every label to its null contents
	public void cleanEvey(JLabel nl, JLabel el, JButton Hex, JButton Dec, JButton Oct, JButton Bin) {
		currentValue = 0L;
		previousValue = 0L;
		equationLabelText = " ";
		operator = 'z';
		String temp = "0";
		Function.setEquationLabel(el, disPlayMod, equationLabelText, disPlayMod, flag);
		nl.setText(temp);
		Hex.setText("HEX  0");
		Dec.setText("DEC  0");
		Oct.setText("OCT  0");
		Bin.setText("BIN   0");
		doubleStacks.cleanUp();
		clean = false;
	}

	/*
	 * This section makes sure through the form of the flag and its current value on
	 * what to display depending in the form the user has chosen varying through
	 * Hex,Dec,Oct,Bin, WOrd, Qwrod , Dword , byte
	 */
	public void disableButton() {
		switch (disPlayMod) {
		case 10:
			letA.setEnabled(false);
			letB.setEnabled(false);
			letC.setEnabled(false);
			letD.setEnabled(false);
			letE.setEnabled(false);
			letF.setEnabled(false);
			num9.setEnabled(true);
			num8.setEnabled(true);
			num7.setEnabled(true);
			num6.setEnabled(true);
			num5.setEnabled(true);
			num4.setEnabled(true);
			num3.setEnabled(true);
			num2.setEnabled(true);
			break;
		case 16:
			letA.setEnabled(true);
			letB.setEnabled(true);
			letC.setEnabled(true);
			letD.setEnabled(true);
			letE.setEnabled(true);
			letF.setEnabled(true);
			num9.setEnabled(true);
			num8.setEnabled(true);
			num7.setEnabled(true);
			num6.setEnabled(true);
			num5.setEnabled(true);
			num4.setEnabled(true);
			num3.setEnabled(true);
			num2.setEnabled(true);
			break;
		case 8:
			letA.setEnabled(false);
			letB.setEnabled(false);
			letC.setEnabled(false);
			letD.setEnabled(false);
			letE.setEnabled(false);
			letF.setEnabled(false);
			num9.setEnabled(false);
			num8.setEnabled(false);
			num7.setEnabled(true);
			num6.setEnabled(true);
			num5.setEnabled(true);
			num4.setEnabled(true);
			num3.setEnabled(true);
			num2.setEnabled(true);
			break;
		case 2:
			letA.setEnabled(false);
			letB.setEnabled(false);
			letC.setEnabled(false);
			letD.setEnabled(false);
			letE.setEnabled(false);
			letF.setEnabled(false);
			num9.setEnabled(false);
			num8.setEnabled(false);
			num7.setEnabled(false);
			num6.setEnabled(false);
			num5.setEnabled(false);
			num4.setEnabled(false);
			num3.setEnabled(false);
			num2.setEnabled(false);
			break;
		}
	}

	/*
	 * We implemented the interface KeyListener in the form of keyReleased so we
	 * made sure that the button is only registered when the user releases the
	 * button
	 * 
	 * We implement the action of the keyListener with the ".doClick()" call
	 */

	public class MyKeyListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_SHIFT)
				Bshift = true;
			if (e.getKeyCode() == KeyEvent.VK_NUMPAD0 || e.getKeyCode() == KeyEvent.VK_0) {
				if (e.getKeyCode() == KeyEvent.VK_0 && Bshift == true)
					ParRight.doClick();
				else
					num0.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 || e.getKeyCode() == KeyEvent.VK_1) {
				num1.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_2) {
				num2.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 || e.getKeyCode() == KeyEvent.VK_3) {
				num3.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_4) {
				num4.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5 || e.getKeyCode() == KeyEvent.VK_5) {
				if (e.getKeyCode() == KeyEvent.VK_5 && Bshift == true)
					JBmod.doClick();
				else
					num5.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 || e.getKeyCode() == KeyEvent.VK_6) {
				num6.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 || e.getKeyCode() == KeyEvent.VK_7) {
				num7.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 || e.getKeyCode() == KeyEvent.VK_8) {
				if (e.getKeyCode() == KeyEvent.VK_8 && Bshift == true)
					mult.doClick();
				else
					num8.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 || e.getKeyCode() == KeyEvent.VK_9) {
				if (e.getKeyCode() == KeyEvent.VK_9 && Bshift == true)
					ParLeft.doClick();
				else
					num9.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
				sub.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_ADD) {
				add.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_X || e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
				mult.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_SLASH || e.getKeyCode() == KeyEvent.VK_DIVIDE) {
				div.doClick();
			} else if (e.getKeyChar() == KeyEvent.VK_EQUALS || e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (e.getKeyCode() == KeyEvent.VK_EQUALS && Bshift == true)
					add.doClick();
				else
					equal.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				backspace.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT_PARENTHESIS) {
				ParLeft.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT_PARENTHESIS) {
				ParRight.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				letA.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_B) {
				letB.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_C) {
				letC.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				letD.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				letE.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_F) {
				letF.doClick();
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				c.doClick();
			}
		}

		public void keyTyped(KeyEvent e) {

		}

		/**
		 * 
		 */
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SHIFT)
				Bshift = false;
		}
		/*
		 * This section implements the mouse listener so that the gui can shadow over
		 * the buttons they are over
		 */

	}

	public class MyMouseListener implements MouseListener {
		Color Origin = new Color(251, 251, 251);

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton JB = (JButton) e.getSource();
			if (JB.isEnabled() == true)
				JB.setBackground(new Color(207, 207, 207));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton JB = (JButton) e.getSource();
			if (JB.isEnabled() == true)
				JB.setBackground(new Color(207, 207, 207));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton JB = (JButton) e.getSource();
			if (JB.isEnabled() == true)
				JB.setBackground(Origin);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton JB = (JButton) e.getSource();
			if (JB.isEnabled() == true) {
				Origin = JB.getBackground();
				JB.setBackground(new Color(219, 219, 219));
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton JB = (JButton) e.getSource();
			if (JB.isEnabled() == true)
				JB.setBackground(Origin);
		}

	}
}
