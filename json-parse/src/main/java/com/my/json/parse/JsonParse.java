package com.my.json.parse;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.JsonContext;

import java.util.Optional;

public class JsonParse {

	public static  Optional<JsonContext> getJsonObject(Optional<String> jsonString) {

		return JsonParseException.wrap(()->jsonString.map(jsonStringVal->(JsonContext)JsonPath.parse(jsonStringVal)),Constants.UNEXPECTED_EXCEPTION);

	}

}
