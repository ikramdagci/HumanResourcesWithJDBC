package com.ikramdg.main;

import java.util.Arrays;


public class Parser {

	public static void main(String[] args) {
		String str = "2343434	2017-01-06\n" + "6343544	2017-01-12\n" + "355445	2017-01-16\n"
				+ "2322324	2017-01-25\n" + "122323	2017-02-05\n" + "4543f43	2017-02-07\n"
				+ "2342342	2017-02-21\n" + "1322323	2017-03-05\n" + "5341124	2017-03-07\n"
				+ "13663442	2017-03-14\n" + "26323242	2017-03-16\n" + "12263545	2017-03-25\n"
				+ "23423423	2017-03-25\n" + "34452352	2017-03-25\n" + "235534534	2017-03-25\n"
				+ "22224233	2017-03-26\n" + "3353235	2017-04-05\n" + "56243434	2017-04-14\n"
				+ "33463266	2017-04-21\n" + "6634653	2017-05-07\n" + "2342324	2017-05-14\n"
				+ "2366436	2017-05-16\n" + "1123124	2017-05-25\n" + "3534534	2017-05-25\n"
				+ "46763543	2017-05-25\n" + "33463255	2017-05-25\n" + "";
		str = str.replace("\n", "");
		char[] charArray = str.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (char c : charArray) {
			if(c > 47 && c < 58 || c == 45) builder.append(c);
		}
		
		System.out.println(builder.toString());
		String string = builder.toString();
		String[] split = string.split("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		System.out.println(Arrays.toString(split));
		String[] split2 = string.split("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		System.out.println(Arrays.toString(split2));
	}

}
