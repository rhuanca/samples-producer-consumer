import java.util.concurrent.ArrayBlockingQueue;

import com.sun.media.jfxmedia.logging.Logger;

public class Producer extends Thread {
    int maxNumberOfTags;
    ArrayBlockingQueue<ThingWrapper> queue;

    public Producer(ArrayBlockingQueue<ThingWrapper> queue, int maxNumberOfTags) {
	this.queue = queue;
	this.maxNumberOfTags = maxNumberOfTags;
    }

    @Override
    public void run() {
	for (int i = 0; i < this.maxNumberOfTags; i++) {
	    String id = "" + (i + 1);
	    Thing thing = new Thing(id, (i + 1));

	    try {
		Log.log("creating: " + thing.getId());
		queue.put(new ThingWrapper(thing, Steps.ENTER));
	    } catch (InterruptedException e) {
		// nothing to do
		System.out.println(e);
	    }
	}
	try {
	    queue.put(new ThingWrapper(new Thing("kill", -1), Steps.ENTER));
	} catch (InterruptedException e) {
	    // nothing to do
	    System.out.println(e);
	}
    }
}
