package fr.isima.myant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;


public class Project {
	
	private String name;
	private Target defaultTarget;
	private ArrayList<Target> targets;
	
	public Project(String filename) {
		targets = new ArrayList<Target>();
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


	public ArrayList<Target> getTargets() {
		return targets;
	}

	public void setTargets(ArrayList<Target> targets) {
		this.targets = targets;
	}

	@SuppressWarnings("unchecked")
	public void loadFromFile(String filename) throws ClassNotFoundException {

		try {
			
			String ligne, nextTarget = null;
			BufferedReader in = new BufferedReader(new FileReader(filename));
			List<Class> myClasses = new ArrayList<Class>();
			List<String> myClassesNames = new ArrayList<String>();
			HashMap<String,String[]> depMap = new HashMap<String,String[]>();
			while(true) {
				if(nextTarget == null ) {
					ligne = in.readLine();
				}else {
					ligne = nextTarget;
					nextTarget = null;
				}
				if( ligne == null) break;
				if(!ligne.startsWith("#")) {
					if(ligne.startsWith("use")) {
						String[] tokens=ligne.split(" ");
						Class cls=Class.forName(tokens[1]);
						myClasses.add(cls);
						myClassesNames.add(tokens[3]);
					}
					else if(ligne.contains(":")) {					
						String targetName = ligne.split(":")[0].trim();
						String [] Dependencies= {};
						if(ligne.split(":").length > 1)
							Dependencies  = ligne.split(":")[1].split(","); 
						depMap.put(targetName,Dependencies);
						Target target = new Target();
						target.setName(targetName);
						while((ligne= in.readLine()) !=null) {
							if(!ligne.contains("[")) {
								nextTarget = ligne;
								break;
							}
							String[] defTokens=ligne.split("\\["); 
							HashMap<String, String> params = new HashMap<>();
							for(String s : ligne.split("\\[")[1].substring(0,  ligne.split("\\[")[1].length()-1).split(",")) {
								params.put( s.split(":")[0].trim(),  s.split(":")[1].replace("\"", "").trim());
							}
							Task t = null;
							if(defTokens[0].trim().equals("echo")) {
								for (String key : params.keySet()) {
									t = new EchoTask();
									utility.classHelper.invokeSetter(t, key, params.get(key));  
								}
							}else {
								int ind=myClassesNames.indexOf(defTokens[0]);
								for(Class cl : utility.classHelper.getClasses("fr.isima.myant")) {
										if(cl.getModifiers() == Modifier.STATIC) continue;
										if(myClasses.get(ind).getName().equals(cl.getName())) {
											t = (Task) myClasses.get(ind).getConstructor().newInstance();
											for (String key : params.keySet()) {
												utility.classHelper.invokeSetter(t, key, params.get(key));  
											}
										}						
								}
							}
							if(t!= null) target.getTasks().add(t);	
						}
						if(targetName.equals("default")) {
							defaultTarget = target;
						}else {
							targets.add(target);
						}
						depMap.put(targetName, Dependencies);
					}
				}
			} 
			
			for (String key : depMap.keySet()) {
				Target t = getTargetByName(key);
				if(key.equals("default")) t = defaultTarget;
				if(t != null) {
					for(String dep : depMap.get(key)) {
						Target dependency = getTargetByName(dep.trim());
						if(t != null) {
							t.getDependancies().add(dependency);
						}else {
							System.err.println("dependency '"+key+"' doesn't exist");
						}
					}
				}else {
					System.err.println("Target '"+key+"' doesn't exist");
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
			e.printStackTrace();
		}
	}

	public void execute() {
		defaultTarget.execute();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Target getTargetByName(String name){
		for(Target t : targets) {
			if(t.getName().equals(name)) return t;
		}
		return null;
	}

}
