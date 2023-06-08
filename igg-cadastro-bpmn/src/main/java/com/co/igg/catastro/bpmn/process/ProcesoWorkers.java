package com.co.igg.catastro.bpmn.process;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcesoWorkers {

  private final static Logger LOG = LoggerFactory.getLogger(JobWorker.class);

  @JobWorker(type = "service1")
  public void modifyMessage(final ActivatedJob job) {
    final String messageContent = (String) job.getVariablesAsMap().get("message_content");
    final String option = (String) job.getVariablesAsMap().get("option");
    final String idProceso = String.valueOf(job.getProcessInstanceKey());
    final String modifiedMessage;

    if ("opcion1".equals(option)) {
      modifiedMessage = messageContent + " modificado por service11";
    } else if ("opcion2".equals(option)) {
      modifiedMessage = messageContent + " modificado por service12";
    } else {
      modifiedMessage = messageContent;
    }

    LOG.info("Message content: {}", modifiedMessage);
    LOG.info("Process Instance Key: {}", idProceso);

    // Realiza las acciones adicionales necesarias con el mensaje modificado y el Process Instance Key
  }

  @JobWorker(type = "service2")
  public void modifyMessage2(final ActivatedJob job) {
    final String messageContent = (String) job.getVariablesAsMap().get("message_content");
    final String option = (String) job.getVariablesAsMap().get("option");
    final String idProceso = String.valueOf(job.getProcessInstanceKey());
    final String modifiedMessage;

    if ("opcion1".equals(option)) {
      modifiedMessage = messageContent + " modificado por service21";
    } else if ("opcion2".equals(option)) {
      modifiedMessage = messageContent + " modificado por service22";
    } else {
      modifiedMessage = messageContent + " modificado por service23";;
    }

    LOG.info("Message content: {}", modifiedMessage);
    LOG.info("Process Instance Key: {}", idProceso);

    // Realiza las acciones adicionales necesarias con el mensaje modificado y el Process Instance Key
  }
}
