// Forecast by Tim Johnson, Copyright 2023 by Tim Johnson, All Rights Reserved

import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;  

public class Forecast extends JFrame {
   public Forecast()
   {
      super( "Forecast" );
      setSize( 1000, 400 );
      show();
   }

   public void paint( Graphics g )
   {	
	   int moMinute = 0;	  
	  while(true) { 
		  Date d=new Date();  
		  int mMinute = d.getMinutes();
		  if (mMinute!=moMinute) {
				Font f = new Font("Verdana", Font.BOLD, 16);
				g.setFont(f);
				g.setColor( new Color( 50, 50, 50 ) );
				g.fillRect( 0, 0, 2000, 1000 );
				g.setColor( new Color( 150, 150, 150 ) );
				g.drawString( "Sacramento California Weather Forecast 2023", 300, 65);
				f = new Font("Arial", Font.BOLD, 10);
				g.setFont(f);
				g.drawString( "this screen updated once every minute - cntrl-c to close", 300, 85);
				f = new Font("Verdana", Font.BOLD, 17);
				g.setFont(f);
				int mCount=0;
				int mPosX = 130;
				int mPosY = 120;
				String moCountry = "";
				String moState = "";
				String moCity = "";				
				int moYear = 0;
				int moMonth = 0;
				int moDay = 0;
				int moHighTemperature = 0;
				int moLowTemperature = 0;	
				try {
					RandomAccessFile ifile = new RandomAccessFile("Weather.csv", "r");
					String line;
					while ((line = ifile.readLine()) != null) {			
						String[] stringarray = line.split(";"); 				
						String mCountry = stringarray[0];
						String mState = stringarray[1];
						String mCity = stringarray[2];				
						int mYear = Integer.parseInt(stringarray[3]);
						int mMonth = Integer.parseInt(stringarray[4]);
						int mDay = Integer.parseInt(stringarray[5]);
						int mHighTemperature = Integer.parseInt(stringarray[6]);
						int mLowTemperature = Integer.parseInt(stringarray[7]);							
						String mOutput1 = "";
						if (mYear != moYear) {mOutput1 = mOutput1 + mYear + " ";}
						if (mMonth != moMonth) {
							if (mMonth==1) {mOutput1 = mOutput1 + "January ";}
							if (mMonth==2) {mOutput1 = mOutput1 + "Feberuary ";}
							if (mMonth==3) {mOutput1 = mOutput1 + "March ";}
							if (mMonth==4) {mOutput1 = mOutput1 + "April ";}
							if (mMonth==5) {mOutput1 = mOutput1 + "May ";}
							if (mMonth==6) {mOutput1 = mOutput1 + "June ";}
							if (mMonth==7) {mOutput1 = mOutput1 + "July ";}
							if (mMonth==8) {mOutput1 = mOutput1 + "August ";}
							if (mMonth==9) {mOutput1 = mOutput1 + "September ";}
							if (mMonth==10) {mOutput1 = mOutput1 + "October ";}
							if (mMonth==11) {mOutput1 = mOutput1 + "November ";}
							if (mMonth==12) {mOutput1 = mOutput1 + "December ";}					
							}
						if (mDay != moDay) {mOutput1 = mOutput1 + mDay;}
						mOutput1 = mOutput1 + " | " + mHighTemperature + "F high | " + mLowTemperature + "F low";	
						mCount++;
						if (mCount > 8) {mPosY = 120; mPosX = mPosX + 500; mCount=0;}
						g.setColor( new Color( 255, 0, 0 ) );
						g.fillRect( mPosX - 100, mPosY, mHighTemperature, 20 );
						g.setColor( new Color( 0, 0, 255 ) );
						g.fillRect( mPosX - 100, mPosY, mLowTemperature, 20 );
						g.setColor( new Color( 150, 150, 150 ) );
						g.drawString( mOutput1, mPosX, mPosY + 16);
						mPosY = mPosY + 30;
						moCountry = mCountry;
						moState = mState;
						moCity = mCity;
						moYear = mYear;
						moMonth = mMonth;
						moDay = mDay;
						moHighTemperature = mHighTemperature;
						moLowTemperature = mLowTemperature;
						}
				ifile.close(); 	 			
				} 		
		catch (IOException e) { 	
			System.err.println(e); 	 	
			return; 	
		}
		moMinute = mMinute;   
		}
	}
}

public static void main( String args[] )
   {
      Forecast app = new Forecast();
      app.addWindowListener(
         new WindowAdapter() {
            public void windowClosing( WindowEvent e )
            {
               System.exit( 0 );
            }
         }
      );
   }
}
