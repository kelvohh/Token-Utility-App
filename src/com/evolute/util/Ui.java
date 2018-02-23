package com.evolute.util;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.jws.Oneway;
import javax.swing.*;

import com.evolute.util.TokenGenStat.mMi;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Window.Type;

public class Ui extends TokenGenStat{

	private JFrame frame;
	private JTextField txtfldcompName;
	private JTextField txtfldcompCode;
	private JTextField txtfldToken;
	private JTextField txtfldTokenvalid;
	private JTextField tknStsenter;
	private JTextPane txtpnTokenalloc;
	private JTextField devcode;
	private JTextField srno;
	File selectedFile = null;


//	private static boolean blDebug = false;
	public static class Log{
		public static void disp(String s){
			System.out.print(s);
		}
		public static void displn(String s){
			System.out.println(s);
		}
		public static void displn(){
			System.out.println();
		}
		public static void disperr(String s){
//			if (TokenGenStat.blDebug)
				System.err.println(s);
		}
		public static void resperr(String s){
			
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Log.disp("UI Entered Main function\n");
		
		//Single instance start
		try{
		    RandomAccessFile randomFile = new RandomAccessFile("single.class","rw");
		    FileChannel channel = randomFile.getChannel();
		    if(channel.tryLock() == null) 
		    return;
		}catch( Exception e ) { 
		    Log.disp(e.toString());
		}
		//Single instance end
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ui window = new Ui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ui() {
		initialize();
		Log.disp("Ui function Initialized\n");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Log.disp("Initialize() started\n");
		frame = new JFrame("Evolute RD Service Utility V1.0.9");
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/OnlyEsmall.png"));
		frame.setIconImage(image);
		frame.setBounds(100, 100, 780, 620);
		frame.setExtendedState(JFrame.NORMAL);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
//		frame.setResizable(true);
//		frame.setPreferredSize(new Dimension(750, 700));
//		frame.getContentPane().setLayout(null);
//		frame.setSize(720, 620);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setSize(screenSize.width, screenSize.height);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Token Allocation", null, panel, null);
		panel.setLayout(null);
		
		//Token allocation Environment selection
		JLabel lblSelectEnvironment = new JLabel("Select Environment ");
		lblSelectEnvironment.setFont(new Font("Andalus", Font.BOLD, 14));
		lblSelectEnvironment.setBounds(21, 195, 133, 31);
		panel.add(lblSelectEnvironment);
		final JComboBox comboBoxalloc = new JComboBox();
		comboBoxalloc.setBounds(164, 196, 127, 31);
		panel.add(comboBoxalloc); 
		comboBoxalloc.addItem("Staging");
		comboBoxalloc.addItem("Pre Production");
		comboBoxalloc.addItem("Production");
		//comboBoxalloc.setSelectedItem()
		
		//Token allocation company name
		JLabel lblNewLabel = new JLabel("Company Name");
		lblNewLabel.setFont(new Font("Andalus", Font.BOLD, 14));
		lblNewLabel.setBounds(21, 243, 133, 31);
		panel.add(lblNewLabel);
		
		txtfldcompName = new JTextField();
		txtfldcompName.setBounds(164, 244, 127, 31);
		panel.add(txtfldcompName);
		txtfldcompName.setColumns(10);
		
		//Token allocation company code
		JLabel lblCompanyCode = new JLabel("Company Code ");
		lblCompanyCode.setFont(new Font("Andalus", Font.BOLD, 14));
		lblCompanyCode.setBounds(21, 295, 133, 31);
		panel.add(lblCompanyCode);
		
		txtfldcompCode = new JTextField();
		txtfldcompCode.setBounds(164, 296, 127, 31);
		panel.add(txtfldcompCode);
		txtfldcompCode.setColumns(10);
		if (txtfldcompCode.getText().length() == 3) {
		    
		} else {

		} 
		
		
		//Token allocation NO. of tokens 
		JLabel lblNoOfTokens = new JLabel("No of Tokens");
		lblNoOfTokens.setFont(new Font("Andalus", Font.BOLD, 14));
		lblNoOfTokens.setBounds(21, 350, 133, 31);
		panel.add(lblNoOfTokens);
		
		txtfldToken = new JTextField();
		txtfldToken.setBounds(164, 351, 127, 31);
		panel.add(txtfldToken);
		txtfldToken.setColumns(10);
		
		//Token allocation Token validity
		JLabel lblValidityInDays = new JLabel("Validity in Days");
		lblValidityInDays.setFont(new Font("Andalus", Font.BOLD, 14));
		lblValidityInDays.setBounds(21, 406, 133, 31);
		panel.add(lblValidityInDays);
		
		txtfldTokenvalid = new JTextField();
		txtfldTokenvalid.setBounds(164, 407, 127, 31);
		panel.add(txtfldTokenvalid);
		txtfldTokenvalid.setColumns(10);
		
		//Token allocation button
		JButton btnAllocateTokens = new JButton("Allocate Tokens");
		btnAllocateTokens.setBackground(SystemColor.textHighlightText);
		btnAllocateTokens.setBounds(81, 492, 155, 41);
		btnAllocateTokens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Log.disperr("Allocate Button clicked");
				String sEnv = (String) comboBoxalloc.getSelectedItem();
				String s1 = txtfldcompName.getText();
				String s2 = txtfldcompCode.getText().replaceAll(" ", "");
				String s3 = txtfldToken.getText().replaceAll(" ", "");
				String s4 = txtfldTokenvalid.getText().replaceAll(" ", "");
				
				if (s1==null||s1.length()>30){
					JOptionPane.showConfirmDialog(frame, "Invalid Company Name", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid company name more than 30 char");
					txtpnTokenalloc.setText("Invalid company name more than 30 char");
					return;
				}
				if (s2==null||s2.length()!=3){
					JOptionPane.showConfirmDialog(frame, "Invalid Code", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid company code Less than 3 char");
					txtpnTokenalloc.setText("Invalid company code Less than 3 char");
					return;
				}
				if (s3==null||s3.length()==0){
					JOptionPane.showConfirmDialog(frame, "Invalid Quantity", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid Quantity=null ");
					txtpnTokenalloc.setText("Invalid Quantity=null ");
					return;
				}
				int qty=0;
				try {
					qty = Integer.parseInt(s3);
				} catch (NumberFormatException e1) {
					JOptionPane.showConfirmDialog(frame, "Invalid Quantity", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid. Quantity=0");
					txtpnTokenalloc.setText("Invalid. Quantity=0");
					return;
				}
				if(qty<=0){
					JOptionPane.showConfirmDialog(frame, "Invalid Quantity", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid Quantity<=0");
					txtpnTokenalloc.setText("Invalid Quantity<=0");
				}
				if (s4==null||s4.length()==0){
					JOptionPane.showConfirmDialog(frame, "Invalid Validity", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid Validity=0");
					txtpnTokenalloc.setText("Invalid. Validity=0");
					return;
				}
				int val=0;
				try {
					val = Integer.parseInt(s4);
				} catch (NumberFormatException e1) {
					JOptionPane.showConfirmDialog(frame, "Invalid Validity", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid Validity");
					txtpnTokenalloc.setText("Invalid. Validity");
					return;
				}
				if(val<=0){
					JOptionPane.showConfirmDialog(frame, "Invalid Validity", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid Validity<=0");
					txtpnTokenalloc.setText("Invalid. Validity<=0");
					
				}
				Details dt = new Details();
				dt.Name = new String(s1);
				dt.Code = new String(s2);
				dt.Quantity = qty;
				dt.Validity = val;
				mEnv en = mEnv.Staging;
				String s = (String) comboBoxalloc.getSelectedItem();
				
				if (s.equalsIgnoreCase("Staging"))
					en=mEnv.Staging;
				else if (s.equalsIgnoreCase("Pre Production"))
					en=mEnv.Pre_Prod;
				else if (s.equalsIgnoreCase("Production"))
					en=mEnv.Production;
				
				JFileChooser chooser = new JFileChooser();
				Log.disperr("File Chooser window");
				chooser.setDialogTitle("Select Output Path");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    int option = chooser.showSaveDialog(null);
			    File path =null;
			    String  spath ="";
			    if (option == JFileChooser.APPROVE_OPTION) {
			    	try {
						path = chooser.getSelectedFile();
						spath = path + File.separator;
						Log.disperr("File path selected");
					} catch (Exception e1) {
						return;
					}
			    } else {
			    	return;
			    }
			    try {
					Date d = new Date();
					SimpleDateFormat sf = new SimpleDateFormat("dd_MMM_yyyy");
					String str_Today = sf.format(d);
					Log.disperr("Today = "+str_Today);
					
					String strDate1 = str_Today+":23:59:59";
					long sad = new SimpleDateFormat("dd_MMM_yyyy:HH:mm:ss").parse(strDate1).getTime();//d.getTime();
					Log.disperr("long  : "+sad);
					
					long validity = dt.Validity;
					long epoch = validity*24*60*60*1000+sad;
					Log.disperr("epoch : "+epoch);
					
					Date sEpoch = new Date(epoch);
					String str_Till = sf.format(sEpoch);
					Log.displn();
					Log.displn();
					Log.disperr("Till  = "+str_Till);
					
					String sToday = str_Today.replaceAll("_", " ");
					String sTill  = str_Till.replaceAll("_", " ");
					String sa1="Token Allocation for "+s+"\n\n";
					String sa2="Tokens Issued to  "+dt.Name+"\n";
					String sa3="Code is "+dt.Code+" ";
					String sa4="of "+dt.Quantity+" quantity.\n";
					String  s5="Issued on "+sToday+" ";
					String  s6="for "+dt.Validity+" days ";
					String  s7="valid upto "+sTill+"\n";
//					String spath="C:/Evolute RD Service Tokens Allocation/";
					int confirmbox1 = JOptionPane.showConfirmDialog(frame, "\n"+sa1+sa2+sa3+sa4+s5+s6+s7+"\n", "Confirm Inputs", JOptionPane.YES_NO_OPTION);
					if (confirmbox1==JOptionPane.YES_OPTION){
						Log.disperr("confirmbox Yes option clicked");
						vTokenAllocation(en, dt,spath,txtpnTokenalloc);
						txtfldcompCode.setText("");
						txtfldcompName.setText("");
						txtfldToken.setText("");
						txtfldTokenvalid.setText("");
						//JOptionPane.showConfirmDialog(frame, "Action Complete","Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Token allocation Action complete");
						
					} else {
						return;
					}					
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(frame, "Application Error","Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Application error in token allocation");
					txtpnTokenalloc.setText("Application error in token allocation");
				}
			}
		});
		panel.add(btnAllocateTokens);
		
		//Token allocation text output pane
		txtpnTokenalloc = new JTextPane();
//		txtpnTokenalloc.setTransferHandler(null);
//		txtpnTokenalloc.setText("this is textpane for token allocation");
		txtpnTokenalloc.setForeground(new Color(0, 0, 0));
		txtpnTokenalloc.setEditable(false);
		txtpnTokenalloc.setBackground(SystemColor.control);
		txtpnTokenalloc.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtpnTokenalloc.setBounds(310, 195, 369, 338);
		panel.add(txtpnTokenalloc);
		
		JScrollPane scroll = new JScrollPane(txtpnTokenalloc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(310, 195, 425, 338);
		panel.add(scroll);
		
		
		//Token allocation evolute logo
		final JLabel lblNewLabel_1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/NewEvoLogoTry - Copy.png")));
//		lblNewLabel_1.setIcon(new ImageIcon("images/NewEvoLogoTry - Copy.png"));
		lblNewLabel_1.setBounds(182, 11, 396, 85);
		panel.add(lblNewLabel_1);
		
		JLabel lblTokenAllocation = new JLabel("TOKEN ALLOCATION");
		lblTokenAllocation.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblTokenAllocation.setBounds(216, 124, 313, 31);
		panel.add(lblTokenAllocation);
		
		
		//Token status
		//Token status tab
		JPanel pane2 = new JPanel();
		pane2.setBackground(Color.WHITE);
		tabbedPane.addTab("Token Status", null, pane2, null);
		pane2.setLayout(null);
		
		//Token status token enter field
//		JLabel lblEnterTokenHere = new JLabel("Enter Token Here");
//		lblEnterTokenHere.setFont(new Font("Andalus", Font.BOLD, 14));
//		lblEnterTokenHere.setBounds(21, 247, 133, 31);
//		pane2.add(lblEnterTokenHere);

		tknStsenter = new JTextField();
		tknStsenter.setBounds(117, 249, 175, 31);
		pane2.add(tknStsenter);
		tknStsenter.setColumns(10);
		
		//Token status text pane 
		final JTextPane txtpneTokenstatus = new JTextPane();
		txtpneTokenstatus.setForeground(Color.BLACK);
		txtpneTokenstatus.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtpneTokenstatus.setEditable(false);
		txtpneTokenstatus.setBackground(SystemColor.menu);
		txtpneTokenstatus.setBounds(310, 195, 369, 338);
		pane2.add(txtpneTokenstatus);
		
		JScrollPane scroll2 = new JScrollPane(txtpneTokenstatus,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setBounds(310, 195, 425, 338); 
		pane2.add(scroll2);
		
		//Token status environment selection
		final JComboBox comboBoxstatus = new JComboBox();
		comboBoxstatus.setBounds(164, 195, 127, 31);
		pane2.add(comboBoxstatus);
		comboBoxstatus.addItem("Staging");
		comboBoxstatus.addItem("Pre Production");
		comboBoxstatus.addItem("Production");
		
		JLabel label = new JLabel("Select Environment ");
		label.setFont(new Font("Andalus", Font.BOLD, 14));
		label.setBounds(21, 195, 133, 31);
		pane2.add(label);
		
		//radio btn label token status
		JLabel lbltoken = new JLabel("Token"); // (Single or CSV File)");
		lbltoken.setFont(new Font("Andalus", Font.BOLD, 14));
		lbltoken.setBounds(21, 247, 270, 31);
		pane2.add(lbltoken);
		
//		final JButton bulktokenbtn = new JButton("Choose CSV file");
//		bulktokenbtn.setBackground(SystemColor.textHighlightText);
//		bulktokenbtn.setBounds(116, 331, 175, 31);
//		bulktokenbtn.setEnabled(false);
//		bulktokenbtn.addActionListener(new ActionListener() {
//	            public void actionPerformed(ActionEvent e) {
//	            	JFileChooser fchooser = new JFileChooser();
//	            	fchooser.setFileFilter(new FileNameExtensionFilter("csv files", "csv", "CSV"));
//	        		Log.disperr("File Chooser window in device deregister tab");
//	        		fchooser.setDialogTitle("Select CSV File");	            	
//	            	fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//	            	int csvfile = fchooser.showDialog(frame, "Select");
//	                if (csvfile == JFileChooser.APPROVE_OPTION) {
//	                	try{
//	                		selectedFile = fchooser.getSelectedFile();
//	                		Log.disperr("Selected file is "+ selectedFile.getAbsoluteFile());
//	                		txtpneTokenstatus.setText("Selected file is "+ selectedFile.getAbsoluteFile());
//	                	} catch (Exception e1){
//	                		return;
//	                	} 
//	                }else if (csvfile == JFileChooser.CANCEL_OPTION){
//	                	return;
//	                }
//	            }
//	        });
//		pane2.add(bulktokenbtn);
		
//		//Radio button for token status
//		final JRadioButtonMenuItem singletokenbtn = new JRadioButtonMenuItem("Single", true);
//		singletokenbtn.setBounds(21, 289, 75, 31);
//		singletokenbtn.addActionListener(new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	        	if(singletokenbtn.isSelected()){
//	        		bulktokenbtn.setEnabled(false);
//	        		tknStsenter.setEnabled(true);
//	        		
//	        	}else{
//	        	}
//	        }
//	        });
//		pane2.add(singletokenbtn);
		
//		final JRadioButtonMenuItem multitokenbtnm = new JRadioButtonMenuItem("Multiple", true);
//		multitokenbtnm.setBounds(21, 331, 75, 31);
//		multitokenbtnm.addActionListener(new ActionListener() {
//		        @Override
//		        public void actionPerformed(ActionEvent e) {
//		        	if(multitokenbtnm.isSelected()){
//		        		bulktokenbtn.setEnabled(true);
//		        		tknStsenter.setEnabled(false);
//		        	}else{
//		        	}
//		        }
//		        });
//		pane2.add(multitokenbtnm);
		
		//Group the radio buttons.
//		ButtonGroup group2 = new ButtonGroup();
//		group2.add(singletokenbtn);
//		group2.add(multitokenbtnm);
		
		
				//Token status button
				JButton btnCheckStatus = new JButton("Check Status");
				btnCheckStatus.setBackground(SystemColor.textHighlightText);
				btnCheckStatus.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnCheckStatus.setBounds(79, 305, 155, 41);
				btnCheckStatus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
//						if(singletokenbtn.isSelected()) { //single token status
						Log.disperr("Token status Button clicked");
						String sEnv = (String)comboBoxstatus.getSelectedItem();
						String tok = tknStsenter.getText().replaceAll(" ", "");
						
						if (tok==null||tok.length()!=18){
							JOptionPane.showConfirmDialog(frame, "Invalid Token", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Token Invalid");
							txtpneTokenstatus.setText("Token Invalid");
							return;
						}
						Details dt = new Details();
						dt.sToken = new String(tok);
						mEnv en = mEnv.Staging;
						String se = (String) comboBoxstatus.getSelectedItem();
						
						if (se.equalsIgnoreCase("Staging"))
							en=mEnv.Staging;
						else if (se.equalsIgnoreCase("Pre Production"))
							en=mEnv.Pre_Prod;
						else if (se.equalsIgnoreCase("Production"))
							en=mEnv.Production;
						
						try{
							String  toks="The Token is "+tok+"\n";
							String  envs="with "+se+ " Environment\n";
							
							int confirmbox1 = JOptionPane.showConfirmDialog(frame, "\n"+toks+envs+"\n", "Confirm Inputs", JOptionPane.YES_NO_OPTION);
							if (confirmbox1==JOptionPane.YES_OPTION){
								Log.disperr("confirmbox Yes option clicked");
								vTokenStatus(en, dt, txtpneTokenstatus);
								txtfldcompCode.setText("");
								txtfldcompName.setText("");
								txtfldToken.setText("");
								txtfldTokenvalid.setText("");
								//JOptionPane.showConfirmDialog(frame, "Action Complete","Info", JOptionPane.PLAIN_MESSAGE);
								Log.disperr("Token Status Action complete");
							} else {
								return;
							}					
						} catch (Exception e1) {
							JOptionPane.showConfirmDialog(frame, "Application Error","Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Application error in token Status");
							txtpneTokenstatus.setText("Application error in token Status");
						}
						
						
//					} 
//						else { //multiple token status
//							// TODO Auto-generated method stub
//						Details dt = new Details();
//						Log.displn(" CSV File Way Token status Button clicked ");
//																																
//						String dsEnv = (String) comboBoxstatus.getSelectedItem();
//						//String modId = (String) modelid1.getSelectedItem();
//						
//						Log.displn(" CSV File Way ");
//						dt.blSingleDev = false;
//						dt.sToken = selectedFile.getAbsolutePath();
//						Log.displn(" dt.blSingleDev "+dt.blSingleDev+", dt.sToken "+dt.sToken);
//						
//						String tokenui = selectedFile.getAbsolutePath();	
//						if (tokenui==null || tokenui.length()==0) {
//							JOptionPane.showConfirmDialog(frame, "Invalid Token.", "Info", JOptionPane.PLAIN_MESSAGE);
//							Log.disperr("Token Invalid");
//							txtpneTokenstatus.setText("Token Invalid");
//							return;
//						}
//						try{
//							
//							//Details dt = new Details();
//							//dt.devCode = new String(derrsrno);
////							dt.mi = new String(modId);
//							mEnv en = mEnv.Staging;
//							//mMi model = mMi.LEOPARD;
//							String dse = (String) dsEnv;
//							//String mmi = (String) modId;
//							Log.disperr("confirmbox shown "+dse);
//																					 
//							 
//							if (dse.equalsIgnoreCase("Staging"))
//								en=mEnv.Staging;
//							else if (dse.equalsIgnoreCase("Pre Production"))
//								en=mEnv.Pre_Prod;
//							else if (dse.equalsIgnoreCase("Production"))
//								en=mEnv.Production;
//							
////							if (mmi.equalsIgnoreCase("LEOPARD"))
////								model=mMi.LEOPARD;
////							else if (mmi.equalsIgnoreCase("FALCON"))
////								model=mMi.FALCON;
////							else if (mmi.equalsIgnoreCase("IMPRESS"))
////								model=mMi.IMPRESS;
////							else if (mmi.equalsIgnoreCase("LILY"))
////								model=mMi.LILY;
////							else if (mmi.equalsIgnoreCase("IDENTI5"))
////								model=mMi.IDENTI5;
////							else if (mmi.equalsIgnoreCase("ESPVPOCMI"))
////								model=mMi.ESPVPOCMI;
////							dt.mi=model;
//							
//							File fff = new File(tokenui);
//							FileInputStream fis = new FileInputStream(fff);
//							byte[] bInData = new byte[40];
//							int len = fis.read(bInData);
//							String sReadNow = new String(bInData,0,len);
//							Log.displn(">> sReadNow "+sReadNow);
//							String sFinal = sReadNow.substring(0, sReadNow.indexOf(","));
//							Log.displn(">> sFinal "+sFinal);
//							tokenui = sFinal.toUpperCase().trim();
//							
//							String  envs= "The Environment is " +dse+ "\n"; 
//							//String  modid="The ModelId is " +modId+ " and\n";
//							//String  devsrno= "Device Code : " +derrsrno+"\n";
//							
//							int confirmbox = JOptionPane.showConfirmDialog(frame,"\n"+envs+"\n", "Confirm Inputs", JOptionPane.YES_NO_OPTION);
//							if (confirmbox==JOptionPane.YES_OPTION){
//								Log.disperr("confirmbox Yes option clicked  "+en);
//								//vTokenStatusBulk(en, dt, txtpneTokenstatus);			 
//								//txtpneTokenstatus.setText("");
//								//JOptionPane.showConfirmDialog(frame, "Action Complete","Info", JOptionPane.PLAIN_MESSAGE);
//								Log.disperr("Token Status Action complete");
//							} else {
//								return;
//							}		
////							
//						} catch (Exception we){
//							JOptionPane.showConfirmDialog(frame, "Input File Error "+we.getMessage(), "Info", JOptionPane.PLAIN_MESSAGE);
//							Log.disperr("Input File Error "+we.getMessage());
//							txtpneTokenstatus.setText("Input File Error "+we.getMessage());
//							return;
//						}
//							
//						}
						
					}
				});
				pane2.add(btnCheckStatus);
				
						final JLabel label_3 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/NewEvoLogoTry - Copy.png")));
						label_3.setBounds(182, 11, 396, 85);
						pane2.add(label_3);
						
						JLabel lblTokenStatus = new JLabel("     TOKEN STATUS");
						lblTokenStatus.setFont(new Font("SansSerif", Font.BOLD, 30));
						lblTokenStatus.setBounds(216, 124, 313, 31);
						pane2.add(lblTokenStatus);
		
		
		//Tab Device status
		JPanel pane4 = new JPanel();
		pane4.setBackground(Color.WHITE);
		tabbedPane.addTab("Device Status", null, pane4, null);
		pane4.setLayout(null);
		
		JLabel label_1 = new JLabel("Model ID");
		label_1.setBounds(20, 254, 72, 34);
		label_1.setFont(new Font("Andalus", Font.BOLD, 14));
		pane4.add(label_1);
		
		JLabel label_2 = new JLabel("Serial No");
		label_2.setBounds(20, 316, 72, 34);
		label_2.setFont(new Font("Andalus", Font.BOLD, 14));
		pane4.add(label_2);
		
		JLabel label_4 = new JLabel("Select Environment ");
		label_4.setBounds(20, 195, 133, 34);
		label_4.setFont(new Font("Andalus", Font.BOLD, 14));
		pane4.add(label_4);
		
		final JComboBox modelid = new JComboBox();
		modelid.setBounds(163, 257, 127, 34);
		pane4.add(modelid);
		modelid.addItem("LEOPARD");
		modelid.addItem("FALCON"); 
		modelid.addItem("IMPRESS");
		modelid.addItem("LILY"); 
		modelid.addItem("IDENTI5");
		modelid.addItem("ESPVPOCMI");
		
		final JTextField srno = new JTextField();
		srno.setBounds(163, 317, 127, 34);
		srno.setColumns(10);
		pane4.add(srno); //FEHCA0476
	
		final JComboBox dsenv = new JComboBox();
		dsenv.setBounds(163, 195, 127, 33);
		pane4.add(dsenv);
		dsenv.addItem("Staging");
		dsenv.addItem("Pre Production");
		dsenv.addItem("Production");
		
		final JTextArea dstxtpn = new JTextArea();
		dstxtpn.setEditable(false);
		dstxtpn.setBounds(310, 195, 369, 338);
		dstxtpn.setForeground(Color.BLACK);
		dstxtpn.setFont(new Font("Courier New", Font.PLAIN, 12));
		dstxtpn.setBackground(SystemColor.menu);
//		dstxtpn.setText("START\n\n\n\n\n\nSTART\n\n\n\n\n\nSTART\n\n\n\n\n\nSTART\n\n\n\n\n\nEnd");
		pane4.add(dstxtpn);
		
		JScrollPane scroll1 = new JScrollPane(dstxtpn,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll1.setBounds(310, 195, 425, 338);
		pane4.add(scroll1);
		
		JButton dssubmit = new JButton("Device Status");
		dssubmit.setBackground(SystemColor.textHighlightText);
		dssubmit.setBounds(85, 368, 155, 41);
		dssubmit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dssubmit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Log.disperr("Device status Button clicked");
			String modId = (String) modelid.getSelectedItem();
			String dsEnv = (String) dsenv.getSelectedItem();
			String dssrno = srno.getText().replaceAll(" ", "");
			Log.disperr("Disp modId,dsEnv,dssrno"+modId+dsEnv+dssrno);
			if (dssrno==null || dssrno.length()>12|| dssrno.length()==0){
				JOptionPane.showConfirmDialog(frame, "Invalid Serial No", "Info", JOptionPane.PLAIN_MESSAGE);
				Log.disperr("Serial No Invalid");
				dstxtpn.setText("Serial No Invalid");
				return;
			}
			
			Details dt = new Details();
			dt.devSerNo = new String(dssrno);
			mEnv en = mEnv.Staging;
			mMi model = mMi.LEOPARD;
			String dse = (String) dsEnv;
			String mmi = (String) modId;
			
			if (dse.equalsIgnoreCase("Staging"))
				en=mEnv.Staging;
				
			else if (dse.equalsIgnoreCase("Pre Production"))
				en=mEnv.Pre_Prod;
			else if (dse.equalsIgnoreCase("Production"))
				en=mEnv.Production;
			
			if (mmi.equalsIgnoreCase("LEOPARD"))
				model=mMi.LEOPARD;
			else if (mmi.equalsIgnoreCase("FALCON"))
				model=mMi.FALCON;
			else if (mmi.equalsIgnoreCase("IMPRESS"))
				model=mMi.IMPRESS;
			else if (mmi.equalsIgnoreCase("LILY"))
				model=mMi.LILY;
			else if (mmi.equalsIgnoreCase("IDENTI5"))
				model=mMi.IDENTI5;
			else if (mmi.equalsIgnoreCase("ESPVPOCMI"))
				model=mMi.ESPVPOCMI;
			
			try{
				String  moDIds="The Model id is "+model+"\n";
				String  envs="with "+dse+ " Environment and\n";
				String  devsrno= "Serial no : "+dssrno+"\n";
				
				int confirmbox = JOptionPane.showConfirmDialog(frame,"\n"+moDIds+envs+devsrno+"\n", "Confirm Inputs", JOptionPane.YES_NO_OPTION);
				if (confirmbox==JOptionPane.YES_OPTION){
					Log.disperr("confirmbox Yes option clicked");
					vDeviceStatus(en, model, dt, dstxtpn);
					srno.setText("");
					//JOptionPane.showConfirmDialog(frame, "Action Complete","Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Device Status Action complete");
				} else {
					return;
				}
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(frame, "Application Error","Info", JOptionPane.PLAIN_MESSAGE);
				Log.disperr("Application error in Device Status");
				dstxtpn.setText("Application error in Device Status");
			}
			
		}
	});
		pane4.add(dssubmit);
		
		final JLabel label_5 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/NewEvoLogoTry - Copy.png")));
		label_5.setBounds(182, 11, 396, 85);
		pane4.add(label_5);
		
		JLabel lblDevicestatus = new JLabel("     DEVICE STATUS");
		lblDevicestatus.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblDevicestatus.setBounds(216, 124, 313, 31);
		pane4.add(lblDevicestatus);

		//Tab Device deregister
				JPanel pane7 = new JPanel();
				pane7.setBackground(Color.WHITE);
				tabbedPane.addTab("Device DeRegister", null, pane7, null);
				pane7.setLayout(null);
				
//				JLabel devcode = new JLabel("Device Code");
//				devcode.setBounds(21, 281, 87, 34);
//				devcode.setFont(new Font("Andalus", Font.BOLD, 14));
//				pane7.add(devcode);
				
				JLabel envi = new JLabel("Select Environment ");
				envi.setBounds(21, 195, 133, 34);
				envi.setFont(new Font("Andalus", Font.BOLD, 14));
				pane7.add(envi);

				JLabel mi = new JLabel("Model ID ");
				mi.setBounds(21, 240, 70, 34);
				mi.setFont(new Font("Andalus", Font.BOLD, 14));
				pane7.add(mi);
				
				final JTextField drdevcode = new JTextField();
				drdevcode.setBounds(116, 315, 175, 31);
				drdevcode.setColumns(10);
				pane7.add(drdevcode); //FEHCA0476
			
				final JComboBox dersenv = new JComboBox();
				dersenv.setBounds(164, 197, 127, 33);
				pane7.add(dersenv);
				dersenv.addItem("Staging");
				dersenv.addItem("Pre Production");
				dersenv.addItem("Production");
				
				
				final JComboBox modelid1 = new JComboBox();
				modelid1.setBounds(164, 242, 127, 33);
				modelid1.addItem("LEOPARD");
				modelid1.addItem("FALCON"); 
				modelid1.addItem("IMPRESS");
				modelid1.addItem("LILY"); 
				modelid1.addItem("IDENTI5");
				modelid1.addItem("ESPVPOCMI");
				pane7.add(modelid1);
				
				final JTextArea dertxtpn = new JTextArea();
				dertxtpn.setBounds(310, 195, 369, 338);
				dertxtpn.setForeground(Color.BLACK);
				dertxtpn.setFont(new Font("Courier New", Font.PLAIN, 12));
				dertxtpn.setBackground(SystemColor.menu);
				dertxtpn.setEditable(false);
				dertxtpn.setLineWrap(true);
				pane7.add(dertxtpn);
				
				JScrollPane scroll10 = new JScrollPane(dertxtpn,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll10.setBounds(310, 195, 425, 338);
				pane7.add(scroll10);
				
				

//				final JTextField singledc = new JTextField();
//				singledc.setBounds(126, 485, 175, 31);
//				pane7.add(singledc);
				
				JLabel lbldevicecode = new JLabel("Device Code. (Single or CSV File)");
				lbldevicecode.setFont(new Font("Andalus", Font.BOLD, 14));
				lbldevicecode.setBounds(21, 285, 270, 31);
				pane7.add(lbldevicecode);
				
				final JButton bulkdcbtn = new JButton("Choose CSV file");
				bulkdcbtn.setBackground(SystemColor.textHighlightText);
				bulkdcbtn.setBounds(116, 357, 175, 31);
				bulkdcbtn.setEnabled(false);
				bulkdcbtn.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	JFileChooser fchooser = new JFileChooser();
			            	fchooser.setFileFilter(new FileNameExtensionFilter("csv files", "csv", "CSV"));
			        		Log.disperr("File Chooser window in device deregister tab");
			        		fchooser.setDialogTitle("Select CSV File");	            	
			            	fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			            	int csvfile = fchooser.showDialog(frame, "Select");
			                if (csvfile == JFileChooser.APPROVE_OPTION) {
			                	try{
			                		selectedFile = fchooser.getSelectedFile();
			                		Log.disperr("Selected file is "+ selectedFile.getAbsoluteFile());
			                		dertxtpn.setText("Selected file is "+ selectedFile.getAbsoluteFile());
			                	} catch (Exception e1){
			                		return;
			                	} 
			                }else if (csvfile == JFileChooser.CANCEL_OPTION){
			                	return;
			                }
			            }
			        });
				pane7.add(bulkdcbtn);
				
				//Radio button for deregister
				final JRadioButtonMenuItem dregsradbtn = new JRadioButtonMenuItem("Single", true);
				dregsradbtn.setBounds(21, 315, 75, 31);
				dregsradbtn.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	if(dregsradbtn.isSelected()){
			        		bulkdcbtn.setEnabled(false);
			        		drdevcode.setEnabled(true);
			        		
			        	}else{
			        	}
			        }
			        });
				pane7.add(dregsradbtn);
				
