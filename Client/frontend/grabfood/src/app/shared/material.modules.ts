import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatToolbarModule,
  MatIconModule,
  MatCardModule,
  MatDialogModule,
  MatInputModule,
  MatTableModule,
  MatMenuModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatFormFieldModule
} from '@angular/material';
import { MatPaginatorModule } from "@angular/material/paginator";
@NgModule({
  imports: [
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    MatTableModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatFormFieldModule
  ],
  exports: [
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    MatTableModule,
    MatMenuModule,
    MatProgressSpinnerModule
  ]
})
export class AngularMaterialModule {}
