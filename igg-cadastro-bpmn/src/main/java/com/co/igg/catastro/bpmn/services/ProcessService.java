package com.co.igg.catastro.bpmn.services;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

  @Autowired
  private RuntimeService runtimeService;

  public Long createProcessAndSetId() {
    // Inicia un nuevo proceso en Camunda
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("TwoUserTwoService");

    // Obtiene el ID del proceso generado por Camunda
    String camundaProcessId = processInstance.getId();

    // Convierte el ID del proceso de String a Long
    Long processId = Long.parseLong(camundaProcessId);

      // Pasa el ID del proceso a tu función o clase correspondiente para asignarlo al proceso en tu aplicación
    return processId;
  }
}
