import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    public static void main(String args[]) {
	ArrayBlockingQueue<ThingWrapper> queue = new ArrayBlockingQueue<>(500);

	Consumer consumer = new Consumer(queue);
	consumer.start();

	long t0 = System.currentTimeMillis();
	Producer producer = new Producer(queue, 500);
	producer.start();
	
	try {
	    consumer.join();
	    producer.join();
	} catch (InterruptedException e) {
	    // nothing to do for now
	}
	long t1 = System.currentTimeMillis();
	Log.log("total time: " + (t1 - t0) + " millis");

    }
}
