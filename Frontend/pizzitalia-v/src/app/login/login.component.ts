import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ResolvedReflectiveFactory } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'app/service/authentication.service';
import { LoginService } from 'app/service/login.service';
import { NotificationService } from 'app/service/notification.service';
import { PizzaServiceService } from 'app/service/pizza-service.service';
import { TokenStorageService } from 'app/token-storage.service';
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
  roles: any;
  token=''
  TokenArray=''


  


  constructor(
    public service:LoginService,
    public notificationService:NotificationService,
    private route: ActivatedRoute,
    private router: Router,
    private http:HttpClient,
    private tokenStorage: TokenStorageService,
    private auth: AuthenticationService ) { }

    get data() { return this.service.form.controls; }
  

  ngOnInit(): void {
    
    
  }

  onSubmit(){
    if(this.service.form.valid){

      let username=this.service.form.value.username
      let password = this.service.form.value.password
     
      this.service.login(username,password).subscribe(
        data => {
          this.token=JSON.stringify(data)
          console.log(this.token)
      
         
      this.tokenStorage.saveToken(this.token.substring(8,this.token.length-2));
      this.tokenStorage.saveUser(username);
      this.roles = this.tokenStorage.getUser();
      console.log(this.roles);
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful';
     
      this.router.navigate(['/pizza'])
      .then(() => {
       window.location.reload();
      });

      
      
        
      }, (error: HttpErrorResponse) => {
       
        this.invalidLogin = true;
        this.loginSuccess = false;
      }),(error: HttpErrorResponse) => {
        this.notificationService.error(error.error.message);
      };;
    }
  
  }


  refresh(): void {
    window.location.reload();
  
}

}
