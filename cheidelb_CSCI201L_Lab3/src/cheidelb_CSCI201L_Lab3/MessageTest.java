package cheidelb_CSCI201L_Lab3;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageTest {

	public static void main(String[] args) {
		MessageQueue mq = new MessageQueue();
		
		Subscriber sub = new Subscriber(mq);
		Messenger mes = new Messenger(mq);
		ExecutorService ex = Executors.newFixedThreadPool(2);
		
		ex.execute(mes);
		ex.execute(sub);
		ex.shutdown();
		
		while(!ex.isTerminated()) Thread.yield();
		
		
		System.out.println("done");
		
		//System.out.println("done");
	}

}
