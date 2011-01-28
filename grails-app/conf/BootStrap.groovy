import org.acme.*

class BootStrap {

     def init = { servletContext ->
		Role.withTransaction {
			def count = Role.count()
		    if(!count) {						
				new Role(name:"User").save()
			}			
		}
     }
     def destroy = {
     }
}