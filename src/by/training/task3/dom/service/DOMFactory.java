package by.training.task3.dom.service;

public class DOMFactory {
	 private static DOMParser domParser;
	 public static IParser getInstance()
	 {
		 if(domParser==null)
		 {
			 domParser = new DOMParser();
		 }
		 return domParser;
	 } 
}
