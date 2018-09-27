import {Buyer} from "./buyer.model";
import {Furniture} from "./furniture.model";

export class Purchase{
    purchaseId: string;
    quantity: number;
    purchaseDate: string;
    buyer: Buyer;
    furniture: Furniture;
}