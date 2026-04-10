import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OrderModel } from 'app/order/Order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
 
    constructor(private http:HttpClient){}
    public getOrders(){
      return this.http.get<OrderModel>("http://localhost:8080/order/viewAll");
    }
    public validateOrders(){
      return this.http.get<String>("https://localhost:8080/order/validateAll");
    }
}
  