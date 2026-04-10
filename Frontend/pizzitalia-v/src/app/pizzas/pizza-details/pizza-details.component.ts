import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PizzaOrderDtoModel } from 'app/pizza-order/PizzaOrder';
import { NotificationService } from 'app/service/notification.service';
import { PizzaServiceService } from 'app/service/pizza-service.service';
import { PizzaModel } from '../Pizza';
import { PizzaComponent } from '../pizza/pizza.component';
import { PizzasComponent } from '../pizzas.component';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'app/service/authentication.service';
import { CoupanComponent } from 'app/coupan/coupan.component';
import { CouponService } from 'app/service/coupon.service';
import { CoupanModel } from './Coupan';
import { Customer } from 'app/register/customer';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-pizza-details',
  templateUrl: './pizza-details.component.html',
  styleUrls: ['./pizza-details.component.css']
})
export class PizzaDetailsComponent implements OnInit {

 
  id!:number;
  couponId!:number;
  key=1;
  message: any;
  pizzaDetail!: PizzaModel[];
  pizza :any;
  permission!:boolean;
  sizes:string[]=["medium","small","large"];
  pizzaOrder!:PizzaOrderDtoModel;
  details!:PizzaModel;
  coupanDetails: any;
  coupan! :CoupanModel[];
  currentCustomer!: Customer;
  
 
 
  constructor(private service:PizzaServiceService,
     private router: Router,
     public notificationService:NotificationService,
     private dialog:MatDialog,
     public auth: AuthenticationService,
     private coupon:CouponService
    ) { }
  
 
  ngOnInit(): void {
    this.id=this.service.get();
    this.key=this.id;
    console.log(this.id);
    
    this.service.getPizza(this.key).subscribe((data:any) => {
      this.pizzaDetail = data[this.key];
      for(let i in this.pizzaDetail){
        this.pizzaDetail[i].quantity=1;
      }
      this.details=data;
      this.coupan=this.details.coupan;
     console.log(this.details);
     console.log(this.coupan);
     
     
    }),(error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    };

    this.auth.getUser().subscribe((data:any)=>{
      this.currentCustomer=data;
      if(this.currentCustomer.roles=='ROLE_ADMIN' || this.currentCustomer.roles=='ROLE_SUPERADMIN')
      this.permission=true;
      else 
      this.permission=false;
  
      console.log(this.permission);
    }),(error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    };


  }


  minus(x: any) {
        
    if(x.quantity!=1){
        x.quantity= x.quantity-1;;
    }
  }   
  plus(x:any) {
      x.quantity=x.quantity+1;
  }
  getcoupon(id:number){
    this.couponId=id;
  }
  
  placeOrder(pizza:PizzaModel){
    this.pizzaOrder =new PizzaOrderDtoModel(pizza);
    if(this.couponId!=null) {this.pizzaOrder.coupan=this.couponId; console.log(this.couponId)}
    else this.pizzaOrder.coupan=1;
    console.log(this.pizzaOrder)
    this.service.orderPizza(this.pizzaOrder).subscribe((data:any)=>{
      window.location.reload()
    })
    this.notificationService.success(':: Deleted Successfully');
  }
  size(x:PizzaModel,y:string) {
    x.size=y
    console.log(x)
  }

  deletePizza(id:number){
    this.service.deletePizza(id).subscribe((data:any)=>{
    })
    this.notificationService.success(':: Deleted Successfully');
    this.refresh();
  }
  
  editPizza(pizza:any){
  
    this.service.populateForm(pizza);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus=true;
    dialogConfig.width= "40%";
    dialogConfig.height="60%";
    this.dialog.open(PizzaComponent,dialogConfig);
  
  }



  addCoupon(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus=true;
    dialogConfig.width= "40%";
    dialogConfig.height="60%";
    this.dialog.open(CoupanComponent,dialogConfig);
    this.coupon.pizzaId(this.id);
    
  }

  editCoupon(coupon:any){
    
    this.coupon.populateForm(coupon);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus=true;
    dialogConfig.width= "40%";
    dialogConfig.height="50%";
    this.dialog.open(CoupanComponent,dialogConfig);
   
  }

  deleteCoupon(id:number){
    this.coupon.delete(id).subscribe((data:any)=>{
    })
    this.notificationService.success(':: Deleted Successfully');
    this.refresh();
  }

  refresh(): void {
    window.location.reload();
  } 

  


}