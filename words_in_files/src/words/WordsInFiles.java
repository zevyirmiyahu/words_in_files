package words;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

/*
 * This program is from a Duke Coursera Assignment. It determines which words 
 * occur in the greatest number of files, and for each word, which files they occur in. 
 *  *IMPORTANT* Must change numberOfFiles variable below to desired amount of files that
 *  will be selected when the program is run.
 * 
 * @author Zev Yirmiyahu
 */

public class WordsInFiles {
	
	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	
	
	public WordsInFiles() {
		tester();
	}
	
	
	//Calls related methods to run and test program
	public void tester() {
		
		int numberOfFiles = 3; //Number of files common words appear in 
		
		buildWordFileMap(); //create map	 
		System.out.println("The greatest number of files a word appears in is " + maxNumber() 
							+", and there are: " + wordsInNumberFiles(maxNumber()).size() + " words" 
							+ " these words are: " + wordsInNumberFiles(maxNumber()));
		
		System.out.println("There are " + wordsInNumberFiles(numberOfFiles).size() 
							+ " words that appear in " + numberOfFiles + " files.");
		printFilesIn("tree");
	}
	
	
	//adds word from f in map and fileNames for new words
	private void addWordsFromFiles(File f) {
		
		FileResource fr = new FileResource(f);
		
		for(String word : fr.words()) {
			
			if(!map.containsKey(word)) {
				ArrayList<String> fileNameList = new ArrayList<String>();
				fileNameList.add(f.getName());
				map.put(word, fileNameList);
			}
			else if(!map.get(word).contains(f.getName())){
				ArrayList<String> fileNameList = map.get(word); //word exist, get value
				fileNameList.add(f.getName()); //add file name to ArrayList
				map.put(word, fileNameList); //put ArrayList in map for that key, word
			}
		}
	}
	
	
	//clears the map and select group of files
	public void buildWordFileMap() {
		map.clear();
		DirectoryResource dr = new DirectoryResource();
		
		for(File file : dr.selectedFiles()) {
			addWordsFromFiles(file);
		}
	}
	
	
	//returns max of files an word appears in
	public int maxNumber() {
		
		int biggest = 0;
		
		for(String word : map.keySet()) {
			
			ArrayList<String> list = map.get(word);
			int size = list.size();
			
			if(size > biggest) {
				biggest = size;
			}
		}
		return biggest;
	}
	
	
	//returns the number of files a word appears in
	public ArrayList<String> wordsInNumberFiles(int number) {
		
		ArrayList<String> words = new ArrayList<String>();

		for(String word : map.keySet()) {
			
			if(map.get(word).size() == number) {
				words.add(word);
			}	
		}
		return words;
	}
	
	
	//Prints all file names for a give word
	public void printFilesIn(String word) {
			
		ArrayList<String> fileNameList = map.get(word);
		for(int i = 0; i < fileNameList.size(); i++) {
			System.out.println(fileNameList.get(i));
		}		
	}
	

	public static void main(String args[]) {
		new WordsInFiles();
	}
}
