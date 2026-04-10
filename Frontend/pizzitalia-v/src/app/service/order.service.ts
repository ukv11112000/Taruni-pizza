import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtClientService } from 'app/jwt-client.service';
import { OrderModel } from 'app/order/Order';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
 
    constructor(private http:HttpClient, private jwt:JwtClientService){}
    public getOrders():Observable<OrderModel>{
      return this.http.get<OrderModel>(environment.orderUrl+"viewAll",this.jwt.httpOptions);
    }
    public validateOrders():Observable<String>{
      return this.http.get<String>(environment.orderUrl+"validateAll",this.jwt.httpOptions);
    }
}
  