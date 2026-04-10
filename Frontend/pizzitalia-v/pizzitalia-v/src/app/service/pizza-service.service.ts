
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

@Injectable({
  providedIn: 'root'
})
export class PizzaServiceService {

  constructor(private http:HttpClient){}

  private pizzaUrl='http://localhost:8080/pizza/'


  form: FormGroup = new FormGroup({
    id: new FormControl(null),
    name: new FormControl('', Validators.required),
    type: new FormControl('1'),
    coupan:new FormControl(null),
    quantity: new FormControl(''),
    description: new FormControl('',  [Validators.required, Validators.minLength(10),Validators.maxLength(70)]),
    price: new FormControl(0,Validators.required),
    url: new FormControl('',Validators.required),
 
    
  }); 

  initializeFormGroup() {  
    this.form.setValue({
      id: null,
      name: '',
      type: '1',
      coupan:null,
      quantity:'',
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
    return this.http.post("http://localhost:8080/pizza/add",p);
  } 
  
  getPizzas(): Observable<PizzaModel[]> {
    return this.http.get<PizzaModel[]>(this.pizzaUrl+"viewpizzalist");
  }

  public orderPizza(pizza:any){
    console.log(pizza)
    return this.http.post<PizzaModel>("http://localhost:8080/pizzaOrder/placeOrder",pizza);
    
  }
  public deletePizza(id:number){
    return this.http.delete("http://localhost:8080/pizza/delete/"+id);
  }

  public updatePizza(pizza:any){
    let body = JSON.stringify({pizza});
    let p=JSON.parse(body);
    return this.http.put(this.pizzaUrl+"update",p);
  }

  public getVegPizza(){
    return this.http.get<PizzaModel[]>(this.pizzaUrl+"type/veg");
  }

  public getNonVegPizza(){
    return this.http.get<PizzaModel[]>(this.pizzaUrl+"type/non-veg");
  }
  
  public getPizza(id:number){
    return this.http.get<PizzaModel[]>(this.pizzaUrl+'viewpizza/'+id);
  }

}



