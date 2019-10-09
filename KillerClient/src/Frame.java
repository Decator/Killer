import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame {
	
	private static ServiceClient serviceClient;
	
	private JButton joinGameButton;
	private JTextField nameField;
	
	public Frame(KillerService look_up) {
		super();

		serviceClient = new ServiceClient(look_up);
		
		setTitle("Killer");
		setSize(800,600); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
	}
	
	public JPanel buildContentPane(){
		JPanel panelStart = new JPanel();
		panelStart.setBackground(Color.ORANGE);
	    this.setContentPane(panelStart); 
		
		nameField = new JTextField();
		nameField.setColumns(10);
		panelStart.add(nameField);
 
		joinGameButton = new JButton("Rejoignez la game");
		joinGameButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e){
				serviceClient.addPlayer(nameField.getText());
				
				JPanel panelWaiting = new JPanel();
				JLabel waitingText = new JLabel(serviceClient.getPlayer().getWaiting());
				panelWaiting.add(waitingText);
				setContentPane(panelWaiting);
				validate();
			   }
			});
		panelStart.add(joinGameButton);
 
		return panelStart;
	}
}
