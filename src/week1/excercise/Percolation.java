package week1.excercise;

import java.util.Arrays;

public class Percolation {
	private Block[][] blocks= null;
	private int minX =1;
	private int minY=1;
	private int maxX=-1;
	private int maxY=-1;
	
	private int rootAtas =0;
	private int rootBawah=-1;
	
	private UF guardian= null;
	
	 // create N-by-N grid, with all sites blocked
	   public Percolation(int n){
		   //System.out.println("hei");
			blocks = new Block[n+1][n+1];
			guardian = new UF(n*n+2);
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
				guardian.union(0, i);
			}//end of for
			
			int last = (n*n)+1;
			
			for(int i=last-n; i<last;i++)
				guardian.union(last, i);
			
			//sing yang dibutuhken ...
			maxX =n;
			maxY=n;
			rootBawah = last;
			
			//System.out.println("finish");
	   }//end of method
	   
	   public boolean open1(int x, int y)
	   {
		   //System.out.println("open "+ x +" "+y);
		   Block b = blocks[x][y];
			if( b.isOpen)
				return false;
			
			open(x,y);
			return true;
	   }//end of method
	   
	   // open site (row i, column j) if it is not open already
	   public void open(int x, int y)
	   {
			Block b = blocks[x][y];
			
			if( b.isOpen)
				return;
			
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
	   }//end of method
	   
	// is site (row i, column j) open?
	   public boolean isOpen(int i, int j)
	   {
		   return (blocks[i][j]).isOpen;
	   }
	   
	   // is site (row i, column j) full?
	   public boolean isFull(int i, int j)
	   {
		   return guardian.connected(rootAtas, blocks[i][j].arrayPos);
	   }
	   
	// does the system percolate?x
	   public boolean percolates()
	   {
		   
		   return guardian.connected(rootAtas, rootBawah);
	   }//end of method
	   
	  
	   
	   
	   private class Block{
			int x=-1;
			int y=-1;
			boolean isOpen =false;
			int arrayPos;
		}//end of private class
	   
	   private class UF {
			
			private int[] master = null;
			private int[] size = null;
			private int panjang=0;
			
			public UF(int N)
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
					//master[node]= master[master[node]];
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
			
		}//end of class
	   
	   public static void main(String[] args)
	   {
		   Percolation per = new Percolation(4);
		   //per.getUF().print();
		   per.open(1, 1);
		   per.open(2, 1);
		   per.open(3, 1);
		   per.open(4, 1);
		   System.out.println(per.percolates());
		   //per.getUF().print();
		   
	   }//end of method
	   
	   
}//end of class