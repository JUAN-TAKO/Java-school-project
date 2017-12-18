package Utils;

public class MessageId extends Message{
	private int idm;
	public MessageId(MessageType t, int id){
		super(t);
		idm = id;
	}
	public int getId(){
		return idm;
	}
}