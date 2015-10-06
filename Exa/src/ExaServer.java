import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ExaServer {
	
	
	static ArrayList<Player> players;
	static java.util.List map;
	final int SLEEP_TIME = 70;
	final int MAX_PLAYERS = 30;
	static int counter = 0;
	
	public static void main(String[] args) {
		new ExaServer();
	}
	public ExaServer(){
		players = new ArrayList<Player>();
		map = Collections.synchronizedList(new ArrayList<Entity>());
		new Listener().start();
		new Updater().start();
		runGame();
	}
	public void runGame(){
		System.out.println("Server Running");
		while(true){
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Players connected: " + players.size());
			
			for(int i = 0; i < players.size(); i ++){
				try {
					players.get(i).getOutput().writeObject(Constants.entityToMessage(map));
					System.out.println("here");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	private class Listener extends Thread{
		ServerSocket socket;
		public Listener(){
			try{
				socket = new ServerSocket(8520);
				
			}catch(Exception e){
				
			}
		}
		public void run(){
			while(true){
			try {
				new Player(socket.accept()).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
		}

	}
	private class Updater extends Thread{
		public void run(){
			while(true){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Updating");
			for(int i = 0; i < map.size(); i ++){
				try{
				((Entity)(map.get(i))).updateLocation();
				System.out.println(((Entity)(map.get(i))).getResultant());
				System.out.println(((Entity)(map.get(i))).getLocation());
				}catch(Exception e){
					System.out.println("There as been a small blip in program productivity. Please hold, and your space journey should resume shortly. In fact, it probably resumed before you had time to process that these words appeard on the screen.");
				}
				}
			}
		}
		
	}
	private class Player extends Thread{
		private Entity entity;
		private Socket socket;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private int ID;
		public Player(Socket socket){
			System.out.println("Player added");
			
			this.ID = getID();
			this.socket = socket;
			try{
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
				output.writeInt(ID);
			}catch(Exception e){
				
			}
			
			players.add(this);
		}
		public ObjectOutputStream getOutput(){
			return output;
		}
		
		public int getID(){
			return counter++;
		}

		public void run(){
			Entity temp;
			while(true){
				try {
					
					temp = new Entity((Message)input.readObject());
					if(map.indexOf(entity) != -1)
						map.remove(map.indexOf(entity));
					map.add(temp);
					entity = temp;
					
					System.out.println(map.size());
					
				} catch (ClassNotFoundException e) {
					System.out.println("YOU HAVE NO CLASS");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Io exception");
					//e.printStackTrace();
				}
			}
		}
	}

}
