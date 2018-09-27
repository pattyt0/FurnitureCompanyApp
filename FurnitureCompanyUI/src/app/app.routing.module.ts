import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {ListBuyerComponent} from "./buyer/list-buyer.component";
import {CreateBuyerComponent} from './buyer/create-buyer.component';

import {ListFurnitureComponent} from "./furniture/list-furniture.component";
import {CreateFurnitureComponent} from "./furniture/create-furniture.component";

import {ListPromotionalPeriodComponent} from "./promotional-period/list-promotional-period.component";
import {CreatePromotionalPeriodComponent} from "./promotional-period/create-promotional-period.component";

import {PurchaseComponent} from "./purchase/purchase.component";

const routes: Routes = [
    { path: 'ListBuyer', component: ListBuyerComponent },
    { path: 'AddBuyer', component: CreateBuyerComponent },
    { path: 'ListFurniture', component: ListFurnitureComponent },
    { path: 'AddFurniture', component: CreateFurnitureComponent },
    { path: 'AddPurchases', component: PurchaseComponent },
    { path: 'ListPromotionalPeriod', component: ListPromotionalPeriodComponent },
    { path: 'AddPromotionalPeriod', component: CreatePromotionalPeriodComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
