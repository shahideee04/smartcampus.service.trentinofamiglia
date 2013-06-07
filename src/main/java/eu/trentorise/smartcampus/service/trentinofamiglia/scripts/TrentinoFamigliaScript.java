package eu.trentorise.smartcampus.service.trentinofamiglia.scripts;

import it.sayservice.platform.core.message.Core.Address;
import it.sayservice.platform.core.message.Core.Coordinate;
import it.sayservice.platform.core.message.Core.POI;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.protobuf.Message;

import eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia;
import eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.EventoFamiglia;
import eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.OrganizzazioneFamiglia;
import eu.trentorise.smartcampus.service.trentinofamiglia.jaxb.Dataroot;
import eu.trentorise.smartcampus.service.trentinofamiglia.jaxb.Dataroot.TABOrg;
import eu.trentorise.smartcampus.service.trentinofamiglia.jaxb.Dataroot.TABOrg.TABAttività;
import eu.trentorise.smartcampus.service.trentinofamiglia.jaxb.Dataroot.TABOrg.TABAttività.TAbTerritorio;

public class TrentinoFamigliaScript {

	public List<Message> parseEstate(String s) throws Exception {
		try {
			List<Message> result = new ArrayList<Message>();

			JAXBContext jc = JAXBContext
					.newInstance("eu.trentorise.smartcampus.service.trentinofamiglia.jaxb");
			Unmarshaller u = jc.createUnmarshaller();

			// InputStream rs =
			// Thread.currentThread().getContextClassLoader().getResourceAsStream("service/trentinofamiglia/estate_giovani_e_famiglia_2013.xml");
			//
			// StreamSource ss = new StreamSource(rs);
			Dataroot dataroot = (Dataroot) u.unmarshal(new StringReader(s));

			for (TABOrg org : dataroot.getTABOrg()) {
				for (TABAttività ta : org.getTABAttività()) {
					Map<String, EventoFamiglia> map = new TreeMap<String, Trentinofamiglia.EventoFamiglia>();
					for (TAbTerritorio tt : ta.getTAbTerritorio()) {
						if (!map.containsKey(tt.getComuni())) {
							EventoFamiglia.Builder builder = EventoFamiglia
									.newBuilder();
							builder.setId(ta.getNomeX0020Attività() + "@"
									+ org.getNomeOrganizzazione());
							builder.setTitle(ta.getNomeX0020Attività());
							builder.setDescription(buildDescription(ta));
							builder.setFrom(ta
									.getDataX0020InizioX0020PeriodoX0020Attività());
							builder.setTo(ta
									.getDataX0020FineX0020PeriodoX0020Attività());
							builder.setDays(ta
									.getLX0027AttivitàX0020SiX0020SvolgeX0020NeiX0020SeguentiX0020Giorni());
							builder.setOrganization(org.getNomeOrganizzazione());
							builder.setPlace(tt.getComuni());
							String certified = ta
									.getLX0027AttivitàX0020ÈX0020MarchiataX0020X0022FamilyX0020InX0020TrentinoX0022X003F();
							builder.setCertified(certified.startsWith("s") ? true
									: false);
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
		sb.append("<p>"
				+ ta.getDescrizioneX0020SinteticaX0020DellX0027Attività()
				+ "</p>");
		sb.append("<p>Orari/Periodicità/Turni: "
				+ checkLen(ta
						.getOrariX0020X002FX0020PeriodicitàX002FX0020TurniX0020DiX0020Svolgimento())
				+ "</p>");
		sb.append("<p>Sede: "
				+ checkLen(ta
						.getSedeX002FIX0020PressoX0020CuiX0020SiX0020SvolgeràX0020LX0027Attività())
				+ "</p>");
		sb.append("<p>Attività diurna/residenziale: "
				+ checkLen(ta
						.getTipoX0020DiX0020AttivitàX0020X0028DiurnaX002FResidenzialeX0029())
				+ "</p>");
		sb.append("<p>Costo: " + checkLen(ta.getTipoX0020DiX0020Costo())
				+ "</p>");
		sb.append("<p>Specifiche sul costo: "
				+ checkLen(ta.getSpecificheX0020SulX0020Costo()) + "</p>");
		sb.append("<p>Fascia età: "
				+ checkLen(ta.getFasciaX002FEX0020DiX0020EtàX0020Destinatari())
				+ "</p>");
		sb.append("<p>Vincoli residenza: "
				+ checkLen(ta
						.getVincoliX0020DiX0020ResidenzaX0020DeiX0020Destinatari())
				+ "</p>");
		sb.append("<p>Referenti: "
				+ checkLen(ta
						.getReferentiX0020CheX0020PossonoX0020FornireX0020InformazioniX0020SuX0020QuestaX0020Attività())
				+ "</p>");
		sb.append("<p>Modalità e tempi di iscrizione: "
				+ checkLen(ta
						.getModalitàX0020EX0020TempiX0020DiX0020Iscrizione())
				+ "</p>");
		sb.append("<p>Scheda: " + checkLen(ta.getScheda()) + "</p>");

		return sb.toString();
	}

	private String checkLen(String s) {
		if (s.length() <= 1) {
			return "-";
		} else {
			return s;
		}
	}

	public List<Message> parseOrganizzazioni(String s) throws Exception {
		List<Message> result = new ArrayList<Message>();
		try {
			// InputStream rs =
			// Thread.currentThread().getContextClassLoader().getResourceAsStream("service/trentinofamiglia/registro_organizzazioni_certificate_familyaudit.csv");
			// InputStreamReader isr = new InputStreamReader(rs);
			BufferedReader br = new BufferedReader(new StringReader(s));

			String line = null;
			boolean first = true;
			while ((line = br.readLine()) != null) {
				if (first) {
					first = false;
					continue;
				}
				String words[] = line.split(";");
				OrganizzazioneFamiglia.Builder builder = OrganizzazioneFamiglia
						.newBuilder();
				builder.setName(words[1].replace("\"", ""));
				builder.setStatus(words[2].replace("\"", ""));
				builder.setLink(words[8].replace("\"", ""));
				POI poi = buildOrganizzazioniPOI(words);
				builder.setPoi(poi);
				
				result.add(builder.build());
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public String getOrganizzazioniURL(Document doc) throws XPathExpressionException {
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xPath.evaluate(
				"//*[@id='dataset-resources']/ul/li/a",
				doc.getDocumentElement(), XPathConstants.NODESET);

		if (nodes.getLength() == 1) {
			String url = ((Element)nodes.item(0)).getAttribute("href");
			return url;
		}

		return "";
	}
	
	public String getOrganizzazioniCSVURL(Document doc) throws XPathExpressionException {
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xPath.evaluate(
				"//*[@id='content']/div/div[1]/a[1]",
				doc.getDocumentElement(), XPathConstants.NODESET);

		if (nodes.getLength() == 1) {
			String url = ((Element)nodes.item(0)).getAttribute("href");
			return url;
		}

		return "";
	}	
	
	public String getEventiURL(Document doc) throws XPathExpressionException {
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xPath.evaluate(
				"//*[@id='dataset-resources']/ul/li/a",
				doc.getDocumentElement(), XPathConstants.NODESET);

		if (nodes.getLength() == 1) {
			String url = ((Element)nodes.item(0)).getAttribute("href");
			return url;
		}

		return "";
	}
	
	public String getEventiXMLURL(Document doc) throws XPathExpressionException {
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xPath.evaluate(
				"//*[@id='content']/div/div[1]/a[1]",
				doc.getDocumentElement(), XPathConstants.NODESET);

		if (nodes.getLength() == 1) {
			String url = ((Element)nodes.item(0)).getAttribute("href");
			return url;
		}

		return "";
	}		
	
	public POI buildOrganizzazioniPOI(String[] words) {
		POI.Builder poiBuilder = POI.newBuilder();
		
		Address.Builder addressBuilder = Address.newBuilder();
		addressBuilder.setStreet(words[3].replace("\"", ""));
		addressBuilder.setCity(removeSpaces(words[4]).replace("\"", ""));
		addressBuilder.setRegion(removeSpaces(words[5]).replace("\"", ""));
		addressBuilder.setCountry("ITA");
		addressBuilder.setState("Italy");
		addressBuilder.setLang("en");
		addressBuilder.setPostalCode("");
		
		poiBuilder.setAddress(addressBuilder.build());
		
		Coordinate.Builder coordBuilder = Coordinate.newBuilder();
		
		coordBuilder.setLatitude(Double.parseDouble(transformLatLong(words[6].replace("\"", ""))));
		coordBuilder.setLongitude(Double.parseDouble(transformLatLong(words[7].replace("\"", ""))));
		poiBuilder.setCoordinate(coordBuilder.build());
		
		poiBuilder.setDatasetId("smart");
		poiBuilder.setPoiId(words[1].replace("\"", "")  + "@smartcampus.service.trentinofamiglia");
		
		
		return poiBuilder.build();
	}
	
	private static String removeSpaces(String s) {
		return s.replaceAll("[\\s]+", " ").trim();
	}

	private static String transformLatLong(String ll) {
		String s = ll.replaceFirst("\\.", ",").replaceFirst("\\.", "")
				.replaceAll(",", ".");
		return s;
	}	

}