				final JRadioButtonMenuItem deregbradbtnm = new JRadioButtonMenuItem("Multiple", true);
				deregbradbtnm.setBounds(21, 357, 75, 31);
				deregbradbtnm.addActionListener(new ActionListener() {
				        @Override
				        public void actionPerformed(ActionEvent e) {
				        	if(deregbradbtnm.isSelected()){
				        		bulkdcbtn.setEnabled(true);
				        		drdevcode.setEnabled(false);
				        	}else{
				        	}
				        }
				        });
				pane7.add(deregbradbtnm);
				
				//Group the radio buttons.
				ButtonGroup group = new ButtonGroup();
				group.add(dregsradbtn);
				group.add(deregbradbtnm);
				
				
				//deregister submit button
				JButton dersubmit = new JButton("Device De-Register");
				dersubmit.setBackground(SystemColor.textHighlightText);
				dersubmit.setBounds(81, 412, 155, 41);
				dersubmit.setFont(new Font("Tahoma", Font.PLAIN, 11));
				dersubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if(dregsradbtn.isSelected()) { //if only single device is selected
					Log.disperr("Device Deregister Button clicked with single radio button");
					String dsEnv = (String) dersenv.getSelectedItem();
					String modId = (String) modelid1.getSelectedItem();
					String derrsrno = drdevcode.getText().replaceAll(" ", "");
					
					if (derrsrno==null || derrsrno.length()!=36){
						JOptionPane.showConfirmDialog(frame, "Invalid Device Code", "Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Device Code Invalid");
						dertxtpn.setText("Device Code Invalid");
						return;
					}
					Details dt = new Details();
					
					dt.devCode = new String(derrsrno);
//					dt.mi = new String(modId);
					mEnv en = mEnv.Staging;
					mMi model = mMi.LEOPARD;
					String dse = (String) dsEnv;
					String mmi = (String) modId;
					Log.disperr("confirmbox Yes option clicked 1 "+dse+mmi);
					if (dse.equalsIgnoreCase("Staging"))
						en=mEnv.Staging;
						
					else if (dse.equalsIgnoreCase("Pre Production"))
						en=mEnv.Pre_Prod;
					else if (dse.equalsIgnoreCase("Production"))
						en=mEnv.Production;
					
					if (mmi.equalsIgnoreCase("LEOPARD"))
						model=mMi.LEOPARD;
					else if (mmi.equalsIgnoreCase("FALCON"))
						model=mMi.FALCON;
					else if (mmi.equalsIgnoreCase("IMPRESS"))
						model=mMi.IMPRESS;
					else if (mmi.equalsIgnoreCase("LILY"))
						model=mMi.LILY;
					else if (mmi.equalsIgnoreCase("IDENTI5"))
						model=mMi.IDENTI5;
					else if (mmi.equalsIgnoreCase("ESPVPOCMI"))
						model=mMi.ESPVPOCMI;
					dt.mi=model;
					try{
						String  envs= "The Environment is " +dse+ " and\n"; 
						String  modid="The ModelId is " +modId+ " and\n";
						String  devsrno= "Device Code : " +derrsrno+"\n";
						
						int confirmbox = JOptionPane.showConfirmDialog(frame,"\n"+envs+modid+devsrno+"\n", "Confirm Inputs", JOptionPane.YES_NO_OPTION);
						if (confirmbox==JOptionPane.YES_OPTION){
							Log.disperr("confirmbox Yes option clicked 1 "+en+model);
							vDeRegister(en, dt, dertxtpn);
							//dertxtpn.setText("");
							//JOptionPane.showConfirmDialog(frame, "Action Complete","Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Device De Register Action complete");
						} else {
							return;
						}					
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(frame, "Application Error","Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Application error in Device De Register");
						dertxtpn.setText("Application error in Device De Register");
					}
					
				} else if(deregbradbtnm.isSelected()) { //multiple radio button is clicked
					Details dt = new Details();
					Log.displn(" CSV File Way deregister button clicked");
					String dsEnv = (String) dersenv.getSelectedItem();
					String modId = (String) modelid1.getSelectedItem();
					
					Log.displn(" CSV File Way ");
					dt.blSingleDev = false;
					dt.devSerNo = selectedFile.getAbsolutePath();
					Log.displn(" dt.blSingleDev "+dt.blSingleDev+", dt.devserno "+dt.devSerNo);
					
					String sSerNoUI = selectedFile.getAbsolutePath();	
					if (sSerNoUI==null || sSerNoUI.length()==0) {
						JOptionPane.showConfirmDialog(frame, "Invalid Device code.", "Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Device code Invalid");
						dertxtpn.setText("Device code Invalid");
						return;
					}
					try{
						
						//Details dt = new Details();
						
						//dt.devCode = new String(derrsrno);
//						dt.mi = new String(modId);
						mEnv en = mEnv.Staging;
						mMi model = mMi.LEOPARD;
						String dse = (String) dsEnv;
						String mmi = (String) modId;
						Log.disperr("confirmbox Yes option clicked 1 "+dse+mmi);
						if (dse.equalsIgnoreCase("Staging"))
							en=mEnv.Staging;
							
						else if (dse.equalsIgnoreCase("Pre Production"))
							en=mEnv.Pre_Prod;
						else if (dse.equalsIgnoreCase("Production"))
							en=mEnv.Production;
						
						if (mmi.equalsIgnoreCase("LEOPARD"))
							model=mMi.LEOPARD;
						else if (mmi.equalsIgnoreCase("FALCON"))
							model=mMi.FALCON;
						else if (mmi.equalsIgnoreCase("IMPRESS"))
							model=mMi.IMPRESS;
						else if (mmi.equalsIgnoreCase("LILY"))
							model=mMi.LILY;
						else if (mmi.equalsIgnoreCase("IDENTI5"))
							model=mMi.IDENTI5;
						else if (mmi.equalsIgnoreCase("ESPVPOCMI"))
							model=mMi.ESPVPOCMI;
						dt.mi=model;
						
						File fff = new File(sSerNoUI);
						FileInputStream fis = new FileInputStream(fff);
						byte[] bInData = new byte[40];
						int len = fis.read(bInData);
						String sReadNow = new String(bInData,0,len);
						Log.displn(">> sReadNow "+sReadNow);
						String sFinal = sReadNow.substring(0, sReadNow.indexOf(","));
						Log.displn(">> sFinal "+sFinal);
						sSerNoUI = sFinal.toUpperCase().trim();
						
						String  envs= "The Environment is " +dse+ " and\n"; 
						String  modid="The ModelId is " +modId+ " and\n";
						//String  devsrno= "Device Code : " +derrsrno+"\n";
						
						int confirmbox = JOptionPane.showConfirmDialog(frame,"\n"+envs+modid+"\n", "Confirm Inputs", JOptionPane.YES_NO_OPTION);
						if (confirmbox==JOptionPane.YES_OPTION){
							Log.disperr("confirmbox Yes option clicked  "+en+model);
							vDeRegBulk(en, dt, dertxtpn);
							//dertxtpn.setText("");
							//JOptionPane.showConfirmDialog(frame, "Action Complete","Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Device De Register Action complete");
						} else {
							return;
						}		
						
					} catch (Exception we){
						JOptionPane.showConfirmDialog(frame, "Input File Error "+we.getMessage(), "Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Input File Error "+we.getMessage());
						dertxtpn.setText("Input File Error "+we.getMessage());
						return;
					}
					
					
					
					
					
				}
				}
			});
				pane7.add(dersubmit);
				
				final JLabel logo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/NewEvoLogoTry - Copy.png")));
				logo.setBounds(182, 11, 396, 85);
				pane7.add(logo);
				
				JLabel lblDeviceDeregister = new JLabel("DEVICE DE-REGISTER");
				lblDeviceDeregister.setFont(new Font("SansSerif", Font.BOLD, 30));
				lblDeviceDeregister.setBounds(212, 124, 325, 31);
				pane7.add(lblDeviceDeregister);
		
		
		

		//Tab Device Whitelist
		JPanel pane3 = new JPanel();
		pane3.setBackground(Color.WHITE);
		tabbedPane.addTab("Device WhiteList", null, pane3, null);
		pane3.setLayout(null);
		
		//Device whitelist evolute logo
		final JLabel lblNewLabel_2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/NewEvoLogoTry - Copy.png")));
