import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame{

	private JButton bHost = new JButton("Host");
	private JButton bJoin = new JButton("Join");

	private JTextField iAddress = new JTextField("127.0.0.1", 5);
	private JTextField iPort = new JTextField("1212", 5);

	private JLabel lAddress = new JLabel("Address");
	private JLabel lPort = new JLabel("Port");

	static GUI app;

	public GUI() {
	    super("Simple Example");
	    this.setBounds(100,100,250,100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

	    Container container = this.getContentPane();
	    container.setLayout(new GridLayout(3,2,2,2));
	    container.add(lAddress);
	    container.add(iAddress);
	    container.add(lPort);
	    container.add(iPort);
	    

	    ButtonGroup group = new ButtonGroup();

	    bHost.addActionListener(al);
	    bJoin.addActionListener(al);
	    container.add(bHost);
	    container.add(bJoin);
	}
	

	public static void main(String[] args) {
		app = new GUI();
		app.setVisible(true);
	}

	ActionListener al = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent button){
			
			if (button.getSource() == bHost){
				
				doHost();
			}

			if (button.getSource() == bJoin){
				
				doJoin();
			}
			
		}
	};

	public void doHost(){
		app.setVisible(false);
		new ServerSide( Integer.parseInt(iPort.getText()) );
	}

	public void doJoin(){

		app.setVisible(false);
		new ClientSide( iAddress.getText(), iPort.getText() );

	}
}