import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: "app-restaurant-navbar",
  templateUrl: "./restaurant-navbar.component.html",
  styleUrls: ["./restaurant-navbar.component.scss"]
})
export class RestaurantNavbarComponent implements OnInit {
  constructor(public router: Router) {}

  ngOnInit() {}

  logOut() {
    localStorage.clear();
    this.router.navigate(["home"]);
  }
}
