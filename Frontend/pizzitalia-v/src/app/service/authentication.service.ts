import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from 'app/register/customer';
import { FormControl, FormGroup, Validators,FormBuilder, Form } from '@angular/forms';
import { environment } from 'environments/environment';
import * as _ from 'lodash';
import { UserModel } from 'app/customers/User';
import { TokenStorageService } from 'app/token-storage.service';
import { JwtClientService } from 'app/jwt-client.service';


@Injectable({
  providedIn: 'root'
})


export class AuthenticationService {

  registerForm !: FormGroup; 
  public username!: string;
  public password!: string;
  obj!:Customer;
  currentCustomer= this.tokenStorage.getUser();
  customer: any;
  user:any
 
  

  constructor(private _http: HttpClient,private formBuilder: FormBuilder,
    private tokenStorage: TokenStorageService, private jwt:JwtClientService) { 
    
    this.registerForm = this.formBuilder.group({
      id:[null],
      name: ['', Validators.required],
      email: ['', [Validators.required,Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      phone: ['', [Validators.required ,Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      username: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      address:['',Validators.required],
      roles:[''],
      order:['']
    },
    
    {
      validator: this.PasswordValidation('password', 'confirmPassword')
    });
    
    
  }

  form:FormGroup= new FormGroup({
    userId:new FormControl(),
    name: new FormControl ('',Validators.required),
    email:new FormControl('',[Validators.required,Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
    phone: new FormControl('',[Validators.required ,Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]),
    username: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required),
    roles:new FormControl(''),
    order: new FormControl(''),
    address: new FormControl('',Validators.required)

    
  });
  get dataa() { return this.form.controls; }
  
  get data() { return this.registerForm.controls; }

  PasswordValidation(controlName:string,macthingControlName:string){
      return(formGroup:FormGroup)=>{
      const control=formGroup.controls[controlName];
      const matchingControl=formGroup.controls[macthingControlName];
      if(matchingControl.errors && !matchingControl.errors.PasswordValidation){
        return;
      }
      if(control.value!=matchingControl.value){
        matchingControl.setErrors({PasswordValidation:true})
      }
      else{
        matchingControl.setErrors(null)
      }
    }

  }

 
  public registeruser(customer : Customer):Observable<any>{
    let body = JSON.stringify({customer});
    let p=JSON.parse(body);
    this.username = customer.username
    this.password = customer.password
    this.registerSuccessfulLogin(this.username,this.password);
    return this._http.post<any>(environment.customerUrl+"add",p);
  }


  registerSuccessfulLogin(username: string, password: string) {
    this.tokenStorage.getUser();
  }
  

  getCustomer(): Observable<Customer[]>{

    return this._http.get<Customer[]>(environment.customerUrl+"currentCustomer/"+this.currentCustomer);

  }

  getUser():Observable<UserModel[]>{
    return this._http.get<UserModel[]>(environment.userUrl+"currentUser/"+this.currentCustomer);
  }

  admin(){
    this.getCustomer().subscribe((data:any)=>{
      this.customer=data;
    })
   
    if(this.customer.roles=='ROLE_SUPERADMIN' || this.customer.roles=='ROLE_ADMIN')
    return true;
    else 
    return  false;
   
  }

  public update(customer:any){
    let body = JSON.stringify({customer});
    let p = JSON.parse(body);
    console.log(p);
    return this._http.put(environment.customerUrl+"update",p);
  }

 
}
