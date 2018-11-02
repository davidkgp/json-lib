package com.my.json.parse;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.JsonContext;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Optional;

public class JsonParseTest {
	
	@Test
	public void testJsonParse() throws MalformedURLException, IOException, URISyntaxException {

		JsonContext expected = (JsonContext) JsonPath.parse(new File("src//test//resources//sample.json"));

		Assert.assertEquals(expected.jsonString(),new JsonParse().getJsonObject(JsonHelper.getFileContent(new File("src//test//resources//sample.json"))).get().jsonString());

	}

	@Test(expected = JsonParseException.class)
	public void testJsonParse1() throws MalformedURLException, IOException, URISyntaxException {

		JsonContext expected = (JsonContext) JsonPath.parse(new File("src//test//resources//sample.json"));

		Assert.assertNotEquals(expected.jsonString(),new JsonParse().getJsonObject(null).get().jsonString());

	}

}
