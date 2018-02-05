package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.enums.CarStatus;
import com.rockka.carrent.services.CarService;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 ** CarDao proxy
 */
@Service("carService")
public class CarServiceImp implements CarService {
	@Autowired
	private CarDao carDao;
	private Logger logger = LoggerFactory.getLogger(CarServiceImp.class);
	/*
	 ** Calling dao method
	 */
	@Override
	public List<Car> getAll() {
		return carDao.getAll();
	}
	/*
	 ** Checks variables before dao call
	 */
	@Override
	public Car getById(long car_id) {
		if(car_id >0) {
			return carDao.getById(car_id);
		} else {
			logger.error("Car_id is negative");
		}
		return null;
	}
	/*
	 ** Checks variables before dao call
	 */
	@Override
	public Car save(Car car) {
		if (car != null) {
			carDao.save(car);
		}else {
			logger.error("Car is null");
		}
		return car;
	}
	/*
	 ** Checks variables and setts invoice status to deleted (Not deleting entity from DB)
	 */
	@Override
	public Car delete(Car car) {
		if (car != null) {
			update(car.setStatus(CarStatus.DELETED));
		}else {
			logger.error("Car is null");
		}
		return car;
	}
	/*
	 ** Checks variables before dao call
	 */
	@Override
	public Car update(Car car) {
		if (car != null) {
			carDao.update(car.setModifiedAt(new LocalDateTime()));
		}else {
			logger.error("Car is null");
		}
        return car;
	}

}
