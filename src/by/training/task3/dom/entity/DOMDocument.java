package by.training.task3.dom.entity;

import java.io.Serializable;

public class DOMDocument implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2868239008874238238L;
	private DOMNode root;

	public DOMDocument()
	{
		
	}
	public DOMDocument(DOMNode root)
	{
		this.root = root; 
	}
	public DOMNode getDocumentElement() {
		return root;
	}
	@Override
	public String toString() {
		return "DOMDocument [root=" + root + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		DOMDocument other = (DOMDocument) obj;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		return true;
	}
	
}
