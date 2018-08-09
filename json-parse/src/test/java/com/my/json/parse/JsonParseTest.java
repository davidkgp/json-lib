package com.my.json.parse;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

public class JsonParseTest {
	
	@Test
	public void testJsonParse() throws MalformedURLException, IOException, URISyntaxException {
		Assert.assertNotNull(new JsonParse().getJsonObject(JsonHelper.getFileContent(new File("src\\test\\resources\\sample.json"))));
	}
	
	

}
