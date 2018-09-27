import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { PromotionalPeriod } from '../models/promotional-period.model';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class PromotionalPeriodService {

    constructor(private http:HttpClient) {}

    private promotionalPeriodBaseUrl = 'http://localhost:8080';

    public getPromotionalPeriod() {
        return this.http.get<PromotionalPeriod[]>(this.promotionalPeriodBaseUrl+ "/ListPromotionalPeriod");
    }

    public createPromotionalPeriod(promotionalPeriod) {
        return this.http.post<PromotionalPeriod>(this.promotionalPeriodBaseUrl + "/AddPromotionalPeriod", promotionalPeriod);
    }
}
