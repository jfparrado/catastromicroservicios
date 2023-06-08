package com.co.igg.catastro.api.rest;

import java.util.Map;

import feign.Body;
import feign.HeaderMap;
import feign.Param;
import feign.RequestLine;

//@FeignClient(name = "engine-rest", url = "localhost:8080")
public interface BpmnClienteRest {
		
// Inicia un proceso mediante una solicitud POST al endpoint /engine-rest/process-definition/key/{aProcessDefinitionKey}/start
@RequestLine("POST /engine-rest/process-definition/key/{aProcessDefinitionKey}/start")
@Body("{body}")
String startProcess(@HeaderMap Map<String, Object> headers, @Param("body") String body, @Param("aProcessDefinitionKey") String  aProcessDefinitionKey);

// Obtiene las instancias de actividad de un proceso mediante una solicitud GET al endpoint /engine-rest/process-instance/{aProcessDefinitionId}/activity-instances
@RequestLine("GET /engine-rest/process-instance/{aProcessDefinitionId}/activity-instances")
String activityInstances(@Param("aProcessDefinitionId") String  aProcessDefinitionId);

// Obtiene una tarea mediante una solicitud GET al endpoint /engine-rest/task?executionId={aProcessDefinitionId}
@RequestLine("GET /engine-rest/task?executionId={aProcessDefinitionId}")
String getTask(@Param("aProcessDefinitionId") String  aProcessDefinitionId);

// Completa una tarea mediante una solicitud POST al endpoint /engine-rest/task/{aTaskId}/complete
@RequestLine("POST /engine-rest/task/{aTaskId}/complete")
@Body("{body}")
String taskComplete(@HeaderMap Map<String, Object> headers, @Param("body") String body, @Param("aTaskId") String  aTaskId);
	
}
