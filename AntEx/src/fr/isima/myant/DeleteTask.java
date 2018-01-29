package fr.isima.myant;

public class DeleteTask extends Task{
	
	private String file;
	
	public DeleteTask(){
		
	}
	public DeleteTask(String file){
		super();
		this.file=file;
		System.out.println("Delete : File : " +file);
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public void execute(){
		System.out.println("Delete : File : " +file);
	}
}
