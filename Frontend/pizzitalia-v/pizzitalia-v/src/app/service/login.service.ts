import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginModel } from 'app/login/Login';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public username!: string;
  public password!: string;
  authenticated = false;

  constructor(private http:HttpClient,
    private router: Router){}
  
  form: FormGroup = new FormGroup({
    id: new FormControl(null),
    username: new FormControl('', Validators.required),
    password: new FormControl('',Validators.required)
    
  }); 

 

  doLogin(username: string, password: string) {
    return this.http.get(environment.hostUrl + `/user/login`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
        this.username = username;
        this.password = password;
        this.registerSuccessfulLogin(username, password);
      }));
  }

  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(username + ":" + password);
  }

  registerSuccessfulLogin(username: string, password: string) {
    localStorage.setItem('username',username);
    
  }

  loggedIn(){
    return !!localStorage.getItem('username');
    
    }

  logoutUser(){
    localStorage.removeItem('username');
    this.router.navigate(['/pizza'])
  }

  getToken(){
    return localStorage.getItem('username');
  }

  
 
  // authenticate(credentials: { username: string; password: string; }, callback: () => any) {

  //   const headers = new HttpHeaders(credentials ? {
  //       authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
  //   } : {});

  //   this.http.get('user', {headers: headers}).subscribe(response => {
  //       if (response['name']) {
  //           this.authenticated = true;
  //       } else {
  //           this.authenticated = false;
  //       }
  //       return callback && callback();
  //   });

}



