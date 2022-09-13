import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/Page";

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

  ngOnInit(): void {
  }

}
