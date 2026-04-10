import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { PizzaServiceService } from '../service/pizza-service.service';
import { PizzaModel } from './Pizza';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
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
import { TokenStorageService } from 'app/token-storage.service';

@Component({
  selector: 'app-pizzas',
  templateUrl: './pizzas.component.html',
  styleUrls: ['./pizzas.component.css']
})
export class PizzasComponent implements OnInit {

  message: any;
  navbar = false;
  display!: boolean;
  permission!: boolean;
  pizzas!: PizzaModel[];
  filteredPizzas!: PizzaModel[];
  private _searchTerm!: string;
  sizes: string[] = ['medium', 'small', 'large'];
  pizzaOrder!: PizzaOrderDtoModel;
  pizzaDetails: Array<any> = [];
  currentCustomer!: Customer;
  superadmin = true;
  vegDetails: PizzaModel[] = [];
  nonvegDetails: PizzaModel[] = [];
  currentUser: any;

  get searchTerm(): string {
    return this._searchTerm;
  }

  set searchTerm(value: string) {
    this._searchTerm = value;
    this.filteredPizzas = this.filterPizzas(value);
  }

  filterPizzas(searchString: string) {
    return this.pizzas.filter(pizza =>
      pizza.name.toLowerCase().indexOf(searchString.toLowerCase()) !== -1
    );
  }

  constructor(
    private service: PizzaServiceService,
    private router: Router,
    public notificationService: NotificationService,
    private dialog: MatDialog,
    public auth: AuthenticationService,
    public login: LoginService,
    private http: HttpClient,
    private tokenStorage: TokenStorageService,
  ) { }

  ngOnInit(): void {

    this.router.navigate(['/pizza']);
    this.display = false;
    this.vegDetails = [];
    this.nonvegDetails = [];

    this.service.getPizzas().subscribe(
      (data: any) => {
        this.pizzas = data;
        this.filteredPizzas = data;
        console.log(this.filteredPizzas);

        for (let i in data) {
          data[i].quantity = 1;

          const pizzaType = (data[i].type || '')
            .toString()
            .toLowerCase()
            .replace(/\s+/g, '')
            .replace('-', '');

          if (pizzaType === '1' || pizzaType === 'veg') {
            this.vegDetails.push(data[i]);
          }

          if (pizzaType === '2' || pizzaType === 'nonveg') {
            this.nonvegDetails.push(data[i]);
          }
        }
      },
      (error: HttpErrorResponse) => {
        this.notificationService.error(error.error.message);
      }
    );

    this.auth.getUser().subscribe(
      (data: any) => {
        this.currentUser = data;
        if (this.currentUser.roles == 'ROLE_ADMIN' || this.currentUser.roles == 'ROLE_SUPERADMIN') {
          this.permission = true;
          this.superadmin = false;
        } else {
          this.permission = false;
        }
      },
      (error: HttpErrorResponse) => {
        this.notificationService.error(error.error.message);
      }
    );
  }

  all() {
    this.filteredPizzas = this.pizzas;
  }

  veg() {
    this.filteredPizzas = this.vegDetails;
  }

  nonveg() {
    this.filteredPizzas = this.nonvegDetails;
  }

  minus(x: any) {
    if (x.quantity != 1) {
      x.quantity = x.quantity - 1;
    }
  }

  plus(x: any) {
    x.quantity = x.quantity + 1;
  }

  placeOrder(pizza: PizzaModel) {
    this.pizzaOrder = new PizzaOrderDtoModel(pizza);

    console.log(this.pizzaOrder);
    this.service.orderPizza(this.pizzaOrder).subscribe(
      (data: any) => {

      },
      (error: HttpErrorResponse) => {
        this.notificationService.error(error.error.message);
      }
    );
    this.notificationService.success(':: Order Placed Successfully');
  }

  size(x: PizzaModel, y: string) {
    x.size = y;
    console.log(x);
  }

  deletePizza(id: number) {
    this.service.deletePizza(id).subscribe(
      (data: any) => {

      },
      (error: HttpErrorResponse) => {
        this.notificationService.error(error.error.message);
      }
    );
    this.notificationService.success(':: Deleted Successfully');
    this.refresh();
  }

  editPizza(pizza: any) {
    this.service.populateForm(pizza);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    dialogConfig.height = '60%';
    this.dialog.open(PizzaComponent, dialogConfig);
  }

  enlargePizza(pizza: any) {
    this.service.set(pizza.id);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '70%';
    this.dialog.open(PizzaDetailsComponent, dialogConfig);
  }

  refresh(): void {
    window.location.reload();
  }

  onCreate() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    dialogConfig.height = '60%';
    this.dialog.open(PizzaComponent, dialogConfig);
  }

  displayName() {
    return this.tokenStorage.getUser();
  }

  editForm() {
    console.log(this.currentCustomer);

    this.auth.form.setValue(this.currentCustomer);
    const dialogConfig = new MatDialogConfig();

    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.height = '70%';
    this.dialog.open(EditComponent, dialogConfig);
  }
}
