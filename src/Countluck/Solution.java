package Countluck;

import java.io.*;
import java.util.*;

public class Solution {
	static boolean submit = false;
	static boolean logging = false;
	static String file = "countluck.txt";
	
	public static void main(String[] args) throws Exception
	{
		Scanner scan = getScanner();
		String firstLine = scan.nextLine();
		int numTest = Integer.parseInt(firstLine);
		int[] addX ={1,-1,0,0};
		int[] addY ={0,0,1,-1};
		
		for( int test=1; test <= numTest; test++)
		{
			//ambil dimension
			String dimensionString = scan.nextLine();
			String[] parserDim = dimensionString.split(" ");
			int numR = Integer.parseInt(parserDim[0]);
			int numC = Integer.parseInt(parserDim[1]);
			
			char[][] peta = new char[numR][numC];
			Location startPos=null;
			int kTujuan =0;
			int kRealitas =0;
			Queue<Location> kui = new LinkedList<Location>(); 
			Queue<Integer> kuiNemu = new LinkedList<Integer>();
			ArrayList<Location> listVisisted = new ArrayList<Location>();
			
			for( int i=0; i<numR; i++)
			{
				StringBuffer buff = new StringBuffer(scan.nextLine() ); 
				
				for( int j=0; j< numC; j++)
				{
					peta[i][j] = buff.charAt(j);
					
					if( peta[i][j]=='M')
						startPos = new Location(i,j,numR,numC);
					
				}//end of for
				
			}//end of for i
			
			kTujuan = Integer.parseInt( scan.nextLine());
			
			//sekarang basa basi udah selesai
			//time to work seriusan..
			kui.add(startPos);
			kuiNemu.add(0);
			
			while(!kui.isEmpty())
			{
				//logging("jalna..");
				Location current = kui.poll();
				kRealitas = kuiNemu.poll();
				
				listVisisted.add(current);
				//logging( "visit.."+ current);
				
				if( peta[current.x][current.y]=='*'){ //findet!!
					logging("found it");
					break;
				}
				else
				{
					ArrayList<Location> newLocation = new ArrayList<Location>();
					
					for( int add =0; add< addX.length; add++)
					{
						Location locationCandidate = new Location(current.x+addX[add], 
								current.y+addY[add],numR,numC);
						
						if( locationCandidate.isValid(peta)  && !listVisisted.contains(locationCandidate))
							newLocation.add(locationCandidate);
							
					}//end of for
					
					
					if( current == startPos)
						logging( newLocation.toString());
					
					if(newLocation.size() > 1)
						kRealitas++;
					
					kui.addAll(newLocation);
					
					for( int i=0; i< newLocation.size();i++)
						kuiNemu.add(kRealitas);
					
				}//end else
				
			}//end of while
			
			logging("krealitas.."+kRealitas +".. tujuan.."+ kTujuan);
			
			if( kRealitas == kTujuan)
				System.out.println("Impressed");
			else
				System.out.println("Oops!");
			
		}//end of for
		
		
	}//end of method
	
	static class Location{
		public int x;
		public int y;
		public int rowMax;
		public int colMax;
		
		public Location(int x, int y, int rowMax, int colMax)
		{
			this.x =x;
			this.y = y;
			this.rowMax = rowMax;
			this.colMax = colMax;
		}//emd of method
		
		
		public boolean isValid(char[][] peta)
		{
			return (x>=0) && (x < rowMax) && (y>=0) &&  (y < colMax) && peta[x][y] != 'X' && peta[x][y]!='M' ;
		}//end of method
		
		@Override
		public String toString()
		{
			return "x:"+x +"..y:"+y +"..";
		}//end of method
		
		
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			Location b = (Location)obj;
			return this.x== b.x && this.y== b.y;
		}
		
	}//end of class
	
	static Scanner getScanner() throws Exception
	{
		Scanner scan;
		
		if(!submit)
		{
			scan = new Scanner(new FileInputStream(file));
		}//end if
		else
		{
			scan = new Scanner(System.in);
		}//end else
		
		return scan;
	}//end of method
	
	
	static void logging(String s)
	{
		if( logging)
			System.out.println(s);
	}//end method
	
}//end of class
