package net.ibtech.hibernate.xbag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class XBag {
    private Map<XBagKey, Object> items = new HashMap<XBagKey, Object>();

	
	public Map<XBagKey, Object> getMap(){
		return this.items;
	}
	
	public void setMap(Map<XBagKey, Object> map){
		this.items = items;
	}
	
	public Object getValue(XBagKey key) {
		return this.items.get(key);
	}
	
	public void add(XBagKey key, Object value) {
		this.items.put(key, value);
	
}
}