package com.my.json.compare;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.JsonContext;
import com.my.json.compare.rules.ValueRule;
import com.my.json.compare.rules.interfaces.ComparisionRule;
import com.my.json.parse.JsonHelper;
import com.my.json.parse.JsonParse;
import net.minidev.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.*;



public class JsonCompare {

    private static ComparisionRule compRule;

    private static Map<String,ComparisionRule> compRules;


    public static List<String> compare(File superSet,File subSet,ComparisionRule compRule) throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException{
        JsonCompare.compRule = compRule!=null?compRule:new ValueRule();
        return compare(
                JsonParse.getJsonObject(JsonHelper.getFileContent(superSet)).orElse((JsonContext) JsonPath.parse("{}")),
                JsonParse.getJsonObject(JsonHelper.getFileContent(subSet)).orElse((JsonContext) JsonPath.parse("{}")));

    }

    public static List<String> compare(Optional<String> superSet,Optional<String> subSet,ComparisionRule compRule){
        JsonCompare.compRule = compRule!=null?compRule:new ValueRule();

        return compare(
                JsonParse.getJsonObject(superSet).orElse((JsonContext) JsonPath.parse("{}")),
                JsonParse.getJsonObject(subSet).orElse((JsonContext) JsonPath.parse("{}")));

    }

    public static List<String> comparewithSpecificRule(File superSet,File subSet,Map<String,ComparisionRule> compRules) throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException{
        JsonCompare.compRules = compRules!=null?compRules:null;
        JsonCompare.compRule = new ValueRule();
        return compare(
                JsonParse.getJsonObject(JsonHelper.getFileContent(superSet)).orElse((JsonContext) JsonPath.parse("{}")),
                JsonParse.getJsonObject(JsonHelper.getFileContent(subSet)).orElse((JsonContext) JsonPath.parse("{}")));

    }

    public static List<String> comparewithSpecificRule(Optional<String> superSet,Optional<String> subSet,Map<String,ComparisionRule> compRules){
        JsonCompare.compRules = compRules!=null?compRules:null;
        JsonCompare.compRule = new ValueRule();
        return compare(
                JsonParse.getJsonObject(superSet).orElse((JsonContext) JsonPath.parse("{}")),
                JsonParse.getJsonObject(subSet).orElse((JsonContext) JsonPath.parse("{}")));

    }

    public static List<String> compare(JSONArray superSet,JSONArray subSet){

        List<String> results = new ArrayList<String>();

        if(subSet.size()>superSet.size()){
            results.add(JsonCompareHelper.logSizeArray(superSet.size(),subSet.size()));
            results.add(JsonCompareHelper.log(superSet,subSet));
        }else{

            for (Object subObj:subSet) {
                int counter = 0;
                for (Object superObj:superSet) {
                    List resultsInter = compare(superObj,subObj);
                    if(resultsInter.size()==0){
                        break;
                    }
                    counter++;
                }
                if(counter== superSet.size()) {
                    results.add(JsonCompareHelper.log(superSet,subSet));
                    break;
                }
            }

        }
        return results;

    }

    public static List<String> compare(JsonContext superSetObj, JsonContext subSetObj){

        List<String> results = new ArrayList<String>();
        Map superSet = (Map) superSetObj.json();
        Map subSet = (Map) subSetObj.json();
        results.addAll(filterMatchingRules(superSetObj,subSetObj));
        results.addAll(compare(superSet,subSet));
        return results;

    }

    public static List<String> compare(Map superSet,Map subSet){

        List<String> results = new ArrayList<String>();

        if(compare(superSet.keySet(),subSet.keySet())) {
            subSet.keySet().stream().forEach(key->{
                List<String> expectedResults = compare(superSet.get(key),subSet.get(key));
                if(!expectedResults.isEmpty()) {
                    results.addAll(expectedResults);
                }

            });
        }else {
            results.add(Constants.MESSAGE_PAYLOAD_NULL);
        }

        return results;

    }

    private static List<String> filterMatchingRules(JsonContext superSetObj, JsonContext subSetObj) {

        List<String> results = new ArrayList<String>();

        if(compRules!=null && !compRules.isEmpty()){

            compRules.entrySet().forEach(entry->{
                String path =entry.getKey();
                ComparisionRule compRuleValue = entry.getValue();

                if(!compRuleValue.compareData(superSetObj.read(path),subSetObj.read(path))){
                    results.add(JsonCompareHelper.log(superSetObj.read(path),subSetObj.read(path)));
                }
                superSetObj.delete(path);
                subSetObj.delete(path);
            });

        }

        return results;

    }

    private static List<String> compare(Object superSet, Object subSet) {

        List<String> results = new ArrayList<String>();

        if(superSet instanceof JSONArray && subSet instanceof JSONArray) {
            results.addAll(compare((JSONArray)superSet, (JSONArray)subSet));
        }else if(superSet instanceof Map && subSet instanceof Map){

            results.addAll(compare((Map)superSet, (Map)subSet));
        }else {
            if(!compRule.compareData(superSet, subSet)) {
                results.add(JsonCompareHelper.log(superSet,subSet));
            }
        }

        return results;

    }

    public static boolean compare(Set superSet,Set subSet){
        return superSet!=null && superSet.size()>0 && subSet!=null && subSet.size()>0?superSet.containsAll(subSet):false;
    }

}
