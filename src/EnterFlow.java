
public class EnterFlow implements Step {

    @Override
    public void doit(Thing thing) {
	Log.log("Entering flow - thing id: " + thing.getId());
    }

}