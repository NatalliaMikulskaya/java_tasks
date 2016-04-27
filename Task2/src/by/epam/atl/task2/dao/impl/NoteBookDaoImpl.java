package by.epam.atl.task2.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.dao.NoteBookDao;

public class NoteBookDaoImpl implements NoteBookDao {

	@Override
	public NoteBook loadNoteBookFromFile(String file_name) {
		NoteBook ntb = new NoteBook();
		List<Note> notes = new ArrayList<Note>();
		
		//work with xml files
		if (file_name.trim().endsWith(".xml")){
			
			//create file
			File fl = new File(file_name);
			
			//try to open file
			if (fl.exists() && fl.isFile() && fl.canRead()){
				System.out.println("-----");
				try{				
					System.out.println("OK");
					
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
			}else{
				System.out.println("file error");
			}
		}
		
		return ntb;
	}

	@Override
	public File saveNoteBookIntoFile(NoteBook ntb, String file_name) {
		
		File fl = new File(file_name);
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			//Element rootElement = doc.createElement("body");
			//doc.appendChild(rootElement);
			Element rootElement = doc.createElement("body");
			doc.appendChild(rootElement);
			
			// root elements
			
		
			for (Note nt : ntb.getNoteBook()){
				Element noteElement = doc.createElement("note");
				doc.appendChild(noteElement);
				
				Element date = doc.createElement("date");
				date.appendChild(doc.createTextNode(nt.getDate().toString()));
				noteElement.appendChild(date);
				
				Element content = doc.createElement("content");
				content.appendChild(doc.createTextNode(nt.getNote()));
				noteElement.appendChild(content);

			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fl);

			transformer.transform(source, result);
		}
		catch( Exception e){
			e.printStackTrace();
		}
		
		return fl;
	}

}
