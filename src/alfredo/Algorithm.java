package alfredo;

public class Algorithm {
	public static void main(String[] args)
	{
		int[] data = {18897109, 12828837, 9461105, 6371773, 5965343, 
				5946800, 5582170, 5564635, 5268860, 
				4552402, 4335391, 4296250, 4224851, 
				4192887, 3439809, 
				3279833, 3095313, 2812896, 2783243, 2710489, 2543482, 
				2356285, 2226009, 2149127, 2142508, 2134411};
		
		int angka = (int)Math.pow(2, data.length);
		
		String answer="";
		
		for( int i=0; i<angka;i++)
		{
			
			
			String binary = Integer.toBinaryString(i);
			
			//append dulu..
			int append=data.length - binary.length();
			
			for(int j=0; j< append;j++)
				binary = '0'+binary;
			
			int jumlah=0;
			
			for(int k=0;k<binary.length();k++)
			{
				char ch = binary.charAt(k);
				
				if(ch=='1')
					jumlah = jumlah + data[k];
				
			}//end of for
			
			if(jumlah == 100000000)
			{
				answer=binary;
				break;
			}
			
		}//end for
		
		System.out.println("jawaban.."+answer);
		
		int jumlah =0;
		for(int k=0;k<answer.length();k++)
		{
			char ch = answer.charAt(k);
			
			if(ch=='1'){
				System.out.println(data[k]);
				jumlah = jumlah + data[k];
			}
		}//end of for
		
		System.out.println("jumlah.."+ jumlah);
		
	}//end of main
}//end of method
