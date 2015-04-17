import java.util.*;
import java.io.*;

public class JourneyTotheMoon {
	static boolean logging = true;
	static boolean submit = false;
	static String file="journey.txt"; 
	
	public static void main(String[] args) throws Exception
	{
		Scanner scan = getScanner();
		
		String firstLine = scan.nextLine();
		String[] parseFirst = firstLine.split(" ");
		
		logging(firstLine);
		
		int N = Integer.parseInt(parseFirst[0]); //banyak array
		int I = Integer.parseInt(parseFirst[1]); // banyak pasangan yg di parse ulang..
		
		//bikin id orang dulu..
		int[] persons = new int[N];
		
		//bikin possible country
		Stack<Integer> antrianCountry = new Stack<Integer>();
		
		//init dulu juga
		for(int i=0; i<N; i++)
			persons[i] = -1;
		
		//push dulu..
		for(int i=0; i< N/2 +1;i++)
			antrianCountry.push(i);
		
		//mulai parsing lagi
		for( int couple=0; couple < I; couple++)
		{
			String data = scan.nextLine();
			String[] dataPerson = data.split(" ");
			
			int astronotA = Integer.parseInt(dataPerson[0] );
			int astronotB = Integer.parseInt(dataPerson[1] );
			
			logging("===========");
			logging("parsingan.."+data);
			
			
			logging("init nilai "+ persons[astronotA]+":"+ persons[astronotB]);
			if( !( haveId(astronotA,persons,antrianCountry) || haveId(astronotB,persons,antrianCountry)))  
			{
				//aman om..
				//ambil id baru..
				logging("bikin baru id nya om");
				int newId = antrianCountry.pop();
				persons[astronotA] = newId;
				persons[astronotB] = newId;
			}//end of if
			else if( ( haveId(astronotA,persons,antrianCountry) && haveId(astronotB,persons,antrianCountry) ) )
			{
				logging("dua2 nya punya id yah..");
				logging("perlu merging neh");
				int idBaru = persons[astronotA] < persons[astronotB]? persons[astronotA]:persons[astronotB];
				int idLama = persons[astronotA] > persons[astronotB]? persons[astronotA]:persons[astronotB];
				
				persons[astronotA]=idBaru;
				persons[astronotB]= idBaru;
				
				for(int ganti=0; ganti < persons.length; ganti++)
				{
					if( persons[ganti]== idLama)
						persons[ganti]= idBaru;
				}//end for
				
			}//end else if
			else
			{
				//salah satu punya id..
				logging("ambil id salah satunya..");
				int newId = haveId(astronotA,persons,antrianCountry)? persons[astronotA]:persons[astronotB];
				persons[astronotA] = newId;
				persons[astronotB] = newId;
			}//end else
			
			logging(Arrays.toString( persons ) );
		}//end for
		logging( Arrays.toString(persons));
		long combination=0;
		
		//itung lah om..
		for(int i=0; i< persons.length;i++)
		{
			for(int j=i+1;j< persons.length;j++)
			{
				if(persons[i]!=persons[j])
				{
					logging( persons[i] +"..."+persons[j]);
					combination++;
				}//end if
			}//end for
		}//end for
		
		System.out.println(combination);
		
	}//end of method
	
	static boolean haveId(int person, int[] mapping, Stack<Integer> listCountry)
	{
		return mapping[person] != -1;
	}//end of method
	
	static Scanner getScanner() throws Exception
	{
		Scanner scan;
		
		if(submit)
			scan = new Scanner(System.in);
		else
			scan = new Scanner(new FileInputStream(file));
		
		return scan;
	}//end of method
	
	static void logging(String s)
	{
		if(logging)
			System.out.println(s);
	}//end of method
}//end of class
