package week1.excercise;

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
			
			Percolation quick = new Percolation(N);
			int attempt =0;
			
			while( !quick.percolates() )
			{
				int x = (int)((Math.random() * dimension) +1);
				int y = (int)((Math.random() * dimension) +1);
				//System.out.println( "open ("+x + ","+y+")");
				//quick.open(x,y);
				//System.out.println(quick.percolates() );
				if( quick.isOpen(x, y))
					continue;
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
	
	
	
	public static void main(String[] args) // test client (described below)
	{
		//bikin UI dulu ah kaka
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(n,t);
		System.out.println( String.format("%-24s = %f", "mean", stats.mean() ));
		System.out.println( String.format("%-24s = %f", "stddev", stats.stddev() ));
		System.out.println( String.format("%-24s = %f, %f", "95% confidence interval ",stats.confidenceLo(), stats.confidenceHi() ));
	}// end of method
}// end of class
