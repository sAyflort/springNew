import { Component, OnInit } from '@angular/core';
import {ProductServiceComponent} from "../product-service/product-service.component";
import {Product} from "../model/Product";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  product = new Product(null, "", 0);

  constructor(private productService: ProductServiceComponent,
            private  route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.productService.findById(param['id'])
        .subscribe(res => {
          this.product = res;
        }, error => {
          console.log(error)
        })
    })
  }
}
