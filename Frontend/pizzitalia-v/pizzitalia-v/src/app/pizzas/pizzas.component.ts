import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { PizzaServiceService } from '../service/pizza-service.service';
import { PizzaModel } from './Pizza';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { PizzaOrderDtoModel } from 'app/pizza-order/PizzaOrder';
import { NotificationService } from 'app/service/notification.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { PizzaComponent } from './pizza/pizza.component';
import { PizzaDetailsComponent } from './pizza-details/pizza-details.component';
import { AuthenticationService } from 'app/service/authentication.service';
import { Customer } from 'app/register/customer';
import { LoginComponent } from 'app/login/login.component';
import { EditComponent } from 'app/register/edit/edit.component';
import { LoginService } from 'app/service/login.service';


@Component({
  selector: 'app-pizzas',
  templateUrl: './pizzas.component.html',
  styleUrls: ['./pizzas.component.css']
})
export class PizzasComponent implements OnInit {

 
  message: any;
  display!:boolean;
  permission!:boolean;
  pizzas!: PizzaModel[];
  filteredPizzas!:PizzaModel[];
  private _searchTerm!:string;
  sizes:string[]=["medium","small","large"];
  pizzaOrder!:PizzaOrderDtoModel
  pizzaDetails: Array<any> = [];
  currentCustomer!: Customer;
  vegDetails:PizzaModel[]=[];
  nonvegDetails:PizzaModel[]=[];
 
  get searchTerm():string{
    return this._searchTerm;
  }

  set searchTerm(value:string){
    this._searchTerm=value;
    this.filteredPizzas= this.filterPizzas(value);
  }

  filterPizzas(searchString:string){
    return this.pizzas.filter(pizza=>
      pizza.name.toLowerCase().indexOf(searchString.toLocaleLowerCase())!==-1);
  }
  

 
  constructor(private service:PizzaServiceService,
     private router: Router,
     public notificationService:NotificationService,
     private dialog:MatDialog,
     public auth:AuthenticationService,
     public login:LoginService,
     private http:HttpClient,
     
     ) { }
  
 
  ngOnInit(): void {
    this.display=false;
    this.service.getPizzas().subscribe((data: any) => {
      this.pizzas = data;
      this.filteredPizzas=data;
      console.log(this.filteredPizzas);
      for(let i in data){       

        data[i].quantity=1;
        if(data[i].type=='1'){
          this.vegDetails.push(data[i]);
       
        }
        if(data[i].type=='2'){
          this.nonvegDetails.push(data[i]);
        }
       
       }
  });
  this.auth.getCustomer().subscribe((data:any)=>{
    this.currentCustomer=data;
    if(this.currentCustomer.roles=='ROLE_ADMIN' || this.currentCustomer.roles=='ROLE_SUPERADMIN')
    this.permission=true;
    else 
    this.permission=false;

    console.log(this.permission);
  })
   
  

}


all(){
  this.filteredPizzas=this.pizzas;
}
veg(){
  this.filteredPizzas=this.vegDetails;
}
nonveg(){
  this.filteredPizzas=this.nonvegDetails;
}
  
minus(x: any) {
       
  if(x.quantity!=1){
      x.quantity= x.quantity-1;
  }
}   
plus(x:any) {
     x.quantity=x.quantity+1;
}

placeOrder(pizza:PizzaModel){
  this.pizzaOrder =new PizzaOrderDtoModel(pizza);
  console.log(this.pizzaOrder)
  this.service.orderPizza(this.pizzaOrder).subscribe((data:any)=>{

  })
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
  dialogConfig.width= "60%";
  dialogConfig.height="60%";
  this.dialog.open(PizzaComponent,dialogConfig);
 }

 enlargePizza(pizza:any){
  this.service.set(pizza.id);
  const dialogConfig = new MatDialogConfig();
  dialogConfig.autoFocus=true;
  dialogConfig.width= "50%";
  dialogConfig.height="70%";
  this.dialog.open(PizzaDetailsComponent,dialogConfig);
}


 refresh(): void {
  window.location.reload();
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
  this.auth.form.setValue(this.currentCustomer);
  const dialogConfig = new MatDialogConfig();
  // dialogConfig.disableClose = true;
  dialogConfig.autoFocus=true;
  dialogConfig.width= "40%";
  dialogConfig.height="70%";
  this.dialog.open(EditComponent,dialogConfig);
}



}


