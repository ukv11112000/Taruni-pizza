import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from 'app/register/customer';
import { FormControl, FormGroup, Validators,FormBuilder, Form } from '@angular/forms';
import { environment } from 'environments/environment';
import * as _ from 'lodash';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  registerForm !: FormGroup; 
  public username!: string;
  public password!: string;
  obj!:Customer;
  currentCustomer=localStorage.getItem("username");
  customer: any;
  Customer: any;

  constructor(private _http: HttpClient,private formBuilder: FormBuilder) { 
    
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
    console.log("HEY"+customer);
    let body = JSON.stringify({customer});
    console.log("heyyyyyy youuuuuu")
    let p=JSON.parse(body);
    this.username = customer.username
    this.password = customer.password
    this.registerSuccessfulLogin(this.username,this.password);
    console.log("hey customer"+p);
    return this._http.post<any>("http://localhost:8080/customer/add",p);
  }


  registerSuccessfulLogin(username: string, password: string) {
    localStorage.setItem('username',username);
    
  }
  

  getCustomer(): Observable<Customer[]>{
    return this._http.get<Customer[]>("http://localhost:8080/customer/currentCustomer/"+this.currentCustomer);

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
    return this._http.put("http://localhost:8080/customer/update",p);
  }

 
}
