import com.my.json.compare.JsonCompare;
import com.my.json.compare.rules.TypeRule;
import com.my.json.compare.rules.ValueRule;
import com.my.json.compare.rules.interfaces.ComparisionRule;
import junit.framework.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JsonCompareTest {
	
	@Test
	public void testJsonCompare() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		JsonCompare.compare(
				new File("src/test/resources/superSet2.json"), 
				new File("src/test/resources/subSet2.json"),null).forEach(System.out::println);
		Assert.assertTrue(JsonCompare.compare(
				new File("src/test/resources/superSet2.json"), 
				new File("src/test/resources/subSet2.json"),
				null).size()>0);
	}
	
	@Test
	public void testJsonCompareValue() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		JsonCompare.compare(
				new File("src/test/resources/superSet3.json"),
				new File("src/test/resources/subSet3.json"),new ValueRule()).forEach(System.out::println);;
		Assert.assertTrue(JsonCompare.compare(
				new File("src/test/resources/superSet3.json"), 
				new File("src/test/resources/subSet3.json"),
				new ValueRule()).size()==0);
	}
	
	@Test
	public void testJsonCompareValueType() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		JsonCompare.compare(
				new File("src/test/resources/superSetType.json"),
				new File("src/test/resources/subSetType.json"),
				new TypeRule()).forEach(System.out::println);
		Assert.assertTrue(JsonCompare.compare(
				new File("src/test/resources/superSetType.json"), 
				new File("src/test/resources/subSetType.json"),
				new TypeRule()).size()==0);
	}
	
	@Test
	public void testJsonCompareSubsetNull() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		JsonCompare.compare(
				new File("src/test/resources/superSetType.json"),
				null,null).forEach(System.out::println);

		Assert.assertTrue(JsonCompare.compare(
				new File("src/test/resources/superSetType.json"), 
				null,null).size()>0);
	}
	
	@Test
	public void testJsonCompareSuperSetNull() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		JsonCompare.compare(
				null,
				new File("src/test/resources/subSetType.json"),null).forEach(System.out::println);

		Assert.assertTrue(JsonCompare.compare(
				null, 
				new File("src/test/resources/subSetType.json"),null).size()>0);
	}
	
	@Test
	public void testJsonCompareSubsetEmpty() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		JsonCompare.compare(
				Optional.ofNullable("{\"name\":\"John\",\"surname\":\"Doe\"}"),
				Optional.ofNullable("{}"),null).forEach(System.out::println);
		Assert.assertTrue(JsonCompare.compare(
				Optional.ofNullable("{\"name\":\"John\",\"surname\":\"Doe\"}"), 
				Optional.ofNullable("{}"),null).size()>0);
	}
	
	@Test
	public void comparewithSpecificRule() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		JsonCompare.comparewithSpecificRule(
				new File("src/test/resources/superSet2.json"),
				new File("src/test/resources/subSet2.json"),null).forEach(System.out::println);
		Assert.assertTrue(JsonCompare.comparewithSpecificRule(
				new File("src/test/resources/superSet2.json"),
				new File("src/test/resources/subSet2.json"),
				null).size()>0);
	}

	@Test
	public void comparewithSpecificRule1() throws MalformedURLException, IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		Map<String, ComparisionRule> rules = new HashMap();

		rules.put("$.lastName",new TypeRule());
		rules.put("$.bestFriends[0].firstName",new TypeRule());

		JsonCompare.comparewithSpecificRule(
				new File("src/test/resources/superSet4.json"),
				new File("src/test/resources/subSet4.json"),rules).forEach(System.out::println);
		Assert.assertTrue(JsonCompare.comparewithSpecificRule(
				new File("src/test/resources/superSet4.json"),
				new File("src/test/resources/subSet4.json"),
				rules).size()==0);
	}


}
