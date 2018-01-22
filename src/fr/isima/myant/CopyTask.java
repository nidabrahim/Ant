package fr.isima.myant;


public class CopyTask extends Task{
	
	private String from;
	private String to;
	
	public CopyTask(String send,String recv ) {
		super();
		this.from = send;
		this.to = recv;
		System.out.println("CopyTask : From " + from + " To " + to);
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	

}
