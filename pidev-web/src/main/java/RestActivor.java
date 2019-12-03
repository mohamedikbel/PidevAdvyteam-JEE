

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



@ApplicationPath("api")
public class RestActivor extends Application {
	 private Set<Class<?>> resources = new HashSet<Class<?>>();

	    public RestActivor () {
	        resources.add(RestActivor.class);
	    }

	    @Override
	    public Set<Class<?>> getClasses() {
	        return resources;
	    }

}
