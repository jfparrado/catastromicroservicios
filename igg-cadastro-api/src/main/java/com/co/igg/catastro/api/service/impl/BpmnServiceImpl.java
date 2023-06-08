package com.co.igg.catastro.api.service.impl;

import java.util.HashMap;
import java.util.Map;

//import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.co.igg.catastro.api.service.IBpmnExternalService;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class BpmnServiceImpl implements IBpmnExternalService {

	public void execute(String topic, JsonNode node) {
		
		/*ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();
		
		client.subscribe(topic).lockDuration(1000).handler((externalTask, externalTaskService) -> {
			ObjectMapper mapper = new ObjectMapper();
			
			Map<String, Object> result = mapper.convertValue(node, new TypeReference<Map<String, Object>>(){});
			
			//Map<String, Object> hm = new HashMap<String, Object>();
			externalTaskService.complete(externalTask, result);
			
		}).open();
		*/
	}
}
