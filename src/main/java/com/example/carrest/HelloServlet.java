package com.example.carrest;

import java.io.*;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/*
        get localhost:8080/cars - get cars
        get localhost:8080/cars/1 - get specific car
        delete localhost:8080/cars/1 - delete car
        post localhost:8080/cars - BODY (car)
 */
@WebServlet("/cars/*")
public class HelloServlet extends HttpServlet {
    private final CarDao carDao = CarDao.getINSTANCE();
    private final ObjectMapper mapper = new ObjectMapper();


    /*
     get localhost:8080/cars - get cars
        get localhost:8080/cars/1 - get specific car
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getPathInfo(); // to get id, use it /1
        System.out.println(contextPath);
        resp.setContentType("application/json");
        List<Car> all = carDao.getAll();
        String json = mapper.writeValueAsString(all);
        resp.getWriter().println(json);
    }

    /*
    delete localhost:8080/cars/1 - delete car
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    /*
    post localhost:8080/cars - BODY (car)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = reader.readLine();
        Car car = mapper.readValue(json, Car.class);
        carDao.saveCar(car);
    }
}