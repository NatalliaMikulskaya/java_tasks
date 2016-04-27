import java.text.SimpleDateFormat;
import java.util.Date;

import by.epam.atl.task2.bin.Note;

public class tester {

	public static void main(String[] args){
		
		Date dt = new Date("2014/5/1 14:15");
		String str = "new record";
		  Note nt = new Note();
		  
		// SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		  
		 System.out.println(nt.toString());
		 System.out.println(nt.getDate() );
		 System.out.println(dt);
		  
		 nt.setDate(dt);
		 nt.setNote(str);
		 System.out.println(nt.toString());
	}
}
