import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { Restaurant } from "../service/restaurant.schema";
import { RestaurantapiService } from "../service/restaurantapi.service";

@Component({
  selector: "app-restaurant-list",
  templateUrl: "./restaurant-list.component.html",
  styleUrls: ["./restaurant-list.component.scss"]
})
export class RestaurantListComponent implements OnInit {
  public restaurants: Restaurant[];
  public isLoadingResults = true;
  displayedColumns: string[] = ["Img", "Name", "Address", "Action"];

  constructor(public router: Router, public api: RestaurantapiService) {}

  ngOnInit() {
    if (this.api.isLogedIn == false) {
      this.router.navigate(["login"]);
    }
    this.api.getRestaurants().subscribe(
      res => {
        this.restaurants = res.data;
        console.log(this.restaurants);
        this.isLoadingResults = false;
      },
      err => {
        console.log(err);
        this.isLoadingResults = false;
      }
    );
  }
}
