package com.co.igg.catastro.api.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface IBpmnExternalService {
	public void execute(String topic, JsonNode node) ;
}
