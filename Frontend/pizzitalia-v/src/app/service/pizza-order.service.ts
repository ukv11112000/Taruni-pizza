import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtClientService } from 'app/jwt-client.service';
import { OrderDtoModel } from 'app/order/Order';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { PizzaOrderComponent } from '../pizza-order/pizza-order.component';
import { PizzaOrderDtoModel, PizzaOrderModel } from '../pizza-order/PizzaOrder';

@Injectable({
  providedIn: 'root'
})
export class PizzaOrderService {
  

  constructor(private http:HttpClient, private jwt:JwtClientService){}
  public getPizzaOrders():Observable<PizzaOrderModel>{
    return this.http.get<PizzaOrderModel>(environment.pizzaOrderUrl+"viewAllActive",this.jwt.httpOptions);
    
  }
  public updatePizzaOrder(p:PizzaOrderDtoModel):Observable<PizzaOrderDtoModel>{
    return this.http.put<PizzaOrderDtoModel>(environment.pizzaOrderUrl+"updateOrder",p,this.jwt.httpOptions);
  }
  public deletePizzaOrder(p:number):Observable<PizzaOrderModel>{
    return this.http.delete<PizzaOrderModel>(environment.pizzaOrderUrl+"deletOrder/"+p,this.jwt.httpOptions)
  }
  
  public placeOrder(order:OrderDtoModel):Observable<PizzaOrderDtoModel>{ 
    return this.http.post<PizzaOrderDtoModel>(environment.orderUrl+"add",order,this.jwt.httpOptions);
  }
}
