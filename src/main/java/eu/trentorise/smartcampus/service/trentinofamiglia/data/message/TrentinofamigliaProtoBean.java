package eu.trentorise.smartcampus.service.trentinofamiglia.data.message;

import java.util.List;
import java.util.LinkedList;

import it.sayservice.xss.api.XSSDataException;
import it.sayservice.xss.api.data.XSSData;
import it.sayservice.xss.api.data.DOMData;

import com.google.protobuf.ByteString;
import java.io.UnsupportedEncodingException;

import it.sayservice.platform.core.message.ProtoBean;
/**
*
* Generated ProtoBean class: DO NOT EDIT!
*
*/
public class TrentinofamigliaProtoBean {
   public static class EventoFamigliaProtoBean implements ProtoBean {
          private String id;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    
          private String title;
    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {
      this.title = title;
    }
    
          private String description;
    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {
      this.description = description;
    }
    
          private String from;
    public String getFrom() {
      return from;
    }
    public void setFrom(String from) {
      this.from = from;
    }
    
          private String to;
    public String getTo() {
      return to;
    }
    public void setTo(String to) {
      this.to = to;
    }
    
          private String days;
    public String getDays() {
      return days;
    }
    public void setDays(String days) {
      this.days = days;
    }
    
          private String organization;
    public String getOrganization() {
      return organization;
    }
    public void setOrganization(String organization) {
      this.organization = organization;
    }
    
          private String place;
    public String getPlace() {
      return place;
    }
    public void setPlace(String place) {
      this.place = place;
    }
    
          private Boolean certified;
    public Boolean getCertified() {
      return certified;
    }
    public void setCertified(Boolean certified) {
      this.certified = certified;
    }
    
          private it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean poi;
    public it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean getPoi() {
      return poi;
    }
    public void setPoi(it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean poi) {
      this.poi = poi;
    }
    
    
    public EventoFamigliaProtoBean() {
    	super();
    }
    
    public EventoFamigliaProtoBean(eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.EventoFamiglia reference) {
      super();
                        setId(reference.getId());
                                    setTitle(reference.getTitle());
                                    setDescription(reference.getDescription());
                                    setFrom(reference.getFrom());
                                    setTo(reference.getTo());
                                    setDays(reference.getDays());
                                    setOrganization(reference.getOrganization());
                                    setPlace(reference.getPlace());
                                    setCertified(reference.getCertified());
                                    setPoi(new it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean(reference.getPoi()));
                      }  

