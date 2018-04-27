package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ClassTest {

	double d1, d2, expected;

	@Parameters
	public static Iterable<Object[]> getData() {
		List<Object[]> parameters = Arrays.asList(new Object[][] { { 1, 2, 3 }, { 2, 2, 4 } });
		return parameters;
	}

	public ClassTest(double d1, double d2, double expected) {
		this.d1 = d1;
		this.d2 = d2;
		this.expected = expected;
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println("before class");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("after class");
	}

	@Before
	public void before() {
		System.out.println("before");
	}

	@After
	public void after() {
		System.out.println("after");
	}

	@Test
	public void testSame() {
		assertSame(d1, d2);
	}

	@Test
	public void testDouble() {
		assertEquals(d1, d2, 0.1);
	}

	@Test(timeout = 100)
	public void testTimeout() throws InterruptedException {
		Thread.sleep(151);
	}

	@Test(expected = Exception.class)
	public void testException() throws Exception {
		throw new Exception("test exception");
	}

}
