package fr.isima.myant;

public class Target {
	
	private String name;
	private Target dependancies[];
	private Task tasks[];
	
	public Task[] getTasks() {
		return tasks;
	}

	public void setTasks(Task[] tasks) {
		this.tasks = tasks;
	}

	public Target[] getDependancies() {
		return dependancies;
	}

	public void setDependancies(Target[] dependancies) {
		this.dependancies = dependancies;
	}

	public void execute() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
