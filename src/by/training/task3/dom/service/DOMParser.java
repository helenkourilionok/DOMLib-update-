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
	    	//������� �����������
	        xmlContent = PatternPasing.deleteComment(FileReader.readXML(xmlFileName));
	        Matcher match = PatternPasing.findTag.matcher(xmlContent);
	        Matcher tmpMatch;
	        String tag;//<tag>,<tag/>,</tag>
	        while(match.find()){
	
	            tag=match.group(1);//���� ���� - <tag>
	            
	            tmpMatch=PatternPasing.findStartTag.matcher(tag);
	            
	            if(tmpMatch.matches()) {
	            	
	              startTag(tmpMatch,match,tag);
	              
	            }else {
	            	
	             tmpMatch = PatternPasing.findEndTag.matcher(tag);
	               
	             endTag(tmpMatch,match);

	            }
	        }
    	}
    	catch(FileNotFoundException ex)
    	{
    		ex.printStackTrace();
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
        return new DOMDocument(root);
    }

    private void endTag(Matcher matcher,Matcher matcherAll) 
    {
    	if(matcher.matches()) {
    		
	      	  String textContent="";
	      	  
	          if(lastOpenTagName.equals(matcher.group(1))){
	        	  
	              textContent=xmlContent.substring(lastOpenTagEndPos, matcherAll.start());
	          }
	          
	          lastOpenTagName="";
	          endElement(matcher.group(1), textContent.trim());
          }
    }
    
    private void startTag(Matcher matcher,Matcher matcherAll,String tag)
    {
    	  boolean noTextContent = isTextContent(matcher);
    		
          String tagName="";
          matcher = PatternPasing.findTagName.matcher(tag);
          if(matcher.find()){
              tagName=matcher.group(1);//<tag> --> tagName = tag
          }
          lastOpenTagName=tagName;
          lastOpenTagEndPos=matcherAll.end();

          ArrayList<DOMAttr> attributes=parseAttributes(tag);
          startElement(tagName, attributes,noTextContent);
    }
    
    private boolean isTextContent(Matcher matcher)
    {
    	boolean result = false;
    	if(matcher.group(2)!=null){//<tag/>
    		result = true;
        }else{//<tag>
        	result = false;
        }
    	return result;
    }
    
    private void startElement(String tagName, ArrayList<DOMAttr> attributes, boolean noTextContent){
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

    private void endElement(String tagName, String textContents){
        if(textContents!=""){
            current.setContent(textContents);
        }

        DOMNode p=current.getRoot();
        if(p!=null) {//���� null, �� �������� �������
            p.addChild(current);
            current = p;//��������� ������ �������� ��� 
            //������������� ������� �� ������������
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
