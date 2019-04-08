# README

- API Server; 13.70.1.53
- MongoDB: Database: backend

- Set only one email exist in collection users:
 `db.users.createIndex( { "email": 1 }, { unique: true } )`

 1. Để tạo user mới:
 Gửi POST Request vào url: `http://13.70.1.53/users`
 Body có dạng:

 ```JSON
 {
    "email": "peter@gmail.com",
    "password": "peter",
    "fullname": "Peter William",
    "phone": "047833902323",
    "avatar": ""
 }
 ```

 Trả về thông tin người dùng đăng kí thành công. Sau đó nên chuyển người dùng qua giao diện đăng nhập để lấy JWT Token.

 2. Để nhận JWT token xác thực:
 Gửi POST request vào url: `http://13.70.1.53/authentication`
 body có dạng:

 ```JSON
 {
    "strategy": "local",
    "email": "peter@gmail.com",
    "password": "peter"
 }
 ```

 Trả về chuỗi token xác thực.
 Kết quả trả về có dạng:

 ```JSON
 {
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6ImFjY2VzcyJ9.eyJ1c2VySWQiOiI1Y2FiMDY5YWEzMzdlZDAwMDY5YjYzODMiLCJpYXQiOjE1NTQ3MTc3MTEsImV4cCI6MTU1NDgwNDExMSwiYXVkIjoiaHR0cHM6Ly95b3VyZG9tYWluLmNvbSIsImlzcyI6ImZlYXRoZXJzIiwic3ViIjoiYW5vbnltb3VzIiwianRpIjoiMzkzMTk3N2QtZGMxZi00Yjc3LTk0NjAtMjkyNWUzNTM1MzQwIn0.8J_ojtsQWv1Xl_RCwkjNv3Qer2eLgnKdWjWerU0hWgI"
 }
 ```
 
 3. Để lấy thông tin user:
 Gửi GET request vào url: `http://13.70.1.53/<user_id>`
 Header có kèm theo JWT Token
 Kết quả trả về:

 ```JSON
 {
    "_id": "5caaffe1a337ed00069b637e",
    "email": "admin@gmail.com",
    "fullname": "Super Admin",
    "phone": "00009999",
    "avatar": "",
    "role": "c2VjcmV0Y29kZQo="
 }
 ```

 4. Để chỉnh sửa thông tin cá nhân của user (chỉ user mới có quyền chỉnh sửa thông tin của mình):
 Gửi PUT request vào url: `http://13.70.1.53/users/<user_id>`
 Header có chứa token đã lấy được.
 body có dạng:

 ```JSON
 {
    "email": "peter@gmail.com",
    "password": "peter",
    "fullname": "Peter William",
    "phone": "047833902323",
    "avatar": ""
 }
 ```

 Kết quả trả về có dạng:

 ```JSON
 {
    "_id": "5cab069aa337ed00069b6383",
    "email": "peter@gmail.com",
    "fullname": "Peter William",
    "phone": "099999323",
    "avatar": ""
 }
 ```

5. Để lấy danh sách cửa hàng và các món ăn của cửa hàng
 Gửi GET request vào url: `http://13.70.1.53/restaurants`
 Kết quả trả về có dạng:

 ```JSON
 {
    "total": 2,
    "limit": 10,
    "skip": 0,
    "data": [
        {
            "_id": "5cab094736cd340006316f77",
            "name": "Feel Coffee",
            "address": "Linh Trung, Thu Duc, TP.HCM",
            "phone": "02020202345",
            "email": "feelcoffee@gmail.com",
            "facebook": "www.facebook.com/feelcoffee",
            "rating": 5,
            "products": [
                {
                    "id": "1",
                    "name": "Tra Sua Truyen Thong",
                    "price": 18000,
                    "category": "Tra Sua"
                },
                {
                    "id": "2",
                    "name": "Tra Sua Tran Chau Cang",
                    "price": 25000,
                    "category": "Tra Sua"
                }
            ]
        },
        {
            "_id": "5cab09a336cd340006316f78",
            "name": "Bobabob",
            "address": "Linh Trung, Thu Duc, TP.HCM",
            "phone": "0202020202",
            "email": "bobapop@gmail.com",
            "facebook": "www.facebook.com/bobabob",
            "rating": 4,
            "products": [
                {
                    "id": "1",
                    "name": "Tra Sua Truyen Thong",
                    "price": 15000,
                    "category": "Tra Sua"
                },
                {
                    "id": "2",
                    "name": "Tra Sua Tran Chau",
                    "price": 18000,
                    "category": "Tra Sua"
                }
            ]
        }
    ]
 }
 ```

6. Để order món ăn:
 Gửi POST request vào url: `http://13.70.1.53/orders`
 Header chứa token đã lấy được.
 body có dạng:

 ```JSON
 {
	"userId" : "5cab069aa337ed00069b6383",
	"total" : 50000,
	"products" : [
		{
			"restaurantId" : "5ca9cdb21c5a4d5fe816ac71",
			"productId" : "2",
			"name" : "Tra Sua Tran Chau Cang",
			"price" : 25000,
			"quantity" : 2
		}
	]
 }
 ```

 Thông tin về restaurantId và productId có trong chuỗi JSON trả về khi lấy danh sách cửa hàng và các món ăn trong cửa hàng. Việc tính giá cho khách thực hiện ở app.
 Kết quả trả về có dạng:

 ```JSON
 {
    "userId": "5cab069aa337ed00069b6383",
    "total": 43000,
    "products": [
        {
            "restaurantId": "5cab094736cd340006316f77",
            "productId": "2",
            "name": "Tra Sua Tran Chau Cang",
            "price": 25000,
            "quantity": 1
        },
        {
            "restaurantId": "5cab094736cd340006316f77",
            "productId": "1",
            "name": "Tra Sua Truyen Thong",
            "price": 18000,
            "quantity": 1
        }
    ],
    "createdAt": "2019-04-08T10:08:26.535Z",
    "_id": "5cab1d9a36cd340006316f7c"
 }
 ```

7. Để tra cứu lịch sử đặt món:
 Gửi GET request vào url: `http://13.70.1.53/orders`
 Header chứa token đã lấy được của khách hàng đó.
 Body để trống
 Kết quả trả về có dạng:

 ```JSON
 {
    "total": 2,
    "limit": 10,
    "skip": 0,
    "data": [
        {
            "_id": "5cab1c5036cd340006316f7a",
            "userId": "5cab069aa337ed00069b6383",
            "total": 50000,
            "products": [
                {
                    "restaurantId": "5cab094736cd340006316f77",
                    "productId": "2",
                    "name": "Tra Sua Tran Chau Cang",
                    "price": 25000,
                    "quantity": 2
                }
            ],
            "createdAt": "2019-04-08T10:02:56.094Z"
        },
        {
            "_id": "5cab1cce36cd340006316f7b",
            "userId": "5cab069aa337ed00069b6383",
            "total": 75000,
            "products": [
                {
                    "restaurantId": "5cab094736cd340006316f77",
                    "productId": "2",
                    "name": "Tra Sua Tran Chau Cang",
                    "price": 25000,
                    "quantity": 3
                }
            ],
            "createdAt": "2019-04-08T10:05:02.644Z"
        }
    ]
 }
 ```
 