import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Furniture } from '../models/furniture.model';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class FurnitureService {

    constructor(private http:HttpClient) {}

    private furnitureBaseUrl = 'http://localhost:8080';

    public getFurniture() {
        return this.http.get<Furniture[]>(this.furnitureBaseUrl+ "/ListFurniture");
    }

    public deleteFurniture(furniture) {
        return this.http.delete(this.furnitureBaseUrl + "/DeleteFurniture/" + furniture.furnitureId);
    }

    public createFurniture(furniture) {
        return this.http.post<Furniture>(this.furnitureBaseUrl + "/AddFurniture", furniture);
    }
}
