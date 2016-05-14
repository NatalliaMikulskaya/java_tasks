package by.epam.atl.marshalling.dao;

import java.io.File;
import java.lang.invoke.MethodHandles;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.marshalling.bean.Book2;
import by.epam.atl.marshalling.bean.Library;
import by.epam.atl.marshalling.bean.Person;
import by.epam.atl.marshalling.bean.Person2;

public class LibraryMarshaller {
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public LibraryMarshaller(){
		
	}
	
	public Library parseXML(String fileName){
		Library lib = null;
		
		File fl = new File(fileName);
		
		if (fl.exists() && fl.isFile() && fl.canRead()){
			
			try{
				
				JAXBContext context = JAXBContext.newInstance(Library.class);
				
				LOG.info("Start unmurshalling file "+fileName);
				
				Unmarshaller unmarsh = context.createUnmarshaller();
				System.out.println("1");
				
				lib = (Library) unmarsh.unmarshal(fl);
				System.out.println("2");
				
				System.out.println(lib.toString());
			}
			catch(JAXBException e){
				LOG.error("ERROR: can't unmarshall file");
			}
			
			
		}else {
			LOG.error("Can't read file "+fileName);
		}
		
		return lib;
		
	}
	
	public void makeXML(String fileName, Library lib){
		
		if (fileName == null) {
			LOG.error("ERROR: empty file name!");
			return;
		}
		
		
		try {

			LOG.info("Marshaller starts.");
			File fl = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(lib, fl);
			jaxbMarshaller.marshal(lib, System.out);
			LOG.info("Marshaller ends.");

		} catch (JAXBException e) {
			LOG.error("ERROR: can't murshall data to xml.");
		}
		
	}
	
	public void makeXMLs(String fileName, Person2 p){
		
		if (fileName == null) {
			LOG.error("ERROR: empty file name!");
			return;
		}
		
		
		try {

			LOG.info("Marshaller starts.");
			File fl = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Person2.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(p, fl);
			jaxbMarshaller.marshal(p, System.out);
			LOG.info("Marshaller ends.");

		} catch (JAXBException e) {
			LOG.error("ERROR: can't murshall data to xml.");
		}
		
	}

	public void makeXMLm(String fileName, Book2 b){
		
		if (fileName == null) {
				LOG.error("ERROR: empty file name!");
				return;
			}
			
			
			try {

				LOG.info("Marshaller starts.");
				File fl = new File(fileName);
				JAXBContext jaxbContext = JAXBContext.newInstance(Book2.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(b, fl);
				jaxbMarshaller.marshal(b, System.out);
				LOG.info("Marshaller ends.");

			} catch (JAXBException e) {
				LOG.error("ERROR: can't murshall data to xml.");
			}
	}
	
}
