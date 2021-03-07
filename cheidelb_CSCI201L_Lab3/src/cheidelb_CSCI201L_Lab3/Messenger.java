package cheidelb_CSCI201L_Lab3;
import java.util.Random;

class Messenger extends Thread{

	MessageQueue messageQueue;
	Random rand;
	public Messenger(MessageQueue mq) {
		messageQueue = mq;
		rand = new Random();
	}
	
	public void run() {
		for (int i = 1; i <= 20; i++) {
			try {
			String msg = "Message #" + i;
			messageQueue.addMessage(msg);
			System.out.println("Added Message: \"" + msg + "\" at time " + System.currentTimeMillis());
			Thread.sleep(rand.nextInt(1001));
			}
			catch (InterruptedException ie) {
				System.out.println("Interupted exception: " + ie);
			}
		}
	}
}

class Subscriber extends Thread {
	Random rand;
	MessageQueue messageQueue;
	
	public Subscriber(MessageQueue mq) {
		messageQueue = mq;
		rand = new Random();
	}
	
	public void run() {
		int count = 0;
		while(count < 20) {
			try {
				String message = messageQueue.getMessage();
				if(message.length() > 0) {
					System.out.println("Read Message: \"" + message + "\" at time " + System.currentTimeMillis());
					count+=1;
				} else {
					System.out.println("No message to read.");
				}
				Thread.sleep(rand.nextInt(1001));
				
			}
			catch (InterruptedException ie) {
				System.out.println("Interupted exception: " + ie);
			}
		}
	}
}