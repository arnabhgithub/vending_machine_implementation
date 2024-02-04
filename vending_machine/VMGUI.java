package vending_machine;


import javax.swing.*; // This is all of the JButtons and JLabels, etc.
import java.awt.*; // This is the Toolkit and the Dimension Stuff.
import java.awt.event.*; // This is for the actionListeners and actionPerformed function.
import javax.swing.JOptionPane; // This is for graphical IO stuff.

public class VMGUI extends JFrame implements ActionListener {

	private int StartingBalance = 0;
	public boolean CloseAfter = false;

	private int Width = 350;
	private int Height = 225;


	private JButton Buttons[];
	private int i;
	public VM Machine = new VM(StartingBalance, CloseAfter);
	public JLabel About = new JLabel("   Vending Machine");

	public JLabel Bal = new JLabel("   Balance: " + Machine.getPrettyBalance());


	public VMGUI() {

		setTitle("Vending Machine");
		Toolkit myTK = Toolkit.getDefaultToolkit();
		Dimension myD = myTK.getScreenSize();
		setBounds((myD.width - Width)/2, (myD.height - Height)/2, Width, Height);


		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} // END windowClosing(WindowEvent e)
		}
		);


		Buttons = new JButton[12];
		Machine.Initialize();

		for (i = 0 ; i < 6 ; i++) {
			Buttons[i] = new JButton(Machine.getDescription(i) + " - " + Machine.PrettyMoney(Machine.getPrice(i)));

		}

		Buttons[6] = new JButton("Dollar");
		Buttons[7] = new JButton("Half Dollar");
		Buttons[8] = new JButton("Quarter");
		Buttons[9] = new JButton("Dime");
		Buttons[10] = new JButton("Nickel");
		Buttons[11] = new JButton("Coin Return");


		Container pane = getContentPane();
		pane.setLayout(new GridLayout(7,2));

		for(i = 0 ; i < 6; i++) {
			pane.add(Buttons[i]);
			pane.add(Buttons[i+6]);
			Buttons[i].addActionListener(this);
			Buttons[i+6].addActionListener(this);
		}

		pane.add(Bal);
		pane.add(About);

	}
	public void actionPerformed(ActionEvent e) {
		boolean Success;
		Machine.Initialize();

		Object theButton = e.getSource();

		for(i = 6; i<12 ; i++) {
			if(theButton == Buttons[i]) {
				switch(i) {
					case 6:
						Machine.AddMoney(100);
						break;
					case 7:
						Machine.AddMoney(50);
						break;
					case 8:
						Machine.AddMoney(25);
						break;
					case 9:
						Machine.AddMoney(10);
						break;
					case 10:
						Machine.AddMoney(5);
						break;
					case 11:
						Machine.ReturnChange();
						System.exit(0);
				}
			}
		}
		for(i = 0; i < 6; i++) {
			if(theButton == Buttons[i]) {
				Success = Machine.BuyItem(i);

				if ((Machine.getCA() == true) || (Success == true)) {

					Machine.ReturnChange();
					System.exit(0);
				}
			}
		}
		Bal.setText("   Balance: " + VM.getPrettyBalance());

	}
}