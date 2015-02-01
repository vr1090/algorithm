
import java.util.*;

public class PercolationStats {
	private int dimension = 0;
	private int count_montecarlo = 0;
	private List<Double> result;
	private List<Percolation> pers;
	private double mean;
	private double stdev;
	private double[] resultArray;
	
	public PercolationStats(int N, int T) // perform T independent experiments
											// on an N-by-N grid
	{
		if( N < 1 || T < 1)
			throw new IllegalArgumentException();
		
		dimension = N;
		count_montecarlo = T;
		result = new ArrayList<Double>();
		pers = new ArrayList<Percolation>();
		
		for (int i = 0; i < count_montecarlo; i++) {

			Percolation quick = new Percolation(N);
			pers.add(quick);
		} // end for

	} // end of method
	
	private void performCalculation()
	{
		if(result.size() != 0 )
			return;
		
		for( Percolation quick : pers)
		{
			int attempt = 0;

			while (!quick.percolates()) {
				int x = StdRandom.uniform(dimension) +1;
				int y = StdRandom.uniform(dimension) + 1;
				// System.out.println( "open ("+x + ","+y+")");
				// quick.open(x,y);
				// System.out.println(quick.percolates() );
				if (quick.isOpen(x, y))
					continue;
				quick.open(x,y);
				attempt++;
			} // end of while

			result.add((double) attempt / (dimension * dimension));
		} //end for
		
		resultArray = new double[result.size()];
		
		for(int i=0; i< result.size(); i++)
		{
			resultArray[i] = result.get(i);
		} //end for
		
		
	} //end of method
	

	public double mean() // sample mean of percolation threshold
	{
		performCalculation();
		mean = StdStats.mean(resultArray);
		return mean;
	} // end of method

	public double stddev() // sample standard deviation of percolation threshold
	{
		performCalculation();
		stdev = StdStats.stddev(resultArray);
		return stdev;
	} // end of method

	public double confidenceLo() // low endpoint of 95% confidence interval
	{
		return mean - (1.96 * stdev ) /( Math.sqrt(count_montecarlo));
	} // end of method

	public double confidenceHi() // high endpoint of 95% confidence interval
	{
		return mean + (1.96 * stdev) / (Math.sqrt(count_montecarlo));
	} // end of method

	public static void main(String[] args) // test client (described below)
	{
		// bikin UI dulu ah kaka
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(n, t);
		System.out.println(String.format("%-24s = %f", "mean", stats.mean()));
		System.out
				.println(String.format("%-24s = %f", "stddev", stats.stddev()));
		System.out.println(String.format("%-24s = %f, %f",
				"95% confidence interval ", stats.confidenceLo(),
				stats.confidenceHi()));
	} // end of method
} // end of class
