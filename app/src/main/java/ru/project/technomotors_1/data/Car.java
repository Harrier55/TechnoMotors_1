package ru.project.technomotors_1.data;

public class Car {

     String model;
     String year;
     String service;
     String date;

     public Car (String _model,String _year,String _service,String _date){
         this.model = _model;
         this.year = _year;
         this.service = _service;
         this.date = _date;
     }

     public Car(){
         this.model = "Уличный кот";
         this.year = "Уличный кот";
         this.service = "Уличный кот";
         this.date = "Уличный кот";
     }
}
