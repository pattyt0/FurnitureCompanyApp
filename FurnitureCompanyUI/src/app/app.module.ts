import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CreateBuyerComponent } from './buyer/create-buyer.component';
import { ListBuyerComponent } from './buyer/list-buyer.component';
import { ListFurnitureComponent } from './furniture/list-furniture.component';
import { CreateFurnitureComponent } from './furniture/create-furniture.component';
import { PurchaseComponent } from './purchase/purchase.component';
import { ListPromotionalPeriodComponent } from './promotional-period/list-promotional-period.component';
import { CreatePromotionalPeriodComponent } from './promotional-period/create-promotional-period.component';

import { AppRoutingModule } from './app.routing.module';


import {HttpClientModule} from "@angular/common/http";
import {BuyerService} from "./buyer/buyer.service";
import {FurnitureService} from "./furniture/furniture.service";
import {PurchaseService} from "./purchase/purchase.service";
import {PromotionalPeriodService} from "./promotional-period/promotional-period.service";


@NgModule({
  declarations: [
      AppComponent,
      CreateBuyerComponent,
      ListBuyerComponent,
      ListFurnitureComponent,
      CreateFurnitureComponent,
      PurchaseComponent,
      ListPromotionalPeriodComponent,
      CreatePromotionalPeriodComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [BuyerService, FurnitureService, PurchaseService, PromotionalPeriodService],
  bootstrap: [AppComponent]
})
export class AppModule { }
