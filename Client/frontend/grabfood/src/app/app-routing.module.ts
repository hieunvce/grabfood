import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {RestaurantComponent} from './restaurant/restaurant.component';
import {UserComponent} from './user/user.component';
import {HelpComponent} from './help/help.component';
import {AboutComponent} from './about/about.component';


const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'restaurant', component: RestaurantComponent},
  { path: 'user', component: UserComponent},
  { path: 'help', component: HelpComponent},
  { path: 'about', component: AboutComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
