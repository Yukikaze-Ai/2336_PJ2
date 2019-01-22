
import java.util.*;

public class doubleStacks {
	private static int leftPare = 0;
	 //Boolean flag section
	private static boolean canAddMinus = false;//can do add or minus 
	private static boolean multiAndDivd = false;//can do multiply or division 

	private static boolean hasMultOrDivd = false;//has * or / before ()
	private static boolean hasAddOrMinu = false; //has + or - before ()

	private static int prevNumbers = 0;//previous number, for ex: “1+2”, prevNumber = 1
	//creation of stacks, one is long and the other one is string 
	private static Stack<Long> numbers = new Stack<Long>();
	private static Stack<String> operator = new Stack<String>();

	//pop the last number 
	public static Long prevNum() {
		if (canAddMinus == true) {//reset the condition 
			hasAddOrMinu = true;
			canAddMinus = false;
		}
		if (multiAndDivd == true) {//reset the condition 
			hasAddOrMinu = true;
			canAddMinus = true;
		}
		return numbers.pop();
	}

	// returns the most recent operator with order of operation logic
	public static String prevOpe() {
		return operator.pop();
	}

	 // Get the result , Where the actual calculation takes place 
	public static Long getResult() {
		if (numbers.size() > 1) {
			while (numbers.size() > 1) {
				Long lastNume = numbers.pop();
				Long PrevNume = numbers.pop();
				String lastOperator = operator.pop();
				if (lastOperator == "+") {
					numbers.push(PrevNume + lastNume);
				} else if (lastOperator == "-") {
					numbers.push(PrevNume - lastNume);
				} else if (lastOperator == "*") {
					numbers.push(PrevNume * lastNume);
				} else if (lastOperator == "/") {
					numbers.push(PrevNume / lastNume);
				} else if (lastOperator == "%") {
					Long temp = PrevNume % lastNume;
					numbers.push(temp);
				}
			}
		}
		Long temp = numbers.pop();
		return temp;
	}

	//get the result in (), we will call the method later when there is a “)”
	public static void getResultInPare(int previousNumbers) {
		if (hasMultOrDivd == true) {
			hasMultOrDivd = false;
			multiAndDivd = true;
		}
		if (hasAddOrMinu == true) {
			canAddMinus = true;
			hasAddOrMinu = false;
		}
		//pop the operator, as long as the operator is not “(“, check the operator
		//pop the last number and previous number, do the operation 
		if (leftPare == 0)
			;
		// if((numbers.size()-previousNumbers)>1)
		String lastOperator = operator.pop();
		if (lastOperator != "(") {
			while (lastOperator != "(") {
				Long lastNume = numbers.pop();
				Long PrevNume = numbers.pop();
				if (lastOperator == "+") {
					numbers.push(PrevNume + lastNume);
					canAddMinus = false;
				} else if (lastOperator == "-") {
					numbers.push(PrevNume - lastNume);
					canAddMinus = false;
				} else if (lastOperator == "*") {
					numbers.push(PrevNume * lastNume);
					multiAndDivd = false;
				} else if (lastOperator == "/") {
					numbers.push(PrevNume / lastNume);
					multiAndDivd = false;
				} else if (lastOperator == "%") {
					numbers.push(PrevNume % lastNume);
					multiAndDivd = false;
				}
				lastOperator = operator.pop();
			}
		}
		leftPare--;
	}
	
	/*This is the push numbers method  
	 If multiAndDivd is true, pop and check the operator, pop the previous number
	 calculate the result of the previous num and the current num based on the operator, 
	 and then push the result.
	 otherwise , push the number into the stack */
	public static void push(Long num) {
		if (multiAndDivd == true) {
			String oprt = operator.pop();
			if (oprt == "*") {
				Long prev = numbers.pop();
				numbers.push(prev * num);
				multiAndDivd = false;
				if (hasAddOrMinu == true) {
					canAddMinus = true;
					hasAddOrMinu = false;
				}
			} else if (oprt == "/") {
				Long prev = numbers.pop();
				numbers.push(prev / num);
				multiAndDivd = false;
				if (hasAddOrMinu == true) {
					canAddMinus = true;
					hasAddOrMinu = false;
				}
			} else if (oprt == "%") {
				Long prev = numbers.pop();
				numbers.push(prev % num);
				multiAndDivd = false;
				if (hasAddOrMinu == true) {
					canAddMinus = true;
					hasAddOrMinu = false;
				}
			}
		} else {
			numbers.push(num);
		}
	}
	/*This method check the operator, check the conditions and  push the operators 
	*/

