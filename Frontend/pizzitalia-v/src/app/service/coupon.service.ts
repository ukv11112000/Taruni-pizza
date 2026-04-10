import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CoupanModel } from 'app/coupan/Coupan';
import { JwtClientService } from 'app/jwt-client.service';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CouponService {

  constructor(private http:HttpClient, private jwt:JwtClientService) { }
  pizzaid!: number;
  
  
  form: FormGroup = new FormGroup({
    coupanid: new FormControl(null),
    coupanName: new FormControl('', Validators.required),
    coupanType: new FormControl('', [ Validators.required,Validators.pattern('^(0|[1-9][0-9]*)$')]),
    coupaanDescription:new FormControl('',[Validators.required, Validators.minLength(10),Validators.maxLength(200)]),
    coupanPizzaid:new FormControl('')
   
    
  }); 

  initializeFormGroup() {  
    this.form.setValue({
      coupanid: null,
      coupanName: '',
      coupanType: null,
      coupaanDescription:'',
      coupanPizzaid:null
      
    });  
}

  populateForm(coupon:any){
      console.log(coupon);
      this.form.setValue(coupon);
  }

   getCoupan(id:any):Observable<CoupanModel[]>{
    return this.http.get<CoupanModel[]>(environment.coupanUrl+"viewAll",this.jwt.httpOptions);
  }

  insertCoupon(coupan:any):Observable<CoupanModel[]>{
    coupan.coupanPizzaid=this.pizzaid;
    console.log("from insert " +this.pizzaid);
    let body = JSON.stringify({coupan});
    let p=JSON.parse(body);
    return this.http.post<CoupanModel[]>(environment.coupanUrl+"add",p,this.jwt.httpOptions);
  }

  updateCoupon(coupan:any):Observable<CoupanModel[]>{
    let body = JSON.stringify({coupan});
    let p=JSON.parse(body);
    console.log(p);
    return this.http.put<CoupanModel[]>(environment.coupanUrl+"edit",p,this.jwt.httpOptions);
  }

  pizzaId(id:number){
    console.log(id);
    this.pizzaid= id;
  }

  delete(id:number):Observable<CoupanModel[]>{
    return this.http.delete<CoupanModel[]>(environment.coupanUrl+"delete/"+id,this.jwt.httpOptions);
  }

}
