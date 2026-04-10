import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthenticationService } from 'app/service/authentication.service';
import { NotificationService } from 'app/service/notification.service';
import { PizzaServiceService } from 'app/service/pizza-service.service';
import { upperFirst } from 'lodash';



@Component({
  selector: 'app-pizza',
  templateUrl: './pizza.component.html',
  styleUrls: ['./pizza.component.css']
})
export class PizzaComponent implements OnInit {
  message: Object | undefined;
  //pizza:PizzaModel= new PizzaModel(0,"","","",0,"");
  constructor( public service:PizzaServiceService,
    public notificationService:NotificationService,
    public dialogRef:MatDialogRef<PizzaComponent>,
    public auth:AuthenticationService) { }
  
  
  
  ngOnInit(): void {
   
  }

  onClear() {
    this.service.form.reset();
    this.service.initializeFormGroup();
  }
 
  onSubmit(){
    if(this.service.form.valid){ 

    const pizza= (this.service.form.value);
    if(this.service.form.value.id==null){
      if(pizza.type=='1')
      pizza.type="veg";
      if(pizza.type=='2')
      pizza.type="non-veg";
     
      this.service.insertPizza(pizza).subscribe((data:any)=>{
      });
    }
    else{
      this.service.updatePizza(pizza).subscribe((data:any)=>{
    
      })

    }
      this.notificationService.success(':: Submitted successfully');
      this.onClose();
      this.refresh();
    
    }
    else 
    return ;
  }
  
  
  onClose(){
    this.service.form.reset();
    this.service.initializeFormGroup();
    this.dialogRef.close();
  }


  refresh(): void {
    window.location.reload();
}

admin(){
  let customer= this.auth.form.value;
  let role= customer.roles;
  if(role=='ROLE_ADMIN' || role=="ROLE_SUPERADMIN")
  return true;
  else 
  return false;
}

}
