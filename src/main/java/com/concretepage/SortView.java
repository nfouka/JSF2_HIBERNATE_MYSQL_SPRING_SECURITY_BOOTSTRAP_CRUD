package com.concretepage;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;
 
@ManagedBean(name="dtSortView")
@ViewScoped
public class SortView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 7890985942552105890L;
	private List<Car> cars1;
    private List<Car> cars2;
    private List<Car> cars3;
     
 
    @PostConstruct
    public void init() {
        cars1 = new CarService().createCars(10)  ; 
        cars2 = new CarService().createCars(10)  ; 
        cars3 = new CarService().createCars(50)  ; 
    }
 
    public List<Car> getCars1() {
        return cars1;
    }
 
    public List<Car> getCars2() {
        return cars2;
    }
     
    public List<Car> getCars3() {
        return cars2;
    }
 
    public void setService(CarService service) {
       
    }
}