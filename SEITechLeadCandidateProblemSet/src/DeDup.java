/**
* <h1>Primitive int Array Deduplication</h1>
* The DeDup program implements an application that contains 3 methods that returns an array 
* with no duplicate values. 
*
* @author  Dewey C. Layman
* @version 1.0
* @since   2016-10-09 
*/
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeDup {
    public int[] randomIntegers = {
    	1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
    	20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
    	13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11
    };   

    /**
     * This method is used as the driver for testing the following 3 methods:
     *      (1) public int[] deDupPrimitive();
     *      (2) public int[] deDupJavaObjects();
     *      (3) public int[] deDupJava8Lambda();
     */
    public static void main(String [] args) {
    	DeDup deDup = new DeDup();
    	
    	System.out.println(Arrays.toString(deDup.deDupPrimitive()));
    	System.out.println(Arrays.toString(deDup.deDupJavaObjects()));
    	System.out.println(Arrays.toString(deDup.deDupJava8Lambda()));
    }
    
    /**
     * This method is used to filter out duplicate values from the global int array. The 
     * duplicate values are removed using typical "old-school" style of looping through the 
     * array and repopulating a temporary array with the desired filtered array.
     * @return int[] Returns an array that has no duplicated and maintains the original order.
     */
    public int[] deDupPrimitive() {
    	// NOTE: This Method Maintains Original Order
    	int[] temp = new int[randomIntegers.length];
    	int j = 0;

    	for (int i = 0; i < randomIntegers.length; i++) {
    		if (i == 0) {
    			temp[j] = randomIntegers[i];
    			j++;
    		} else {
    			boolean found = false;
    			
    			for (int k = 0; k < j; k++) {
    				if (temp[k] == randomIntegers[i]) {
    					found = true;
    					break;
    				}
    			}
    			
    			if (!found) {
        			temp[j] = randomIntegers[i];
        			j++;
    			}
    		}
    	}
    	
    	return Arrays.copyOf(temp, j);
    }
    
    /**
     * This method is used to filter out duplicate values from the global int array. This 
     * method utilizes Java List and Collection objects for filtering duplicate values and
     * sorting the values in descending order. 
     * @return int[] Returns an array that has no duplicates and is sorted in descending order.
     */
    public int[] deDupJavaObjects() {
    	List<Integer> list = new LinkedList<Integer>();
    	
    	for (int i = 0; i < randomIntegers.length; i++) {
    		if (!list.contains(new Integer(randomIntegers[i]))) {
    			list.add(randomIntegers[i]);
    		}
    	}
    	
    	Collections.sort(list, Collections.reverseOrder());
    	
    	int[] temp = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		temp[i] = list.get(i);
    	}

    	return temp;
    }
    
    /**
     * This method is used to filter out duplicate values from the global int array. This 
     * method utilizes new features found in Java 8. The end result is a list that has the
     * duplicates filtered out and is also sorted in ascending order. 
     * @return int[] Returns an array that has no duplicates and is sorted in ascending order.
     */
    public int[] deDupJava8Lambda() {
    	List<Integer> list = 
    			IntStream.of(randomIntegers)
    			.boxed()
    			.collect(Collectors.toList())
    			.parallelStream()
    			.distinct()
    			.collect(Collectors.toList());
    	
    	Collections.sort(list);
    	
    	return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
