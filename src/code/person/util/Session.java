package code.person.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//session½Ó¿Ú
public interface Session{
		
	public Object get(Object key);
	
	public Object put(Object key,Object value);
	
	public void putAll(Map m);	
	
	public void remove(Object key);
	
	public void clear();
	
}