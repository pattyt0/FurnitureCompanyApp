import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Buyer } from '../models/buyer.model';
import { BuyerService } from './buyer.service';

@Component({
  templateUrl: './create-buyer.component.html',
})
export class CreateBuyerComponent implements OnInit {

  buyer: Buyer = new Buyer();
  constructor(private router:Router, private buyerService: BuyerService) { }

  createBuyer(): void {
    this.buyerService.createBuyer(this.buyer)
        .subscribe( data => {
          alert("Buyer created successfully!!!");
        })
  }

  ngOnInit(): void {
  }
}
