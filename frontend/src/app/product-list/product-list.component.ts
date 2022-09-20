import { Component, OnInit } from '@angular/core';
import {Product} from "../model/Product";
import {ProductServiceComponent} from "../product-service/product-service.component";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductServiceComponent) { }

  ngOnInit(): void {
    this.productService.findAll()
      .subscribe(response => {
        this.products = response.content;
      }, error => {
        console.log(error);
      })
  }

  delete(id: number | null) {

  }

}
