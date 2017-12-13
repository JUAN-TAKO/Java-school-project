package Messages;

public class MessageId extends Message{
	private int id;
	public MessageId(MessageType t, int x, int y){
		super(t);
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