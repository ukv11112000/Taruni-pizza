import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserModel } from 'app/customers/User';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {


constructor(private http:HttpClient){}

public getUsers(){
  return this.http.get<UserModel>("http://localhost:8080/customer/all");
  
}
public delete(id: Number) {
  return this.http.delete<UserModel>("http://localhost:8080/user/delete/"+id);
}
}
