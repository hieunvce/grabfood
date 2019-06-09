import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { Restaurant } from "../service/restaurant.schema";
import { RestaurantapiService } from "../service/restaurantapi.service";
import { MatPaginatorModule, MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";

@Component({
  selector: "app-restaurant-list",
  templateUrl: "./restaurant-list.component.html",
  styleUrls: ["./restaurant-list.component.scss"]
})
export class RestaurantListComponent implements OnInit {
  public restaurants: Restaurant[];
  public isLoadingResults = true;
  displayedColumns: string[] = ["Img", "Name", "Address", "Action"];
  public dataSource = new MatTableDataSource<Restaurant>(this.restaurants);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor(public router: Router, public api: RestaurantapiService) {}

  ngOnInit() {
    if (this.api.isLogedIn == false) {
      this.router.navigate(["login"]);
    }
    this.api.getRestaurants().subscribe(
      res => {
        this.restaurants = res;
        this.dataSource = new MatTableDataSource<Restaurant>(this.restaurants);
        this.dataSource.paginator = this.paginator;
        console.log(this.restaurants);
        this.isLoadingResults = false;
      },
      err => {
        console.log(err);
        this.isLoadingResults = false;
      }
    );
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
