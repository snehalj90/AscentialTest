import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FindNumberTest {

    FindNumber finder = new FindNumber();
    List<CustomNumberEntity> list = finder.readFromFile("src/test/java/resources/input.json");

    @Test
    public void shouldReturnTrueIfNumberIsFound(){
        Boolean result = finder.contains(45, list);
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfNumberIsNotFound(){
        Boolean result = finder.contains(88, list);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldHandleEmptyInputFile(){
        List<CustomNumberEntity> listEmpty = finder.readFromFile("src/test/java/resources/inputEmpty.json");
        Boolean result = finder.contains(-1, listEmpty);
        Assert.assertEquals(listEmpty.isEmpty(), true);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldHandleNonNumericInputFile(){
        List<CustomNumberEntity> listOutput = finder.readFromFile("src/test/java/resources/inputNonNumeric.json");
        Boolean result = finder.contains(100, listOutput);
        Assert.assertEquals(listOutput.isEmpty(), false);
        Assert.assertFalse(result);

    }

    @Test
    public void shouldThrowFormatExceptionForNoNInput(){
        List<CustomNumberEntity> listOutput = finder.readFromFile("src/test/java/resources/inputNonNumeric.json");
        Assert.assertThrows(NumberFormatException.class, ()-> finder.contains(Integer.parseInt("s"), listOutput));
    }

}
