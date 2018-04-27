package musicbox;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class VTest {
@Test
public void v () {

	List<Integer> coll1 = asList(1,2);
	List<Integer> coll2 = asList(2,1);
	
	//option1
	Collections.sort(coll1);
	Collections.sort(coll2);
	coll1.equals(coll2);
	
	//option2
	assertEquals(new HashSet<>(coll1), new HashSet<>(coll2));
	
}
}
