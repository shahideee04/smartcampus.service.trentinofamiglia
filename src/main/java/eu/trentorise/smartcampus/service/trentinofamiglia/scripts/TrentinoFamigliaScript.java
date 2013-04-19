package eu.trentorise.smartcampus.service.trentinofamiglia.scripts;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.google.protobuf.Message;

import eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia;
import eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.EventoFamiglia;
import eu.trentorise.smartcampus.service.trentinofamiglia.jaxb.Dataroot;
import eu.trentorise.smartcampus.service.trentinofamiglia.jaxb.Dataroot.TABOrg;
import eu.trentorise.smartcampus.service.trentinofamiglia.jaxb.Dataroot.TABOrg.TABAttività;
import eu.trentorise.smartcampus.service.trentinofamiglia.jaxb.Dataroot.TABOrg.TABAttività.TAbTerritorio;

public class TrentinoFamigliaScript {

	public List<Message> parse() throws Exception {
		try {
		List<Message> result = new ArrayList<Message>();

		JAXBContext jc = JAXBContext.newInstance("eu.trentorise.smartcampus.service.trentinofamiglia.jaxb");
		Unmarshaller u = jc.createUnmarshaller();

		InputStream rs = Thread.currentThread().getContextClassLoader().getResourceAsStream("service/trentinofamiglia/estate_giovani_e_famiglia_2013.xml");
		
		StreamSource ss = new StreamSource(rs);
		Dataroot dataroot = (Dataroot)u.unmarshal(ss);
		
		for (TABOrg org : dataroot.getTABOrg()) {
			for (TABAttività ta : org.getTABAttività()) {
				Map <String, EventoFamiglia> map = new TreeMap<String, Trentinofamiglia.EventoFamiglia>();
				for (TAbTerritorio tt : ta.getTAbTerritorio()) {
					if (!map.containsKey(tt.getComuni())) {
					EventoFamiglia.Builder builder = EventoFamiglia.newBuilder();
					builder.setId(ta.getNomeX0020Attività() + "@" + org.getNomeOrganizzazione());
					builder.setTitle(ta.getNomeX0020Attività());
					builder.setDescription(buildDescription(ta));
					builder.setFrom(ta.getDataX0020InizioX0020PeriodoX0020Attività());
					builder.setTo(ta.getDataX0020FineX0020PeriodoX0020Attività());
					builder.setDays(ta.getLX0027AttivitàX0020SiX0020SvolgeX0020NeiX0020SeguentiX0020Giorni());
					builder.setOrganization(org.getNomeOrganizzazione());
					builder.setPlace(tt.getComuni());
					EventoFamiglia event = builder.build();
					
					map.put(tt.getComuni(), event);
					}
				}
				result.addAll(map.values());
			}
		}

		return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private String buildDescription(TABAttività ta) {
		StringBuilder sb = new StringBuilder();
		sb.append("<p>" + ta.getDescrizioneX0020SinteticaX0020DellX0027Attività() + "</p>");
		sb.append("<p>Orari/Periodicità/Turni: " + checkLen(ta.getOrariX0020X002FX0020PeriodicitàX002FX0020TurniX0020DiX0020Svolgimento()) + "</p>");
		sb.append("<p>Sede: " + checkLen(ta.getSedeX002FIX0020PressoX0020CuiX0020SiX0020SvolgeràX0020LX0027Attività())+ "</p>");
		sb.append("<p>Attività diurna/residenziale: " + checkLen(ta.getTipoX0020DiX0020AttivitàX0020X0028DiurnaX002FResidenzialeX0029()) + "</p>");
		sb.append("<p>Costo: " + checkLen(ta.getTipoX0020DiX0020Costo())+ "</p>");
		sb.append("<p>Specifiche sul costo: " + checkLen(ta.getSpecificheX0020SulX0020Costo()) + "</p>");
		sb.append("<p>Fascia età: " + checkLen(ta.getFasciaX002FEX0020DiX0020EtàX0020Destinatari()) + "</p>");
		sb.append("<p>Vincoli residenza: " + checkLen(ta.getVincoliX0020DiX0020ResidenzaX0020DeiX0020Destinatari()) + "</p>");
		sb.append("<p>Referenti: " + checkLen(ta.getReferentiX0020CheX0020PossonoX0020FornireX0020InformazioniX0020SuX0020QuestaX0020Attività()) + "</p>");
		sb.append("<p>Modalità e tempi di iscrizione: " + checkLen(ta.getModalitàX0020EX0020TempiX0020DiX0020Iscrizione()) + "</p>");
		sb.append("<p>Scheda: " + checkLen(ta.getScheda()) + "</p>");

		return sb.toString();
	}
	
	private String checkLen(String s) {
		if (s.length() <= 1) {
			return "-";
		}  else {
			return s;
		}
	}
	

}
