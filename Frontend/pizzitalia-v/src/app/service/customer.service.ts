import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tokenize } from '@angular/compiler/src/ml_parser/lexer';
import { Injectable } from '@angular/core';
import { UserModel } from 'app/customers/User';
import { JwtClientService } from 'app/jwt-client.service';
import { PizzaOrderModel } from 'app/pizza-order/PizzaOrder';
import { Customer } from 'app/register/customer';
import { TokenStorageService } from 'app/token-storage.service';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';




@Injectable({
  providedIn: 'root'
})

export class CustomerService {



constructor(private http:HttpClient,private tokenStorage: TokenStorageService,
  private jwt:JwtClientService
 ){}

public getUsers():Observable<any>{
 
  console.log(this.jwt.httpOptions);
  return this.http.get(environment.customerUrl+"all", this.jwt.httpOptions);
  
}

public delete(id: Number):Observable<Customer> {
  return this.http.delete<Customer>(environment.customerUrl+"delete/"+id,this.jwt.httpOptions);
}


}
