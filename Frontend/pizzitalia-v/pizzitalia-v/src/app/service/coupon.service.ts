import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CoupanModel } from 'app/coupan/Coupan';

@Injectable({
  providedIn: 'root'
})
export class CouponService {

  constructor(private http:HttpClient) { }

   getCoupan(id:any){
    return this.http.get<CoupanModel[]>("http://localhost:8080/coupan/viewAll");
  }
}
