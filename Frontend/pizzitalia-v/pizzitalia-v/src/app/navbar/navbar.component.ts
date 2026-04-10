import { Component, OnInit } from '@angular/core';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { PizzaComponent } from 'app/pizzas/pizza/pizza.component';
import { LoginService } from 'app/service/login.service';
import { PizzaServiceService } from 'app/service/pizza-service.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/finally';
import { AuthenticationService } from 'app/service/authentication.service';
import { RegisterComponent } from 'app/register/register.component';
import { EditComponent } from 'app/register/edit/edit.component';
import { Customer } from 'app/register/customer';
import * as _ from 'lodash';
import { PizzaModel } from 'app/pizzas/Pizza';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  
  pizzas!: PizzaModel[];
  currentCustomer!:Customer;
  toggle=false;

  constructor(private dialog:MatDialog,
    public service:LoginService,
    private http: HttpClient,
    private router: Router,
    private pizza:PizzaServiceService,
    private customer:AuthenticationService) { 
   
    }

  ngOnInit(): void {
    this.customer.getCustomer().subscribe((data:any)=>{
      this.currentCustomer=data;
    })
    
  }


  onCreate(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus=true;
    dialogConfig.width= "60%";
    dialogConfig.height="60%";
    this.dialog.open(PizzaComponent,dialogConfig);
  }

  displayName(){
    return localStorage.getItem("username");
  }

  editForm(){
   
    console.log(this.currentCustomer)
    
   // this.customer.registerForm.setValue(_.omit(this.customer,'confirmPassword','userId'))
    this.customer.form.setValue(this.currentCustomer);
    const dialogConfig = new MatDialogConfig();
    // dialogConfig.disableClose = true;
    dialogConfig.autoFocus=true;
    dialogConfig.width= "40%";
    dialogConfig.height="70%";
    this.dialog.open(EditComponent,dialogConfig);
  }


  
  
  
  
 
  
  
}







