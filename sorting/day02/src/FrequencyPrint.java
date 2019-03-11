import java.util.Arrays;
import java.util.HashMap;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;

public class FrequencyPrint {

    static String frequencyPrint(String s) {

        String output = "";
        String[] words = s.split("\\W+");

        HashMap<String, Integer> word_counts = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            if (word_counts.containsKey(words[i])) {
                word_counts.put(words[i], word_counts.get(words[i]) + 1);
            } else {
                word_counts.put(words[i], 1);
            }
        }

        TreeMap<String, Integer> sortedMap = sortMapByValue(word_counts);
        System.out.println(sortedMap);


        for(Map.Entry<String,Integer> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println(key + " => " + value);
            output = prepend(key, value, output);
        }


        System.out.println(output);
        return output;
    }

    static String prepend(String a, int a_counts, String b){
        for(int j = 0; j<a_counts; j++){
            b = a + " " +b;
        }
        return b;
    }


    public static TreeMap<String, Integer> sortMapByValue(HashMap<String, Integer> map) {
        Comparator<String> comparator = new ValueComparator(map);
        //TreeMap is a map sorted by its keys.
        //The comparator is used to sort the TreeMap by keys.
        TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
        result.putAll(map);
        return result;
    }
}






class ValueComparator implements Comparator<String> {

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    public ValueComparator(HashMap<String, Integer> map) {
        this.map.putAll(map);
    }

    @Override
    public int compare(String s1, String s2) {
        if (map.get(s1) >= map.get(s2)) {
            return -1;
        } else {
            return 1;
        }
    }
}