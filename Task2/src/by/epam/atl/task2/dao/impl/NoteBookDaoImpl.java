package by.epam.atl.task2.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.dao.NoteBookDao;

public class NoteBookDaoImpl implements NoteBookDao {
	private String path = "data/";

	@Override
	public NoteBook loadNoteBookFromFile(String file_name) {
		NoteBook ntb = new NoteBook();
		List<Note> notes = new ArrayList<Note>();
		
		//work with xml files
		if (file_name.trim().endsWith(".xml")){
			
			//create file
			File fl = new File(path+file_name);
			
			//try to open file
			if (fl.exists() && fl.isFile() && fl.canRead()){
				try{				
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fl);
					
					//do normalization for xml file
					doc.getDocumentElement().normalize();
					
					//get all nodes with tag <note>. It is notes in notebook
					NodeList nList = doc.getElementsByTagName("node");
					
					for (int i =0; i<nList.getLength(); i++ ){
						Node node = nList.item(i);
						Element eElement = (Element) node;
						
						//get date
						String dt = eElement.getElementsByTagName("date").item(0).getTextContent();
						Date date = new Date(dt);
						
						//get content
						String note = eElement.getElementsByTagName("content").item(0).getTextContent();
						
						//create note
						Note nt = new Note();
						nt.setDate(date);
						nt.setNote(note);
						
						//add note to list
						notes.add(nt);
						
					}
					
					//write notes into notebook
					ntb.setNoteBook(notes);
					
				}
				catch(Exception e){
					e.getStackTrace();
				}
			}
		}
		
		return ntb;
	}

	@Override
	public File saveNoteBookIntoFile(NoteBook ntb) {
		// TODO Auto-generated method stub
		return null;
	}

}
