package edu.unsj.fcefn.lcc.optimizacion.api.model.entities;


import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "frames")

public class FrameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "id_transport_company")
    private Integer id_transport_company;

    @Column(name = "id_stop_departure")
    private Integer id_stop_departure;

    @Column(name = "id_stop_arrival")
    private Integer id_stop_arrival;

    @Column(name = "price")
    private float price;

    @Column(name = "category")
    private String category;

    @Column(name = "departure_datetime")
    private LocalTime departure_datetime;

    @Column(name = "arrival_datetime")
    private LocalTime arrival_datetime;

    public Integer getId() {  return id;   }

    public void setId(Integer id) {      this.id = id;    }

    public Integer getId_transport_company() {    return id_transport_company;    }

    public void setId_transport_company(Integer id_transport_company) {        this.id_transport_company = id_transport_company;    }

    public Integer getId_stop_departure() {     return id_stop_departure;   }

    public void setId_stop_departure(Integer id_stop_departure) {     this.id_stop_departure = id_stop_departure;   }

    public Integer getId_stop_arrival() {      return id_stop_arrival;   }

    public void setId_stop_arrival(Integer id_stop_arrival) {     this.id_stop_arrival = id_stop_arrival;  }

    public float getPrice() {    return price;   }

    public void setPrice(float price) {     this.price = price;  }

    public String getCategory() {     return category;  }

    public void setCategory(String category) {    this.category = category;  }

    public LocalTime getDeparture_datetime() {     return departure_datetime;  }

    public void setDeparture_datetime(LocalTime departure_datetime) {     this.departure_datetime = departure_datetime;  }

    public LocalTime getArrival_datetime() {     return arrival_datetime;  }

    public void setArrival_datetime(LocalTime arrival_datetime) {   this.arrival_datetime = arrival_datetime;  }
}
