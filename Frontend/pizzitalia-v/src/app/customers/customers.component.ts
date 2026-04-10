import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'app/service/customer.service';
import { NotificationService } from 'app/service/notification.service';
import { UserModel } from './User';


@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers!:UserModel [];
  navbar=true;
  currentUser: any;
  customer: any;
  
  constructor(private service:CustomerService,
    private notificationService:NotificationService,
    private router: Router) { }
  
  ngOnInit(): void {
    
    this.service.getUsers().subscribe((data: any) => {
    this.customers=data;
    console.log(this.customers);
    console.log("from customer");
    },
    (error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    })
  }
   
  delete(customer:UserModel):void{
    this.service.delete(customer.userId).subscribe(
      (data:any)=>{
        this.notificationService.error(data);
      },
      (error:HttpErrorResponse)=>{
       
        this.notificationService.error("Superadmin cannot be deleted");

    })
  }

  getCurrentCustomer(){
    this.customer.getCustomer().subscribe((data:any)=>{
      this.currentUser=data;
      console.log(data);
    }),(error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    
     ;
    };
  }
  
 display(){
   console.log(this.currentUser);
  if(this.currentUser.roles==="ROLE_SUPERADMIN"){
    console.log("admin");
    this.service.getUsers();
    (error:HttpErrorResponse)=>{
     
      this.notificationService.error(error.error.message);

  }
  }
  else{
   this.router.navigate(['/']);

  }
 }
  

}
