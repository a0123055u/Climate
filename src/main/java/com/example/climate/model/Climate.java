package com.example.climate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name="Climate")
public class Climate {
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "climate_parameters")
	private String id;
	@Column(name="latitude")
	private String latitude;
	@Column(name="longitute")
	private String longitute;
	@Column(name="time")
	private String time;
	@Column(name="summary")
	private String summary;
	@Column(name="icon")
	private String icon;
	@Column(name="nearestStormDistance")
	private String nearestStormDistance;
	@Column(name="precipIntensity")
	private String precipIntensity;
	@Column(name="precipProbability")
	private String precipProbability;
	@Column(name="precipType")
	private String precipType;
	@Column(name="temperature")
	private String temperature;
	@Column(name="apparentTemperature")
	private String apparentTemperature;
	@Column(name="dewPoint")
	private String dewPoint;
	@Column(name="humidity")
	private String humidity;
	@Column(name="pressure")
	private String pressure;
	@Column(name="windSpeed")
	private String windSpeed;
	@Column(name="windGust")
	private String windGust;
	@Column(name="windBearing")
	private String windBearing;
	@Column(name="cloudCover")
	private String cloudCover;
	@Column(name="uvIndex")
	private String uvIndex;
	@Column(name="visibility")
	private String visibility;
	@Column(name="ozone")
	private String ozone;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitute() {
		return longitute;
	}
	public void setLongitute(String longitute) {
		this.longitute = longitute;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getNearestStormDistance() {
		return nearestStormDistance;
	}
	public void setNearestStormDistance(String nearestStormDistance) {
		this.nearestStormDistance = nearestStormDistance;
	}
	public String getPrecipIntensity() {
		return precipIntensity;
	}
	public void setPrecipIntensity(String precipIntensity) {
		this.precipIntensity = precipIntensity;
	}
	public String getPrecipProbability() {
		return precipProbability;
	}
	public void setPrecipProbability(String precipProbability) {
		this.precipProbability = precipProbability;
	}
	public String getPrecipType() {
		return precipType;
	}
	public void setPrecipType(String precipType) {
		this.precipType = precipType;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getApparentTemperature() {
		return apparentTemperature;
	}
	public void setApparentTemperature(String apparentTemperature) {
		this.apparentTemperature = apparentTemperature;
	}
	public String getDewPoint() {
		return dewPoint;
	}
	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindGust() {
		return windGust;
	}
	public void setWindGust(String windGust) {
		this.windGust = windGust;
	}
	public String getWindBearing() {
		return windBearing;
	}
	public void setWindBearing(String windBearing) {
		this.windBearing = windBearing;
	}
	public String getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(String cloudCover) {
		this.cloudCover = cloudCover;
	}
	public String getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(String uvIndex) {
		this.uvIndex = uvIndex;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getOzone() {
		return ozone;
	}
	public void setOzone(String ozone) {
		this.ozone = ozone;
	}
	

}
