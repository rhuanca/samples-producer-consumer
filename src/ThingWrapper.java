
public class ThingWrapper {
    Thing thing;
    Steps step;

    public ThingWrapper(Thing thing, Steps step) {
	this.thing = thing;
	this.step = step;
    }

    public Steps getStep() {
	return step;
    };
    
    public Thing getThing() {
        return thing;
    }

    public void goNextStep() {
	if (this.step == Steps.ENTER) {
	    this.step = Steps.CREATE;
	} else if (this.step == Steps.CREATE) {
	    this.step = Steps.ESPER;
	} else if (this.step == Steps.ESPER) {
	    this.step = Steps.UPDATECACHE;
	} else if (this.step == Steps.UPDATECACHE) {
	    this.step = null;
	}
    }
}
