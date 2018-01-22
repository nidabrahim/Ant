package fr.isima.myant;

public class EchoTask extends Task{
	
	private String message;
	
	public EchoTask(String msg){
		super();
		this.message=msg;
		System.out.println("EchoTask : Message : " +message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
