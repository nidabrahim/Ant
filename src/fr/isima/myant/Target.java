package fr.isima.myant;

import java.util.ArrayList;

public class Target {
	
	private String name;
	private ArrayList<Target> dependancies;
	private ArrayList<Task> tasks;
	
	public Target() {

		dependancies = new ArrayList<Target>();
		tasks = new ArrayList<Task>();
	}
	public ArrayList<Target> getDependancies() {
		return dependancies;
	}

	public void setDependancies(ArrayList<Target> dependancies) {
		this.dependancies = dependancies;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public void execute() {
		System.out.println(name + " : [");
		for(Target t : dependancies) {
			t.execute();
		}
		for(Task t : tasks) {
			t.execute();
		}
		System.out.println("]");

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
