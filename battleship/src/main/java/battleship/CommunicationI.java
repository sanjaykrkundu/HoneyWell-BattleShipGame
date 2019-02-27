package battleship;

public interface CommunicationI {
	public void speak(String text);
	public String listen();
	public void close();
	public boolean isFirstPlayer();
}
