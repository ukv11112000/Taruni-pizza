import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OrderDtoModel } from 'app/order/Order';
import { PizzaOrderComponent } from '../pizza-order/pizza-order.component';
import { PizzaOrderDtoModel, PizzaOrderModel } from '../pizza-order/PizzaOrder';

@Injectable({
  providedIn: 'root'
})
export class PizzaOrderService {
  

  constructor(private http:HttpClient){}
  public getPizzaOrders(){
    return this.http.get<PizzaOrderModel>("http://localhost:8080/pizzaOrder/viewAllActive");
    
  }
  public updatePizzaOrder(p:PizzaOrderDtoModel){
    return this.http.put<PizzaOrderDtoModel>("http://localhost:8080/pizzaOrder/updateOrder",p);
  }
  public deletePizzaOrder(p:number){
    return this.http.delete<PizzaOrderModel>("http://localhost:8080/pizzaOrder/deletOrder/"+p)
  }
  
  public placeOrder(order:OrderDtoModel) {
    return this.http.post<PizzaOrderDtoModel>("http://localhost:8080/order/add",order);
  }
}
