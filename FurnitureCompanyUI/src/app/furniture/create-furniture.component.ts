import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Furniture } from '../models/furniture.model';
import { FurnitureService } from './furniture.service';

@Component({
    templateUrl: './create-furniture.component.html',
})
export class CreateFurnitureComponent implements OnInit {

    furniture: Furniture = new Furniture();
    constructor(private router:Router, private furnitureService: FurnitureService) { }

    ngOnInit(): void {
    }

    createFurniture(): void {
        this.furnitureService.createFurniture(this.furniture)
            .subscribe( data => {
                alert("Furniture created successfully!!!");
            })
    }
}
