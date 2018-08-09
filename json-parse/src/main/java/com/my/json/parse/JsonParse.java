package com.my.json.parse;

import java.util.Optional;

import org.json.JSONObject;

public class JsonParse {

	public static Optional<JSONObject> getJsonObject(Optional<String> jsonString) {

		try {
			return jsonString.map(jsonStringValue -> new JSONObject(jsonString.get()));

		} catch (Exception excep) {
			excep.printStackTrace();
		}
		return Optional.empty();

	}

}
