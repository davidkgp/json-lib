package com.my.json.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonCompare {
	
	public static List<String> compare(JSONArray superSet,JSONArray subSet){
		
		List<String> results = new ArrayList<String>();
		
		if(superSet.length()<subSet.length()) {
			
			results.add("Expected Array Size "+subSet.length()+" But was"+superSet.length());
			
		}else if((superSet.length()>subSet.length())||(superSet.length()==subSet.length())) {
			
			List<String> resultsInter = null;
			for (int i = 0 ; i < subSet.length() ; i++) {
				Object subSetObj = subSet.get(i);
				int counter = 0;
				for(int j = 0 ; j < superSet.length() ; j++) {
					Object superSetObj = superSet.get(j);
					resultsInter = new ArrayList<String>();
					resultsInter.addAll(compare(superSetObj,subSetObj));
					if(resultsInter.size()==0) {
						break;
					}
					counter++;
				}
				
				if(counter== superSet.length()) {
					results.addAll(resultsInter);
					break;
				}
				
			}
			
		}
		
		return results;
	
	}
	
	public static List<String> compare(JSONObject superSet,JSONObject subSet){
		
		List<String> results = new ArrayList<String>();
		
		if(compare(superSet.keySet(),subSet.keySet())) {
			subSet.keySet().stream().forEach(key->{
				results.addAll(compare(superSet.get(key),subSet.get(key)));
			});
		}
		
		return results;
		
	}
	
	private static List<String> compare(Object superSet, Object subSet) {
		
		List<String> results = new ArrayList<String>();
		
		if(superSet instanceof JSONObject && subSet instanceof JSONObject) {
			results.addAll(compare((JSONObject)superSet, (JSONObject)subSet));
		}else if(superSet instanceof JSONArray && subSet instanceof JSONArray) {
			results.addAll(compare((JSONArray)superSet, (JSONArray)subSet));
		}else {
			if(!superSet.equals(subSet)) {
				results.add("Expected "+subSet.toString()+"But received "+superSet.toString());
			}
		}
		
		return results;
		
	}

	public static boolean compare(Set superSet,Set subSet){
		return superSet!=null?superSet.containsAll(subSet):false;
	}

}