    public EventoFamigliaProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("id") != null && !data.get("id").isEmpty()) {
            if (data.get("id").size()>1) throw new XSSDataException("Incorrect data cardinality for field id: expected single value.");
            
            Object item = data.get("id").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field id: expected DOMData");
                                  setId(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("title") != null && !data.get("title").isEmpty()) {
            if (data.get("title").size()>1) throw new XSSDataException("Incorrect data cardinality for field title: expected single value.");
            
            Object item = data.get("title").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field title: expected DOMData");
                                  setTitle(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("description") != null && !data.get("description").isEmpty()) {
            if (data.get("description").size()>1) throw new XSSDataException("Incorrect data cardinality for field description: expected single value.");
            
            Object item = data.get("description").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field description: expected DOMData");
                                  setDescription(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("from") != null && !data.get("from").isEmpty()) {
            if (data.get("from").size()>1) throw new XSSDataException("Incorrect data cardinality for field from: expected single value.");
            
            Object item = data.get("from").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field from: expected DOMData");
                                  setFrom(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("to") != null && !data.get("to").isEmpty()) {
            if (data.get("to").size()>1) throw new XSSDataException("Incorrect data cardinality for field to: expected single value.");
            
            Object item = data.get("to").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field to: expected DOMData");
                                  setTo(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("days") != null && !data.get("days").isEmpty()) {
            if (data.get("days").size()>1) throw new XSSDataException("Incorrect data cardinality for field days: expected single value.");
            
            Object item = data.get("days").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field days: expected DOMData");
                                  setDays(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("organization") != null && !data.get("organization").isEmpty()) {
            if (data.get("organization").size()>1) throw new XSSDataException("Incorrect data cardinality for field organization: expected single value.");
            
            Object item = data.get("organization").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field organization: expected DOMData");
                                  setOrganization(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("place") != null && !data.get("place").isEmpty()) {
            if (data.get("place").size()>1) throw new XSSDataException("Incorrect data cardinality for field place: expected single value.");
            
            Object item = data.get("place").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field place: expected DOMData");
                                  setPlace(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("certified") != null && !data.get("certified").isEmpty()) {
            if (data.get("certified").size()>1) throw new XSSDataException("Incorrect data cardinality for field certified: expected single value.");
            
            Object item = data.get("certified").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field certified: expected DOMData");
                                  setCertified(convertToBoolean(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("poi") != null && !data.get("poi").isEmpty()) {
            if (data.get("poi").size()>1) throw new XSSDataException("Incorrect data cardinality for field poi: expected single value.");
            
            Object item = data.get("poi").get(0); 
                          if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field poi: expected XSSData");
              setPoi(new it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean((XSSData)item));
                      }
                  }  

    
    public eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.EventoFamiglia buildMessage() {
      eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.EventoFamiglia .Builder builder = eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.EventoFamiglia .newBuilder();
                        if (getId() != null) {
      	builder.setId(getId());
      }
                                    if (getTitle() != null) {
      	builder.setTitle(getTitle());
      }
                                    if (getDescription() != null) {
      	builder.setDescription(getDescription());
      }
                                    if (getFrom() != null) {
      	builder.setFrom(getFrom());
      }
                                    if (getTo() != null) {
      	builder.setTo(getTo());
      }
                                    if (getDays() != null) {
      	builder.setDays(getDays());
      }
                                    if (getOrganization() != null) {
      	builder.setOrganization(getOrganization());
      }
                                    if (getPlace() != null) {
      	builder.setPlace(getPlace());
      }
                                    if (getCertified() != null) {
      	builder.setCertified(getCertified());
      }
                                    if (getPoi() != null) {
      	builder.setPoi(getPoi() .buildMessage());
      }
                        return builder.buildPartial();
    }
    
    
  }
  public static class OrganizzazioneFamigliaProtoBean implements ProtoBean {
          private String name;
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    
          private String status;
    public String getStatus() {
      return status;
    }
    public void setStatus(String status) {
      this.status = status;
    }
    
          private String link;
    public String getLink() {
      return link;
    }
    public void setLink(String link) {
      this.link = link;
    }
    
          private it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean poi;
    public it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean getPoi() {
      return poi;
    }
    public void setPoi(it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean poi) {
      this.poi = poi;
    }
    
    
    public OrganizzazioneFamigliaProtoBean() {
    	super();
    }
    
    public OrganizzazioneFamigliaProtoBean(eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.OrganizzazioneFamiglia reference) {
      super();
                        setName(reference.getName());
                                    setStatus(reference.getStatus());
                                    setLink(reference.getLink());
                                    setPoi(new it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean(reference.getPoi()));
                      }  

    public OrganizzazioneFamigliaProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("name") != null && !data.get("name").isEmpty()) {
            if (data.get("name").size()>1) throw new XSSDataException("Incorrect data cardinality for field name: expected single value.");
            
            Object item = data.get("name").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field name: expected DOMData");
                                  setName(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("status") != null && !data.get("status").isEmpty()) {
            if (data.get("status").size()>1) throw new XSSDataException("Incorrect data cardinality for field status: expected single value.");
            
            Object item = data.get("status").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field status: expected DOMData");
                                  setStatus(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("link") != null && !data.get("link").isEmpty()) {
            if (data.get("link").size()>1) throw new XSSDataException("Incorrect data cardinality for field link: expected single value.");
            
            Object item = data.get("link").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field link: expected DOMData");
                                  setLink(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("poi") != null && !data.get("poi").isEmpty()) {
            if (data.get("poi").size()>1) throw new XSSDataException("Incorrect data cardinality for field poi: expected single value.");
            
            Object item = data.get("poi").get(0); 
                          if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field poi: expected XSSData");
              setPoi(new it.sayservice.platform.core.message.CoreProtoBean.POIProtoBean((XSSData)item));
                      }
                  }  

    
    public eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.OrganizzazioneFamiglia buildMessage() {
      eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.OrganizzazioneFamiglia .Builder builder = eu.trentorise.smartcampus.service.trentinofamiglia.data.message.Trentinofamiglia.OrganizzazioneFamiglia .newBuilder();
                        if (getName() != null) {
      	builder.setName(getName());
      }
                                    if (getStatus() != null) {
      	builder.setStatus(getStatus());
      }
                                    if (getLink() != null) {
      	builder.setLink(getLink());
      }
                                    if (getPoi() != null) {
      	builder.setPoi(getPoi() .buildMessage());
      }
                        return builder.buildPartial();
    }
    
    
  }

 
  protected static double convertToDouble(String value) {
    return Double.parseDouble(value);
  }
  
  protected static float convertToFloat(String value) {
    return Float.parseFloat(value);
  }

  protected static boolean convertToBoolean(String value) {
    return Boolean.parseBoolean(value);
  }

  protected static String convertToString(String value) {
    return value;
  }

  protected static int convertToInteger(String value) {
    return Integer.parseInt(value);
  }

  protected static long convertToLong(String value) {
    return Long.parseLong(value);
  }

  protected static ByteString convertToByteString(String value) {
    try {
      return ByteString.copyFrom(value.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      return ByteString.copyFrom(value.getBytes());
    }
  }
 
}
