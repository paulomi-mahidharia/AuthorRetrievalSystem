package com.neu.msd.AuthorRetriever.util;

import java.util.Stack;

import application.LoginScene;
import javafx.scene.Scene;

@SuppressWarnings({ "restriction"})
public final class SceneStack {
	
	public static Stack<Scene> sceneStack = null;

	public static void createSceneStack(){
		sceneStack = new Stack<>();
	}
	
	public static void pushSceneToStack(Scene scene){
		sceneStack.push(scene);
		System.out.println("STACK SIZE:::: "+sceneStack.size());
	}
	
	public static void popSceneToStack(){
		sceneStack.pop();
	}
	
	public static Scene getSceneAtTopOfStack(){
		return sceneStack.peek();
	}	
	
	public static void flushSceneStack(){
		sceneStack.clear();
		System.out.println("STACK SIZE:::: "+sceneStack.size());
	}
}
