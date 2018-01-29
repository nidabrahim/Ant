package fr.isima.myant;

public class CompileTask extends Task{
	
	private String file;
	
	public CompileTask(){
		
	}
	public CompileTask(String file){
		super();
		this.file=file;
		System.out.println("Delete : File : " +file);
	}

	public String getMessage() {
		return file;
	}

	public void setMessage(String file) {
		this.file = file;
	}
	
	public void execute(){
		System.out.println("Compile : File : " +file);
	}
}
