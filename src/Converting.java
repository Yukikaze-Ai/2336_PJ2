import java.util.ArrayList;

public class Converting {
	//--------DEC-and-BIN-----------
	 //this method converts decimal to binary
	public static char[] decToBit(Long dec,int SIZE)
	{
		boolean negative =false;
		if(dec <0)
		{
			dec *=(-1);
			negative=true;
		}
		ArrayList <Long> tempList = new ArrayList<Long> (); 
		while(dec!=0)
		{
			tempList.add(dec%2);
			dec = dec /2;
		}
		
		char [] Cbit= new char[SIZE];
		for (int i = 0; i< Cbit.length;i++)
			Cbit[i]='0';
		int j=SIZE-tempList.size();
		for (int i=(tempList.size()-1); i >=0; i--)
		{
			Cbit[j] += tempList.get(i);
			j++;
		}
		if(negative ==false)
			return Cbit;
		else
			return negate(Cbit);

	}
	//this method converts binary to decimal 
	public static Long bitToDec(char [] bit,int SIZE)
	{
		int j=bit.length-1;
		int [] bitList = new int [bit.length];
		Long sum=0L;
		if (bit[0]=='1')
		{
			negate(bit);
			for (int i = 0; i< bit.length; i++)
			{
					bitList[i]= bit[j]-48;
					j--;
			}
			for (int i=0; i<bit.length;i++)
			{
				sum = (long)(sum + bitList[i]*Math.pow(2, i)) ;
			}
			sum = sum*(-1);
		}else
		{
			for (int i = 0; i< bit.length; i++)
			{
					bitList[i]= bit[j]-48;
					j--;
			}
			
			for (int i=0; i<bit.length;i++)
			{
				sum = (long)(sum + bitList[i]*Math.pow(2, i)) ;
			}
		}
		return sum;
	}
	//--------OCT-and-BIN-----------
	 //this method converts binary to Oct
	public static char[] bitToOct(char [] bit, int SIZE)
	{
		char [] Oct=new char [(SIZE/3)+1];
		for (int i=(bit.length-1), j=(SIZE/3); j>=0;)
		{
			if(j==0)
			{
				int temp=0;
				for(int k = 0;i>=0;i--)
				{
					double fst=(bit[i]-48)*Math.pow(2, k);
					k++;
					temp += (int) fst;
				}
				Oct[j--]=(char) (temp+48);
			}else
			{
				double fst=(bit[i--]-48)*Math.pow(2, 0);
				double sec=(bit[i--]-48)*Math.pow(2, 1);
				double trd=(bit[i--]-48)*Math.pow(2, 2);
				int temp =(int) (fst+sec+trd);
				Oct[j--]=(char) (temp+48);
			}
		}
		return Oct; 
	}
	//this method converts Oct to binary
	public static char[] octToBit(char [] oct ,int SIZE)
	{
		char [] bitList = new char [SIZE];
		for(int i =0; i<bitList.length;i++)
			bitList[i]='0';
		int j=(SIZE-1);
		for (int i = (oct.length-1) ;i>=0;)
		{
			
			int temp=0;
			temp=oct[i--]-48;
			bitList[j--]=(char) (temp%2+48);
			temp= temp/2;
			bitList[j--]=(char) (temp%2+48);
			temp= temp/2;
			bitList[j--]=(char) (temp+48);
		}

		return bitList;
	}
	//--------HEX-and-BIN-----------
	//this method converts binary to hex
	public static char[] bitToHex(char [] bit ,int SIZE)
	{
		char [] Hex=new char [(SIZE/4)];
		for (int i=(bit.length-1), j=((SIZE/4)-1); j>=0;)
		{
			Long temp=(long)((bit[i--]-48)*Math.pow(2, 0)+(bit[i--]-48)*Math.pow(2, 1)+(bit[i--]-48)*Math.pow(2, 2)+(bit[i--]-48)*Math.pow(2, 3));
			Long case10 = 10L;
			Long case11 = 11L;
			Long case12 = 12L;
			Long case13 = 13L;
			Long case14 = 14L;
			Long case15 = 15L;
			if (temp.equals(case10))
				Hex[j--] ='A';
			else if(temp.equals(case11))
				Hex[j--]= 'B';
			else if(temp.equals(case12))
				Hex[j--]= 'C';
			else if(temp.equals(case13))
				Hex[j--]= 'D';
			else if(temp.equals(case14))
				Hex[j--]= 'E';
			else if(temp.equals(case15))
				Hex[j--]= 'F';
			else			
				Hex[j--]  = (char)(temp + 48);
		}
		return Hex;
		
	}
	//this method converts hex to binary
	public static char[] hexToBit(char[] hex,int SIZE)
	{
		//hex = new StringBuffer(hex).reverse().toString();  
		char [] bitList = new char [(hex.length)*4];
		for(int i =0; i<bitList.length;i++)
			bitList[i]='0';
		
		for (int i = (hex.length-1) ;i>=0;)
		{
			int j=((i+1)*4-1);
			int temp=0;
			if (hex[i]>=48 && hex[i]<=57)
				temp=hex[i--]-48;
			else 
				temp=hex[i--]-55;
			
			bitList[j--]=(char) (temp%2+48);
			temp= temp/2;
			bitList[j--]=(char) (temp%2+48);
			temp= temp/2;
			bitList[j--]=(char) (temp%2+48);
			temp= temp/2;
			bitList[j--]=(char) (temp+48);
		}

		return bitList;
	}
	//-------------------
	public static char[] decToOct(long dec,int SIZE){return bitToOct(decToBit(dec,SIZE),SIZE);}
	public static Long octToDec(char [] oct,int SIZE){return bitToDec(octToBit(oct,SIZE),SIZE);}
	public static char[] decToHex(long dec,int SIZE){return bitToHex(decToBit(dec,SIZE),SIZE);}
	public static Long hexToDec(char [] hex,int SIZE){return bitToDec(hexToBit(hex,SIZE),SIZE);}
	public static char[] octToHex(char [] oct,int SIZE){return bitToHex(octToBit(oct,SIZE),SIZE);}
	public static char[] hexToOct(char [] hex,int SIZE){return bitToOct(hexToBit(hex,SIZE),SIZE);}
	//-------------------
	//”Not” method for binary
	public static char[] not(char [] bit)
	{
		for (int i=0; i<bit.length;i++)
		{
			if(bit[i]=='1')
				bit[i]='0';
			else if(bit[i]=='0')
				bit[i]='1';
		}
		return bit;
	}
	//get negative values.
	public static char[] negate(char [] bit)
	{
		not(bit);
		bit[bit.length-1]+=1;
		for (int i = bit.length-1; i>=0;i--)
		{
			if(bit[i]=='2')
			{
				bit[i]='0';
				bit[i-1]+=1;
			}
			else
				break;
		}
		return bit;
	}
	public static char[] Convert(long val,int mod,int SIZE)
	{
		char[] specialcase= {'0'};
		switch(mod)
		{
		case 2:
			return decToBit(val,SIZE);
		case 8:
			return decToOct(val,SIZE);
		case 16:
			return decToHex(val,SIZE);
		default:
			return specialcase;
		}
	}

}


