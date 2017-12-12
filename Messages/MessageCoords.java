
public class MessageCoords extends Message{
	private int cx;
	private int cy;
	public MessageCoords(Action a, int x, int y){
		super(a);
		cx = x;
		cy = y;
	}
	public int getX(){
		return cx;
	}
	public int getY(){
		return cy;
	}
}