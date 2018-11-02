package com.my.json.parse;

import java.util.concurrent.Callable;

public class JsonParseException extends RuntimeException{

    public JsonParseException(String s) {
        super(s);
    }

    public static <T> T wrap(Callable<T> callable,String message){

        try{
           return callable.call();

        }catch(Exception excep){
            excep.printStackTrace();
            throw new JsonParseException(message);

        }

    }
}
