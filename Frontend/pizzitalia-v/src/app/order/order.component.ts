import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'app/register/customer';
import { AuthenticationService } from 'app/service/authentication.service';
import { NotificationService } from 'app/service/notification.service';
import { OrderService } from 'app/service/order.service';
import { OrderModel } from './Order';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orders!:OrderModel[];
  currentCustomer!:Customer;
  navbar=true;
  constructor(private service:OrderService,private router:Router,public notificationService:NotificationService,private customer:AuthenticationService) { }

  ngOnInit(): void {
    
    this.customer.getCustomer().subscribe((data:any)=>{
      this.currentCustomer=data
      console.log(this.currentCustomer)
      this.orders=data.order;
    }),(error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    };

}
}
