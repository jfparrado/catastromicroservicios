package com.co.igg.catastro.api.utils;


	import java.io.IOException;
	import java.text.SimpleDateFormat;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;

	import com.fasterxml.jackson.core.JsonGenerator;
	import com.fasterxml.jackson.core.JsonProcessingException;
	import com.fasterxml.jackson.core.type.TypeReference;
	import com.fasterxml.jackson.databind.DeserializationFeature;
	import com.fasterxml.jackson.databind.JsonSerializer;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.fasterxml.jackson.databind.SerializerProvider;
	import com.fasterxml.jackson.databind.module.SimpleModule;
	import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

	/**
	import org.codehaus.jackson.map.DeserializationConfig.Feature;
	import org.codehaus.jackson.map.ObjectMapper;
	import org.codehaus.jackson.map.ser.StdSerializerProvider;
	import org.codehaus.jackson.type.TypeReference;
	**/
	/**
	 * Jackson扩展封装
	 *
	 */
	public class JsonUtils {
		private static final Logger logger = LogManager.getLogger(JsonUtils.class);
		
		final static ObjectMapper objectMapper;
		
		static {
			objectMapper = new ObjectMapper();

			//objectMapper.getSerializerProvider().setNullValueSerializer(new NullSerializer());
			
	        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        
	     
	        SimpleModule simpleModule = new SimpleModule();
	        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
	        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
	        objectMapper.registerModule(simpleModule);

	    }
	 
	    public static ObjectMapper getObjectMapper() {
	        return objectMapper;
	    }
	   
	    private static class NullSerializer extends JsonSerializer<Object> {   
	        public void serialize(Object value, JsonGenerator jgen,   
	                SerializerProvider provider) throws IOException,   
	                JsonProcessingException {   
	            jgen.writeString("");   
	        }   
	    } 
	    /**
	     * @param <T>
	     * @param jsonString JSON
	    * @param tr TypeReference,: new TypeReference< List<FamousUser> >(){}
	     * @return List
	     */
	    public static <T> T toGenericObject(String jsonString, TypeReference<T> tr) {
	 
	        if (jsonString == null || "".equals(jsonString)) {
	            return null;
	        } else {
	            try {
	                return objectMapper.readValue(jsonString, tr);
	            } catch (Exception e) {
		            //	e.printStackTrace();
		            if (logger.isErrorEnabled()) {
		            	logger.error("JSON",e);
		   		    }
	            
	            }
	        }
	        return null;
	    }
	 
	    /**
	     * Java Json 
	     *  
	     * @param object Java ，List,Map
	     * @return json 
	     */
	    public static String toJSONString(Object object) {
	        String jsonString = "";
	        try {
	            
	             jsonString = objectMapper.writeValueAsString(object);
	            
	        } catch (Exception e) {
	        	//e.printStackTrace();
	        	if (logger.isErrorEnabled()) {
	            	logger.error("Java",e);
	   		    }
	        }
	        return jsonString;
	 
	    }
	 
	    /**
	     * Json 
	     * @param jsonString
	     * @param c
	     * @return
	     */
		public static <T> T toObject(String jsonString, Class<?> c) {
	    	
	        if (jsonString == null || "".equals(jsonString)) {
	        	
	            return (T) "";
	        } else {
	            try {
	            	return (T)objectMapper.readValue(jsonString, c);	
	            } catch (Exception e) {
	            	if (logger.isErrorEnabled()) {
	                	logger.error("Json ",e);
	       		    }
	            }
	 
	        }
	        return (T) "";
	    }
	 
		
	}