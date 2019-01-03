package windows;

import mysql.Database;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class CreatAccountFrame extends JFrame{


	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 300;


	private JTextField resultFieldFirstName;
	private JTextField resultFieldLastName;
	private JTextField resultFieldUseName;
	private JTextField resultFieldPassword;
	private JTextField resultFieldConfirmPassword;
	private JTextField resultFieldStreet;
	private JTextField resultFieldCity;
	private JTextField resultFieldZipCode;
    private JTextField resultFieldEmailAddress;
	
	private JButton submitButton;

	private JLabel FirstNameLabel;
	private JLabel LastNameLabel;
	private JLabel UseNameLabel;
	private JLabel PasswordLabel;
	private JLabel ConfirmPasswordLabel;
	private JLabel AddressLabel;
	private JLabel CityLabel;
	private JLabel StateLabel;
	private JComboBox<String> StateCombo;
	private JLabel ZIPLabel;
    private JLabel EmailLabel;
    private JLabel AccountLabel;
    private ButtonGroup accountGroup;
    private JRadioButton businessType;
    private JRadioButton consumerType;
    private int accountType;

	private JPanel keyPanel;
	private JPanel keyonePanel;
	private JPanel keytwoPanel;
	private JPanel keythreePanel;
	private JPanel radioPanel;

	private ActionListener listener;
	private Database dbConnect = new Database();


	public CreatAccountFrame()
	{  

		createTextFieldFirstName();
		createTextFieldLastName();
		createTextFieldUseName();
		createTextFieldPassword();
		createTextFieldConfirmedPassword();
		createTextFieldStreet();
		createTextFieldCity();
		createStateComboBox();
		createTextFieldZIP();
		createTextFieldEmail();
		createSubmitButton();
		createAccountType();
		
		
		createPanel();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}


	private void createTextFieldFirstName()
	{
		FirstNameLabel = new JLabel(" First Name: ");
		final int FIELD_WIDTH = 10;
		resultFieldFirstName = new JTextField(FIELD_WIDTH);

	}

	private void createTextFieldLastName()
	{
		LastNameLabel = new JLabel(" Last Name: ");
		final int FIELD_WIDTH = 10;
		resultFieldLastName = new JTextField(FIELD_WIDTH);
		}

	private void createTextFieldUseName()
	{
		UseNameLabel = new JLabel(" Username: ");
		final int FIELD_WIDTH = 10;
		resultFieldUseName = new JTextField(FIELD_WIDTH);
	}

	private void createTextFieldPassword()
	{
		PasswordLabel = new JLabel(" Password: ");
		final int FIELD_WIDTH = 10;
		resultFieldPassword = new JTextField(FIELD_WIDTH);
	}

	private void createTextFieldConfirmedPassword()
	{
		ConfirmPasswordLabel = new JLabel(" Password Confirm: ");
		final int FIELD_WIDTH = 10;
		resultFieldConfirmPassword = new JTextField(FIELD_WIDTH);
	}

	private void createTextFieldStreet()
	{
		AddressLabel = new JLabel(" Street: ");
		final int FIELD_WIDTH = 10;
		resultFieldStreet = new JTextField(FIELD_WIDTH);
	}

	private void createTextFieldCity()
	{
		CityLabel = new JLabel(" City: ");
		final int FIELD_WIDTH = 10;
		resultFieldCity = new JTextField(FIELD_WIDTH);
	}


	public JPanel createStateComboBox()
	{
		StateLabel=new JLabel(" State:");
		StateCombo = new JComboBox<String>();
		StateCombo.addItem("");
		StateCombo.addItem("AL");
		StateCombo.addItem("AK");
		StateCombo.addItem("AZ");
		StateCombo.addItem("AR");
		StateCombo.addItem("CA");
		StateCombo.addItem("CO");
		StateCombo.addItem("CT");
		StateCombo.addItem("DE");
		StateCombo.addItem("FL");
		StateCombo.addItem("GA");
		StateCombo.addItem("HI");
		StateCombo.addItem("ID");
		StateCombo.addItem("IL");
		StateCombo.addItem("IN");
		StateCombo.addItem("IA");
		StateCombo.addItem("KS");
		StateCombo.addItem("KY");
		StateCombo.addItem("LA");
		StateCombo.addItem("ME");
		StateCombo.addItem("MD");
		StateCombo.addItem("MA");
		StateCombo.addItem("MI");
		StateCombo.addItem("MN");
		StateCombo.addItem("MS");
		StateCombo.addItem("MO");
		StateCombo.addItem("MT");
		StateCombo.addItem("ME");
		StateCombo.addItem("NV");
		StateCombo.addItem("NH");
		StateCombo.addItem("NJ");
		StateCombo.addItem("NM");
		StateCombo.addItem("NY");
		StateCombo.addItem("NC");
		StateCombo.addItem("ND");
		StateCombo.addItem("OH");
		StateCombo.addItem("OK");
		StateCombo.addItem("OR");
		StateCombo.addItem("PA");
		StateCombo.addItem("RI");
		StateCombo.addItem("SC");
		StateCombo.addItem("SD");
		StateCombo.addItem("TN");
		StateCombo.addItem("TX");
		StateCombo.addItem("UT");
		StateCombo.addItem("VT");
		StateCombo.addItem("VA");
		StateCombo.addItem("WA");
		StateCombo.addItem("WV");
		StateCombo.addItem("WI");
		StateCombo.addItem("WY");
		StateCombo.setEditable(false);

		StateCombo.addActionListener(listener);

		JPanel panel = new JPanel();
		panel.add(StateCombo);
		return panel;
	}

	private void createTextFieldZIP()
	{
		ZIPLabel = new JLabel(" ZIP: ");
		final int FIELD_WIDTH = 10;
		resultFieldZipCode = new JTextField(FIELD_WIDTH);
	}
   
    private void createTextFieldEmail(){
    	EmailLabel= new JLabel("Email Address:");
    	final int FIELD_WIDTH =10;
    	resultFieldEmailAddress = new JTextField(FIELD_WIDTH);
    	
    }
    
    private void createAccountType()
    {
    	AccountLabel = new JLabel(" Type: ");
    	consumerType = new JRadioButton("Customer", true);
    	businessType = new JRadioButton("Business");
    	
    	accountGroup = new ButtonGroup();
    	accountGroup.add(businessType);
    	accountGroup.add(consumerType);
    }
    


	private void createSubmitButton()
	{
		submitButton = new JButton("Submit");

		class AddInterestListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				
				boolean customerSelected = consumerType.isSelected();
				
				String getFirstName = resultFieldFirstName.getText();

				String getLastName = resultFieldLastName.getText();

				String getUseName = resultFieldUseName.getText();

				String getPassword = resultFieldPassword.getText();

				String getConfirmPassword = resultFieldConfirmPassword.getText();

				String getStreet = resultFieldStreet.getText();

				String getCity = resultFieldCity.getText();

				String getState = StateCombo.getSelectedItem().toString();

				String getZipCode = resultFieldZipCode.getText();
				
				String getEmailAddress = resultFieldEmailAddress.getText();
				
				boolean createAccount;
				
				if(customerSelected)
				{
					createAccount = Database.createUser(getUseName, getPassword,
							getEmailAddress, 1);
					System.out.println("CreateAccount = " + createAccount);
				}
				else
				{
					createAccount = Database.createUser(getUseName, getPassword,
							getEmailAddress, 0);
					System.out.println("CreateAccount = " + createAccount);
				}
				if(createAccount)
				{
				    JOptionPane.showMessageDialog(null, "success!");
				    dispose();
				}
				else
				{
				    JOptionPane.showMessageDialog(null, "fail!");
				}
			}            
		}

		ActionListener listener = new AddInterestListener();
		submitButton.addActionListener(listener);
	}

	private void createPanel()
	{
		keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(11,1));

		radioPanel = new JPanel();
		radioPanel.setLayout(new BorderLayout());
		radioPanel.add(consumerType, BorderLayout.WEST);
		radioPanel.add(businessType, BorderLayout.EAST);

		keyPanel.add(submitButton);

		keytwoPanel = new JPanel();
		keytwoPanel.setLayout(new GridLayout(11,1));

		keytwoPanel.add(FirstNameLabel);
		keytwoPanel.add(LastNameLabel);
		keytwoPanel.add(UseNameLabel);
		keytwoPanel.add(PasswordLabel);
		keytwoPanel.add(ConfirmPasswordLabel);
		keytwoPanel.add(AddressLabel);
		keytwoPanel.add(CityLabel);
		keytwoPanel.add(StateLabel);
		keytwoPanel.add(ZIPLabel);
        keytwoPanel.add(EmailLabel);
        keytwoPanel.add(AccountLabel);
        
		keythreePanel = new JPanel();
		keythreePanel.setLayout(new GridLayout(11,1));

		keythreePanel.add(resultFieldFirstName);
		keythreePanel.add(resultFieldLastName);
		keythreePanel.add(resultFieldUseName);
		keythreePanel.add (resultFieldPassword);
		keythreePanel.add (resultFieldConfirmPassword);
		keythreePanel.add(resultFieldStreet);
		keythreePanel.add(resultFieldCity);
		keythreePanel.add(StateCombo);
		keythreePanel.add(resultFieldZipCode);
		keythreePanel.add(resultFieldEmailAddress);
		keythreePanel.add(radioPanel);
		
		keyonePanel = new JPanel();
		keyonePanel.setLayout(new GridLayout(1,3));
		keyonePanel.add(keytwoPanel,BorderLayout.WEST);
		keyonePanel.add(keythreePanel, BorderLayout.CENTER);
		keyonePanel.add(keyPanel, BorderLayout.EAST);

		add(keyonePanel);
	} 

}