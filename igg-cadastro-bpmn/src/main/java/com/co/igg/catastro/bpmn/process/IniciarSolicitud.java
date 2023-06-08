package com.co.igg.catastro.bpmn.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class IniciarSolicitud  implements JavaDelegate{
	@Override
	public void execute(DelegateExecution ctx) throws Exception {
		Integer content = (Integer) ctx.getVariable("idSolicitud");
		System.out.print(content);
	}
}
