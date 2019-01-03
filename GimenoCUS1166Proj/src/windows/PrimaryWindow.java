package windows;

import mysql.Database;
import itemList.Pkg;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PrimaryWindow extends JFrame {
	
   private static final int FRAME_WIDTH = 800;
   private static final int FRAME_HEIGHT = 400;
  
   private JLabel JL_User;
   private JScrollPane pkgPane;
   private TableModel tableModel;
   private JPanel keyPanel;
   private JPanel keytwoPanel; 
   private JPanel keythreePanel;
   private JTextField TF_ItemNumber;
   private JButton JB_Item;
   private JButton JB_submit;
   private JButton JB_courier;
   private JButton JB_shipedDate;
   private JButton JB_estimatedDelivery;
   private JButton JB_orderStatus;
   private JButton JB_accountOwnership;
   private JTable table;
   private String[][] data;
   private String[] columns;
   private int userID;
   private int usertype;
   private int numRows;
   private int numCol;
   private ArrayList<Pkg> pkgList;
   private ArrayList<Pkg> orgList;

   public PrimaryWindow(int userID, int usertype){
       
	   numRows = Database.getRowCount(userID, usertype);
	   numCol = 5;
	   data = new String[numRows][numCol];
	   this.userID = userID;
	   this.usertype = usertype;
	   JL_User = new JLabel("Please enter your tracking number:");
       TF_ItemNumber = new JTextField("", 16);		
       JB_submit = new JButton("Submit");
       JB_Item = new JButton("Item");
       JB_courier = new JButton("Courier");
       JB_shipedDate = new JButton("Shipped Date");
       JB_estimatedDelivery = new JButton("Estimated Delivery Date");
       JB_orderStatus = new JButton("Order Status");
	   JB_accountOwnership = new JButton("Account ownership");
       if(usertype == 0)
       {
    	   String[] tempArray = {"Tracking No.", "Ordered By", "Date Shipped",
    			   "Date of Arrival", "Status"};
    	   columns = tempArray;

       }
       else
       {
    	   String[] tempArray = {"Tracking No.", "Ordered From", "Date Shipped",
    			   "Date of Arrival", "Status"};
    	   columns = tempArray;
   	   }
       
       pkgList = Database.getPackages(userID, usertype);
       orgList = pkgList;
       createButton();
       createPanel();

       setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }




   public void createButton(){
	   JB_submit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   keyPanel.setVisible(false);
               String ItemNumber = TF_ItemNumber.getText();
               ArrayList<Pkg> newList = new ArrayList<Pkg>();
               for(Pkg pkg: pkgList){
            	   if(ItemNumber.equals(pkg.getTrackingNumber())){
            		   newList.add(pkg);
            	   }
               }
               pkgList = newList;
               data = new String[pkgList.size()][numCol];
               remove(keyPanel);
               createPanel();
               
            	   
           }
       });
	   JB_Item.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   sortList("item");
               
           }
       });
	   JB_courier.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   sortList("courier");
			   
		   }
	   });
	   JB_shipedDate.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   sortList("shipped");
		   }
	   });
	   JB_estimatedDelivery.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   sortList("arrival");
		   }
	   });
	   JB_orderStatus.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   sortList("status");
		   }
	   });
    
       }
        

   public void createPanel(){
       createPackages();
       
       pkgPane = new JScrollPane(table);
       int numRows = table.getRowCount();
       int numCols = table.getColumnCount();
       tableModel = table.getModel();
       
          
	   keythreePanel = new JPanel();
	   keythreePanel.add(JL_User);
       keythreePanel.add(TF_ItemNumber);
	   keythreePanel.add(JB_submit);
	   keythreePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		keytwoPanel = new JPanel();
		keytwoPanel.add(JB_Item);
		keytwoPanel.add(JB_courier);
		keytwoPanel.add(JB_shipedDate);
		keytwoPanel.add(JB_estimatedDelivery);
		keytwoPanel.add(JB_orderStatus);
		if(usertype == 0)
			keytwoPanel.add(JB_accountOwnership);
		
		keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(3,0));
		keyPanel.add(keythreePanel, BorderLayout.NORTH);	
		keyPanel.add(keytwoPanel,BorderLayout.CENTER);
		keyPanel.add(pkgPane, BorderLayout.SOUTH);
        add(keyPanel);
      
   }
   
   private void createPackages(){
	   for(int i = 0; i < pkgList.size(); i++)
	   {
		   data[i][0] = pkgList.get(i).getTrackingNumber();
		   data[i][1] = pkgList.get(i).getAccountName();
		   data[i][2] = pkgList.get(i).getDateShipped();
		   data[i][3] = pkgList.get(i).getDateArrival();
		   data[i][4] = pkgList.get(i).getStatus();
	   }
	   if(usertype == 1)
	   {
		   MyTableModel tableModel = new MyTableModel(data, columns);
		   table = new JTable(tableModel);
	   }
	   else
		   table = new JTable(data, columns);
	   table.setPreferredScrollableViewportSize(new Dimension(500, 700));
	   table.setFillsViewportHeight(true);
	   }
   
   private void sortList(String byType){
	   pkgList = orgList;
	   Pkg temp;
	   int minI;
	   for(int i=0;i<pkgList.size()-1;i++){
		   minI = i;
		   if(byType.equals("item")){
			   for(int j=i+1;j<pkgList.size();j++){
				   if(pkgList.get(j).getTrackingNumber().compareTo(pkgList.get(minI).getTrackingNumber())<0){
					   minI = j;
				   }
			   }
		   }else if(byType.equals("courier")){
			   for(int j=i+1;j<pkgList.size();j++){
				   if(pkgList.get(j).getAccountName().compareTo(pkgList.get(minI).getAccountName())<0){
					   minI = j;
				   }
			   }
		   }else if(byType.equals("shipped")){
			   for(int j=i+1;j<pkgList.size();j++){
				   if(pkgList.get(j).getDateShipped().compareTo(pkgList.get(minI).getDateShipped())<0){
					   minI = j;
				   }
			   }
		   }else if(byType.equals("arrival")){
			   for(int j=i+1;j<pkgList.size();j++){
				   if(pkgList.get(j).getDateArrival().compareTo(pkgList.get(minI).getDateArrival())<0){
					   minI = j;
				   }
			   }
		   }else if(byType.equals("status")){
			   for(int j=i+1;j<pkgList.size();j++){
				   if(pkgList.get(j).getStatus().compareTo(pkgList.get(minI).getStatus())<0){
					   minI = j;
				   }
			   }
		   }
		   temp = pkgList.get(minI);
		   pkgList.set(minI, pkgList.get(i));
		   pkgList.set(i, temp);
	   }
	   
	   keyPanel.setVisible(false);
	   remove(keyPanel);
	   createPanel();
   }
   
   public class MyTableModel extends DefaultTableModel {

	   public MyTableModel(Object[][] tableData, Object[] colNames) {
	      super(tableData, colNames);
	   }

	   public boolean isCellEditable(int row, int column) {
	      return false;
	   }
	}

}




	