package com.kreditech.markdown;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class UnitTest {

	@Test
	public void test() {
				
		ArrayList<String> list = new ArrayList<String>();
				
		list.add("# Lorem ipsum!");
		list.add("Dolor sit amet!");
		list.add("*lorem*");
		list.add("**lorem ipsum**");
		list.add("## DoubleHash");
		list.add("### TripleHash");
		list.add("[example link](http://example.com/)");
		
		ArrayList<String> results = Parser.parse(list);
		
		assertTrue("html opening tag not found",results.get(0).equals("<html>"));
		assertTrue("body opening tag not found",results.get(1).equals("<body>"));
		
		assertTrue("body closing tag not found",results.get(results.size()-2).equals("</body>"));
		assertTrue("html closing tag not found",results.get(results.size()-1).equals("</html>"));
		
		assertTrue("<h1> tag not found",	results.get(2).equals("<h1> Lorem ipsum!</h1>"));
		assertTrue("<p>  tag not found",	results.get(3).equals("<p>Dolor sit amet!</p>"));
		assertTrue("<em> tag not found",	results.get(4).equals("<em>lorem</em>"));
		assertTrue("<strong> tag not found",results.get(5).equals("<strong>lorem ipsum</strong>"));
		assertTrue("<h2> tag not found",	results.get(6).equals("<h2> DoubleHash</h2>"));
		assertTrue("<h3> tag not found",	results.get(7).equals("<h3> TripleHash</h3>"));
		assertTrue("<href> tag not found",	results.get(8).equals("<a href=\"example link\">http://example.com/</a>"));
		
		
	}

}
