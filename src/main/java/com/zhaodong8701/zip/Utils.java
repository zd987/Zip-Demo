package com.zhaodong8701.zip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tools.ant.DirectoryScanner;

public class Utils {
	public static List<String> parseWildcardFile(String filePath) {
		DirectoryScanner scanner = new DirectoryScanner();
		scanner.setIncludes(new String[]{filePath});		
		scanner.setCaseSensitive(false);
		scanner.scan();
		String[] files = scanner.getIncludedFiles();
		List<String> re = new ArrayList<String>();
		re.addAll(Arrays.asList(files));
		scanner.setBasedir(".");
		scanner.scan();
		files = scanner.getIncludedFiles();
		re.addAll(Arrays.asList(files));
		return re;
	}
	
	public static void main(String args[]) {
		System.out.println(parseWildcardFile("F:/t/*"));
	}
}
