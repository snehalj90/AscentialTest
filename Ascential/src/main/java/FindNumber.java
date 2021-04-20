import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FindNumber class implements NumberFinder Interface
 * And it's methods.
 */

public class FindNumber implements NumberFinder {
    @Override
    public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
        FastestComparator comparator = new FastestComparator();
        int result;
        for(CustomNumberEntity pojo: list) {
            if(pojo.getNumber()!= null && isNumeric(pojo.getNumber())) {
                result = comparator.compare(valueToFind, pojo);
                if (result == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<CustomNumberEntity> readFromFile(String filePath) {
        List<CustomNumberEntity> customNumberList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            CustomNumberEntity[] customNumberEntities = objectMapper.readValue(new File(filePath), CustomNumberEntity[].class );
             customNumberList = Arrays.asList(customNumberEntities);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return customNumberList;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
