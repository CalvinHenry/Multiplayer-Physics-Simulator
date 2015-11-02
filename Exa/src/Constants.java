import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/*
 * This class includes methods and variables that are constant and unchanging, and do not fit well in any one class of the program
 * Including math operators, and other static methods needed in multiple classes
 */
public class Constants {
	
	public static BufferedImage[] images = new BufferedImage[5];
	
	/*
	 * 0 - Qufeb
	 * 1 - 
	 * 2 - 
	 */
	
	public static void initializeImages() throws java.io.IOException{
		images[0] = ImageIO.read(new File(System.getProperty("user.home") + "/Desktop/Exa/background.png"));
		images[1] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/Qufeb.png")));
		images[2] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/bigBoii.png")));
	}
	
	public static class Socket {
		public static final int UPDATE_TIME = 10;
		public static final int SERVER_REFRESH = 150;
		public static final int CLIENT_REFRESH = 140;
		public static final int CYCLES_TO_SERVER_UPDATE = SERVER_REFRESH / (2 * UPDATE_TIME); //The number of times the client refreshes the screen before the server sends an update
	}
	public static ArrayList<Message> entityToMessage(java.util.List<Entity> list){
		ArrayList<Message> retrn = new ArrayList<>();
		for(int i = 0; i < list.size(); i ++){
			retrn.add(new Message(list.get(i)));
		}
		return retrn;
	}
	
	public static ArrayList<Entity> messageToEntity(ArrayList<Message> list){
		ArrayList<Entity> retrn = new ArrayList<>();
		for(int i = 0; i < list.size(); i ++){
			retrn.add(new Entity(list.get(i)));
		}
		return retrn;
	}
}