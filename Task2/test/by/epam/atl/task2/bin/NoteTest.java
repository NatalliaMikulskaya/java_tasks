package by.epam.atl.task2.bin;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import by.epam.atl.task2.bean.Note;

import org.testng.annotations.BeforeClass;

import java.util.Date;

public class NoteTest {
	private Note nt;
  
  @BeforeClass
  public void beforeClass() {
	  nt = new Note();
  }
  
  @Test
  public void testNoteSettersGetters() {
	 // Note nt = new Note();
	  
	  Date dt = new Date("2015/3/1 14:15");
	 // SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
	  
	  String str = "new record";
	  
	  nt.setDate(dt);
	  nt.setNote(str);
	  
	  Assert.assertEquals(nt.getDate(),dt,"Expected date "+dt+" but founded "+nt.getDate());
	  Assert.assertEquals(nt.getNote(), str,"Expected string "+str+" but founded "+nt.getNote());
	  
  }
  
  @Test
  public void testNoteConstructor() {
		
		  
	  Date dt = new Date("2015/3/1 14:15");
	  //SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		  
	  String str = "new record";
	  nt = new Note(dt, str);  
			  
	  Assert.assertEquals(nt.getDate(),dt,"Expected date "+dt+" but founded "+nt.getDate());
	  Assert.assertEquals(nt.getNote(), str,"Expected string "+str+" but founded "+nt.getNote());
		  
	  }
  
  
  @AfterClass
  public void afterClass() {
	  
  }

}
