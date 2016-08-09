import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer extends Thread implements Callback {
    ArrayBlockingQueue<ThingWrapper> queue;

    ExecutorService service = Executors.newFixedThreadPool(250);

    EnterFlow enterFlow;
    CreateThing createThing;
    ProcessEsperRules processEsperRules;
    UpdateCache updateCache;

    public Consumer(ArrayBlockingQueue<ThingWrapper> queue) {
	this.queue = queue;
	this.enterFlow = new EnterFlow();
	this.createThing = new CreateThing();
	this.processEsperRules = new ProcessEsperRules();
	this.updateCache = new UpdateCache();
    }

    @Override
    public void completed(ThingWrapper wrapper) {
	wrapper.goNextStep();
	try {
	    queue.put(wrapper);
	} catch (InterruptedException e) {
	    // nothing to do
	    Log.log(e.getMessage());
	}
    }

    @Override
    public void run() {
	while (true) {
	    ThingWrapper thingWrapper = null;
	    try {
		thingWrapper = queue.take();

	    } catch (InterruptedException e) {
		// nothing to do
		Log.log(e.getMessage());
	    }

	    if (thingWrapper != null) {
		StepExecutor stepExecutor = null;

		Steps step = thingWrapper.getStep();

		if (step != null) {
		    if (step.equals(Steps.ENTER)) {
			stepExecutor = new StepExecutor(thingWrapper, enterFlow, Consumer.this);
		    } else if (thingWrapper.getStep().equals(Steps.CREATE)) {
			stepExecutor = new StepExecutor(thingWrapper, createThing, Consumer.this);
		    } else if (thingWrapper.getStep().equals(Steps.ESPER)) {
			stepExecutor = new StepExecutor(thingWrapper, processEsperRules, Consumer.this);
		    } else if (thingWrapper.getStep().equals(Steps.UPDATECACHE)) {
			stepExecutor = new StepExecutor(thingWrapper, updateCache, Consumer.this);
		    }
		}

		if (stepExecutor != null) {
		    service.execute(stepExecutor);
		}

		if (stepExecutor == null && thingWrapper.getThing().getId().equals("kill")) {
		    // poison pill
		    service.shutdown();
		    break;
		}
	    }
	}
	
	Log.log("waiting for termination...");
	
	if (!service.isTerminated())
	    ;
	Log.log("terminated...");
    }

    public static class StepExecutor implements Runnable {
	ThingWrapper wrapper;
	Step step;
	Callback callback;

	public StepExecutor(ThingWrapper wrapper, Step step, Callback callback) {
	    this.wrapper = wrapper;
	    this.step = step;
	    this.callback = callback;
	}

	@Override
	public void run() {
	    step.doit(wrapper.getThing());
	    callback.completed(wrapper);
	}
    }

}
