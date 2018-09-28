import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Purchase } from '../models/purchase.model';
import { PurchaseService } from './purchase.service';

@Component({
  templateUrl: './purchase.component.html',
})
export class PurchaseComponent implements OnInit {
    purchaseList: Purchase[];
  selectedBuyer: number = 0;
  buyers = [
      {
          "buyerId": 1,
          "buyerName": "Mario",
          "buyerAddress": "NN",
          "buyerPhone": "123456789",
          "buyerPersonalId": "123456"
      },
      {
          "buyerId": 2,
          "buyerName": "Maria",
          "buyerAddress": "NN",
          "buyerPhone": "123456789",
          "buyerPersonalId": "123456"
      },
      {
          "buyerId": 3,
          "buyerName": "pepe",
          "buyerAddress": "nn",
          "buyerPhone": "4567897",
          "buyerPersonalId": "4444444"
      },
      {
          "buyerId": 4,
          "buyerName": "sisi",
          "buyerAddress": "nn",
          "buyerPhone": "4444444",
          "buyerPersonalId": "5555555"
      }
      ];

  furniture = [
      {
          "furnitureId": 1,
          "furnitureName": "office Desk II",
          "furnitureCode": "OD1000"
      },
      {
          "furnitureId": 2,
          "furnitureName": "office Desk III",
          "furnitureCode": "OD1001"
      },
      {
          "furnitureId": 3,
          "furnitureName": "Computer desk III",
          "furnitureCode": "OD1001"
      },
      {
          "furnitureId": 4,
          "furnitureName": "Kitchen table",
          "furnitureCode": "KT1000"
      },
      {
          "furnitureId": 5,
          "furnitureName": "Garden table",
          "furnitureCode": "GT1000"
      },
      {
          "furnitureId": 6,
          "furnitureName": "Office Cup",
          "furnitureCode": "OC1000"
      },
      {
          "furnitureId": 7,
          "furnitureName": "Phone table",
          "furnitureCode": "PT1000"
      },
      {
          "furnitureId": 8,
          "furnitureName": "Phone table II",
          "furnitureCode": "PT1001"
      },
      {
          "furnitureId": 9,
          "furnitureName": "Phone table III",
          "furnitureCode": "PT1002"
      }
  ]
  constructor(private router:Router, private purchaseService: PurchaseService) { }

  ngOnInit() {
  }

  purchaseFurniture(): void {
      this.purchaseService.purchaseFurniture(this.purchaseList)
          .subscribe( data => {
              var furnitureList = [];
              // $('[id*=tblCustomers] tr:not(:has(th))').each(function (index, item) {
              //     var furnitureId = this.item[index].furnitureId;
              //     var quantity = this.item[index].quantity;
              //     var selected = $(item).find('[id*=ddlYesNo] option:selected').val();
              //     if(selected === 'yes'){
              //         var furniture={};
              //         furniture["furnitureId"] = furnitureId;
              //         furniture["quantity"] = quantity;
              //         furnitureList.push(furniture);
              //     }
              // });
              // let purchase: Purchase = new Purchase();
              // let purchases: Purchase[] = [];
              // for(let i=0; i<furnitureList.length; i++){
              //     purchase.quantity = furnitureList[i].quantity;
              //     purchase.purchaseDate = Date.now().toString();
              //     purchase.buyer = new Buyer();
              //     purchase.buyer.buyerId = this.selectedBuyer.toString();
              //     purchase.furniture = furnitureList[i];
              //
              //     purchases.push(purchase)
              // }
              // this.purchaseList = purchases;
              alert("Furniture Purchased Successfully!!!");
          })
  }

    selectchange(args):void{
        this.selectedBuyer = args.target.value;
    }
}
