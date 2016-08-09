
public class UpdateCache implements Step {

    @Override
    public void doit(Thing thing) {
	Log.log("Update cache - thing id: " + thing.getId());
	try {
	    Thread.sleep(20);
	} catch (InterruptedException e) {
	    // nothing to do
	}
    }
}