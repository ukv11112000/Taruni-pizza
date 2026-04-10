import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PizzaServiceService } from './service/pizza-service.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from './material/material.module';
import { PizzaOrderComponent } from './pizza-order/pizza-order.component';
import { PizzaOrderService } from './service/pizza-order.service';
import { CoupanComponent } from './coupan/coupan.component';
import { PizzaComponent } from './pizzas/pizza/pizza.component';
import { PizzaDetailsComponent } from './pizzas/pizza-details/pizza-details.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EditComponent } from './register/edit/edit.component';
import { OrderComponent } from './order/order.component';
import { CustomersComponent } from './customers/customers.component';





@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    CoupanComponent,
    PizzaDetailsComponent,
    LoginComponent,
    RegisterComponent,
    EditComponent,
    OrderComponent,
    CustomersComponent,
    
    
   
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    FlexLayoutModule,
    BrowserAnimationsModule ,
    MaterialModule,
    ReactiveFormsModule,
    
  ],
  providers: [PizzaServiceService],
  bootstrap: [AppComponent],
  entryComponents :[PizzaComponent]
})
export class AppModule { }