//		lblNewLabel_1.setIcon(new ImageIcon("images/NewEvoLogoTry - Copy.png"));
		lblNewLabel_2.setBounds(182, 11, 396, 85);
		pane3.add(lblNewLabel_2);
		
		JLabel label_7 = new JLabel("Select Environment ");
		label_7.setFont(new Font("Andalus", Font.BOLD, 14));
		label_7.setBounds(21, 195, 127, 31);
		pane3.add(label_7);
		
		final JComboBox wlenv = new JComboBox();
		wlenv.setBounds(164, 196, 127, 31);
		pane3.add(wlenv);
		wlenv.addItem("Staging");
		wlenv.addItem("Pre Production");
		wlenv.addItem("Production");
		
		JLabel lblModelId = new JLabel("Model ID");
		lblModelId.setFont(new Font("Andalus", Font.BOLD, 14));
		lblModelId.setBounds(21, 300, 133, 31);
		pane3.add(lblModelId);
		final JComboBox dwmodelid = new JComboBox();
		dwmodelid.setBounds(164, 301, 127, 31);
		pane3.add(dwmodelid);
		dwmodelid.addItem("LEOPARD");
		dwmodelid.addItem("FALCON"); 
		dwmodelid.addItem("IMPRESS");
		dwmodelid.addItem("LILY"); 
		dwmodelid.addItem("IDENTI5");
		dwmodelid.addItem("ESPVPOCMI");
		
		//whitelist tab output text pane
		final JTextPane tvDeviceCode = new JTextPane();
		tvDeviceCode.setFont(new Font("Courier New", Font.BOLD, 12));
		tvDeviceCode.setBackground(SystemColor.menu);
		tvDeviceCode.setBounds(310, 195, 369, 338);
		tvDeviceCode.setEditable(false);
		pane3.add(tvDeviceCode);
		
		JScrollPane scroll6 = new JScrollPane(tvDeviceCode,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll6.setBounds(310, 195, 425, 338); 
		pane3.add(scroll6);
		
		JLabel lbldc = new JLabel("Company Code");
		lbldc.setFont(new Font("Andalus", Font.BOLD, 14));
		lbldc.setBounds(21, 249, 133, 31);
		pane3.add(lbldc);
		
		final JTextField comcode = new JTextField();
		comcode.setBounds(164, 250, 127, 31);
		pane3.add(comcode);
		comcode.setColumns(10);
		if (comcode.getText().length() == 3) {
		   
		} else {
			
		} 

		final JTextField tvSerialName = new JTextField();
		tvSerialName.setBounds(116, 372, 175, 31);

		pane3.add(tvSerialName);

		JLabel lblDeviceSerialNo = new JLabel("Device Serial No. (Single or CSV File)");
		lblDeviceSerialNo.setFont(new Font("Andalus", Font.BOLD, 14));
		lblDeviceSerialNo.setBounds(21, 342, 270, 31);
		pane3.add(lblDeviceSerialNo);
		
		final JButton fcbtn = new JButton("Choose CSV file");
		fcbtn.setBackground(SystemColor.textHighlightText);
		fcbtn.setBounds(116, 414, 175, 31);
		fcbtn.setEnabled(false);
		fcbtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	JFileChooser chooser1 = new JFileChooser();
	            	chooser1.setFileFilter(new FileNameExtensionFilter("csv files", "csv", "CSV"));
	        		Log.disperr("File Chooser window in device whitelist tab");
	        		chooser1.setDialogTitle("Select CSV File");	            	
	            	chooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
	            	int csvfile = chooser1.showDialog(frame, "Select");
	                if (csvfile == JFileChooser.APPROVE_OPTION) {
	                	try{
	                		selectedFile = chooser1.getSelectedFile();
	                		Log.disperr("Selected file is "+ selectedFile.getAbsoluteFile());
	                		tvDeviceCode.setText("Selected file is "+ selectedFile.getAbsoluteFile());
	                	} catch (Exception e1){
	                		return;
	                	} 
	                }else if (csvfile == JFileChooser.CANCEL_OPTION){
	                	return;
	                }
	            }
	        });
		pane3.add(fcbtn);
		
		//Radio button for whitelist
		final JRadioButtonMenuItem wlradbtn = new JRadioButtonMenuItem("Single", true);
		wlradbtn.setBounds(21, 372, 75, 31);
		wlradbtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(wlradbtn.isSelected()){
	        		fcbtn.setEnabled(false);
	        		tvSerialName.setEnabled(true);
	        		
	        	}else{
	        	}
	        }
	        });
		pane3.add(wlradbtn);
		
		final JRadioButtonMenuItem wlradbtnm = new JRadioButtonMenuItem("Multiple", true);
		wlradbtnm.setBounds(21, 414, 75, 31);
		wlradbtnm.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	if(wlradbtnm.isSelected()){
		        		fcbtn.setEnabled(true);
		        		tvSerialName.setEnabled(false);
		        	}else{
		        	}
		        }
		        });
		pane3.add(wlradbtnm);
		
		//Group the radio buttons.
		ButtonGroup dereggroup = new ButtonGroup();
		dereggroup.add(wlradbtn);
		dereggroup.add(wlradbtnm);
		


		JButton drsub = new JButton("WhiteList Device's");
		drsub.setBackground(SystemColor.textHighlightText);
		drsub.setBounds(81, 492, 155, 41);
