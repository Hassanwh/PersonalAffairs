package code.person.service.menu.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MenuManager{
	public Menu getMenuFromXml(String filePath){
		Menu menu = new Menu();
		File file = new File(filePath);
		if(file.exists()){
			System.out.println("´æÔÚ");
		}else{
			System.out.println("no");
		}
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			Element node = document.getRootElement();
			Element one = node.element("one");
			Element two = node.element("two");
			Element three = node.element("three");

			
			Iterator<Element> oneIt = one.elementIterator();


			Vector oneVector = getChildBottonByNode(one);
			ChildButton[] oneArray = new ChildButton[oneVector.size()];
			for(int i=0;i<oneVector.size();i++){
				oneArray[i]=(ChildButton)oneVector.get(i);
			}
			Vector twoVector = getChildBottonByNode(two);
			ChildButton[] twoArray = new ChildButton[twoVector.size()];
			for(int i=0;i<twoVector.size();i++){
				twoArray[i]=(ChildButton)twoVector.get(i);
			}
			Vector threeVector = getChildBottonByNode(three);
			ChildButton[] threeArray = new ChildButton[threeVector.size()];
			for(int i=0;i<threeVector.size();i++){
				threeArray[i]=(ChildButton)threeVector.get(i);
			}
			
			
			ParentButton parentButton1 = new ParentButton();
			parentButton1.setName(one.attributeValue("name"));
			parentButton1.setSub_button(oneArray);
			ParentButton parentButton2 = new ParentButton();
			parentButton2.setName(two.attributeValue("name"));
			parentButton2.setSub_button(twoArray);
			ParentButton parentButton3 = new ParentButton();
			parentButton3.setName(three.attributeValue("name"));
			parentButton3.setSub_button(threeArray);
			
			ParentButton[] parentButton = new ParentButton[]{parentButton1,parentButton2,parentButton3};
			
			menu.setButton(parentButton);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menu;
	}
	
	public Vector getChildBottonByNode(Element node){
		Vector vector = new Vector();
		Iterator<Element> it = node.elementIterator();
		while(it.hasNext()){
			
			Element e = it.next();
			String type="";
			String name="";
			String key="";
			String url="";
			List<Attribute> list = e.attributes();				
			for(Attribute attr:list){
				String nodeName = attr.getName();
				String value = attr.getValue();
				if(nodeName.equals("type")){
					type=value;
				}else if(nodeName.equals("name")){
					name=value;
				}else if(nodeName.equals("key")){
					key=value;
				}else{
					
				}
			}
			if(type.equals("click")){
				ClickButton clickButton = new ClickButton();
				clickButton.setKey(key);
				clickButton.setName(name);
				clickButton.setType(type);
				vector.add(clickButton);
			}else if(type.equals("view")){
				UrlButton urlButton = new UrlButton();
				urlButton.setName(name);
				urlButton.setType(type);
				url = e.getText();
				urlButton.setUrl(url);
				vector.add(urlButton);
			}else{
				OtherButton otherButton = new OtherButton();
				otherButton.setKey(key);
				otherButton.setName(name);
				otherButton.setType(type);
				vector.add(otherButton);
			}
		}
		return vector;
	}
}