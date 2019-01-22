import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Function {
	/**
	 * This section sends the content of the previosuly pressed buttons to the
	 * equation label
	 * 
	 * @param el
	 *            equation label
	 * @param mod
	 *            mod
	 * @param input
	 *            input number
	 */
	public static void setEquationLabel(JLabel el, int mod, String input, int disPlayMod, int flag) {
		String temp = "";
		String Disply = " ";
		Long Ltemp = 0L;
		if (input.isEmpty() == false) {
			for (int i = 0; i < input.length(); i++) {

				if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
					temp = temp + String.valueOf(input.charAt(i));
				} else {
					if (temp.isEmpty() == false) {
						Ltemp = (Long.valueOf(temp));
						temp = "";
						if (disPlayMod == 10)
							Disply = Disply + Ltemp + input.charAt(i);
						else {
							String rawValue = String.valueOf(Converting.Convert(Ltemp, mod, flag));
							int loc, J;
							for (loc = 0, J = 0; J < rawValue.length() - 1;) {

								if (rawValue.charAt(J++) != '0')
									break;
								loc = J;
							}
							String NewString = rawValue.substring(loc, rawValue.length());
							Disply = Disply + String.valueOf(NewString) + input.charAt(i);
						}
					} else {
						Disply = Disply + input.charAt(i);
					}
				}
			}
			el.setText(Disply);
		} else {
			el.setText(input);
		}
	}

	// THis function obtains the string form of previous entry from the equation
	// label
	// and converts it to bits . then makes the check every four binary digits to be
	// correct.
	// Then adjusts the hex Dec, Bin , and oct labels to their correct values
	public static void ChangeValues(JLabel nl, JLabel el, JButton Hex, JButton Dec, JButton Oct, JButton Bin,
			Long currentValue, int flag, int disPlayMod) {

		String Sbin = String.valueOf(Converting.decToBit(currentValue, flag));
		int i = 0;
		int loc = 0;

		for (loc = 0, i = 0; i < Sbin.length() - 1;) {

			if (Sbin.charAt(i++) != '0')
				break;
			if (Sbin.charAt(i++) != '0')
				break;
			if (Sbin.charAt(i++) != '0')
				break;
			if (Sbin.charAt(i++) != '0')
				break;
			loc = i;
		}
		String NewBin = Sbin.substring(loc, Sbin.length());
		if (NewBin.isEmpty())
			NewBin = "0";
		Bin.setText("BIN   " + NewBin);
		// ------------------------------------------
		String Shex = String.valueOf(Converting.decToHex(currentValue, flag)).toUpperCase();
		for (loc = 0, i = 0; i < Shex.length() - 1;) {

			if (Shex.charAt(i++) != '0')
				break;
			loc = i;
		}
		String NewHex = Shex.substring(loc, Shex.length());
		Hex.setText("HEX  " + NewHex);
		// --------------------------------------------
		String Soct = String.valueOf(Converting.decToOct(currentValue, flag));
		for (loc = 0, i = 0; i < Soct.length() - 1;) {

			if (Soct.charAt(i++) != '0')
				break;
			loc = i;
		}
		String NewOct = Soct.substring(loc, Soct.length());
		Oct.setText("OCT  " + NewOct);
		Dec.setText("DEC  " + currentValue);
		// -----------------------------
		switch (disPlayMod) {
		case 16:
			nl.setText(NewHex);
			break;
		case 10:
			nl.setText(String.valueOf(currentValue));
			break;
		case 8:
			nl.setText(NewOct);
			if (currentValue > 18014398509481984L) {
				nl.setFont(new Font("SansSerif", Font.BOLD, 45));
			} else {
				nl.setFont(new Font("SansSerif", Font.BOLD, 50));
			}
			break;
		case 2:
			for (loc = 0, i = 0; i < Sbin.length() - 1;) {

				if (Sbin.charAt(i++) != '0')
					break;
				loc = i;
			}
			if (currentValue > 262144) {
				if (currentValue > 134217728L) {
					if (currentValue > 274877906944L) {
						nl.setFont(new Font("SansSerif", Font.BOLD, 15));
					} else {
						nl.setFont(new Font("SansSerif", Font.BOLD, 25));
					}
				} else {
					nl.setFont(new Font("SansSerif", Font.BOLD, 35));
				}
			} else if (currentValue < 0 && (flag == 64 || flag == 32)) {
				if (flag == 64)
					nl.setFont(new Font("SansSerif", Font.BOLD, 15));
				else
					nl.setFont(new Font("SansSerif", Font.BOLD, 25));
			} else {
				nl.setFont(new Font("SansSerif", Font.BOLD, 50));
			}
			String NewDispyBin = Sbin.substring(loc, Sbin.length());
			nl.setText(NewDispyBin);
			break;
		default:
			break;
		}
	}
	//this method will convert the value to the correct data type, ex: in word, 1000*1000=16960 rather than 1000000
	public static Long valueCheck(long currentValue, int flag) {
		Long temp;
		switch (flag) {
		case 64:
			return currentValue;
		case 32:
			int dis32 = (int) currentValue;
			temp = (long) dis32;
			return temp;
		case 16:
			short dis16 = (short) currentValue;
			temp = (long) dis16;
			return temp;
		case 8:
			byte dis8 = (byte) currentValue;
			temp = (long) dis8;
			return temp;
		}
		return currentValue;
	}
}
