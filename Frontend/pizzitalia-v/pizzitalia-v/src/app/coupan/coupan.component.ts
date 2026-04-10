import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-coupan',
  templateUrl: './coupan.component.html',
  styleUrls: ['./coupan.component.css']
})
export class CoupanComponent implements OnInit {
  getCoupon(key: number) {
    throw new Error('Method not implemented.');
  }

  constructor() { }

  ngOnInit(): void {
  }

}