//		Log.disperr("Device Whitelist Submit button clicked");
		drsub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Log.disperr("Device whitelist Button clicked");
				String modId = (String) dwmodelid.getSelectedItem();
				String dsEnv = (String) wlenv.getSelectedItem();
				String sCodeUI = (String) comcode.getText().replaceAll(" ", ""); 
				String dse = (String) dsEnv;
				
				Log.displn("?? modid "+modId+", dsEnv "+dsEnv+", sCodeUI "+sCodeUI);
				String mmi = modId;
				String custcode = comcode.getText().replaceAll(" ", "");
				Details dt = new Details();
				boolean blSelectedNow = wlradbtn.isSelected();
				Log.displn(" blSelectedNow "+blSelectedNow);
				
				if(sCodeUI.length()!=3){
					JOptionPane.showConfirmDialog(frame, "Invalid Company Code length", "Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Invalid Company Code length");
					dstxtpn.setText("Invalid Company Code length");
					return;
				}
				
				mEnv en = mEnv.Staging;
				if (dse.equalsIgnoreCase("Staging"))
					en=mEnv.Staging;
				else if (dse.equalsIgnoreCase("Pre Production"))
					en=mEnv.Pre_Prod;
				else if (dse.equalsIgnoreCase("Production"))
					en=mEnv.Production;
				
				
				mMi model = mMi.LEOPARD;
				if (mmi.equalsIgnoreCase("LEOPARD"))
					model=mMi.LEOPARD;
				else if (mmi.equalsIgnoreCase("FALCON"))
					model=mMi.FALCON;
				else if (mmi.equalsIgnoreCase("IMPRESS"))
					model=mMi.IMPRESS;
				else if (mmi.equalsIgnoreCase("LILY"))
					model=mMi.LILY;
				else if (mmi.equalsIgnoreCase("IDENTI5"))
					model=mMi.IDENTI5;
				else if (mmi.equalsIgnoreCase("ESPVPOCMI"))
					model= mMi.ESPVPOCMI;
				
