import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CouponService } from 'app/service/coupon.service';
import { NotificationService } from 'app/service/notification.service';

@Component({
  selector: 'app-coupan',
  templateUrl: './coupan.component.html',
  styleUrls: ['./coupan.component.css']
})
export class CoupanComponent implements OnInit {
  getCoupon(key: number) {
    throw new Error('Method not implemented.');
  }

  constructor(public service:CouponService ,
    public notificationService:NotificationService,
    private dialogRef:MatDialog) { }

  get data() { return this.service.form.controls; }
  

  ngOnInit(): void {
  }
  onSubmit(){
    if(this.service.form.valid){ 

      const coupan= (this.service.form.value);
      if(this.service.form.value.coupanid==null){
      
        this.service.insertCoupon(coupan).subscribe((data:any)=>{
        });
      }
      else{
        this.service.updateCoupon(coupan).subscribe((data:any)=>{
      
        })
  
      }
        this.notificationService.success(':: Submitted successfully');
        this.onClose();
        window.location.reload()
      }
      else 
      return ;
    }

    onClose(){
      this.service.form.reset();
      this.service.initializeFormGroup();
      
    }
  
  
    refresh(): void {
      window.location.reload();
  }
  

 


}
