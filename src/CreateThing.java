
public class CreateThing implements Step {

    @Override
    public void doit(Thing thing) {
	Log.log("Insert thing - thing id: " + thing.getId());
	try {
	    Thread.sleep(20);
	} catch (InterruptedException e) {
	    // nothing to do
	}
    }

}