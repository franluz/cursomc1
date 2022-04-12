package com.nelioalves.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	public static String decodeParam(String string) throws UnsupportedEncodingException{
//		string.URL.DE
		
		return URLDecoder.decode(string, "UTF-8");
	}

	public static List<Integer> decodeIntList(String string){
		String[] s = string.split(",");
	    return Arrays.asList(s).stream().map(Integer::parseInt).collect(Collectors.toList());
		
	}
}
