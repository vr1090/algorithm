package week1;

import java.util.Arrays;

public class UF {
	
	int[] array= null;
	
	public UF(int N)
	{
		array = new int[N];
		
		for( int i=0; i< N; i++)
			array[i] = i;
	}//end of constructor
	
	
	public void union(int p, int q)
	{
		int pid = array[p];
		int qid = array[q];
		
		
		for(int i=0; i< array.length;i++)
		{
			if( pid == array[i])
				array[i] = qid;
		}//end for
		
	}//end of method
	
	public void print()
	{
		System.out.println( Arrays.toString(array));
	}//end of method
	
	public static void main(String[] args)
	{
		UF ucup = new UF(10);
		ucup.union(2,1);
		ucup.union(2,9);
		ucup.union(9,6);
		ucup.union(3,6);
		ucup.union(2,0);
		ucup.union(4,2);
		
		ucup.print();
		
		
	}//end of method
	
	
}//end of class
