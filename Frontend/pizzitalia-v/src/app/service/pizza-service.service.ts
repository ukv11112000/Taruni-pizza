
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { validateBasis } from '@angular/flex-layout';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { PizzaOrderDtoModel } from 'app/pizza-order/PizzaOrder';
import { PizzaModel } from 'app/pizzas/Pizza';
import * as _ from 'lodash';
import { CompileShallowModuleMetadata } from '@angular/compiler';
import { environment } from 'environments/environment';
import { JwtClientService } from 'app/jwt-client.service';


@Injectable({
  providedIn: 'root'
})
export class PizzaServiceService {

  constructor(private http:HttpClient, private jwt:JwtClientService){}

  form: FormGroup = new FormGroup({
   id: new FormControl(null),
    name: new FormControl('', Validators.required),
    type: new FormControl('1'),
    coupan:new FormControl(null),
    quantity: new FormControl(1),
    description: new FormControl('',  [Validators.required, Validators.minLength(10),Validators.maxLength(200)]),
    price: new FormControl('', [ Validators.required,Validators.pattern('^(0|[1-9][0-9]*)$')]),
    url: new FormControl('',Validators.required),
 
    
  }); 

  initializeFormGroup() {  
    this.form.setValue({
      id: null,
      name: '',
      type: '1',
      coupan:null,
      quantity:1,
      description: '',
      price: 0,
      url: ''
      
    });  
}

  populateForm(pizza:any){
    console.log("kjdk");
    console.log(pizza);
    if(pizza.type=="1")
    pizza.type='1';
    else 
    pizza.type='2';
    this.form.setValue(pizza);
  }

  key!:number;
  set(id:any){
    this.key=id;
   // console.log(this.key);
  }

  get(){
    return this.key;
  }
    
 public insertPizza(pizza:any){
    let body = JSON.stringify({pizza});
    let p=JSON.parse(body);
    console.log(p);
    return this.http.post(environment.pizzaUrl+"add",p,this.jwt.httpOptions);
  } 
  
  getPizzas(): Observable<PizzaModel[]> {
    return this.http.get<PizzaModel[]>(environment.pizzaUrl+"viewpizzalist",this.jwt.httpOptions);
  }

  public orderPizza(pizza:any){
    console.log(pizza)
    return this.http.post<PizzaModel>(environment.pizzaOrderUrl+"placeOrder",pizza,this.jwt.httpOptions);
    
  }
  public deletePizza(id:number){
    return this.http.delete(environment.pizzaUrl+"delete/"+id,this.jwt.httpOptions);
  }

  public updatePizza(pizza:any){
    let body = JSON.stringify({pizza});
    let p=JSON.parse(body);
    return this.http.put(environment.pizzaUrl+"update",p,this.jwt.httpOptions);
  }

  public getPizza(id:number){
    return this.http.get<PizzaModel[]>(environment.pizzaUrl+"viewpizza/"+id,this.jwt.httpOptions);
  }

}
