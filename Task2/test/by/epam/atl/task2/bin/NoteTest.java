package by.epam.atl.task2.bin;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NoteTest {
	private Note nt;
  
  @BeforeClass
  public void beforeClass() {
	  nt = new Note();
  }
  
  @Test
  public void testNoteSettersGetters() {
	 // Note nt = new Note();
	  
	  GregorianCalendar dt = new GregorianCalendar(2015, 3, 1, 14, 15);
	  SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
	  
	  String str = "new record";
	  
	  nt.setDate(dt);
	  nt.setNote(str);
	  
	  Assert.assertEquals(nt.getDate(),dt,"Expected date "+format1.format(dt.getTime())+" but founded "+format1.format(nt.getDate().getTime()));
	  Assert.assertEquals(nt.getNote(), str,"Expected string "+str+" but founded "+nt.getNote());
	  
  }
  
  @Test
  public void testNoteConstructor() {
		
		  
	  GregorianCalendar dt = new GregorianCalendar(2015, 3, 1, 14, 15);
	  SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		  
	  String str = "new record";
	  nt = new Note(dt, str);  
			  
	  Assert.assertEquals(nt.getDate(),dt,"Expected date "+format1.format(dt.getTime())+" but founded "+format1.format(nt.getDate().getTime()));
	  Assert.assertEquals(nt.getNote(), str,"Expected string "+str+" but founded "+nt.getNote());
		  
	  }
  
  
  @AfterClass
  public void afterClass() {
	  
  }

}
