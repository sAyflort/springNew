import { Component, OnInit } from '@angular/core';
import {ProductServiceComponent} from "../product-service/product-service.component";
import {Product} from "../model/Product";
import {ActivatedRoute, Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  product = new Product(null, "", 0);
  error = false;
  errorMessage = "";

  constructor(private productService: ProductServiceComponent,
            private  route: ActivatedRoute,
            private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.productService.findById(param['id'])
        .subscribe(res => {
          this.product = res;
          this.error = false;
          this.errorMessage = "";
        }, error => {
          console.log(error)
          this.error = true;
          this.errorMessage = error.error;
        })
    })
  }

  save() {
    this.productService.save(this.product)
      .subscribe(res => {
        console.log(res)
        this.router.navigateByUrl('/products')
        this.error = false;
        this.errorMessage = "";
      }, error => {
        console.log(error)
        this.error = true;
        this.errorMessage = error.error;
      })
  }
}
