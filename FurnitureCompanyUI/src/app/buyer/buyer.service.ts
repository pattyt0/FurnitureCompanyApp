import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Buyer } from '../models/buyer.model';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class BuyerService {

    constructor(private http:HttpClient) {}

    private buyerBaseUrl = 'http://localhost:8080';

    public getBuyers() {
        return this.http.get<Buyer[]>(this.buyerBaseUrl+ "/ListBuyer");
    }

    public deleteBuyer(buyer) {
        return this.http.delete(this.buyerBaseUrl + "/DeleteBuyer/"+ buyer.id);
    }

    public createBuyer(buyer) {
        return this.http.post<Buyer>(this.buyerBaseUrl + "/AddBuyer", buyer);
    }
}
