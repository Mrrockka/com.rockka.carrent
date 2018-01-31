package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.services.CarService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("carService")
public class CarServiceImp implements CarService {
	@Autowired
	private CarDao carDao;

	@Override
	public List<Car> getAll() {
		return carDao.getAll();
	}

	@Override
	public Car getById(long car_id) {
		return carDao.getById(car_id);
	}

	@Override
	public Car save(Car car) {
		if (car != null) {
			carDao.save(car);
		}
		return car;
	}

	@Override
	public Car delete(Car car) {
		if (car != null) {
			update(car.setCarStatus(0));
		}
		return car;
	}

	@Override
	public Car update(Car car) {
		if (car != null) {
			carDao.update(car.setModifiedAt(new LocalDateTime()));
		}
        return car;
	}

}
