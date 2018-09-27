import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';

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

    onDateSelection(date: NgbDate) {
        if (!this.fromDate && !this.toDate) {
            this.fromDate = date;
        } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
            this.toDate = date;
        } else {
            this.toDate = null;
            this.fromDate = date;
        }
    }
}
