package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MuenzBeutelTest.class, GetraenkBoxTest.class, GetraenkeBoxTest.class, GetraenkAutomatTest.class })
public class AllTests {

}
