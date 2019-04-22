import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.scss']
})
export class RestaurantComponent implements OnInit {

  displayedColumns: string[] = ['name', 'address', 'phone', 'email','button'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor() { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }
}

export interface PeriodicElement {
  _id: string;
  name: string;
  address: string;
  phone: string;
  email: string;
  facebook: string;
  rating: number;
  products: Object[];
}

const ELEMENT_DATA: PeriodicElement[] = [
  {
    _id: '5cab09a336cd340006316f78',
    name: 'Bobabob',
    address: 'Linh Trung, Thu Duc, TP.HCM',
    phone: '0202020202',
    email: 'bobapop@gmail.com',
    facebook: 'www.facebook.com/bobabob',
    rating: 4,
    products: [
      {
        id: '1',
        name: 'Tra Sua Truyen Thong',
        price: 15000,
        category: 'Tra Sua'
      },
      {
        id: '2',
        name: 'Tra Sua Tran Chau',
        price: 18000,
        category: 'Tra Sua'
      }
    ]
  },
  {
  _id: '5cab09a336cd340006316f78',
    name: 'Bobabob',
    address: 'Linh Trung, Thu Duc, TP.HCM',
    phone: '0202020202',
    email: 'bobapop@gmail.com',
    facebook: 'www.facebook.com/bobabob',
    rating: 4,
    products: [
      {
        id: '1',
        name: 'Tra Sua Truyen Thong',
        price: 15000,
        category: 'Tra Sua'
      },
      {
        id: '2',
        name: 'Tra Sua Tran Chau',
        price: 18000,
        category: 'Tra Sua'
      }
    ]
  },
  {
    _id: '5cab09a336cd340006316f78',
      name: 'Bobabob',
      address: 'Linh Trung, Thu Duc, TP.HCM',
      phone: '0202020202',
      email: 'bobapop@gmail.com',
      facebook: 'www.facebook.com/bobabob',
      rating: 4,
      products: [
        {
          id: '1',
          name: 'Tra Sua Truyen Thong',
          price: 15000,
          category: 'Tra Sua'
        },
        {
          id: '2',
          name: 'Tra Sua Tran Chau',
          price: 18000,
          category: 'Tra Sua'
        }
      ]
    },
    {
      _id: '5cab09a336cd340006316f78',
        name: 'Bobabob',
        address: 'Linh Trung, Thu Duc, TP.HCM',
        phone: '0202020202',
        email: 'bobapop@gmail.com',
        facebook: 'www.facebook.com/bobabob',
        rating: 4,
        products: [
          {
            id: '1',
            name: 'Tra Sua Truyen Thong',
            price: 15000,
            category: 'Tra Sua'
          },
          {
            id: '2',
            name: 'Tra Sua Tran Chau',
            price: 18000,
            category: 'Tra Sua'
          }
        ]
      },
      {
        _id: '5cab09a336cd340006316f78',
          name: 'Bobabob',
          address: 'Linh Trung, Thu Duc, TP.HCM',
          phone: '0202020202',
          email: 'bobapop@gmail.com',
          facebook: 'www.facebook.com/bobabob',
          rating: 4,
          products: [
            {
              id: '1',
              name: 'Tra Sua Truyen Thong',
              price: 15000,
              category: 'Tra Sua'
            },
            {
              id: '2',
              name: 'Tra Sua Tran Chau',
              price: 18000,
              category: 'Tra Sua'
            }
          ]
        },
        {
          _id: '5cab09a336cd340006316f78',
            name: 'Bobabob',
            address: 'Linh Trung, Thu Duc, TP.HCM',
            phone: '0202020202',
            email: 'bobapop@gmail.com',
            facebook: 'www.facebook.com/bobabob',
            rating: 4,
            products: [
              {
                id: '1',
                name: 'Tra Sua Truyen Thong',
                price: 15000,
                category: 'Tra Sua'
              },
              {
                id: '2',
                name: 'Tra Sua Tran Chau',
                price: 18000,
                category: 'Tra Sua'
              }
            ]
          },
          {
            _id: '5cab09a336cd340006316f78',
              name: 'Bobabob',
              address: 'Linh Trung, Thu Duc, TP.HCM',
              phone: '0202020202',
              email: 'bobapop@gmail.com',
              facebook: 'www.facebook.com/bobabob',
              rating: 4,
              products: [
                {
                  id: '1',
                  name: 'Tra Sua Truyen Thong',
                  price: 15000,
                  category: 'Tra Sua'
                },
                {
                  id: '2',
                  name: 'Tra Sua Tran Chau',
                  price: 18000,
                  category: 'Tra Sua'
                }
              ]
            }
];
