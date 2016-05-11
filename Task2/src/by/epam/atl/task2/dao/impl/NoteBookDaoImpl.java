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
import by.epam.atl.task2.dao.exception.DAOException;

public class NoteBookDaoImpl implements NoteBookDao {
	
	@Override
	public NoteBook loadNoteBookFromFile(String fileName) throws DAOException {
		NoteBook ntb = new NoteBook();
		List<Note> notes = new ArrayList<Note>();
		
		if(fileName.isEmpty()){
			throw new DAOException("File "+fileName+" not spesified");
		}
		
		if (fileName.length() == 0){
			throw new DAOException("File "+fileName+" not spesified");
		}
		
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
				
				catch(ParserConfigurationException e){
					throw new DAOException("XML parser configuration error", e);

				}
				catch(SAXException e){
					throw new DAOException("XML parser error", e);

				}
				catch(IOException e){
					throw new DAOException("XML file reading error", e);
					
				}
			}else{
				throw new DAOException("File "+fileName+" not found");
				
			}
		}
		
		return ntb;
	}
	
	private List<Note> processNodeList(NodeList nList) throws DAOException{
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
					throw new DAOException("Invalid data in file.", e);
					
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
	public File saveNoteBookIntoFile(NoteBook ntb, String fileName) throws DAOException {
		
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
				rootElement.appendChild(noteElement);
				
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
				throw new DAOException("Error occured while written data in XML: transformer configuration error", e);
			}
			catch (TransformerException e) {
				throw new DAOException("Error occured while written data in XML: transformer error", e);
			}
		}
		catch(ParserConfigurationException e){
			throw new DAOException("Error occured while written data in XML: XML configuration error", e);
		}
		
		return fl;
	}

}
