package week1;

import java.util.Arrays;

public class QuickUnion {
	
	private int[] master = null;
	private int[] size = null;
	int panjang=0;
	
	public QuickUnion(int N)
	{
		master = new int[N];
		size = new int[N];
		panjang = N;
		
		//init as usual
		for( int i=0; i< N; i++)
		{
			master[i] =i;
			size[i] =1;
		}//end for
	}//end of constructor
	
	
	private int findRoot( int node)
	{
		while( node != master[node])
		{
			node = master[node];
		}//end while
		
		return node;
	}//end of method
	
	
	public void union(int p,int q)
	{
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		
		if( size[rootP] < size[rootQ])
		{
			master[rootP] = rootQ;
			size[rootQ] = size[rootQ]+ size[rootP];
		}//end if
		else
		{
			master[rootQ] = rootP;
			size[rootP] = size[rootQ]+ size[rootP];
			
		}//end else
		
	
		
	}//end of method
	
	
	public boolean connected(int p, int q)
	{
		return findRoot(p)== findRoot(q);
	}//end of method
	
	public void print()
	{
		System.out.println( Arrays.toString(master)   );
	}//end of method
	
	
	public static void main(String[] args)
	{
		QuickUnion up = new QuickUnion(10);
		
		up.union(0,9);
		up.union(7,2);
		up.union(5,0);
		up.union(4,6);
		up.union(3,1);
		up.union(3,2);
		up.union(9,6);
		up.union(1,6);
		up.union(7,8);
		
		
		up.print();
	}//end of method
}//end of class
