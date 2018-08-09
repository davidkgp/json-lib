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
		JsonCompare.compare(JsonParse.getJsonObject(getFileContent(new File("src\\test\\resources\\superSet2.json"))).get(), JsonParse.getJsonObject(getFileContent(new File("src\\test\\resources\\subSet2.json"))).get()).forEach(System.out::println);
	}
	
	public Optional<String> getFileContent(File fileObj)
			throws MalformedURLException, IOException, URISyntaxException {

		return Optional.ofNullable(fileObj.exists() && fileObj.isFile() && fileObj.canRead()
				? new String(Files.readAllBytes(Paths.get(fileObj.toURI())))
				: null);

	}

}
