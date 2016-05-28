/*******************************************************************************
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.kreditech.markdown;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * The Class Main.
 * @author Yasith Lokuge
 */
public class Main {

	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(Main.class);
	
	/** The print to console. */
	static boolean printToConsole = false;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		
		BasicConfigurator.configure();
		
		String inputFilename = null;
		String outputFilename = null;
		
		try{
			inputFilename = args[0];
			logger.debug("Input File Name : "+inputFilename);
		}catch(Exception e){
			logger.error("Input file name should not be empty \n");
			throw e;
		}
		
		try{
			outputFilename = args[1];
			logger.debug("Output File Name : "+outputFilename);
		}catch(Exception e){
			printToConsole=true;
			logger.warn("Output will be printed in the console");
		}
		
		ArrayList<String> inputlist = null;
		
		try {
			inputlist = ListingsReader.getListings(inputFilename); 
		} catch (FileNotFoundException e) {			
			logger.error("Input File not found");
			throw e;
		} catch (IOException e) {			
			logger.error("Input File cannot read");	
			throw e;
		}
		
		ArrayList<String> outputlist = Parser.parse(inputlist);
		
		
		if(!printToConsole){
			try {
				OutputWriter.writeToFile(outputlist, outputFilename);
			} catch (IOException e) {				
				logger.error("Cannot write to the Output File");
				throw e;
			}
		}else{
			for (String line : outputlist) {
				System.out.println(line);
			}
		}
		
	}
}
