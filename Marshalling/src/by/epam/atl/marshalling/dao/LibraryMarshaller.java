package by.epam.atl.marshalling.dao;

import java.io.File;
import java.lang.invoke.MethodHandles;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.marshalling.bean.Library;

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
				lib = (Library) unmarsh.unmarshal(fl);
			}
			catch(JAXBException e){
				LOG.error("ERROR: can't unmarshall file");
			}
			
			
		}else {
			LOG.error("Can't read file "+fileName);
		}
		
		return lib;
		
	}

}
