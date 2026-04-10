import { Component, OnInit } from '@angular/core';
import { LoginComponent } from 'app/login/login.component';
import { AuthenticationService } from 'app/service/authentication.service';
import { LoginService } from 'app/service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public service:LoginService) { }

  ngOnInit(): void {
  }

  displayName(){
    return localStorage.getItem("username");
  }

}
