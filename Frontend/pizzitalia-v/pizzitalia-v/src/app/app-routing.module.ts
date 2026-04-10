import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoupanComponent } from './coupan/coupan.component';
import { CustomersComponent } from './customers/customers.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { OrderComponent } from './order/order.component';
import { PizzaOrderComponent } from './pizza-order/pizza-order.component';
import { PizzaDetailsComponent } from './pizzas/pizza-details/pizza-details.component';
import { PizzaComponent } from './pizzas/pizza/pizza.component';
import { PizzasComponent } from './pizzas/pizzas.component';
import { EditComponent } from './register/edit/edit.component';
import { RegisterComponent } from './register/register.component';



const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'pizza', component:PizzasComponent},
  {path:'pizza/add', component:PizzaComponent},
  {path:'pizzaorder', component:PizzaOrderComponent},
  {path:'login',component:LoginComponent},
  {path: 'register', component: RegisterComponent },
  {path: 'user/edit', component: EditComponent },
  {path:'orderhistory',component:OrderComponent},
  {path:'coupon', component:CoupanComponent},
  {path:'customers',component:CustomersComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents =[HomeComponent,PizzaComponent,PizzasComponent,NavbarComponent,PizzaOrderComponent]
