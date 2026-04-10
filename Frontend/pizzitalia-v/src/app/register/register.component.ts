import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from 'app/service/authentication.service';
import { Customer } from './customer';
import { HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from 'app/service/notification.service';




@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm !: FormGroup;
  title="REGISTER HERE";
  customerObj !:Customer;
  
  

  constructor(private formBuilder: FormBuilder, private router: Router, private _snackBar: MatSnackBar,
    public _service:AuthenticationService,
    public notificationService:NotificationService,) { }
  get data() { return this._service.registerForm.controls; }

  ngOnInit() {
    
    let edit =false;
    
}
  onSubmit() {
    if (this._service.registerForm.invalid) {
      return;
    } else {
      this.customerObj=this._service.registerForm.value;
     
      const name=this.data.name.value;
      const  username=this.data.username.value;
      const  password=this.data.password.value;
      const  email=this.data.email.value;
      const  phone=this.data.phone.value;
      const roles = this.data.roles.value;
      const confirmPassword = this.data.confirmPassword.value;


      let customer=new Customer(name,username,password,email,phone,roles);
      console.log(customer);
     
      this.customerObj=customer;
      console.log(this.customerObj);
      
      this._service.registeruser(customer).subscribe(
        data=>console.log("response received"),
        error=>console.log("error")
      ),(error: HttpErrorResponse) => {
        this.notificationService.error(error.error.message);
      };
      
    
      this._snackBar.open('Register Successfully', 'Success', {
        duration: 2000,
      });
      
     
    this.router.navigate(['/login'])
  
    }
  
    
  }

}