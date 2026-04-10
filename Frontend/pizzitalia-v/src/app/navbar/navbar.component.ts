import { Component, Input, OnInit } from '@angular/core';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { PizzaComponent } from 'app/pizzas/pizza/pizza.component';
import { LoginService } from 'app/service/login.service';
import { PizzaServiceService } from 'app/service/pizza-service.service';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import 'rxjs/add/operator/finally';
import { AuthenticationService } from 'app/service/authentication.service';
import { RegisterComponent } from 'app/register/register.component';
import { EditComponent } from 'app/register/edit/edit.component';
import { Customer } from 'app/register/customer';
import * as _ from 'lodash';
import { PizzaModel } from 'app/pizzas/Pizza';
import { UserModel } from 'app/customers/User';
import { NotificationService } from 'app/service/notification.service';
import { TokenStorageService } from 'app/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  
  pizzas!: PizzaModel[];
  currentCustomer!:Customer;
  currentUser!:UserModel;
  toggle=false;
  currentProfile:any;
  permission!:boolean;
 
  
  @Input() data: any;
  superadmin=true;

  constructor(private dialog:MatDialog,
    public service:LoginService,
    private http: HttpClient,
    private router: Router,
    private pizza:PizzaServiceService,
    private customer:AuthenticationService,
    public auth:AuthenticationService,
    public notificationService:NotificationService,
    private tokenStorage: TokenStorageService
    ) { 
   
    }

  ngOnInit(): void {
    this.currentProfile = this.tokenStorage.getUser();
    this.customer.getCustomer().subscribe((data:any)=>{
      this.currentCustomer=data;
    }),(error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    };;
    
    this.auth.getUser().subscribe((data:any)=>{
      this.currentUser=data;
    if(this.currentUser.roles=='ROLE_ADMIN' || this.currentUser.roles=='ROLE_SUPERADMIN'){
      this.permission=true;
      this.superadmin=false;
    }
      else 
      this.permission=false;
  
    }),(error: HttpErrorResponse) => {
      this.notificationService.error(error.error.message);
    };
     
    
  }


  onCreate(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus=true;
    dialogConfig.width= "60%";
    dialogConfig.height="60%";
    this.dialog.open(PizzaComponent,dialogConfig);
  }

  displayName(){
    return this.tokenStorage.getUser();
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







