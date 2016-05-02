package by.epam.atl.task2.service.impl;

import by.epam.atl.task2.bean.NoteBook;

public class NoteBookProvider {
	private static NoteBook instance;
	
	private  NoteBookProvider(){
		instance = null;
	}
	
	public static NoteBook getInstance(){
		if (instance == null){
			instance = new NoteBook();
		}
		return instance;
	}
	
	public static void setInstance(NoteBook ntb){
		instance = ntb;
	}
	
}
