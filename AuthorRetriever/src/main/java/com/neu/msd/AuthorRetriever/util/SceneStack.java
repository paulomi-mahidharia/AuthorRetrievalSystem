package com.neu.msd.AuthorRetriever.util;

import java.util.Stack;

import application.LoginScene;
import javafx.scene.Scene;



/**
 * This class is used to create a stack to store previous scene that was staged
 * @Given:Void
 * @return:A scene object
 * 
 *
 */
@SuppressWarnings({ "restriction"})
public final class SceneStack {
	
	public static Stack<Scene> sceneStack = null;
	public static Scene currentScene = null;

	public static Scene getCurrentScene() {
		return currentScene;
	}

	public static void setCurrentScene(Scene currentScene) {
		SceneStack.currentScene = currentScene;
	}

	public static void createSceneStack(){
		sceneStack = new Stack<>();
	}
	
	public static void pushSceneToStack(Scene scene){
		sceneStack.push(scene);
		System.out.println("STACK SIZE:::: "+sceneStack.size());
	}
	
	public static Scene getSceneAtTopOfStack(){
		return sceneStack.pop();
	}	
	
	public static void flushSceneStack(){
		sceneStack.clear();
		System.out.println("STACK SIZE:::: "+sceneStack.size());
	}
}
