package by.training.task3.dom.entity;

import java.io.Serializable;
import java.util.ArrayList;

import by.training.task3.dom.entity.DOMAttr;
import by.training.task3.dom.entity.DOMNode;

public class DOMNode implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -547217984669021934L;
	private DOMNode root;//родитель
    private ArrayList<DOMAttr> attr;//атрибуты:список пара имя="значение"
    private ArrayList<DOMNode> children;//дочерние элементы
    private String content;//текст между открывающим и закрывающим тегом
    private String name;//название тега

	public DOMNode(){
		content = "";
		name = "";
    }
    
	public DOMAttr getAttr(int index)
	{
		return  attr.get(index);
	}
	
	public void setAttr(DOMAttr domAttr)
	{
		attr.add(domAttr);
	}
	
	public ArrayList<DOMAttr> getListAttr() {
		return attr;
	}

	public void setAttr(ArrayList<DOMAttr> attr) {
		this.attr = attr;
	}

	public DOMNode getChild(int index)
	{
		return children.get(index);
	}
	
	public void setChild(DOMNode domNode)
	{
		children.add(domNode);
	}
	
	public ArrayList<DOMNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<DOMNode> children) {
		this.children = children;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public void setRoot(DOMNode node){
        root=node;
        attr = new ArrayList<DOMAttr>();
        children = new ArrayList<DOMNode>();
    }

    public DOMNode getRoot(){
        return root;
    }
    
    public void addChild(DOMNode node)
    {
    	children.add(node);
    }
    
    public void addAttr(DOMAttr attr)
    {
    	this.attr.add(attr);
    }
    
    public String toString(){
        String  str="-----------------\n----------------\n";
        str+="tag --"+name+"\n";
        if(content=="")
        {
        	content = "none";
        }
        str+="content--"+content+"\n";
        str+="ListAttr(count="+attr.size()+")"+"\n";
        for(DOMAttr attr1: attr){
            str+="AttrInfo -- "+attr1.getName()+"=" + "'" + attr1.getValue() + "'\n";
        }
        for(DOMNode child : children){
            str+=child.toString();
        }

        return str;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attr == null) ? 0 : attr.hashCode());
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DOMNode other = (DOMNode) obj;
		if (attr == null) {
			if (other.attr != null)
				return false;
		} else if (!attr.equals(other.attr))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		return true;
	}
    
}
