import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
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
  
  constructor(private service:CustomerService,
    private notificationService:NotificationService) { }
  
  ngOnInit(): void {
    this.service.getUsers().subscribe((data: any) => {
      this.customers = data;
      console.log("hey");
      console.log(data);
     
    })
  } 
  delete(customer:UserModel):void{
    this.service.delete(customer.userId).subscribe(
      (data:any)=>{
        this.notificationService.error(data);
      },
      (error:HttpErrorResponse)=>{
       
        this.notificationService.error(error.error.message);

    })
  }
  

}
