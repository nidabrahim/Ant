package fr.isima.myant;

public class MkdirTask extends Task{
	
	private String dir;
	
	public MkdirTask (String rep) {
		super();
		this.dir = rep;
		System.out.println("MkdirTask : Dir : " + dir);
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
