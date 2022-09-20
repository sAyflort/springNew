import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/Page";
import {Product} from "../model/Product";

@Component({
  selector: 'app-product-service',
  templateUrl: './product-service.component.html',
  styleUrls: ['./product-service.component.scss']
})
export class ProductServiceComponent implements OnInit {

  constructor(private http: HttpClient) { }

  public findAll() {
    return this.http.get<Page>("api/v1/products")
  }

  public findById(id: number) {
    return this.http.get<Product>('api/v1/products/${id}/id');
  }

  public save(product : Product) {
    return this.http.put<Product>('api/v1/products', product)
  }

  ngOnInit(): void {
  }

}
