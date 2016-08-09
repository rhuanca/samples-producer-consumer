
public class ProcessEsperRules implements Step {

    @Override
    public void doit(Thing thing) {
	Log.log("Esper rules - thing id: " + thing.getId());
        try {
    	Thread.sleep(20);
        } catch (InterruptedException e) {
    	// nothing to do
        }
    }

}