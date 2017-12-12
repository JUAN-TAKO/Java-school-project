package Messages;

public class Message{
	private Action act;
	public Message(Action a){
		act = a;
	}

    /**
     * @return the act
     */
    public Action getAct() {
        return act;
    }

    /**
     * @param act the act to set
     */
    public void setAct(Action act) {
        this.act = act;
    }
	
}