import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;

import com.my.json.compare.JsonCompare;
import com.my.json.parse.JsonParse;

import junit.framework.Assert;

public class JsonCompareTest {
	
	@Test
	public void testJsonCompare() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		Assert.assertTrue(JsonCompare.compare(
				new File("src\\test\\resources\\superSet2.json"), 
				new File("src\\test\\resources\\subSet2.json"),
				null).size()>0);
	}
	
	@Test
	public void testJsonCompareValue() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		Assert.assertTrue(JsonCompare.compare(
				new File("src\\test\\resources\\superSet3.json"), 
				new File("src\\test\\resources\\subSet3.json"),
				"value").size()==0);
	}
	
	@Test
	public void testJsonCompareValueType() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		Assert.assertTrue(JsonCompare.compare(
				new File("src\\test\\resources\\superSetType.json"), 
				new File("src\\test\\resources\\subSetType.json"),
				"type").size()==0);
	}
	
}
