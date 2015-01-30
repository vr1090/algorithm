package week1.excercise;

import week1.*;

import java.sql.Struct;
import java.util.*;

public class PercolationStats {
	private int dimension = 0;
	private int count_montecarlo =0;
	private List<Double> result;
	private double mean;
	private double stdev;
	
	
	public PercolationStats(int N, int T) // perform T independent experiments
											// on an N-by-N grid
	{
		dimension = N;
		count_montecarlo = T;
		result = new ArrayList<Double>();
		
		for( int i=0; i < count_montecarlo; i++)
		{
			
			QuickUnion quick = new QuickUnion(N*N+2);
			PercolationStructure struct = new PercolationStructure(N, quick);
			int attempt =0;
			
			
			
			
			
			while( !struct.isDone() )
			{
				int x = (int)((Math.random() * dimension) +1);
				int y = (int)((Math.random() * dimension) +1);
				//System.out.println( "open ("+x + ","+y+")");
				if( struct.open(x,y) )
					attempt++;
			}//end of while
			
			result.add( (double)attempt/(dimension*dimension) );
			
		}//end for
		
		
	}// end of method

	public double mean() // sample mean of percolation threshold
	{
		double tempValue =0;
		
		for(Double d: result)
		{
			tempValue = tempValue +d;
		}//end for
		mean = tempValue/count_montecarlo;
		return mean;
	}// end of method

	public double stddev() // sample standard deviation of percolation threshold
	{
		double tempValue =0;
		
		for(Double d: result)
		{
			tempValue = tempValue + Math.pow( (d-mean),2);
		}//end for
		
		stdev = tempValue / (count_montecarlo-1);
		
		return stdev;
	}// end of method

	public double confidenceLo() // low endpoint of 95% confidence interval
	{
		return mean -   1.96 * Math.sqrt(stdev) / Math.sqrt(count_montecarlo);
	}// end of method

	public double confidenceHi() // high endpoint of 95% confidence interval
	{
		return mean +   1.96 * Math.sqrt(stdev) / Math.sqrt(count_montecarlo);
	}// end of method
	
	
	private class PercolationStructure
	{
		private Block[][] blocks= null;
		private int minX =1;
		private int minY=1;
		private int maxX=-1;
		private int maxY=-1;
		
		private int rootAtas =0;
		private int rootBawah=-1;
		
		private QuickUnion guardian= null;
		
		public PercolationStructure(int n, QuickUnion nf)
		{
			
			blocks = new Block[n+1][n+1];
			int pos =1;
			//initialize dulu kali yah..
			for( int row=1;row <=n; row++)
			{
				
				for( int col =1; col <= n; col++)
				{
					Block newBlock = new Block();
					newBlock.isOpen =false;
					newBlock.x=row;
					newBlock.y= col;
					newBlock.arrayPos = pos;
					
					blocks[row][col] = newBlock;
					pos++;
				}//end for
			}//end for
			
			//set the first row
			for( int i=1; i<=n;i++)
			{
				nf.union(0, i);
			}//end of for
			
			int last = (n*n)+1;
			
			for(int i=last-n; i<last;i++)
				nf.union(last, i);
			
			//sing yang dibutuhken ...
			maxX =n;
			maxY=n;
			rootBawah = last;
			guardian = nf;
			
		}//end of constructor
		
		public boolean open(int x, int y)
		{
			Block b = blocks[x][y];
			
			if( b.isOpen)
				return false;
			
			b.isOpen = true;
			
			//kanan kiri atas bawah om
			int xKiri = x-1;
			int xKanan = x+1;
			int yAtas = y-1;
			int yBawah = y+1;
			
			
			//cek kiri
			if( xKiri >= minX)
			{
				Block bxkiri = blocks[xKiri][y];
				if(bxkiri.isOpen )
				{
					guardian.union(b.arrayPos, bxkiri.arrayPos);
				}//end of if
			}//end if
			
			
			//cek kanan
			if( xKanan <= maxX)
			{
				Block bTemp = blocks[xKanan][y];
				if(bTemp.isOpen )
				{
					guardian.union(b.arrayPos, bTemp.arrayPos);
				}//end of if
			}//end if
			
			//cek atas
			if( yAtas >= minY)
			{
				Block bTemp = blocks[x][yAtas];
				if(bTemp.isOpen )
				{
					guardian.union(b.arrayPos, bTemp.arrayPos);
				}//end of if
			}//end if
			
			//cek bawah
			if( yBawah <= maxY)
			{
				Block bTemp = blocks[x][yBawah];
				if(bTemp.isOpen )
				{
					guardian.union(b.arrayPos, bTemp.arrayPos);
				}//end of if
			}//end if
			
			return true;
			
		}//end of method
		
		public boolean isDone()
		{
			return guardian.connected(rootAtas, rootBawah);
		}//end of method
		
		
		private class Block{
			int x=-1;
			int y=-1;
			boolean isOpen =false;
			int arrayPos;
		}//end of private class
	}//end of class

	public static void main(String[] args) // test client (described below)
	{
		//bikin UI dulu ah kaka
		PercolationStats stats = new PercolationStats(200, 100);
		System.out.println( String.format("%-24s = %f", "mean", stats.mean() ));
		System.out.println( String.format("%-24s = %f", "stddev", stats.stddev() ));
		System.out.println( String.format("%-24s = %f, %f", "95% confidence interval ",stats.confidenceLo(), stats.confidenceHi() ));
	}// end of method
}// end of class