	public static void push(char oper) {
		if (oper == '(') {
			prevNumbers = numbers.size();
			if (multiAndDivd == true) {
				hasMultOrDivd = true;
				multiAndDivd = false;
			}
			if (canAddMinus == true) {
				canAddMinus = false;
				hasAddOrMinu = true;
			}
			leftPare++;
			operator.push("(");
		} else if (oper == ')') {//when there is a “)”,  call getResultInPare() method 
			if (leftPare > 0) {
				getResultInPare(prevNumbers);
			}
		} else if (oper == '*') {//the case that num*num
			multiAndDivd = true;
			if (canAddMinus == true) {
				canAddMinus = false;
				hasAddOrMinu = true;
			}
			operator.push("*");
		} else if (oper == '/') {//the case that num/num
			multiAndDivd = true;
			if (canAddMinus == true) {
				canAddMinus = false;
				hasAddOrMinu = true;
			}
			operator.push("/");
		} else if (oper == '%') {//the case that num%num
			multiAndDivd = true;
			if (canAddMinus == true) {
				canAddMinus = false;
				hasAddOrMinu = true;
			}
			operator.push("%");
		} else if (oper == '+') {  //the case that num +/- num * or / or % num + num
			if (canAddMinus == true) {
				Long lastNum = numbers.pop();
				Long PrevNum = numbers.pop();
				String lastOperator = operator.pop();
				if (lastOperator == "+") {
					numbers.push(PrevNum + lastNum);
					operator.push("+");
					canAddMinus = true;
				} else if (lastOperator == "-") {
					numbers.push(PrevNum - lastNum);
					operator.push("+");
					canAddMinus = true;
				}
			} else {
				canAddMinus = true;
				operator.push("+");
			}

		} else if (oper == '-') {
			/*the cases that 
			something * num - num
			 something / num - num
			 something %num - num*/
			if (multiAndDivd == true) {
				String oprt = operator.pop();
				Long last = numbers.pop();
				Long prev = numbers.pop();
				if (oprt == "*") {
					numbers.push(prev * last);
				} else if (oprt == "/") {
					numbers.push(prev / last);
				} else if (oprt == "%") {
					numbers.push(prev % last);
				}
				multiAndDivd = false;
				//the case that num +/- num * or / or % num - num
				if (hasAddOrMinu == true) {
					canAddMinus = true;
					hasAddOrMinu = false;
				}
				if (operator.size() == 1) {
					oprt = operator.pop();
					if (oprt == "(") {
						leftPare--;
						operator.push(oprt);
						canAddMinus = false;
					} else {
						operator.push(oprt);
						canAddMinus = true;
					}
				}
			}
			//the case that num +or- num - num
			if (canAddMinus == true) {
				Long last = numbers.pop();
				Long prev = numbers.pop();
				String lastOperator = operator.pop();
				if (lastOperator == "+") {
					numbers.push(prev + last);
					operator.push("-");
					canAddMinus = true;
				} else if (lastOperator == "-") {
					numbers.push(prev - last);
					operator.push("-");
					canAddMinus = true;
				}
			} else {
				canAddMinus = true;
				operator.push("-");
			}
		} else if (oper == 'z')
			;
		else
			operator.push(String.valueOf(oper));
	}
	
	//clear everything when the method is called
	public static void cleanUp() {
		leftPare = 0;
		canAddMinus = false;
		multiAndDivd = false;
		hasMultOrDivd = false;
		hasAddOrMinu = false;
		prevNumbers = 0;
		numbers.clear();
		operator.clear();
	}
	//check if it is a proper equation
	public static boolean isGood() {
		if ((numbers.size() - (operator.size() - leftPare)) == 1)
			return true;
		else
			return false;
	}
	//check whether the number stack is empty or not 
	public static boolean numIsEmpty() {
		return numbers.size() == 0;
	}
}
