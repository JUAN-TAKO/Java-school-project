package Messages;

public class Message{
	private Action act;
	public Message(Action a){
		act = a;
	}

    /**
     * @return the act
     */
    public Action getAction() {
        return act;
    }

    /**
     * @param act the act to set
     */
    public void setAction(Action act) {
        this.act = act;
    }
	
}