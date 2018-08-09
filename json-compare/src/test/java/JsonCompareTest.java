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

public class JsonCompareTest {
	
	@Test
	public void testJsonCompare() throws MalformedURLException, IOException, URISyntaxException {
		JsonCompare.compare(
				new File("src\\test\\resources\\superSet2.json"), 
				new File("src\\test\\resources\\subSet2.json")).forEach(System.out::println);
	}
	
}
