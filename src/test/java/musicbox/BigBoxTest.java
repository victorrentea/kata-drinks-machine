package musicbox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BigBoxTest {
	
	@Mock
	private GetraenkBox littleBox1Mock;
	
	
	//TODO best way: have the big box receive the list of little boxes., and NOT instantiate them itself.
	@Test
	public void m() {
		GetraenkeBox bigBox = new GetraenkeBox(1, 1);
		bigBox.getraenkeBoxes.set(0, littleBox1Mock);
	}
	
	// TODO it may work to unit test the big box working with real little boxes.
}
