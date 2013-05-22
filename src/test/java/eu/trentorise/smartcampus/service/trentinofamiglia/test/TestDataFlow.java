package eu.trentorise.smartcampus.service.trentinofamiglia.test;

import it.sayservice.platform.core.bus.common.AppConfig;
import it.sayservice.platform.servicebus.test.DataFlowTestHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

import com.google.protobuf.Message;

import eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.EventoFamiglia;
import eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.OrganizzazioneFamiglia;
import eu.trentorise.smartcampus.service.trentinofamiglia.impl.GetEventiDataFlow;
import eu.trentorise.smartcampus.service.trentinofamiglia.impl.GetOrganizzazioniDataFlow;

public class TestDataFlow extends TestCase {
	
	public void testRun() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-context.xml");
		AppConfig ac = (AppConfig)context.getBean("appConfig");		
		
		DataFlowTestHelper helper = new DataFlowTestHelper();
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map<String, Object> out1 = helper.executeDataFlow("smartcampus.service.trentinofamiglia", "GetEventi", new GetEventiDataFlow(), parameters);
		List<Message> data1 = (List<Message>)out1.get("data");
//		for (Message msg: data1) {
//			System.err.println(((EventoFamiglia)msg));
//			System.err.println("----------------------------------");
//		}
		
		Map<String, Object> out2 = helper.executeDataFlow("smartcampus.service.trentinofamiglia", "GetOrganizzazioni", new GetOrganizzazioniDataFlow(), parameters);
		List<Message> data2 = (List<Message>)out2.get("data");
//		for (Message msg: data2) {
//			System.err.println(((OrganizzazioneFamiglia)msg).getName());
//			System.err.println(((OrganizzazioneFamiglia)msg).getStatus());
//			System.err.println(((OrganizzazioneFamiglia)msg).getLink());
//			System.err.println("----------------------------------");
//		}
		
		System.err.println(data1.size());
		System.err.println(data2.size());
	}
}
