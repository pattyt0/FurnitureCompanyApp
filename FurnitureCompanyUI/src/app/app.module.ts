import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AddUserComponent } from './user/add-user.component';
import { UserComponent } from './user/user.component';
import { CreateBuyerComponent } from './buyer/create-buyer.component';
import { ListBuyerComponent } from './buyer/list-buyer.component';
import { ListFurnitureComponent } from './furniture/list-furniture.component';

import { AppRoutingModule } from './app.routing.module';

import {UserService} from './user/user.service';

import {HttpClientModule} from "@angular/common/http";
import {BuyerService} from "./buyer/buyer.service";
import {FurnitureService} from "./furniture/furniture.service";


@NgModule({
  declarations: [
      AppComponent,
      UserComponent,
      CreateBuyerComponent,
      ListBuyerComponent,
      ListFurnitureComponent,
      AddUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService, BuyerService, FurnitureService],
  bootstrap: [AppComponent]
})
export class AppModule { }
