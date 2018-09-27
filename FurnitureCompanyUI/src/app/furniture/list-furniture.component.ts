import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import { Furniture } from '../models/furniture.model';
import {FurnitureService} from "../furniture/furniture.service";

@Component({
    templateUrl: './list-furniture.component.html',
})
export class ListFurnitureComponent implements OnInit {

    furniture: Furniture[];
    constructor(private router: Router, private furnitureService: FurnitureService) { }

    ngOnInit() {
        this.furnitureService.getFurniture()
            .subscribe(data => {
                this.furniture = data;
            });
    }
}
