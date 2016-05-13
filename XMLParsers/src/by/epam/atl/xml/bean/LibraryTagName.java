package by.epam.atl.xml.bean;

public enum LibraryTagName {
	BOOK, BOOKNAME, AUTHOR, FIRSTNAME, SECONDNAME, PARENTNAME, GENRE, PUBLICATIONDATE, SERIESNAME, LIBRARY;
	
	public static LibraryTagName getElementTagName(String el){
		switch (el){
		case "bookName":{
			return BOOKNAME;
		}
		
		case "author" :{
			return AUTHOR;
		}
	
		case "firstName" :{
			return FIRSTNAME;
		}
		
		case "secondName" :{
			return SECONDNAME;
		}
		
		case "parentName" :{
			return PARENTNAME;
		}
		
		case "genre" :{
			return GENRE;
		}
		
		case "publicationDate" :{
			return PUBLICATIONDATE;
		}
		
		case "seriesName" :{
			return SERIESNAME;
		}
		
		case "book" :{
			return BOOK;
		}
		default: return LIBRARY;
		}
	}
}
