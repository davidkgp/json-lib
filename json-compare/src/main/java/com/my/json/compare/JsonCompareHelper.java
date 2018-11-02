package com.my.json.compare;

import com.jayway.jsonpath.internal.JsonFormatter;
import net.minidev.json.JSONArray;

import java.text.MessageFormat;

public class JsonCompareHelper {

    public static String log(Object superSet,Object subSet){
        return MessageFormat.format(Constants.MESSAGE_MISMATCH,preetyPrint(subSet),preetyPrint(superSet));
    }

    public static String logSizeArray(int superSet,int subSet){
        return MessageFormat.format(Constants.MESSAGE_MISMATCH_ARRAY,subSet,superSet);
    }

    public static String preetyPrint(Object json){

        String preetyPrint = "null or empty";

        if(json!=null){
            if(json instanceof JSONArray){
                preetyPrint = JsonFormatter.prettyPrint(json.toString());
            }else{
                preetyPrint= json.toString();
            }
        }


        return preetyPrint;
    }
}
