import { Component, OnInit } from '@angular/core';
import { LoginComponent } from 'app/login/login.component';
import { AuthenticationService } from 'app/service/authentication.service';
import { LoginService } from 'app/service/login.service';
import { TokenStorageService } from 'app/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public service:LoginService,private tokenStorage:TokenStorageService) { }

  ngOnInit(): void {
  }

  displayName(){
    return this.tokenStorage.getUser();
  }

}
