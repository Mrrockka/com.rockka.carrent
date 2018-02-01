package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.services.CarService;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImp implements CarService {
	@Autowired
	private CarDao carDao;
	private Logger logger = LoggerFactory.getLogger(CarServiceImp.class);
	@Override
	public List<Car> getAll() {
		return carDao.getAll();
	}

	@Override
	public Car getById(long car_id) {
		if(car_id >0) {
			return carDao.getById(car_id);
		} else {
			logger.error("Car_id is negative");
		}
		return null;
	}

	@Override
	public Car save(Car car) {
		if (car != null) {
			carDao.save(car);
		}else {
			logger.error("Car is null");
		}
		return car;
	}

	@Override
	public Car delete(Car car) {
		if (car != null) {
			update(car.setStatus(0));
		}else {
			logger.error("Car is null");
		}
		return car;
	}

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
