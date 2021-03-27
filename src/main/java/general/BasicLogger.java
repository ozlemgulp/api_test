package general;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class BasicLogger {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(BasicLogger.class.getName());

	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();

	}

}
