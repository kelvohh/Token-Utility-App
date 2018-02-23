package com.evolute.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.xml.ws.Response;

import com.evolute.util.Ui.Log;

import java.lang.*;


public class TokenGenStat {
	
	private static boolean blDebug = true; 
	
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
			if (TokenGenStat.blDebug)
				System.err.println(s);
		}
	}

	enum mMenu {TokenAollcation, TokenStatus, Exit, Help}
	enum mEnv {Staging, Pre_Prod, Production, Invalid}
	enum mMi {LEOPARD, FALCON, IMPRESS, LILY, IDENTI5, ESPVPOCMI}
	
	public static class Details {
		String Name = "";
		String Code = "";
		int Quantity = 1;
		int Validity = 90;
		String sToken = "";
		mMi mi = mMi.LEOPARD;
		String devCode = "";
		String devSerNo = "";
		
		boolean blSingleDev = true;
	}

	static void vShowHelp(){
		Log.displn("=========================================");
		Log.displn("1] Token Allocation");
		Log.displn("   Requisites");
		Log.displn("   Companay     Name for Reference Maximum 30 chars are allowed");
		Log.displn("   CODE         for token 3chars [A-Z,a-z,0-9] to identify the company");
		Log.displn("   QUANTITY     that is number of Tokens required");
		Log.displn("   VALIDITY     in days from current System date ");
		Log.displn("   DATE         System date will be populated automatically");
		Log.displn("   ENVIRONMENT  Staging/Pre-Production/Production must be selected during execution");
		Log.displn();
		Log.displn("   On successful execution a zip file is generated with password");
		Log.displn("   File name will be of this format       {EvoluteTokens_JFS_S_04_Sep_2017.zip}");
		Log.displn("   Generic format                         {EvoluteTokens_[CODE]_[ENVIRONMENT]_[DATE].zip}");
		Log.displn("   It will contain a CSV File of format   {EvoluteTokens_JFS_S_5_04_Sep_2017.csv}");
		Log.displn("   Generic format                         {EvoluteTokens_[CODE]_[ENVIRONMENT]_[QUANTITY]_[DATE].csv}");
		Log.displn("   Password of format                     {esysjfs@100}"); 
		Log.displn("   Generic format (CODE always in small)  {esys[code]@[QUANTITY]");
		Log.displn();
		Log.displn();
		Log.displn("2] Token Status");
		Log.displn("   Displays the status of token as per the Environment");
		Log.displn("   TOKEN        Generated valid TOKEN");
		Log.displn("   ENVIRONMENT  Staging/Pre-Production/Production must be selected during execution");
		Log.displn();
		Log.displn("   Output will be as below");
		Log.displn("   hostId          : 913a6d76926f3dbd");
		Log.displn("   modelId         : FaESPVPOCMI");
		Log.displn("   serialNo        : FIMPEGCA6141");
		Log.displn("   status          : TokenUsed");
		Log.displn("   token           : STG-2037-0321-5021");
		Log.displn("   tokenCreatedOn  : 1501571107795 [01 Aug 2017 - 12:35:07]");
		Log.displn("   tokenExpiresOn  : 1508092199000 [15 Oct 2017 - 23:59:59]");
		Log.displn("=========================================");
	}
	
	static void vShowHelphn(){
		Log.displn("=========================================");
		Log.displn("1] Token Allocation");
		Log.displn("   Requisites");
		Log.displn("   Companay     Name for Reference Maximum 30 chars are allowed");
		Log.displn("   CODE         for token 3chars [A-Z,a-z,0-9] to identify the company");
		Log.displn("   QUANTITY     that is number of Tokens required");
		Log.displn("   VALIDITY     in days from current System date ");
		Log.displn("   DATE         System date will be populated automatically");
		Log.displn("   ENVIRONMENT  Staging/Pre-Production/Production must be selected during execution");
		Log.displn();
		Log.displn("   On successful execution a zip file is generated with password");
		Log.displn("   File name will be of this format       {EvoluteTokens_JFS_S_04_Sep_2017.zip}");
		Log.displn("   Generic format                         {EvoluteTokens_[CODE]_[ENVIRONMENT]_[DATE].zip}");
		Log.displn("   It will contain a CSV File of format   {EvoluteTokens_JFS_S_5_04_Sep_2017.csv}");
		Log.displn("   Generic format                         {EvoluteTokens_[CODE]_[ENVIRONMENT]_[QUANTITY]_[DATE].csv}");
		Log.displn("   Password of format                     {esysjfs@100}"); 
		Log.displn("   Generic format (CODE always in small)  {esys[code]@[QUANTITY]");
		Log.displn();
		Log.displn();
		Log.displn("2] Token Status");
		Log.displn("   Displays the status of token as per the Environment");
		Log.displn("   TOKEN        Generated valid TOKEN");
		Log.displn("   ENVIRONMENT  Staging/Pre-Production/Production must be selected during execution");
		Log.displn();
		Log.displn("   Output will be as below");
		Log.displn("   hostId          : 913a6d76926f3dbd");
		Log.displn("   modelId         : FaESPVPOCMI");
		Log.displn("   serialNo        : FIMPEGCA6141");
		Log.displn("   status          : TokenUsed");
		Log.displn("   token           : STG-2037-0321-5021");
		Log.displn("   tokenCreatedOn  : 1501571107795 [01 Aug 2017 - 12:35:07]");
		Log.displn("   tokenExpiresOn  : 1508092199000 [15 Oct 2017 - 23:59:59]");
		Log.displn("=========================================");
	}

	public static void maingfhgfhgf (String[] args) {
		Log.disperr("=========================================");
		Log.displn ("=============== Wel Come ================");

		try {
			if (args.length==1 && args[0]!=null && args[0].equalsIgnoreCase("debug")){
				TokenGenStat.blDebug = true;
			}

			while (true) {
//				mMenu ss = mMenuSelect();
//				//System.out.println( "Selected :"+ss);
//				if (ss==mMenu.Help) {
//					continue;
//				}
				
				try {
					String s = "{\"complianceLevel\":\"L0\",\"deviceCode\":\"50714586-106c-44b7-bb94-5fb258886aec\",\"deviceStatus\":\"registered\","
							+"\"errors\":{\"errors\":[]},\"modelId\":\"LEOPARD\",\"registrationDetails\":[{\"hostId\":\"ec8b59a03cf6371a\",\"registeredOn\":\"1503845888061\""
							+",\"token\":\"EPP-9965-1119-3267\"}],\"serialNo\":\"LEPEGCC2357\"}";
//					System.out.println(s);
//					String sx = "";
//					sx = s.replaceAll("{", "");
//					sx = sx.replaceAll("}", "");
//					sx = sx.replaceAll("[", "");
//					sx = sx.replaceAll("]", "");
					char[] cx = s.toCharArray();
					String sss = "";
					for (char c :cx){
						if (c=='{'||c=='}'||c=='['||c==']')
							continue;
						sss = sss.concat(String.valueOf(c));
					}
					sss = sss.replaceAll("\"errors\":", "");
					System.out.println(sss);
					if(sss.contains("\"registrationDetails\":"))
						sss = sss.replaceAll("\"registrationDetails\":","");
					System.out.println("> > > "+sss);
					System.out.println();
					String [] sArr = sss.split(",");
					for (String ss : sArr) {
						if(ss.isEmpty())
							continue;
						//System.out.println("> "+ss);
						ss = ss.replaceAll("\"", "");
						String sData[] = ss.split(":");
						String s1="" , s2="";
						if (sData.length==1){
							s1 = sData[0];
						}else if (sData.length==1){
							s1 = sData[0];s2 = sData[0];
						}
						String sDisp = String.format("  %-18s = %s", s1, s2);
						System.out.println(">> "+sDisp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
//				if (ss!=mMenu.Exit){
//					//System.exit(0);
//					mEnv e = mEnvSelect(ss);
//					if (e!=null){
//						Details d = mDetailsSelect(ss,e);
//						if (d!=null){
//							//System.exit(1);
//							if (ss==mMenu.TokenAollcation) {
//								//System.out.println("skip ta");
//								vTokenAllocation(e, d,"",null);
//							} else if (ss==mMenu.TokenStatus) {
//								//System.out.println("skip ts");
//								vTokenStatus(e, d, null);
//							} 
//						}
//					}
//				}
				int cnt=3;
				boolean blExit = false;
				while (true) {//TODO
					try {
						Log.disp("Do you want to EXIT Application (Y/N) : ");
						Scanner sc = new Scanner(System.in);
						String sCd = sc.nextLine();
						//sc.close();
						if (cnt==0){
							Log.displn("Retry Failed Application Exit");
							System.exit(1);
						}
						if (sCd==null ||!(sCd.equalsIgnoreCase("Y")||sCd.equalsIgnoreCase("N")||sCd.equalsIgnoreCase("Yes")||sCd.equalsIgnoreCase("No"))){
							cnt--;
							Log.displn("Invalid Input Please Retry");
							continue;
						}
						Log.disp("Entered Code is "+sCd);
						//dt.Code = new String(sCd);
						if(sCd.equalsIgnoreCase("Y")||sCd.equalsIgnoreCase("yes")){
							blExit = true;
						} else {
							blExit = false;
						}
						break;
					} catch (Exception ex){
						cnt--;
						if (cnt==0){
							Log.displn("Retry Failed Application Exit");
							System.exit(1);
						}
						Log.displn("Invalid Input Please Retry");
						continue;
					}
				}
				if (blExit){
					Log.displn("Bye Bye");
					System.exit(0);
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.displn("Application Error "+e);
			Log.disperr("Application Error "+e);
		}
	}
	
	public static mMenu mMenuSelect () {
		int cnt = 3;
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("dd MMM yyyy");
		String strDate = sf.format(d);
		Log.displn("=========================================");
		Log.displn("Esys Generator and Status Monitor of RD-Service Tokens");
		Log.displn("=========================================");
		Log.displn("Today is "+strDate);
		Log.displn("=========================================");
		Log.displn("  1] Token Allocation");
		Log.displn("  2] Token Status");
		Log.displn("  3] Show Help");
		Log.displn("  4] Exit");
		Log.displn("=========================================");
		while (true) {
			Log.disp("Select : ");
			try {
				Scanner sc = new Scanner(System.in);
				//int selected = sc.nextInt();
				String sx = sc.nextLine();
				int selected = Integer.parseInt(sx.trim());//sc.nextInt();
				//sc.close();
				switch (selected){
				case 1: Log.disperr("Selected : Token Allocation");
					return mMenu.TokenAollcation;
				case 2: Log.disperr("Selected : Token Status");
					return mMenu.TokenStatus;
				case 3: vShowHelp(); 
					return mMenu.Help;
				case 4: Log.disperr("Bye Bye");
					return mMenu.Exit;
				default: cnt--;
				if (cnt==0){
					Log.displn("Retry Failed Application Exit");
					return mMenu.Exit;
				}
				Log.displn("Invalid Input Please Retry");
				continue;
				}
			} catch (Exception e){
				cnt--;
				if (cnt==0){
					Log.displn("Retry Failed Application Exit");
					return mMenu.Exit;
				}
				Log.displn("Invalid Input Please Retry");
				continue;
			}
		}
	}
	
	public static mEnv mEnvSelect(mMenu menu) throws Exception {
		int cnt = 3;
		String s = "";
		if (menu==mMenu.TokenAollcation)
			s = "for Token Allocation";
		else if (menu == mMenu.TokenStatus)
			s = "for Tokens Status";
		Log.displn("=========================================");
		Log.displn("Select Environment "+s);
		Log.displn("=========================================");
		Log.displn("  1] Staging");
		Log.displn("  2] Pre Production");
		Log.displn("  3] Production");
		Log.displn("=========================================");
		while (true) {
			Log.disp("Select : ");
			try {
				Scanner sc = new Scanner(System.in);
				String sx = sc.nextLine();
				int selected = Integer.parseInt(sx.trim());//sc.nextInt();
				//sc.close();
				switch (selected){
				case 1: Log.displn("Selected : Staging");
					return mEnv.Staging;
				case 2: Log.displn("Selected : Pre Prod");
					return mEnv.Pre_Prod;
				case 3: Log.displn("Selected : Production");
					return mEnv.Production;
				default: cnt--;
				if (cnt==0){
					Log.displn("Retry Failed Application Exit");
					return null;//System.exit(1);s;
				}
				Log.displn("Invalid Input Please Retry");
				continue;
				}
			} catch (Exception e){
				cnt--;
				if (cnt==0){
					Log.displn("Retry Failed Application Exit");
					return null;//System.exit(1);s;
				}
				Log.displn("Invalid Input Please Retry");
				continue;
			}
		}
	}
	
	public static Details mDetailsSelect(mMenu menu,mEnv env) throws Exception{
		int cnt = 3;
		String s = "";
		if (menu==mMenu.TokenAollcation)
			s = "Details for Token Allocation";
		else if (menu == mMenu.TokenStatus)
			s = "Details for Tokes Status";
		if (env==mEnv.Staging)
			s += " on Staging Environment";
		else if (env==mEnv.Staging)
			s += " on Pre Prod Environment";
		else if (env==mEnv.Staging)
			s += " on Production Environment";
		Log.displn("=========================================");
		Log.displn(s);
		Log.displn("=========================================");
		Details dt = new Details();
		if (menu==mMenu.TokenAollcation) {
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("dd MMM yyyy");
			String strDate = sf.format(d);
			Log.displn("  Please Enter the following details...");
			Log.displn("  1] Companay Name for Reference [Max 30 chars]");
			Log.displn("  2] Code for token 3chars [A-Z,a-z,0-9]");
			Log.displn("  3] Number of Tokens required [Ex:100]");
			Log.displn("  4] Validitiy in days from [Ex:90] "+strDate);
			Log.displn("=========================================");
			//Scanner sc = new Scanner(System.in);
			while (true) {//TODO
				try {
					Log.disp("-> Enter Company Name : ");
					Scanner sc = new Scanner(System.in);
					String sIn = sc.nextLine();
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					if (sIn==null ||sIn.length()==0||sIn.length()>30){
						cnt--;
						Log.displn("Invalid Input Please Retry");
						continue;
					}
//					Log.disp("Entered Name is "+sIn);
					dt.Name = new String(sIn);
					break;
				} catch (Exception e){
					cnt--;
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					Log.displn("Invalid Input Please Retry");
					continue;
				}
			}
			cnt=3;
			while (true) {//TODO
				try {
					Log.disp("-> Enter Code for Token [AAA] : ");
					Scanner sc = new Scanner(System.in);
					String sCd = sc.nextLine();
					//sc.close();
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					if (sCd==null ||sCd.length()!=3){
						cnt--;
						Log.displn("Invalid Input Please Retry");
						continue;
					}
//					Log.disp("Entered Code is "+sCd);
					dt.Code = new String(sCd);
					break;
				} catch (Exception e){
					cnt--;
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					Log.displn("Invalid Input Please Retry");
					continue;
				}
			}
			cnt=3;
			while (true) {//TODO
				try {
					Log.disp("-> Enter Number of Tokens required (Quantity) : ");
					
					Scanner sc = new Scanner(System.in);
					String sx = sc.nextLine();
					int iQty = Integer.parseInt(sx.trim());//sc.nextInt();
					//sc.close();
					
					//int iQty = sc.nextInt();
					
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					if (iQty==0){
						cnt--;
						Log.displn("Invalid Input Please Retry");
						continue;
					}
					cnt=3;
//					Log.disp("Entered Quantity is "+iQty);
					dt.Quantity = iQty;
					break;
				} catch (Exception e){
					cnt--;
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					Log.displn("Invalid Input Please Retry");
					continue;
				}
			}
			cnt=3;
			while (true) {//TODO
				try {
					Log.disp("-> Enter Number of days Tokens are valid from "+strDate+" : ");
					//int iVal = sc.nextInt();
					Scanner sc = new Scanner(System.in);
					String sx = sc.nextLine();
					int iVal = Integer.parseInt(sx.trim());//sc.nextInt();
					//sc.close();
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					if (iVal==0){
						cnt--;
						Log.displn("Invalid Input Please Retry");
						continue;
					}
					cnt=3;
//					Log.disp("Entered Validity is "+iVal);
					dt.Validity = iVal;
					break;
				} catch (Exception e) {
					cnt--;
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					Log.displn("Invalid Input Please Retry");
					continue;
				}
			}
			Date d1 = new Date();
			SimpleDateFormat sfa = new SimpleDateFormat("dd_MMM_yyyy");
			String str_Today = sfa.format(d1);
			Log.displn("Today = "+str_Today);
			String strDate1 = str_Today+":23:59:59";
			long sad = new SimpleDateFormat("dd_MMM_yyyy:HH:mm:ss").parse(strDate1).getTime();//d.getTime();
//			Log.disperr("long  : "+sad);
			long validity = dt.Validity;
			long epoch = validity*24*60*60*1000+sad;
//			Log.disperr("epoch : "+epoch);
			Date sEpoch = new Date(epoch);
			String str_Till = sfa.format(sEpoch);
//			Log.disperr("Till  = "+str_Till);
			Log.displn("=========================================");
			boolean validate = false;
			Log.displn(" Tokens Issued to  "+dt.Name);
			Log.displn(" Code is           "+dt.Code);
			Log.displn(" Quantity          "+dt.Quantity);
			Log.displn(" Issued on         "+str_Today.replaceAll("_", " "));
			Log.displn(" For               "+dt.Validity+" days");
			Log.displn(" Valid upto        "+str_Till.replaceAll("_", " ")); 
			Log.displn("=========================================");
			cnt=3;
			while (true) {//TODO
				try {
					Log.disp("Is the above information correct (Y/N) : ");
					Scanner sc = new Scanner(System.in);
					String sCd = sc.nextLine();
					//sc.close();
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					if (sCd==null ||!(sCd.equalsIgnoreCase("Y")||sCd.equalsIgnoreCase("N")||sCd.equalsIgnoreCase("Yes")||sCd.equalsIgnoreCase("No"))){
						cnt--;
						Log.displn("Invalid Input Please Retry");
						continue;
					}
					/*Log.disp("Entered Code is "+sCd);
					dt.Code = new String(sCd);*/
					if(sCd.equalsIgnoreCase("Y")||sCd.equalsIgnoreCase("yes")){
						validate = true;
					} else {
						validate = false;
					}
					break;
				} catch (Exception e){
					cnt--;
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					Log.displn("Invalid Input Please Retry");
					continue;
				}
			}
			if (validate) {
				return dt;
			} else {
				Log.displn("Restart/Exit the Application ");
				return null;//System.exit(1);
			}
		} else if (menu==mMenu.TokenStatus) {
			cnt=3;
			while (true) {//TODO
				try {
					Log.disp("-> Enter Token [ABC-1234-1234-1234]: ");
					Scanner sc = new Scanner(System.in);
					String sTok = sc.nextLine();
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					if (sTok==null || sTok.length()!=18) {
						cnt--;
						Log.displn("Invalid Input Please Retry");
						continue;
					}
//					Log.disp("Entered Name is "+sIn);
					dt.sToken = new String(sTok);
					break;
				} catch (Exception e) {
					cnt--;
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					Log.displn("Invalid Input Please Retry");
					continue;
				}
			}
			Log.displn("=========================================");
			boolean validate = false;
			Log.displn(" Entered Token is     "+dt.sToken);
			Log.displn("=========================================");
			cnt=3;
			while (true) {//TODO
				try {
					Log.disp("Is the above information correct (Y/N) : ");
					Scanner sc = new Scanner(System.in);
					String sCd = sc.nextLine();
					//sc.close();
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					if (sCd==null ||!(sCd.equalsIgnoreCase("Y")||sCd.equalsIgnoreCase("N")||sCd.equalsIgnoreCase("Yes")||sCd.equalsIgnoreCase("No"))){
						cnt--;
						Log.displn("Invalid Input Please Retry");
						continue;
					}
					/*Log.disp("Entered Code is "+sCd);
					dt.Code = new String(sCd);*/
					if(sCd.equalsIgnoreCase("Y")||sCd.equalsIgnoreCase("yes")){
						validate = true;
					} else {
						validate = false;
					}
					
					break;
				} catch (Exception e){
					cnt--;
					if (cnt==0){
						Log.displn("Retry Failed Application Exit");
						return null;//System.exit(1);
					}
					Log.displn("Invalid Input Please Retry");
					continue;
				}
			}
			if (validate) {
				return dt;
			} else {
				Log.displn("Restart the Application ");
				return null;//System.exit(1);
			}
		}
		return null;
	}
	
public static class Logger {
	static PrintWriter pwLogIt=null;
	static String sCurrFile = "";
	public Logger(String sd) {
		try {
			SimpleDateFormat sfz = new SimpleDateFormat("ddMMMyyyy_HHmmss");
			String sLogTime = sfz.format(new Date());
			//File fLogIt = new File("LogData.txt");
			sCurrFile = "Log_"+sd+"_"+sLogTime+".txt";
			File fLogIt = new File(sCurrFile);//new File(sCscFileNameWhiteList+"_Log.txt");
//			if(!fLogIt.exists())
//				fLogIt.createNewFile();
			pwLogIt = new PrintWriter (fLogIt);
		} catch (Exception e) {
			Log.disperr("Error while logging "+e.toString());
			e.printStackTrace();
		}
	}
	public void add(String s){
		SimpleDateFormat sfz = new SimpleDateFormat("dd-MM-yyyy:HH:mm:ss");
		String sLogTime = sfz.format(new Date());
		if (pwLogIt!=null)
			pwLogIt.append("\n"+sLogTime+"  "+s);
	}
	public void add(){
		SimpleDateFormat sfz = new SimpleDateFormat("dd-MM-yyyy:HH:mm:ss");
		String sLogTime = sfz.format(new Date());
		if (pwLogIt!=null)
			pwLogIt.append("\n"+sLogTime+"\n");
	}
	public void close(){
		if (pwLogIt!=null)
			pwLogIt.close();
	}
}
	
	public static void vTokenAllocation(mEnv env, Details dt,String sPath,JTextPane txtpnTokenalloc) {
		Logger lgr = new Logger("TokAlloc");
		try {
			final String stg  = "https://stage-rdm.aujas.com/rdm-device-app/tokenAllocation";
			final String pre  = "https://pre-rdm.aujas.com/rdm-device-app/tokenAllocation";
			final String prod = "https://rdm.aujas.com/rdm-device-app/tokenAllocation";
			final String stg_lk = "CUKhZCTq3/70CoKTWVZDOILQkU5GXpqLImV60C+aOMA=";
			final String stg_lkh = "e51544986c0e1795be9d61e26670d36a1ca9a97a9f88feaf6548373a086ad17e";
			final String prod_lk = "pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";
			final String prod_lkh = "XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";
			
			String url = "",sEnvNow = "S",sEnvFull = "Staging";
			String flk = "";
			String flkh = "";
			if (env==mEnv.Staging) {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "S";sEnvFull = "Staging";
			} else if (env==mEnv.Pre_Prod) {
				url = pre;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "PP";sEnvFull = "Pre-Production";
			} else if (env==mEnv.Production) {
				url = prod;
				flk = prod_lk;
				flkh = prod_lkh;
				sEnvNow = "P";sEnvFull = "Production";
			} else {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "S";sEnvFull = "Staging";;
			}
			String s1="Token Allocation for "+sEnvFull+"\n";
			String s2="Tokens Issued to  "+dt.Name+"\n";
			String s3="Code is           "+dt.Code+"\n";
			String s4="Quantity          "+dt.Quantity+"\n";
			
			
			lgr.add(s1.trim());
			lgr.add(s2.trim());
			lgr.add(s3.trim());
			lgr.add(s4.trim());
			
//			 pwLogIt.append("hello");
//			    pwLogIt.close ();  
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
			String s5="Issued on         "+sToday+"["+sad+"]"+"\n";
			String s6="For               "+dt.Validity+" days"+"\n";
			String s7="Valid upto        "+sTill+"["+epoch+"]"+"\n";
			lgr.add(s5.trim());
			lgr.add(s6.trim());
			lgr.add(s7.trim());
			
			String sb1="Token Allocation for "+sEnvFull+"\n\n";
			String sb2="Tokens Issued to  "+dt.Name+"\n";
			String sb3="Code is "+dt.Code+" ";
			String sb4="of "+dt.Quantity+" quantity.\n";
			String sb5="Issued on "+sToday+" ";
			String sb6="for "+dt.Validity+" days ";
			String sb7="valid upto "+sTill+"\n";
			
			//int confirmbox1 = JOptionPane.showConfirmDialog(frame, "\n"+s1+s2+s3+s4+s5+s6+s7+"\n", "Info", JOptionPane.OK_OPTION);
			txtpnTokenalloc.setText("");
			String sUpdate = txtpnTokenalloc.getText();
			sUpdate+=sb1+sb2+sb3+sb4+sb5+sb6+sb7;
			txtpnTokenalloc.setText(sUpdate+"\n");
			
			String propertyFile = "tenantLK = "+flk+"\r\n"
						+ "tenantLKHash = "+flkh+"\r\n"
						+ "url = "+url+"\r\n"//"https://rdm.aujas.com/rdm-device-app/registration"
						//+ "message = {\"tokenCount\":"+qty+", \"tokenCode\": \""+code+"\", \"expireOn\" : "+epoch+"}"+"\r\n"
						+ "message = {\"tokenCount\":"+dt.Quantity+", \"tokenCode\": \""+dt.Code+"\", \"expireOn\" : "+epoch+"}"+"\r\n"
						+ "requestType = POST"+"\r\n";
			Log.disperr("File content :\n"+propertyFile);
			Log.disperr("================================");
			
			File fl = new File("tmp.prop");
			if (fl.exists()){
				fl.delete();
			}
			fl.createNewFile();
			FileOutputStream fos = new FileOutputStream(fl);
			fos.write(propertyFile.getBytes());
			fos.close();
			
			Runtime rt = Runtime.getRuntime();
			//String cmdString = "cmd /c type tmp.prop";
			String cmdString = "java -cp SendRequest.jar;groovy-2.4.5.jar SendRequest tmp.prop";
			// String cmdString = "cmd /c dir";
			// String cmdString = "java -cp TokenAllocation.jar;groovy-2.4.5.jar TokenAllocation " + qty + " " + code + " " + epoch;
			// String cmdString = "java -cp TokenAllocation.jar;groovy-2.4.5.jar TokenAllocation 10 AUJ 1501439400000";
			Log.disperr("Command : "+cmdString);
			 
			Log.disperr("================================");
			 
			Process pr = rt.exec(cmdString);
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = input.readLine()) != null) {
				Log.disperr(line);
				sb.append(line);
			}
			int exitVal = pr.waitFor();
			Log.disperr("Exited with error code " + exitVal);
			if (exitVal!=0) {
				lgr.add("Error Occoured ");
				BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
				String line2 = null;
				while ((line2 = input2.readLine()) != null) {
					Log.displn(line2);
					lgr.add(line2); 
				}
				Log.displn("Command Execution Failed");
				
				lgr.add("Command Execution Failed");
				lgr.add("~~~~~ LOG END ~~~~~");
				lgr.close();
				return;//System.exit(1);
			}
			String s = sb.toString();
			Log.disperr("Result : " + s);
		
			String out = s.substring(s.indexOf(":[\"") + 2, s.indexOf("\"]") + 1);
			//return out;
			Log.disperr("================================");
			
			Log.disperr(s);
			
			Log.disperr("================================");
			
			String sArr[] = out.split(",");
			String sFileNameZip = "EvoluteTokens_"+dt.Code+"_"+sEnvNow+"_"+str_Today+".zip";
			String sFileName = "EvoluteTokens_"+dt.Code+"_"+sEnvNow+"_"+dt.Quantity+"_"+str_Today+".csv";
			File flo = new File(sFileName);
			flo.createNewFile();
			FileOutputStream fos2 = new FileOutputStream(flo);
			Log.displn("================================");
			
			
			//String sCon1 = "EvoluteTokens_"+dt.Code+"_"+sEnvNow+"_"+str_Today+".csv"+"\r\n";
			String sCon1 = sFileName+"\r\n";
			fos2.write(sCon1.getBytes());
			Log.displn("Tokens File : "+sCon1.trim());
		
			lgr.add(sCon1);
			sUpdate = txtpnTokenalloc.getText();
			sUpdate+="Tokens File : "+sCon1.trim()+"\n";
			sUpdate+="Zip File    : "+sFileNameZip+"\n";
			sUpdate+="Allocated Tokens";
			txtpnTokenalloc.setText(sUpdate+"\n");
			
			String sCon2 = "Issued To,"+dt.Name+"\r\n";
			fos2.write(sCon2.getBytes());
			Log.displn("Issued To   : "+dt.Name.trim());
			
			lgr.add(sCon2);
			
			String sCon3 = "Quantity,"+dt.Quantity+"\r\n";
			fos2.write(sCon3.getBytes());
			Log.displn("Quantity    : "+dt.Quantity);
			
			lgr.add(sCon3);
			
			String sCon4 = "Date,"+sToday+"\r\n";
			fos2.write(sCon4.getBytes());
			Log.displn("Issued on   : "+sToday.trim());
			
			lgr.add(sCon4);
			
			String sCon5 = "Valid Upto,"+sTill+"\r\n";
			fos2.write(sCon5.getBytes());
			Log.displn("Valid Upto  : "+sTill.trim());
			
			lgr.add(sCon5);
			
			String sCon6 = "Environment,"+sEnvFull+"\r\n";
			fos2.write(sCon6.getBytes());
			Log.displn("Environment : "+sEnvFull.trim());
			
			lgr.add(sCon6);
			
			Log.displn("================================");
			
			for (int i = 0; i < sArr.length; i++) {
				String sx = String.format("%05d", (i + 1));
				Log.displn(sx + " : " + sArr[i]);
				
				String sa1 = new String(sx + "," + sArr[i].replaceAll("\"", "") + "\r\n");
				fos2.write(sa1.getBytes());
				sUpdate = txtpnTokenalloc.getText();
				//sUpdate+=sa1.trim().replace(",", ": ");
				sUpdate+= sa1;
				//sUpdate+=".";
				txtpnTokenalloc.setText(sUpdate);
				lgr.add(sa1);
			}
			fos2.close();
			Log.displn("================================");

			//String sPath="";//"c:\asdasd\asdasd";
			String sPassWord = "esys"+dt.Code.toLowerCase()+"@"+dt.Quantity;
			lgr.add("CSV File "+sFileName);
			lgr.add("ZIP File "+sFileNameZip);
			lgr.add("PassWord "+sPassWord);
			if (CreatePasswordProtectedZipExample.vZipIt(sFileName, sPath+sFileNameZip, sPassWord)==0){
			//if (CreatePasswordProtectedZipExample.vZipIt(sFileName, sFileName.replaceAll(".csv", ".zip"), "esys1234")==0){
				//return "Command Executed";
				File fll = new File(sFileName);
				if(fll.exists())
					fll.delete();
				lgr.add("Operation Closed");
				lgr.add("~~~~~ LOG END ~~~~~");
				lgr.close();
				return;//System.exit(0);
			} else {
				Log.displn("File Creation Failed");
				 
				lgr.add("File Creation Failed");
				lgr.add("~~~~~ LOG END ~~~~~");
				lgr.close();
				return;//System.exit(1);
			}
		} catch (Exception e) {
			Log.displn("System Error\n"+e.toString());
			
			e.printStackTrace();
			lgr.add("System Error "+e.toString());
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			return;//System.exit(1);
		}
	}


	public static void vTokenStatus(mEnv env, Details dt, JTextPane txtpneTokenstatus) {
		Logger lgr = new Logger("TokStat");
		try {
			final String stg  = "https://stage-rdm.aujas.com/rdm-device-app/tokenStatus";
			final String pre  = "https://pre-rdm.aujas.com/rdm-device-app/tokenStatus";
			final String prod = "https://rdm.aujas.com/rdm-device-app/tokenStatus";
			final String stg_lk = "CUKhZCTq3/70CoKTWVZDOILQkU5GXpqLImV60C+aOMA=";
			final String stg_lkh = "e51544986c0e1795be9d61e26670d36a1ca9a97a9f88feaf6548373a086ad17e";
			final String prod_lk = "pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";
			final String prod_lkh = "XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";
			
			String url = "";
			String flk = "";
			String flkh = "";
			String sEnvFull = "Staging";
			if (env==mEnv.Staging) {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Staging";
			} else if (env==mEnv.Pre_Prod) {
				url = pre;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Pre Production";
			} else if (env==mEnv.Production) {
				url = prod;
				flk = prod_lk;
				flkh = prod_lkh;
				sEnvFull = "Production";
			} else {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Staging";
			}
			
			lgr.add("Token Status for "+sEnvFull);
			lgr.add("of Tokens "+dt.sToken);
			
			
			
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = sf.format(d);
			Log.disperr("Today = "+strDate);
			
			String strDate1 = strDate+":23:59:59";
			long sad = new SimpleDateFormat("dd-MM-yyyy:HH:mm:ss").parse(strDate1).getTime();//d.getTime();
			Log.disperr("long  : "+sad);
			
			long validity = dt.Validity;
			long epoch = validity*24*60*60*1000+sad;
			Log.disperr("epoch : "+epoch);
			
			Date sEpoch = new Date(epoch);
			String strDateFinal = sf.format(sEpoch);
			Log.disperr("Till  = "+strDateFinal);

		
			String propertyFile = "tenantLK = "+flk+"\r\n"
						+ "tenantLKHash = "+flkh+"\r\n"
						+ "url = "+url+"\r\n"//"https://rdm.aujas.com/rdm-device-app/registration"
						//+ "message = {\"tokenCount\":"+qty+", \"tokenCode\": \""+code+"\", \"expireOn\" : "+epoch+"}"+"\r\n"
						//+ "message = {\"tokenCount\":"+dt.Quantity+", \"tokenCode\": \""+dt.Code+"\", \"expireOn\" : "+epoch+"}"+"\r\n"
						+ "message = {\"token\" : \""+dt.sToken+"\"}"+"\r\n"
						+ "requestType = POST"+"\r\n";
			Log.disperr("File content :\n"+propertyFile);
			Log.disperr("~~~~~~~~~~~~~~~~~");
			
			File fl = new File("tmp.prop");
			if (fl.exists()){
				fl.delete();
			}
			fl.createNewFile();
			FileOutputStream fos = new FileOutputStream(fl);
			fos.write(propertyFile.getBytes());
			fos.close();
			
			Runtime rt = Runtime.getRuntime();
			//String cmdString = "cmd /c type tmp.prop";
			String cmdString = "java -cp SendRequest.jar;groovy-2.4.5.jar SendRequest tmp.prop";
			// String cmdString = "cmd /c dir";
			// String cmdString = "java -cp TokenAllocation.jar;groovy-2.4.5.jar TokenAllocation " + qty + " " + code + " " + epoch;
			// String cmdString = "java -cp TokenAllocation.jar;groovy-2.4.5.jar TokenAllocation 10 AUJ 1501439400000";
			Log.disperr("Command : "+cmdString);
			
			Log.disperr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			Process pr = rt.exec(cmdString);
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = input.readLine()) != null) {
				Log.disperr(line);
				sb.append(line);
			}
			int exitVal = pr.waitFor();
			Log.disperr("Exited with error code " + exitVal);
			
			
			if (exitVal!=0){
				lgr.add("Command Exited with error code " + exitVal);
				BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
				String line2 = null;
				while ((line2 = input2.readLine()) != null) {
					Log.displn(line2);
					
					lgr.add(line2);
				}
				Log.disperr("Command Execution Failed");
				
				lgr.add("Command Execution Failed");
				lgr.add("~~~~~ LOG END ~~~~~");
				lgr.close();
				return; //System.exit(1);
			}
			
			String sParseTokenStatus = sb.toString();
			Log.displn("================================");
			
			Log.disperr(sParseTokenStatus);
			
			String matter = sParseTokenStatus.substring(sParseTokenStatus.indexOf("hostId"));
			Log.disperr(matter);
		
			String noquote = matter.replaceAll("\"", "").replaceAll("}", "");
			Log.disperr(noquote);
			
			String sVals[] =noquote.split(",");
			Log.displn();
			
			
			String se="Token Status for "+sEnvFull+"\n";
			String tok="Token Number is:  "+dt.sToken+"\n";
			
			
			
			String asdf=se+tok;
			for(int i=0;i<sVals.length;i++){
				//Log.disp(sVals[i]);
				String sData[] = sVals[i].split(":");
				String sDisp = String.format("  %-15s : %s", sData[0],sData[1]);
				if (i==5||i==6){
					try {
						long l = Long.parseLong(sData[1]);
						Date da = new Date(l);
						SimpleDateFormat sfa = new SimpleDateFormat("dd MMM yyyy - HH:mm:ss");
						String strDatea = sfa.format(da);
						sDisp += " ["+strDatea+"]";  
						
					} catch (Exception e) { }
				}
				
				asdf+=sDisp+"\n";
				
				Log.displn(sDisp);
				lgr.add(sDisp);

			}
			txtpneTokenstatus.setText("");
			String stUpdate = txtpneTokenstatus.getText();
			stUpdate+= se+"\n"+tok+"\n"+noquote+"\n";
			txtpneTokenstatus.setText(asdf);
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			return;//System.exit(0);
		} catch (Exception e) {
			Log.displn("System Error "+e.toString());
			txtpneTokenstatus.setText("System Error "+e.toString());
			lgr.add("System Error "+e.toString());
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			e.printStackTrace();
			return; //System.exit(1);
		}
	}
	
//	public static void vTokenStatusBulk(mEnv env, Details dt, JTextPane txtpneTokenstatus){ //TODO
//		Logger lgr = new Logger("TokStatBulk");
//		try {
//			final String stg  = "https://stage-rdm.aujas.com/rdm-device-app/tokenStatus";
//			final String pre  = "https://pre-rdm.aujas.com/rdm-device-app/tokenStatus";
//			final String prod = "https://rdm.aujas.com/rdm-device-app/tokenStatus";
//			final String stg_lk = "CUKhZCTq3/70CoKTWVZDOILQkU5GXpqLImV60C+aOMA=";
//			final String stg_lkh = "e51544986c0e1795be9d61e26670d36a1ca9a97a9f88feaf6548373a086ad17e";
//			final String prod_lk = "pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";
//			final String prod_lkh = "XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";
//			
//			String url = "",sEnvNow = "S",sEnvFull = "Staging";
//			String flk = "";
//			String flkh = "";
//			//String sEnvFull = "Staging";
//			if (env==mEnv.Staging) {
//				url = stg;
//				flk = stg_lk;
//				flkh = stg_lkh;
//				sEnvNow = "S";sEnvFull = "Staging";
//			} else if (env==mEnv.Pre_Prod) {
//				url = pre;
//				flk = stg_lk;
//				flkh = stg_lkh;
//				sEnvNow = "PP";sEnvFull = "Pre-Production";
//			} else if (env==mEnv.Production) {
//				url = prod;
//				flk = prod_lk;
//				flkh = prod_lkh;
//				sEnvNow = "P";sEnvFull = "Production";
//			} else {
//				url = stg;
//				flk = stg_lk;
//				flkh = stg_lkh;
//				sEnvNow = "S";sEnvFull = "Staging";
//			}
//			
//			lgr.add("Token Status for "+sEnvFull);
//			lgr.add("of Tokens "+dt.sToken);
//	
//								 
//			Date d = new Date();
//			SimpleDateFormat sf = new SimpleDateFormat("dd_MMM_yyyy");
//			String str_Today = sf.format(d);
//			Log.disperr("Today = "+str_Today);
//			String strDate1 = str_Today+":23:59:59";
//			long sad = new SimpleDateFormat("dd_MMM_yyyy:HH:mm:ss").parse(strDate1).getTime();//d.getTime();
//			Log.disperr("long  : "+sad);
//   
//			long validity = dt.Validity;
//			long epoch = validity*24*60*60*1000+sad;
//			Log.disperr("epoch : "+epoch);
//   
//			Date sEpoch = new Date(epoch);
//			String str_Till = sf.format(sEpoch);
//			Log.displn();
//			Log.displn();
//			Log.disperr("Till  = "+str_Till);
//			String sToday = str_Today.replaceAll("_", " ");
//			String sTill  = str_Till.replaceAll("_", " ");
//			String s5="Token status check on         "+sToday+" ["+sad+"]"+"\n";
//			
//			lgr.add(s5.trim());
//			///////////////							 
//			String sList = "";
//			if (dt.blSingleDev==true){
//				Log.displn("Single Token Input "+dt.sToken);
////				txtpneTokenstatus.setText("Single Device Input "+dt.devSerNo);
//				lgr.add("Single Token Input "+dt.sToken);
//				sList= dt.sToken;
//				if (!sList.endsWith(","))
//					sList = sList+",";
//			} else{
//				Log.displn("Multiple Token Input File :"+dt.sToken);
////				txtpneTokenstatus.setText("Multiple Device Input File :"+dt.devSerNo);
//				lgr.add("Multiple Token Input File :"+dt.sToken);
//				File fli = new File(dt.sToken);
//				if (fli==null||!fli.exists()){
//					Log.disp("Input File Error");
//					txtpneTokenstatus.setText("Input File Error");
//					lgr.add("Input File Error");
//					lgr.add("~~~~~ LOG END ~~~~~");
//					lgr.close();
//					return;
//				}
//				FileInputStream fis = new FileInputStream(fli);
//				BufferedInputStream bis = new BufferedInputStream(fis); //(new FileReader("readme.txt"));
//				
//				ByteBuffer buf = new ByteBuffer();
//				byte[] bRd = new byte[4096];
//				while (bis.available()>0){
//					int len = bis.read(bRd);
//					buf.appendBytes(bRd, len);
//				}
//				bis.close();
//				byte[] bReadData = buf.getBuffer();			
//				////////////////
//				sList = new String(bReadData);//"LEPEFCA0001,LEPEFCA0002,LEPEFCA0003,LEPEFCA0004,LEPEFCA0005,LEPEFCA0006";
//			}
//			//String sMidConstBeg ="message = { ";//\"customerCode\": \""+dt.Code+"\", \"devices\": [";
//			//String sMi = dt.mi.toString();
//			///////////
//			String sTokArrCsv[] = sList.split(",");
//			if (sTokArrCsv==null) {
//				lgr.add("Invalid CSV ");
//				txtpneTokenstatus.setText("Invalid CSV ");
//				Log.disperr("Invalid CSV ");
//				lgr.add("~~~~~ LOG END ~~~~~");
//				lgr.close();
//			}
//			Log.displn("Input CSV Elements : "+sTokArrCsv.length);
////			txtpneTokenstatus.setText("Input CSV Elements : "+sSerArrCsv.length);
//			lgr.add("Input CSV Elements : "+sTokArrCsv.length);
//			
//			for (int i=0;i<sTokArrCsv.length;i++) {
//				lgr.add(">>> >> > CSV Elements ["+(i+1)+"] "+sTokArrCsv[i]);
//				String token = sTokArrCsv[i].trim().replaceAll(",","").toUpperCase();//dt.devSerNo;
//				Log.displn("Chk 1 : "+token);
//				//String sEncOut = new String(HexString.bufferToHex(sMi.getBytes())+"-"+HexString.bufferToHex(token.getBytes()));
//				//Log.displn("Chk 2 : "+sEncOut);
//				String currurl = url ;//+ sEncOut;
//				Log.displn("Chk 3 : " + currurl);
//			
//
//				String sHdr = "tenantLK = "+flk+"\r\n"
//						+ "tenantLKHash = "+flkh+"\r\n"
//						+ "url = "+currurl+ "\r\n";
//
//				//////////////
//				StringBuilder sMid = new StringBuilder();
//				for (i=0;i<sTokArrCsv.length;i++) {
//					if (i==sTokArrCsv.length-1)
//						sMid.append("{\"serialNo\": \""+sTokArrCsv[i].trim().toUpperCase());
//					else
//						sMid.append("{\"serialNo\": \""+sTokArrCsv[i].trim().toUpperCase());
//				}
//				String sEachSerNo = sMid.toString();//"{\"serialNo\": \""+serArr+"\", \"modelId\": \""+mi+"\"}";
//				///////////////
//				//String sEachSerNo = "\"modelId\": \""+dt.mi.toString()+"\", \"deviceCode\": \""+dt.devCode+"\" ";
//				//String sEachSerNo = "\"modelId\": \""+dt.mi.toString()+"\", \"deviceCode\": \""+sSerArrCsv[i].trim()+"\" ";
//				String sMidConstEnd = " }"+"\r\n";
//				//String sFtr = "requestType = PUT"+"\r\n";
//				String sFtr = "requestType = POST"+"\r\n";
//				/////////////////////
//				String propertyFile1 = "tenantLK = "+flk+"\r\n"
//						+ "tenantLKHash = "+flkh+"\r\n"
//						+ "url = "+url+"\r\n"
//						+ "message = {\"token\" : \""+dt.sToken+"\"}"+"\r\n"
//						//+ "message = {\"modelId\": \""+dt.mi.toString()+"\", \"deviceCode\": \""+dt.devCode+"\"}"+"\r\n"
//						+ "requestType = POST"+"\r\n";
//				/////////////////////
//				
//				//Log.disp(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);
//				//String propertyFile = new String(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);
//				String propertyFile = new String(sHdr+sEnvNow+sEachSerNo+sMidConstEnd+sFtr);
//	//		
//	//			lgr.add(sCscFileNameWhiteList);
//	//			Log.displn(sCscFileNameWhiteList);
//	//			lgr.add();
//				lgr.add("Input URL : ");
//				//lgr.add(propertyFile);
//				lgr.add(currurl);
//				Log.disperr("File content :\n"+propertyFile);
//				Log.disperr("================================");
//				File fl = new File("tmp.prop");
//				if (fl.exists()){
//					fl.delete();
//				}
//				fl.createNewFile();
//				FileOutputStream fos = new FileOutputStream(fl);
//				fos.write(propertyFile.getBytes());
//				fos.close();
//				
//				Runtime rt = Runtime.getRuntime();
//				//String cmdString = "cmd /c type tmp.prop";
//				String cmdString = "java -cp SendRequest.jar;groovy-2.4.5.jar SendRequest tmp.prop";
//				//cmdString = "clear";
//				Log.disperr("Command : "+cmdString);
//				Log.disperr("================================");
//				Process pr = rt.exec(cmdString);
//				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//				String line = null;
//				StringBuilder sb = new StringBuilder();
//				while ((line = input.readLine()) != null) {
//					Log.displn(line);
////					txtpneTokenstatus.setText(line);
//					sb.append(line.trim()+"\n");
//				}
//				int exitVal = pr.waitFor();
//				Log.disperr("Exited with error code " + exitVal);
////				txtpneTokenstatus.setText("Exited with error code " + exitVal);
//				if (exitVal!=0){
//					lgr.add("Error Occoured ");
//					BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
//					String line2 = null;
//					txtpneTokenstatus.setText(input2.readLine());
//				StringBuilder sd = new StringBuilder();
//					while ((line2 = input2.readLine()) != null) {
//						Log.disperr(line2);
//						txtpneTokenstatus.setText(line2);
//						sd.append(line2);
//						if (!line2.trim().startsWith("at"))
//							lgr.add(line2.trim()+"\n"); 
//					}
//					Log.displn("Command Execution Failed\n>>>>\n"+sd.toString());
////					txtpneTokenstatus.setText("Command Execution Failed");
//					lgr.add("Command Execution Failed");
//					lgr.add("~~~~~ LOG END ~~~~~");
//					continue;
//					//lgr.close();
//					//return;//System.exit(1);
//				}
//				Log.displn("Command Execution Complete");
////				txtpneTokenstatus.setText("Command Execution Complete");
//				String sOut = sb.toString();
//				Log.displn(sOut);
//				lgr.add(sOut);
//				lgr.add("~~~~~~~~~~~~~");
//				
//			}
//			lgr.add("~~~~~ LOG END ~~~~~");
//			lgr.close();
//						   
//		} catch (Exception e) {
//			Log.displn("System Error\n"+e.toString());
////			txtpneTokenstatus.setText("System Error\n"+e.toString());
//			e.printStackTrace();
//			lgr.add("System Error "+e.toString());
//			lgr.add("~~~~~ LOG END ~~~~~");
//			lgr.close();
//					   
//			return;//System.exit(1);
//		}
//	}
	
	public static void vDeviceStatus(mEnv env, mMi model,Details dt, JTextArea dstxtpn) {
		Logger lgr = new Logger("DevStat");
		try {
			final String stg  = "https://stage-rdm.aujas.com/rdm-device-app/deviceStatus";
			final String pre  = "https://pre-rdm.aujas.com/rdm-device-app/deviceStatus";
			final String prod = "https://rdm.aujas.com/rdm-device-app/deviceStatus";
			final String stg_lk = "CUKhZCTq3/70CoKTWVZDOILQkU5GXpqLImV60C+aOMA=";
			final String stg_lkh = "e51544986c0e1795be9d61e26670d36a1ca9a97a9f88feaf6548373a086ad17e";
			final String prod_lk = "pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";
			final String prod_lkh = "XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";
			
			String url = "";
			String flk = "";
			String flkh = "";
			String sEnvFull = "Staging";
			if (env==mEnv.Staging) {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Staging";
			} else if (env==mEnv.Pre_Prod) {
				url = pre;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Pre Production";
			} else if (env==mEnv.Production) {
				url = prod;
				flk = prod_lk;
				flkh = prod_lkh;
				sEnvFull = "Production";
			} else {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Staging";
			}
			
			lgr.add("Device Status for "+sEnvFull);
			lgr.add("of serial no "+dt.devSerNo);
			
			
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = sf.format(d);
			
			Log.disperr("Today = "+strDate);
			
			String strDate1 = strDate+":23:59:59";
			long sad = new SimpleDateFormat("dd-MM-yyyy:HH:mm:ss").parse(strDate1).getTime();//d.getTime();
			Log.disperr("long  : "+sad);
			
			long validity = dt.Validity;
			long epoch = validity*24*60*60*1000+sad;
			Log.disperr("epoch : "+epoch);
			
			Date sEpoch = new Date(epoch);
			String strDateFinal = sf.format(sEpoch);
			Log.disperr("Till  = "+strDateFinal);
			
			String propertyFile = "tenantLK = "+flk+"\r\n"
						+ "tenantLKHash = "+flkh+"\r\n"
						+ "url = "+url+"\r\n"//"https://rdm.aujas.com/rdm-device-app/registration"
						//message = {"complianceLevel" : "L0", "serialNo" : "LEPEGCC2357", "modelId" : "LEOPARD"}
						+ "message = {\"complianceLevel\" : \"L0\", \"serialNo\" : \""+dt.devSerNo+"\", \"modelId\" : \""+model.toString()+"\"}"+"\r\n"
						//+ "message = {\"token\" : \""+dt.sToken+"\"}"+"\r\n"		
						+ "requestType = POST"+"\r\n";
			Log.disperr("File content :\n"+propertyFile);
			Log.disperr("~~~~~~~~~~~~~~~~~");
			
			File fl = new File("tmp.prop");
			if (fl.exists()){
				fl.delete();
			}
			fl.createNewFile();
			FileOutputStream fos = new FileOutputStream(fl);
			fos.write(propertyFile.getBytes());
			fos.close();
			
			Runtime rt = Runtime.getRuntime();
			//String cmdString = "cmd /c type tmp.prop";
			String cmdString = "java -cp SendRequest.jar;groovy-2.4.5.jar SendRequest tmp.prop";
			// String cmdString = "cmd /c dir";
			// String cmdString = "java -cp TokenAllocation.jar;groovy-2.4.5.jar TokenAllocation " + qty + " " + code + " " + epoch;
			// String cmdString = "java -cp TokenAllocation.jar;groovy-2.4.5.jar TokenAllocation 10 AUJ 1501439400000";
			Log.disperr("Command : "+cmdString);
			Log.disperr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Process pr = null;// = rt.exec(cmdString);
			try{
				pr = rt.exec(cmdString);
				Log.disperr("try block");
			}catch (Exception e){
				Log.disperr("rt error response catch"+e);
			}
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
	
			while ((line = input.readLine()) != null) {
				Log.disperr(line);
				sb.append(line);
			}
			int exitVal = pr.waitFor();
			Log.disperr("Exited with error code " + exitVal);
//			dstxtpn.setText("Exited with error code " + exitVal);
			
			
			if (exitVal!=0){
				Log.disperr("Command Exited with error code " + exitVal);
				dstxtpn.setText("Command Exited with error code "+ exitVal);
				lgr.add("Command Exited with error code " + exitVal);
				
				BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
				dstxtpn.setText(input2.readLine());
				String line2 = null;
				while ((line2 = input2.readLine()) != null) {
					Log.displn(line2);
					lgr.add(line2);
					
				}
				Log.disperr("Command Execution Failed");
				lgr.add("Command Execution Failed");
				lgr.add("~~~~~ LOG END ~~~~~");
				lgr.close();
				return; //System.exit(1);
			}

			String sParseDeviceStatus = sb.toString();
			Log.displn("================================");
			Log.disperr(sParseDeviceStatus);
			
			//String matter = sParseDeviceStatus.substring(sParseDeviceStatus.indexOf("hostId"));
			//Log.disperr(matter);
			//String noquote = matter.replaceAll("\"", "").replaceAll("}", "");
			//Log.disperr(noquote);
			//String sVals[] =noquote.split(",");
			Log.displn();
			
			String dsenv="Device Status for "+sEnvFull+"\n";
			String dsno="Device Serial Number is:  "+dt.devSerNo+"\n";
			String outtxt=dsenv+dsno;

			try {
				String s = "{\"complianceLevel\":\"L0\",\"deviceCode\":\"50714586-106c-44b7-bb94-5fb258886aec\",\"deviceStatus\":\"registered\","
						+"\"errors\":{\"errors\":[]},\"modelId\":\"LEOPARD\",\"registrationDetails\":[{\"hostId\":\"ec8b59a03cf6371a\",\"registeredOn\":\"1503845888061\""
						+",\"token\":\"EPP-9965-1119-3267\"}],\"serialNo\":\"LEPEGCC2357\"}";
				char[] cx = sParseDeviceStatus.toCharArray();
				String sss = "";
				for (char c :cx){
					if (c=='{'||c=='}'||c=='['||c==']')
						continue;
					sss = sss.concat(String.valueOf(c));
				}
				sss = sss.replaceAll("\"errors\":", "");
				Log.displn(sss);//Response::
				
				
				if(sss.contains("Response::"))
					sss = sss.replaceAll("Response::","");
				if(sss.contains("\"registrationDetails\":"))
					sss = sss.replaceAll("\"registrationDetails\":","");
				
				Log.displn("> > > "+sss);
				Log.displn();
				String [] sArr = sss.split(",");
				
				for (String ss : sArr) {
					if(ss.isEmpty())
						continue;
					//System.out.println("> "+ss);
					ss = ss.replaceAll("\"", "");
					String sData[] = ss.split(":");
					
					String s1="" , s2="";
					if (sData.length==1){
						s1 = sData[0];
					}else if (sData.length>=2){
						s1 = sData[0];
						s2 = sData[1];
					}
					
					String dsDisp = String.format("  %-18s = %s", s1.trim(), s2.trim());
					if (s2.length()>=13){
						try{
							long l = Long.parseLong(sData[1]);
							Date da = new Date(l);
							SimpleDateFormat sfa = new SimpleDateFormat("dd MMM yyyy - HH:mm:ss");
							String strDatea = sfa.format(da);
							dsDisp += " ["+strDatea+"]";  
						}catch (Exception e){
							
						}
					}
					
					Log.displn("  "+dsDisp);
					dstxtpn.setText(dsDisp);
					lgr.add(dsDisp);
					outtxt+=dsDisp+"\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
				dstxtpn.setText(e.toString());
			}
			dstxtpn.setText("");
			String dsUpdate = dstxtpn.getText();
			dsUpdate+= dsenv+"\n"+dsno+"\n"+"\n";
			dstxtpn.setText(outtxt);

//			dstxtpn.setText("device status is shown after parsing");
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			return;//System.exit(0);
		} catch (Exception e) {
			Log.displn("System Error "+e.toString());
			dstxtpn.setText("System Error "+e.getMessage());
			lgr.add("System Error "+e.toString());
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			e.printStackTrace();
			return; //System.exit(1);
		}
	}
	
	public static void vDeRegister(mEnv env, Details dt, JTextArea dertxtpn) {
		Logger lgr = new Logger("DeReg");
		try {
			final String stg  = "https://stage-rdm.aujas.com/rdm-device-app/registration";
			final String pre  = "https://pre-rdm.aujas.com/rdm-device-app/registration";
			final String prod = "https://rdm.aujas.com/rdm-device-app/registration";
			final String stg_lk = "CUKhZCTq3/70CoKTWVZDOILQkU5GXpqLImV60C+aOMA=";
			final String stg_lkh = "e51544986c0e1795be9d61e26670d36a1ca9a97a9f88feaf6548373a086ad17e";
			final String prod_lk = "pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";
			final String prod_lkh = "XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";
			
			String url = "";
			String flk = "";
			String flkh = "";
			String sEnvFull = "Staging";
			if (env==mEnv.Staging) {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Staging";
			} else if (env==mEnv.Pre_Prod) {
				url = pre;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Pre Production";
			} else if (env==mEnv.Production) {
				url = prod;
				flk = prod_lk;
				flkh = prod_lkh;
				sEnvFull = "Production";
			} else {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvFull = "Staging";
			}
			
			lgr.add("De Register Device for "+sEnvFull);
			lgr.add("of Device Code "+dt.devCode);
			
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = sf.format(d);
			Log.disperr("Today = "+strDate);
			String strDate1 = strDate+":23:59:59";
			long sad = new SimpleDateFormat("dd-MM-yyyy:HH:mm:ss").parse(strDate1).getTime();//d.getTime();
			Log.disperr("long  : "+sad);
			long validity = dt.Validity;
			long epoch = validity*24*60*60*1000+sad;
			Log.disperr("epoch : "+epoch);
			Date sEpoch = new Date(epoch);
			String strDateFinal = sf.format(sEpoch);
			Log.disperr("Till  = "+strDateFinal);
			
			String propertyFile = "tenantLK = "+flk+"\r\n"
						+ "tenantLKHash = "+flkh+"\r\n"
						+ "url = "+url+"\r\n"//"https://rdm.aujas.com/rdm-device-app/registration"
						//message : {"modelId": "LEOPARD", "deviceCode": "666a9fa6-6999-4cfc-b16a-d9e0753c1790"}
						+ "message = {\"modelId\": \""+dt.mi.toString()+"\", \"deviceCode\": \""+dt.devCode+"\"}"+"\r\n"
						+ "requestType = PUT"+"\r\n";
			Log.disperr("File content :\n"+propertyFile);
			Log.disperr("~~~~~~~~~~~~~~~~~");
			
			File fl = new File("tmp.prop");
			if (fl.exists()){
				fl.delete();
			}
			fl.createNewFile();
			FileOutputStream fos = new FileOutputStream(fl);
			fos.write(propertyFile.getBytes());
			fos.close();
			
			Runtime rt = Runtime.getRuntime();
			//String cmdString = "cmd /c type tmp.prop";
			String cmdString = "java -cp SendRequest.jar;groovy-2.4.5.jar SendRequest tmp.prop";
			Log.disperr("Command : "+cmdString);
			Log.disperr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Process pr = rt.exec(cmdString);
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = input.readLine()) != null) {
				Log.disperr(line);
				sb.append(line);
			}
			int exitVal = pr.waitFor();
			Log.disperr("Exited with error code " + exitVal);
		
			if (exitVal!=0){
				lgr.add("Command Exited with error code " + exitVal);
				BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
				String line2 = null;
				dertxtpn.setText(input2.readLine());
				StringBuilder sd = new StringBuilder();
				while ((line2 = input2.readLine()) != null) {
					Log.displn(line2);
					sd.append(line2);
					lgr.add(line2);
				}
//				dertxtpn.setText(sd.toString());
				Log.disperr("Command Execution Failed \n>>>>\n"+sd.toString());
				lgr.add("Command Execution Failed");
				lgr.add("~~~~~ LOG END ~~~~~");
				lgr.close();
				return; //System.exit(1);
			}
			Log.disperr("Command Execution Completed");
			String sParseTokenStatus = sb.toString();
			lgr.add(sParseTokenStatus);
			dertxtpn.setText("");
			String stUpdate = dertxtpn.getText();
			stUpdate+= sParseTokenStatus+"\n";
			dertxtpn.setText(stUpdate);
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			return;//System.exit(0);
		} catch (Exception e) {
			Log.displn("System Error "+e.toString());
			lgr.add("System Error "+e.toString());
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			e.printStackTrace();
			return; //System.exit(1);
		}
	}

	public static void vDeRegBulk(mEnv env, Details dt, JTextArea dertxtpn){ //TODO
		Logger lgr = new Logger("DeRegBulkMod_");
		try {//https://stage-rdm.aujas.com/rdm-device-app/registration
			final String stg  = "https://stage-rdm.aujas.com/rdm-device-app/registration";
			final String pre  = "https://pre-rdm.aujas.com/rdm-device-app/registration";
			final String prod = "https://rdm.aujas.com/rdm-device-app/registration";//"https://rdm.aujas.com/rdm-device-app/deviceWhitelisting";
			final String stg_lk = "CUKhZCTq3/70CoKTWVZDOILQkU5GXpqLImV60C+aOMA=";
			final String stg_lkh = "e51544986c0e1795be9d61e26670d36a1ca9a97a9f88feaf6548373a086ad17e";
			final String prod_lk = "pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";//"pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";
			final String prod_lkh = "XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";//"XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";
			
			String url = "",sEnvNow = "S",sEnvFull = "Staging";
			String flk = "";
			String flkh = "";
			if (env==mEnv.Staging) {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "S";sEnvFull = "Staging";
			} else if (env==mEnv.Pre_Prod) {
				url = pre;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "PP";sEnvFull = "Pre-Production";
			} else if (env==mEnv.Production) {
				url = prod;
				flk = prod_lk;
				flkh = prod_lkh;
				sEnvNow = "P";sEnvFull = "Production";
			} else {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "S";sEnvFull = "Staging";
			}
			String s1="De White Listing for "+sEnvFull+"\n";
			
			
			lgr.add(s1.trim());
			

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
			String s5="De White Listed on         "+sToday+" ["+sad+"]"+"\n";
			
			lgr.add(s5.trim());
			///////////////
			String sList = "";
			
			if (dt.blSingleDev==true){
				Log.displn("Single Device Input "+dt.devSerNo);
				lgr.add("Single Device Input "+dt.devSerNo);
				sList= dt.devSerNo;
				if (!sList.endsWith(","))
					sList = sList+",";
			} else{
				Log.displn("Multiple Device Input File :"+dt.devSerNo);
				lgr.add("Multiple Device Input File :"+dt.devSerNo);
				File fli = new File(dt.devSerNo);
				if (fli==null||!fli.exists()){
					Log.disp("Input File Error");
					dertxtpn.setText("Input File Error");
					lgr.add("Input File Error");
					lgr.add("~~~~~ LOG END ~~~~~");
					lgr.close();
					return;
				}
				FileInputStream fis = new FileInputStream(fli);
				BufferedInputStream bis = new BufferedInputStream(fis); 
				
				ByteBuffer buf = new ByteBuffer();
				byte[] bRd = new byte[4096];
				while (bis.available()>0){
					int len = bis.read(bRd);
					buf.appendBytes(bRd, len);
				}
				bis.close();
				byte[] bReadData = buf.getBuffer();			
				sList = new String(bReadData);
			}
			String sMidConstBeg ="message = { ";
			String sMi = dt.mi.toString();
			
			
			String sSerArrCsv[] = sList.split(",");
			if (sSerArrCsv==null) {
				lgr.add("Invalid CSV ");
				dertxtpn.setText("Invalid CSV ");
				Log.disperr("Invalid CSV ");
				
				lgr.close();
			}
			Log.displn("Input CSV Elements : "+sSerArrCsv.length);
			lgr.add("Input CSV Elements : "+sSerArrCsv.length);
		
			for (int i=0;i<sSerArrCsv.length;i++) {
				lgr.add(">>> >> > CSV Elements ["+(i+1)+"] "+sSerArrCsv[i]);
				String serNo = sSerArrCsv[i].trim().replaceAll(",","").toUpperCase();//dt.devSerNo;
				Log.displn("Chk 1 : "+sMi+"-"+serNo);
				String sEncOut = new String(HexString.bufferToHex(sMi.getBytes())+"-"+HexString.bufferToHex(serNo.getBytes()));
				Log.displn("Chk 2 : "+sEncOut);
				String currurl = url ;
				Log.displn("Chk 3 : " + currurl);
			
				String sHdr = "tenantLK = "+flk+"\r\n"
						+ "tenantLKHash = "+flkh+"\r\n"
						+ "url = "+currurl+ "\r\n";
				

				String sEachSerNo = "\"modelId\": \""+dt.mi.toString()+"\", \"deviceCode\": \""+sSerArrCsv[i].trim()+"\" ";
				String sMidConstEnd = " }"+"\r\n";
				String sFtr = "requestType = PUT"+"\r\n";

				String propertyFile1 = "tenantLK = "+flk+"\r\n"
						+ "tenantLKHash = "+flkh+"\r\n"
						+ "url = "+url+"\r\n"
						+ "message = {\"modelId\": \""+dt.mi.toString()+"\", \"deviceCode\": \""+dt.devCode+"\"}"+"\r\n"
						+ "requestType = PUT"+"\r\n";
				
				Log.disp(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);
				String propertyFile = new String(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);
	
				lgr.add("Input URL : ");
			
				lgr.add(currurl);
				Log.disperr("File content :\n"+propertyFile);
				Log.disperr("================================");
				File fl = new File("tmp.prop");
				if (fl.exists()){
					fl.delete();
				}
				fl.createNewFile();
				FileOutputStream fos = new FileOutputStream(fl);
				fos.write(propertyFile.getBytes());
				fos.close();
				
				Runtime rt = Runtime.getRuntime();
				
				String cmdString = "java -cp SendRequest.jar;groovy-2.4.5.jar SendRequest tmp.prop";
				
				Log.disperr("Command : "+cmdString);
				Log.disperr("================================");
				Process pr = rt.exec(cmdString);
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = input.readLine()) != null) {
					Log.displn(line);
					sb.append(line.trim()+"\n");
				}
				int exitVal = pr.waitFor();
				Log.disperr("Exited with error code " + exitVal);

				if (exitVal!=0){
					lgr.add("Error Occoured ");
					BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
					String line2 = null;
					dertxtpn.setText(input2.readLine());
				StringBuilder sd = new StringBuilder();
					while ((line2 = input2.readLine()) != null) {
						Log.disperr(line2);
						sd.append(line2);
						if (!line2.trim().startsWith("at"))
							lgr.add(line2.trim()+"\n"); 
					}
					Log.displn("Command Execution Failed\n>>>>\n"+sd.toString());
					lgr.add("Command Execution Failed");
					lgr.add("~~~~~ LOG END ~~~~~");
					continue;

				}
				Log.displn("Command Execution Complete");
				String sOut = sb.toString();
				Log.displn(sOut);
				lgr.add(sOut);
				lgr.add("~~~~~~~~~~~~~");
				
			}
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
		} catch (Exception e) {
			Log.displn("System Error\n"+e.toString());
			e.printStackTrace();
			lgr.add("System Error "+e.toString());
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			return;
		}
	}
	
	public static void vWhiteList(mEnv env, Details dt, JTextPane tvDeviceCode){
		Logger lgr = new Logger("WhiteList");
		try {
			final String stg  = "https://stage-rdm.aujas.com/rdm-device-app/deviceWhitelisting";
			final String pre  = "https://pre-rdm.aujas.com/rdm-device-app/deviceWhitelisting";
			final String prod = "https://rdm.aujas.com/rdm-device-app/deviceWhitelisting";//"https://rdm.aujas.com/rdm-device-app/deviceWhitelisting";
			final String stg_lk = "CUKhZCTq3/70CoKTWVZDOILQkU5GXpqLImV60C+aOMA=";
			final String stg_lkh = "e51544986c0e1795be9d61e26670d36a1ca9a97a9f88feaf6548373a086ad17e";
			final String prod_lk = "pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";//"pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";
			final String prod_lkh = "XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";//"XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";
			//env = mEnv.Production;
			
			String url = "",sEnvNow = "S",sEnvFull = "Staging";
			String flk = "";
			String flkh = "";
			if (env==mEnv.Staging) {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "S";sEnvFull = "Staging";
			} else if (env==mEnv.Pre_Prod) {
				url = pre;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "PP";sEnvFull = "Pre-Production";
			} else if (env==mEnv.Production) {
				url = prod;
				flk = prod_lk;
				flkh = prod_lkh;
				sEnvNow = "P";sEnvFull = "Production";
			} else {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "S";sEnvFull = "Staging";;
			}
			String s1="White Listing for "+sEnvFull+"\n";
			//String s2="Tokens Issued to  "+dt.Name+"\n";
			String s3="Code is           "+dt.Code+"\n";
			//String s4="Quantity          "+dt.Quantity+"\n";
			
		
			lgr.add(s1.trim());
			//lgr.add(s2.trim());
			lgr.add(s3.trim());
			//lgr.add(s4.trim());
			
//			 pwLogIt.append("hello");
//			    pwLogIt.close ();  
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
			String s5="White Listed on         "+sToday+" ["+sad+"]"+"\n";
			
			lgr.add(s5.trim());
			

			String sList = "";
			String sCsvFileNameInput = "";
			mMi model = dt.mi;
			if (dt.blSingleDev==true){
				//asd;
				Log.displn("Single Device Input : "+dt.devSerNo);
				lgr.add("Single Device Input : "+dt.devSerNo);
				sList = dt.devSerNo.trim();
				if (!sList.endsWith(","))
					sList = sList+",";
			} else {
				Log.displn("Multiple Device Input File : "+dt.devSerNo);
				lgr.add("Multiple Device Input File : "+dt.devSerNo);
				//File fli = new File(sCscFileNameWhiteList+".csv");
				sCsvFileNameInput = dt.devSerNo;
				File fli = new File(sCsvFileNameInput);
				if (fli==null || !fli.exists()) {
					Log.disperr("File Error");
					lgr.add("File error");
					lgr.add("~~~~~ LOG END ~~~~~");
					lgr.close();
					return;
				}
				FileInputStream fis = new FileInputStream(fli);
				BufferedInputStream bis = new BufferedInputStream(fis); //(new FileReader("readme.txt"));
				
				ByteBuffer buf = new ByteBuffer();
				byte[] bRd = new byte[4096];
				while (bis.available()>0){
					int len = bis.read(bRd);
					buf.appendBytes(bRd, len);
				}
				bis.close();
				byte[] bReadData = buf.getBuffer();
				
				sList = new String(bReadData);//"LEPEFCA0001,LEPEFCA0002,LEPEFCA0003,LEPEFCA0004,LEPEFCA0005,LEPEFCA0006";
			}
			String sMidConstBeg ="message = {\"customerCode\": \""+dt.Code+"\", \"devices\": [";
			
			//String sEachSerNo = "{\"serialNo\": \""+serArr+"\", \"modelId\": \""+mi+"\"}";
			String sHdr = "tenantLK = "+flk+"\r\n"
					+ "tenantLKHash = "+flkh+"\r\n"
					+ "url = "+url+"\r\n";
			String sSerArrCsv[] = sList.split(",");
			if (sSerArrCsv==null||sSerArrCsv.length==0){
				lgr.add("Invalid CSV ");
			
				Log.disperr("Invalid CSV ");
				lgr.close();
			}
			Log.displn("Input CSV Elements : "+sSerArrCsv.length);
			tvDeviceCode.setText("Input CSV Elements : "+sSerArrCsv.length);
			lgr.add("Input CSV Elements : "+sSerArrCsv.length);
			StringBuilder sMid = new StringBuilder();
			for (int i=0;i<sSerArrCsv.length;i++) {
				
				String sSerNoUI = sSerArrCsv[i].trim().toUpperCase();
				
				if (model==mMi.LEOPARD) {
					if (sSerNoUI.length()==9 && sSerNoUI.startsWith("L"))
						sSerNoUI = sSerNoUI.replace("L", "LEP");
					if (!sSerNoUI.startsWith("LEP") || sSerNoUI.length()!= 11){
						lgr.add("Aborted due to Invalid Serial Number "+sSerNoUI+" at "+(i+1));
						lgr.add("~~~~~ LOG END ~~~~~");
						lgr.close();
						return;
					}
				} else if (model==mMi.FALCON) {
					if (sSerNoUI.length()==9 && sSerNoUI.startsWith("F"))
						sSerNoUI.replace("F", "FIMP");
					if (!sSerNoUI.startsWith("FIMP") || sSerNoUI.length()!= 12){
						lgr.add("Aborted due to Invalid Serial Number "+sSerNoUI+" at "+(i+1));
						lgr.add("~~~~~ LOG END ~~~~~");
						lgr.close();
						return;
					}
				} else if (model==mMi.IMPRESS) {
					if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
						sSerNoUI = sSerNoUI.replace("I", "IMP");
					if (!sSerNoUI.startsWith("IMP") || sSerNoUI.length()!= 11){
						lgr.add("Aborted due to Invalid Serial Number "+sSerNoUI+" at "+(i+1));
						lgr.add("~~~~~ LOG END ~~~~~");
						lgr.close();
						return;
					}
				} else if (model==mMi.LILY) {
					if (sSerNoUI.length()==9 && sSerNoUI.startsWith("G"))
						sSerNoUI = sSerNoUI.replace("G", "LIMP");
					if (!sSerNoUI.startsWith("LIMP") || sSerNoUI.length()!= 12){
						lgr.add("Aborted due to Invalid Serial Number "+sSerNoUI+" at "+(i+1));
						lgr.add("~~~~~ LOG END ~~~~~");
						lgr.close();
						return;
					}
				} else if (model==mMi.IDENTI5) {
					if (sSerNoUI.length()==9 && sSerNoUI.startsWith("I"))
						sSerNoUI.replace("I", "IIMP");
					if (!sSerNoUI.startsWith("IIMP") || sSerNoUI.length()!= 12){
						lgr.add("Aborted due to Invalid Serial Number "+sSerNoUI+" at "+(i+1));
						lgr.add("~~~~~ LOG END ~~~~~");
						lgr.close();
						return;
					}
				} else if (model==mMi.ESPVPOCMI) {
					// DONT CARE
				}
				
				
				if (i==sSerArrCsv.length-1)
					sMid.append("{\"serialNo\": \""+sSerNoUI+"\", \"modelId\": \""+model.toString()+"\"}");
				else
					sMid.append("{\"serialNo\": \""+sSerNoUI+"\", \"modelId\": \""+model.toString()+"\"},");
			}
			String sEachSerNo = sMid.toString();//"{\"serialNo\": \""+serArr+"\", \"modelId\": \""+mi+"\"}";
			
			String sMidConstEnd = "]}"+"\r\n";
			
			String sFtr = "requestType = POST"+"\r\n";
			
			Log.disp(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);
			tvDeviceCode.setText(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);
			String propertyFile = new String(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);
//			lgr.add();
//			lgr.add(sCscFileNameWhiteList);
//			Log.displn(sCscFileNameWhiteList);
		
			lgr.add("Command Input File : ");
			lgr.add(propertyFile);
			
			Log.disperr("File content :\n"+propertyFile);
			Log.disperr("================================");
			File fl = new File("tmp.prop");
			if (fl.exists()){
				fl.delete();
			}
			fl.createNewFile();
			FileOutputStream fos = new FileOutputStream(fl);
			fos.write(propertyFile.getBytes());
			fos.close();
			
			Runtime rt = Runtime.getRuntime();
			//String cmdString = "cmd /c type tmp.prop";
			String cmdString = "java -cp SendRequest.jar;groovy-2.4.5.jar SendRequest tmp.prop";
			Log.disperr("Command : "+cmdString);
			Log.disperr("================================");
			Process pr = rt.exec(cmdString);
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = input.readLine()) != null) {
				Log.disperr(line);
				sb.append(line);
			}
			int exitVal = pr.waitFor();
			Log.disperr("Exited with error code " + exitVal);
