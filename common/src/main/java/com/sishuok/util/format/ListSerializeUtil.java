package com.sishuok.util.format;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ListSerializeUtil {
	
	private static final Logger log = Logger.getLogger(ListSerializeUtil.class);
	/**
	 * 列表序列化（用于Redis整存整取）
	 * @param value
	 * @return
	 */
	public static <T> byte[] serialize(List<T> value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv=null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			for(T obj : value){
				os.writeObject(obj);
			}
			os.writeObject(null);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		}
		return rv;
	}
	
	/**
     * 反序列化列表（用于Redis整存整取）
     * @param in
     * @return
     */
    public static <T> List<T> unserializeForList(byte[] in) {  
        List<T> list = new ArrayList<T>();  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                while (true) {  
                    T obj = (T) is.readObject();  
                    if(obj == null){  
                        break;  
                    }else{  
                        list.add(obj);  
                    }  
                }  
                is.close();  
                bis.close();  
            }  
        } catch (IOException e) {  
            log.error("Caught IOException decoding %d bytes of data", e);  
        } catch (ClassNotFoundException e) {  
            log.warn("Caught CNFE decoding %d bytes of data", e);  
        } 
        return list;  
    }
}
