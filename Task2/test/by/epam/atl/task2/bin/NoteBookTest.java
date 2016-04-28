package by.epam.atl.task2.bin;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;

import org.testng.annotations.BeforeClass;

import java.util.Date;

public class NoteBookTest {
	private NoteBook ntb;
  
  @BeforeClass
  public void beforeClass() {
	  ntb = new NoteBook();
  }
  
  @Test
  public void testNoteBookSettersGetters() {
	 	  
	  Date dt = new Date("2015.3.1 14:15");
	 // SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
	  
	  String str = "new record";
	  Note nt = new Note(dt,str); 
	  
	  dt = new Date("2016.3.1 14:15");
	  str = "new record 2";
	  Note nt2 = new Note(dt,str);
	  
	  Assert.assertEquals(nt.getDate(),dt,"Expected date "+dt+" but founded "+nt.getDate());
	  Assert.assertEquals(nt.getNote(), str,"Expected string "+str+" but founded "+nt.getNote());
	  
  }
  
 /* @Test
  public void testNoteConstructor() {
		
		  
	  GregorianCalendar dt = new GregorianCalendar(2015, 3, 1, 14, 15);
	  SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		  
	  String str = "new record";
	  nt = new Note(dt, str);  
			  
	  Assert.assertEquals(nt.getDate(),dt,"Expected date "+format1.format(dt.getTime())+" but founded "+format1.format(nt.getDate().getTime()));
	  Assert.assertEquals(nt.getNote(), str,"Expected string "+str+" but founded "+nt.getNote());
		  
	  }*/
  
  
  @AfterClass
  public void afterClass() {
	  
  }

}
