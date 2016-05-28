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

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class Parser.
 * @author Yasith Lokuge
 */
public class Parser {

	/**
	 * Parses the.
	 *
	 * @param inlist the inlist
	 * @return the array list
	 */
	public static ArrayList<String> parse(ArrayList<String> inlist){
		
		ArrayList<String> outlist = new ArrayList<String>();
		
		outlist.add("<html>");
		outlist.add("<body>");
		
		for (String line : inlist) {

			String header = null;
			
			if(line.startsWith("###")){
				header = "<h3>"+line.substring(3)+"</h3>";
			}else if(line.startsWith("##")){
				header = "<h2>"+line.substring(2)+"</h2>";
			}else if(line.startsWith("#")){
				header = "<h1>"+line.substring(1)+"</h1>";
			}
			
			
			
			Pattern p1 = Pattern.compile("\\*([^\\*]*)\\*");
			Matcher m1 = p1.matcher(line);
			String parsed1 = null;
			
			while (m1.find()) {
				parsed1 = m1.group(1);
			}			
			
			if(parsed1 != null){
				String preHeader = "<em>"+parsed1+"</em>";
				header = line.replace("*"+parsed1+"*", preHeader);
			}
			
			Pattern p2 = Pattern.compile("\\*\\*([^\\*\\*]*)\\*\\*");
			Matcher m2= p2.matcher(line);
			String parsed2 = null;
			
			while (m2.find()) {				
				parsed2 = m2.group(1);
			}
			
			if(parsed2 != null){
				String preHeader = "<strong>"+parsed2+"</strong>";
				header = line.replace("**"+parsed2+"**", preHeader);
			}
			
			if(!line.startsWith("*") && !line.startsWith("**") && !line.startsWith("#")){
				header = "<p>"+line+"</p>";
			}
			
			Pattern p3 = Pattern.compile("\\[(?<text>[^\\]]*)\\]\\((?<link>[^\\)]*)\\)");
			Matcher m3 = p3.matcher(line);
			String desc = null;
			String link = null;
			
			while (m3.find()) {
				desc = m3.group(1);
				link = m3.group(2);
			}
			
			if(desc != null){
				StringBuilder preHeader = new StringBuilder();
				
				preHeader.append("<a href=\""+desc+"\">")
				.append(link)
				.append("</a>");
				header = line.replace("["+desc+"]("+link+")", preHeader);
			}
			
			outlist.add(header);
		}
		
		outlist.add("</body>");
		outlist.add("</html>");
		
		return outlist;
	}
}
