import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { PromotionalPeriod } from '../models/promotional-period.model';
import { PromotionalPeriodService } from './promotional-period.service';

@Component({
    templateUrl: './create-promotional-period.component.html',
})
export class CreatePromotionalPeriodComponent implements OnInit {
    promotionalPeriod: PromotionalPeriod = new PromotionalPeriod();
    constructor(private router:Router, private promotionalPeriodService: PromotionalPeriodService) { }

    ngOnInit(): void {
    }

    createPromotionalPeriod(): void {
        this.promotionalPeriodService.createPromotionalPeriod(this.promotionalPeriod)
            .subscribe( data => {
                alert("Promotional Period created successfully!!!");
            })
    }

}
