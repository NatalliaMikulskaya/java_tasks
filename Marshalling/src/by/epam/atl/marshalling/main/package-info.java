@XmlSchema(
    namespace="http://www.example.org/lib",
    elementFormDefault = XmlNsForm.QUALIFIED,
    attributeFormDefault = javax.xml.bind.annotation.XmlNsForm.UNQUALIFIED,
    xmlns={
       @XmlNs(prefix="lib",         namespaceURI="http://www.example.org/lib"),
       @XmlNs(prefix="bookData",    namespaceURI="http://www.example.org/bookSchema"), 
       @XmlNs(prefix="authorData",  namespaceURI="http://www.example.org/personSchema"), 
        
   }
)
package by.epam.atl.marshalling.main;
import javax.xml.bind.annotation.*;