import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {UserComponent } from './user/user.component';
import {AddUserComponent} from './user/add-user.component';

import {ListBuyerComponent} from "./buyer/list-buyer.component";

const routes: Routes = [
    { path: 'users', component: UserComponent },
    { path: 'add', component: AddUserComponent },
    { path: 'ListBuyer', component: ListBuyerComponent }
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
