import org.junit.Test;

public class Test_001 {

	
	
	@Test
	public void test_001() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		
		System.out.println(Class.forName("org.cc.imageViewer.core.OpenAction").newInstance());
		
		
	}
	
	
	
}
