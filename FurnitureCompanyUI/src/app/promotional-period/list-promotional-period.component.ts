import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import { PromotionalPeriod } from '../models/promotional-period.model';
import {PromotionalPeriodService} from "../promotional-period/promotional-period.service";

@Component({
    templateUrl: './list-promotional-period.component.html',
})
export class ListPromotionalPeriodComponent implements OnInit {

    promotionalPeriods: PromotionalPeriod[];
    constructor(private router: Router, private furnitureService: PromotionalPeriodService) { }

    ngOnInit() {
        this.furnitureService.getPromotionalPeriod()
            .subscribe(data => {
                this.promotionalPeriods = data;
            });
    }
}
