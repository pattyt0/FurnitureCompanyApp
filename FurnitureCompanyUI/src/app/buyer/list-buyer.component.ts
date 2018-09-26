import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import { Buyer } from '../models/buyer.model';
import {BuyerService} from "../buyer/buyer.service";

@Component({
    templateUrl: './list-buyer.component.html',
})
export class ListBuyerComponent implements OnInit {

    buyers: Buyer[];
    constructor(private router: Router, private buyerService: BuyerService) { }

    ngOnInit() {
        this.buyerService.getBuyers()
            .subscribe(data => {
                this.buyers = data;
            });
    }
}
