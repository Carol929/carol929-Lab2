import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class UnitTestExample {

	private Integer count; // not used, just showing a style issue
	private String planet = "Earth";
	private String satellite = "Moon";

	@Test
	public void test1() {
		String expectedName = "Earth";
		String resultName = this.planet; // or would be something a method could return, etc
		assertEquals(expectedName, resultName);
	}

	@Test
	public void test2() {
		String expectedName = "Moon";
		String resultName = this.satellite; // or would be something a method could return, etc
		assertEquals(expectedName, resultName);
	}	

	// illustrates running something from the terminal via Java/Junit
	@Test
	public void test3() {
		String outputCollected = "";

		String command = "java -Dfile.encoding=UTF-8 -Duser.language=en -Duser.country=US " 
                 + "-jar checkstyle-9.2.1-all.jar -c ./CS1111_checks.xml UnitTestExample.java";

		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
			String line;
			while ((line = reader.readLine()) != null) 
				outputCollected += line;
			reader.close();

			reader = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
			while ((line = reader.readLine()) != null) 
				outputCollected += line;
			reader.close();
			process.waitFor();
		} catch (IOException | InterruptedException exc) {
			exc.printStackTrace();
		}
		
		System.out.println(outputCollected);

		String expectedName = "Starting audit...Audit done.";
		String resultName = outputCollected; 
		assertEquals(expectedName, resultName);
	}

	// no main method

}