//			tvDeviceCode.setText("Exited with error code " + exitVal);
			if (exitVal!=0){
				lgr.add("Error Occoured ");
				BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
				String line2 = null;
				while ((line2 = input2.readLine()) != null) {
//					tvDeviceCode.setText(input2.readLine());
					Log.displn(line2);
//					tvDeviceCode.setText(line2);
					lgr.add(line2); 
				}
				Log.displn("Command Execution Failed");
				tvDeviceCode.setText("Command Execution Failed");
				lgr.add("Command Execution Failed");
				lgr.add("~~~~~ LOG END ~~~~~");
				lgr.close();
				return;//System.exit(1);
			}
			
			Log.displn("Command Execution Complete");
			String sOut = sb.toString();
//			sOut = sOut.replaceAll("[\x7B", "[\n\{");
//			sOut = sOut.replaceAll("]\}", "]\n\}");
//			sOut = sOut.replaceAll("\},\{", "\\},\n\{");
			tvDeviceCode.setText(sOut);
			Log.displn(sOut);
			lgr.add(sOut);
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
		} catch (Exception e) {
			Log.displn("System Error\n"+e.toString());
			e.printStackTrace();
			lgr.add("System Error "+e.toString());
			tvDeviceCode.setText("System Error "+e.toString());
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			return;//System.exit(1);
		}
	}
	
	public static void vDeWhiteList(mEnv env, mMi model,Details dt, JTextPane dwltxtpn){
		Logger lgr = new Logger("DeWhiteList");
		try {
			final String stg  = "https://stage-rdm.aujas.com/rdm-device-app/deviceWhitelisting/";
			final String pre  = "https://pre-rdm.aujas.com/rdm-device-app/deviceWhitelisting/";
			final String prod = "https://rdm.aujas.com/rdm-device-app/deviceWhitelisting/";//"https://rdm.aujas.com/rdm-device-app/deviceWhitelisting";
			final String stg_lk = "CUKhZCTq3/70CoKTWVZDOILQkU5GXpqLImV60C+aOMA=";
			final String stg_lkh = "e51544986c0e1795be9d61e26670d36a1ca9a97a9f88feaf6548373a086ad17e";
			final String prod_lk = "pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";//"pVSHVPhOq52Dm2m6nd7+jg5X90apQVdNzYo3d4Zc44Q=";
			final String prod_lkh = "XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";//"XF4GX4UOPAXzYiD7K47VlUWHlZ9IXb4RIc87AT6XrqM=";
			//env = mEnv.Production;
			
			String url = "",sEnvNow = "S",sEnvFull = "Staging";
			String flk = "";
			String flkh = "";
			if (env==mEnv.Staging) {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "S";sEnvFull = "Staging";
			} else if (env==mEnv.Pre_Prod) {
				url = pre;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "PP";sEnvFull = "Pre-Production";
			} else if (env==mEnv.Production) {
				url = prod;
				flk = prod_lk;
				flkh = prod_lkh;
				sEnvNow = "P";sEnvFull = "Production";
			} else {
				url = stg;
				flk = stg_lk;
				flkh = stg_lkh;
				sEnvNow = "S";sEnvFull = "Staging";
			}
			String s1="De White Listing for "+sEnvFull+"\n";
			//String s2="Tokens Issued to  "+dt.Name+"\n";
			//String s3="Code is           "+dt.Code+"\n";
			//String s4="Quantity          "+dt.Quantity+"\n";
			
			
			lgr.add(s1.trim());
			
			//lgr.add(s2.trim());
			//lgr.add(s3.trim());
			//lgr.add(s4.trim());

			Date d = new Date();//IMPVCAA0009IMPVCAA0009
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
			String s5="De White Listed on         "+sToday+" ["+sad+"]"+"\n";
			//String s6="For               "+dt.Validity+" days"+"\n";
			//String s7="Valid upto        "+sTill+"["+epoch+"]"+"\n";
			lgr.add(s5.trim());
			///////////////
			String sList = "";
			
			if (dt.blSingleDev==true){
				Log.displn("Single Device Input "+dt.devSerNo);
				lgr.add("Single Device Input "+dt.devSerNo);
				sList= dt.devSerNo;
				if (!sList.endsWith(","))
					sList = sList+",";
			} else{
				Log.displn("Multiple Device Input File :"+dt.devSerNo);
				lgr.add("Multiple Device Input File :"+dt.devSerNo);
				File fli = new File(dt.devSerNo);
				if (fli==null||!fli.exists()){
					Log.disp("Input File Error");
					lgr.add("Input File Error");
					lgr.add("~~~~~ LOG END ~~~~~");
					lgr.close();
					return;
				}
				FileInputStream fis = new FileInputStream(fli);
				BufferedInputStream bis = new BufferedInputStream(fis); //(new FileReader("readme.txt"));
				
				ByteBuffer buf = new ByteBuffer();
				byte[] bRd = new byte[4096];
				while (bis.available()>0){
					int len = bis.read(bRd);
					buf.appendBytes(bRd, len);
				}
				bis.close();
				byte[] bReadData = buf.getBuffer();			
				////////////////
				sList = new String(bReadData);//"LEPEFCA0001,LEPEFCA0002,LEPEFCA0003,LEPEFCA0004,LEPEFCA0005,LEPEFCA0006";
			}
			String sMidConstBeg ="message = { ";//\"customerCode\": \""+dt.Code+"\", \"devices\": [";
			String sMi = model.toString();
			///////////
			
			
			String sSerArrCsv[] = sList.split(",");
			if (sSerArrCsv==null) {
				lgr.add("Invalid CSV ");
				Log.disperr("Invalid CSV ");
				lgr.close();
			}
			Log.displn("Input CSV Elements : "+sSerArrCsv.length);
			lgr.add("Input CSV Elements : "+sSerArrCsv.length);
			
			for (int i=0;i<sSerArrCsv.length;i++) {
				lgr.add(">>> >> > CSV Elements ["+(i+1)+"] "+sSerArrCsv[i]);
				String serNo = sSerArrCsv[i].trim().replaceAll(",","").toUpperCase();//dt.devSerNo;
				Log.displn("Chk 1 : "+sMi+"-"+serNo);
				String sEncOut = new String(HexString.bufferToHex(sMi.getBytes())+"-"+HexString.bufferToHex(serNo.getBytes()));
				Log.displn("Chk 2 : "+sEncOut);
				String currurl = url + sEncOut;
				Log.displn("Chk 3 : " + currurl);
			
				//String sEachSerNo = "{\"serialNo\": \""+serArr+"\", \"modelId\": \""+mi+"\"}";
				String sHdr = "tenantLK = "+flk+"\r\n"
						+ "tenantLKHash = "+flkh+"\r\n"
						+ "url = "+currurl+ "\r\n";
				
				//////////////
	//			StringBuilder sMid = new StringBuilder();
	//			for (int i=0;i<sSerArrCsv.length;i++) {
	//				if (i==sSerArrCsv.length-1)
	//					sMid.append("{\"serialNo\": \""+sSerArrCsv[i].trim().toUpperCase()+"\", \"modelId\": \""+dt.mi+"\"}");
	//				else
	//					sMid.append("{\"serialNo\": \""+sSerArrCsv[i].trim().toUpperCase()+"\", \"modelId\": \""+dt.mi+"\"},");
	//			}
	//			String sEachSerNo = sMid.toString();//"{\"serialNo\": \""+serArr+"\", \"modelId\": \""+mi+"\"}";
				///////////////
				String sEachSerNo="";
				String sMidConstEnd = " }"+"\r\n";
				String sFtr = "requestType = DELETE"+"\r\n";
				//String sFtr = "requestType = POST"+"\r\n";
				
				Log.disp(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);
				String propertyFile = new String(sHdr+sMidConstBeg+sEachSerNo+sMidConstEnd+sFtr);

				lgr.add("Input URL : ");
				//lgr.add(propertyFile);
				lgr.add(currurl);
				Log.disperr("File content :\n"+propertyFile);
				Log.disperr("================================");
				File fl = new File("tmp.prop");
				if (fl.exists()){
					fl.delete();
				}
				fl.createNewFile();
				FileOutputStream fos = new FileOutputStream(fl);
				fos.write(propertyFile.getBytes());
				fos.close();
				
				Runtime rt = Runtime.getRuntime();
				//String cmdString = "cmd /c type tmp.prop";
				String cmdString = "java -cp SendRequest.jar;groovy-2.4.5.jar SendRequest tmp.prop";
				Log.disperr("Command : "+cmdString);
				Log.disperr("================================");
				Process pr = rt.exec(cmdString);
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = input.readLine()) != null) {
					Log.displn(line);
					sb.append(line.trim()+"\n");
				}
				int exitVal = pr.waitFor();
				Log.disperr("Exited with error code " + exitVal);
				if (exitVal!=0) {
					lgr.add("Error Occoured ");
					BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
					String line2 = null;
					dwltxtpn.setText(input2.readLine());
					while ((line2 = input2.readLine()) != null) {
						Log.disperr(line2);
						lgr.add(line2.trim()+"\n"); 
					}
					Log.displn("Command Execution Failed");
					//dwltxtpn.setText("Command Execution Failed");
					lgr.add("Command Execution Failed");
					lgr.add("~~~~~ LOG END ~~~~~");
					lgr.close();
					return;//System.exit(1);
				}
				Log.displn("Command Execution Complete");
				String sOut = sb.toString();
				//sOut = sOut.replaceAll("[{", "[\n{").replaceAll("]}", "]\n}").replaceAll("},{", "},\n{");
				Log.displn(sOut);
				dwltxtpn.setText(sOut);
				lgr.add(sOut);
				lgr.add("~~~~~ LOG END ~~~~~");
			}
			lgr.close();
		} catch (Exception e) {
			Log.displn("System Error\n"+e.toString());
			dwltxtpn.setText("System Error\n"+e.toString());
			e.printStackTrace();
			lgr.add("System Error "+e.toString());
			lgr.add("~~~~~ LOG END ~~~~~");
			lgr.close();
			return;//System.exit(1);
		}
	}
}
