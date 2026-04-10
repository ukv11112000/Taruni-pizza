import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderDtoModel } from 'app/order/Order';

import { AuthenticationService } from 'app/service/authentication.service';
import { NotificationService } from 'app/service/notification.service';
import { orderBy } from 'lodash';

import { PizzaOrderService } from '../service/pizza-order.service';
import { PizzaOrderDtoModel, PizzaOrderModel } from './PizzaOrder';

@Component({
  selector: 'app-pizza-order',
  templateUrl: './pizza-order.component.html',
  styleUrls: ['./pizza-order.component.css']
})
export class PizzaOrderComponent implements OnInit {

  pizzaOrders:PizzaOrderModel[]=[];
  totalcost:number=0
  order:OrderDtoModel =new OrderDtoModel("","",1,true,0)
  c:boolean=true
  navbar:boolean=true
  currentCustomer!: any;
  constructor(private service:PizzaOrderService,
              private router:Router,
              public notificationService:NotificationService,
              private customer:AuthenticationService) { }

  ngOnInit(): void {
    this.service.getPizzaOrders().subscribe((data:any)=>{
      this.pizzaOrders=data;
      console.log(this.pizzaOrders)
      this.calculatetotal()
  },
   (error: HttpErrorResponse) => {
    this.notificationService.error(error.error.message);
    
    
  }),
  this.customer.getCustomer().subscribe((data:any)=>{
    this.currentCustomer=data
    console.log(this.currentCustomer)
  }),(error: HttpErrorResponse) => {
    this.notificationService.error(error.error.message);
  };;
  
  
  
  
 
}

minus(x: any) {
       
  if(x.quantity!=1){
      x.quantity= x.quantity-1;;
  }
}   
plus(x:any) { 
     x.quantity=x.quantity+1;
}

updatePO(pizzaOrder:PizzaOrderModel):void{
  const pizzaOrder1=new PizzaOrderModel(pizzaOrder)
  const pizzaOrderDto=new PizzaOrderDtoModel(pizzaOrder1)
  
   this.service.updatePizzaOrder(pizzaOrderDto).subscribe((data:any)=>{
    window.location.reload()
  })
}

deletePO(pizzaOrder:PizzaOrderModel):void{
  
   this.service.deletePizzaOrder(pizzaOrder.bookingOrderId).subscribe((data:any)=>{
    window.location.reload()
  },
  (error: HttpErrorResponse) => {
    this.notificationService.error(error.error.message);
    
    
  })
}
size(x:PizzaOrderModel,y:string) {
  x.size=y
  console.log(x)
}
calculatetotal(){

 for(let i=0; i<this.pizzaOrders.length; i++){
  this.totalcost+=this.pizzaOrders[i]['totalCost'] //use i instead of 0
  console.log("total "+ this.totalcost);
  console.log(this.customer)
}

}
placeOrder(){
  

  
  this.order.orderTotal=this.totalcost
  this.order.orderCustomerId=this.currentCustomer.userId
  console.log(this.currentCustomer)
  this.order.orderDescription="Cash"
  this.order.orderType="Online"
  this.order.success=true
  for(let i=0; i<this.pizzaOrders.length; i++){
    this.order.pizzaorder.push(this.pizzaOrders[i]['bookingOrderId']) //use i instead of 0
  }
  console.log(this.order)
 this.service.placeOrder(this.order).subscribe(
  (data:any)=>{
 // window.location.reload()
})
console.log(this.currentCustomer)

 
}



}





