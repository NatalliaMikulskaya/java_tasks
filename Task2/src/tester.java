import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import by.epam.atl.task2.bin.Note;

public class tester {

	public static void main(String[] args){
		
		GregorianCalendar dt = new GregorianCalendar(2014, 5, 1, 14, 15);
		String str = "new record";
		  Note nt = new Note();
		  
		 SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		  
		 System.out.println(nt.toString());
		 System.out.println(format1.format(nt.getDate().getTime()) );
		 System.out.println(format1.format(dt.getTime()));
		  
		 nt.setDate(dt);
		 nt.setNote(str);
		 System.out.println(nt.toString());
	}
}
