package fr.isima.myant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class Project {
	
	private String name;
	private Target defaultTarget;
	private Target targets[];
	
	public Project(String filename) {
		
		try {
			loadFromFile(filename);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	public Target getDefaultTarget() {
		return defaultTarget;
	}

	public void setDefaultTarget(Target defaultTarget) {
		this.defaultTarget = defaultTarget;
	}


	public Target[] getTargets() {
		return targets;
	}

	public void setTargets(Target[] targets) {
		this.targets = targets;
	}

	public static void loadFromFile(String filename) throws ClassNotFoundException {

		try {
			
			String ligne;
			BufferedReader in = new BufferedReader(new FileReader(filename));
			List<Class> myClasses = new ArrayList<Class>();
			List<String> myClassesNames = new ArrayList<String>();

			while((ligne = in.readLine()) !=null) {

				if(!ligne.startsWith("#")) {

					if(ligne.startsWith("use")) {
						String[] tokens=ligne.split(" ");
											
						Class cls=Class.forName(tokens[1]);
						myClasses.add(cls);
						myClassesNames.add(tokens[3]);
					
					}
					else if (ligne.startsWith("default:")) {

						while((ligne= in.readLine()) !=null) {
							
							if (ligne.startsWith("echo[")){							
								System.out.println(ligne);
							}
							else {
							
								String[] defTokens=ligne.split("\\["); 
								String[] params=ligne.split("\""); 
								int ind=myClassesNames.indexOf(defTokens[0]);
								
								if (myClasses.get(ind).getName()=="fr.isima.myant.EchoTask") {								
									myClasses.get(ind).getConstructor(String.class).newInstance(params[1]);	
									
								}
								else if (myClasses.get(ind).getName()=="fr.isima.myant.CopyTask")
									myClasses.get(ind).getConstructor(String.class,String.class).newInstance(params[1],params[3]);
								else if (myClasses.get(ind).getName()=="fr.isima.myant.MkdirTask")
									myClasses.get(ind).getConstructor(String.class).newInstance(params[1]);
							}
						}
					}
				}
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
