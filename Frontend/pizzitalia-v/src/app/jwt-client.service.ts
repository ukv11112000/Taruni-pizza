import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})


export class JwtClientService {
 

  httpOptions = {
  headers: new HttpHeaders({
      'Content-Type':  'application/json',
       Authorization: 'Bearer ' +this.tokenStorage.getToken()
  })
};
  constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService,) { }



}