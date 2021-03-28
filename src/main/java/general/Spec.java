package general;

import java.io.IOException;

import org.apache.log4j.Logger;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/* Create requestSpecification 
 * and get the environment dependent parameters 
 * from Environment Properties (EnvProb)
 * */
public class Spec {

	final static Logger log = Logger.getLogger(BasicLogger.class);

	public static RequestSpecification requestSpec;
	public static ResponseSpecification successResponseSpec;
	
	//Handle Specification related chnages from one Class
	public Spec() {
		try {
			new EnvProp();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}

		Spec.requestSpec = new RequestSpecBuilder()
				.setBaseUri(EnvProp.baseUrl)
				.addHeader("Content-Type", "application/json")
				.addFilter(new ResponseLoggingFilter())
				.build();
		;
	}

}
