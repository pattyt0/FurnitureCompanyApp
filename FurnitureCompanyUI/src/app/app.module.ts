import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AddUserComponent } from './user/add-user.component';
import { UserComponent } from './user/user.component';
import { BuyerComponent } from './buyer/buyer.component';
import { ListBuyerComponent } from './buyer/list-buyer.component';

import { AppRoutingModule } from './app.routing.module';

import {UserService} from './user/user.service';

import {HttpClientModule} from "@angular/common/http";
import {BuyerService} from "./buyer/buyer.service";


@NgModule({
  declarations: [
      AppComponent,
      UserComponent,
      BuyerComponent,
      ListBuyerComponent,
      AddUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService, BuyerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
