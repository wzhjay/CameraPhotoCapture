package com.androidexample.cameraphotocapture;

public class Temple {
	//private variables
	
	String _id;
    String _templeName;
    String _type;
    String _deities;
    String _dialect;
    String _builderName;
    String _worships;
    String _contact;
    String _others;
    String _lon;
    String _lat;
    
    // Empty constructor
    public Temple(){
    }
    
 // constructor
    public Temple(String _id,
    			String _templeName,
    			String _type,
    			String _deities,
    			String _dialect,
    			String _builderName,
    			String _worships,
    			String _contact,
    			String _others,
    			String _lon,
    			String _lat){
    	this._id = _id;
        this._templeName = _templeName;
        this._type = _type;
        this._deities = _deities;
        this._dialect = _dialect;
        this._builderName = _builderName;
        this._worships = _worships;
        this._contact = _contact;
        this._others = _others;
        this._lon = _lon;
        this._lat = _lat;
    }
    
    public String getID(){
        return this._id;
    }
    
    public void setID(String id){
        this._id = id;
    }
    
    public String getTempleName(){
        return this._templeName;
    }
    
    public void setTempleName(String templeName){
        this._templeName = templeName;
    }
    
    public String getType(){
        return this._type;
    }
    
    public void setType(String type){
        this._type = type;
    }
    
    public String getDeities(){
        return this._deities;
    }
    
    public void setDeities(String deities){
        this._deities = deities;
    }
    
    public String getDialect(){
        return this._dialect;
    }
    
    public void setDialect(String dialect){
        this._dialect = dialect;
    }
    
    public String getBuilderName(){
        return this._builderName;
    }
    
    public void setBuilderName(String builderName){
        this._builderName = builderName;
    }
    
    public String getWorships(){
        return this._worships;
    }
    
    public void setWorships(String worships){
        this._worships = worships;
    }

    public String getContact(){
        return this._contact;
    }
    
    public void setContact(String contact){
        this._contact = contact;
    }
    
    public String getOthers(){
        return this._others;
    }
    
    public void setOthers(String others){
        this._others = others;
    }
    
    public String getLon(){
        return this._lon;
    }
    
    public void setLon(String lon){
        this._lon = lon;
    }
    
    public String getLat(){
        return this._lat;
    }
    
    public void setLat(String lat){
        this._lat = lat;
    }

}
