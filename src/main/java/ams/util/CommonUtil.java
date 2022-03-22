package ams.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ams.response.StudentAttendanceResponse;

public class CommonUtil {
	
	public static String getJsonAsString(Object object) {
		String jsonString = null;
		try {
			jsonString = new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
		}
		return jsonString;
	}
	
	public static List<Long> getResponseFromString(Object object) {
		List<Long> resp = new ArrayList<>();
		try {
			resp = new ObjectMapper().readValue(object.toString(), new TypeReference<List<Long>>(){});
		} catch (JsonMappingException e) {
		} catch (JsonProcessingException e) {
		}
		return resp;
	}
}
