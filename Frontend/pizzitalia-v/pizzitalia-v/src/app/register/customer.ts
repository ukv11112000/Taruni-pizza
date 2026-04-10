export class Customer {

  name:string;
  username:string;
  password:string;
  email:string;
  phone:number;
  roles:string;
  constructor(name:string,username:string,password:string,email:string,phone:number,roles:string){
   
   this.name=name;
   this.username=username;
   this.password=password;
   this.email=email;
   this.phone=phone;
   this.roles= roles;
  
  }
 }
