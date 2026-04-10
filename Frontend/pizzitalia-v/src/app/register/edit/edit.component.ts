import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'app/service/authentication.service';
import { NotificationService } from 'app/service/notification.service';
import { Customer } from '../customer';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  customer !:Customer;
  currentCustomer: any;

  constructor(public _service:AuthenticationService,
    public notificationService:NotificationService,) {}

  ngOnInit(): void {
   
    this._service.getCustomer().subscribe((data:any)=>{
      this.currentCustomer=data;
    }),(error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    };
  
  }

  onSubmit(){
  
    console.log(this._service.form.value);
    let customer=this._service.form.value;
    this._service.update(customer).subscribe((data:any)=>{
    this.refresh();
    }),(error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    }; 
  }
  refresh(): void {
    window.location.reload();
}
  

 

}
