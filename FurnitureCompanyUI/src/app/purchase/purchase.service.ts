import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Purchase } from '../models/purchase.model';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class PurchaseService {

    constructor(private http:HttpClient) {}

    private purchaseBaseUrl = 'http://localhost:8080';

    public purchaseFurniture(purchases) {
        return this.http.post<Purchase[]>(this.purchaseBaseUrl + "/AddPurchases", purchases);
    }
}
