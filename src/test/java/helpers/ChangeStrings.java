package helpers;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeStrings {
    @Test
    public void nameChangeTest(){
        String name = ("Middle-earth™: Shadow of War™ Standard Edition");
        String x = this.removeSpecialCharactersAndEditions(name);
        System.out.println("Fixed: " + x);
    }

    public static String removeSpecialCharactersAndEditions(String name){
        name = name.replace("™", "").replace("®", "").trim();

        String[] nameSplitArray = name.split(" ");
        List<String> nameWords = new ArrayList<String>(Arrays.asList(nameSplitArray));

        boolean containsEdition = nameWords.remove("Edition");
        if(containsEdition){
            nameWords.remove(nameWords.size()-1);
        }
        nameWords.remove("-");

        String fixed = String.join(" ", nameWords);

        return fixed;
    }

}
