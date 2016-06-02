package by.training.task3.dom.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import by.training.task3.dom.entity.DOMAttr;
import by.training.task3.dom.entity.DOMNode;
import by.training.task3.dom.entity.DOMDocument;;

public class DOMParser implements IParser {
	private String xmlContent = "";
    private String lastOpenTagName;
    private int lastOpenTagEndPos;
    private DOMNode root;
	private DOMNode current;

    public DOMParser() {
		// TODO Auto-generated constructor stub
	}

    public DOMDocument parse(String xmlFileName) {
    	try
    	{
	    	//удаляем комментарии
	        xmlContent = PatternPasing.deleteComment(FileReader.readXML(xmlFileName));
	        Matcher match = PatternPasing.findTag.matcher(xmlContent);
	        Matcher tmpMatch;
	        String tag;//<tag>,<tag/>,</tag>
	        String tagName;
	        String textContent;
	        boolean noTextContent;
	
	        while(match.find()){
	
	            tag=match.group(1);//ищем теги - <tag>
	            tmpMatch=PatternPasing.findStartTag.matcher(tag);
	            //проверяем является ли тег окрывающим
	            if(tmpMatch.matches()) {
	                if(tmpMatch.group(2)!=null){//<tag/>
	                	noTextContent = true;
	                }else{//<tag>
	                	noTextContent = false;
	                }
	
	                tagName="";
	                tmpMatch= PatternPasing.findTagName.matcher(tag);
	                if(tmpMatch.find()){
	                    tagName=tmpMatch.group(1);//<tag> --> tagName = tag
	                }
	                lastOpenTagName=tagName;
	                lastOpenTagEndPos=match.end();
	
	                ArrayList<DOMAttr> attributes=parseAttributes(tag);
	                startElement(tagName, attributes,noTextContent);
	              //если тег закрывающий, то
	            }else {
	                tmpMatch = PatternPasing.findEndTag.matcher(tag);
	                if(tmpMatch.matches()) {
	
	                    textContent="";
	                    if(lastOpenTagName.equals(tmpMatch.group(1))){
	                        textContent=xmlContent.substring(lastOpenTagEndPos, match.start());
	                    }
	                   
	                    lastOpenTagName="";
	                    endElement(tmpMatch.group(1), textContent.trim());
	                }
	            }
	        }
    	}
    	catch(FileNotFoundException ex)
    	{
    		System.err.println("File not found!");
    	}
    	catch(IOException ex)
    	{
    		System.err.println("IOException");
    	}
        return new DOMDocument(root);
    }

    public void startElement(String tagName, ArrayList<DOMAttr> attributes, boolean noTextContent){
        DOMNode node=new DOMNode();
        node.setRoot(current);
        if(root==null){
            root=node;
        }
        if(!attributes.isEmpty()) {
            for (DOMAttr atr : attributes) {
                node.addAttr(atr);
            }
        }
        node.setName(tagName);
        current=node;
        if(noTextContent){
            endElement(tagName, "");
        }
    }

    public void endElement(String tagName, String textContents){
        if(textContents!=""){
            current.setContent(textContents);
        }

        DOMNode p=current.getRoot();
        if(p!=null) {//если null, то корневой элемент
            p.addChild(current);
            current = p;//поскольку найден закрытый тег 
            //устанавливаем текущий на родительский
        }
    }
    
    private ArrayList<DOMAttr> parseAttributes(String tag){
        ArrayList<DOMAttr> list = new ArrayList<DOMAttr>();
        Matcher tmpMatch=PatternPasing.findAttr.matcher(tag);
        while(tmpMatch.find()){
        					
            DOMAttr attribute=new DOMAttr(tmpMatch.group(1), tmpMatch.group(2));
            list.add(attribute);
        }
        return list;
    }

}
