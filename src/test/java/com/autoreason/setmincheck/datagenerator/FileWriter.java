package com.autoreason.setmincheck.datagenerator;

import java.util.Collection;
import java.util.Set;

import com.autoreason.setfileconverter.SetFileConverter;

/**
 * Class to write test data to a file that can be used read during testing
 *
 */
public class FileWriter {
	final static int NUM_SETS = 25; 
	final static int MAX_SIZE = 12;
	final static int RANGE = 50;
	final static long SEED = 1384205;

	public static void main(String[] args) {
		// generate random collection of minimal sets
		Collection<Set<Integer>> col = SetGenerator.randomMinSetCollection(NUM_SETS, MAX_SIZE, RANGE, SEED);
		
		// write sets to file
		for (Set<Integer> set : col) {
			SetFileConverter.writeSetToFile(set, "src\\test\\resources\\minSets.txt");
		}	

	}

}
