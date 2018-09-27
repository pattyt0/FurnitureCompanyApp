import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {UserComponent } from './user/user.component';
import {AddUserComponent} from './user/add-user.component';

import {ListBuyerComponent} from "./buyer/list-buyer.component";
import {CreateBuyerComponent} from './buyer/create-buyer.component';

import {ListFurnitureComponent} from "./furniture/list-furniture.component";

const routes: Routes = [
    { path: 'users', component: UserComponent },
    { path: 'add', component: AddUserComponent },
    { path: 'ListBuyer', component: ListBuyerComponent },
    { path: 'AddBuyer', component: CreateBuyerComponent },
    { path: 'ListFurniture', component: ListFurnitureComponent }
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
