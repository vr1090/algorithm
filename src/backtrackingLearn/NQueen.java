package backtrackingLearn;

import java.util.Arrays;

public class NQueen {
	
	public static void main(String[] args)
	{
		int[] position = new int[4];
		
		queen(0,position);
		
	}//end of method
	
	public static void queen(int k,int[] position)
	{
		for( int i=0; i< position.length; i++)
		{
			if(isSafe(k, i, position))
			{
				position[k] = i;
				
				if( k == position.length-1)
				{
					String output = Arrays.toString(position);
					System.out.println(output);
					return;
				}//end if
				
				queen(k+1, position);
			}//end if
		}//end of for
	}//end of method
	
	
	public static boolean isSafe(int r, int c, int[] position)
	{
		for( int i=0; i< r; i++)
		{
			if( c == position[i])
				return false;
			
			if( Math.abs(r-i) == Math.abs(c - position[i]))
				return false;
		}//end for
		
		return true;
	}//end of method
	
}//end of class