//				if (en==mEnv.Staging && !(model==mMi.ESPVPOCMI)) {
//					JOptionPane.showConfirmDialog(frame, "Invalid Model Selection for Staging ", "Info", JOptionPane.PLAIN_MESSAGE);
//					Log.disperr("Invalid Model Selection for Staging ");
//					dstxtpn.setText("Invalid Model Selection for Staging ");
//					return;
//				}
				dt.Code = sCodeUI;
				dt.mi = model;
				if (blSelectedNow) {
					Log.displn(" Single Device Way ");
					String sSerNoUI = tvSerialName.getText().replaceAll(" ", "");	
					if (sSerNoUI==null || sSerNoUI.length()==0) {
						JOptionPane.showConfirmDialog(frame, "Invalid Serial No.", "Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Serial No Invalid");
						dstxtpn.setText("Serial No Invalid");
						return;
					}
					Log.displn("UI Inputs : "+sSerNoUI);
					if (model==mMi.LEOPARD) {
						
						if (!sSerNoUI.startsWith("L")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("L"))
							sSerNoUI = sSerNoUI.replaceFirst("L", "LEP");
						if (!sSerNoUI.startsWith("LEP")){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.FALCON) {
						if (!sSerNoUI.startsWith("F")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("F"))
							sSerNoUI = sSerNoUI.replaceFirst("F", "FIMP");
						if (!sSerNoUI.startsWith("FIMP")){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.IMPRESS) {
						if (!sSerNoUI.startsWith("I")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
							sSerNoUI = sSerNoUI.replaceFirst("I", "IMP");
						if (!sSerNoUI.startsWith("IMP")){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.LILY) {
						if (!sSerNoUI.startsWith("L") && !sSerNoUI.startsWith("G")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("G"))
							sSerNoUI = sSerNoUI.replaceFirst("G", "LIMP");
						if (!sSerNoUI.startsWith("LIMP")){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.IDENTI5) {
						if (!sSerNoUI.startsWith("I")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
							sSerNoUI = sSerNoUI.replaceFirst("I", "IIMP");
						if (!sSerNoUI.startsWith("IIMP")){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.ESPVPOCMI) {
						// DONT CARE
					}
						
					dt.blSingleDev = true;
					dt.devSerNo = sSerNoUI;
					
					Log.displn(" dt.blSingleDev "+dt.blSingleDev+", dt.devserno "+dt.devSerNo);
					
				} else {////////////////// MULTIPLE CSV
					
					Log.displn(" CSV File Way ");
					dt.blSingleDev = false;
					dt.devSerNo = selectedFile.getAbsolutePath(); 
					Log.displn(" dt.blSingleDev "+dt.blSingleDev+", dt.devserno "+dt.devSerNo);
					
					String sSerNoUI = selectedFile.getAbsolutePath();	
					if (sSerNoUI==null || sSerNoUI.length()==0) {
						JOptionPane.showConfirmDialog(frame, "Invalid Serial No.", "Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Serial No Invalid");
						dstxtpn.setText("Serial No Invalid");
						return;
					}
					try{
						File fff = new File(sSerNoUI);
						FileInputStream fis = new FileInputStream(fff);
						byte[] bInData = new byte[20];
						int len = fis.read(bInData);
						String sReadNow = new String(bInData,0,len);
						Log.displn(">> sReadNow "+sReadNow);
						String sFinal = sReadNow.substring(0, sReadNow.indexOf(","));
						Log.displn(">> sFinal "+sFinal);
						sSerNoUI = sFinal.toUpperCase().trim();
					} catch (Exception we){
						JOptionPane.showConfirmDialog(frame, "Input File Error "+we.getMessage(), "Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Input File Error "+we.getMessage());
						dstxtpn.setText("Input File Error "+we.getMessage());
						return;
					}
					if (model==mMi.LEOPARD) {
						
						if (!sSerNoUI.startsWith("L")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("L"))
							sSerNoUI.replaceFirst("L", "LEP");
						
						if (!sSerNoUI.startsWith("LEP")||sSerNoUI.length()!=11){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.FALCON) {
						if (!sSerNoUI.startsWith("F")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("F"))
							sSerNoUI.replaceFirst("F", "FIMP");

						if (!sSerNoUI.startsWith("FIMP")||sSerNoUI.length()!=12){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.IMPRESS) {
						if (!sSerNoUI.startsWith("I")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
							sSerNoUI.replaceFirst("I", "IMP");
						if (!sSerNoUI.startsWith("IMP")||sSerNoUI.length()!=11){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.LILY) {
						if (!sSerNoUI.startsWith("L") && !sSerNoUI.startsWith("G")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("G"))
							sSerNoUI.replaceFirst("G", "LIMP");
						
						if (!sSerNoUI.startsWith("LIMP")||sSerNoUI.length()!=12){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.IDENTI5) {
						if (!sSerNoUI.startsWith("I")) {
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
						if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
							sSerNoUI.replaceFirst("I", "IIMP");

						if (!sSerNoUI.startsWith("IIMP")||sSerNoUI.length()!=12){
							JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							dstxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
							return;
						}
					} else if (model==mMi.ESPVPOCMI) {
						// DONT CARE
					}
				}
				try{
					boolean blSingleDev = true;
					String  moDIds="The Model id is "+modId+"\n";
					String  envs=" with "+dse+ " Environment and\n";
					String ccode = "the customer code is "+dt.Code+"\n";
					String  devsrno= "Serial no/File : "+dt.devSerNo+"\n";
					Log.displn("Inputs Now "+model+envs+ccode+devsrno);
					int confirmbox = JOptionPane.showConfirmDialog(frame,"\n"+model+envs+ccode+devsrno+"\n", "Confirm Inputs", JOptionPane.YES_NO_OPTION);
					if (confirmbox==JOptionPane.YES_OPTION){
						Log.disperr("confirmbox Yes option clicked");
						vWhiteList(en, dt, tvDeviceCode);
//						srno.setText("");
						//JOptionPane.showConfirmDialog(frame, "Action Complete","Info", JOptionPane.PLAIN_MESSAGE);
						Log.disperr("Device Whitelist Action complete");
					} else {
						return;
					}					
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(frame, "Application Error","Info", JOptionPane.PLAIN_MESSAGE);
					Log.disperr("Application error in Device Whitelist");
					tvDeviceCode.setText("Application error in Device Whitelist");
				}
				
			}
		});
		pane3.add(drsub);
		
		JLabel lblDeviceWhitelist = new JLabel("  DEVICE WHITELIST");
		lblDeviceWhitelist.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblDeviceWhitelist.setBounds(216, 124, 313, 31);
		pane3.add(lblDeviceWhitelist);

		//Tab Device De-Whitelist
				JPanel pane6 = new JPanel();
				pane6.setBackground(Color.WHITE);
				tabbedPane.addTab("Device De-WhiteList", null, pane6, null);
				pane6.setLayout(null);
				
				//Device dewhitelist evolute logo
				final JLabel lblNewLabel_3 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/NewEvoLogoTry - Copy.png")));
//				lblNewLabel_3.setIcon(new ImageIcon("images/NewEvoLogoTry - Copy.png"));
				lblNewLabel_3.setBounds(182, 11, 396, 85);
				pane6.add(lblNewLabel_3);
				
				//dewhitelist tab output text pane
				final JTextPane dwltxtpn = new JTextPane();
				dwltxtpn.setFont(new Font("Courier New", Font.BOLD, 12));
				dwltxtpn.setBackground(SystemColor.menu);
				dwltxtpn.setBounds(310, 195, 369, 338);
				dwltxtpn.setEditable(false);
				pane6.add(dwltxtpn);
				JScrollPane scroll5 = new JScrollPane(dwltxtpn,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll5.setBounds(310, 195, 425, 338); 
				pane6.add(scroll5);
				
				JLabel dwenv = new JLabel("Select Environment ");
				dwenv.setFont(new Font("Andalus", Font.BOLD, 14));
				dwenv.setBounds(21, 195, 127, 31);
				pane6.add(dwenv);
				final JComboBox dwdenv = new JComboBox();
				dwdenv.setBounds(164, 196, 127, 31);
				pane6.add(dwdenv);
				dwdenv.addItem("Staging");
				dwdenv.addItem("Pre Production");
				dwdenv.addItem("Production");
				
				JLabel dwlblModelId = new JLabel("Model ID");
				dwlblModelId.setFont(new Font("Andalus", Font.BOLD, 14));
				dwlblModelId.setBounds(21, 243, 133, 31);
				pane6.add(dwlblModelId);
				
				final JComboBox dwlmodelid = new JComboBox();
				dwlmodelid.setBounds(164, 244, 127, 31);
				pane6.add(dwlmodelid);
				dwlmodelid.addItem("LEOPARD");
				dwlmodelid.addItem("FALCON"); 
				dwlmodelid.addItem("IMPRESS");
				dwlmodelid.addItem("LILY"); 
				dwlmodelid.addItem("IDENTI5");
				dwlmodelid.addItem("ESPVPOCMI");
				 
				final JTextField dwlsrno = new JTextField();
				dwlsrno.setBounds(116, 315, 175, 31);
				
				pane6.add(dwlsrno);
				
				JLabel dwllblDeviceSerialNo = new JLabel("Device Serial No. (Single or CSV File)");
				dwllblDeviceSerialNo.setFont(new Font("Andalus", Font.BOLD, 14));
				dwllblDeviceSerialNo.setBounds(21, 285, 270, 31);
				pane6.add(dwllblDeviceSerialNo);
				
				final JButton dwfcbtn = new JButton("Choose CSV file");
				dwfcbtn.setBackground(SystemColor.textHighlightText);
				dwfcbtn.setBounds(116, 357, 175, 31);
				dwfcbtn.setEnabled(false);
				dwfcbtn.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	JFileChooser chooser2 = new JFileChooser();
			            	chooser2.setFileFilter(new FileNameExtensionFilter("csv files", "csv", "CSV"));
			        		Log.disperr("File Chooser window in device De-WhiteList tab");
			        		chooser2.setDialogTitle("Select CSV File");	            	
			        		chooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			            	int csvfile = chooser2.showDialog(frame, "Select");
			                if (csvfile == JFileChooser.APPROVE_OPTION) {
			                	try{
			                		File selectedFile1 = chooser2.getSelectedFile();
			                		Log.disperr("Selected file is "+ selectedFile1);
			                		dwltxtpn.setText("Selected file is "+ selectedFile1);
			                	} catch (Exception e1){
			                		return;
			                	}
			                    
			                }else if (csvfile == JFileChooser.CANCEL_OPTION){
			                	return;
			                }          
			
			            }
			        });
				pane6.add(dwfcbtn);
				
				//Radio button for dewhitelist
				final JRadioButtonMenuItem dwlradbtn = new JRadioButtonMenuItem("Single", true);
				dwlradbtn.setBounds(21, 315, 75, 31);
				dwlradbtn.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	if(dwlradbtn.isSelected()){
			        		dwfcbtn.setEnabled(false);
			        		dwlsrno.setEnabled(true);
			        		
			        	}else{
			        		
			        	}
			        }
			        });
				pane6.add(dwlradbtn);
				
				final JRadioButtonMenuItem dwlradbtnm = new JRadioButtonMenuItem("Multiple", true);
				dwlradbtnm.setBounds(21, 357, 75, 31);
				dwlradbtnm.addActionListener(new ActionListener() {
				        @Override
				        public void actionPerformed(ActionEvent e) {
				        	if(dwlradbtnm.isSelected()){
				        		dwfcbtn.setEnabled(true);
				        		dwlsrno.setEnabled(false);
				        	}else{
				        	}
				        }
				        });
				pane6.add(dwlradbtnm);
				
				//Group the radio buttons.
				ButtonGroup group1 = new ButtonGroup();
				group1.add(dwlradbtn);
				group1.add(dwlradbtnm);
				
				JButton dwsub = new JButton("Device De-Whitelist");
				dwsub.setBackground(SystemColor.textHighlightText);
				dwsub.setBounds(81, 412, 155, 41);
//				Log.disperr("Device DeWhitelist Submit button clicked");
				dwsub.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Log.disperr("Device Dewhitelist Button clicked");
						String modId = (String) dwlmodelid.getSelectedItem();
						String dsEnv = (String) dwdenv.getSelectedItem();
						String sCodeUI = (String) dwlsrno.getText().replaceAll(" ", ""); 
						String dse = (String) dsEnv;
						
						Log.displn("?? modid "+modId+", dsEnv "+dsEnv);
						String mmi = modId;
//						String custcode = comcode.getText().replaceAll(" ", "");
						Details dt = new Details();
						boolean blSelectedNow = dwlradbtn.isSelected();
						Log.displn(" blSelectedNow "+blSelectedNow);
						
//						if(sCodeUI.length()!=3){
//							JOptionPane.showConfirmDialog(frame, "Invalid Company Code length", "Info", JOptionPane.PLAIN_MESSAGE);
//							Log.disperr("Invalid Company Code length");
//							dstxtpn.setText("Invalid Company Code length");
//							return;
//						}
						
						mEnv en = mEnv.Staging;
						if (dse.equalsIgnoreCase("Staging"))
							en=mEnv.Staging;
						else if (dse.equalsIgnoreCase("Pre Production"))
							en=mEnv.Pre_Prod;
						else if (dse.equalsIgnoreCase("Production"))
							en=mEnv.Production;
						
						
						mMi model = mMi.LEOPARD;
						if (mmi.equalsIgnoreCase("LEOPARD"))
							model=mMi.LEOPARD;
						else if (mmi.equalsIgnoreCase("FALCON"))
							model=mMi.FALCON;
						else if (mmi.equalsIgnoreCase("IMPRESS"))
							model=mMi.IMPRESS;
						else if (mmi.equalsIgnoreCase("LILY"))
							model=mMi.LILY;
						else if (mmi.equalsIgnoreCase("IDENTI5"))
							model=mMi.IDENTI5;
						else if (mmi.equalsIgnoreCase("ESPVPOCMI"))
							model= mMi.ESPVPOCMI;
						
//						if (en==mEnv.Staging && !(model==mMi.ESPVPOCMI)) {
//							JOptionPane.showConfirmDialog(frame, "Invalid Model Selection for Staging ", "Info", JOptionPane.PLAIN_MESSAGE);
//							Log.disperr("Invalid Model Selection for Staging ");
//							dwltxtpn.setText("Invalid Model Selection for Staging ");
//							return;
//						}
						
						dt.mi = model;
						if (blSelectedNow) {
							Log.displn(" Single Device Way ");
							String sSerNoUI = dwlsrno.getText().replaceAll(" ", "");	
							if (sSerNoUI==null || sSerNoUI.length()==0) {
								JOptionPane.showConfirmDialog(frame, "Invalid Serial No.", "Info", JOptionPane.PLAIN_MESSAGE);
								Log.disperr("Serial No Invalid");
								dwltxtpn.setText("Serial No Invalid");
								return;
							}
							Log.displn("UI Inputs : "+sSerNoUI);
							if (model==mMi.LEOPARD) {
								
								if (!sSerNoUI.startsWith("L")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("L"))
									sSerNoUI = sSerNoUI.replaceFirst("L", "LEP");
								if (!sSerNoUI.startsWith("LEP")){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.FALCON) {
								if (!sSerNoUI.startsWith("F")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("F"))
									sSerNoUI = sSerNoUI.replaceFirst("F", "FIMP");
								if (!sSerNoUI.startsWith("FIMP")){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.IMPRESS) {
								if (!sSerNoUI.startsWith("I")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
									sSerNoUI = sSerNoUI.replaceFirst("I", "IMP");
								if (!sSerNoUI.startsWith("IMP")){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.LILY) {
								if (!sSerNoUI.startsWith("L") && !sSerNoUI.startsWith("G")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("G"))
									sSerNoUI = sSerNoUI.replaceFirst("G", "LIMP");
								if (!sSerNoUI.startsWith("LIMP")){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.IDENTI5) {
								if (!sSerNoUI.startsWith("I")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
									sSerNoUI = sSerNoUI.replaceFirst("I", "IIMP");
								if (!sSerNoUI.startsWith("IIMP")){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.ESPVPOCMI) {
								// DONT CARE
							}
								
							dt.blSingleDev = true;
							dt.devSerNo = sSerNoUI;
							
							Log.displn(" dt.blSingleDev "+dt.blSingleDev+", dt.devserno "+dt.devSerNo);
							
						} else {////////////////// MULTIPLE CSV
							
							Log.displn(" CSV File Way ");
							dt.blSingleDev = false;
							dt.devSerNo = selectedFile.getAbsolutePath();
							Log.displn(" dt.blSingleDev "+dt.blSingleDev+", dt.devserno "+dt.devSerNo);
							
							String sSerNoUI = selectedFile.getAbsolutePath();	
							if (sSerNoUI==null || sSerNoUI.length()==0) {
								JOptionPane.showConfirmDialog(frame, "Invalid Serial No.", "Info", JOptionPane.PLAIN_MESSAGE);
								Log.disperr("Serial No Invalid");
								dwltxtpn.setText("Serial No Invalid");
								return;
							}
							try{
								File fff = new File(sSerNoUI);
								FileInputStream fis = new FileInputStream(fff);
								byte[] bInData = new byte[20];
								int len = fis.read(bInData);
								String sReadNow = new String(bInData,0,len);
								Log.displn(">> sReadNow "+sReadNow);
								String sFinal = sReadNow.substring(0, sReadNow.indexOf(","));
								Log.displn(">> sFinal "+sFinal);
								sSerNoUI = sFinal.toUpperCase().trim();
							} catch (Exception we){
								JOptionPane.showConfirmDialog(frame, "Input File Error "+we.getMessage(), "Info", JOptionPane.PLAIN_MESSAGE);
								Log.disperr("Input File Error "+we.getMessage());
								dwltxtpn.setText("Input File Error "+we.getMessage());
								return;
							}
							if (model==mMi.LEOPARD) {
								
								if (!sSerNoUI.startsWith("L")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("L"))
									sSerNoUI.replaceFirst("L", "LEP");
								
								if (!sSerNoUI.startsWith("LEP")||sSerNoUI.length()!=11){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.FALCON) {
								if (!sSerNoUI.startsWith("F")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("F"))
									sSerNoUI.replaceFirst("F", "FIMP");

								if (!sSerNoUI.startsWith("FIMP")||sSerNoUI.length()!=12){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.IMPRESS) {
								if (!sSerNoUI.startsWith("I")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
									sSerNoUI.replaceFirst("I", "IMP");
								if (!sSerNoUI.startsWith("IMP")||sSerNoUI.length()!=11){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.LILY) {
								if (!sSerNoUI.startsWith("L") && !sSerNoUI.startsWith("G")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("G"))
									sSerNoUI.replaceFirst("G", "LIMP");

								if (!sSerNoUI.startsWith("LIMP")||sSerNoUI.length()!=12){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.IDENTI5) {
								if (!sSerNoUI.startsWith("I")) {
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
								if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
									sSerNoUI.replaceFirst("I", "IIMP");

								if (!sSerNoUI.startsWith("IIMP")||sSerNoUI.length()!=12){
									JOptionPane.showConfirmDialog(frame, "Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match", "Info", JOptionPane.PLAIN_MESSAGE);
									Log.disperr("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									dwltxtpn.setText("Input Serial Number "+sSerNoUI+" and Selected Model "+model+" doesnot match");
									return;
								}
							} else if (model==mMi.ESPVPOCMI) {
								// DONT CARE
							}
						}
						try{
							boolean blSingleDev = true;
							String  moDIds="The Model id is "+modId+"\n";
							String  envs=" with "+dse+ " Environment and\n";
//							String ccode = "the customer code is "+custcode+"\n";
							String  devsrno= "Serial no : "+dt.devSerNo+"\n";
//							Log.displn("Inputs Now "+model+envs+ccode+devsrno);
							int confirmbox = JOptionPane.showConfirmDialog(frame,"\n"+model+envs+devsrno+"\n", "Confirm Inputs", JOptionPane.YES_NO_OPTION);
							if (confirmbox==JOptionPane.YES_OPTION){
								Log.disperr("confirmbox Yes option clicked");
								vDeWhiteList(en, model, dt, dwltxtpn);
								srno.setText("");
								//JOptionPane.showConfirmDialog(frame, "Action Complete","Info", JOptionPane.PLAIN_MESSAGE);
								Log.disperr("Device De-WhiteList Action complete");
							} else {
								return;
							}					
						} catch (Exception e1) {
							JOptionPane.showConfirmDialog(frame, "Application Error","Info", JOptionPane.PLAIN_MESSAGE);
							Log.disperr("Application error in Device De-WhiteList");
							dwltxtpn.setText("Application error in Device De-WhiteList");
						}
					}
				});
				pane6.add(dwsub);
				
				JLabel lblDeviceDewhitelist = new JLabel("DEVICE DE-WHITELIST");
				lblDeviceDewhitelist.setFont(new Font("SansSerif", Font.BOLD, 30));
				lblDeviceDewhitelist.setBounds(211, 124, 333, 31);
				pane6.add(lblDeviceDewhitelist);

				
				
		//Tab Help
		JPanel pane5 = new JPanel();
		JTextArea help = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(pane5,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.getVerticalScrollBar().setValue(10);
		
		
		pane5.setBackground(Color.WHITE);
		tabbedPane.addTab("Help", scrollPane);

	    help.setVisible( true );
		help.setTransferHandler(null);
		help.setBackground(Color.WHITE);
		help.setEditable(false);
		help.setText("==================================================================================\n"
				+"TAB NO 1: TOKEN ALLOCATION\n"
				+"~~~~~~~~~~~~~~~~~\n"
				+"STEP 1: SELECT THE ENVIRONMENT FROM THE DROP DOWN LIST.\n"
				+"STEP 2: ENTER Companay NAME [COMPANY NAME FOR REFERENCE MAX 30 CHARS ARE ALLOWED].\n"
				+"STEP 3: ENTER COMPANY CODE [MAX 3 CHARS [A-Z,a-z,0-9] TO IDENTIFY THE COMPANY].\n"
				+"STEP 4: ENTER NO OF TOKENS [NO OF TOKENS TO BE GENERATED].\n"
				+"STEP 5: ENTER VALIDITY IN DAYS FROM CURRNET DATE.\n"
				+"STEP 6: CLICK ON THE ALLOCATE TOKENS BUTTON.\n"
				+"\n"
				+"TOKEN ALLOCATION CONFIRM MESSAGE IS SHOWN.\nON SUCCESSFUL EXECUTION.\nTHE PASSWORD PROTECTED ZIP FILE IS CREATED AND STORED IN THE SELECTED PATH.\n"
				+"THE GENERIC FORMAT FOR ZIP FILE NAME, CSV FLE NAME, ZIP FILE PASSWORD, IS AS SHOWN BELOW\n"
				+"ZIP FILE NAME: {EvoluteTokens_[CODE]_[ENVIRONMENT]_[DATE].zip}\n"
				+"CSV FLE NAME: {EvoluteTokens_[CODE]_[ENVIRONMENT]_[QUANTITY]_[DATE].csv}\n"
				+"ZIP FILE PASSWORD: (CODE always in small)  {esys[code]@[QUANTITY]\n"
				+"==================================================================================\n"
				+"TAB NO 2: TOKEN STATUS\n"
				+"~~~~~~~~~~~~~\n"
				+"STEP 1: SELECT THE ENVIRONMENT FROM THE DROP DOWN LIST.\n"
				+"STEP 2: ENTER TOKEN [WHICH NEEDS TO BE CHECKED FOR STATUS].\n"
				+"STEP 3: CLICK ON THE CHECK STATUS BUTTON.\n"
				+"\n"
				+"TOKEN STATUS IS SHOWN IN THE TEXT VIEW PANEL.\n"
				+"==================================================================================\n"
				+"TAB NO 3: CHECK THE DEVICE STATUS\n"
				+"~~~~~~~~~~~~~~~~~~~~~~~~\n"
				+"STEP 1: SELECT THE ENVIRONMENT FROM THE DROP DOWN LIST.\n"
				+"STEP 2: SELECT THE MODEL ID FROM THE DROP DOWN LIST.\n"
				+"STEP 3: ENTER THE DEVICE SERIAL NUMBER WHICH NEEDS TO BE CHECKED.\n"
				+"STEP 4: CLICK ON THE DEVICE STATUS BUTTON.\n"
				+"\n"
				+"THE DEVICE STATUS IS SHOWN ON THE TEXT VIEW PANEL.\n"
				+"==================================================================================\n"
				+"TAB NO 4: DEVICE DEREGISTER\n"
				+"~~~~~~~~~~~~~~~~~~~\n"
				+"STEP 1: SELECT ENVIRONMENT FROM THE DROP DOWN LIST.\n"
				+"STEP 2: SELECT THE MODEL ID FORM THE DROP DOWN LIST.\n"
				+"STEP 3: ENTER THE DEVICE CODE IN THE DEVICE CODE TEXT FIELD.\n"
				+"STEP 4: CLICK ON THE DEVICE DEREGISTER BUTTON. \n"
				+"\n"
				+"DEVICE DEREGISTER STATUS IS SHOWN IN THE TEXT VIEW PANEL.\n"
				+"==================================================================================\n"
				+"TAB NO 5: DEVICE WHITELIST\n"
				+"~~~~~~~~~~~~~~~~~\n"
				+"STEP 1: SELECT ENVIRONMENT FROM THE DROP DOWN LIST.\n"
				+"STEP 2: ENTER THE 3-CHARACTER COMPANY CODE IN THE COMPANY CODE TEXT FIELD.\n"
				+"STEP 3: SELECT THE MODEL ID FORM THE DROP DOWN LIST.\n"
				+"STEP 4: SELECT THE RADIO BUTTON CORRESPONDING TO THE DEVICE SERIAL NUMBER \n\tTO ENTER SINGLE DEVICE SERIAL NUMBER OR MULTIPLE DEVICE SERIAL NUMBER.\n"
				+"STEP 5: IF SINGLE DEVICE RADIO BUTTON IS SELECTED, ENTER THE DEVICE CODE \n\tIN THE DEVICE CODE TEXT FIELD.\n"
				+"STEP 6: IF MULTIPLE DEVICE RADIO BUTTON IS SELECTED, CHOOSE THE CSV FILE \n\tWHICH CONTAINS MULTIPLE SERIAL NUMBERS SEPERATED BY ','\n"
				+"STEP 7: CLICK ON THE WHITELIST DEVICE'S BUTTON.\n"
				+"\n"
				+"DEVICE WHITELIST STATUS IS SHOWN IN THE TEXT VIEW PANEL.\n"
				+"==================================================================================\n"
				+"TAB NO 6: DEVICE DEWHITELIST\n"
				+"~~~~~~~~~~~~~~~~~~~\n"
				+"STEP 1: SELECT ENVIRONMENT FROM THE DROP DOWN LIST.\n"
				+"STEP 2: SELECT THE MODEL ID FORM THE DROP DOWN LIST.\n"
				+"STEP 3: SELECT THE RADIO BUTTON CORRESPONDING TO THE DEVICE SERIAL NUMBER \n\tTO ENTER SINGLE DEVICE SERIAL NUMBER OR MULTIPLE DEVICE SERIAL NUMBER.\n"
				+"STEP 4: IF SINGLE DEVICE RADIO BUTTON IS SELECTED, ENTER THE DEVICE CODE \n\tIN THE DEVICE CODE TEXT FIELD.\n"
				+"STEP 5: IF MULTIPLE DEVICE RADIO BUTTON IS SELECTED, CHOOSE THE CSV FILE \n\tWHICH CONTAINS MULTIPLE SERIAL NUMBERS SEPERATED BY ',' FROM THE FILE CHOOSER BUTTON.\n"
				+"STEP 6: CLICK ON THE DE WHITELIST DEVICE'S BUTTON.\n"

				+"DEVICE DEWHITELIST STATUS IS SHOWN IN THE TEXT VIEW PANEL.\n"
				+"==================================================================================\n");
		pane5.add(help);		
	}
}
