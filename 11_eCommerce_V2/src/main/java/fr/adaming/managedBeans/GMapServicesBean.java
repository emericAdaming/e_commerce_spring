package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name= GMapServicesBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class GMapServicesBean implements Serializable{
    public static final String BEAN_NAME = "gMapServicesBean";
    public String getBeanName() { return BEAN_NAME; }

    private String points="Regent's Park, London: Kingston upon Thames";
    private String options="optimizeWaypoints:true";
    private String travelMode="DRIVING";

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }
}