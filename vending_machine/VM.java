package vending_machine;

import javax.swing.JOptionPane;

public class VM {

final static int PRICE_0 = 105;
final static int PRICE_1 = 145;
final static int PRICE_2 = 215;
final static int PRICE_3 = 295;
final static int PRICE_4 = 995;
final static int PRICE_5 = 405;

final static String DESC_0 = "Pepsi";
final static String DESC_1 = "Mountain Dew";
final static String DESC_2 = "Coke";
final static String DESC_3 = "Diet Coke";
final static String DESC_4 = "FANTA";
final static String DESC_5 = "MILK";

private static int balance; /
private static boolean CA = false;


private static int Price[] = new int[6];

private static String Description[] = new String[6];

private static int MaxLen = 0;

public static void setPrice(int ItemNumber, int thePrice) {

	Price[ItemNumber] = thePrice;
}
public static int getPrice(int ItemNumber) {

	return Price[ItemNumber];
}
public static void setDescription(int ItemNumber, String theDescription) {

	Description[ItemNumber] = theDescription;
}

public static String getDescription(int ItemNumber) {

	return Description[ItemNumber];
}

public VM(int myBalance, boolean CloseAfter) {
	balance = myBalance;
	CA = CloseAfter;

}

public static boolean getCA() {
	return CA;
}

public static void setBalance(int Balance) {

	balance = Balance;
}

public static boolean BuyItem(int ItemNumber) {
	boolean returnMe;
	if (balance < getPrice(ItemNumber)) {

		Output("Not enough money!", 1);

		returnMe = false;
	}

	else {
		balance -= getPrice(ItemNumber);

		Output("Bought " + getDescription(ItemNumber) + " for " + PrettyMoney(getPrice(ItemNumber)), 1);
		returnMe = true;
	}

	return returnMe;
}
public static void PrintMenu() {

	String myMenu =
	      "1  " + getDescription(0) + "   " + PrettyMoney(getPrice(0)) + "    D  1.00 (Dollar)\n" +
	      "2  " + getDescription(1) + "   " + PrettyMoney(getPrice(1)) + "    H  0.50 (Half Dollar)\n" +
	      "3  " + getDescription(2) + "   " + PrettyMoney(getPrice(2)) + "    Q  0.25 (Quarter)\n" +
	      "4  " + getDescription(3) + "   " + PrettyMoney(getPrice(3)) + "    M  0.10 (Dime)\n" +
	      "5  " + getDescription(4) + "   " + PrettyMoney(getPrice(4)) + "    N  0.05 (Nickel)\n" +
	      "6  " + getDescription(5) + "   " + PrettyMoney(getPrice(5)) + "    C  Coin Return";

    Output(myMenu, 1);
}

public static void Output(String Message, int Type) {

	if (Type == 1) {
		JOptionPane.showMessageDialog(null, Message, "Vending Machine", JOptionPane.PLAIN_MESSAGE);
	}
	else {


	}
}

public static void ReturnChange() {

	int myTotal = getBalance();
	int Dollars = 0;
	int HalfDollars = 0;
	int Quarters = 0;
	int Dimes = 0;
	int Nickels = 0;

	Dollars = myTotal / 100;
	myTotal -= (100 * Dollars);
	HalfDollars = myTotal / 50;
	myTotal -= (50 * HalfDollars);
	Quarters = myTotal / 25;
	myTotal -= (25 * Quarters);
	Dimes = myTotal / 10;
	myTotal -= (10 * Dimes);
	Nickels = myTotal / 5;
	myTotal -= (5 * Nickels);
	if (!(myTotal == 0)) {
		Output("Something went very wrong! You need pennies! How did you get pennies in there?", 1);

	}

	Output("Your change is: \n" +
			Dollars + " Dollars\n" +
			HalfDollars + " Half Dollars\n" +
			Quarters + " Quarters\n" +
			Dimes + " Dimes\n" +
			Nickels + " Nickels\n" +
			"For a total of " + PrettyMoney(getBalance())
			,1);
	setBalance(0); }

public static String getPrettyBalance() {

	return PrettyMoney(balance); }

public static int getBalance() {

	return balance; }

public static void AddMoney(int Amount) {

	balance = balance + Amount; }


public static void Initialize() {

	int i = 0;
	String Desc = null;
    setDescription(0, DESC_0);
    setDescription(1, DESC_1);
    setDescription(2, DESC_2);
    setDescription(3, DESC_3);
    setDescription(4, DESC_4);
    setDescription(5, DESC_5);

    setPrice(0, PRICE_0);
    setPrice(1, PRICE_1);
    setPrice(2, PRICE_2);
    setPrice(3, PRICE_3);
    setPrice(4, PRICE_4);
    setPrice(5, PRICE_5);

    for(i = 0 ; i < 6; i++) {
		Desc = getDescription(i);
		if (Desc.length() > MaxLen) {
			MaxLen = getDescription(i).length();
		}
	}
	FormatDescriptions(MaxLen);
}

public static void FormatDescriptions(int ML) {

}


public static String PrettyMoney (int Money) {
    int Dollars = 0;
    int Cents = 0;
    int Tens = 0;
	int Ones = 0;
    String Pretty = null;
    Dollars = Money / 100;
    Cents = Money % 100;

    Tens = Cents / 10;
    Ones = Cents % 10;

    Pretty = "$" + Dollars + "." + Tens + Ones;
    return Pretty;
}
}
