package backtrackingLearn;

import java.util.Arrays;

/**
 * kalo gw punya angak x1,x2,x3,x4,x5
 * bisa engga sih ni angka bakalan end up jadi angka tertrntu??
 * di solve pake teknik backtracking
 * @author inu
 *
 */
public class NumberAddition {
	public static void main(String[] args)
	{
		int[] angka ={1,2,3,4,5,5};
		int n= 5;
		int k=1;
		char[] solution = new char[n-1];
		
		solve(angka,solution,k,n,angka[0]);
		
	}//end of method
	
	static void solve( int[] angka, char[] solution, int k, int n, int result)
	{
		if( (k == n) && (result == angka[n]) )
		{
			System.out.println(angka[n]+".."+ result);
			//print out result
			System.out.print(angka[0] );
			for( int i=0; i < solution.length; i++)
				System.out.print(" "+solution[i]+ angka[i+1]);
			System.out.println();
		}//end of if
		else if( k==n)
		{
			return;
		}//end else
		else
		{
			int tempResult = result;
			//coba path pertama om..
			tempResult = tempResult + angka[k];
			solution[k-1] = '+';
			//recurse..
			solve(angka,solution,k+1,n, tempResult);
			
			//lu backtrack sekarang..
			tempResult = result;
			tempResult = tempResult - angka[k];
			solution[k-1]='-';
			solve(angka,solution,k+1,n, tempResult);
			
		}//end else
	}//end of method
}//end of class
