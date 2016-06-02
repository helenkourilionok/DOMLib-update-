package by.training.task3.dom.main;

import by.training.task3.dom.entity.DOMDocument;
import by.training.task3.dom.entity.DOMNode;
import by.training.task3.dom.service.DOMParser;

public class DOMLib {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xmlPath = "resources/menu.xml";
		
		DOMParser parser=new DOMParser();

        DOMDocument document = parser.parse(xmlPath);

        DOMNode node = document.getDocumentElement();
        
        System.out.println(node.toString());
	}

}
