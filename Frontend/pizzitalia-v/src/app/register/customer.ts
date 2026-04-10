import { UserModel } from "app/customers/User";

export class Customer extends UserModel{

  name:string;
  username:string;
  password:string;
  email:string;
  
  roles:string;
  constructor(name:string,username:string,password:string,email:string,phone:number,roles:string){
   
    super();
   this.name=name;
   this.username=username;
   this.password=password;
   this.email=email;
   
   this.roles= roles;
  
  }
 }
