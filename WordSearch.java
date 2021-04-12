/*
 * WordSeach.java
 * 
 * Version: 3.1.1
 * 
 * Revisions: 1.1
 * 
 */

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * This program creates word search application that searches the word
 * horizontally,vertically and diagonally in forward and backward directions. 
 * 
 * @author ANKITA KHADSARE
 * @author SALONI PODDAR
 *
 */

public class WordSearch {
	static File file;
	static Scanner sc,scNew;
	static int countLines=0,startIndex=0,endIndex=0,posX=0,posY=0;
	static String arr[][];
	static boolean flagFinal=false;
	
	/**
	 * Method to check if the word is present or not. 
	 * It uses Pattern and Matcher Class to verify the word.
	 *  
	 * @param s String in which the word is searched in forward direction.
	 * @param srev String in which the word is searched in backward direction.
	 * @param word Word that needs to be searched.
	 * @return Returns true if word is found else false.
	 */
	
	static boolean isPresent(String s,String srev,String word) {
		Pattern pattern=Pattern.compile(word,Pattern.CASE_INSENSITIVE);
		Matcher matcher1=pattern.matcher(s);
		Matcher matcher2=pattern.matcher(srev);
		if(matcher1.find() ) {
			startIndex=matcher1.start();
			endIndex=matcher1.end();
			return true;
		}
		else if(matcher2.find()) {
			startIndex=countLines-matcher2.start()-1;
			endIndex=countLines-matcher2.end()+1;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Method to search the word in horizontal manner 
	 * in both the diretions that is forward and backward.
	 * 
	 * @param arr 2D array that stores every element of puzzle as String.
	 * @param word Word that needs to be searched.
	 * @return Returns flag value that can be true if word is found else false.
	 */
	static boolean horizontalSearch(String arr[][],String word) {
		String s="",srev="";
		boolean flag=false;
		int i=0,j=0;
		for(i=0;i<countLines;i++) {
			for(j=0;j<countLines;j++) {
				s+=arr[i][j];
				srev=arr[i][j]+srev;
			}
			flag=isPresent(s, srev,word);
			s="";srev="";
			if(flag==true) {
				posX=startIndex;
				posY=endIndex-1;
				System.out.println("Found Word at StartIndex ("+i+","+posX+
						")\nEndIndex ("+i+","+posY+")");
				break;
			}
		}
		return flag;
	}
	
	/**
	 * Method to search the word in vertical manner 
	 * in both the directions that is forward and backward.
	 * 
	 * @param arr 2D array that stores every element of puzzle as String.
	 * @param word Word that needs to be searched.
	 * @return Returns flag value that can be true if word is found else false.
	 */
	
	static boolean verticalSearch(String arr[][],String word) {
		String s="",srev="";
		boolean flag=false;
		int i=0,j=0;
		while(j<countLines) {
			for(i=0;i<countLines;i++) {
				s+=arr[i][j];
				srev=arr[i][j]+srev;
			}
			flag=isPresent(s, srev,word);
			s="";srev="";
			if(flag==true) {
				posX=startIndex;
				posY=endIndex-1;
				System.out.println("Found Word at StartIndex ("+posX+","+j+
						")\n EndIndex ("+posY+","+j+")");
				break;
			}
			j++;
		}
		return flag;
	}
	
	/**
	 *  Method to search the word in diagonal(from topRight to bottomLeft)
	 *  manner in both the directions that is forward and backward.
	 * 
	 * @param arr 2D array that stores every element of puzzle as String.
	 * @param word word Word that needs to be searched.
	 * @return Returns flag value that can be true if word is found else false.
	 */
	static boolean diagonalSearchRL(String arr[][],String word) {
		String s="",srev="";
		boolean flag=false;
		//top right to bottom left
		for(int i=0;i<countLines*2-1;++i) {
			int k=i<countLines?0:i-countLines+1;
			for(int j=k;j<=i-k;++j) {
				int m=j;
				int n=(countLines-1)-(i-j);
				s+=arr[m][n];
				srev=arr[m][n]+srev;
			}
			flag=isPresent(s, srev,word);
			s="";srev="";
			if(flag==true) {
				System.out.println("Found Word at "+i+"th diagonal iteration,"
						+ "\nfrom top right to bottom left search.");
				break;
			}
			
		}
		return flag;
	}
	
	/**
	 *  Method to search the word in diagonal(from topLeft to bottomRight)
	 *  manner in both the directions that is forward and backward.
	 * 
	 * @param arr 2D array that stores every element of puzzle as String.
	 * @param word word Word that needs to be searched.
	 * @return Returns flag value that can be true if word is found else false.
	 */
	static boolean diagonalSearchLR(String arr[][],String word) {
		String s="",srev="";
		boolean flag=false;
		//top left to bottom right
		for(int i=0;i<countLines*2-1;++i) {
			int k=i<countLines?0:i-countLines+1;
			for(int j=k;j<=i-k;++j) {
				int m=j;
				int n=i-j;
				s+=arr[m][n];
				srev=arr[m][n]+srev;
				
			}
			flag=isPresent(s, srev,word);
			s="";srev="";
			if(flag==true) {
				System.out.println("Found Word at "+i+"th diagonal iteration,"
						+ "\nfrom top left to bottom right search.");
				break;
			}
		}
		return flag;
	}
	
	/**
	 * Method to search word in the puzzle horizontally,vertically 
	 * and diagonally by calling different functions.
	 * 
	 *@param arr 2D array that stores every element of puzzle as String.
	 *@param word word Word that needs to be searched.
	 */
	static void wordSearch(String arr[][],String word) {
		flagFinal=horizontalSearch(arr, word);
		if(flagFinal!=true) {
			flagFinal=verticalSearch(arr, word);
			if(flagFinal!=true) {
				flagFinal=diagonalSearchRL(arr, word);
				if(flagFinal!=true) {
					flagFinal=diagonalSearchLR(arr, word);
				}
			}
		}
		if(flagFinal==false && !word.equalsIgnoreCase("exit")) {
			System.out.println("Unable to find word : "+word);
		}
	}
	
	/**
	 * Method to read and display file and store them in 2D array.
	 * 
	 * @param file Puzzle File that is read.
	 * @param word Word that needs to be searched.
	 */
	static void wordsToArray(File file,String word) {
		String line="";
		String w[]=new String[word.length()];
		int k=0;
		try{
			sc=new Scanner(file);
			while(sc.hasNextLine()) {
				countLines++;
				sc.nextLine();
			}
			arr=new String[countLines][countLines];
			scNew=new Scanner(file);
			while(scNew.hasNextLine()) {
				line=scNew.nextLine();
				for(int i=0;i<line.length();i++) {
					arr[k][i]=line.substring(i,i+1);
				}
				k++;
			}
			for(int i=0;i<word.length();i++) {
				w[i]=word.substring(i, i+1);
			}
			//Displaying the matrix
			System.out.println("Displaying the matrix of Word Puzzle: \n");
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr.length;j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * The main function.
	 * @param args CommandLine Arguments
	 */
	public static void main(String args[]) {
		Scanner scObj=new Scanner(System.in);
		System.out.println("Enter puzzle file name :");
		String fileName=scObj.nextLine();
		System.out.println("Displaying file contents :\n");
		try {
			file = new File(fileName);
			sc=new Scanner(file);
			
			while(sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			System.out.println();
		}
		catch(Exception e) {
			System.out.println("Can't find that File ! Try Again..\n"
					+ "Enter puzzle file name :");
			fileName=scObj.nextLine();
			try {
				file = new File(fileName);
				sc=new Scanner(file);
				while(sc.hasNextLine()) {
					System.out.println(sc.nextLine());
				}
			}
			catch(Exception e1){
				if(fileName.equalsIgnoreCase("exit")) {
					System.exit(1);
				}
				else {
					System.out.println("File Not Found.");
					System.exit(0);
				}
			}
		}
		String word="";
		if(word.equalsIgnoreCase("exit")) {
			System.exit(2);
		}
		wordsToArray(file,word);
		while(!word.equalsIgnoreCase("exit")) {
			System.out.println("Which word would you like to find ?");
			word=scObj.nextLine();
			wordSearch(arr,word);
		}
		
	}

}
