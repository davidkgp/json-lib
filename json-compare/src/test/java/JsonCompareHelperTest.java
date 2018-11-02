import com.my.json.compare.JsonCompareHelper;
import org.junit.Assert;
import org.junit.Test;


public class JsonCompareHelperTest {

    private String superSet = "{\n" +
            "    \"address\": {\n" +
            "        \"addressLine1\": \"1026YV\",\n" +
            "        \"addressLine2\": \"Arjenstraat\"\n" +
            "    },\n" +
            "    \"firstName\": \"Mickey\",\n" +
            "    \"lastName\": \"Mouse\"\n" +
            "}";
    private String subSet = "{\n" +
            "    \"address\": {\n" +
            "        \"addressLine1\": \"1026YV\",\n" +
            "        \"addressLine2\": \"Arjenstraat\"\n" +
            "    },\n" +
            "    \"firstName\": \"Mickey\",\n" +
            "    \"lastName\": \"Mouse\"\n" +
            "}";

    private String superSetArray = "{\n" +
            "    \"book\": [\n" +
            "        {\n" +
            "            \"author\": \"Nigel Rees\",\n" +
            "            \"category\": \"reference\",\n" +
            "            \"price\": 8.95,\n" +
            "            \"title\": \"Sayings of the Century\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"author\": \"Evelyn Waugh\",\n" +
            "            \"category\": \"fiction\",\n" +
            "            \"price\": 12.99,\n" +
            "            \"title\": \"Sword of Honour\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"author\": \"Herman Melville\",\n" +
            "            \"category\": \"fiction\",\n" +
            "            \"isbn\": \"0-553-21311-3\",\n" +
            "            \"price\": 8.99,\n" +
            "            \"title\": \"Moby Dick\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private String subSetArray = "{\n" +
            "    \"book\": [\n" +
            "        {\n" +
            "            \"author\": \"Nigel Rees\",\n" +
            "            \"category\": \"reference\",\n" +
            "            \"price\": 8.95,\n" +
            "            \"title\": \"Sayings of the Century\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"author\": \"Evelyn Waugh\",\n" +
            "            \"category\": \"fiction\",\n" +
            "            \"price\": 12.99,\n" +
            "            \"title\": \"Sword of Honour\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"author\": \"Herman Melville\",\n" +
            "            \"category\": \"fiction\",\n" +
            "            \"isbn\": \"0-553-21311-3\",\n" +
            "            \"price\": 8.99,\n" +
            "            \"title\": \"Moby Dick\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    public  void testlog1(){
        System.out.println(JsonCompareHelper.log(null,null));
        Assert.assertNotNull(JsonCompareHelper.log(null,null));
    }

    @Test
    public  void testlog2(){
        System.out.println(JsonCompareHelper.log(null,3));
        Assert.assertNotNull(JsonCompareHelper.log(null,3));
    }

    @Test
    public  void testlog3(){
        System.out.println(JsonCompareHelper.log(2,null));
        Assert.assertNotNull(JsonCompareHelper.log(2,null));
    }

    @Test
    public  void testlog4(){
        System.out.println(JsonCompareHelper.log(2,3));
        Assert.assertNotNull(JsonCompareHelper.log(2,3));
    }

    @Test
    public  void logSizeArray(){
        System.out.println(JsonCompareHelper.logSizeArray(2,3));
        Assert.assertNotNull(JsonCompareHelper.logSizeArray(2,3));
    }

    @Test
    public  void logSizeArray1(){
        System.out.println(JsonCompareHelper.logSizeArray(0,0));
        Assert.assertNotNull(JsonCompareHelper.logSizeArray(0,0));
    }

    @Test
    public  void testlog5(){

        System.out.println(JsonCompareHelper.log(superSet,null));
        Assert.assertNotNull(JsonCompareHelper.log(superSet,null));
    }

    @Test
    public  void testlog6(){
        System.out.println(JsonCompareHelper.log(null,subSet));
        Assert.assertNotNull(JsonCompareHelper.log(null,subSet));
    }

    @Test
    public  void testlog7(){
        System.out.println(JsonCompareHelper.log(superSet,subSet));
        Assert.assertNotNull(JsonCompareHelper.log(superSet,subSet));
    }

    @Test
    public  void testlog8(){
        System.out.println(JsonCompareHelper.log(superSetArray,subSetArray));
        Assert.assertNotNull(JsonCompareHelper.log(superSetArray,subSetArray));
    }
}
