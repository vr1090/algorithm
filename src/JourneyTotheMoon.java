import java.util.*;
import java.io.*;

public class JourneyTotheMoon {
	static boolean logging = true;
	static boolean submit = false;
	static String file="journey.txt"; 
	static int[] persons;
	
	public static void main(String[] args) throws Exception
	{
		Scanner scan = getScanner();
		
		String firstLine = scan.nextLine();
		String[] parseFirst = firstLine.split(" ");
		
		logging(firstLine);
		
		int N = Integer.parseInt(parseFirst[0]); //banyak array
		int I = Integer.parseInt(parseFirst[1]); // banyak pasangan yg di parse ulang..
		
		//bikin id orang dulu..
		persons = new int[N];
		
		
		
		//init dulu juga
		for(int i=0; i<N; i++)
			persons[i] = i;
		
		
		//mulai parsing lagi
		for( int couple=0; couple < I; couple++)
		{
			String data = scan.nextLine();
			String[] dataPerson = data.split(" ");
			
			int astronotA = Integer.parseInt(dataPerson[0] );
			int astronotB = Integer.parseInt(dataPerson[1] );
			
			union(astronotA,astronotB);
			
		}//end for
		
		//tambah step.. buat pake uick union
		for( int i=0; i< persons.length;i++)
			persons[i] = root(i);
		
		logging( Arrays.toString(persons));
		
		
		
		long combination=0;
		
		Map<Integer,Integer> maps = new HashMap<Integer,Integer>();
		
		for( int i=0; i< persons.length;i++)
		{
			if( maps.get(persons[i]) == null)
			{
				maps.put(persons[i], 1);
			}//end if
			else
			{
				int value = maps.get(persons[i]);
				maps.put(persons[i], value+1);
			}//end else
		}//end if
		
		Integer[] arrays = maps.values().toArray(new Integer[maps.size()]);
		logging( Arrays.toString(arrays));
		
		for( int i=0; i< arrays.length;i++)
		{
			for(int j=i+1; j< arrays.length;j++)
			{
				combination = combination + arrays[i] * arrays[j];
			}
		}//end for
		
		System.out.println(combination);
		
	}//end of method
	
	static void union(int a,int b)
	{
		logging("==========");
		logging("union.."+a +"...with.."+b);
		logging(Arrays.toString(persons));
		
		//int idA = persons[a];
		//int idB = persons[b];
		
		//for( int i=0; i< persons.length;i++)
		//{
		//	if( persons[i]== idB)
		//		persons[i] = idA;
		//}//end for
		
		/**
		 * codingan quick union
		 */
		int parenta = root(a);
		int parentb = root(b);
		
		if( parenta == parentb )
			return;
		else if( parenta < parentb)
		{
			persons[b] = parenta;
		}//end else
		else
		{
			persons[a] = parentb;
		}//end else
		
		
		
		logging(Arrays.toString(persons));
		logging("========");
	}//end of method
	
	static int root(int a)
	{
		while(a != persons[a])
		{
			a = persons[a];
		}///end of while
		
		return a;
	}//end of method
	
	static boolean find(int a, int b)
	{
		return root(a) == root(b);
		//return persons[a] == persons[b];
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
