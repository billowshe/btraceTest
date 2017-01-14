package test.btracetest;


import java.lang.reflect.Field;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace(trusted=true)
public class BTraceHttp {
	
	private static Field nameField = field(classForName("io.netty.handler.codec.http.HttpObjectDecoder", contextClassLoader()), "name");
	private static Field valueField = field(classForName("io.netty.handler.codec.http.HttpObjectDecoder", contextClassLoader()), "value");
	private static Field messageField = field(classForName("io.netty.handler.codec.http.HttpObjectDecoder", contextClassLoader()), "message");
	
    @OnMethod(clazz="io.netty.handler.codec.http.HttpObjectDecoder",location= @Location(value = Kind.LINE, line = 424)) //424
    public static void onThreadStart(@Self Object self) {
    	Object message = get(messageField, self);
    	if(message != null){
    		println(message.toString());
    	//	printFields(message.toString());println();
    	}else{
    		println("message null");
    	}
    	
    	Object name = get(nameField, self);
    	if(name != null){
    		println(name.toString());
    	}else{
    		println("name null");
    	}
    	
    	Object value = get(valueField, self);
    	if(value != null){
    		println(value.toString());
    	}else{
    		print("value null");
    	}
    	
    	
    	
    }
}