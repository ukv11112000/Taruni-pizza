import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from 'app/service/order.service';
import { OrderModel } from './Order';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orders!:OrderModel[];
  constructor(private service:OrderService,private router:Router) { }

  ngOnInit(): void {
    
    this.service.getOrders().subscribe((data:any)=>{
      this.orders=data;
      console.log(this.orders)
  });
 // this.service.validateOrders().subscribe();
}
}
