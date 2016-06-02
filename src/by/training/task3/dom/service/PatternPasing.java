package by.training.task3.dom.service;
import java.util.regex.*;

public class PatternPasing {
	//? проверка на строку декларации,ищет стартовый и конечный теги
	//group2 - tagcontent,group1 - alltag
	public static final Pattern findTag = Pattern.compile("(\\<[^\\?](/?[^\\>]+)\\>)");

	public static final Pattern findTagName = Pattern.compile("\\<([\\w|-]+)");
	//<tag> или <tag/>
	public static final Pattern findStartTag = Pattern.compile("\\<([^/]+)([ /])?\\>");

	public static final Pattern findEndTag = Pattern.compile("\\</([^>]+)\\>");
	//xml позволяет заключать атрибуты в " или ' кавычки;group1 - 
	public static final Pattern findAttr = Pattern.compile("(\\w+)=[\"|']{1}([^'\"]*)[\"|']{1}");
	
    public  static String deleteComment(String content){

        Pattern pattern = Pattern.compile("(\\<!--.*?--\\>)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher m = pattern.matcher(content);
        while(m.find()) {
            content=content.replace(m.group(0), "");//заменяем комментарии
        }
        return content;
    }
}
