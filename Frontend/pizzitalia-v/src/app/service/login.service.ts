import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtClientService } from 'app/jwt-client.service';
import { LoginModel } from 'app/login/Login';
import { TokenStorageService } from 'app/token-storage.service';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})


export class LoginService {

  public username!: string;
  public password!: string;
  authenticated = false;
  
  
  

  constructor(private http:HttpClient,
    private router: Router,  
    private tokenStorage: TokenStorageService,
    private jwt:JwtClientService){}
  
  form: FormGroup = new FormGroup({
    id: new FormControl(null),
    username: new FormControl('', Validators.required),
    password: new FormControl('',Validators.required)
    
  }); 


login( username: any,password: any ): Observable<any> {
  return this.http.post('http://localhost:8080/authenticate', {
    username: username,
    password: password
  }, httpOptions);
}

loggedIn(){
  
  return !!sessionStorage.getItem('auth-user');
}

logoutUser(){
  
  this.tokenStorage.signOut();
  window.location.reload();
  
}

getToken(){
  return this.tokenStorage.getUser();
}

}

