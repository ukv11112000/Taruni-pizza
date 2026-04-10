import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ResolvedReflectiveFactory } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'app/service/login.service';
import { NotificationService } from 'app/service/notification.service';
import { PizzaServiceService } from 'app/service/pizza-service.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  

  errorMessage = 'Invalid Credentials';
  successMessage!: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(
    public service:LoginService,
    public notificationService:NotificationService,
    private route: ActivatedRoute,
    private router: Router,
    private http:HttpClient ) { }

  ngOnInit(): void {
    sessionStorage.setItem('token', '');
  }

  onSubmit(){
    if(this.service.form.valid){

      let username=this.service.form.value.username
      let password = this.service.form.value.password
      this.service.doLogin(username,password).subscribe((result: any) => {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful';
      console.log("hruuu");
      this.router.navigate(['/pizza'])
        
      }, () => {
        console.log("wrouuu");
        this.invalidLogin = true;
        this.loginSuccess = false;
      });
    }
  }
  refresh(): void {
    window.location.reload();
}

}
