package com.my.json.compare;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.my.json.compare.rules.ValueRule;
import com.my.json.compare.rules.interfaces.ComparisionRule;
import com.my.json.parse.JsonHelper;
import com.my.json.parse.JsonParse;

public class JsonCompare {
	
	private static ComparisionRule compRule;
	
	
	public static List<String> compare(File superSet,File subSet,ComparisionRule compRule) throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException{
		JsonCompare.compRule = compRule!=null?compRule:new ValueRule();
		return compare(
				JsonParse.getJsonObject(JsonHelper.getFileContent(superSet)).get(), 
				JsonParse.getJsonObject(JsonHelper.getFileContent(subSet)).get());
		
	}
	
	public static List<String> compare(Optional<String> superSet,Optional<String> subSet,ComparisionRule compRule) throws InstantiationException, IllegalAccessException{
		JsonCompare.compRule = compRule!=null?compRule:new ValueRule();
		return compare(
				JsonParse.getJsonObject(superSet).get(), 
				JsonParse.getJsonObject(subSet).get());
		
	}
	
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
		appendErrors(superSet, subSet, results);
		return results;
	
	}
	
	public static List<String> compare(JSONObject superSet,JSONObject subSet){
		
		List<String> results = new ArrayList<String>();
		
		if(compare(superSet.keySet(),subSet.keySet())) {
			subSet.keySet().stream().forEach(key->{
				List<String> expectedResults = compare(superSet.get(key),subSet.get(key));
				if(!expectedResults.isEmpty()) {
					results.addAll(expectedResults);
				}
				
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
			if(!compRule.compareData(superSet, subSet)) {
				results.add("Expected "+subSet.toString()+" But received "+superSet.toString());
			}
		}
		
		return results;
		
	}

	public static boolean compare(Set superSet,Set subSet){
		return superSet!=null?superSet.containsAll(subSet):false;
	}
	
	private static void appendErrors(Object superSet,Object subSet,List<String> errors){
		if(errors!=null && errors.size()>0) {
			
			if(superSet instanceof JSONObject && subSet instanceof JSONObject) {
				
				errors.add("======================Actual====================");
				errors.add(((JSONObject)subSet).toString(2));
				errors.add("======================Actual====================");
				errors.add("======================Expected==================");
				errors.add(((JSONObject)superSet).toString(2));
				errors.add("======================Expected==================");
			}else if(superSet instanceof JSONArray && subSet instanceof JSONArray) {
				errors.add("======================Actual====================");
				errors.add(((JSONArray)subSet).toString(2));
				errors.add("======================Actual====================");
				errors.add("======================Expected==================");
				errors.add(((JSONArray)superSet).toString(2));
				errors.add("======================Expected==================");
			}/*else {
				errors.add("======================Actual====================");
				errors.add("======================Actual====================");
				errors.add("======================Expected==================");
				errors.add("======================Expected==================");
			}*/
			
		}
	}

}
