package Messages;

public class Message{
	private MessageType type;
	public Message(MessageType t){
		type = t;
	}

    /**
     * @return the act
     */
    public MessageType getType() {
        return type;
    }

    /**
     * @param act the act to set
     */
    public void setType(MessageType t) {
        type = t;
    }
	
}