import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'app/service/authentication.service';
import { Customer } from '../customer';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  customer !:Customer;
  currentCustomer: any;

  constructor(public _service:AuthenticationService) {}

  ngOnInit(): void {
   
    this._service.getCustomer().subscribe((data:any)=>{
      this.currentCustomer=data;
    })
  
  }

  onSubmit(){
  
    console.log(this._service.form.value);
    let customer=this._service.form.value;
    this._service.update(customer).subscribe((data:any)=>{
    this.refresh();
    })  
  }
  refresh(): void {
    window.location.reload();
}
  

 

}
