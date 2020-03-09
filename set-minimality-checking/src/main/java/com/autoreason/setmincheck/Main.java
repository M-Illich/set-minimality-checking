package com.autoreason.setmincheck;


/**
 * Main class to execute implementation
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    	
    	
    	int len = 2;
    	long[] bitVector = new long[len];
    	bitVector[0] = 1025;
    	bitVector[1] = 1025;
    	bitVector[1] |= (long) 1 << 9;
    	
		for (int i = 0; i < bitVector.length; i++) {
			System.out.println(Long.toBinaryString(bitVector[i]));
		}
				
    }
}
