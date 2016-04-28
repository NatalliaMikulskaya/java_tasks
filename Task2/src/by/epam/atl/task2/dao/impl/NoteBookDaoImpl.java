package by.epam.atl.task2.dao.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.dao.NoteBookDao;

public class NoteBookDaoImpl implements NoteBookDao {

	@Override
	public NoteBook loadNoteBookFromFile(String fileName) {
		NoteBook ntb = new NoteBook();
		List<Note> notes = new ArrayList<Note>();
		
		//work with xml files
		if (fileName.trim().endsWith(".xml")){
			
			//create file
			File fl = new File(fileName);
			
			//try to open file
			if (fl.exists() && fl.isFile() && fl.canRead()){
				
				try{				
										
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fl);
					
					//do normalization for xml file
					doc.getDocumentElement().normalize();
					
					//get all nodes with tag <note>. It is notes in notebook
					NodeList nList = doc.getElementsByTagName("note");
					
					//parse nodes
					notes = processNodeList(nList);
					
					//write notes into notebook
					ntb.setNoteBook(notes);
					
				}
				catch(IOException e){
					System.err.println("XML reading error");
				}
				catch(ParserConfigurationException e){
					System.err.println("XML reading error");
				}
				catch(SAXException e){
					System.err.println("XML reading error");
				}
			}else{
				System.err.println("file error");
			}
		}
		
		return ntb;
	}
	
	private List<Note> processNodeList(NodeList nList){
		List<Note> listNotes = new ArrayList<Note>();
		
		for (int i =0; i<nList.getLength(); i++ ){
			
			Node node = nList.item(i);
			Date date = null;
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				
				//get date
				String dt = eElement.getElementsByTagName("date").item(0).getTextContent();
											
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				try{
					date = formatter.parse(dt);
				}
				catch(ParseException e){
					System.out.println("Invalid data in file. ");
				}
			
				//get content
				String noteContent = eElement.getElementsByTagName("content").item(0).getTextContent();
			
				//create note
				Note nt = new Note();
				nt.setDate(date);
				nt.setNote(noteContent);
			
				//add note to list
				listNotes.add(nt);
			}
		}
		
		return listNotes;
	}

	@Override
	public File saveNoteBookIntoFile(NoteBook ntb, String fileName) {
		
		File fl = new File(fileName);
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("body");
			doc.appendChild(rootElement);
			
			// root elements

			for (Note nt : ntb.getListNotes()){
				Element noteElement = doc.createElement("note");
				doc.appendChild(noteElement);
				
				Element date = doc.createElement("date");
				date.appendChild(doc.createTextNode(nt.getDate().toString()));
				noteElement.appendChild(date);
				
				Element content = doc.createElement("content");
				content.appendChild(doc.createTextNode(nt.getNote()));
				noteElement.appendChild(content);

			}
			
			try{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(fl);

				transformer.transform(source, result);
				
			}
			catch (TransformerConfigurationException e) {
				System.err.println("Error occured while written data in XML");
			}
			catch (TransformerException e) {
				System.err.println("Error occured while written data in XML");
			}
		}
		catch(ParserConfigurationException e){
			System.err.println("Error occured while written data in XML");
		}
		
		return fl;
	}

}
